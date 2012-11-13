/**
 * 
 */
package ie.cit.cloud.testcenter.service.project;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ProjectsColMolsAndNames;
import ie.cit.cloud.testcenter.display.ProjectsDisplay;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.respository.project.ProjectRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
    @Qualifier("hibernateProjectRespository")
    ProjectRepository projectRepo;    
	
	@Autowired
	CompanyService companyService;
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<Project> getAllProjects() {
	return projectRepo.findAll();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Project getProject(long projectID) {
	return projectRepo.findById(projectID);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Project getProjectByName(String projectName) {
    	return projectRepo.findProjectByName(projectName);
    }
    
   // @Secured("ROLE_ADMIN")
    @Transactional(rollbackFor=ConstraintViolationException.class)   
    public void addNewProject(Project project) {
    	projectRepo.create(project);	
    }
   
   // @Secured("ROLE_ADMIN")
    public void update(Project project) {
	projectRepo.update(project);
    }  
    
  //  @Secured("ROLE_ADMIN")
    public void remove(long projectID) {
	projectRepo.delete(projectRepo.get(projectID));
    }
       
  //  @Secured("ROLE_ADMIN")    
    public void updateProjectWithId(long projectID, Project project) {
    	Project oldProject = projectRepo.findById(projectID);
    	oldProject.setProjectName(project.getProjectName());
    	oldProject.setParentID(project.getParentID());
    	oldProject.setRegressionRequiredPercent(project.getRegressionRequiredPercent());
    	oldProject.setNewFeatureRequiredPercent(project.getNewFeatureRequiredPercent());
    	oldProject.setAllowedSev1(project.getAllowedSev1());
    	oldProject.setAllowedSev2(project.getAllowedSev2());
    	oldProject.setAllowedSev3(project.getAllowedSev3());
    	oldProject.setAllowedSev4(project.getAllowedSev4());   		
    	projectRepo.update(oldProject);
    }
  //  @Secured("ROLE_ADMIN")
    public void updateProjectNameWithId(long projectID, Project project, String projectName) {
    	Project oldProject = projectRepo.findById(projectID);
    	oldProject.setProjectName(project.getProjectName());
    	oldProject.setParentID(project.getParentID());
    	oldProject.setRegressionRequiredPercent(project.getRegressionRequiredPercent());
    	oldProject.setNewFeatureRequiredPercent(project.getNewFeatureRequiredPercent());
    	oldProject.setAllowedSev1(project.getAllowedSev1());
    	oldProject.setAllowedSev2(project.getAllowedSev2());
    	oldProject.setAllowedSev3(project.getAllowedSev3());
    	oldProject.setAllowedSev4(project.getAllowedSev4());  	
    	projectRepo.update(oldProject);
    }

	public boolean updateProject(long projectID, Project project) {
		// TODO Auto-generated method stub
		return false;
	}
		
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Project> getAllProjectsByCompanyID(long companyID) {
		return projectRepo.findAllProjectsByCompanyID(companyID);
	}
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public ProjectSummary getProjectSummary(long projectID) {
		int totalDefects = 0;
		Project project = projectRepo.findById(projectID);
		ProjectSummary projectSummary = new ProjectSummary();
		
		projectSummary.setProjectID(project.getProjectID());
		projectSummary.setProjectName(project.getProjectName());	
					
		// Child Projects
		int numbOfChildProjs = 55;
		if(numbOfChildProjs == 1)
		{
			projectSummary.setChildProjects("GET ChildProjs NAME");		
		}
		else
		{			
			projectSummary.setChildProjects(Integer.toString(numbOfChildProjs));
		}
		
		projectSummary.setNewFeatureRequiredPercent(project.getNewFeatureRequiredPercent());
		int currentNewFeaturePercent = 1 + (int)(Math.random() * ((100 - 1) + 1)); // just a random number until all entities are populated 
		projectSummary.setNewFeatureCurrentPercent(currentNewFeaturePercent);
		projectSummary.setRegressionCurrentPercent(project.getRegressionRequiredPercent());
		int currentRegressionPercent = 1 + (int)(Math.random() * ((100 - 1) + 1)); // just a random number until all entities are populated 
		projectSummary.setRegressionRequiredPercent(currentRegressionPercent);
		
		projectSummary.setAllowedSev1s(project.getAllowedSev1());
		projectSummary.setAllowedSev2s(project.getAllowedSev2());
		projectSummary.setAllowedSev3s(project.getAllowedSev3());
		projectSummary.setAllowedSev4s(project.getAllowedSev4());
		
		int currentSev1 = 1 + (int)(Math.random() * ((50 - 1) + 1)); // just a random number until all entities are populated 
		projectSummary.setCurrentSev1s(currentSev1);
		int currentSev2 = 1 + (int)(Math.random() * ((70 - 1) + 1)); // just a random number until all entities are populated 
		projectSummary.setCurrentSev2s(currentSev2);
		int currentSev3 = 1 + (int)(Math.random() * ((100 - 1) + 1)); // just a random number until all entities are populated 
		projectSummary.setCurrentSev3s(currentSev3);
		int currentSev4 = 1 + (int)(Math.random() * ((200 - 1) + 1)); // just a random number until all entities are populated 
		projectSummary.setCurrentSev4s(currentSev4);
		
		totalDefects = currentSev1 + currentSev2 + currentSev3 + currentSev4;
		
		projectSummary.setTotalDefects(totalDefects);
		projectSummary.setCompanyID(project.getCompanyID());	
		
		// Cycles
		if(project.getCycles().size() == 0)
		{
			projectSummary.setCycles("None");
		}
		else if(project.getCycles().size() == 1)
		{
			projectSummary.setCycles("GET CYCLE NAME");
		}
		else
		{			
			projectSummary.setCycles(Integer.toString(project.getCycles().size()));
		}
		// env
		int numOfEnvs = 10;
		if(numOfEnvs == 1)
		{
			projectSummary.setEnvironments("GET Env NAME");	
		}
		else
		{			
			projectSummary.setEnvironments(Integer.toString(numOfEnvs));
		}
		// requirements
		int numbOfReqs = 10;
		if(numbOfReqs == 1)
		{
			projectSummary.setRequirements("GET REQS NAME");	
		}
		else
		{			
			projectSummary.setRequirements(Integer.toString(numbOfReqs));
		}
		// TestRun
		int numOfTestRuns = 1585;
		if(numOfTestRuns == 1)
		{
			projectSummary.setTestRuns("GET TEST RUN NAME");	
		}
		else
		{			
			projectSummary.setTestRuns(Integer.toString(numOfTestRuns));
		}
		// Test Case
		int numOfTestCases = 1900;
		if(numOfTestCases == 1)
		{
			projectSummary.setTestcases("GET TEST CASE NAME");	
		}
		else
		{			
			projectSummary.setTestcases(Integer.toString(numOfTestCases));
		}
		// Test Plan
		int numOfTestPlans = 22;
		if(numOfTestPlans == 1)
		{
			projectSummary.setTestplans("GET TEST Plan NAME");	
		}
		else
		{			
			projectSummary.setTestplans(Integer.toString(numOfTestPlans));
		}
		// Testers
		int numOfTesters = 22;
		if(numOfTesters == 1)
		{
			projectSummary.setTesters("GET TESTERS NAME");	
		}
		else
		{			
			projectSummary.setTesters(Integer.toString(numOfTesters));
		}
		// SNR Testers
		int numOfSnrTesters = 22;
		if(numOfSnrTesters == 1)
		{
			projectSummary.setSeniorTesters("GET SNR TESTERS NAME");	
		}
		else
		{			
			projectSummary.setSeniorTesters(Integer.toString(numOfSnrTesters));
		}
		// Developers
		int numOfDevs = 22;
		if(numOfDevs == 1)
		{
			projectSummary.setDevelopers("GET DEV NAME");	
		}
		else
		{			
			projectSummary.setDevelopers(Integer.toString(numOfDevs));
		}
		// SNR Developers
		int numOfSnrDevs = 22;
		if(numOfSnrDevs == 1)
		{
			projectSummary.setSeniorDevelopers("GET SNR DEV NAME");	
		}
		else
		{			
			projectSummary.setSeniorDevelopers(Integer.toString(numOfSnrDevs));
		}
		
		projectSummary.setLastModifiedBy(project.getLastModifiedBy());
		projectSummary.setLastModifiedDate(project.getLastModifiedDate());
		projectSummary.setCreatedBy(project.getCreatedBy());
		projectSummary.setCreationDate(project.getCreationDate());
		
		projectSummary.setParentProjectName("NOT DONE");	
		
		return projectSummary;
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<ProjectSummary> getAllProjectSummaryForCompany(long companyID) {
		// Current List of Projects
		Collection<Project> projects = projectRepo.findAllProjectsByCompanyID(companyID);		
		// New Project Summary Collection 
		Collection<ProjectSummary> projectSummaryCollection = new ArrayList<ProjectSummary>();
		// Project iterator
		Iterator<Project> projectsItr = projects.iterator();
		// loop through all projects and add a summary of the project to the summary list
		while (projectsItr.hasNext()) {				
			Project project = projectsItr.next();						
			ProjectSummary projectSummary = getProjectSummary(project.getProjectID());	
			projectSummaryCollection.add(projectSummary);
		}
		return projectSummaryCollection;
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<ProjectSummary> getAllProjectSummaryForCycle(long cycleID) {
		//long projectSummaryID = 0;
		Collection<Project> projects = projectRepo.findAllProjectsByCycleID(cycleID);		
		Collection<ProjectSummary> projectSummaryCollection = new ArrayList<ProjectSummary>();	
		Iterator<Project> projectsItr = projects.iterator();
		while (projectsItr.hasNext()) {	
				long projectID = 0;
				long projectSummaryID = 0;
				Project project = projectsItr.next();					
				projectID = project.getProjectID();
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Project -- " + projectID);				
				ProjectSummary projectSummary = getProjectSummary(projectID);
				projectSummaryID = projectSummary.getProjectID();
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Project Summary -- " + projectSummaryID);
				
				projectSummaryCollection.add(projectSummary);
		}
		return projectSummaryCollection;
	}
	

 private Collection<ProjectsDisplay> getProjectsColumnModel(long companyID)
 {
	 // Constructor in order
	 // name;index;hidden;width;align;
	 // sortable;resizable;search;sorttype;jsonmap;key;
	 
	// Company company = companyService.getCompany(companyID);
	 Collection<ProjectsDisplay> columnModelCollection =  new ArrayList<ProjectsDisplay>();		 
	 columnModelCollection.add(new ProjectsDisplay("projectID",10));	
	 columnModelCollection.add(new ProjectsDisplay("projectName",40));
	 columnModelCollection.add(new ProjectsDisplay("overAllState","setOverallBarChart","unSetBarChart", 80));		
	 columnModelCollection.add(new ProjectsDisplay("regressionState","setRegressionBarChart","unSetBarChart", 60));	
	 columnModelCollection.add(new ProjectsDisplay("newFeatureState","setNewFeatureBarChart","unSetBarChart",60));	
	 columnModelCollection.add(new ProjectsDisplay("parentProjectName",25));
	 columnModelCollection.add(new ProjectsDisplay("childProjects",15));
	 columnModelCollection.add(new ProjectsDisplay("regressionRequiredPercent","percentFmatter","unformatPercent",15));
	 columnModelCollection.add(new ProjectsDisplay("regressionCurrentPercent","percentFmatter","unformatPercent",15));
	 columnModelCollection.add(new ProjectsDisplay("newFeatureRequiredPercent","percentFmatter","unformatPercent",15));
	 columnModelCollection.add(new ProjectsDisplay("newFeatureCurrentPercent","percentFmatter","unformatPercent",15));
	 columnModelCollection.add(new ProjectsDisplay("allowedSev1s",true));
	 columnModelCollection.add(new ProjectsDisplay("currentSev1s",true));
	 columnModelCollection.add(new ProjectsDisplay("allowedSev2s",true));
	 columnModelCollection.add(new ProjectsDisplay("currentSev2s",true));
	 columnModelCollection.add(new ProjectsDisplay("allowedSev3s",true));
	 columnModelCollection.add(new ProjectsDisplay("currentSev3s",true));	 
	 columnModelCollection.add(new ProjectsDisplay("allowedSev4s",true));
	 columnModelCollection.add(new ProjectsDisplay("currentSev4s",true));
	 columnModelCollection.add(new ProjectsDisplay("companyID",true));
	 columnModelCollection.add(new ProjectsDisplay("cycles",true));
	 columnModelCollection.add(new ProjectsDisplay("environments",true));
	 columnModelCollection.add(new ProjectsDisplay("testRuns",true));
	 columnModelCollection.add(new ProjectsDisplay("testcases",true));
	 columnModelCollection.add(new ProjectsDisplay("testplans",true));	 
	 columnModelCollection.add(new ProjectsDisplay("testers",true));
	 columnModelCollection.add(new ProjectsDisplay("seniorTesters",true));	 
	 columnModelCollection.add(new ProjectsDisplay("developers",true));
	 columnModelCollection.add(new ProjectsDisplay("seniorDevelopers",true));
	 columnModelCollection.add(new ProjectsDisplay("lastModifiedDate",true));
	 columnModelCollection.add(new ProjectsDisplay("lastModifiedBy",true));	 
	 columnModelCollection.add(new ProjectsDisplay("createdBy",true));
	 columnModelCollection.add(new ProjectsDisplay("creationDate",true));	
	// if customValue1 set then 
	//columnModelCollection.add(getColumnModel("customColumn1"));	
	// if customValue2 set then 
	//columnModelCollection.add(getColumnModel("customColumn2"));	
	// if customValue3 set then 
	//columnModelCollection.add(getColumnModel("customColumn3"));
	// if customValue4 set then 
	//columnModelCollection.add(getColumnModel("customColumn4"));	
	// if customValue5 set then 
	//columnModelCollection.add(getColumnModel("customColumn5"));	
	 return columnModelCollection;
 }
 
 private Collection<String> getProjectsColumnNames(long companyID)
 {
	 Company company = companyService.getCompany(companyID);
	 Collection<String> colNames = new ArrayList<String>();
	 
	 if(company.getRegressionDisplayName()!= null) { company.setRegressionDisplayName("Regression");};
	 if(company.getNewFeatureDisplayName()!= null) { company.setNewFeatureDisplayName("New Feature");};
	 
	 colNames.add("ID");
	 colNames.add(company.getProjectDisplayName()+ " Name");
	 colNames.add("Overall");
	 colNames.add(company.getRegressionDisplayName());
	 colNames.add(company.getNewFeatureDisplayName());
	 colNames.add("Parent");
	 colNames.add("Child<BR/>"+ company.getProjectsDisplayName());
	 colNames.add("Min<BR/>" + company.getRegressionDisplayName());
	 colNames.add("Current<BR/>" + company.getRegressionDisplayName());
	 colNames.add("Min<BR/>" + company.getNewFeatureDisplayName());
	 colNames.add("Current<BR/>" + company.getNewFeatureDisplayName());
	 colNames.add("Max <BR/> Sev 1s");
	 colNames.add("Current <BR/> Sev 1s");
	 colNames.add("Max <BR/> Sev 2s");
	 colNames.add("Current <BR/> Sev 2s");
	 colNames.add("Max <BR/> Sev 3s");
	 colNames.add("Current <BR/> Sev 3s");
	 colNames.add("Max <BR/> Sev 4s");
	 colNames.add("Current <BR/> Sev 4s");
	 colNames.add("Company ID");
	 colNames.add(company.getCyclesDisplayName());
	 colNames.add(company.getEnvironmentsDisplayName());
	 colNames.add(company.getTestrunsDisplayName());
	 colNames.add(company.getTestcasesDisplayName());
	 colNames.add(company.getTestplansDisplayName());
	 colNames.add(company.getTestersDisplayName());
	 colNames.add(company.getSeniorTestersDisplayName());
	 colNames.add(company.getDevelopersDisplayName());
	 colNames.add(company.getSeniordevelopersDisplayName());
	 colNames.add("LastModifiedDate");
	 colNames.add("LastModifiedBy");
	 colNames.add("CreatedBy");
	 colNames.add("CreationDate");
	 // if customValue1 set then 
	 //colNames.add("CreationDate");
	 // if customValue2 set then 
	 //colNames.add("CreationDate");	
	 // if customValue3 set then 
	 //colNames.add("CreationDate");
	 // if customValue4 set then 
	 //colNames.add("CreationDate");	
	 // if customValue5 set then 
	 //colNames.add("CreationDate");	
	 return colNames;
 }
	
 public ProjectsColMolsAndNames getProjectColumnModelAndNames(long companyID)
 {
 	ProjectsColMolsAndNames colModelAndName = new ProjectsColMolsAndNames();
	colModelAndName.setColName(getProjectsColumnNames(companyID));    	
	colModelAndName.setColModel(getProjectsColumnModel(companyID));
	return colModelAndName;
 }

public ProjectSummaryList getsummaryList(long companyID, String cycleID,
		String testplanID, String userID, String environmentID,
		String requirementID, String defectID, String testrunID) {
	
	Collection<Project> projects = new ArrayList<Project>();
	
	
	if (cycleID != null)
	{
		long cycleID_long = Long.valueOf(cycleID).longValue();
		// get a collection of projects for the cycle and add them to the current collection
		//projectRepo.findAllProjectsByCompanyID(companyID);	
		projects.addAll(projects);
		
	}
	if (testplanID != null){long testplanID_long = Long.valueOf(testplanID).longValue();	}
	if (environmentID != null){long environmentID_long = Long.valueOf(environmentID).longValue();	}
	if (requirementID != null){long requirementID_long = Long.valueOf(requirementID).longValue();	}
	if (defectID != null){long defectID_long = Long.valueOf(defectID).longValue();	}
	if (testrunID != null){long testrunID_long = Long.valueOf(testrunID).longValue();	}
	if (userID != null){long userID_long = Long.valueOf(userID).longValue();	}
	return null;
}

}