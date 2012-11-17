package ie.cit.cloud.testcenter.controller.json;


import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.NoResultException;
import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.RelatedObject;
import ie.cit.cloud.testcenter.model.summary.RelatedObjectList;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
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
	public @ResponseBody CycleSummary getCycleSummaryAt(@PathVariable("cycleID") long cycleID) {
		return cycleService.getCycleSummary(cycleID);
	} 	
	
	@RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CycleSummaryList returnCycles(
			@PathVariable long companyID,    		
			@RequestParam(required = false) String projectID,
			@RequestParam(required = false) String testplanID,
			@RequestParam(required = false) String testcaseID,  		
			@RequestParam(required = false) String userID, 
			@RequestParam(required = false) String environmentID,
			@RequestParam(required = false) String requirementID,
			@RequestParam(required = false) String defectID,    		
			@RequestParam(required = false) String testrunID
			) 
	{
		return cycleService.getGridCycles(companyID,projectID,testplanID,testcaseID,userID,environmentID,
				requirementID,defectID,testrunID);  	   	
	}  

	// Columns for project CHANGE companyID TO UserID
	@RequestMapping(value = "/projectColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames returnColAndModels(@PathVariable("index") long companyID) {		
		return cycleService.getColumnModelAndNames(companyID);    	
	} 

	// Project Related Items
	@RequestMapping(value = "/projectRelatedObjects/{cycleID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RelatedObjectList getProjectRelatedObjects(@PathVariable("cycleID") long cycleID) {
		//Cycle cycle = cycleService.getCycle(cycleID);
		Collection<RelatedObject> relatedObjectCollection =  new ArrayList<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList();    	
		try{
			CycleSummary cycleSummary = cycleService.getCycleSummary(cycleID); 
			Company company = companyService.getCompany(cycleSummary.getCompanyID());

			relatedObjectCollection.add(new RelatedObject(1,"Parent "+ company.getCycleDisplayName(),cycleSummary.getParentCycleName()));
			relatedObjectCollection.add(new RelatedObject(2,"Child "+ company.getCyclesDisplayName(),String.valueOf(cycleSummary.getTotalChildCycles())));
			relatedObjectCollection.add(new RelatedObject(3,company.getProjectDisplayName(),cycleSummary.getProjectName()));

			relatedObjectCollection.add(new RelatedObject(4,"All "+ company.getTestrunsDisplayName(),String.valueOf(cycleSummary.getTotalAllTestruns())));
			relatedObjectCollection.add(new RelatedObject(5,"Required "+ company.getTestrunsDisplayName(),String.valueOf(cycleSummary.getTotalAllTestruns())));    	
			relatedObjectCollection.add(new RelatedObject(6,company.getTestcasesDisplayName(),String.valueOf(cycleSummary.getTotalTestcases())));    
			relatedObjectCollection.add(new RelatedObject(7,company.getTestplansDisplayName(),String.valueOf(cycleSummary.getTotalTestplans()))); 

			relatedObjectCollection.add(new RelatedObject(8,company.getTestersDisplayName(),String.valueOf(cycleSummary.getTotalTesters())));    
			relatedObjectCollection.add(new RelatedObject(9,company.getSeniorTestersDisplayName(),String.valueOf(cycleSummary.getTotalSeniorTesters())));    
			relatedObjectCollection.add(new RelatedObject(10,company.getDevelopersDisplayName(),String.valueOf(cycleSummary.getTotalDevelopers())));     	
			relatedObjectCollection.add(new RelatedObject(11,company.getSeniordevelopersDisplayName(),String.valueOf(cycleSummary.getTotalSeniorDevelopers()))); 

			relatedObjectCollection.add(new RelatedObject(12,company.getEnvironmentsDisplayName(),String.valueOf(cycleSummary.getTotalEnvironments())));    
			relatedObjectCollection.add(new RelatedObject(13,company.getRequirementsDisplayName(),String.valueOf(cycleSummary.getTotalRequirements())));    
			relatedObjectCollection.add(new RelatedObject(14,company.getDefectsDisplayName()+"-Total",Long.toString(cycleSummary.getTotalDefects())));

			relatedObjectCollection.add(new RelatedObject(15,company.getDefectsDisplayName()+"-Sev 1",Long.toString(cycleSummary.getTotalCurrentSev1s())));
			relatedObjectCollection.add(new RelatedObject(16,company.getDefectsDisplayName()+"-Sev 2",Long.toString(cycleSummary.getTotalCurrentSev2s())));
			relatedObjectCollection.add(new RelatedObject(17,company.getDefectsDisplayName()+"-Sev 3",Long.toString(cycleSummary.getTotalCurrentSev3s())));
			relatedObjectCollection.add(new RelatedObject(18,company.getDefectsDisplayName()+"-Sev 4",Long.toString(cycleSummary.getTotalCurrentSev4s())));

			relatedObjectCollection.add(new RelatedObject(19,company.getDefectDisplayName()+" Rules",String.valueOf(cycleSummary.getTotalDefectRules())));  
			relatedObjectCollection.add(new RelatedObject(20,"Test History Rules",String.valueOf(cycleSummary.getTotalTestHistoryRules())));  
			relatedObjectCollection.add(new RelatedObject(21,"Code impact Rules",String.valueOf(cycleSummary.getTotalCodeImpactRules())));  
			relatedObjectCollection.add(new RelatedObject(22,company.getRequirementDisplayName()+" Rules",String.valueOf(cycleSummary.getTotalReqRules())));  

			relatedObjectList.setRelatedObjects(relatedObjectCollection);
			return relatedObjectList;
			
		}catch(NoResultException e)
		{
			relatedObjectCollection.add(new RelatedObject(1,"Select a Row to View Details",""));  
			relatedObjectList.setRelatedObjects(relatedObjectCollection);
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
