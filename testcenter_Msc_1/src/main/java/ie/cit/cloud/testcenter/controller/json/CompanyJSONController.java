package ie.cit.cloud.testcenter.controller.json;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("company")
public class CompanyJSONController { 
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
   
    // GET Company
    @RequestMapping(value = "{companyID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Company getCompanyAt(@PathVariable("companyID") Long companyID) {
    	return companyService.getCompany(companyID);    	
    }       
    // POST Company
    @RequestMapping(value = "{companyID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCompnay(@PathVariable("companyID") long companyID) {
    	companyService.addNewCompany(new Company( "New Company_"+companyID,new Date(),"KEN"));   	  
    } 
    // DELETE Company
    @RequestMapping(value = "{companyID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyAt(@PathVariable("companyID") Long companyID) {
    	companyService.remove(companyID);    	
    }   
    
    // GET Project
    @RequestMapping(value = "/project/{projectID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Project getProjectAt(@PathVariable("projectID") Long projectID) {
    	return projectService.getProject(projectID);    	
    }      
    // POST Project
    @RequestMapping(value = "/{companyID}/project/{projectID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProject(@PathVariable("companyID") long companyID,
    		@PathVariable("projectID") long projectID)
    {
    	String projectName = "Project_"+projectID;    	
    	try
    	{  
    		Project project = projectService.getProjectByName(projectName);		
    		if(project.getCompanyID() == companyID )
    		{		
    			throw new DuplicateKeyException(projectName);
    		}
    	}
    	catch(NoResultException e)
    	{   					
    		//do nothing;
    	}
    	projectService.addNewProject(new Project(companyID,projectName,0,94,97,10,20,30,40,"DATE","CURENT_USER"));			  
    } 
    // DELETE Company
    @RequestMapping(value = "/project/{projectID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectAt(@PathVariable("projectID") Long projectID) {
    	projectService.remove(projectID);    	
    }   
    
    // GET Cycle
    @RequestMapping(value = "/cycle/{cycleID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cycle getCycleAt(@PathVariable("cycleID") Long cycleID) {
    	return cycleService.getCycle(cycleID);    	
    }    
    // POST Cycle
    @RequestMapping(value = "/{projectID}/cycle/{cycleID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCycle(@PathVariable("projectID") long projectID,
    		@PathVariable("cycleID") long cycleID)
    {
    	String cycleName = "Cycle_"+projectID+"_"+cycleID;    	
    	try
    	{  
    		Cycle cycle = cycleService.getCycleByName(cycleName);		
    		if(cycle.getProjectID() == projectID )
    		{		
    			throw new DuplicateKeyException(cycleName);
    		}
    	}
    	catch(NoResultException e)
    	{   					
    		//do nothing;
    	}
    	int projectPosition = cycleService.getMaxProjectPosNum(projectID) + 1;					
		cycleService.addNewCycle(new Cycle(cycleName,projectID,1,projectPosition,"START_DATE","END_DATE"));	
     }     
    // DELETE Cycle
    @RequestMapping(value = "/cycle/{cycleID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCycleAt(@PathVariable("cycleID") Long cycleID) {
    	cycleService.remove(cycleID);    	
    }  
    
    // GET Testcase
    @RequestMapping(value = "/testcase/{testcaseID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Testcase getTestcaseAt(@PathVariable("testcaseID") Long testcaseID) {
    	return testcaseService.getTestcase(testcaseID);    	
    }    
    // POST Test Case
    @RequestMapping(value = "/{companyID}/testcase/{testcaseID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTestcase(@PathVariable("companyID") long companyID,
    		@PathVariable("testcaseID") long testcaseID)
    {
    	String testcaseName = "TestCase_"+companyID+"_"+testcaseID;    	
    	try
    	{  
    		Testcase testcase = testcaseService.getTestcaseByName(testcaseName);		
    		if(testcase.getCompanyID() == companyID )
    		{		
    			throw new DuplicateKeyException(testcaseName);
    		}
    	}
    	catch(NoResultException e)
    	{   					
    		//do nothing;
    	}
    	//int testPlanPosition = testcaseService.getMaxTestPlanPosNum(projectID);					
    	testcaseService.addNewTestcase(new Testcase(testcaseName,companyID,"REGRESSION","APPROVED",
    			"SUMMARY","PRE_CONDITION","STEPS","PASS_CONDITION","TESTER","SENIOR TESTER"));	
     } 
    // DELETE TestCase 
    @RequestMapping(value = "/testcase/{testcaseID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestcaseAt(@PathVariable("testcaseID") Long testcaseID) {
    	testcaseService.remove(testcaseID);    	
    }  
    
    // GET Testrun
    @RequestMapping(value = "/testrun/{testrunID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Testrun getTestrunAt(@PathVariable("testrunID") Long testrunID) {
    	return testrunService.getTestrun(testrunID);    	
    }   
    // POST Test Run
    @RequestMapping(value = "/testrun/{cycleID}/{testcaseID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTestrun(@PathVariable("cycleID") long cycleID,
    		@PathVariable("testcaseID") long testcaseID)
    {    	
    	//int testPlanPosition = testcaseService.getMaxTestPlanPosNum(projectID);	
    	Testcase testcase = testcaseService.getTestcase(testcaseID);
    	
    	testrunService.addNewTestrun(new Testrun(testcase.getTestcaseName(),testcaseID,cycleID,
    			testcase.getEstimatedTime(),testcase.getLevel(),testcase.getLastTestRunID(),     			   			
    			testcase.getTester(),testcase.getSeniorTester()));    	
    } 
    // DELETE Testrun 
    @RequestMapping(value = "/testrun/{testrunID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestrunAt(@PathVariable("testrunID") Long testrunID) {
    	testrunService.remove(testrunID);    	
    }  
  

  
    // GET Cycle Testruns
    @RequestMapping(value = "/cycleCasRuns/{cycleID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody int getcycleCasRunsAt(@PathVariable("cycleID") Long cycleID) {
    	Cycle cycle = cycleService.getCycle(cycleID);
    	return cycle.getCascadedAllTestRunsCount();    	
    }   
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }
    
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException.class)
    public void conflictResult() {
	// no code needed
    }
   
    
    
	
}
