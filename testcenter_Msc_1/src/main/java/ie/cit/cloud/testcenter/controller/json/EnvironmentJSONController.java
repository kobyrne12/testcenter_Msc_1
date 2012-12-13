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
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.JqgridFilter;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.JqgridFilter.Rule;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.summary.CycleList;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.EnvironmentList;
import ie.cit.cloud.testcenter.model.summary.EnvironmentSummary;
import ie.cit.cloud.testcenter.model.summary.EnvironmentSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.RequirementList;
import ie.cit.cloud.testcenter.model.summary.TestcaseList;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestplanSummary;
import ie.cit.cloud.testcenter.model.summary.TestplanSummaryList;
import ie.cit.cloud.testcenter.model.summary.RequirementSummary;
import ie.cit.cloud.testcenter.model.summary.RequirementSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestrunList;
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
@RequestMapping("environment")
public class EnvironmentJSONController {    
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
	private RequirementService requirementService;
	@Autowired
	private EnvironmentService environmentService;
	@Autowired
	private DefectService defectService;
	@Autowired
	private TestrunService testrunService;


	// All Testplans For a company
	@RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EnvironmentSummaryList returnEnvironments(
			@PathVariable Long companyID,
			@RequestParam(required = false) String projectID,
			@RequestParam(required = false) String cycleID,
			@RequestParam(required = false) String testplanID,
			@RequestParam(required = false) String testcaseID, 
			@RequestParam(required = false) String requirementID,   
			@RequestParam(required = false) String defectID,       		
			@RequestParam(required = false) String testrunID,
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

			EnvironmentSummaryList environmentSummaryList = environmentService.getGridEnvironments(companyID, projectID,cycleID, testplanID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);

			Set<EnvironmentSummary> oldEnvironmentSummarySet = environmentSummaryList.getEnvironments();
			Set<EnvironmentSummary> newEnvironmentSummarySet = new LinkedHashSet<EnvironmentSummary>();

			for(EnvironmentSummary oldEnvironmentSummary : oldEnvironmentSummarySet)
			{
				boolean environmentNameFound = false; 
				boolean environmentIDFound = false; 
				//System.out.println(" ^^^^^^ 3 : " + oldEnvironmentSummary.getEnvironmentName());
				for(Rule rule : jqgridFilter.getRules())
				{        			
					//System.out.println(" ^^^^^^ 4 : " + rule.getField());
					if(rule.getField().equalsIgnoreCase("environmentName"))
					{
						//System.out.println(" ^^^^^^ 5 a : " +oldEnvironmentSummary.getEnvironmentName());
						if(oldEnvironmentSummary.getEnvironmentName().toLowerCase().contains(rule.getData().toLowerCase()))
						{
							environmentNameFound = true;      					
						}
					}
					else
					{
						environmentNameFound = true;   
					}
					if(rule.getField().equalsIgnoreCase("environmentID"))
					{
						System.out.println(" ^^^^^^ 5 a : " +oldEnvironmentSummary.getEnvironmentID());
						if(String.valueOf(oldEnvironmentSummary.getEnvironmentID()).toLowerCase().contains(rule.getData().toLowerCase()))
						{        		
							System.out.println(" ^^^^^^ 5 b : " +oldEnvironmentSummary.getEnvironmentID());
							environmentIDFound = true; 
						}
					}
					else
					{
						System.out.println(" ^^^^^^ 5 c : " +oldEnvironmentSummary.getEnvironmentID());
						environmentIDFound = true; 
					}
				}
				if(environmentNameFound == true && environmentIDFound == true)
				{
					newEnvironmentSummarySet.add(oldEnvironmentSummary);
				}
			}

			environmentSummaryList.setEnvironments(newEnvironmentSummarySet);    		
			return environmentSummaryList;
		}
		else
		{
			return environmentService.getGridEnvironments(companyID, projectID,
					cycleID, testplanID, testcaseID,
					requirementID, defectID, requirementID,
					environmentID, userID,level,stage,required);
		}
	}     

	// Columns for environment CHANGE companyID TO UserID
	@RequestMapping(value = "/ColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames testArray(@PathVariable("index") Long companyID) {		
		return environmentService.getColumnModelAndNames(companyID);    	
	}     

	// Environment Related Items
	@RequestMapping(value = "/relatedObjects/{environmentID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RelatedObjectList returnRelatedObjects(
			@PathVariable Long environmentID,    		
			@RequestParam(required = false) String cycleID,
			@RequestParam(required = false) String testcaseID,// +1 testcases
			@RequestParam(required = false) String projectID,// +1 testcases    		
			@RequestParam(required = false) String userID, // +1 testcases
			@RequestParam(required = false) String requirementID,
			@RequestParam(required = false) String testrunID,
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
			Environment environment = environmentService.getEnvironment(environmentID); 			
			EnvironmentSummary environmentSummary = new EnvironmentSummary(environment, testrunService, 
					defectService);

			Company company = companyService.getCompany(environmentSummary.getCompanyID());
			relatedObjectSet.add(new RelatedObject(1,
					company.getProjectsDisplayName(),
					Integer.toString(environmentSummary.getTotalProjects()), 
					environmentID, 
					company.getProjectsDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(2,
					company.getCyclesDisplayName(),
					Integer.toString(environmentSummary.getTotalCycles()), 
					environmentID, 
					company.getCyclesDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(3,
					company.getTestplansDisplayName(),
					Integer.toString(environmentSummary.getTotalTestplans()), 
					environmentID, 
					company.getTestplansDisplayName().replace(" ","")));	
			
			relatedObjectSet.add(new RelatedObject(4,
					company.getTestcasesDisplayName(),
					Integer.toString(environmentSummary.getTotalTestcases()), 
					environmentID, 
					company.getTestplansDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(5,
					"All "+ company.getTestrunsDisplayName(),
					Integer.toString(environmentSummary.getTotalAllTestruns()), 
					environmentID, 
					"all"+company.getTestrunsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(5,
					"Required "+ company.getTestrunsDisplayName(),
					Integer.toString(environmentSummary.getTotalRequiredTestruns()), 
					environmentID, 
					"required"+ company.getTestrunsDisplayName().replace(" ","")));		
			relatedObjectSet.add(new RelatedObject(5,
					"Optional "+ company.getTestrunsDisplayName(),
					Integer.toString(environmentSummary.getTotalOptionalTestruns()), 
					environmentID, 
					"optional"+ company.getTestrunsDisplayName().replace(" ","")));			

			relatedObjectSet.add(new RelatedObject(5,company.getDefectsDisplayName()+"-Total",Integer.toString(environmentSummary.getTotalDefects()), environmentID, company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(6,company.getDefectsDisplayName()+"-Sev 1",Integer.toString(environmentSummary.getTotalCurrentSev1s()), environmentID,company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(7,company.getDefectsDisplayName()+"-Sev 2",Integer.toString(environmentSummary.getTotalCurrentSev2s()), environmentID,company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(8,company.getDefectsDisplayName()+"-Sev 3",Integer.toString(environmentSummary.getTotalCurrentSev3s()), environmentID,company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(9,company.getDefectsDisplayName()+"-Sev 4",Integer.toString(environmentSummary.getTotalCurrentSev4s()), environmentID,company.getDefectsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(10,company.getRequirementsDisplayName(),Integer.toString(environmentSummary.getTotalRequirements()), environmentID, company.getRequirementsDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(12,company.getTestersDisplayName(),Integer.toString(environmentSummary.getTotalTesters()), environmentID, company.getTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(13,company.getSeniorTestersDisplayName(),Integer.toString(environmentSummary.getTotalSeniorTesters()), environmentID, company.getSeniorTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(14,company.getDevelopersDisplayName(),Integer.toString(environmentSummary.getTotalDevelopers()), environmentID, company.getDevelopersDisplayName().replace(" ",""))); 	
			relatedObjectSet.add(new RelatedObject(15,company.getSeniorDevelopersDisplayName(),Integer.toString(environmentSummary.getTotalSeniorDevelopers()), environmentID, company.getSeniorDevelopersDisplayName().replace(" ","")));

			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;

		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", environmentID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}    	
	} 

		
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewEnvironment(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="environmentName", required=true) String environmentName,			
			@RequestParam(value="environmentOs", required=true) String environmentOs,
			Model model) 
	{
		try{    					
			environmentService.addNewEnvironment(new Environment(companyID, environmentName, environmentOs));
			return "ok";   
		}
		catch(ConstraintViolationException CVE)
		{   			
			System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations()); 				
			return CVE.getConstraintViolations().toString();
		}	
	}	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deleteEnvironment(@RequestParam(value="id", required=true) Long environmentID) 
	{		
		environmentService.remove(environmentID);
	}	
	
	@RequestMapping(value = "/getcompanyenvironments/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EnvironmentList getCompanyEnvironments(@PathVariable("companyID") Long companyID) 
	{	
		EnvironmentList environmentList = new EnvironmentList();
		Set<Environment> environments = companyService.getCompany(companyID).getEnvironments();
		if(environments != null && !environments.isEmpty())
		{
			environmentList.setEnvironments(environments);
		}
		return environmentList; 		
	}  

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}
}
