package ie.cit.cloud.testcenter.controller.json;


import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.NoResultException;
import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.RelatedObject;
import ie.cit.cloud.testcenter.display.RelatedObjectList;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("cycle")
public class CycleJSONController {    
	@Autowired
	private CompanyService companyService; 
	@Autowired
	private ProjectService projectService;  
	@Autowired
	private CycleService cycleService;   

	// Cycles
	@RequestMapping(value = "/summary/{cycleID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CycleSummary getCycleSummaryAt(@PathVariable("cycleID") long cycleID)
	{
		Cycle cycle = cycleService.getCycle(cycleID);
		Project project = projectService.getProject(cycle.getProjectID());
		return cycleService.getCycleSummary(project.getCompanyID(), cycleID, null);
	} 	
	
	@RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CycleSummaryList returnCycles(
			@PathVariable long companyID,    		
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
    		@RequestParam("_search") boolean search,
    		@RequestParam(value="filters", required=false) String filters,
    		@RequestParam(value="page", required=false) Integer page,
    		@RequestParam(value="rows", required=false) Integer rows,
    		@RequestParam(value="sidx", required=false) String sidx,
    		@RequestParam(value="sord", required=false) String sord) throws JsonParseException, JsonMappingException, IOException    		
    {    	
		//cycle/summaryList/1?projectID=5&cycleID=5&projectID=5
		return cycleService.getGridCycles(companyID,projectID,cycleID,testplanID,testcaseID,userID,environmentID,
				requirementID,defectID,testrunID,level);  	   	
	}  
	
	// Columns for project CHANGE companyID TO UserID
	@RequestMapping(value = "/projectColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames returnColAndModels(@PathVariable("index") long companyID) {		
		return cycleService.getColumnModelAndNames(companyID);    	
	} 

	// Cycle Related Items
	 // Cycle Related Items
    @RequestMapping(value = "/relatedObjects/{cycleID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RelatedObjectList returnRelatedObjects(
    		@PathVariable long cycleID,    		
    		@RequestParam(required = false) String projectID,
    		@RequestParam(required = false) String testplanID,// +1 projects
    		@RequestParam(required = false) String testcaseID,// +1 projects    		
    		@RequestParam(required = false) String userID, // +1 projects
    		@RequestParam(required = false) String environmentID,
    		@RequestParam(required = false) String requirementID,
    		@RequestParam(required = false) String defectID,    		
    		@RequestParam(required = false) String testrunID
    		) 
    {   	
//	@RequestMapping(value = "/relatedObjects/{cycleID}", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public @ResponseBody RelatedObjectList getProjectRelatedObjects(@PathVariable("cycleID") long cycleID) {
		//Cycle cycle = cycleService.getCycle(cycleID);
		Set<RelatedObject> relatedObjectSet =  new LinkedHashSet<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList();    	
		try{
			Cycle cycle = cycleService.getCycle(cycleID);
			Project project = projectService.getProject(cycle.getProjectID());			
			CycleSummary cycleSummary = cycleService.getCycleSummary(project.getCompanyID(), cycleID, null);
			Company company = companyService.getCompany(cycleSummary.getCompanyID());

			relatedObjectSet.add(new RelatedObject(1,"Parent "+ company.getCycleDisplayName(),cycleSummary.getParentCycleName(), cycleID, "parentCycle"));
			relatedObjectSet.add(new RelatedObject(2,"Child "+ company.getCyclesDisplayName(),String.valueOf(cycleSummary.getTotalChildCycles()), cycleID, "childCycles"));   	
			relatedObjectSet.add(new RelatedObject(3,company.getProjectDisplayName(),cycleSummary.getProjectName(), cycleID, company.getProjectDisplayName().replace(" ","")));
			
			relatedObjectSet.add(new RelatedObject(4,"All "+ company.getTestrunsDisplayName(),Integer.toString(cycleSummary.getTotalAllTestruns()), cycleID, "all"+company.getTestrunsDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(5,"Required "+ company.getTestrunsDisplayName(),Integer.toString(cycleSummary.getTotalRequiredTestruns()), cycleID, "required"+company.getTestrunsDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(6,"Optional "+ company.getTestrunsDisplayName(),Integer.toString(cycleSummary.getTotalOptionalTestruns()), cycleID, "optional"+company.getTestrunsDisplayName().replace(" ","")));
	    	
	    	relatedObjectSet.add(new RelatedObject(7,company.getTestcasesDisplayName(),Integer.toString(cycleSummary.getTotalAllTestcases()), cycleID,  "all"+company.getTestcasesDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(10,company.getTestplansDisplayName(),Integer.toString(cycleSummary.getTotalAllTestplans()), cycleID,  "all"+company.getTestplansDisplayName().replace(" ","")));
	    	
	    	
//	    	relatedObjectSet.add(new RelatedObject(7,"All "+ company.getTestcasesDisplayName(),Integer.toString(cycleSummary.getTotalAllTestcases()), cycleID,  "all"+company.getTestcasesDisplayName()));
//	    	relatedObjectSet.add(new RelatedObject(8,"Required "+ company.getTestcasesDisplayName(),Integer.toString(cycleSummary.getTotalRequiredTestcases()), cycleID, "required"+company.getTestcasesDisplayName()));
//	    	relatedObjectSet.add(new RelatedObject(9,"Optional "+ company.getTestcasesDisplayName(),Integer.toString(cycleSummary.getTotalOptionalTestcases()), cycleID, "optional"+company.getTestcasesDisplayName()));
//	    	
//	    	relatedObjectSet.add(new RelatedObject(10,"All "+ company.getTestplansDisplayName(),Integer.toString(cycleSummary.getTotalAllTestplans()), cycleID,  "all"+company.getTestplansDisplayName()));
//	    	relatedObjectSet.add(new RelatedObject(11,"Required "+ company.getTestplansDisplayName(),Integer.toString(cycleSummary.getTotalRequiredTestplans()), cycleID, "required"+company.getTestplansDisplayName()));
//	    	relatedObjectSet.add(new RelatedObject(12,"Optional "+ company.getTestplansDisplayName(),Integer.toString(cycleSummary.getTotalOptionalTestplans()), cycleID, "optional"+company.getTestplansDisplayName()));
//	 
			
			relatedObjectSet.add(new RelatedObject(13,company.getTestersDisplayName(),String.valueOf(cycleSummary.getTotalTesters()), cycleID, company.getTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(14,company.getSeniorTestersDisplayName(),String.valueOf(cycleSummary.getTotalSeniorTesters()), cycleID,company.getSeniorTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(15,company.getDevelopersDisplayName(),String.valueOf(cycleSummary.getTotalDevelopers()), cycleID,company.getDevelopersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(16,company.getSeniordevelopersDisplayName(),String.valueOf(cycleSummary.getTotalSeniorDevelopers()), cycleID, company.getSeniordevelopersDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(17,company.getEnvironmentsDisplayName(),String.valueOf(cycleSummary.getTotalEnvironments()), cycleID, company.getEnvironmentsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(18,company.getRequirementsDisplayName(),String.valueOf(cycleSummary.getTotalRequirements()), cycleID,company.getRequirementsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(19,company.getDefectsDisplayName()+"-Total",Long.toString(cycleSummary.getTotalDefects()), cycleID, company.getDefectsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(20,company.getDefectsDisplayName()+"-Sev 1",Long.toString(cycleSummary.getTotalCurrentSev1s()), cycleID, "sev1"));
			relatedObjectSet.add(new RelatedObject(21,company.getDefectsDisplayName()+"-Sev 2",Long.toString(cycleSummary.getTotalCurrentSev2s()), cycleID, "sev2"));
			relatedObjectSet.add(new RelatedObject(22,company.getDefectsDisplayName()+"-Sev 3",Long.toString(cycleSummary.getTotalCurrentSev3s()), cycleID, "sev3"));
			relatedObjectSet.add(new RelatedObject(23,company.getDefectsDisplayName()+"-Sev 4",Long.toString(cycleSummary.getTotalCurrentSev4s()), cycleID, "sev4"));

			relatedObjectSet.add(new RelatedObject(24,company.getDefectDisplayName()+" Rules",String.valueOf(cycleSummary.getTotalDefectRules()), cycleID, company.getDefectsDisplayName().replace(" ","")+"Rules"));  
			relatedObjectSet.add(new RelatedObject(25,"Test History Rules",String.valueOf(cycleSummary.getTotalTestHistoryRules()), cycleID, "testHistoryRules"));  
			relatedObjectSet.add(new RelatedObject(26,"Code impact Rules",String.valueOf(cycleSummary.getTotalCodeImpactRules()), cycleID, "codeImpactRules"));  
			relatedObjectSet.add(new RelatedObject(27,company.getRequirementDisplayName()+" Rules",String.valueOf(cycleSummary.getTotalReqRules()), cycleID,company.getRequirementDisplayName().replace(" ","")+"Rules"));  

			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
			
		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", cycleID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}

	} 
	// Projects
	@RequestMapping(value = "/cycle/{cycleID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Cycle getCycle(@PathVariable("cycleID") long cycleID) {
		return cycleService.getCycle(cycleID);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}
}
