package ie.cit.cloud.testcenter.controller.json;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Collection;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.model.summary.RelatedObject;
import ie.cit.cloud.testcenter.model.summary.RelatedObjectList;
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
    
    // Projects
    @RequestMapping(value = "/summary/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProjectSummary getCompanySummaryAt(@PathVariable("index") long projectID) {
    	return projectService.getProjectSummary(projectID);   
    } 
    // All Projects For a company
    @RequestMapping(value = "/summaryList/{companyID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProjectSummaryList returnProjectsForCompany(
    		@PathVariable long companyID,
    		@RequestParam(required = false) String cycleID_string,
    		@RequestParam(required = false) String testplanID,// +1 projects
    		@RequestParam(required = false) String testcaseID,// +1 projects    		
    		@RequestParam(required = false) String userID, // +1 projects
    		@RequestParam(required = false) String environmentID,
    		@RequestParam(required = false) String requirementID,
    		@RequestParam(required = false) String defectID,    		
    		@RequestParam(required = false) String testrunID
    		) 
    {
    	return projectService.getsummaryList(companyID,cycleID_string,testplanID,userID,environmentID,
    			requirementID,defectID,testrunID);    	
    	    	   	
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
    // Columns for project CHANGE companyID TO UserID
    @RequestMapping(value = "/projectColsAndNames/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ColModelAndNames testArray(@PathVariable("index") long companyID) {		
    	return projectService.getProjectColumnModelAndNames(companyID);    	
    } 
    
    // Project Related Items
    @RequestMapping(value = "/projectRelatedObjects/{projectID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RelatedObjectList getProjectRelatedObjects(@PathVariable("projectID") long projectID) {
    	ProjectSummary projectSummary = projectService.getProjectSummary(projectID); 
    	Company company = companyService.getCompany(projectSummary.getCompanyID());
    	Collection<RelatedObject> relatedObjectList =  new ArrayList<RelatedObject>();	
    	relatedObjectList.add(new RelatedObject(1,"Parent "+ company.getProjectDisplayName(),projectSummary.getParentProjectName()));
    	relatedObjectList.add(new RelatedObject(2,"Child "+ company.getProjectsDisplayName(),projectSummary.getChildProjects()));
    	relatedObjectList.add(new RelatedObject(3,company.getCyclesDisplayName(),projectSummary.getCycles()));
    	relatedObjectList.add(new RelatedObject(4,company.getTestrunsDisplayName(),projectSummary.getTestRuns()));
    	relatedObjectList.add(new RelatedObject(5,company.getTestcasesDisplayName(),projectSummary.getTestcases()));
    	relatedObjectList.add(new RelatedObject(6,company.getTestplansDisplayName(),projectSummary.getTestplans()));
    	relatedObjectList.add(new RelatedObject(7,company.getTestersDisplayName(),projectSummary.getTesters()));
    	relatedObjectList.add(new RelatedObject(8,company.getSeniorTestersDisplayName(),projectSummary.getSeniorTesters()));
    	relatedObjectList.add(new RelatedObject(9,company.getDevelopersDisplayName(),projectSummary.getDevelopers()));    	
    	relatedObjectList.add(new RelatedObject(10,company.getSeniordevelopersDisplayName(),projectSummary.getSeniorDevelopers()));
    	relatedObjectList.add(new RelatedObject(11,company.getEnvironmentsDisplayName(),projectSummary.getEnvironments()));
    	relatedObjectList.add(new RelatedObject(12,company.getRequirementsDisplayName(),projectSummary.getRequirements()));
    	relatedObjectList.add(new RelatedObject(13,company.getDefectsDisplayName()+"-Total",Long.toString(projectSummary.getTotalDefects())));
    	relatedObjectList.add(new RelatedObject(14,company.getDefectsDisplayName()+"-Sev 1",Long.toString(projectSummary.getCurrentSev1s())));
    	relatedObjectList.add(new RelatedObject(15,company.getDefectsDisplayName()+"-Sev 2",Long.toString(projectSummary.getCurrentSev2s())));
    	relatedObjectList.add(new RelatedObject(16,company.getDefectsDisplayName()+"-Sev 3",Long.toString(projectSummary.getCurrentSev3s())));
    	relatedObjectList.add(new RelatedObject(17,company.getDefectsDisplayName()+"-Sev 4",Long.toString(projectSummary.getCurrentSev4s())));
        
    	RelatedObjectList relatedObjectList2 = new RelatedObjectList();
    	relatedObjectList2.setRelatedObjects(relatedObjectList);
    	return relatedObjectList2;
    } 
    // Projects
    @RequestMapping(value = "/project/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Project getProject(@PathVariable("index") long projectID) {
    	return projectService.getProject(projectID);
    }
      
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }
}
