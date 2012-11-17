/**
 * 
 */
package ie.cit.cloud.testcenter.controller.ajax;

/**
 * @author byrnek1
 *
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.service.project.ProjectService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Handles and retrieves the main requests
 */
@Controller
public class ProjectAjaxController {
	@Autowired
	private ProjectService projectService;  

	/**
	 * Handles request for create a new project 
	 */
	@RequestMapping(value = "/newprojectAJAX", method = RequestMethod.POST)
	public @ResponseBody String addNewProjectAJAX(
			@RequestParam(value="companyID", required=true) long companyID,
			@RequestParam(value="projectName", required=true) String projectName,
			@RequestParam(value="regression", required=true) int regression,
			@RequestParam(value="newFeature", required=true) int newFeature,
			@RequestParam(value="minSev1", required=true) int minSev1,
			@RequestParam(value="minSev2", required=true) int minSev2,
			@RequestParam(value="minSev3", required=true) int minSev3,
			@RequestParam(value="minSev4", required=true) int minSev4,
			Model model) 
	{
		boolean alreadyExists = false;
		try
		{  
			Project project = projectService.getProjectByName(projectName);		
			if(project.getCompanyID() == companyID )
			{				
				alreadyExists = true;
			}
		}
		catch(NoResultException e)
		{   			
			System.out.println("No Cycle Exists with that name "); 			
		}
		if(alreadyExists == false)
		{
			try{    					
				projectService.addNewProject(new Project(companyID,projectName,0,regression,newFeature,minSev1,minSev2,minSev3,minSev4,GetDateNow(),getCurrentUser()));
				return "ok";   

			}
			catch(ConstraintViolationException CVE)
			{   			
				System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations()); 				
				return CVE.getConstraintViolations().toString();
			}		
		}
		else
		{
			return projectName + " already Exists";	 
		}		
	}
	/**
	 * Handles empty Result 
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
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
}