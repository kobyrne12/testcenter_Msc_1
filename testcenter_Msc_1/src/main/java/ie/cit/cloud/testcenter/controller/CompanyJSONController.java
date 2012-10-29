package ie.cit.cloud.testcenter.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import ie.cit.cloud.testcenter.display.ProjectsColMolsAndNames;
import ie.cit.cloud.testcenter.display.ProjectsDisplay;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("company")
public class CompanyJSONController { 
    @Autowired
    private CompanyService companyService;  
    @Autowired
    private ProjectService projectService;  
   
    // Companies
    @RequestMapping(value = "{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Company getCompanyAt(@PathVariable("index") Long companyID) {
    	return companyService.getCompany(companyID);    	
    }     
  
    // All Projects For a company ******** DELETE
    @RequestMapping(value = "/summaryText/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String returnAllProjectsForCompany(@PathVariable("index") Long companyID) {
    	String Test = "{\"projects\":[";
        Test = Test + "{\"environments\":55,\"creationDate\":\"2012/Oct/11 10:55:20\",\"lastModifiedDate\":\"2012/Oct/10 12:39:20\",\"testRuns\":1585,\"projectID\":6,\"projectName\":\"IBM 1\",\"regressionRequiredPercent\":70,\"newFeatureRequiredPercent\":94,\"companyID\":3,\"cycles\":0,\"lastModifiedBy\":\"Ken\",\"createdBy\":\"Kenneth\",\"childProjects\":2,\"regressionCurrentPercent\":96,\"allowedSev1s\":10,\"currentSev1s\":20,\"allowedSev2s\":25,\"currentSev2s\":60,\"allowedSev3s\":50,\"currentSev3s\":100,\"allowedSev4s\":100,\"currentSev4s\":200,\"testcases\":1900,\"testplans\":22,\"testers\":10,\"seniorTesters\":2,\"developers\":5,\"seniorDevelopers\":2,\"parentProjectName\":\"Parent Name\",\"newFeatureCurrentPercent\":80},{\"environments\":10,\"creationDate\":\"2012/Oct/11 10:55:20\",\"lastModifiedDate\":\"2012/Oct/10 12:39:20\",\"testRuns\":1585,\"projectID\":7,\"projectName\":\"IBM 2\",\"regressionRequiredPercent\":70,\"newFeatureRequiredPercent\":94,\"companyID\":3,\"cycles\":0,\"lastModifiedBy\":\"Ken\",\"createdBy\":\"Kenneth\",\"childProjects\":2,\"regressionCurrentPercent\":96,\"allowedSev1s\":10,\"currentSev1s\":20,\"allowedSev2s\":25,\"currentSev2s\":60,\"allowedSev3s\":50,\"currentSev3s\":100,\"allowedSev4s\":100,\"currentSev4s\":200,\"testcases\":1900,\"testplans\":22,\"testers\":10,\"seniorTesters\":2,\"developers\":5,\"seniorDevelopers\":2,\"parentProjectName\":\"Parent Name\",\"newFeatureCurrentPercent\":80},{\"environments\":10,\"creationDate\":\"2012/Oct/11 10:55:20\",\"lastModifiedDate\":\"2012/Oct/10 12:39:20\",\"testRuns\":1585,\"projectID\":8,\"projectName\":\"IBM 3\",\"regressionRequiredPercent\":70,\"newFeatureRequiredPercent\":94,\"companyID\":3,\"cycles\":0,\"lastModifiedBy\":\"Ken\",\"createdBy\":\"Kenneth\",\"childProjects\":2,\"regressionCurrentPercent\":96,\"allowedSev1s\":10,\"currentSev1s\":20,\"allowedSev2s\":25,\"currentSev2s\":60,\"allowedSev3s\":50,\"currentSev3s\":100,\"allowedSev4s\":100,\"currentSev4s\":200,\"testcases\":1900,\"testplans\":22,\"testers\":10,\"seniorTesters\":2,\"developers\":5,\"seniorDevelopers\":2,\"parentProjectName\":\"Parent Name\",\"newFeatureCurrentPercent\":80},{\"environments\":10,\"creationDate\":\"2012/Oct/11 10:55:20\",\"lastModifiedDate\":\"2012/Oct/10 12:39:20\",\"testRuns\":1585,\"projectID\":9,\"projectName\":\"IBM 4\",\"regressionRequiredPercent\":70,\"newFeatureRequiredPercent\":94,\"companyID\":3,\"cycles\":0,\"lastModifiedBy\":\"Ken\",\"createdBy\":\"Kenneth\",\"childProjects\":2,\"regressionCurrentPercent\":96,\"allowedSev1s\":10,\"currentSev1s\":20,\"allowedSev2s\":25,\"currentSev2s\":60,\"allowedSev3s\":50,\"currentSev3s\":100,\"allowedSev4s\":100,\"currentSev4s\":200,\"testcases\":1900,\"testplans\":22,\"testers\":10,\"seniorTesters\":2,\"developers\":5,\"seniorDevelopers\":2,\"parentProjectName\":\"Parent Name\",\"newFeatureCurrentPercent\":80},{\"environments\":10,\"creationDate\":\"2012/Oct/11 10:55:20\",\"lastModifiedDate\":\"2012/Oct/10 12:39:20\",\"testRuns\":1585,\"projectID\":10,\"projectName\":\"IBM 5\",\"regressionRequiredPercent\":70,\"newFeatureRequiredPercent\":94,\"companyID\":3,\"cycles\":0,\"lastModifiedBy\":\"Ken\",\"createdBy\":\"Kenneth\",\"childProjects\":2,\"regressionCurrentPercent\":96,\"allowedSev1s\":10,\"currentSev1s\":20,\"allowedSev2s\":25,\"currentSev2s\":60,\"allowedSev3s\":50,\"currentSev3s\":100,\"allowedSev4s\":100,\"currentSev4s\":200,\"testcases\":1900,\"testplans\":22,\"testers\":10,\"seniorTesters\":2,\"developers\":5,\"seniorDevelopers\":2,\"parentProjectName\":\"Parent Name\",\"newFeatureCurrentPercent\":80},{\"environments\":10,\"creationDate\":\"2012/Oct/11 10:55:20\",\"lastModifiedDate\":\"2012/Oct/10 12:39:20\",\"testRuns\":1585,\"projectID\":11,\"projectName\":\"IBM 6\",\"regressionRequiredPercent\":70,\"newFeatureRequiredPercent\":94,\"companyID\":3,\"cycles\":0,\"lastModifiedBy\":\"Ken\",\"createdBy\":\"Kenneth\",\"childProjects\":2,\"regressionCurrentPercent\":96,\"allowedSev1s\":10,\"currentSev1s\":20,\"allowedSev2s\":25,\"currentSev2s\":60,\"allowedSev3s\":50,\"currentSev3s\":100,\"allowedSev4s\":100,\"currentSev4s\":200,\"testcases\":1900,\"testplans\":22,\"testers\":10,\"seniorTesters\":2,\"developers\":5,\"seniorDevelopers\":2,\"parentProjectName\":\"Parent Name\",\"newFeatureCurrentPercent\":80}";
        Test = Test + "]}";
        //return companyService.getAllProjectSummaryForCompany(companyID); 
    	return Test;
    } 
    
    // All Projects For a company
    @RequestMapping(value = "/summary/{companyID}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProjectSummaryList returnAllJSONProjectsForCompany(@PathVariable("companyID") long companyID) {
    	return companyService.getAllProjectSummaryForCompany(companyID); 
    	   	
    } 
    
    // TEST
    @RequestMapping(value = "/projectColsAndNames/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProjectsColMolsAndNames testArray(@PathVariable("index") long companyID) {		
    	return projectService.getProjectColumnModelAndNames(companyID);    	
    } 
    // Companies
    @RequestMapping(value = "/summary/cycle/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ProjectSummaryList returnAllProjectsForCycle(@PathVariable("index") long cycleID) {
    	return companyService.getAllProjectSummaryForCycle(cycleID);    	
    } 
    
    
   // All Project for that company
    @RequestMapping(value = "{index}/projects", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Collection<Project> getAllProjectsForCompanyAt(@PathVariable("index") Long companyID) {
    	 // Retrieve all users from the service
    	//Company company = companyService.getCompany(companyID);
    	//Collection<JSONProject> projects = companyService.getAll();    	
    	//Collection<Project> allProjects = company.getProjects();    	
    	//Collection<JSONProject> CustomProjects ;
    	return projectService.getAllProjectsByCompanyID(companyID);
    	//return allProjects;
    	
//    	for (Project p : allProjects)
//    	{
//    		System.out.println("****----*** : "+ p.getProjectName());
//    		//CustomProjects.a
//    		
//    	}
//    	   
//    	////////////////////
//    	 // Retrieve all users from the service
//        //List<user> users = userService.getAll();
//         
//        // Initialize our custom user response wrapper
//        CustomProjectResponse response = new CustomProjectResponse();
//         
//        // Assign the result from the service to this response
//        response.setProjects(allProjects);
//
//        // Assign the total number of records found. This is used for paging
//        response.setRecords( String.valueOf(allProjects.size()) );
//         
//        // Since our service is just a simple service for teaching purposes
//        // We didn't really do any paging. But normally your DAOs or your persistence layer should support this
//        // Assign a dummy page
//        response.setPage( "1" );
//         
//        // Same. Assign a dummy total pages
//        response.setTotal( "10" );
//         
//        // Return the response
//        // Spring will automatically convert our CustomUserResponse as JSON object. 
//        // This is triggered by the @ResponseBody annotation. 
//        // It knows this because the JqGrid has set the headers to accept JSON format when it made a request
//        // Spring by default uses Jackson to convert the object to JSON
//        return response;
    	//////////////////////////////////
    	
    } 
    
   
  
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }
}
