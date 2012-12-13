package ie.cit.cloud.testcenter.controller.json;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.display.RelatedObject;
import ie.cit.cloud.testcenter.display.RelatedObjectList;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.JqgridFilter;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.TestrunLevel;
import ie.cit.cloud.testcenter.model.JqgridFilter.Rule;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.CycleList;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseList;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestplanSummary;
import ie.cit.cloud.testcenter.model.summary.TestplanSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestrunSummary;
import ie.cit.cloud.testcenter.model.summary.TestrunSummaryList;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("testrun")
public class TestrunJSONController {    
	@Autowired
	private CompanyService companyService; 
	@Autowired
	private ProjectService projectService;  
	@Autowired
	private CycleService cycleService;    
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private TestcaseService testcaseService;   
	@Autowired
	private TestrunService testrunService;
	@Autowired
	private DefectService defectService;
	@Autowired
	private EnvironmentService environmentService;

	// All Testplans For a company
	@RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TestrunSummaryList returnTestruns(
			@PathVariable Long companyID,
			@RequestParam(required = false) String projectID,
			@RequestParam(required = false) String cycleID,
			@RequestParam(required = false) String testplanID,
			@RequestParam(required = false) String testcaseID, 
			@RequestParam(required = false) String testrunID,   
			@RequestParam(required = false) String defectID,       		
			@RequestParam(required = false) String requirementID,
			@RequestParam(required = false) String environmentID,    		
			@RequestParam(required = false) String userID,
			@RequestParam(required = false) String level,
			@RequestParam(required = false) String stage,
			@RequestParam(required = false) String required,
			@RequestParam("_search") boolean search,
			@RequestParam(value="filters", required=false) String filters,
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="rows", required=false) Integer rows,
			@RequestParam(value="sidx", required=false) String sidx,
			@RequestParam(value="sord", required=false) String sord) throws JsonParseException, JsonMappingException, IOException    		
			{    	
		if (search == true) 
		{   	

			ObjectMapper mapper = new ObjectMapper();    	
			JqgridFilter jqgridFilter = mapper.readValue(filters, JqgridFilter.class); 

			TestrunSummaryList testrunSummaryList = testrunService.getGridTestruns(companyID, projectID,cycleID, testrunID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);

			Set<TestrunSummary> oldTestrunSummarySet = testrunSummaryList.getTestruns();
			Set<TestrunSummary> newTestrunSummarySet = new LinkedHashSet<TestrunSummary>();

			for(TestrunSummary oldTestrunSummary : oldTestrunSummarySet)
			{
				boolean testrunNameFound = false; 
				boolean testrunIDFound = false; 
				//System.out.println(" ^^^^^^ 3 : " + oldTestrunSummary.getTestrunName());
				for(Rule rule : jqgridFilter.getRules())
				{        			
					//System.out.println(" ^^^^^^ 4 : " + rule.getField());
					if(rule.getField().equalsIgnoreCase("testrunName"))
					{
						//System.out.println(" ^^^^^^ 5 a : " +oldTestrunSummary.getTestrunName());
						if(oldTestrunSummary.getTestrunName().toLowerCase().contains(rule.getData().toLowerCase()))
						{
							testrunNameFound = true;      					
						}
					}
					else
					{
						testrunNameFound = true;   
					}
					if(rule.getField().equalsIgnoreCase("testrunID"))
					{
						System.out.println(" ^^^^^^ 5 a : " +oldTestrunSummary.getTestrunID());
						if(String.valueOf(oldTestrunSummary.getTestrunID()).toLowerCase().contains(rule.getData().toLowerCase()))
						{        		
							System.out.println(" ^^^^^^ 5 b : " +oldTestrunSummary.getTestrunID());
							testrunIDFound = true; 
						}
					}
					else
					{
						System.out.println(" ^^^^^^ 5 c : " +oldTestrunSummary.getTestrunID());
						testrunIDFound = true; 
					}
				}
				if(testrunNameFound == true && testrunIDFound == true)
				{
					newTestrunSummarySet.add(oldTestrunSummary);
				}
			}

			testrunSummaryList.setTestruns(newTestrunSummarySet);    		
			return testrunSummaryList;
		}
		else
		{
			return testrunService.getGridTestruns(companyID, projectID,
					cycleID, testplanID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);
		}

			}     

	// Columns for testrun CHANGE companyID TO UserID
	@RequestMapping(value = "/ColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames testArray(@PathVariable("index") Long companyID) {		
		return testrunService.getColumnModelAndNames(companyID);    	
	}     

	// Testrun Related Items
	@RequestMapping(value = "/relatedObjects/{testrunID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RelatedObjectList returnRelatedObjects(
			@PathVariable Long testrunID,    		
			@RequestParam(required = false) String cycleID,
			@RequestParam(required = false) String testcaseID,// +1 testcases
			@RequestParam(required = false) String projectID,// +1 testcases    		
			@RequestParam(required = false) String userID, // +1 testcases
			@RequestParam(required = false) String environmentID,
			@RequestParam(required = false) String requirementID,
			@RequestParam(required = false) String defectID,    		
			@RequestParam(required = false) String testplanID,
			@RequestParam(required = false) String levelName,
			@RequestParam(required = false) String stage,
			@RequestParam(required = false) String required
			) 
	{   	
		Set<RelatedObject> relatedObjectSet =  new LinkedHashSet<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList();    	
		try{
			Testrun testrun = testrunService.getTestrun(testrunID); 			
			TestrunSummary testrunSummary = new TestrunSummary(testrun, levelName,testcaseService, defectService,testrunService);

			Company company = companyService.getCompany(testrunSummary.getCompanyID());

			relatedObjectSet.add(new RelatedObject(1,
					company.getProjectDisplayName(),
					testrunSummary.getProjectName(), 
					testrunID, 
					company.getProjectDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(2,
					company.getCycleDisplayName(),
					testrunSummary.getCycleName(),
					testrunID,
					company.getCycleDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(3,
					company.getTestplanDisplayName(),
					testrunSummary.getTestplanName(),
					testrunID,
					company.getTestplanDisplayName().replace(" ","")));			
			relatedObjectSet.add(new RelatedObject(4,
					company.getTestcaseDisplayName(),
					testrunSummary.getTestcaseName(),
					testrunID,
					company.getTestcaseDisplayName().replace(" ","")));				

			relatedObjectSet.add(new RelatedObject(5,company.getDefectsDisplayName()+"-Total",Integer.toString(testrunSummary.getTotalDefects()), testrunID, company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(6,company.getDefectsDisplayName()+"-Sev 1",Integer.toString(testrunSummary.getTotalCurrentSev1s()), testrunID,company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(7,company.getDefectsDisplayName()+"-Sev 2",Integer.toString(testrunSummary.getTotalCurrentSev2s()), testrunID,company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(8,company.getDefectsDisplayName()+"-Sev 3",Integer.toString(testrunSummary.getTotalCurrentSev3s()), testrunID,company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(9,company.getDefectsDisplayName()+"-Sev 4",Integer.toString(testrunSummary.getTotalCurrentSev4s()), testrunID,company.getDefectsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(10,company.getEnvironmentsDisplayName(),Integer.toString(testrunSummary.getTotalEnvironments()), testrunID, company.getEnvironmentsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(11,company.getRequirementsDisplayName(),Integer.toString(testrunSummary.getTotalRequirements()), testrunID, company.getRequirementsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(12,company.getTestersDisplayName(),Integer.toString(testrunSummary.getTotalTesters()), testrunID, company.getTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(13,company.getSeniorTestersDisplayName(),Integer.toString(testrunSummary.getTotalSeniorTesters()), testrunID, company.getSeniorTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(14,company.getDevelopersDisplayName(),Integer.toString(testrunSummary.getTotalDevelopers()), testrunID, company.getDevelopersDisplayName().replace(" ",""))); 	
			relatedObjectSet.add(new RelatedObject(15,company.getSeniorDevelopersDisplayName(),Integer.toString(testrunSummary.getTotalSeniorDevelopers()), testrunID, company.getSeniorDevelopersDisplayName().replace(" ","")));

			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;

		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", testrunID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}    	
	} 

	/**
	 * Handles request for create a new Testrun 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewTestrun(
			@RequestParam(value="cycleID", required=true) Long cycleID,
			@RequestParam(value="testcaseID", required=true) Long testcaseID,					
			Model model) 
	{
		Testcase testcase = testcaseService.getTestcase(testcaseID);		
		if(testcase == null)
		{
			return "Could not find test Object with ID : " + cycleID ;
		}	
		Company company = companyService.getCompany(testcase.getCompanyID());
		Cycle cycle = cycleService.getCycle(cycleID);
		if(cycle == null)
		{
			return "Could not find  "+company.getCycleDisplayName()+" with ID  : " + cycleID ;
		}			
		
		Long lastrunID = testcaseService.getLastTestRunID(testcaseID);
		
		Testrun testrun = new Testrun(testcase.getTestcaseName(), testcaseID, cycleID,
				testcase.getEstimatedTime(), testcase.getTestrunLevel(),
				lastrunID,"CURRENT_TESTER","TESTCASE_SENIOR_TESTER");
		try{    					
			testrunService.addNewTestrun(testrun);				
		}
		catch(ConstraintViolationException CVE)
		{   			
			System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations()); 				
			return CVE.getConstraintViolations().toString();
		}	
		
		try{    					
			cycle.getTestruns().add(testrun);
			cycleService.update(cycle);			
		}
		catch(ConstraintViolationException CVE)
		{   					
			return "Error Adding "+company.getTestrunDisplayName()+" to "+company.getCycleDisplayName();
		}	
		try{    					
			testcase.getTestruns().add(testrun);
			testcaseService.update(testcase);			
		}
		catch(ConstraintViolationException CVE)
		{   					
			return "Error Adding "+company.getTestrunDisplayName()+" to "+company.getTestcaseDisplayName();
		}	
		return "ok";   
	}	
   
	@RequestMapping(value = "/delete", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deleteTestrun(@RequestParam(value="id", required=true) Long testrunID) 
	{		
		testrunService.remove(testrunID);
	}	
	
	@RequestMapping(value = "/addRequirement", method = RequestMethod.POST)
	public @ResponseBody String addReqToTestrun(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="testrunID", required=true) Long testrunID,
			@RequestParam(value="requirementID", required=true) Long requirementID,					
			Model model) 
	{
		Testrun testrun = testrunService.getTestrun(testrunID);
		Requirement requirement = requirementService.getRequirement(requirementID);
		testrun.getRequirements().add(requirement);
		testrunService.update(testrun);
		return "ok"; 		
	}
	
	@RequestMapping(value = "/addDefect", method = RequestMethod.POST)
	public @ResponseBody String addDefectToTestrun(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="testrunID", required=true) Long testrunID,
			@RequestParam(value="defectID", required=true) Long defectID,					
			Model model) 
	{
		Testrun testrun = testrunService.getTestrun(testrunID);
		Defect defect = defectService.getDefect(defectID);
		testrun.getDefects().add(defect);
		testrunService.update(testrun);
		return "ok"; 		
	}
	
	@RequestMapping(value = "/addEnvironment", method = RequestMethod.POST)
	public @ResponseBody String addEnvironmentToTestrun(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="testrunID", required=true) Long testrunID,
			@RequestParam(value="environmentID", required=true) Long environmentID,					
			Model model) 
	{
		Testrun testrun = testrunService.getTestrun(testrunID);
		Environment environment = environmentService.getEnvironment(environmentID);
		testrun.getEnvironments().add(environment);
		testrunService.update(testrun);
		return "ok"; 		
	}
	@RequestMapping(value = "/Run", method = RequestMethod.POST)
	public @ResponseBody String runTestrun(
			@RequestParam(value="companyID", required=true) Long companyID,			
			@RequestParam(value="testrunID", required=true) Long testrunID,
			@RequestParam(value="testrunState", required=true) String testrunState,					
			Model model) 
	{
		Testrun testrun = testrunService.getTestrun(testrunID);
		if(testrun == null)
		{
			return "Could not Find";
		}
		if(setTestrunState(testrun,testrunState))
		{
			return "ok";
		}
		else
		{
			return "Could Not Update";
		}
	}
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}
	public boolean setTestrunState(Testrun testrun,String testrunState)
	{
	
		if(testrunState.equalsIgnoreCase("passed"))
		{testrun.setPassed(true);}
		else{testrun.setPassed(false);}
		
		if(testrunState.equalsIgnoreCase("failed"))
		{testrun.setFailed(true);}
		else{testrun.setFailed(false);}
		
		if(testrunState.equalsIgnoreCase("deferred"))
		{testrun.setDeferred(true);}
		else{testrun.setDeferred(false);}
		
		if(testrunState.equalsIgnoreCase("blocked"))
		{testrun.setBlocked(true);}
		else{testrun.setBlocked(false);}
		
		if(testrunState.equalsIgnoreCase("inprog"))
		{testrun.setInprogress(true);}
		else{testrun.setInprogress(false);}
		
		if(testrunState.equalsIgnoreCase("notrun"))
		{testrun.setNotrun(true);}
		else{testrun.setNotrun(false);}
		
		try{
			testrunService.update(testrun);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
}
