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

import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Queue;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;


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
public class CycleController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CycleService cycleService;
	
	
	@RequestMapping(value = { "cycles"}, method = GET)
	public String showCycles(@CookieValue("companyID") String companyID_String,
			@CookieValue(required = false, value="projectID") String projectID_String,
			@RequestParam(required = false) String userPath, Model model) 
	{	
		System.out.println("&&&&&&&&&&&&&&&&&& : "+ userPath);
		if(companyID_String == null)
		{
			return "NO COMPANY COOKIE";
		}
		else
		{
			String breadCrumb = "";
			String gridUrl = "";
			boolean allCompanyProjects = true;
			Long projectID = null;
			Long companyID = Long.valueOf(companyID_String);
			Company company = companyService.getCompany(companyID);
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
			model.addAttribute("columnModel", cycleService.getColumnModelAndNames(companyID));	
			
			if(userPath == null)
			{
				if(projectID_String == null)
				{					
					//userPath = "Home:>:Cycles";		
					userPath = "Home:>:Projects:>:5:>:Cycles";
				}	
				else
				{						
					userPath = "Home:>:Projects:>:"+projectID_String+":>:Cycles";
				}				
			}			
			
			String[] breadCrumbArray = userPath.split(":>:");		
			gridUrl = "cycle/summaryList/"+companyID;
			for(int x=0; x <breadCrumbArray.length;x++)
			{
				if(breadCrumbArray[x].equalsIgnoreCase("Home"))
				{
					breadCrumb = "<a href='index.html'>Home</a> >";	
					if(x < (breadCrumbArray.length - 1))
					{// there is a details view 		
						breadCrumb = "<a href='index.html'>Home</a> >";							
					}	
					else
					{
						breadCrumb = "Home";	
					}
				}
				if(breadCrumbArray[x].equalsIgnoreCase("Projects"))
				{										
					if(x < (breadCrumbArray.length - 1))
					{// there is a details view 		
						breadCrumb = breadCrumb + " <a href='projects.html'>"+company.getProjectsDisplayName()+"</a> >";
						projectID = Long.valueOf(breadCrumbArray[x+1]);
						Project project = projectService.getProject(projectID);
						breadCrumb = breadCrumb + " <a href='projectsDetails.html'>"+project.getProjectName()+"</a> >";
						if(gridUrl.contains("?"))
						{
							gridUrl = gridUrl +  "&";
						}
						else
						{
							gridUrl = gridUrl +  "?";
						}
						gridUrl = gridUrl +  "projectID="+projectID;
						allCompanyProjects = false;
						x++;
					}	
					else
					{
						breadCrumb = breadCrumb + " "+company.getProjectsDisplayName();
					}
				}
				if(breadCrumbArray[x].equalsIgnoreCase("Cycles"))
				{					
					
					if(x < (breadCrumbArray.length - 1))
					{// there is a details view 
						// this is the list view of a single cycle
						breadCrumb = breadCrumb + " <a href='cycles.html'>"+company.getCyclesDisplayName()+"</a> >";
						Long cycleID = Long.valueOf(breadCrumbArray[x+1]);
						Cycle cycle = cycleService.getCycle(cycleID);
						breadCrumb = breadCrumb + " <a href='cyclesDetails.html'>"+cycle.getCycleName()+"</a> >";
						if(gridUrl.contains("?"))
						{
							gridUrl = gridUrl +  "&";
						}
						else
						{
							gridUrl = gridUrl +  "?";
						}
						gridUrl = gridUrl +  "cycleID="+cycleID;	
						x++;
					}		
					else
					{
						breadCrumb = breadCrumb + " "+company.getCyclesDisplayName();
					}
				}
				// TODO : complete rest  of related objects				
			}
			//breadCrumb : <a href='index.html'>Home</a> > <a href='cycles.html'>Cycles</a> 
			//gridUrl : "cycle/summaryList/"+companyID;

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
			model.addAttribute("breadCrumb", breadCrumb);
			model.addAttribute("gridUrl", gridUrl);		
			
			return "cycles";
		}

	}  

	private long getModelID(String url, String tcModel)
	{		
		int beginIndex = url.indexOf(tcModel);
		//System.out.println("*****(((((((( beginIndex : " + beginIndex);
		int endIndex = url.indexOf("&", beginIndex);
		//System.out.println("*****(((((((( endIndex : " + endIndex);	
		String fullRequestParam = url.substring(beginIndex, endIndex); //projectID=45689
		//System.out.println("*****(((((((( substring : " + fullRequestParam );	
		String[] tempStringArray = fullRequestParam.split("=");		
		//System.out.println("*****(((((((( id : " + tempStringArray[1] );	
		Long ID = Long.valueOf(tempStringArray[1]) ;		
		return ID;
	}
	public String GetDateNow()
	{ 	 
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime()); 
		//System.out.println("*********** Now the currentDate is :=>  " + currentDate);
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