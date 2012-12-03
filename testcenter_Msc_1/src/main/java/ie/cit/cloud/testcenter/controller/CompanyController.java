/**
 * 
 */
package ie.cit.cloud.testcenter.controller;

/**
 * @author byrnek1
 *
 */

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CycleService cycleService;
	@Autowired
	private TestcaseService testcaseService;
	@Autowired
	private TestplanService testplanService;
	@Autowired
	private TestrunService testrunService;
	@Autowired
	private DefectService defectService;
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private EnvironmentService environmentService;

	private Company company;


	@RequestMapping(value = {"companies"}, method = GET)
	public String openCompanySelection(	@RequestParam(required = false) String displaymessage, Model model)	
	{	
		try{			
			model.addAttribute("companyList", companyService.getAllCompanies());			
			model.addAttribute("displaymessage", displaymessage);
			return "companies";

		}catch(NoResultException nre)
		{			
			model.addAttribute("displaymessage", "NO COMPANIES FOUND");	
			return "newcompany";
		}		
	}

	@RequestMapping(value = {"selectcompany"}, method = GET)
	public String setCompany(HttpServletResponse response,
			@RequestParam Long companyID, Model model)	
	{	
		Cookie cookie = new Cookie("companyID",Long.toString(companyID));
		cookie.setMaxAge(60*60); //1 hour
		response.addCookie(cookie);
		return "redirect:index.html"; 			
	}


	//HttpServletResponse response
	@RequestMapping(value = {"index",""}, method = GET)
	public String openView( @CookieValue(value="companyID",defaultValue="") String companyID_String,			
			@RequestParam(value="userpath",defaultValue="") String userpath,
			@RequestParam(required = false) String displaymessage, Model model)	
	{		

		if(companyID_String.isEmpty())
		{		
			try{			
				model.addAttribute("companyList", companyService.getAllCompanies());
				model.addAttribute("displaymessage", displaymessage);
				return "companies";

			}catch(NoResultException nre)
			{	
				model.addAttribute("displaymessage", "NO COMPANIES FOUND");	
				return "newcompany";
			}						
		}
		else
		{						
			Long companyID = Long.valueOf(companyID_String);
			company = companyService.getCompany(companyID);
			model.addAttribute("companyID", companyID);	
			model.addAttribute("companyName", company.getCompanyName());	
			model.addAttribute("projectsDisplayName", company.getProjectsDisplayName());
			model.addAttribute("reportsDisplayName", company.getReportsDisplayName());
			model.addAttribute("defectsDisplayName", company.getDefectsDisplayName());
			model.addAttribute("requirementsDisplayName", company.getRequirementsDisplayName());
			model.addAttribute("cyclesDisplayName", company.getCyclesDisplayName());
			model.addAttribute("usersDisplayName", company.getUsersDisplayName());
			model.addAttribute("environmentsDisplayName", company.getEnvironmentsDisplayName());
			model.addAttribute("testLibraryDisplayName", company.getTestLibraryDisplayName());			
			model.addAttribute("testrunsDisplayName", company.getTestrunsDisplayName());	
			model.addAttribute("testplansDisplayName", company.getTestplansDisplayName());	
			model.addAttribute("testcasesDisplayName", company.getTestcasesDisplayName());	
			if(userpath.isEmpty())			
			{
				System.out.println("HERE 1 :" + userpath);								
				return "index"; 	
			}
			else
			{
				System.out.println("HERE 2 :" + userpath);

				boolean allCompanyProjects = true;	
				boolean allCompanyTestplans = true;	
				Long projectID = null;
				Long testplanID = null;
				//String relatedObjects = "";
				//String gridUrl = "/summaryList/"+companyID;
				String gridUrl = "";
				String relatedObjects = "";
				String breadCrumb = "";			
				String newuserpath = "Home";
				if (!userpath.toLowerCase().contains("home"))
				{
					userpath = "Home>"+userpath;				
				}
				//Home>Projects>5>Cycles
				String[] userPathArray = userpath.split(">");				

				for(int x=0; x <userPathArray.length;x++)
				{
					if(userPathArray[x].equalsIgnoreCase("Home"))
					{					
						if(x < (userPathArray.length - 1))
						{// there is a details view 		
							breadCrumb = "<a href='?userpath=Home'>Home</a> >";							
						}	
						else
						{
							breadCrumb = "Home";						
						}
					}
					//   *********************************        Projects          ************************************
					// id userpath contains project or projects 
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getProjectsDisplayName().replace(" ","")) || 
							userPathArray[x].replace(" ","").equalsIgnoreCase(company.getProjectDisplayName().replace(" ","")))
					{										
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Projects>"5">Cycles
							projectID = Long.valueOf(userPathArray[x+1]);
							try{
								Project project = projectService.getProject(projectID);
								newuserpath = newuserpath + ">"+company.getProjectsDisplayName();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+company.getProjectsDisplayName()+"</a> >";
								newuserpath = newuserpath + ">"+project.getProjectID();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+project.getProjectName()+"</a> >";

								//								if(gridUrl.contains("?"))
								//								{
								//									gridUrl = gridUrl +  "&";
								//								}
								//								else
								//								{
								//									gridUrl = gridUrl +  "?";
								//								}
								//								gridUrl = gridUrl +  "projectID="+projectID;

								//								if(relatedObjects.contains("?"))
								//								{
								//									relatedObjects = relatedObjects +  "&";
								//								}
								//								else
								//								{
								//									relatedObjects = relatedObjects +  "?";
								//								}
								//								relatedObjects = relatedObjects +  "projectID="+projectID;								
								gridUrl = "/summaryList/"+companyID+"?projectID="+projectID;
								relatedObjects = "?projectID="+projectID;
								allCompanyProjects = false;
								x++;
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5								
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									return "projectDetails";
								}
							}catch(NoResultException nre)
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Cycles>33>Projects
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}
							newuserpath = newuserpath + ">"+company.getProjectsDisplayName();
							breadCrumb = breadCrumb + " "+company.getProjectsDisplayName();

							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "project"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	

							return "projects";	// straight to projects.html				
						}
					}
					//   *********************************    END of projects     ************************************

					//   *********************************        Cycles          ************************************
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getCyclesDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase(company.getCycleDisplayName().replace(" ","")))
					{										
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Cycles>"CD 1">Projects
							Long cycleID = Long.valueOf(userPathArray[x+1]);

							Cycle cycle = cycleService.getCycle(cycleID);
							if(cycle != null)
							{
								newuserpath = newuserpath + ">"+company.getCyclesDisplayName();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+company.getCyclesDisplayName()+"</a> >";

								newuserpath = newuserpath + ">"+cycle.getCycleID();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+cycle.getCycleName()+"</a> >";

								gridUrl = "/summaryList/"+companyID+"?cycleID="+cycleID;
								relatedObjects = "?cycleID="+cycleID;							

								//allCompanyProjects = false;
								x++;
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5								
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl", "cycle"+gridUrl);
									model.addAttribute("relatedObjects", relatedObjects);	
									return "cycleDetails";
								}
							}
							else
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Cycles>33>Projects
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}							
							newuserpath = newuserpath + ">"+company.getCyclesDisplayName();
							breadCrumb = breadCrumb + " "+company.getCyclesDisplayName();
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "cycle"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("allCompanyProjects", allCompanyProjects);
							if(allCompanyProjects == true)
							{
								model.addAttribute("projects", company.getProjects());
							}
							else
							{
								Project project = projectService.getProject(projectID);
								model.addAttribute("projectID", projectID);	
								model.addAttribute("projects", project);
							}						
							return "cycles";						
						}
					}
					//   *********************************    END of Cycles     ************************************

					//   *********************************    Test Library          ************************************
					// id userpath contains project or projects 
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getTestLibraryDisplayName().replace(" ","")))
					{								
						breadCrumb = "<a href='?userpath=Home'>Home</a> > "+company.getTestLibraryDisplayName();							
						model.addAttribute("breadCrumb", breadCrumb);	
						return "testlibrary";	// straight to testlibrary.html				

					}
					//   *********************************    END of Test Library     ************************************
					//   *********************************        Test plans          ************************************
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getTestplansDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase(company.getTestplanDisplayName().replace(" ","")))
					{										
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Cycles>"CD 1">Projects
							testplanID = Long.valueOf(userPathArray[x+1]);

							Testplan testplan = testplanService.getTestplan(testplanID);
							if(testplan != null)
							{
								newuserpath = newuserpath + ">"+company.getTestplansDisplayName();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+company.getTestplansDisplayName()+"</a> >";

								newuserpath = newuserpath + ">"+testplanID;
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+testplan.getTestplanName()+"</a> >";

								gridUrl = "/summaryList/"+companyID+"?testplanID="+testplanID;
								relatedObjects = "?testplanID="+testplanID;							

								allCompanyTestplans = false;
								x++;
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5								
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl", "testplan"+gridUrl);
									model.addAttribute("relatedObjects", relatedObjects);	
									return "testplanDetails";
								}
							}
							else
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Cycles>33>Projects
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}							
							newuserpath = newuserpath + ">"+company.getTestplansDisplayName();
							breadCrumb = breadCrumb + " "+company.getTestplansDisplayName();
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "testplan"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("allCompanyProjects", allCompanyProjects);
							if(allCompanyProjects == true)
							{
								model.addAttribute("projects", company.getProjects());
							}
							else
							{
								Project project = projectService.getProject(projectID);
								model.addAttribute("projectID", projectID);	
								model.addAttribute("projects", project);
							}						
							return "testplans";						
						}
					}
					//   *********************************    END of Test plans     ************************************

					//   *********************************        Testcases          ************************************
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getTestcaseDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase(company.getTestcasesDisplayName().replace(" ","")))
					{										
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Cycles>"CD 1">Projects
							Long testcaseID = Long.valueOf(userPathArray[x+1]);

							Testcase testcase = testcaseService.getTestcase(testcaseID);
							if(testcase != null)
							{
								newuserpath = newuserpath + ">"+company.getTestcasesDisplayName();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+company.getTestcasesDisplayName()+"</a> >";

								newuserpath = newuserpath + ">"+testcaseID;
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+testcaseID+"</a> >";

								gridUrl = "/summaryList/"+companyID+"?testcaseID="+testcaseID;
								relatedObjects = "?testcaseID="+testcaseID;					

								//allCompanyProjects = false;
								x++;
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5								
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl", "testcase"+gridUrl);
									model.addAttribute("relatedObjects", relatedObjects);	
									return "testcaseDetails";
								}
							}
							else
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Testcases
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}							
							newuserpath = newuserpath + ">"+company.getTestcasesDisplayName();
							breadCrumb = breadCrumb + " "+company.getTestcasesDisplayName();
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "testcase"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("allCompanyProjects", allCompanyProjects);
							model.addAttribute("allCompanyTestplans", allCompanyTestplans);
							if(allCompanyProjects == true)
							{
								model.addAttribute("projects", company.getProjects());
							}
							else
							{
								Project project = projectService.getProject(projectID);
								model.addAttribute("projectID", projectID);	
								model.addAttribute("projects", project);
							}	
							if(allCompanyTestplans == true)
							{
								model.addAttribute("testplans", company.getTestplans());
							}
							else
							{
								Testplan testplan = testplanService.getTestplan(testplanID);
								model.addAttribute("testplanID", testplanID);	
								model.addAttribute("testplans", testplan);
							}	
							return "testcases";						
						}
					}
					//   *********************************    END of Testcases     ************************************

					//   *********************************        Testruns          ************************************
					// id userpath contains project or projects 
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getTestrunsDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase(company.getTestrunDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase("all"+company.getTestrunsDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase("all"+company.getTestrunDisplayName().replace(" ","")))
					{										
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Tesruns>"5">Cycles
							Long testrunID = Long.valueOf(userPathArray[x+1]);
							try{
								Testrun testrun = testrunService.getTestrun(testrunID);

								newuserpath = newuserpath + ">"+company.getTestrunsDisplayName();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+company.getTestrunsDisplayName()+"</a> >";
								newuserpath = newuserpath + ">"+testrun.getTestrunID();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+testrun.getTestrunName()+"</a> >";

								gridUrl = "/summaryList/"+companyID+"?testrunID="+testrunID;
								relatedObjects = "?testrunID="+testrunID;
								allCompanyProjects = false;
								x++;
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5								
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","testrun"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									return "testrunDetails";
								}
							}catch(NoResultException nre)
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Cycles>33>testruns
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}
							newuserpath = newuserpath + ">"+company.getTestrunsDisplayName();
							breadCrumb = breadCrumb + " "+company.getTestrunsDisplayName();

							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "testrun"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	

							return "testruns";	// straight to projects.html				
						}
					}
					//   *********************************      END of Testruns        ************************************
					//   *********************************       required Testruns          ************************************
					// id userpath contains project or projects 
					if(userPathArray[x].replace(" ","").equalsIgnoreCase("required"+company.getTestrunsDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase("required"+company.getTestrunDisplayName().replace(" ","")))
					{		
						model.addAttribute("required", "required");
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Tesruns>"5">Cycles
							Long testrunID = Long.valueOf(userPathArray[x+1]);
							try{
								Testrun testrun = testrunService.getTestrun(testrunID);

								newuserpath = newuserpath + ">"+company.getTestrunsDisplayName();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+company.getTestrunsDisplayName()+"</a> >";
								newuserpath = newuserpath + ">"+testrun.getTestrunID();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+testrun.getTestrunName()+"</a> >";

								gridUrl = "/summaryList/"+companyID+"?testrunID="+testrunID;
								relatedObjects = "?testrunID="+testrunID;
								allCompanyProjects = false;
								x++;
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5								
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","testrun"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									return "testrunDetails";
								}
							}catch(NoResultException nre)
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Cycles>33>testruns
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}
							newuserpath = newuserpath + ">"+company.getTestrunsDisplayName();
							breadCrumb = breadCrumb + " Required "+company.getTestrunsDisplayName();

							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "testrun"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	

							return "testruns";	// straight to projects.html				
						}
					}
					//   *********************************    END of  required Testruns     ************************************
					//   *********************************       optional Testruns          ************************************
					// id userpath contains project or projects 
					if(userPathArray[x].replace(" ","").equalsIgnoreCase("optional"+company.getTestrunsDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase("optional"+company.getTestrunDisplayName().replace(" ","")))
					{		
						model.addAttribute("required", "optional");
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Tesruns>"5">Cycles
							Long testrunID = Long.valueOf(userPathArray[x+1]);
							try{
								Testrun testrun = testrunService.getTestrun(testrunID);

								newuserpath = newuserpath + ">"+company.getTestrunsDisplayName();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+company.getTestrunsDisplayName()+"</a> >";
								newuserpath = newuserpath + ">"+testrun.getTestrunID();
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'>"+testrun.getTestrunName()+"</a> >";

								gridUrl = "/summaryList/"+companyID+"?testrunID="+testrunID;
								relatedObjects = "?testrunID="+testrunID;
								allCompanyProjects = false;
								x++;
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5								
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","testrun"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									return "testrunDetails";
								}
							}catch(NoResultException nre)
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Cycles>33>testruns
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}
							newuserpath = newuserpath + ">"+company.getTestrunsDisplayName();
							breadCrumb = breadCrumb + " Optional "+company.getTestrunsDisplayName();

							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "testrun"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	

							return "testruns";	// straight to projects.html				
						}
					}
					//   *********************************    END of  optional Testruns     ************************************

					// TODO : complete rest  of related objects				
				}
				return "index"; 
			}

		}  
	}

	@RequestMapping(value = {"login"}, method = GET)
	public String returnLogin(Model model) {   
		return "login";  
	}  

	//  
	//    
	//    @RequestMapping(value = {"jsonprojectTEST"}, method = GET)
	//    public String jsonprojectTEST(@RequestParam(required = false)Long companyID, Model model) {
	//    	model.addAttribute("company", companyService.getCompany(companyID));
	//    	return "jsonprojectTEST";  
	//  }  
	//    
	//    @RequestMapping(value = {"projectsListView"}, method = GET)
	//    public String projectListView(@RequestParam(required = false)Long companyID, Model model) {
	//    	model.addAttribute("company", companyService.getCompany(companyID));
	//    	return "projectsListView";  
	//  }  
	//    
	//    @RequestMapping(value = {"newcompany"}, method = GET)
	//    public String newCompany(@RequestParam(required = false) String errormessage,String successmessage,Model model) {	
	//    	model.addAttribute("errormessage", errormessage);
	//    	return "newcompany";
	//    }   
	//
	//    @RequestMapping(value = {"newcompany"}, method = POST)   
	//    public String createNewCompany(@RequestParam String companyName, Model model) {      	
	//    	try{
	//    		// Company already exists    	
	//    		Company company = companyService.getCompanyByName(companyName); 
	//    		model.addAttribute("errormessage", companyName+" already exists");      	
	//    		return "redirect:newcompany.html";	 
	//    	}
	//    	catch(NoResultException nre)
	//    	{
	//    		// No Company of this name Exist    		
	//    		try{        			
	//    			companyService.addNewCompany(new Company(companyName,new Date(),getCurrentUser()));   		
	//    			model.addAttribute("successmessage", companyName+" Created");      	    	
	//    			return "redirect:viewcompanies.html";   	    		 
	//    		}
	//    		catch(ConstraintViolationException CVE)
	//    		{   			
	//    			model.addAttribute("errormessage", CVE.getMessage());   	    
	//    			return "redirect:newcompany.html";	
	//    		}
	//    	}    
	//    }       

	//    @RequestMapping(value = {"viewcompanies"}, method = GET)
	//    public String viewAllCompanies(@RequestParam(required = false) String errormessage,String successmessage,Model model) {
	//    	
	//    	Set<Company> allCompanies = companyService.getAllCompanies();    	
	//    	if (allCompanies.isEmpty())
	//    	{
	//    		// No Company Exist  
	//    		model.addAttribute("errormessage", "No Companies exist");     	
	//    		return "redirect:newcompany.html";        	
	//    	}
	//    	else
	//    	{    		
	//    		model.addAttribute("errormessage", errormessage);
	//    		model.addAttribute("successmessage", successmessage);
	//	    	model.addAttribute("companies", allCompanies);    	
	//	    	return "viewcompanies";
	//    	}
	//    }      
	//    
	public Calendar GetCurentDate()
	{ 	     	
		Calendar currentDate = Calendar.getInstance();    	
		System.out.println("*********** Now the currentDate is :=>  " + currentDate);
		return currentDate;
	}
	public String GetDateNow()
	{ 	 
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime()); 
		System.out.println("*********** Now the currentDate is :=>  " + currentDate);
		System.out.println("*********** Now the dateNow is :=>  " + dateNow);
		return dateNow;
	}

	private String getCurrentUser() 
	{
		System.out.println("*********** Current User is :=>  " + SecurityContextHolder.getContext().getAuthentication().getName());
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}

}