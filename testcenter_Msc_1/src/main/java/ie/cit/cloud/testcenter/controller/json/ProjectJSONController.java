package ie.cit.cloud.testcenter.controller.json;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import ie.cit.cloud.testcenter.model.JqgridFilter.Rule;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
    		@RequestParam(required = false) String userID,
    		@RequestParam(required = false) String level,
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
    		
        	ProjectSummaryList projectSummaryList = projectService.getGridProjects(companyID, projectID,
        			cycleID, testplanID, testcaseID, testrunID, defectID, requirementID, environmentID, userID,level);
    		
        	Set<ProjectSummary> oldProjectSummarySet = projectSummaryList.getProjects();
    		Set<ProjectSummary> newProjectSummarySet = new LinkedHashSet<ProjectSummary>();
    		
    		for(ProjectSummary oldProjectSummary : oldProjectSummarySet)
			{
    			boolean projectNameFound = false; 
    			boolean projectIDFound = false; 
    			//System.out.println(" ^^^^^^ 3 : " + oldProjectSummary.getProjectName());
    			for(Rule rule : jqgridFilter.getRules())
        		{        			
    				//System.out.println(" ^^^^^^ 4 : " + rule.getField());
        			if(rule.getField().equalsIgnoreCase("projectName"))
        			{
        				//System.out.println(" ^^^^^^ 5 a : " +oldProjectSummary.getProjectName());
        				if(oldProjectSummary.getProjectName().toLowerCase().contains(rule.getData().toLowerCase()))
    					{
        					projectNameFound = true;      					
    					}
        			}
        			else
        			{
        				projectNameFound = true;   
        			}
        			if(rule.getField().equalsIgnoreCase("projectID"))
        			{
        				System.out.println(" ^^^^^^ 5 a : " +oldProjectSummary.getProjectID());
        				if(String.valueOf(oldProjectSummary.getProjectID()).toLowerCase().contains(rule.getData().toLowerCase()))
    					{        		
        					System.out.println(" ^^^^^^ 5 b : " +oldProjectSummary.getProjectID());
        					projectIDFound = true; 
    					}
        			}
        			else
        			{
        				System.out.println(" ^^^^^^ 5 c : " +oldProjectSummary.getProjectID());
        				projectIDFound = true; 
        			}
        		}
    			if(projectNameFound == true && projectIDFound == true)
    			{
    				newProjectSummarySet.add(oldProjectSummary);
    			}
			}

    		projectSummaryList.setProjects(newProjectSummarySet);    		
    		return projectSummaryList;
    	}
    	else
    	{
    		return projectService.getGridProjects(companyID, projectID, cycleID, testplanID,
        			testcaseID, testrunID, defectID, requirementID, environmentID, userID, level);
    	}
    	
    }     
  
    // Columns for project CHANGE companyID TO UserID
    @RequestMapping(value = "/ColsAndNames/{index}", method = RequestMethod.GET)
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
    	Set<RelatedObject> relatedObjectSet =  new LinkedHashSet<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList();    	
		try{
			Project project = projectService.getProject(projectID); 
			ProjectSummary projectSummary = projectService.getProjectSummary(project.getCompanyID(), projectID, null);
			
	    	Company company = companyService.getCompany(projectSummary.getCompanyID());
	    	
	    	relatedObjectSet.add(new RelatedObject(1,"Parent "+ company.getProjectDisplayName(),projectSummary.getParentProjectName(), projectID, "Parent"+ company.getProjectDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(2,"Child "+ company.getProjectsDisplayName(),Integer.toString(projectSummary.getChildProjects()), projectID, "Child"+ company.getProjectsDisplayName().replace(" ","")));
	    	
	    	relatedObjectSet.add(new RelatedObject(3,company.getCyclesDisplayName(),Integer.toString(projectSummary.getTotalCycles()), projectID, company.getCyclesDisplayName().replace(" ","")));
	    	
	    	relatedObjectSet.add(new RelatedObject(4,company.getTestplansDisplayName(),Integer.toString(projectSummary.getTotalAllTestplans()), projectID,  company.getTestplansDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(5,company.getTestcasesDisplayName(),Integer.toString(projectSummary.getTotalAllTestcases()), projectID,  company.getTestcasesDisplayName().replace(" ","")));
	    
	    	relatedObjectSet.add(new RelatedObject(6,"All "+ company.getTestrunsDisplayName(),Integer.toString(projectSummary.getTotalAllTestruns()), projectID, "all"+company.getTestrunsDisplayName().replace(" ",""))); 
	    	relatedObjectSet.add(new RelatedObject(7,"Required "+ company.getTestrunsDisplayName(),Integer.toString(projectSummary.getTotalRequiredTestruns()), projectID, "required"+company.getTestrunsDisplayName().replace(" ",""))); 
	    	relatedObjectSet.add(new RelatedObject(8,"Optional "+ company.getTestrunsDisplayName(),Integer.toString(projectSummary.getTotalOptionalTestruns()), projectID, "optional"+company.getTestrunsDisplayName().replace(" ","")));
	    		    	
	    	relatedObjectSet.add(new RelatedObject(9,company.getEnvironmentsDisplayName(),Integer.toString(projectSummary.getTotalEnvironments()), projectID, company.getEnvironmentsDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(10,company.getRequirementsDisplayName(),Integer.toString(projectSummary.getTotalRequirements()), projectID, company.getRequirementsDisplayName().replace(" ","")));
	    	
	    	relatedObjectSet.add(new RelatedObject(11,company.getDefectsDisplayName()+"-Total",Integer.toString(projectSummary.getTotalDefects()), projectID, company.getDefectsDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(12,company.getDefectsDisplayName()+"-Sev 1",Integer.toString(projectSummary.getTotalCurrentSev1s()), projectID,"sev1"));
	    	relatedObjectSet.add(new RelatedObject(13,company.getDefectsDisplayName()+"-Sev 2",Integer.toString(projectSummary.getTotalCurrentSev2s()), projectID,"sev2"));
	    	relatedObjectSet.add(new RelatedObject(14,company.getDefectsDisplayName()+"-Sev 3",Integer.toString(projectSummary.getTotalCurrentSev3s()), projectID,"sev3"));
	    	relatedObjectSet.add(new RelatedObject(15,company.getDefectsDisplayName()+"-Sev 4",Integer.toString(projectSummary.getTotalCurrentSev4s()), projectID,"sev4"));
	    	
	    	relatedObjectSet.add(new RelatedObject(16,company.getTestersDisplayName(),Integer.toString(projectSummary.getTotalTesters()), projectID, company.getTestersDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(17,company.getSeniorTestersDisplayName(),Integer.toString(projectSummary.getTotalSeniorTesters()), projectID, company.getSeniorTestersDisplayName().replace(" ","")));
	    	relatedObjectSet.add(new RelatedObject(18,company.getDevelopersDisplayName(),Integer.toString(projectSummary.getTotalDevelopers()), projectID, company.getDevelopersDisplayName().replace(" ",""))); 	
	    	relatedObjectSet.add(new RelatedObject(19,company.getSeniordevelopersDisplayName(),Integer.toString(projectSummary.getTotalSeniorDevelopers()), projectID, company.getSeniordevelopersDisplayName().replace(" ","")));
	    				
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
			
		}catch(NoResultException e)
		{
			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", projectID, null));  
			relatedObjectList.setRelatedObjects(relatedObjectSet);
			return relatedObjectList;
		}    	
    } 
    /**
	 * Handles request for create a new project 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewProjectAJAX(
			@RequestParam(value="companyID", required=true) long companyID,
			@RequestParam(value="projectName", required=true) String projectName,
			@RequestParam(value="regression", required=true) int regression,
			@RequestParam(value="newFeature", required=true) int newFeature,
			@RequestParam(value="minSev1", required=true) int minSev1,
			@RequestParam(value="minSev2", required=true) int minSev2,
			@RequestParam(value="minSev3", required=true) int minSev3,
			@RequestParam(value="minSev4", required=true) int minSev4,
			Model model) 
	{
		boolean alreadyExists = false;
		try
		{  
			Project project = projectService.getProjectByName(projectName);		
			if(project.getCompanyID() == companyID )
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
				projectService.addNewProject(new Project(companyID,projectName,0,regression,newFeature,minSev1,minSev2,minSev3,minSev4,GetDateNow(),getCurrentUser()));
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
			return projectName + " already Exists";	 
		}		
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deleteProject(@RequestParam(value="id", required=true) long projectID) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% DELETE projectID = " +projectID);
		projectService.remove(projectID);
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
    public String GetDateNow()
	{ 	 
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime()); 
		//System.out.println("*********** Now the currentDate is :=>  " + currentDate);
		System.out.println("*********** Now the dateNow is :=>  " + dateNow);
		return dateNow;
	}

	private String getCurrentUser() 
	{
		System.out.println("*********** Current User is :=>  " + SecurityContextHolder.getContext().getAuthentication().getName());
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
