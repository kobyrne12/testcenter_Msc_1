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
    ProjectRepository repo;    
	
	@Autowired
	CompanyService companyService;
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<Project> getAllProjects() {
	return repo.findAll();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Project getProject(long projectID) {
	return repo.findById(projectID);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Project getProjectByName(String projectName) {
    	return repo.findProjectByName(projectName);
    }
    
   // @Secured("ROLE_ADMIN")
    @Transactional(rollbackFor=ConstraintViolationException.class)   
    public void addNewProject(Project project) {
    	repo.create(project);	
    }
   
   // @Secured("ROLE_ADMIN")
    public void update(Project project) {
	repo.update(project);
    }  
    
  //  @Secured("ROLE_ADMIN")
    public void remove(long projectID) {
	repo.delete(repo.get(projectID));
    }
       
  //  @Secured("ROLE_ADMIN")    
    public void updateProjectWithId(long projectID, Project project) {
    	Project oldProject = repo.findById(projectID);
    	oldProject.setProjectName(project.getProjectName());
    	oldProject.setParentID(project.getParentID());
    	oldProject.setRegressionRequiredPercent(project.getRegressionRequiredPercent());
    	oldProject.setNewFeatureRequiredPercent(project.getNewFeatureRequiredPercent());
    	oldProject.setAllowedSev1(project.getAllowedSev1());
    	oldProject.setAllowedSev2(project.getAllowedSev2());
    	oldProject.setAllowedSev3(project.getAllowedSev3());
    	oldProject.setAllowedSev4(project.getAllowedSev4());   		
    	repo.update(oldProject);
    }
  //  @Secured("ROLE_ADMIN")
    public void updateProjectNameWithId(long projectID, Project project, String projectName) {
    	Project oldProject = repo.findById(projectID);
    	oldProject.setProjectName(project.getProjectName());
    	oldProject.setParentID(project.getParentID());
    	oldProject.setRegressionRequiredPercent(project.getRegressionRequiredPercent());
    	oldProject.setNewFeatureRequiredPercent(project.getNewFeatureRequiredPercent());
    	oldProject.setAllowedSev1(project.getAllowedSev1());
    	oldProject.setAllowedSev2(project.getAllowedSev2());
    	oldProject.setAllowedSev3(project.getAllowedSev3());
    	oldProject.setAllowedSev4(project.getAllowedSev4());  	
    	repo.update(oldProject);
    }

	public boolean updateProject(long projectID, Project project) {
		// TODO Auto-generated method stub
		return false;
	}
		
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Project> getAllProjectsByCompanyID(long companyID) {
		return repo.findAllProjectsByCompanyID(companyID);
	}
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public ProjectSummary getProjectSummary(long projectID) {
		int totalDefects = 0;
		Project project = repo.findById(projectID);
		ProjectSummary projectSummary = new ProjectSummary();
		
		projectSummary.setProjectID(project.getProjectID());
		projectSummary.setProjectName(project.getProjectName());	
		
			
		// requirements
		int numbOfChildProjs = 10;
		if(numbOfChildProjs == 1)
		{
			projectSummary.setChildProjects("GET ChildProjs NAME");		
		}
		else
		{			
			projectSummary.setChildProjects(Integer.toString(numbOfChildProjs));
		}
		projectSummary.setNewFeatureRequiredPercent(94);				
		projectSummary.setNewFeatureCurrentPercent(80);
		projectSummary.setRegressionCurrentPercent(96);
		projectSummary.setRegressionRequiredPercent(70);
		
		projectSummary.setAllowedSev1s(10);
		projectSummary.setAllowedSev2s(25);
		projectSummary.setAllowedSev3s(50);
		projectSummary.setAllowedSev4s(100);
		int currentSev1 = 20;
		projectSummary.setCurrentSev1s(20);
		int currentSev2 = 20;
		projectSummary.setCurrentSev2s(60);
		int currentSev3 = 20;
		projectSummary.setCurrentSev3s(100);
		int currentSev4 = 20;
		projectSummary.setCurrentSev4s(200);	
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
		
		projectSummary.setLastModifiedBy("Ken");
		projectSummary.setLastModifiedDate("2012/Oct/10 12:39:20");
		projectSummary.setCreatedBy("Kenneth");
		projectSummary.setCreationDate( "2012/Oct/11 10:55:20");
		
		projectSummary.setParentProjectName("Parent Name");	
		
		return projectSummary;
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<ProjectSummary> getAllProjectSummaryForCompany(long companyID) {
		// Current List of Projects
		Collection<Project> projects = repo.findAllProjectsByCompanyID(companyID);		
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
		Collection<Project> projects = repo.findAllProjectsByCycleID(cycleID);		
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
	 columnModelCollection.add(new ProjectsDisplay("overAllState","setOverallBarChart","unSetBarChart"));		
	 columnModelCollection.add(new ProjectsDisplay("regressionState","setRegressionBarChart","unSetBarChart"));	
	 columnModelCollection.add(new ProjectsDisplay("newFeatureState","setNewFeatureBarChart","unSetBarChart"));	
	 columnModelCollection.add(new ProjectsDisplay("parentProjectName"));
	 columnModelCollection.add(new ProjectsDisplay("childProjects"));
	 columnModelCollection.add(new ProjectsDisplay("regressionRequiredPercent","percentFmatter","unformatPercent"));
	 columnModelCollection.add(new ProjectsDisplay("regressionCurrentPercent","percentFmatter","unformatPercent"));
	 columnModelCollection.add(new ProjectsDisplay("newFeatureRequiredPercent","percentFmatter","unformatPercent"));
	 columnModelCollection.add(new ProjectsDisplay("newFeatureCurrentPercent","percentFmatter","unformatPercent"));
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

}