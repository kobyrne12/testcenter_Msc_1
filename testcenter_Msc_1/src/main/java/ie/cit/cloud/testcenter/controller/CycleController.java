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
public class CycleController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CycleService cycleService;

	@RequestMapping(value = { "cycles"}, method = GET)
	public String TESTProject(@RequestParam(required = false) String errormessage,String successmessage,long projectID,Model model) 
	{
		Project project = projectService.getProject(projectID);
		Long companyID = project.getCompanyID();
		Company company = companyService.getCompany(companyID); 		
		model.addAttribute("companyID", companyID);  
		model.addAttribute("projectsDisplayName", company.getProjectsDisplayName());
		model.addAttribute("reportsDisplayName", company.getReportsDisplayName());
		model.addAttribute("defectsDisplayName", company.getDefectsDisplayName());
		model.addAttribute("requirementsDisplayName", company.getRequirementsDisplayName());
		model.addAttribute("cyclesDisplayName", company.getCyclesDisplayName());
		model.addAttribute("usersDisplayName", company.getUsersDisplayName());
		model.addAttribute("environmentsDisplayName", company.getEnvironmentsDisplayName());		
		model.addAttribute("columnModel", cycleService.getColumnModelAndNames(companyID));
		model.addAttribute("cyclesUrl", "cycle/summaryList/"+projectID);	
		return "cycles";
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