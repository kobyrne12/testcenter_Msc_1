package ie.cit.cloud.testcenter.controller.json;


import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.RelatedObject;
import ie.cit.cloud.testcenter.display.RelatedObjectList;
import ie.cit.cloud.testcenter.model.ChangeImpactRule;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseList;
import ie.cit.cloud.testcenter.model.summary.TestplanList;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
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
@RequestMapping("cycle")
public class CycleJSONController {    
	@Autowired
	private CompanyService companyService; 
	@Autowired
	private ProjectService projectService;  
	@Autowired
	private CycleService cycleService;   	  
	@Autowired
	private TestrunService testrunService;
	@Autowired
	private DefectService defectService;	

	// Cycles
	@RequestMapping(value = "/summary/{cycleID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CycleSummary getCycleSummaryAt(@PathVariable("cycleID") Long cycleID)
	{
		Cycle cycle = cycleService.getCycle(cycleID);		
		CycleSummary cycleSummary = new CycleSummary(cycle, null, projectService, cycleService, testrunService, defectService);
		return cycleSummary;
	} 	

	@RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody CycleSummaryList returnCycles(
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
	@RequestMapping(value = "/ColsAndNames/{index}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ColModelAndNames returnColAndModels(@PathVariable("index") Long companyID) {		
		return cycleService.getColumnModelAndNames(companyID);    	
	} 

	// Cycle Related Items
	// Cycle Related Items
	@RequestMapping(value = "/relatedObjects/{cycleID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RelatedObjectList returnRelatedObjects(
			@PathVariable Long cycleID,    		
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
		//	public @ResponseBody RelatedObjectList getProjectRelatedObjects(@PathVariable("cycleID") Long cycleID) {
		//Cycle cycle = cycleService.getCycle(cycleID);
		Set<RelatedObject> relatedObjectSet =  new LinkedHashSet<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList();    	
		try{
			Cycle cycle = cycleService.getCycle(cycleID);
			//Project project = projectService.getProject(cycle.getProjectID());			
			CycleSummary cycleSummary = new CycleSummary(cycle, null, projectService, cycleService, testrunService, defectService);
			Company company = companyService.getCompany(cycleSummary.getCompanyID());			

			String parentCycleName = "";
			if(cycleSummary.getParentCycleName() != null)
			{
				parentCycleName = cycleSummary.getParentCycleName();							
			}

			relatedObjectSet.add(new RelatedObject(1,"Parent "+ company.getCycleDisplayName(),parentCycleName, cycleID, "parentCycle"));
			relatedObjectSet.add(new RelatedObject(2,"Child "+ company.getCyclesDisplayName(),String.valueOf(cycleSummary.getTotalChildCycles()), cycleID, "childCycles"));   	
			relatedObjectSet.add(new RelatedObject(3,company.getProjectDisplayName(),cycleSummary.getProjectName(), cycleID, company.getProjectDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(4,company.getTestplansDisplayName(),Integer.toString(cycleSummary.getTotalTestplans()), cycleID, company.getTestplansDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(5,company.getTestcasesDisplayName(),Integer.toString(cycleSummary.getTotalTestcases()), cycleID, company.getTestcasesDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(6,"All "+ company.getTestrunsDisplayName(),Integer.toString(cycleSummary.getTotalAllTestruns()), cycleID, "all"+company.getTestrunsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(7,"Required "+ company.getTestrunsDisplayName(),Integer.toString(cycleSummary.getTotalRequiredTestruns()), cycleID, "required"+company.getTestrunsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(8,"Optional "+ company.getTestrunsDisplayName(),Integer.toString(cycleSummary.getTotalOptionalTestruns()), cycleID, "optional"+company.getTestrunsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(15,company.getDefectsDisplayName()+"-Total",Long.toString(cycleSummary.getTotalDefects()), cycleID, company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(9,company.getDefectsDisplayName()+"-Sev 1",Long.toString(cycleSummary.getTotalCurrentSev1s()), cycleID,company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(10,company.getDefectsDisplayName()+"-Sev 2",Long.toString(cycleSummary.getTotalCurrentSev2s()), cycleID, company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(11,company.getDefectsDisplayName()+"-Sev 3",Long.toString(cycleSummary.getTotalCurrentSev3s()), cycleID,company.getDefectsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(12,company.getDefectsDisplayName()+"-Sev 4",Long.toString(cycleSummary.getTotalCurrentSev4s()), cycleID, company.getDefectsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(13,company.getEnvironmentsDisplayName(),String.valueOf(cycleSummary.getTotalEnvironments()), cycleID, company.getEnvironmentsDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(14,company.getRequirementsDisplayName(),String.valueOf(cycleSummary.getTotalRequirements()), cycleID,company.getRequirementsDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(16,company.getTestersDisplayName(),String.valueOf(cycleSummary.getTotalTesters()), cycleID, company.getTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(17,company.getSeniorTestersDisplayName(),String.valueOf(cycleSummary.getTotalSeniorTesters()), cycleID,company.getSeniorTestersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(18,company.getDevelopersDisplayName(),String.valueOf(cycleSummary.getTotalDevelopers()), cycleID,company.getDevelopersDisplayName().replace(" ","")));
			relatedObjectSet.add(new RelatedObject(19,company.getSeniorDevelopersDisplayName(),String.valueOf(cycleSummary.getTotalSeniorDevelopers()), cycleID, company.getSeniorDevelopersDisplayName().replace(" ","")));

			relatedObjectSet.add(new RelatedObject(20,company.getDefectDisplayName()+" Rules",String.valueOf(cycleSummary.getTotalDefectRules()), cycleID, company.getDefectsDisplayName().replace(" ","")+"Rules"));  
			relatedObjectSet.add(new RelatedObject(21,"Test History Rules",String.valueOf(cycleSummary.getTotalTestHistoryRules()), cycleID, "testHistoryRules"));  
			relatedObjectSet.add(new RelatedObject(22,"Code impact Rules",String.valueOf(cycleSummary.getTotalCodeImpactRules()), cycleID, "codeImpactRules"));  
			relatedObjectSet.add(new RelatedObject(23,company.getRequirementDisplayName()+" Rules",String.valueOf(cycleSummary.getTotalReqRules()), cycleID,company.getRequirementDisplayName().replace(" ","")+"Rules"));  

			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;

		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", cycleID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}

	} 
	/**
	 * Handles request for create a new Cycle
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewCycleAJAX(
			@RequestParam(value="projectID", required=true) Long projectID,
			@RequestParam(value="cycleName", required=true) String cycleName,
			@RequestParam(value="cycleStartDate", required=true) String cycleStartDate,
			@RequestParam(value="cycleEndDate", required=true) String cycleEndDate,	
			@RequestParam(value="requiredPriority", required=true) Integer requiredPriority,	
			Model model) 
	{
		System.out.println("************|||||||| ---: Received Ajax Request to create new Cycle");
		boolean alreadyExists = false;
		try
		{  
			Cycle cycle = cycleService.getCycleByName(cycleName);	
			if (cycle.getProjectID() == projectID )
			{				
				alreadyExists = true;
			}
		}
		catch(NoResultException e)
		{   			
			System.out.println("No Cycle Exists with that name "); 			
		}
		if(alreadyExists == false)
		{
			try{    
				int projectPosition = cycleService.getMaxProjectPosNum(projectID) + 1;					
				cycleService.addNewCycle(new Cycle(cycleName,projectID,requiredPriority,projectPosition,cycleStartDate,cycleEndDate));
				//Set All other Cycles latest to false
				Set<Cycle> projectCycles = projectService.getProject(projectID).getCycles();
				if(projectCycles == null || projectCycles.isEmpty())
				{
					return "Could Not reset"; 
				}
				for(Cycle cycle : projectCycles)
				{
					if(!cycle.getCycleName().equalsIgnoreCase(cycleName))
					{
						cycle.setLatest(false);
						cycleService.update(cycle);
					}
				}
				return "ok";   
			}
			catch(ConstraintViolationException CVE)
			{   			
				System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations()); 					
				return CVE.getConstraintViolations().toString();
			}			
		}
		else
		{
			return cycleName + " already Exists";	
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deleteCycle(@RequestParam(value="id", required=true) Long cycleID) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% Long cycleID = " +cycleID);
		cycleService.remove(cycleID);
	}	 

	// Projects
	@RequestMapping(value = "/cycle/{cycleID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Cycle getCycle(@PathVariable("cycleID") Long cycleID) {
		return cycleService.getCycle(cycleID);
	}

	@RequestMapping(value = "/getavailtestplans/{cycleID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TestplanList getAvailTestplans(@PathVariable("cycleID") Long cycleID) 
	{			
		TestplanList testplanList = new TestplanList();
		Set<Testplan> availableTestplans = cycleService.getAvailTestplans(cycleID);
		if(availableTestplans != null && !availableTestplans.isEmpty())
		{
			testplanList.setTestplans(availableTestplans);
		}		
		return testplanList; 		
	}  

	@RequestMapping(value = "/gettestplanavailtestcases/{cycleID}/{testplanID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TestcaseList getAvailTestcases(@PathVariable("cycleID") Long cycleID,
			@PathVariable("testplanID") Long testplanID) 
	{	
		// Get Testcases that have no testruns in the selected cycle
		TestcaseList testcaseList = new TestcaseList();
		Set<Testcase> availableTestcases = cycleService.getAvailTestcases(cycleID,testplanID);
		if(availableTestcases != null && !availableTestcases.isEmpty())
		{
			testcaseList.setTestcases(availableTestcases);
		}
		return testcaseList; 	
	}  

	@RequestMapping(value = "/getcycletestplans/{cycleID}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TestplanList getCycleTestplans(@PathVariable("cycleID") Long cycleID) 
	{	
		// Get Testcases that have no testruns in the selected cycle
		TestplanList testplanList = new TestplanList();
		Set<Testplan> availableTestplans = cycleService.getAllTestplans(cycleID);
		if(availableTestplans != null && !availableTestplans.isEmpty())
		{
			testplanList.setTestplans(availableTestplans);
		}
		return testplanList; 	
	}  

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String updateProject(
			@RequestParam(value="cycleID", required=true) Long cycleID,			
			@RequestParam(value="cycleName", required=true) String cycleName,			
			@RequestParam(value="cyclePriority", required=true) int requiredPriority,			
			Model model) 
	{
		Cycle cycle = cycleService.getCycle(cycleID);
		cycle.setCycleName(cycleName);
		cycle.setRequiredPriority(requiredPriority);
		cycleService.update(cycle);
		return "ok";
	}
	
	
	@RequestMapping(value = "/addccrule", method = RequestMethod.POST)
	public @ResponseBody String cycleAddRule(
			@RequestParam(value="cycleID", required=true) Long cycleID,
			@RequestParam(value="ccRuleName", required=true) String ccRuleName,			
			@RequestParam(value="ccRLevel", required=true) String ccRLevel,			
			@RequestParam(value="ccRRequirementPriority", required=true) int ccRRequirementPriority,
			@RequestParam(value="ccRtestrunPriorityChoice", required=true) String ccRtestrunPriorityChoice,
			@RequestParam(value="ccRtestrunPriority", required=true) int ccRtestrunPriority,
			Model model) 
	{
		
		cycleService.addChangeImpactRule(new ChangeImpactRule(cycleID, ccRuleName,ccRLevel,
				ccRRequirementPriority, ccRtestrunPriorityChoice,ccRtestrunPriority));		
		return "ok";
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
	}
}
