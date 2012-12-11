package ie.cit.cloud.testcenter.controller.json;

import java.util.ArrayList;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
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
    @Autowired
    private DefectService defectService; 
    @Autowired
    private RequirementService requirementService; 
    @Autowired
    private EnvironmentService environmentService; 
   
    // GET All Company
    @RequestMapping(value = " ", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Set<Company> getAllCompanies() {
    	return companyService.getAllCompanies();    	
    }  
    // GET All projects IDs
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Long> getAllProjectIDs() {
    	List<Long> projectIDs = new ArrayList<Long>();    
    	try{
    		for(final Project project : projectService.getAllProjects())
    		{
    			projectIDs.add(project.getProjectID());
    		}
    		return projectIDs;  	
    	}catch(NoResultException nre)
    	{
    		return null;
    	}
    }  
    // GET All Testcases IDs  
    @RequestMapping(value = "/testcases", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Long> getAllTestcasesIDs() {
    	List<Long> testcaseIDs = new ArrayList<Long>();    
    	try{
    		for(final Testcase testcase :testcaseService.getAllTestcases())
    		{
    			testcaseIDs.add(testcase.getTestcaseID());
    		}
    		return testcaseIDs;  	
    	}catch(NoResultException nre)
    	{
    		return null;
    	}
    }  
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Company
    @RequestMapping(value = "{companyID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Company getCompanyAt(@PathVariable("companyID") Long companyID) {
    	return companyService.getCompany(companyID);    	
    }       
    // POST Company
    @RequestMapping(value = "{companyID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCompnay(@PathVariable("companyID") Long companyID) {
    	companyService.addNewCompany(new Company( "New Company_"+companyID));   	  
    } 
    // DELETE Company
    @RequestMapping(value = "{companyID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyAt(@PathVariable("companyID") Long companyID) {
    	companyService.remove(companyID);    	
    }   
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Project
    @RequestMapping(value = "/project/{projectID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Project getProjectAt(@PathVariable("projectID") Long projectID) {
    	return projectService.getProject(projectID);    	
    }
 
    // POST Project
    @RequestMapping(value = "/{companyID}/project/{projectID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProject(@PathVariable("companyID") Long companyID,
    		@PathVariable("projectID") Long projectID)
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
    	projectService.addNewProject(new Project(companyID,projectName,null,94,97,10,20,30,40,"DATE","CURENT_USER"));			  
    } 
    // DELETE Company
    @RequestMapping(value = "/project/{projectID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectAt(@PathVariable("projectID") Long projectID) {
    	projectService.remove(projectID);    	
    }   
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Cycle
    @RequestMapping(value = "/cycle/{cycleID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cycle getCycleAt(@PathVariable("cycleID") Long cycleID) {
    	return cycleService.getCycle(cycleID);    	
    }    
    // POST Cycle
    @RequestMapping(value = "/{projectID}/cycle/{cycleID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCycle(@PathVariable("projectID") Long projectID,
    		@PathVariable("cycleID") Long cycleID)
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
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Testcase
    @RequestMapping(value = "/testcase/{testcaseID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Testcase getTestcaseAt(@PathVariable("testcaseID") Long testcaseID)
    {
    	Testcase testcase = testcaseService.getTestcase(testcaseID);
    	Testcase noProjectTestcase = new Testcase();
    	noProjectTestcase.setCompanyID(testcase.getCompanyID());
    	noProjectTestcase.setTestcaseName(testcase.getTestcaseName());
    	noProjectTestcase.setTestplanID(testcase.getTestcaseID());    	
    	noProjectTestcase.setTestcaseSummary(testcase.getTestcaseSummary());
    	noProjectTestcase.setTestcaseSteps(testcase.getTestcaseSteps());
    	noProjectTestcase.setTestcasePreCondition(testcase.getTestcasePreCondition());
    	noProjectTestcase.setTestcasePreCondition(testcase.getTestcasePreCondition());
    	return noProjectTestcase;    	
    }    
    // POST Test Case
    @RequestMapping(value = "/{companyID}/testcase/{testcaseID}/project/{projectID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTestcase(@PathVariable("companyID") Long companyID,
    		@PathVariable("testcaseID") Long testcaseID,
    		@PathVariable("projectID") Long projectID)
    {
    	String testcaseName = "TestCase_"+companyID+"_"+testcaseID;
    	Project project = projectService.getProject(projectID);
    	Testcase testcase = new Testcase(companyID,(long) 1,testcaseName,null,"APPROVED",
    			"SUMMARY","PRE_CONDITION","STEPS","PASS_CONDITION","TESTER","SENIOR TESTER");	
    	testcaseService.addNewTestcase(testcase);	
    	//project.getTestcases().add(testcase);     
    	//projectService.update(project);     	
    //	testcaseService.addNewTestcase(new Testcase(companyID,testcaseName,"REGRESSION","APPROVED",
    //			"SUMMARY","PRE_CONDITION","STEPS","PASS_CONDITION","TESTER","SENIOR TESTER"));	
    	
    	
     } 
    // Add Test Case to another project 
    @RequestMapping(value = "/{companyID}/testcase/{testcaseID}/updateproject/{projectID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTestcasetoAnotherProject(@PathVariable("companyID") Long companyID,
    		@PathVariable("testcaseID") Long testcaseID,
    		@PathVariable("projectID") Long projectID)
    {
    	Project project = projectService.getProject(projectID);
    	Testcase testcase = testcaseService.getTestcase(testcaseID);
//    	project.getTestcases().add(testcase);     
//    	projectService.update(project);     	
    } 
    
//    // GET Testcase projects 
//    @RequestMapping(value = "/testcaseprojects/{testcaseID}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody List<Long> getTestcaseProjects(@PathVariable("testcaseID") Long testcaseID) 
//    {    	
//    	List<Long> projectIDs = new ArrayList<Long>();    
//    	try{
//    		Testcase testcase = testcaseService.getTestcase(testcaseID); 
//    		for(final Project project :testcase.getProjects())
//    		{
//    			projectIDs.add(project.getProjectID());
//    		}
//    		return projectIDs;  	
//    	}catch(NoResultException nre)
//    	{
//    		return null;
//    	}    	
//    }    
//   
    // DELETE TestCase 
    @RequestMapping(value = "/testcase/{testcaseID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestcaseAt(@PathVariable("testcaseID") Long testcaseID) {
    	testcaseService.remove(testcaseID);    	
    }  
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Testrun
    @RequestMapping(value = "/testrun/{testrunID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Testrun getTestrunAt(@PathVariable("testrunID") Long testrunID) {
    	return testrunService.getTestrun(testrunID);    	
    }   
    // POST Test Run
    @RequestMapping(value = "/testrun/{cycleID}/{testcaseID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTestrun(@PathVariable("cycleID") Long cycleID,
    		@PathVariable("testcaseID") Long testcaseID)
    {    	
    	//int testPlanPosition = testcaseService.getMaxTestPlanPosNum(projectID);	
    	Testcase testcase = testcaseService.getTestcase(testcaseID);
    	
    	testrunService.addNewTestrun(new Testrun(testcase.getTestcaseName(),testcaseID,cycleID,
    			testcase.getEstimatedTime(),testcase.getTestrunLevel(),
    			testcaseService.getLastTestRunID(testcase.getTestcaseID()),     			   			
    			testcase.getTester(),testcase.getSeniorTester()));    	
    } 
    // DELETE Testrun 
    @RequestMapping(value = "/testrun/{testrunID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestrunAt(@PathVariable("testrunID") Long testrunID) {
    	testrunService.remove(testrunID);    	
    }  
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Testplan
    @RequestMapping(value = "/testplan/{testplanID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Testplan getTestplanAt(@PathVariable("testplanID") Long testplanID) {
    	return testplanService.getTestplan(testplanID);    	
    }   
    // POST Test plan
    @RequestMapping(value = "{companyID}/testplan/{testplanID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTestplan(@PathVariable("companyID") Long companyID,
    		@PathVariable("testplanID") Long testplanID)
    {    	    	
    	String testcaseName = "Testplan_"+companyID+"_"+testplanID;     	
    	testplanService.addNewTestplan(new Testplan(companyID,testcaseName));    	
    } 
    // DELETE Testplan 
    @RequestMapping(value = "/testplan/{testplanID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestplanAt(@PathVariable("testplanID") Long testplanID) {
    	testplanService.remove(testplanID);    	
    }   
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Defect
    @RequestMapping(value = "/defect/{defectID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Defect getDefectAt(@PathVariable("defectID") Long defectID) {
    	return defectService.getDefect(defectID);    	
    }   
    // POST Defect
    @RequestMapping(value = "{companyID}/defect/{defectID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createDefect(@PathVariable("companyID") Long companyID,
    		@PathVariable("defectID") Long defectID)
    {    	    	    	    	
    	defectService.addNewDefect(new Defect(companyID,"SUMMARY",1,"DETAILS",null,"TYPE"));    	
    } 	
    // DELETE Defect
    @RequestMapping(value = "/defect/{defectID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDefectAt(@PathVariable("defectID") Long defectID) {
    	defectService.remove(defectID);    	
    }    
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Requirement
    @RequestMapping(value = "/requirement/{requirementID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Requirement getRequirementAt(@PathVariable("requirementID") Long requirementID) {
    	return requirementService.getRequirement(requirementID);    	
    }   
    // POST Requirement
    @RequestMapping(value = "{companyID}/requirement/{requirementID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRequirement(@PathVariable("companyID") Long companyID,
    		@PathVariable("requirementID") Long requirementID)
    {    	    	    	    	
    	requirementService.addNewRequirement(new Requirement(companyID,"SUMMARY","DETAILS",null,1));    	
    } 	   
    // DELETE Requirement
    @RequestMapping(value = "/requirement/{requirementID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequirementAt(@PathVariable("requirementID") Long requirementID) {
    	requirementService.remove(requirementID);    	
    }    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////     
 // GET Environment
    @RequestMapping(value = "/environment/{environmentID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Environment getEnvironmentAt(@PathVariable("environmentID") Long environmentID) {
    	return environmentService.getEnvironment(environmentID);    	
    }   
    // POST Environment
    @RequestMapping(value = "{companyID}/environment/{environmentID}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEnvironment(@PathVariable("companyID") Long companyID,
    		@PathVariable("environmentID") Long environmentID)
    {    	    	    	    	
    	environmentService.addNewEnvironment(new Environment(companyID,"ENV_NAME","ENV_OS"));    	
    } 	   
    // DELETE Environment
    @RequestMapping(value = "/environment/{environmentID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnvironmentAt(@PathVariable("environmentID") Long environmentID) {
    	environmentService.remove(environmentID);    	
    }    

 //////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // GET Cycle Testruns
    @RequestMapping(value = "/cycleCasRuns/{cycleID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody int getcycleCasRunsAt(@PathVariable("cycleID") Long cycleID) {    	
    	return cycleService.getCascadedAllTestRuns(cycleID).size();    	
    }   
 ////////////////////////////////////////////////////////////////////////////////////////////////////////    
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
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    public void noResults() {
	// no code needed
    }
    
    
    
	
}
