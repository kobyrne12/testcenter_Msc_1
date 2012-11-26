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
import java.util.Set;

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
public class ProjectControllerNOTUSED {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CompanyService companyService;
	
//	@RequestMapping(value = { "projects"}, method = GET)
//	public String showProjects(@CookieValue("companyID") String companyID_String,			
//			@RequestParam(required = false) String gridUrl,Model model) 
//	{	
//		if(companyID_String == null)
//		{
//			model.addAttribute("displaymessage", "Session Timed out");	
//			return "companies";
//		}
//		try{
//			Long companyID = Long.valueOf(companyID_String);
//			Company company = companyService.getCompany(companyID);
//			model.addAttribute("companyID", companyID);	
//			model.addAttribute("companyName", company.getCompanyName());	
//			model.addAttribute("projectsDisplayName", company.getProjectsDisplayName());
//			model.addAttribute("reportsDisplayName", company.getReportsDisplayName());
//			model.addAttribute("defectsDisplayName", company.getDefectsDisplayName());
//			model.addAttribute("requirementsDisplayName", company.getRequirementsDisplayName());
//			model.addAttribute("cyclesDisplayName", company.getCyclesDisplayName());
//			model.addAttribute("usersDisplayName", company.getUsersDisplayName());
//			model.addAttribute("environmentsDisplayName", company.getEnvironmentsDisplayName());
//			model.addAttribute("testLibraryDisplayName", company.getTestLibraryDisplayName());			
//			model.addAttribute("testrunsDisplayName", company.getTestrunsDisplayName());	
//			model.addAttribute("columnModel", projectService.getColumnModelAndNames(companyID));				
//			if(gridUrl != null)				
//			{				
//				model.addAttribute("breadCrumb", "FORMAT URL");
//				model.addAttribute("gridUrl", gridUrl);			
//			}
//			else
//			{		
//				model.addAttribute("breadCrumb", "<a href='index.html'>Home</a> > Projects");				
//				model.addAttribute("gridUrl", "project/summaryList/"+companyID);	
//			}
//			return "projects";
//		}catch(NoResultException nre)
//		{
//			return "projects";
//		}		
//	}  	

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