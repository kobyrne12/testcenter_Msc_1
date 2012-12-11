package ie.cit.cloud.testcenter.controller.json;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import java.io.IOException;
import java.util.ArrayList;
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
import ie.cit.cloud.testcenter.model.JqgridFilter;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.JqgridFilter.Rule;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestplanSummary;
import ie.cit.cloud.testcenter.model.summary.TestplanSummaryList;
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
@RequestMapping("testplan")
public class TestplanJSONController {    
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


	// All Testplans For a company
	@RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TestplanSummaryList returnTestplans(
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

			TestplanSummaryList testplanSummaryList = testplanService.getGridTestplans(companyID, projectID,cycleID, testplanID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);

			Set<TestplanSummary> oldTestplanSummarySet = testplanSummaryList.getTestplans();
			Set<TestplanSummary> newTestplanSummarySet = new LinkedHashSet<TestplanSummary>();

			for(TestplanSummary oldTestplanSummary : oldTestplanSummarySet)
			{
				boolean testplanNameFound = false; 
				boolean testplanIDFound = false; 
				//System.out.println(" ^^^^^^ 3 : " + oldTestplanSummary.getTestplanName());
				for(Rule rule : jqgridFilter.getRules())
				{        			
					//System.out.println(" ^^^^^^ 4 : " + rule.getField());
					if(rule.getField().equalsIgnoreCase("testplanName"))
					{
						//System.out.println(" ^^^^^^ 5 a : " +oldTestplanSummary.getTestplanName());
						if(oldTestplanSummary.getTestplanName().toLowerCase().contains(rule.getData().toLowerCase()))
						{
							testplanNameFound = true;      					
						}
					}
					else
					{
						testplanNameFound = true;   
					}
					if(rule.getField().equalsIgnoreCase("testplanID"))
					{
						System.out.println(" ^^^^^^ 5 a : " +oldTestplanSummary.getTestplanID());
						if(String.valueOf(oldTestplanSummary.getTestplanID()).toLowerCase().contains(rule.getData().toLowerCase()))
						{        		
							System.out.println(" ^^^^^^ 5 b : " +oldTestplanSummary.getTestplanID());
							testplanIDFound = true; 
						}
					}
					else
					{
						System.out.println(" ^^^^^^ 5 c : " +oldTestplanSummary.getTestplanID());
						testplanIDFound = true; 
					}
				}
				if(testplanNameFound == true && testplanIDFound == true)
				{
					newTestplanSummarySet.add(oldTestplanSummary);
				}
			}

			testplanSummaryList.setTestplans(newTestplanSummarySet);    		
			return testplanSummaryList;
		}
		else
		{
			return testplanService.getGridTestplans(companyID, projectID,
					cycleID, testplanID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);
		}

			}     

	// Columns for testplan CHANGE companyID TO UserID
	@RequestMapping(value = "/ColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames testArray(@PathVariable("index") Long companyID) {		
		return testplanService.getColumnModelAndNames(companyID);    	
	}     

	// Testplan Related Items
	@RequestMapping(value = "/relatedObjects/{testplanID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RelatedObjectList returnRelatedObjects(
			@PathVariable Long testplanID,    		
			@RequestParam(required = false) String cycleID,
			@RequestParam(required = false) String testcaseID,// +1 testcases
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
			Testplan testplan = testplanService.getTestplan(testplanID); 
			TestplanSummary testplanSummary = new TestplanSummary(testplan, levelName, testrunService, defectService);

			Company company = companyService.getCompany(testplanSummary.getCompanyID());

			relatedObjectSet.add(new RelatedObject(1,
					company.getProjectsDisplayName(),
					Integer.toString(testplanSummary.getTotalProjects()), 
					testplanID, 
					company.getProjectsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(2,
					company.getCyclesDisplayName(),
					Integer.toString(testplanSummary.getTotalCycles()),
					testplanID,
					company.getCyclesDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(3,
					company.getTestcasesDisplayName(),
					Integer.toString(testplanSummary.getTotalTestcases()),
					testplanID,
					company.getTestcasesDisplayName().replace(" ","")));			
			relatedObjectSet.add(new RelatedObject(4,
					"All "+ company.getTestrunsDisplayName(),
					Integer.toString(testplanSummary.getTotalAllTestruns()),
					testplanID,
					"all"+company.getTestrunsDisplayName().replace(" ",""))); 
			relatedObjectSet.add(new RelatedObject(5,
					"Required "+ company.getTestrunsDisplayName(),
					Integer.toString(testplanSummary.getTotalRequiredTestruns()),
					testplanID,
					"required"+company.getTestrunsDisplayName().replace(" ",""))); 
			relatedObjectSet.add(new RelatedObject(6,
					"Optional "+ company.getTestrunsDisplayName(),
					Integer.toString(testplanSummary.getTotalOptionalTestruns()),
					testplanID, 
					"optional"+company.getTestrunsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(7,company.getDefectsDisplayName()+"-Total",Integer.toString(testplanSummary.getTotalDefects()), testplanID, company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(8,company.getDefectsDisplayName()+"-Sev 1",Integer.toString(testplanSummary.getTotalCurrentSev1s()), testplanID,"sev1"));
			relatedObjectSet.add(new RelatedObject(9,company.getDefectsDisplayName()+"-Sev 2",Integer.toString(testplanSummary.getTotalCurrentSev2s()), testplanID,"sev2"));
			relatedObjectSet.add(new RelatedObject(10,company.getDefectsDisplayName()+"-Sev 3",Integer.toString(testplanSummary.getTotalCurrentSev3s()), testplanID,"sev3"));
			relatedObjectSet.add(new RelatedObject(11,company.getDefectsDisplayName()+"-Sev 4",Integer.toString(testplanSummary.getTotalCurrentSev4s()), testplanID,"sev4"));

			relatedObjectSet.add(new RelatedObject(12,company.getEnvironmentsDisplayName(),Integer.toString(testplanSummary.getTotalEnvironments()), testplanID, company.getEnvironmentsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(13,company.getRequirementsDisplayName(),Integer.toString(testplanSummary.getTotalRequirements()), testplanID, company.getRequirementsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(14,company.getTestersDisplayName(),Integer.toString(testplanSummary.getTotalTesters()), testplanID, company.getTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(15,company.getSeniorTestersDisplayName(),Integer.toString(testplanSummary.getTotalSeniorTesters()), testplanID, company.getSeniorTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(16,company.getDevelopersDisplayName(),Integer.toString(testplanSummary.getTotalDevelopers()), testplanID, company.getDevelopersDisplayName().replace(" ",""))); 	
			relatedObjectSet.add(new RelatedObject(17,company.getSeniorDevelopersDisplayName(),Integer.toString(testplanSummary.getTotalSeniorDevelopers()), testplanID, company.getSeniorDevelopersDisplayName().replace(" ","")));

			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;

		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", testplanID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}    	
	} 

	/**
	 * Handles request for create a new Testplan 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewTestplan(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="testplanName", required=true) String testplanName,
			@RequestParam(value="projectID", defaultValue="") String projectID,			
			Model model) 
	{

		boolean alreadyExists = false;
		System.out.println("#%%#%#%#%#%#%#%# : "+companyID);
		try
		{  
			Testplan testplan = testplanService.getTestplanByName(testplanName);		
			if(testplan.getCompanyID() == companyID )
			{				
				alreadyExists = true;
			}
		}
		catch(NoResultException e)
		{   			
			System.out.println("No Test plan Exists with that name "); 			
		}
		if(alreadyExists == false)
		{
			try{    	
				if(companyID != null)
				{
					Testplan testplan = new Testplan(companyID,testplanName);

					try
					{
						testplanService.addNewTestplan(testplan);	
					}
					catch(ConstraintViolationException CVE)
					{   			
						System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations()); 				
						return CVE.getConstraintViolations().toString();
					}

					//		    		System.out.println(e);
					//		    		return "ERROR :: Could not add Test plan :"+testplanName; 
					//		    	}
					if(!projectID.isEmpty())
					{
						Project project = projectService.getProject(Long.valueOf(projectID));
						if(project == null)
						{
							return "ERROR :: Could not find project :"+ projectID; 
						}				    	
						try
						{
							project.getTestplans().add(testplan);     
							projectService.update(project);  	
						}
						catch(Exception e)
						{
							return "ERROR :: Could not add Test plan :"+testplanName+" To project :"+ projectID; 
						}					
					}
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
			return testplanName + " already Exists";	 
		}		
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deleteCycle(@RequestParam(value="id", required=true) Long testplanID) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% DELETE testplanID = " + testplanID);
		testplanService.remove(testplanID);
	}
	/**
	 * Handles request for create a new Testplan 
	 */
	@RequestMapping(value = "/addto", method = RequestMethod.POST)
	public @ResponseBody String addTestplanTo(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="testplanID", required=true) Long testplanID,
			@RequestParam(value="relatedItem",  required=true) String relatedItem,	
			@RequestParam(value="relatedItemID",  required=true) String relatedItemID,	
			Model model) 
	{
		if(relatedItem.equalsIgnoreCase("project"))
		{
			Testplan testplan = testplanService.getTestplan(testplanID);
			Project project = projectService.getProject(Long.valueOf(relatedItemID));
			if(project == null)
			{
				return "ERROR :: Could not find project :"+ relatedItemID; 
			}				    	
			try
			{
				project.getTestplans().add(testplan);     
				projectService.update(project);  	
			}
			catch(Exception e)
			{
				return "ERROR :: Could not add Test plan :"+testplan.getTestplanName()+" To project :"+relatedItemID ; 
			}				
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("cycle"))
		{
			// Add Testplan to cycle : all testcases 
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("defect"))
		{
			// Add Testplan to defect : all testcase test runs
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("requirement"))
		{
			// Add Testplan to requirement : all testcase test runs
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("environment"))
		{
			// Add Testplan to environment : all testcase test runs
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("user"))
		{
			// Add Testplan to user : all testcase test runs
			return "ok";  
		}
		return "Could Not Add to " + relatedItem;  

	}
	/**
	 * Handles request for create a new Testplan 
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody String removeTestplanFrom(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="testplanID", required=true) Long testplanID,
			@RequestParam(value="relatedItem",  required=true) String relatedItem,	
			@RequestParam(value="relatedItemID",  required=true) String relatedItemID,	
			Model model) 
	{
		if(relatedItem.equalsIgnoreCase("project"))
		{
			Testplan testplan = testplanService.getTestplan(testplanID);
			Project project = projectService.getProject(Long.valueOf(relatedItemID));
			if(project == null)
			{
				return "ERROR :: Could not find project :"+ relatedItemID; 
			}				    	
			try
			{	
				Set<Testplan> newProjectTestplans = new LinkedHashSet<Testplan>();
				for(Testplan projectTestplan :project.getTestplans())
				{
					System.out.println("COMPARE : "+projectTestplan.getTestplanID()+" TO "+testplan.getTestplanID());
					if(projectTestplan.getTestplanID() != testplan.getTestplanID())
					{
						newProjectTestplans.add(projectTestplan);
						System.out.println("NOT FOUND");
					}
				}
				project.getTestplans().clear();   
				project.getTestplans().addAll(newProjectTestplans);   
				projectService.update(project);  	
			}
			catch(Exception e)
			{
				return "ERROR :: Could not remove Test plan :"+testplan.getTestplanName()+" To project :"+relatedItemID ; 
			}				
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("cycle"))
		{
			// Add Testplan to cycle : all testcases 
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("defect"))
		{
			// Add Testplan to defect : all testcase test runs
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("requirement"))
		{
			// Add Testplan to requirement : all testcase test runs
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("environment"))
		{
			// Add Testplan to environment : all testcase test runs
			return "ok";  
		}
		else if(relatedItem.equalsIgnoreCase("user"))
		{
			// Add Testplan to user : all testcase test runs
			return "ok";  
		}
		return "Could Not Add to " + relatedItem;  

	}
	// Testplan summary
	@RequestMapping(value = "/summary/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TestplanSummary getCompanySummaryAt(@PathVariable("index") Long testplanID) {
		//return testplanService.getTestplanSummary(testplanID);   
		return null;
	} 
	/* @RequestMapping(value = {"jsontestplanTEST"}, method = GET)
     public String jsontestplanTEST(@RequestParam(required = false)Long companyID, Model model) {
     	model.addAttribute("company", companyService.getCompany(companyID));
     	return "jsontestplanTEST";  
   }  
     // All Testplans For a company
     @RequestMapping(value = "/summaryList/{companyID}/cycle/{cycleID}", method = RequestMethod.GET)
     @ResponseStatus(HttpStatus.OK)
     public @ResponseBody TestplanSummaryList returnTestplansForCycle(@PathVariable("companyID") Long companyID) {
     	return companyService.getAllTestcaseSummaryForCompany(companyID);     	   	
     }  */


	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}
}
