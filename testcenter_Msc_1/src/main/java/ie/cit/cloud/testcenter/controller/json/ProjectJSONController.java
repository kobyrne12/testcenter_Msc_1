package ie.cit.cloud.testcenter.controller.json;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.NoResultException;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.display.RelatedObject;
import ie.cit.cloud.testcenter.display.RelatedObjectList;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
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
@RequestMapping("project")
public class ProjectJSONController {    
    @Autowired
    private CompanyService companyService; 
    @Autowired
    private ProjectService projectService;  
    @Autowired
    private CycleService cycleService;   
    
   
    // All Projects For a company
    @RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProjectSummaryList returnProjects(
    		@PathVariable long companyID,
    		@RequestParam(required = false) String projectID,
    		@RequestParam(required = false) String cycleID,
    		@RequestParam(required = false) String testplanID,
    		@RequestParam(required = false) String testcaseID, 
    		@RequestParam(required = false) String testrunID,   
    		@RequestParam(required = false) String defectID,       		
    		@RequestParam(required = false) String requirementID,
    		@RequestParam(required = false) String environmentID,    		
    		@RequestParam(required = false) String userID) 
    {    	
    	return projectService.getGridProjects(companyID, projectID, cycleID, testplanID,
    			testcaseID, testrunID, defectID, requirementID, environmentID, userID);
    }     
  
    // Columns for project CHANGE companyID TO UserID
    @RequestMapping(value = "/projectColsAndNames/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ColModelAndNames testArray(@PathVariable("index") long companyID) {		
    	return projectService.getColumnModelAndNames(companyID);    	
    }     
  
    // Project Related Items
    @RequestMapping(value = "/relatedObjects/{projectID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RelatedObjectList returnRelatedObjects(
    		@PathVariable long projectID,    		
    		@RequestParam(required = false) String cycleID,
    		@RequestParam(required = false) String testplanID,// +1 projects
    		@RequestParam(required = false) String testcaseID,// +1 projects    		
    		@RequestParam(required = false) String userID, // +1 projects
    		@RequestParam(required = false) String environmentID,
    		@RequestParam(required = false) String requirementID,
    		@RequestParam(required = false) String defectID,    		
    		@RequestParam(required = false) String testrunID
    		) 
    {   	
    	Collection<RelatedObject> relatedObjectCollection =  new ArrayList<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList();    	
		try{
			Project project = projectService.getProject(projectID); 
			ProjectSummary projectSummary = projectService.getProjectSummary(project.getCompanyID(), projectID, cycleID, testplanID,
	    			testcaseID, testrunID, defectID, requirementID, environmentID, userID);
			
	    	Company company = companyService.getCompany(projectSummary.getCompanyID());
	    	
	    	relatedObjectCollection.add(new RelatedObject(1,"Parent "+ company.getProjectDisplayName(),projectSummary.getParentProjectName(), projectID, "Parent"+ company.getProjectDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(2,"Child "+ company.getProjectsDisplayName(),Integer.toString(projectSummary.getChildProjects()), projectID, "Child"+ company.getProjectsDisplayName()));
	    	
	    	relatedObjectCollection.add(new RelatedObject(3,company.getCyclesDisplayName(),Integer.toString(projectSummary.getTotalCycles()), projectID, company.getCyclesDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(4,company.getTestrunsDisplayName(),Integer.toString(projectSummary.getTotalAllTestruns()), projectID, "All "+company.getTestrunsDisplayName()));  
	    	relatedObjectCollection.add(new RelatedObject(5,company.getTestrunsDisplayName(),Integer.toString(projectSummary.getTotalRequiredTestruns()), projectID, "Required "+company.getTestrunsDisplayName()));  
	    	relatedObjectCollection.add(new RelatedObject(6,company.getTestrunsDisplayName(),Integer.toString(projectSummary.getTotalOptionalTestruns()), projectID, "Optional "+company.getTestrunsDisplayName()));  
	    	
	    	relatedObjectCollection.add(new RelatedObject(7,company.getTestcasesDisplayName(),Integer.toString(projectSummary.getTotalAllTestcases()), projectID,  "All "+company.getTestcasesDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(8,company.getTestcasesDisplayName(),Integer.toString(projectSummary.getTotalRequiredTestcases()), projectID, "Required "+company.getTestcasesDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(9,company.getTestcasesDisplayName(),Integer.toString(projectSummary.getTotalOptionalTestcases()), projectID, company.getTestcasesDisplayName()));
	    	
	    	relatedObjectCollection.add(new RelatedObject(10,company.getTestplansDisplayName(),Integer.toString(projectSummary.getTotalAllTestplans()), projectID,  "All "+company.getTestplansDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(11,company.getTestplansDisplayName(),Integer.toString(projectSummary.getTotalRequiredTestplans()), projectID, "Required "+company.getTestplansDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(12,company.getTestplansDisplayName(),Integer.toString(projectSummary.getTotalOptionalTestplans()), projectID, "Optional "+company.getTestplansDisplayName()));
	    		    	
	    	relatedObjectCollection.add(new RelatedObject(13,company.getTestersDisplayName(),Integer.toString(projectSummary.getTotalTesters()), projectID, company.getTestersDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(14,company.getSeniorTestersDisplayName(),Integer.toString(projectSummary.getTotalSeniorTesters()), projectID, company.getSeniorTestersDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(15,company.getDevelopersDisplayName(),Integer.toString(projectSummary.getTotalDevelopers()), projectID, company.getDevelopersDisplayName()));    	
	    	relatedObjectCollection.add(new RelatedObject(16,company.getSeniordevelopersDisplayName(),Integer.toString(projectSummary.getTotalSeniorDevelopers()), projectID, company.getSeniordevelopersDisplayName()));
	    	
	    	relatedObjectCollection.add(new RelatedObject(17,company.getEnvironmentsDisplayName(),Integer.toString(projectSummary.getTotalEnvironments()), projectID, company.getEnvironmentsDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(18,company.getRequirementsDisplayName(),Integer.toString(projectSummary.getTotalRequirements()), projectID, company.getRequirementsDisplayName()));
	    	
	    	relatedObjectCollection.add(new RelatedObject(19,company.getDefectsDisplayName()+"-Total",Integer.toString(projectSummary.getTotalDefects()), projectID, company.getDefectsDisplayName()));
	    	relatedObjectCollection.add(new RelatedObject(20,company.getDefectsDisplayName()+"-Sev 1",Integer.toString(projectSummary.getTotalCurrentSev1s()), projectID,"sev1"));
	    	relatedObjectCollection.add(new RelatedObject(21,company.getDefectsDisplayName()+"-Sev 2",Integer.toString(projectSummary.getTotalCurrentSev2s()), projectID,"sev2"));
	    	relatedObjectCollection.add(new RelatedObject(22,company.getDefectsDisplayName()+"-Sev 3",Integer.toString(projectSummary.getTotalCurrentSev3s()), projectID,"sev3"));
	    	relatedObjectCollection.add(new RelatedObject(23,company.getDefectsDisplayName()+"-Sev 4",Integer.toString(projectSummary.getTotalCurrentSev4s()), projectID,"sev4"));
	        			
			relatedObjectList.setRelatedObjects(relatedObjectCollection);
			return relatedObjectList;
			
		}catch(NoResultException e)
		{
			relatedObjectCollection.add(new RelatedObject(1,"Select a Row to View Details","", projectID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectCollection);
			return relatedObjectList;
		}    	
    } 
    
    // Project summary
    @RequestMapping(value = "/summary/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProjectSummary getCompanySummaryAt(@PathVariable("index") long projectID) {
    	//return projectService.getProjectSummary(projectID);   
    	return null;
    } 
    /* @RequestMapping(value = {"jsonprojectTEST"}, method = GET)
     public String jsonprojectTEST(@RequestParam(required = false)long companyID, Model model) {
     	model.addAttribute("company", companyService.getCompany(companyID));
     	return "jsonprojectTEST";  
   }  
     // All Projects For a company
     @RequestMapping(value = "/summaryList/{companyID}/cycle/{cycleID}", method = RequestMethod.GET)
     @ResponseStatus(HttpStatus.OK)
     public @ResponseBody ProjectSummaryList returnProjectsForCycle(@PathVariable("companyID") long companyID) {
     	return companyService.getAllProjectSummaryForCompany(companyID);     	   	
     }  */
   
      
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }
}
