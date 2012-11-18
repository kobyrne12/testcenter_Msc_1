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
import java.util.Collection;

import javax.persistence.NoResultException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    
    @RequestMapping(value = { "index", ""}, method = GET)    
    public String index(HttpServletResponse response,@RequestParam(required = false) Long companyID,Model model) {
    	if(companyID == null)
    	{
    		companyID = (long) 1;
    	}
    	
    	Cookie cookie = new Cookie("companyID",Long.toString(companyID));
		cookie.setMaxAge(60*60); //1 hour
		response.addCookie(cookie);
		
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
		Long projectID = (long) 1;
		if(projectID != null)
    	{
			model.addAttribute("projectID", projectID);			
    	}
    	return "index";    	
    }

    
    @RequestMapping(value = {"jsonprojectTEST"}, method = GET)
    public String jsonprojectTEST(@RequestParam(required = false)long companyID, Model model) {
    	model.addAttribute("company", companyService.getCompany(companyID));
    	return "jsonprojectTEST";  
  }  
    
    @RequestMapping(value = {"projectsListView"}, method = GET)
    public String projectListView(@RequestParam(required = false)long companyID, Model model) {
    	model.addAttribute("company", companyService.getCompany(companyID));
    	return "projectsListView";  
  }  
    
    @RequestMapping(value = {"newcompany"}, method = GET)
    public String newCompany(@RequestParam(required = false) String errormessage,String successmessage,Model model) {	
    	model.addAttribute("errormessage", errormessage);
    	return "newcompany";
    }   

    @RequestMapping(value = {"newcompany"}, method = POST)   
    public String createNewCompany(@RequestParam String companyName, Model model) {      	
    	try{
    		// Company already exists    	
    		Company company = companyService.getCompanyByName(companyName); 
    		model.addAttribute("errormessage", companyName+" already exists");      	
    		return "redirect:newcompany.html";	 
    	}
    	catch(NoResultException nre)
    	{
    		// No Company of this name Exist    		
    		try{        			
    			companyService.addNewCompany(new Company(companyName,GetDateNow(),getCurrentUser()));   		
    			model.addAttribute("successmessage", companyName+" Created");      	    	
    			return "redirect:viewcompanies.html";   	    		 
    		}
    		catch(ConstraintViolationException CVE)
    		{   			
    			model.addAttribute("errormessage", CVE.getMessage());   	    
    			return "redirect:newcompany.html";	
    		}
    	}    
    }       
    
    @RequestMapping(value = {"viewcompanies"}, method = GET)
    public String viewAllCompanies(@RequestParam(required = false) String errormessage,String successmessage,Model model) {
    	
    	Collection<Company> allCompanies = companyService.getAllCompanies();    	
    	if (allCompanies.isEmpty())
    	{
    		// No Company Exist  
    		model.addAttribute("errormessage", "No Companies exist");     	
    		return "redirect:newcompany.html";        	
    	}
    	else
    	{    		
    		model.addAttribute("errormessage", errormessage);
    		model.addAttribute("successmessage", successmessage);
	    	model.addAttribute("companies", allCompanies);    	
	    	return "viewcompanies";
    	}
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