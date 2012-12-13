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
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
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
import org.springframework.web.bind.annotation.RequestMethod;
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



	//HttpServletResponse response
	@RequestMapping(value = {"index",""}, method = GET)
	public String openView( HttpServletResponse response,
			@CookieValue(value="companyID",defaultValue="") String companyID_String,			
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

			if(company == null)
			{
				Cookie cookie = new Cookie("companyID", null); // Not necessary, but saves bandwidth.				
				cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
				response.addCookie(cookie);
				model.addAttribute("companyList", companyService.getAllCompanies());
				model.addAttribute("displaymessage", "Company does not exist");
				return "companies";
			}

			model.addAttribute("companyID", companyID);	
			model.addAttribute("company", company);	
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
			model.addAttribute("testrunDisplayName", company.getTestrunDisplayName());
			model.addAttribute("testplansDisplayName", company.getTestplansDisplayName());	
			model.addAttribute("testcasesDisplayName", company.getTestcasesDisplayName());	
			model.addAttribute("testplanDisplayName", company.getTestplanDisplayName());	
			model.addAttribute("testcaseDisplayName", company.getTestcaseDisplayName());
			
			if(company.getProjects() == null || company.getProjects().isEmpty())
			{
				userpath = "Home>"+company.getProjectsDisplayName();
				model.addAttribute("userpath",userpath);
			}
			if(userpath.isEmpty())			
			{
				System.out.println("HERE 1 :" + userpath);					
				return "index"; 
			}
			else
			{
				boolean allCompanyProjects = true;	
				boolean allCompanyTestplans = true;	
				Long projectID = null;
				Long testplanID = null;

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
								newuserpath = newuserpath + ">"+company.getProjectsDisplayName().replace(" ","");
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#013953>"+company.getProjectsDisplayName()+"</font></a> >";
								newuserpath = newuserpath + ">"+project.getProjectID();								
								
								//newuserpath = newuserpath + ">"+company.getProjectsDisplayName();
								//breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'><font color=#013953>"+company.getProjectsDisplayName()+"</font></a> >";
								//newuserpath = newuserpath + ">"+project.getProjectID();


								gridUrl = "/summaryList/"+companyID+"?projectID="+projectID;
								relatedObjects = "?projectID="+projectID;
								allCompanyProjects = false;
								x++;
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5	
									breadCrumb = breadCrumb + "<font color=#666666>"+project.getProjectName()+"</font>";
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									model.addAttribute("project", project);	
									model.addAttribute("defects", (projectService.getCascadedDefects(projectID) == null) ? 0 : projectService.getCascadedDefects(projectID).size());	
									model.addAttribute("sev1", (projectService.getCascadedSev1Defects(projectID) == null) ? 0 : projectService.getCascadedSev1Defects(projectID).size());	
									model.addAttribute("sev2", (projectService.getCascadedSev2Defects(projectID) == null) ? 0 : projectService.getCascadedSev2Defects(projectID).size());	
									model.addAttribute("sev3", (projectService.getCascadedSev3Defects(projectID) == null) ? 0 : projectService.getCascadedSev3Defects(projectID).size());	
									model.addAttribute("sev4", (projectService.getCascadedSev4Defects(projectID) == null) ? 0 : projectService.getCascadedSev4Defects(projectID).size());	
									return "projectDetails";
								}
								else
								{
									breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#539909>"+project.getProjectName()+"</font></a> >";
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
							newuserpath = newuserpath + ">"+company.getProjectsDisplayName().replace(" ","");
							breadCrumb = breadCrumb + " <font color=#013953>"+company.getProjectsDisplayName()+"</font>";

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
								newuserpath = newuserpath + ">"+company.getCyclesDisplayName().replace(" ","");
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#013953>"+company.getCyclesDisplayName()+"</font></a> >";

								newuserpath = newuserpath + ">"+cycle.getCycleID();
								//breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'><font color=#539909>"+cycle.getCycleName()+"</font></a> >";

								gridUrl = "/summaryList/"+companyID+"?cycleID="+cycleID;
								relatedObjects = "?cycleID="+cycleID;	

								x++;								
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5	
									breadCrumb = breadCrumb + "<font color=#666666>"+cycle.getCycleName()+"</font>";
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									model.addAttribute("cycle", cycle);
									model.addAttribute("ccRules", cycle.getChangeImpactRules());
									return "cycleDetails";
								}
								else
								{
									breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#539909>"+cycle.getCycleName()+"</font></a> >";
								}
								
							}
							else
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}							

							newuserpath = newuserpath + ">"+company.getCyclesDisplayName().replace(" ","");
							breadCrumb = breadCrumb + " <font color=#013953>"+company.getCyclesDisplayName()+"</font>";

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
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "cycle"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("allCompanyProjects", allCompanyProjects);
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
								newuserpath = newuserpath + ">"+company.getTestplansDisplayName().replace(" ","");
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#013953>"+company.getTestplansDisplayName()+"</font></a> >";

								newuserpath = newuserpath + ">"+testplanID;
								//breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'><font color=#539909>"+testplan.getTestplanName()+"</font></a> >";

								gridUrl = "/summaryList/"+companyID+"?testplanID="+testplanID;
								relatedObjects = "?testplanID="+testplanID;							

								allCompanyTestplans = false;
								x++;								
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5	
									breadCrumb = breadCrumb + " <font color=#666666>"+testplan.getTestplanName()+"</font>";
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									model.addAttribute("testplan", testplan);
									return "testplanDetails";
								}
								else
								{
									breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#539909>"+testplan.getTestplanName()+"</font></a> >";
								}
															
							}
							else
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Cycles>33>Testplans
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}							
							newuserpath = newuserpath + ">"+company.getTestplansDisplayName().replace(" ","");
							breadCrumb = breadCrumb + " <font color=#666666>"+company.getTestplansDisplayName()+"</font>";
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "testplan"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("allCompanyProjects", allCompanyProjects);
							if(!relatedObjects.isEmpty())
							{
								String relatedItem = relatedObjects.substring(relatedObjects.indexOf("?")+1,relatedObjects.indexOf("ID"));
								String relatedItemID = relatedObjects.substring(relatedObjects.indexOf("=")+1,relatedObjects.length());
								model.addAttribute("relatedItem", relatedItem);
								model.addAttribute("relatedItemID", relatedItemID);
								Set<Testplan> existingTestplans = testplanService.getExistingTestplans(companyID,relatedItem,relatedItemID);								
								if(existingTestplans != null && !existingTestplans.isEmpty())
								{									
									model.addAttribute("existingTestplans", existingTestplans);
								}								
								Set<Testplan> newTestplans = testplanService.getAvailableTestplans(companyID,relatedItem,relatedItemID);								
								if(newTestplans != null && !newTestplans.isEmpty())
								{
									model.addAttribute("newTestplans",newTestplans);
								}

							}		
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
								newuserpath = newuserpath + ">"+company.getTestcasesDisplayName().replace(" ","");
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#013953>"+company.getTestcasesDisplayName()+"</font></a> >";

								newuserpath = newuserpath + ">"+testcaseID;
								//breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'><font color=#539909>"+testcaseID+"</font></a> >";

								gridUrl = "/summaryList/"+companyID+"?testcaseID="+testcaseID;
								relatedObjects = "?testcaseID="+testcaseID;					

								//allCompanyProjects = false;
								x++;								
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5	
									breadCrumb = breadCrumb + " <font color=#666666>"+testcaseID+"</font>";
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									model.addAttribute("testcase", testcase);
									return "testcaseDetails";
								}
								else
								{
									breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#539909>"+testcaseID+"</font></a> >";
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
							newuserpath = newuserpath + ">"+company.getTestcasesDisplayName().replace(" ","");
							breadCrumb = breadCrumb + " <font color=#666666>"+company.getTestcasesDisplayName()+"</font>";
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "testcase"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("allCompanyProjects", allCompanyProjects);
							model.addAttribute("allCompanyTestplans", allCompanyTestplans);
							model.addAttribute("projects", company.getProjects());
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
							userPathArray[x].replace(" ","").equalsIgnoreCase("all"+company.getTestrunDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase("required"+company.getTestrunsDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase("required"+company.getTestrunDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase("optional"+company.getTestrunsDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase("optional"+company.getTestrunDisplayName().replace(" ","")))
					{		
						String required = "";						
						if(userPathArray[x].replace(" ","").equalsIgnoreCase("required"+company.getTestrunsDisplayName().replace(" ","")) ||
								userPathArray[x].replace(" ","").equalsIgnoreCase("required"+company.getTestrunDisplayName().replace(" ","")) )
						{
							required = "Required";							
						}
						else if(userPathArray[x].replace(" ","").equalsIgnoreCase("optional"+company.getTestrunsDisplayName().replace(" ","")) ||
								userPathArray[x].replace(" ","").equalsIgnoreCase("optional"+company.getTestrunDisplayName().replace(" ","")) )
						{
							required = "Optional";
						}
						else
						{
							required = "All";
						}												

						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Tesruns>"5">Cycles
							Long testrunID = Long.valueOf(userPathArray[x+1]);
							try{
								Testrun testrun = testrunService.getTestrun(testrunID);

								newuserpath = newuserpath + ">"+company.getTestrunsDisplayName().replace(" ","");
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#013953>"+required+" "+company.getTestrunsDisplayName()+"</font></a> >";
								newuserpath = newuserpath + ">"+testrun.getTestrunID();
								//breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'><font color=#539909>"+testrun.getTestrunName()+"</font></a> >";

								gridUrl = "/summaryList/"+companyID+"?required="+required+"&testrunID="+testrunID;
								relatedObjects = "?required="+required+"&testrunID="+testrunID;
								allCompanyProjects = false;
								x++;								
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5	
									breadCrumb = breadCrumb + " <font color=#666666>"+testrun.getTestrunName()+"</font>";
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									model.addAttribute("testrun", testrun);
									return "testrunDetails";
								}
								else
								{
									breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#539909>"+testrun.getTestrunName()+"</font></a> >";
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
								gridUrl = "/summaryList/"+companyID+"?required="+required;
							}
							if(!relatedObjects.isEmpty())
							{
								String relatedItem = relatedObjects.substring(relatedObjects.indexOf("?")+1,relatedObjects.indexOf("ID"));
								String relatedItemID = relatedObjects.substring(relatedObjects.indexOf("=")+1,relatedObjects.length());
								model.addAttribute("relatedItem", relatedItem);
								model.addAttribute("relatedItemID", relatedItemID);
								Set<Testrun> existingTestruns = testrunService.getExistingTestruns(companyID,relatedItem,relatedItemID,required);								
								if(existingTestruns != null && !existingTestruns.isEmpty())
								{									
									model.addAttribute("existingTestruns", existingTestruns);
								}								
								Set<Testrun> newTestruns = testrunService.getAvailableTestruns(companyID,relatedItem,relatedItemID,required);								
								if(newTestruns != null && !newTestruns.isEmpty())
								{
									model.addAttribute("newTestruns",newTestruns);
								}
								relatedObjects = relatedObjects + "&required="+required;

							}		
							else
							{
								relatedObjects = "?required="+required;
							}

							newuserpath = newuserpath + ">"+company.getTestrunsDisplayName().replace(" ","");
							breadCrumb = breadCrumb + " <font color=#666666>"+required+" "+company.getTestrunsDisplayName()+"</font>";
							Set<Project> companyProjects = company.getProjects();
							model.addAttribute("projects", companyProjects);
							model.addAttribute("required", required);
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "testrun"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	

							return "testruns";	// straight to projects.html				
						}
					}
					//   *********************************      END of Testruns        ************************************

					//   *********************************        Requirement          ************************************
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getRequirementDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase(company.getRequirementsDisplayName().replace(" ","")))
					{										
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Cycles>"CD 1">Projects
							Long requirementID = Long.valueOf(userPathArray[x+1]);

							Requirement requirement = requirementService.getRequirement(requirementID);
							if(requirement != null)
							{
								newuserpath = newuserpath + ">"+company.getRequirementsDisplayName().replace(" ","");
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#013953>"+company.getRequirementsDisplayName()+"</font></a> >";

								newuserpath = newuserpath + ">"+requirementID;
								//breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'><font color=#539909>"+requirementID+"</font></a> >";

								gridUrl = "/summaryList/"+companyID+"?requirementID="+requirementID;
								relatedObjects = "?requirementID="+requirementID;					

								//allCompanyProjects = false;
								x++;															
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5	
									breadCrumb = breadCrumb + " <font color=#666666>"+requirementID+"</font>";
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									model.addAttribute("requirement", requirement);
									return "requirementDetails";
								}
								else
								{
									breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#539909>"+requirementID+"</font></a> >";
								}
							}
							else
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Requirements
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}							
							newuserpath = newuserpath + ">"+company.getRequirementsDisplayName();
							breadCrumb = breadCrumb + " <font color=#666666>"+company.getRequirementsDisplayName()+"</font>";
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "requirement"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("projects", company.getProjects());								
							return "requirements";						
						}
					}
					//   *********************************    END of Requirements     ************************************
					//   *********************************        Defect          ************************************
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getDefectDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase(company.getDefectsDisplayName().replace(" ","")))
					{										
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Cycles>"CD 1">Projects
							Long defectID = Long.valueOf(userPathArray[x+1]);

							Defect defect = defectService.getDefect(defectID);
							if(defect != null)
							{
								newuserpath = newuserpath + ">"+company.getDefectsDisplayName().replace(" ","");
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#013953>"+company.getDefectsDisplayName()+"</font></a> >";

								newuserpath = newuserpath + ">"+defectID;
								//breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'><font color=#539909>"+defectID+"</font></a> >";

								gridUrl = "/summaryList/"+companyID+"?defectID="+defectID;
								relatedObjects = "?defectID="+defectID;					

								//allCompanyProjects = false;
								x++;							
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5	
									breadCrumb = breadCrumb + " <font color=#666666>"+defectID+"</font>";
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									model.addAttribute("defect", defect);
									return "defectDetails";
								}
								else
								{
									breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#539909>"+defectID+"</font></a> >";
								}														
							}
							else
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Defects
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}							
							newuserpath = newuserpath + ">"+company.getDefectsDisplayName().replace(" ","");
							breadCrumb = breadCrumb + " <font color=#666666>"+company.getDefectsDisplayName()+"</font>";
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "defect"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("projects", company.getProjects());	
							return "defects";						
						}
					}
					//   *********************************    END of Defects     ************************************
					//   *********************************        Environment          ************************************
					if(userPathArray[x].replace(" ","").equalsIgnoreCase(company.getEnvironmentDisplayName().replace(" ","")) ||
							userPathArray[x].replace(" ","").equalsIgnoreCase(company.getEnvironmentsDisplayName().replace(" ","")))
					{										
						if(x < (userPathArray.length - 1))
						{// there is a details view in the userpath i.e //Home>Cycles>"CD 1">Projects
							Long environmentID = Long.valueOf(userPathArray[x+1]);

							Environment environment = environmentService.getEnvironment(environmentID);
							if(environment != null)
							{
								newuserpath = newuserpath + ">"+company.getEnvironmentsDisplayName().replace(" ","");
								breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#013953>"+company.getEnvironmentsDisplayName()+"</font></a> >";

								newuserpath = newuserpath + ">"+environmentID;
								//breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'><font color=#539909>"+environmentID+"</font></a> >";

								gridUrl = "/summaryList/"+companyID+"?environmentID="+environmentID;
								relatedObjects = "?environmentID="+environmentID;					

								//allCompanyProjects = false;
								x++;								
								if(x == (userPathArray.length - 1))
								{// there is a details view in the userpath i.e //Home>Projects>5	
									breadCrumb = breadCrumb + " <font color=#666666>"+environmentID+"</font>";
									model.addAttribute("userpath", newuserpath);
									model.addAttribute("breadCrumb", breadCrumb);
									model.addAttribute("gridUrl","project"+gridUrl);	
									model.addAttribute("relatedObjects", relatedObjects);	
									model.addAttribute("environment", environment);
									return "environmentDetails";
								}
								else
								{
									breadCrumb = breadCrumb + " <a href='?userpath="+newuserpath+"'> <font color=#539909>"+environmentID+"</font></a> >";
								}
							}
							else
							{
								model.addAttribute("userpath", userpath);
								return "index";
							}
						}	
						else
						{// Last item i.e //Home>Projects>5>Environments
							if(gridUrl.isEmpty())
							{
								gridUrl = "/summaryList/"+companyID;
							}							
							newuserpath = newuserpath + ">"+company.getEnvironmentsDisplayName().replace(" ","");
							breadCrumb = breadCrumb + " <font color=#666666>"+company.getEnvironmentsDisplayName()+"</font>";
							model.addAttribute("userpath", newuserpath);
							model.addAttribute("breadCrumb", breadCrumb);
							model.addAttribute("gridUrl", "environment"+gridUrl);
							model.addAttribute("relatedObjects", relatedObjects);	
							model.addAttribute("projects", company.getProjects());	
							return "environments";						
						}
					}
					//   *********************************    END of Environments     ************************************

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
		Company company = companyService.getCompany(companyID);
		model.addAttribute("company", company);
		return "redirect:index.html"; 			
	}

	@RequestMapping(value = {"logout"}, method = GET)
	public String logout( HttpServletResponse response,
			@CookieValue(value="companyID",defaultValue="") String companyID_String,			
			@RequestParam(value="userpath",defaultValue="") String userpath,
			@RequestParam(required = false) String displaymessage, Model model)	
	{	
		model.addAttribute("companyList", companyService.getAllCompanies());
		model.addAttribute("displaymessage", displaymessage);
		Cookie cookie = new Cookie("companyID", null); // Not necessary, but saves bandwidth.				
		cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
		response.addCookie(cookie);
		return "companies";
	}

	@RequestMapping(value = {"viewbill"}, method = GET)
	public String viewCompanyBill(@RequestParam(required = true) Long companyID,
			@RequestParam(required = false) String errormessage,
			String successmessage,Model model) 
	{	
		Company company = companyService.getCompany(companyID);		
		model.addAttribute("company", company);			
		return "viewbill";
	} 

	@RequestMapping(value = {"newcompany"}, method = GET)
	public String newCompany(@RequestParam(required = false) String errormessage,String successmessage,Model model) {	
		model.addAttribute("errormessage", errormessage);
		return "newcompany";
	} 

	@RequestMapping(value = {"editcompany"}, method = GET)
	public String editCompany(@RequestParam(required = true) Long companyID,
			@RequestParam(required = false) String errormessage,
			String successmessage,Model model) 
	{	
		model.addAttribute("company", companyService.getCompany(companyID));
		model.addAttribute("errormessage", errormessage);
		return "editcompany";
	} 

	@RequestMapping(value = {"addnewcompany"}, method = GET)
	public String addNewCompany(HttpServletResponse response,
			@RequestParam(required = true) String companyName,
			@RequestParam(required = true) String projectDisplayName,
			@RequestParam(required = true) String projectsDisplayName,
			@RequestParam(required = true) String cycleDisplayName,
			@RequestParam(required = true) String cyclesDisplayName,
			@RequestParam(required = true) String reportDisplayName,
			@RequestParam(required = true) String reportsDisplayName,
			@RequestParam(required = true) String defectDisplayName,
			@RequestParam(required = true) String defectsDisplayName,			
			@RequestParam(required = true) String requirementDisplayName,											 
			@RequestParam(required = true) String requirementsDisplayName,			
			@RequestParam(required = true) String environmentDisplayName,
			@RequestParam(required = true) String environmentsDisplayName,			
			@RequestParam(required = true) String userDisplayName,
			@RequestParam(required = true) String usersDisplayName,
			@RequestParam(required = true) String testLibraryDisplayName,			
			@RequestParam(required = true) String testplanDisplayName,
			@RequestParam(required = true) String testplansDisplayName,
			@RequestParam(required = true) String testcaseDisplayName,
			@RequestParam(required = true) String testcasesDisplayName,	    		
			@RequestParam(required = true) String testrunDisplayName,
			@RequestParam(required = true) String testrunsDisplayName,
			@RequestParam(required = true) String testerDisplayName,
			@RequestParam(required = true) String testersDisplayName,
			@RequestParam(required = true) String seniorTesterDisplayName,
			@RequestParam(required = true) String seniorTestersDisplayName,			
			@RequestParam(required = true) String developerDisplayName,
			@RequestParam(required = true) String developersDisplayName,			
			@RequestParam(required = true) String seniorDeveloperDisplayName,
			@RequestParam(required = true) String seniorDevelopersDisplayName,				    	
			Model model) 
	{	
		Company company =  new Company(companyName,
				projectDisplayName, projectsDisplayName,
				reportDisplayName,reportsDisplayName,
				defectDisplayName,defectsDisplayName,
				requirementDisplayName,requirementsDisplayName,
				cycleDisplayName,cyclesDisplayName,
				userDisplayName,usersDisplayName,
				environmentDisplayName,environmentsDisplayName,
				testplanDisplayName,testplansDisplayName,
				testcaseDisplayName,testcasesDisplayName,	
				testrunDisplayName,testrunsDisplayName,
				testerDisplayName,testersDisplayName,
				developerDisplayName,developersDisplayName,
				seniorTesterDisplayName,seniorTestersDisplayName,
				seniorDeveloperDisplayName,seniorDevelopersDisplayName,										
				testLibraryDisplayName);  

		companyService.addNewCompany(company);

		Cookie cookie = new Cookie("companyID",Long.toString(company.getCompanyID()));
		cookie.setMaxAge(60*60); //1 hour
		response.addCookie(cookie);

		model.addAttribute("company", company);
		return "redirect:index.html"; 		

	}

	@RequestMapping(value = {"editexistingcompany"}, method = GET)
	public String editExistingCompany(HttpServletResponse response,
			@RequestParam(required = true) Long companyID,
			@RequestParam(required = true) String companyName,
			@RequestParam(required = true) String projectDisplayName,
			@RequestParam(required = true) String projectsDisplayName,
			@RequestParam(required = true) String cycleDisplayName,
			@RequestParam(required = true) String cyclesDisplayName,
			@RequestParam(required = true) String reportDisplayName,
			@RequestParam(required = true) String reportsDisplayName,
			@RequestParam(required = true) String defectDisplayName,
			@RequestParam(required = true) String defectsDisplayName,			
			@RequestParam(required = true) String requirementDisplayName,											 
			@RequestParam(required = true) String requirementsDisplayName,			
			@RequestParam(required = true) String environmentDisplayName,
			@RequestParam(required = true) String environmentsDisplayName,			
			@RequestParam(required = true) String userDisplayName,
			@RequestParam(required = true) String usersDisplayName,
			@RequestParam(required = true) String testLibraryDisplayName,			
			@RequestParam(required = true) String testplanDisplayName,
			@RequestParam(required = true) String testplansDisplayName,
			@RequestParam(required = true) String testcaseDisplayName,
			@RequestParam(required = true) String testcasesDisplayName,	    		
			@RequestParam(required = true) String testrunDisplayName,
			@RequestParam(required = true) String testrunsDisplayName,
			@RequestParam(required = true) String testerDisplayName,
			@RequestParam(required = true) String testersDisplayName,
			@RequestParam(required = true) String seniorTesterDisplayName,
			@RequestParam(required = true) String seniorTestersDisplayName,			
			@RequestParam(required = true) String developerDisplayName,
			@RequestParam(required = true) String developersDisplayName,			
			@RequestParam(required = true) String seniorDeveloperDisplayName,
			@RequestParam(required = true) String seniorDevelopersDisplayName,		    	
			Model model) 
	{	

		Company company = companyService.getCompany(companyID);

		company.setCompanyName(companyName);
		company.setProjectDisplayName(projectDisplayName);
		company.setProjectsDisplayName(projectsDisplayName);
		company.setCycleDisplayName(cycleDisplayName);
		company.setCyclesDisplayName(cyclesDisplayName);
		company.setReportDisplayName(reportDisplayName);
		company.setReportsDisplayName(reportsDisplayName);
		company.setDefectDisplayName(defectDisplayName);
		company.setDefectsDisplayName(defectsDisplayName);
		company.setRequirementDisplayName(requirementDisplayName);
		company.setRequirementsDisplayName(requirementsDisplayName);
		company.setEnvironmentDisplayName(environmentDisplayName);
		company.setEnvironmentsDisplayName(environmentsDisplayName);
		company.setUserDisplayName(userDisplayName);
		company.setUsersDisplayName(usersDisplayName);
		company.setTestcaseDisplayName(testcaseDisplayName);
		company.setTestcasesDisplayName(testcasesDisplayName);
		company.setTestplanDisplayName(testplanDisplayName);
		company.setTestplansDisplayName(testplansDisplayName);
		company.setTestrunDisplayName(testrunDisplayName);
		company.setTestrunsDisplayName(testrunsDisplayName);
		company.setTesterDisplayName(testerDisplayName);
		company.setTestersDisplayName(testersDisplayName);
		company.setSeniorTesterDisplayName(seniorTesterDisplayName);
		company.setSeniorTestersDisplayName(seniorTestersDisplayName);
		company.setDeveloperDisplayName(developerDisplayName);
		company.setDevelopersDisplayName(developersDisplayName);
		company.setSeniorDeveloperDisplayName(seniorDeveloperDisplayName);
		company.setSeniorDevelopersDisplayName(seniorDevelopersDisplayName);
		company.setTestLibraryDisplayName(testLibraryDisplayName);

		companyService.update(company);	     		  

		model.addAttribute("companyID", company.getCompanyID());	
		model.addAttribute("company", company);	
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
		model.addAttribute("testrunDisplayName", company.getTestrunDisplayName());
		model.addAttribute("testplansDisplayName", company.getTestplansDisplayName());	
		model.addAttribute("testcasesDisplayName", company.getTestcasesDisplayName());	
		model.addAttribute("testplanDisplayName", company.getTestplanDisplayName());	
		model.addAttribute("testcaseDisplayName", company.getTestcaseDisplayName());		
		model.addAttribute("company", company);

		Cookie cookie = new Cookie("companyID",Long.toString(company.getCompanyID()));
		cookie.setMaxAge(60*60); //1 hour
		response.addCookie(cookie);		

		return "redirect:index.html"; 		

	}  

	@RequestMapping(value = {"deletecompany"}, method = GET)
	public String deleteTestrun(HttpServletResponse response,
			@RequestParam(value="companyID", required=true) Long companyID) 
	{		
		companyService.remove(companyID);
		Cookie cookie = new Cookie("companyID", null); 			
		cookie.setMaxAge(0); 
		response.addCookie(cookie);
		return "redirect:companies.html"; 
	}

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