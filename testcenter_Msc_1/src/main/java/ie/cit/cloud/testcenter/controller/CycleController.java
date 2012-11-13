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
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;

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
	private CycleService cycleService;
	@Autowired
	private CompanyService companyService;

	// new Cycle
	@RequestMapping(value = { "newcycle"}, method = GET)
	public String newCycle(@RequestParam(required = false) String errormessage,String successmessage,long projectID,Model model) {	
		Company company = companyService.getCompany(projectID);
		model.addAttribute("errormessage", errormessage);
		model.addAttribute("company", company);
		return "newcycle";
	}   
	
	
	
	// Create new Cycle()
	@RequestMapping(value = {"newcycle"}, method = POST)     
	public String createNewCycle(@RequestParam String cycleName, long projectID, Model model) {  		
		try{
			// Cycle already exists    		
			System.out.println("here ---: "+ cycleService.getCycleByName(cycleName));   
			model.addAttribute("projectID", projectID);
			model.addAttribute("errormessage", cycleName+" already exists");
			return "redirect:newcycle.html";	 
		}
		catch(NoResultException nre)
		{
			// No Cycle of this name Exist
			try{    
				/**
				 * @param cycleName
				 * @param projectID
				 * @param testruns
				 * @param requiredPriority
				 * @param projectPosition
				 * @param totalCycleEstTime
				 * @param cycleStartDate
				 * @param cycleEndDate
				 * @param codeChangeRule
				 * @param defectRule
				 * @param testHistoryRule
				 * @param requirementRule
				 * @param creationDate
				 * @param createdBy
				 * @param lastModifiedDate
				 * @param lastModifiedBy
				 */
				//long cycleID = cycleService.addNewCycle(new Cycle(cycleName, projectID,null, 0, 0, cycleID, cycleName,GetDateNow(),0, 0, 0, 0, getCurrentUser(), cycleName, cycleName, cycleName));
					
			//	Cycle cycle = cycleService.getCycle(cycleID);    			
			//	model.addAttribute("cycle", cycle);   
				model.addAttribute("projectID", projectID);  
				model.addAttribute("successmessage", cycleName+" Created");
				return "redirect:viewcycles.html";   

			}
			catch(ConstraintViolationException CVE)
			{   			
				System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations());    			
				model.addAttribute("projectID", projectID);
				model.addAttribute("errormessage",CVE.getConstraintViolations());
				return "redirect:newcycle.html";
			}
		}

	}     

	@RequestMapping(value = { "viewcycles"}, method = GET)
	public String viewAllCycles(@RequestParam(required = false) String errormessage,String successmessage,long projectID,Model model) {

		Company company = companyService.getCompany(projectID);    		
		//Collection<Cycle> allCycles = company.getCycles();
		Collection<Cycle> allCycles = cycleService.getAllCyclesByProjectID(projectID);
		if (allCycles.isEmpty())
		{
			// No Cycles Exist   
			model.addAttribute("errormessage", "No Cycles exist");   				
			model.addAttribute("projectID", projectID); 
			return "redirect:newcycle.html";        	
		}
		else
		{
			model.addAttribute("errormessage", errormessage);
			model.addAttribute("successmessage", successmessage);
			model.addAttribute("cycles", allCycles);  	
			model.addAttribute("company", company);
			return "viewcycles";
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