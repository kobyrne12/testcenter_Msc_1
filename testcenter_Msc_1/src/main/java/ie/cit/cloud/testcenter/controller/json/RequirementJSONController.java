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
@RequestMapping("requirement")
public class RequirementJSONController {    
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
	private DefectService defectService;
	@Autowired
	private TestrunService testrunService;


	// All Testplans For a company
	@RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RequirementSummaryList returnRequirements(
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

			RequirementSummaryList requirementSummaryList = requirementService.getGridRequirments(companyID, projectID,cycleID, requirementID, testcaseID,
					requirementID, defectID, requirementID,
					environmentID, userID,level,stage,required);

			Set<RequirementSummary> oldRequirementSummarySet = requirementSummaryList.getRequirements();
			Set<RequirementSummary> newRequirementSummarySet = new LinkedHashSet<RequirementSummary>();

			for(RequirementSummary oldRequirementSummary : oldRequirementSummarySet)
			{
				boolean requirementNameFound = false; 
				boolean requirementIDFound = false; 
				//System.out.println(" ^^^^^^ 3 : " + oldRequirementSummary.getRequirementName());
				for(Rule rule : jqgridFilter.getRules())
				{        			
					//System.out.println(" ^^^^^^ 4 : " + rule.getField());
					if(rule.getField().equalsIgnoreCase("requirementSummary"))
					{
						//System.out.println(" ^^^^^^ 5 a : " +oldRequirementSummary.getRequirementName());
						if(oldRequirementSummary.getRequirementSummary().toLowerCase().contains(rule.getData().toLowerCase()))
						{
							requirementNameFound = true;      					
						}
					}
					else
					{
						requirementNameFound = true;   
					}
					if(rule.getField().equalsIgnoreCase("requirementID"))
					{
						System.out.println(" ^^^^^^ 5 a : " +oldRequirementSummary.getRequirementID());
						if(String.valueOf(oldRequirementSummary.getRequirementID()).toLowerCase().contains(rule.getData().toLowerCase()))
						{        		
							System.out.println(" ^^^^^^ 5 b : " +oldRequirementSummary.getRequirementID());
							requirementIDFound = true; 
						}
					}
					else
					{
						System.out.println(" ^^^^^^ 5 c : " +oldRequirementSummary.getRequirementID());
						requirementIDFound = true; 
					}
				}
				if(requirementNameFound == true && requirementIDFound == true)
				{
					newRequirementSummarySet.add(oldRequirementSummary);
				}
			}

			requirementSummaryList.setRequirements(newRequirementSummarySet);    		
			return requirementSummaryList;
		}
		else
		{
			return requirementService.getGridRequirments(companyID, projectID,
					cycleID, testplanID, testcaseID,
					requirementID, defectID, requirementID,
					environmentID, userID,level,stage,required);
		}
	}     

	// Columns for requirement CHANGE companyID TO UserID
	@RequestMapping(value = "/ColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames testArray(@PathVariable("index") Long companyID) {		
		return requirementService.getColumnModelAndNames(companyID);    	
	}     

	// Requirement Related Items
	@RequestMapping(value = "/relatedObjects/{requirementID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RelatedObjectList returnRelatedObjects(
			@PathVariable Long requirementID,    		
			@RequestParam(required = false) String cycleID,
			@RequestParam(required = false) String testcaseID,// +1 testcases
			@RequestParam(required = false) String projectID,// +1 testcases    		
			@RequestParam(required = false) String userID, // +1 testcases
			@RequestParam(required = false) String environmentID,
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
			Requirement requirement = requirementService.getRequirement(requirementID); 			
			RequirementSummary requirementSummary = new RequirementSummary(requirement,testrunService, 
					defectService,testplanService);

			Company company = companyService.getCompany(requirementSummary.getCompanyID());
			relatedObjectSet.add(new RelatedObject(1,
					company.getProjectsDisplayName(),
					Integer.toString(requirementSummary.getTotalProjects()), 
					requirementID, 
					company.getProjectsDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(2,
					company.getCyclesDisplayName(),
					Integer.toString(requirementSummary.getTotalCycles()), 
					requirementID, 
					company.getCyclesDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(3,
					company.getTestplansDisplayName(),
					Integer.toString(requirementSummary.getTotalTestplans()), 
					requirementID, 
					company.getTestplansDisplayName().replace(" ","")));	
			
			relatedObjectSet.add(new RelatedObject(4,
					company.getTestcasesDisplayName(),
					Integer.toString(requirementSummary.getTotalTestcases()), 
					requirementID, 
					company.getTestplansDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(5,
					"All "+ company.getTestrunsDisplayName(),
					Integer.toString(requirementSummary.getTotalAllTestruns()), 
					requirementID, 
					"all"+company.getTestrunsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(5,
					"Required "+ company.getTestrunsDisplayName(),
					Integer.toString(requirementSummary.getTotalRequiredTestruns()), 
					requirementID, 
					"required"+ company.getTestrunsDisplayName().replace(" ","")));		
			relatedObjectSet.add(new RelatedObject(5,
					"Optional "+ company.getTestrunsDisplayName(),
					Integer.toString(requirementSummary.getTotalOptionalTestruns()), 
					requirementID, 
					"optional"+ company.getTestrunsDisplayName().replace(" ","")));			

			relatedObjectSet.add(new RelatedObject(5,company.getDefectsDisplayName()+"-Total",Integer.toString(requirementSummary.getTotalDefects()), requirementID, company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(6,company.getDefectsDisplayName()+"-Sev 1",Integer.toString(requirementSummary.getTotalCurrentSev1s()), requirementID,"sev1"));
			relatedObjectSet.add(new RelatedObject(7,company.getDefectsDisplayName()+"-Sev 2",Integer.toString(requirementSummary.getTotalCurrentSev2s()), requirementID,"sev2"));
			relatedObjectSet.add(new RelatedObject(8,company.getDefectsDisplayName()+"-Sev 3",Integer.toString(requirementSummary.getTotalCurrentSev3s()), requirementID,"sev3"));
			relatedObjectSet.add(new RelatedObject(9,company.getDefectsDisplayName()+"-Sev 4",Integer.toString(requirementSummary.getTotalCurrentSev4s()), requirementID,"sev4"));

			relatedObjectSet.add(new RelatedObject(10,company.getEnvironmentsDisplayName(),Integer.toString(requirementSummary.getTotalEnvironments()), requirementID, company.getEnvironmentsDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(12,company.getTestersDisplayName(),Integer.toString(requirementSummary.getTotalTesters()), requirementID, company.getTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(13,company.getSeniorTestersDisplayName(),Integer.toString(requirementSummary.getTotalSeniorTesters()), requirementID, company.getSeniorTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(14,company.getDevelopersDisplayName(),Integer.toString(requirementSummary.getTotalDevelopers()), requirementID, company.getDevelopersDisplayName().replace(" ",""))); 	
			relatedObjectSet.add(new RelatedObject(15,company.getSeniorDevelopersDisplayName(),Integer.toString(requirementSummary.getTotalSeniorDevelopers()), requirementID, company.getSeniorDevelopersDisplayName().replace(" ","")));

			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;

		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", requirementID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}    	
	} 

		
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewRequirement(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="requirementSummary", required=true) String requirementSummary,
			@RequestParam(value="requirementDetails", required=true) String requirementDetails,
			@RequestParam(value="requirementPriority", required=true) Integer requirementPriority,
			Model model) 
	{
		try{    					
			requirementService.addNewRequirement(new Requirement(companyID,requirementSummary,requirementDetails,null,requirementPriority));
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
	public void deleteRequirement(@RequestParam(value="id", required=true) Long requirementID) 
	{		
		requirementService.remove(requirementID);
	}	
	
	@RequestMapping(value = "/getcompanyrequirements/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RequirementList getCompanyRequirements(@PathVariable("companyID") Long companyID) 
	{	
		RequirementList requirementList = new RequirementList();
		Set<Requirement> requirements = companyService.getCompany(companyID).getRequirements();
		if(requirements != null && !requirements.isEmpty())
		{
			requirementList.setRequirements(requirements);
		}
		return requirementList; 		
	}  

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}
}
