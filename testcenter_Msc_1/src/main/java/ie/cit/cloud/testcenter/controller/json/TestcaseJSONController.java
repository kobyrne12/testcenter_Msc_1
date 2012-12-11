package ie.cit.cloud.testcenter.controller.json;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.display.RelatedObject;
import ie.cit.cloud.testcenter.display.RelatedObjectList;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.JqgridFilter;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.JqgridFilter.Rule;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
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
@RequestMapping("testcase")
public class TestcaseJSONController {    
	@Autowired
	private CompanyService companyService; 
	@Autowired
	private ProjectService projectService;  
	@Autowired
	private CycleService cycleService;    
	@Autowired
	private TestplanService testplanService;
	@Autowired
	private TestcaseService testcaseService;   
	@Autowired
	private TestrunService testrunService;
	@Autowired
	private DefectService defectService;


	// All Testcases For a company
	@RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TestcaseSummaryList returnTestcases(
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
			System.out.println(" ^^^^^^ 2 : " + filters);
			ObjectMapper mapper = new ObjectMapper();    	
			JqgridFilter jqgridFilter = mapper.readValue(filters, JqgridFilter.class);  

			TestcaseSummaryList testcaseSummaryList = testcaseService.getGridTestcases(companyID, projectID,cycleID, testplanID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);

			Set<TestcaseSummary> oldTestcaseSummarySet = testcaseSummaryList.getTestcases();
			Set<TestcaseSummary> newTestcaseSummarySet = new LinkedHashSet<TestcaseSummary>();

			for(TestcaseSummary oldTestcaseSummary : oldTestcaseSummarySet)
			{
				boolean testcaseNameFound = false; 
				boolean testcaseIDFound = false; 
				//System.out.println(" ^^^^^^ 3 : " + oldTestcaseSummary.getTestcaseName());
				for(Rule rule : jqgridFilter.getRules())
				{        			
					//System.out.println(" ^^^^^^ 4 : " + rule.getField());
					if(rule.getField().equalsIgnoreCase("testcaseName"))
					{
						//System.out.println(" ^^^^^^ 5 a : " +oldTestcaseSummary.getTestcaseName());
						if(oldTestcaseSummary.getTestcaseName().toLowerCase().contains(rule.getData().toLowerCase()))
						{
							testcaseNameFound = true;      					
						}
					}
					else
					{
						testcaseNameFound = true;   
					}
					if(rule.getField().equalsIgnoreCase("testcaseID"))
					{
						System.out.println(" ^^^^^^ 5 a : " +oldTestcaseSummary.getTestcaseID());
						if(String.valueOf(oldTestcaseSummary.getTestcaseID()).toLowerCase().contains(rule.getData().toLowerCase()))
						{        		
							System.out.println(" ^^^^^^ 5 b : " +oldTestcaseSummary.getTestcaseID());
							testcaseIDFound = true; 
						}
					}
					else
					{
						System.out.println(" ^^^^^^ 5 c : " +oldTestcaseSummary.getTestcaseID());
						testcaseIDFound = true; 
					}
				}
				if(testcaseNameFound == true && testcaseIDFound == true)
				{
					newTestcaseSummarySet.add(oldTestcaseSummary);
				}
			}

			testcaseSummaryList.setTestcases(newTestcaseSummarySet);    		
			return testcaseSummaryList;
		}
		else
		{
			return testcaseService.getGridTestcases(companyID, projectID,
					cycleID, testplanID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);
		}

			}     

	// Columns for testcase CHANGE companyID TO UserID
	@RequestMapping(value = "/ColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames testArray(@PathVariable("index") Long companyID) {		
		return testcaseService.getColumnModelAndNames(companyID);    	
	}     

	// Testcase Related Items
	@RequestMapping(value = "/relatedObjects/{testcaseID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RelatedObjectList returnRelatedObjects(
			@PathVariable Long testcaseID,    		
			@RequestParam(required = false) String cycleID,
			@RequestParam(required = false) String testplanID,// +1 testcases
			@RequestParam(required = false) String projectID,// +1 testcases    		
			@RequestParam(required = false) String userID, // +1 testcases
			@RequestParam(required = false) String environmentID,
			@RequestParam(required = false) String requirementID,
			@RequestParam(required = false) String defectID,    		
			@RequestParam(required = false) String testrunID,
			@RequestParam(required = false) String levelName,
			@RequestParam(required = false) String stage,
			@RequestParam(required = false) String required
			) 
	{   	
		Set<RelatedObject> relatedObjectSet =  new LinkedHashSet<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList();    	
		try{
			Testcase testcase = testcaseService.getTestcase(testcaseID); 
			TestcaseSummary testcaseSummary = new TestcaseSummary(testcase, levelName, testrunService, defectService, testplanService);

			Company company = companyService.getCompany(testcaseSummary.getCompanyID());

			relatedObjectSet.add(new RelatedObject(1,
					company.getProjectsDisplayName(),
					Integer.toString(testcaseSummary.getTotalProjects()), 
					testcaseID, 
					company.getProjectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(2,
					company.getCyclesDisplayName(),
					Integer.toString(testcaseSummary.getTotalCycles()),
					testcaseID,
					company.getCyclesDisplayName().replace(" ","")));	
			relatedObjectSet.add(new RelatedObject(3,
					company.getTestplanDisplayName(),
					testcaseSummary.getTestplanName(),
					testcaseID,
					company.getTestplanDisplayName().replace(" ","")));		
			relatedObjectSet.add(new RelatedObject(4,
					"All "+ company.getTestrunsDisplayName(),
					Integer.toString(testcaseSummary.getTotalAllTestruns()),
					testcaseID,
					"all"+company.getTestrunsDisplayName().replace(" ",""))); 
			relatedObjectSet.add(new RelatedObject(5,
					"Required "+ company.getTestrunsDisplayName(),
					Integer.toString(testcaseSummary.getTotalRequiredTestruns()),
					testcaseID,
					"required"+company.getTestrunsDisplayName().replace(" ",""))); 
			relatedObjectSet.add(new RelatedObject(6,
					"Optional "+ company.getTestrunsDisplayName(),
					Integer.toString(testcaseSummary.getTotalOptionalTestruns()),
					testcaseID, 
					"optional"+company.getTestrunsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(7,company.getDefectsDisplayName()+"-Total",Integer.toString(testcaseSummary.getTotalDefects()), testcaseID, company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(8,company.getDefectsDisplayName()+"-Sev 1",Integer.toString(testcaseSummary.getTotalCurrentSev1s()), testcaseID,"sev1"));
			relatedObjectSet.add(new RelatedObject(9,company.getDefectsDisplayName()+"-Sev 2",Integer.toString(testcaseSummary.getTotalCurrentSev2s()), testcaseID,"sev2"));
			relatedObjectSet.add(new RelatedObject(10,company.getDefectsDisplayName()+"-Sev 3",Integer.toString(testcaseSummary.getTotalCurrentSev3s()), testcaseID,"sev3"));
			relatedObjectSet.add(new RelatedObject(11,company.getDefectsDisplayName()+"-Sev 4",Integer.toString(testcaseSummary.getTotalCurrentSev4s()), testcaseID,"sev4"));

			relatedObjectSet.add(new RelatedObject(12,company.getEnvironmentsDisplayName(),Integer.toString(testcaseSummary.getTotalEnvironments()), testcaseID, company.getEnvironmentsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(13,company.getRequirementsDisplayName(),Integer.toString(testcaseSummary.getTotalRequirements()), testcaseID, company.getRequirementsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(14,company.getTestersDisplayName(),Integer.toString(testcaseSummary.getTotalTesters()), testcaseID, company.getTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(15,company.getSeniorTestersDisplayName(),Integer.toString(testcaseSummary.getTotalSeniorTesters()), testcaseID, company.getSeniorTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(16,company.getDevelopersDisplayName(),Integer.toString(testcaseSummary.getTotalDevelopers()), testcaseID, company.getDevelopersDisplayName().replace(" ",""))); 	
			relatedObjectSet.add(new RelatedObject(17,company.getSeniorDevelopersDisplayName(),Integer.toString(testcaseSummary.getTotalSeniorDevelopers()), testcaseID, company.getSeniorDevelopersDisplayName().replace(" ","")));

			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;

		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", testcaseID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}    	
	} 

	/**
	 * Handles request for create a new Testcase 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewTestcase(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="testplanID", required=true) Long testplanID,
			@RequestParam(value="projectID", defaultValue="") String projectID,					
			@RequestParam(value="testcaseName", required=true) String testcaseName,			
			@RequestParam(value="testcaseSummary", defaultValue="") String testcaseSummary,	
			@RequestParam(value="testcasePreCondition", defaultValue="") String testcasePreCondition,	
			@RequestParam(value="testcaseSteps", defaultValue="") String testcaseSteps,	
			@RequestParam(value="testcasePassCondition", defaultValue="") String testcasePassCondition,	
			Model model) 
	{

		boolean alreadyExists = false;
		System.out.println("#%%#%#%#%#%#%#%# : "+companyID);
		try
		{  
			Testcase testcase = testcaseService.getTestcaseByName(testcaseName);		
			if(testcase.getCompanyID() == companyID )
			{				
				alreadyExists = true;
			}
		}
		catch(NoResultException e)
		{   			
			System.out.println("No Test Case Exists with that name "); 			
		}
		if(alreadyExists == false)
		{
			try{    	
				if(companyID != null)
				{
					Testcase testcase = new Testcase(companyID,testplanID,testcaseName,null,"DRAFT",
							testcaseSummary,testcasePreCondition,testcaseSteps,testcasePassCondition,"TESTER","SENIOR TESTER");

					try
					{
						testcaseService.addNewTestcase(testcase);	
					}
					catch(ConstraintViolationException CVE)
					{   			
						System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations()); 				
						return CVE.getConstraintViolations().toString();
					}

					//		    		System.out.println(e);
					//		    		return "ERROR :: Could not add Test Case :"+testcaseName; 
					//		    	}
//					if(!projectID.isEmpty())
//					{
//						Project project = projectService.getProject(Long.valueOf(projectID));
//						if(project == null)
//						{
//							return "ERROR :: Could not find project :"+ projectID; 
//						}				    	
//						try
//						{
//							project.getTestplans().
//							project.getTestcases().add(testcase);     
//							projectService.update(project);  	
//						}
//						catch(Exception e)
//						{
//							return "ERROR :: Could not add Test Case :"+testcaseName+" To project :"+ projectID; 
//						}					
//					}
					return "ok";  
				}
				else
				{
					return "ERROR:: company is null";    
				}
			}
			catch(ConstraintViolationException CVE)
			{   			
				System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations()); 				
				return CVE.getConstraintViolations().toString();
			}		
		}
		else
		{
			return testcaseName + " already Exists";	 
		}		
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deleteCycle(@RequestParam(value="id", required=true) Long testcaseID) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% DELETE testcaseID = " + testcaseID);
		testcaseService.remove(testcaseID);
	}
	// Testcase summary
	@RequestMapping(value = "/summary/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TestcaseSummary getCompanySummaryAt(@PathVariable("index") Long testcaseID) {
		//return testcaseService.getTestcaseSummary(testcaseID);   
		return null;
	} 
	/* @RequestMapping(value = {"jsontestcaseTEST"}, method = GET)
     public String jsontestcaseTEST(@RequestParam(required = false)Long companyID, Model model) {
     	model.addAttribute("company", companyService.getCompany(companyID));
     	return "jsontestcaseTEST";  
   }  
     // All Testcases For a company
     @RequestMapping(value = "/summaryList/{companyID}/cycle/{cycleID}", method = RequestMethod.GET)
     @ResponseStatus(HttpStatus.OK)
     public @ResponseBody TestcaseSummaryList returnTestcasesForCycle(@PathVariable("companyID") Long companyID) {
     	return companyService.getAllTestcaseSummaryForCompany(companyID);     	   	
     }  */


	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}
}
