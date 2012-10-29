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
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CompanyService companyService;

	// new Project
	@RequestMapping(value = { "newproject"}, method = GET)
	public String newProject(@RequestParam(required = false) String errormessage,String successmessage,long companyID,Model model) {	
		Company company = companyService.getCompany(companyID);
		model.addAttribute("errormessage", errormessage);
		model.addAttribute("company", company);
		return "newproject";
	}   


	///////////////////////////// test
	
	@RequestMapping(value = { "projects2"}, method = GET)
	public String TESTProject(@RequestParam(required = false) String errormessage,String successmessage,long companyID,Model model) {

		Company company = companyService.getCompany(companyID);    		
		//Collection<Project> allProjects = company.getProjects();
		Collection<Project> allProjects = projectService.getAllProjectsByCompanyID(companyID);
		String TempSuccessmessage = "";
		String TempErrormessage = "";
		model.addAttribute("projects", allProjects);		  
		model.addAttribute("companyID", companyID);  
		model.addAttribute("projectsDisplayName", company.getProjectsDisplayName());
		model.addAttribute("reportsDisplayName", company.getReportsDisplayName());
		model.addAttribute("defectsDisplayName", company.getDefectsDisplayName());
		model.addAttribute("requirementsDisplayName", company.getRequirementsDisplayName());
		model.addAttribute("cyclesDisplayName", company.getCyclesDisplayName());
		model.addAttribute("usersDisplayName", company.getUsersDisplayName());
		model.addAttribute("environmentsDisplayName", company.getEnvironmentsDisplayName());

		if(successmessage != null)
		{
			TempSuccessmessage =  successmessage;		
		}
		model.addAttribute("successmessage", TempSuccessmessage);

		if(successmessage != null)
		{
			TempErrormessage =  errormessage;		
		}
		else
		{
			if (allProjects.isEmpty())
			{
				// No Projects Exist   					
				model.addAttribute("errormessage", "No Projects exist");  				  	
			}
		}

		model.addAttribute("errormessage", errormessage);
		model.addAttribute("successmessage", TempErrormessage);	
		model.addAttribute("columnModel", projectService.getProjectColumnModelAndNames(companyID));

		return "projects2";


	}  
	///////////////
	// Create new Project()
	@RequestMapping(value = {"newproject"}, method = POST)     
	public String createNewProject(@RequestParam String projectName, long companyID, Model model) {  		
		try{
			// Project already exists    		
			System.out.println("here ---: "+ projectService.getProjectByName(projectName));   
			model.addAttribute("companyID", companyID);
			model.addAttribute("errormessage", projectName+" already exists");
			return "redirect:newproject.html";	 
		}
		catch(NoResultException nre)
		{
			// No Project of this name Exist
			try{    	

				projectService.addNewProject(new Project(companyID,projectName,0,96,94,10,25,50,100,GetDateNow(),getCurrentUser()));
				model.addAttribute("companyID", companyID);		
				model.addAttribute("projectName", projectName);		
				return "redirect:viewprojects.html";   

			}
			catch(ConstraintViolationException CVE)
			{   			
				System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations());    			
				model.addAttribute("companyID", companyID);
				model.addAttribute("errormessage",CVE.getConstraintViolations());
				return "redirect:newproject.html";
			}
		}

	}     

	@RequestMapping(value = { "viewprojects"}, method = GET)
	public String viewAllProjects(@RequestParam(required = false) String errormessage,String successmessage,String projectName,long companyID,Model model) {

		Company company = companyService.getCompany(companyID);    		
		//Collection<Project> allProjects = company.getProjects();
		Collection<Project> allProjects = projectService.getAllProjectsByCompanyID(companyID);
		if (allProjects.isEmpty())
		{
			// No Projects Exist   
			model.addAttribute("errormessage", "No Projects exist");   				
			model.addAttribute("companyID", companyID); 
			return "redirect:newproject.html";        	
		}
		else
		{
			model.addAttribute("errormessage", errormessage);
			model.addAttribute("successmessage", successmessage);
			model.addAttribute("projects", allProjects);		  
			model.addAttribute("companyID", companyID);  
			model.addAttribute("projectsDisplayName", (company.getProjectsDisplayName() != null) ?  company.getProjectsDisplayName() : "Projects");
			model.addAttribute("reportsDisplayName", company.getReportsDisplayName());
			model.addAttribute("defectsDisplayName", company.getDefectsDisplayName());
			model.addAttribute("requirementsDisplayName", company.getRequirementsDisplayName());
			model.addAttribute("cyclesDisplayName", company.getCyclesDisplayName());
			model.addAttribute("usersDisplayName", company.getUsersDisplayName());
			model.addAttribute("environmentsDisplayName", company.getEnvironmentsDisplayName());			
			model.addAttribute("successmessage", projectName +" Created");	
			return "viewprojects";
		}    

	}  
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = { "projects"}, method = GET)
	public String viewCompanyProjects(@RequestParam(required = false) String errormessage,String successmessage,long companyID,Model model) {

		Company company = companyService.getCompany(companyID);    		
		//Collection<Project> allProjects = company.getProjects();
		Collection<Project> allProjects = projectService.getAllProjectsByCompanyID(companyID);
		String TempSuccessmessage = "";
		String TempErrormessage = "";
		model.addAttribute("projects", allProjects);		  
		model.addAttribute("companyID", companyID);  
		model.addAttribute("projectsDisplayName", company.getProjectsDisplayName());
		model.addAttribute("reportsDisplayName", company.getReportsDisplayName());
		model.addAttribute("defectsDisplayName", company.getDefectsDisplayName());
		model.addAttribute("requirementsDisplayName", company.getRequirementsDisplayName());
		model.addAttribute("cyclesDisplayName", company.getCyclesDisplayName());
		model.addAttribute("usersDisplayName", company.getUsersDisplayName());
		model.addAttribute("environmentsDisplayName", company.getEnvironmentsDisplayName());

		if(successmessage != null)
		{
			TempSuccessmessage =  successmessage;		
		}
		model.addAttribute("successmessage", TempSuccessmessage);

		if(successmessage != null)
		{
			TempErrormessage =  errormessage;		
		}
		else
		{
			if (allProjects.isEmpty())
			{
				// No Projects Exist   					
				model.addAttribute("errormessage", "No Projects exist");  				  	
			}
		}

		model.addAttribute("errormessage", errormessage);
		model.addAttribute("successmessage", TempErrormessage);	
		model.addAttribute("columnModel", projectService.getProjectColumnModelAndNames(companyID));

		return "projects";


	}  
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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