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

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.TestCaseService;
import ie.cit.cloud.testcenter.TestPlanService;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.TestPlan;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Handles and retrieves the main requests
 */
@Controller
public class AjaxController {
	 @Autowired
	 private TestCaseService testcaseService;
	 @Autowired
	 private TestPlanService testplanService;   
	 @Autowired
	 private ProjectService projectService;  
	 
	 /**
	  * Handles request for create a new project 
	  */
	 @RequestMapping(value = "/newprojectAJAX", method = RequestMethod.POST)
	 public @ResponseBody String addNewProjectAJAX(
			 @RequestParam(value="companyID", required=true) long companyID,
			 @RequestParam(value="projectName", required=true) String projectName,
			 @RequestParam(value="regression", required=true) Integer regression,
			 @RequestParam(value="newFeature", required=true) Integer newFeature,
			 @RequestParam(value="minSev1", required=true) Integer minSev1,
			 @RequestParam(value="minSev2", required=true) Integer minSev2,
			 @RequestParam(value="minSev3", required=true) Integer minSev3,
			 @RequestParam(value="minSev4", required=true) Integer minSev4,
			 Model model) 
	 {

		 System.out.println("************|||||||| ---: Received Ajax Request to create new Project");
		 try{
			 // Project already exists    		
			 System.out.println("here --- Project already exists: "+ projectService.getProjectByName(projectName));   
			 
			 return projectName + " already Exists";	 
		 }
		 catch(NoResultException nre)
		 {
			 // No Project of this name Exist
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

	 }
	/* 
	 @RequestMapping(value = "/deleteprojectAJAX/{id}", method = RequestMethod.DELETE)
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteTestCaseAt(@PathVariable("id") long id) {
		 System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% long id = " +id );
	    	projectService.remove(id);
	    }
	 */
	 @RequestMapping(value = "/deleteprojectAJAX", method = RequestMethod.POST )
	    @ResponseStatus(HttpStatus.NO_CONTENT)	
	    public void deleteTestCaseAt(@RequestParam(value="id", required=true) long projectID) {
		 System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% long projectID = " +projectID);
	    	projectService.remove(projectID);
	    }
	 
	/**
	 * Handles and retrieves the AJAX Add page
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAjaxAddPage() {
		System.out.println("here ---: Received request to show AJAX, add page");       
		// This will resolve to /WEB-INF/jsp/ajax-add-page.jsp
		return "ajax-add-page";
	}

	/**
	 * Handles request for adding two numbers
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String add(@RequestParam(value="inputNumber1", required=true) Integer inputNumber1,
			@RequestParam(value="inputNumber2", required=true) Integer inputNumber2,
			Model model) {
		System.out.println("here ---: Received request to add two numbers");

		// Delegate to service to do the actual adding
		String sum = inputNumber1 + " + " + inputNumber2 + " = " + (inputNumber1 + inputNumber2);

		// @ResponseBody will automatically convert the returned value into JSON format
		// You must have Jackson in your classpath
		return sum;
	}
	
	// passed()
	// update testcase state to only passed = true
	// Update Test plan results 
	@RequestMapping(value = "testconfig/passed_Ajax", method = RequestMethod.POST)  
	public @ResponseBody String passed_Ajax(@RequestParam(value="testCaseid", required=true) Long id,@RequestParam(value="testPlanID", required=true) Long testplanID, Model model) {
		testcaseService.passed(testcaseService.getTestCase(id));
		TestPlan testPlan = testplanService.getTestPlan(testplanID);
		boolean testPlanUpdated = testplanService.updateTestPlan(testplanID, testPlan);	
		if (testPlanUpdated)
		{
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}  	
			TestPlan testPlan1 = testplanService.getTestPlan(testplanID);
			String splitterString = "_splithere_";
			String returnedText = "";
			returnedText = returnedText + id.toString() + splitterString;
			returnedText = returnedText + testPlan1.getTotalTests() + splitterString;
			returnedText = returnedText + testPlan1.getTotalNotRun() + splitterString;
			returnedText = returnedText + testPlan1.getTotalPassed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalFailed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalInProgress() + splitterString;
			returnedText = returnedText + testPlan1.getTotaldeferred() + splitterString;
			returnedText = returnedText + testPlan1.getTotalBlocked();
			System.out.println("-------returnedText : "+returnedText);
			return returnedText;
		}else
		{
			return "ERROR Updating";
		}
	}
	// Not Run()    
	@RequestMapping(value = "testconfig/notrun_Ajax", method = RequestMethod.POST)  
	public @ResponseBody String notrun_Ajax(@RequestParam(value="testCaseid", required=true) Long id,@RequestParam(value="testPlanID", required=true) Long testplanID, Model model) {
		testcaseService.notrun(testcaseService.getTestCase(id));
		TestPlan testPlan = testplanService.getTestPlan(testplanID);
		boolean testPlanUpdated = testplanService.updateTestPlan(testplanID, testPlan);	
		if (testPlanUpdated)
		{
			
			TestPlan testPlan1 = testplanService.getTestPlan(testplanID);
			String splitterString = "_splithere_";
			String returnedText = "";
			returnedText = returnedText + id.toString() + splitterString;
			returnedText = returnedText + testPlan1.getTotalTests() + splitterString;
			returnedText = returnedText + testPlan1.getTotalNotRun() + splitterString;
			returnedText = returnedText + testPlan1.getTotalPassed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalFailed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalInProgress() + splitterString;
			returnedText = returnedText + testPlan1.getTotaldeferred() + splitterString;
			returnedText = returnedText + testPlan1.getTotalBlocked();
			System.out.println("-------returnedText : "+returnedText);
			return returnedText;
		}else
		{
			return "ERROR Updating";
		}
	}
	// Failed()    
	@RequestMapping(value = "testconfig/failed_Ajax", method = RequestMethod.POST)  
	public @ResponseBody String failed_Ajax(@RequestParam(value="testCaseid", required=true) Long id,@RequestParam(value="testPlanID", required=true) Long testplanID, Model model) {
		testcaseService.failed(testcaseService.getTestCase(id));
		TestPlan testPlan = testplanService.getTestPlan(testplanID);
		boolean testPlanUpdated = testplanService.updateTestPlan(testplanID, testPlan);	
		if (testPlanUpdated)
		{
			
			TestPlan testPlan1 = testplanService.getTestPlan(testplanID);
			String splitterString = "_splithere_";
			String returnedText = "";
			returnedText = returnedText + id.toString() + splitterString;
			returnedText = returnedText + testPlan1.getTotalTests() + splitterString;
			returnedText = returnedText + testPlan1.getTotalNotRun() + splitterString;
			returnedText = returnedText + testPlan1.getTotalPassed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalFailed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalInProgress() + splitterString;
			returnedText = returnedText + testPlan1.getTotaldeferred() + splitterString;
			returnedText = returnedText + testPlan1.getTotalBlocked();
			System.out.println("-------returnedText : "+returnedText);
			return returnedText;
		}else
		{
			return "ERROR Updating";
		}
	}
	// In Progress()    
	@RequestMapping(value = "testconfig/inprogress_Ajax", method = RequestMethod.POST)  
	public @ResponseBody String inprogress_Ajax(@RequestParam(value="testCaseid", required=true) Long id,@RequestParam(value="testPlanID", required=true) Long testplanID, Model model) {
		testcaseService.inprogress(testcaseService.getTestCase(id));
		TestPlan testPlan = testplanService.getTestPlan(testplanID);
		boolean testPlanUpdated = testplanService.updateTestPlan(testplanID, testPlan);	
		if (testPlanUpdated)
		{
			
			TestPlan testPlan1 = testplanService.getTestPlan(testplanID);
			String splitterString = "_splithere_";
			String returnedText = "";
			returnedText = returnedText + id.toString() + splitterString;
			returnedText = returnedText + testPlan1.getTotalTests() + splitterString;
			returnedText = returnedText + testPlan1.getTotalNotRun() + splitterString;
			returnedText = returnedText + testPlan1.getTotalPassed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalFailed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalInProgress() + splitterString;
			returnedText = returnedText + testPlan1.getTotaldeferred() + splitterString;
			returnedText = returnedText + testPlan1.getTotalBlocked();
			System.out.println("-------returnedText : "+returnedText);
			return returnedText;
		}else
		{
			return "ERROR Updating";
		}
	}
	// Deferred()    
	@RequestMapping(value = "testconfig/deferred_Ajax", method = RequestMethod.POST)  
	public @ResponseBody String deferred_Ajax(@RequestParam(value="testCaseid", required=true) Long id,@RequestParam(value="testPlanID", required=true) Long testplanID, Model model) {
		testcaseService.deferred(testcaseService.getTestCase(id));
		TestPlan testPlan = testplanService.getTestPlan(testplanID);
		boolean testPlanUpdated = testplanService.updateTestPlan(testplanID, testPlan);	
		if (testPlanUpdated)
		{
			
			TestPlan testPlan1 = testplanService.getTestPlan(testplanID);
			String splitterString = "_splithere_";
			String returnedText = "";
			returnedText = returnedText + id.toString() + splitterString;
			returnedText = returnedText + testPlan1.getTotalTests() + splitterString;
			returnedText = returnedText + testPlan1.getTotalNotRun() + splitterString;
			returnedText = returnedText + testPlan1.getTotalPassed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalFailed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalInProgress() + splitterString;
			returnedText = returnedText + testPlan1.getTotaldeferred() + splitterString;
			returnedText = returnedText + testPlan1.getTotalBlocked();
			System.out.println("-------returnedText : "+returnedText);
			return returnedText;
		}else
		{
			return "ERROR Updating";
		}
	}
	// Blocked()    
	@RequestMapping(value = "testconfig/blocked_Ajax", method = RequestMethod.POST)  
	public @ResponseBody String blocked_Ajax(@RequestParam(value="testCaseid", required=true) Long id,@RequestParam(value="testPlanID", required=true) Long testplanID, Model model) 
	{
		testcaseService.blocked(testcaseService.getTestCase(id));
		TestPlan testPlan = testplanService.getTestPlan(testplanID);
		boolean testPlanUpdated = testplanService.updateTestPlan(testplanID, testPlan);	
		if (testPlanUpdated)
		{
			
			TestPlan testPlan1 = testplanService.getTestPlan(testplanID);
			String splitterString = "_splithere_";
			String returnedText = "";
			returnedText = returnedText + id.toString() + splitterString;
			returnedText = returnedText + testPlan1.getTotalTests() + splitterString;
			returnedText = returnedText + testPlan1.getTotalNotRun() + splitterString;
			returnedText = returnedText + testPlan1.getTotalPassed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalFailed() + splitterString;
			returnedText = returnedText + testPlan1.getTotalInProgress() + splitterString;
			returnedText = returnedText + testPlan1.getTotaldeferred() + splitterString;
			returnedText = returnedText + testPlan1.getTotalBlocked();
			System.out.println("-------returnedText : "+returnedText);
			return returnedText;
		}else
		{
			return "ERROR Updating";
		}

	}
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