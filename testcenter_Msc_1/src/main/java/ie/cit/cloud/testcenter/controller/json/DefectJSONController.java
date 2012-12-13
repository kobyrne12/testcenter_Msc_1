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
import ie.cit.cloud.testcenter.model.summary.DefectList;
import ie.cit.cloud.testcenter.model.summary.DefectSummary;
import ie.cit.cloud.testcenter.model.summary.DefectSummaryList;
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
@RequestMapping("defect")
public class DefectJSONController {    
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
	public @ResponseBody DefectSummaryList returnDefects(
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

			DefectSummaryList defectSummaryList = defectService.getGridDefects(companyID, projectID,cycleID, testplanID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);

			Set<DefectSummary> oldDefectSummarySet = defectSummaryList.getDefects();
			Set<DefectSummary> newDefectSummarySet = new LinkedHashSet<DefectSummary>();

			for(DefectSummary oldDefectSummary : oldDefectSummarySet)
			{
				boolean defectNameFound = false; 
				boolean defectIDFound = false; 
				//System.out.println(" ^^^^^^ 3 : " + oldDefectSummary.getDefectName());
				for(Rule rule : jqgridFilter.getRules())
				{        			
					//System.out.println(" ^^^^^^ 4 : " + rule.getField());
					if(rule.getField().equalsIgnoreCase("defectSummary"))
					{
						//System.out.println(" ^^^^^^ 5 a : " +oldDefectSummary.getDefectName());
						if(oldDefectSummary.getDefectSummary().toLowerCase().contains(rule.getData().toLowerCase()))
						{
							defectNameFound = true;      					
						}
					}
					else
					{
						defectNameFound = true;   
					}
					if(rule.getField().equalsIgnoreCase("defectID"))
					{
						System.out.println(" ^^^^^^ 5 a : " +oldDefectSummary.getDefectID());
						if(String.valueOf(oldDefectSummary.getDefectID()).toLowerCase().contains(rule.getData().toLowerCase()))
						{        		
							System.out.println(" ^^^^^^ 5 b : " +oldDefectSummary.getDefectID());
							defectIDFound = true; 
						}
					}
					else
					{
						System.out.println(" ^^^^^^ 5 c : " +oldDefectSummary.getDefectID());
						defectIDFound = true; 
					}
				}
				if(defectNameFound == true && defectIDFound == true)
				{
					newDefectSummarySet.add(oldDefectSummary);
				}
			}

			defectSummaryList.setDefects(newDefectSummarySet);    		
			return defectSummaryList;
		}
		else
		{
			return defectService.getGridDefects(companyID, projectID,
					cycleID, testplanID, testcaseID,
					testrunID, defectID, requirementID,
					environmentID, userID,level,stage,required);
		}
	}     

	// Columns for requirement CHANGE companyID TO UserID
	@RequestMapping(value = "/ColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames testArray(@PathVariable("index") Long companyID) {		
		return defectService.getColumnModelAndNames(companyID);    	
	}     

	// Defect Related Items
	@RequestMapping(value = "/relatedObjects/{defectID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RelatedObjectList returnRelatedObjects(
			@PathVariable Long defectID,    		
			@RequestParam(required = false) String cycleID,
			@RequestParam(required = false) String testcaseID,// +1 testcases
			@RequestParam(required = false) String projectID,// +1 testcases    		
			@RequestParam(required = false) String userID, // +1 testcases
			@RequestParam(required = false) String environmentID,
			@RequestParam(required = false) String testrunID,
			@RequestParam(required = false) String requirementD,    		
			@RequestParam(required = false) String testplanID,
			@RequestParam(required = false) String levelName,
			@RequestParam(required = false) String stage,
			@RequestParam(required = false) String required
			) 
	{   	
		Set<RelatedObject> relatedObjectSet =  new LinkedHashSet<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList();    	
		try{
			Defect defect = defectService.getDefect(defectID); 			
			DefectSummary defectSummary = new DefectSummary(defect,testrunService, 
					defectService,testplanService);

			Company company = companyService.getCompany(defectSummary.getCompanyID());
			relatedObjectSet.add(new RelatedObject(1,
					company.getProjectsDisplayName(),
					Integer.toString(defectSummary.getTotalProjects()), 
					defectID, 
					company.getProjectsDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(2,
					company.getCyclesDisplayName(),
					Integer.toString(defectSummary.getTotalCycles()), 
					defectID, 
					company.getCyclesDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(3,
					company.getTestplansDisplayName(),
					Integer.toString(defectSummary.getTotalTestplans()), 
					defectID, 
					company.getTestplansDisplayName().replace(" ","")));	
			
			relatedObjectSet.add(new RelatedObject(4,
					company.getTestcasesDisplayName(),
					Integer.toString(defectSummary.getTotalTestcases()), 
					defectID, 
					company.getTestcasesDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(5,
					"All "+ company.getTestrunsDisplayName(),
					Integer.toString(defectSummary.getTotalAllTestruns()), 
					defectID, 
					"all"+company.getTestrunsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(6,
					"Required "+ company.getTestrunsDisplayName(),
					Integer.toString(defectSummary.getTotalRequiredTestruns()), 
					defectID, 
					"required"+ company.getTestrunsDisplayName().replace(" ","")));		
			relatedObjectSet.add(new RelatedObject(7,
					"Optional "+ company.getTestrunsDisplayName(),
					Integer.toString(defectSummary.getTotalOptionalTestruns()), 
					defectID, 
					"optional"+ company.getTestrunsDisplayName().replace(" ","")));	
			
			relatedObjectSet.add(new RelatedObject(8,company.getRequirementsDisplayName(),Integer.toString(defectSummary.getTotalRequirements()), defectID, company.getRequirementsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(9,company.getEnvironmentsDisplayName(),Integer.toString(defectSummary.getTotalEnvironments()), defectID, company.getEnvironmentsDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(10,company.getTestersDisplayName(),Integer.toString(defectSummary.getTotalTesters()), defectID, company.getTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(11,company.getSeniorTestersDisplayName(),Integer.toString(defectSummary.getTotalSeniorTesters()), defectID, company.getSeniorTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(12,company.getDevelopersDisplayName(),Integer.toString(defectSummary.getTotalDevelopers()), defectID, company.getDevelopersDisplayName().replace(" ",""))); 	
			relatedObjectSet.add(new RelatedObject(13,company.getSeniorDevelopersDisplayName(),Integer.toString(defectSummary.getTotalSeniorDevelopers()), defectID, company.getSeniorDevelopersDisplayName().replace(" ","")));

			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;

		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", defectID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}    	
	} 

		
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewDefect(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="defectSummary", required=true) String defectSummary,
			@RequestParam(value="defectDetails", required=true) String defectDetails,
			@RequestParam(value="defectSeverity", required=true) Integer defectSeverity,
			Model model) 
	{
		try{    					
			defectService.addNewDefect(new  Defect(companyID, defectSummary, defectSeverity,
					defectDetails, null, null));
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
	public void deleteDefect(@RequestParam(value="id", required=true) Long defectID) 
	{		
		defectService.remove(defectID);
	}	
	
	@RequestMapping(value = "/getcompanydefects/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody DefectList getCompanyDefects(@PathVariable("companyID") Long companyID) 
	{	
		DefectList defectList = new DefectList();
		Set<Defect> defects = companyService.getCompany(companyID).getDefects();
		if(defects != null && !defects.isEmpty())
		{
			defectList.setDefects(defects);
		}
		return defectList; 		
	}  
	 
	@RequestMapping(value = "/addRequirement ", method = RequestMethod.POST)
	public @ResponseBody String addReqToDefect(
			@RequestParam(value="companyID", required=true) Long companyID,
			@RequestParam(value="defectID", required=true) Long defectID,
			@RequestParam(value="requirementID", required=true) Long requirementID,					
			Model model) 
	{
		Defect defect = defectService.getDefect(defectID);
		Requirement requirement = requirementService.getRequirement(requirementID);
		requirement.getDefects().add(defect);
		defect.getRequirements().add(requirement);
		defectService.update(defect);
		requirementService.update(requirement);
		int req = defect.getRequirementCount();
		System.out.println(req);
		int def = requirement.getDefects().size();
		System.out.println(def);
		return "ok"; 		
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}
}
