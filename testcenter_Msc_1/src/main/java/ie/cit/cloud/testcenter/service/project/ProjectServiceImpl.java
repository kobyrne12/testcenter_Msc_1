/**
 * 
 */
package ie.cit.cloud.testcenter.service.project;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.display.RelatedObject;
import ie.cit.cloud.testcenter.display.RelatedObjectList;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.respository.project.ProjectRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

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
	@Autowired
	CycleService cycleService;
	@Autowired
	TestrunService testrunService;
	@Autowired
	DefectService defectService;
	@Autowired
	RequirementService requirementService;
	@Autowired
	EnvironmentService environmentService;	

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Project> getAllChildProjects(Long projectID) {
		return projectRepo.findAllChildProjects(projectID);
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

		//		Project project = getProject(projectID);
		//		ProjectSummary projectSummary = new ProjectSummary();
		//		
		//		projectSummary.setProjectID(project.getProjectID());
		//		projectSummary.setProjectName(project.getProjectName());	
		//					
		//		// Child Projects
		//		int numbOfChildProjs = 55;
		//		if(numbOfChildProjs == 1)
		//		{
		//			projectSummary.setChildProjects("GET ChildProjs NAME");		
		//		}
		//		else
		//		{			
		//			projectSummary.setChildProjects(Integer.toString(numbOfChildProjs));
		//		}
		//		
		//		projectSummary.setNewFeatureRequiredPercent(project.getNewFeatureRequiredPercent());
		//		int currentNewFeaturePercent = (int) (Math.random() * ((100 - 1) + 1)); // just a random number until all entities are populated 
		//		projectSummary.setNewFeatureCurrentPercent(currentNewFeaturePercent);
		//		projectSummary.setRegressionCurrentPercent(project.getRegressionRequiredPercent());
		//		int currentRegressionPercent = (int) (Math.random() * ((100 - 1) + 1)); // just a random number until all entities are populated 
		//		projectSummary.setRegressionRequiredPercent(currentRegressionPercent);
		//		
		//		projectSummary.setAllowedSev1s(project.getAllowedSev1());
		//		projectSummary.setAllowedSev2s(project.getAllowedSev2());
		//		projectSummary.setAllowedSev3s(project.getAllowedSev3());
		//		projectSummary.setAllowedSev4s(project.getAllowedSev4());
		//		
		//		int currentSev1 = (int) (Math.random() * ((50 - 1) + 1)); // just a random number until all entities are populated 
		//		projectSummary.setCurrentSev1s(currentSev1);
		//		int currentSev2 = (int) (Math.random() * ((70 - 1) + 1)); // just a random number until all entities are populated 
		//		projectSummary.setCurrentSev2s(currentSev2);
		//		int currentSev3 = (int) (Math.random() * ((100 - 1) + 1)); // just a random number until all entities are populated 
		//		projectSummary.setCurrentSev3s(currentSev3);
		//		int currentSev4 = (int) (Math.random() * ((200 - 1) + 1)); // just a random number until all entities are populated 
		//		projectSummary.setCurrentSev4s(currentSev4);
		//		
		//		int totalDefects = currentSev1 + currentSev2 + currentSev3 + currentSev4;
		//		
		//		projectSummary.setTotalDefects(totalDefects);
		//		projectSummary.setCompanyID(project.getCompanyID());	
		//		
		//		// Cycles
		//		if(project.getCycles().size() == 0)
		//		{
		//			projectSummary.setCycles("None");
		//		}
		//		else if(project.getCycles().size() == 1)
		//		{
		//			projectSummary.setCycles("GET CYCLE NAME");
		//		}
		//		else
		//		{			
		//			projectSummary.setCycles(Integer.toString(project.getCycles().size()));
		//		}
		//		// env
		//		int numOfEnvs = 10;
		//		if(numOfEnvs == 1)
		//		{
		//			projectSummary.setEnvironments("GET Env NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setEnvironments(Integer.toString(numOfEnvs));
		//		}
		//		// requirements
		//		int numbOfReqs = 10;
		//		if(numbOfReqs == 1)
		//		{
		//			projectSummary.setRequirements("GET REQS NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setRequirements(Integer.toString(numbOfReqs));
		//		}
		//		// TestRun
		//		int numOfTestRuns = 1585;
		//		if(numOfTestRuns == 1)
		//		{
		//			projectSummary.setTestRuns("GET TEST RUN NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setTestRuns(Integer.toString(numOfTestRuns));
		//		}
		//		// Test Case
		//		int numOfTestCases = 1900;
		//		if(numOfTestCases == 1)
		//		{
		//			projectSummary.setTestcases("GET TEST CASE NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setTestcases(Integer.toString(numOfTestCases));
		//		}
		//		// Test Plan
		//		int numOfTestPlans = 22;
		//		if(numOfTestPlans == 1)
		//		{
		//			projectSummary.setTestplans("GET TEST Plan NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setTestplans(Integer.toString(numOfTestPlans));
		//		}
		//		// Testers
		//		int numOfTesters = 22;
		//		if(numOfTesters == 1)
		//		{
		//			projectSummary.setTesters("GET TESTERS NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setTesters(Integer.toString(numOfTesters));
		//		}
		//		// SNR Testers
		//		int numOfSnrTesters = 22;
		//		if(numOfSnrTesters == 1)
		//		{
		//			projectSummary.setSeniorTesters("GET SNR TESTERS NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setSeniorTesters(Integer.toString(numOfSnrTesters));
		//		}
		//		// Developers
		//		int numOfDevs = 22;
		//		if(numOfDevs == 1)
		//		{
		//			projectSummary.setDevelopers("GET DEV NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setDevelopers(Integer.toString(numOfDevs));
		//		}
		//		// SNR Developers
		//		int numOfSnrDevs = 22;
		//		if(numOfSnrDevs == 1)
		//		{
		//			projectSummary.setSeniorDevelopers("GET SNR DEV NAME");	
		//		}
		//		else
		//		{			
		//			projectSummary.setSeniorDevelopers(Integer.toString(numOfSnrDevs));
		//		}
		//		
		//		projectSummary.setLastModifiedBy(project.getLastModifiedBy());
		//		projectSummary.setLastModifiedDate(project.getLastModifiedDate());
		//		projectSummary.setCreatedBy(project.getCreatedBy());
		//		projectSummary.setCreationDate(project.getCreationDate());
		//		
		//		projectSummary.setParentProjectName("NOT DONE");	
		//		
		//		return projectSummary;
		return null;
	}



	public ColModelAndNames getColumnModelAndNames(Long companyID)
	{
		// Constructor in order
		// name;index;hidden;width;align;
		// sortable;resizable;search;sorttype;jsonmap;key;

		Company company = companyService.getCompany(companyID);
		Collection<String> colNames = new ArrayList<String>();
		ColModelAndNames colModelAndName = new ColModelAndNames();
		Collection<GridAttributes> columnModelCollection =  new ArrayList<GridAttributes>();	

		colNames.add("ID");	 
		columnModelCollection.add(new GridAttributes("projectID",10));	
		colNames.add(company.getProjectDisplayName()+ " Name");
		columnModelCollection.add(new GridAttributes("projectName",40));

		colNames.add("Overall");
		columnModelCollection.add(new GridAttributes("overAllState","setOverallBarChart","unSetBarChart", 80));
		colNames.add(company.getNewFeatureDisplayName());
		columnModelCollection.add(new GridAttributes("newFeatureState","setNewFeatureBarChart","unSetBarChart",60));
		colNames.add(company.getRegressionDisplayName());
		columnModelCollection.add(new GridAttributes("regressionState","setRegressionBarChart","unSetBarChart", 60));	 
		colNames.add(company.getSanityDisplayName());
		columnModelCollection.add(new GridAttributes("sanityState","setSanityBarChart","unSetBarChart",60));

		colNames.add("Parent");
		columnModelCollection.add(new GridAttributes("parentProjectName",25));
		colNames.add("Child "+ company.getProjectsDisplayName());
		columnModelCollection.add(new GridAttributes("childProjects",15));

		colNames.add("Min " + company.getRegressionDisplayName());
		columnModelCollection.add(new GridAttributes("regressionRequiredPercent","percentFmatter","unformatPercent",15));
		colNames.add("Current " + company.getRegressionDisplayName());
		columnModelCollection.add(new GridAttributes("regressionCurrentPercent","percentFmatter","unformatPercent",15));
		colNames.add("Min " + company.getNewFeatureDisplayName());
		columnModelCollection.add(new GridAttributes("newFeatureRequiredPercent","percentFmatter","unformatPercent",15));
		colNames.add("Current " + company.getNewFeatureDisplayName());
		columnModelCollection.add(new GridAttributes("newFeatureCurrentPercent","percentFmatter","unformatPercent",15));
		colNames.add("Current " + company.getSanityDisplayName());
		columnModelCollection.add(new GridAttributes("sanityRequiredPercent","percentFmatter","unformatPercent",15));
		colNames.add("Current " + company.getSanityDisplayName());
		columnModelCollection.add(new GridAttributes("sanityCurrentPercent","percentFmatter","unformatPercent",15));

		colNames.add("Total "+ company.getDefectsDisplayName());
		columnModelCollection.add(new GridAttributes("totalDefects"));
		colNames.add("Max Sev 1s");
		columnModelCollection.add(new GridAttributes("totalAllowedSev1s",true));
		colNames.add("Current Sev 1s");
		columnModelCollection.add(new GridAttributes("totalCurrentSev1s"));
		colNames.add("Max Sev 2s");
		columnModelCollection.add(new GridAttributes("totalAllowedSev2s",true));
		colNames.add("Current Sev 2s");
		columnModelCollection.add(new GridAttributes("totalCurrentSev2s"));
		colNames.add("Max Sev 3s");
		columnModelCollection.add(new GridAttributes("totalAllowedSev3s",true));
		colNames.add("Current Sev 3s");
		columnModelCollection.add(new GridAttributes("totalCurrentSev3s"));	
		colNames.add("Max Sev 4s");
		columnModelCollection.add(new GridAttributes("totalAllowedSev4s",true));
		colNames.add("Current Sev 4s");
		columnModelCollection.add(new GridAttributes("totalCurrentSev4s"));

		colNames.add("Company ID");
		columnModelCollection.add(new GridAttributes("companyID",true));

		colNames.add(company.getCyclesDisplayName());
		columnModelCollection.add(new GridAttributes("totalCycles",true));

		colNames.add("Required "+company.getTestrunsDisplayName());
		columnModelCollection.add(new GridAttributes("totalRequiredTestruns",10,true));	
		colNames.add("All "+company.getTestrunsDisplayName());
		columnModelCollection.add(new GridAttributes("totalAllTestruns",10,true));		

		colNames.add(company.getTestplansDisplayName());
		columnModelCollection.add(new GridAttributes("totalTestplans",true));
		colNames.add(company.getTestcasesDisplayName());
		columnModelCollection.add(new GridAttributes("totalTestcases",true));	 

		colNames.add(company.getEnvironmentsDisplayName());
		columnModelCollection.add(new GridAttributes("totalEnvironments",true));
		colNames.add(company.getRequirementsDisplayName());
		columnModelCollection.add(new GridAttributes("totalRequirements",true));

		colNames.add(company.getTestersDisplayName());
		columnModelCollection.add(new GridAttributes("totalTesters",true));
		colNames.add(company.getSeniorTestersDisplayName());
		columnModelCollection.add(new GridAttributes("totalSeniorTesters",true));
		colNames.add(company.getDevelopersDisplayName());
		columnModelCollection.add(new GridAttributes("totalDevelopers",true));
		colNames.add(company.getSeniordevelopersDisplayName());
		columnModelCollection.add(new GridAttributes("totalSeniorDevelopers",true));	 

		colNames.add("LastModifiedDate");
		columnModelCollection.add(new GridAttributes("lastModifiedDate",true));
		colNames.add("LastModifiedBy");
		columnModelCollection.add(new GridAttributes("lastModifiedBy",true));	 
		colNames.add("CreatedBy");
		columnModelCollection.add(new GridAttributes("createdBy",true));
		colNames.add("CreationDate");
		columnModelCollection.add(new GridAttributes("creationDate",true));
		colNames.add("Position");
		columnModelCollection.add(new GridAttributes("compannyPosition",true));	
		// if customValue1 set then 	
		//colNames.add("customValue1");
		//columnModelCollection.add(new GridAttributes("customColumn1",true));	 
		// if customValue2 set then 
		//colNames.add("customValue1");
		//columnModelCollection.add(new GridAttributes("customColumn2",true));		
		// if customValue3 set then 
		//colNames.add("customValue3");
		//columnModelCollection.add(new GridAttributes("customColumn3",true));		
		// if customValue4 set then 
		//colNames.add("customValue4");	
		//columnModelCollection.add(new GridAttributes("customColumn4",true));		
		// if customValue5 set then 
		//colNames.add("customValue5");	
		//columnModelCollection.add(new GridAttributes("customColumn5",true));	

		colModelAndName.setColName(colNames);    	
		colModelAndName.setColModel(columnModelCollection);
		return colModelAndName;
	}

	public ProjectSummaryList getGridProjects(long companyID, String projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID)
	{
		Collection<ProjectSummary> projectSummaryCollection = new ArrayList<ProjectSummary>();
		ProjectSummaryList projectSummaryList = new ProjectSummaryList();
		Collection<Project> projects = new ArrayList<Project>();


		if (cycleID != null)
		{
			long cycleID_long = Long.valueOf(cycleID).longValue();	
			Cycle cycle = cycleService.getCycle(cycleID_long);
			projects.add(getProject(cycle.getProjectID()));
		}
		else if (projectID != null)
		{
			long projectID_long = Long.valueOf(projectID).longValue();
			projects.add(getProject(projectID_long));
		}
		else
		{
			projects.addAll(getAllProjectsByCompanyID(companyID));
		}	
		// Remove all projects that are not associated with the following
		if (testplanID != null){long testplanID_long = Long.valueOf(testplanID).longValue();	}
		if (environmentID != null){long environmentID_long = Long.valueOf(environmentID).longValue();	}
		if (requirementID != null){long requirementID_long = Long.valueOf(requirementID).longValue();	}
		if (defectID != null){long defectID_long = Long.valueOf(defectID).longValue();	}
		if (testrunID != null){long testrunID_long = Long.valueOf(testrunID).longValue();	}
		if (userID != null){long userID_long = Long.valueOf(userID).longValue();	}


		if(projects.isEmpty())
		{
			return null;
		}
		else
		{
			Iterator<Project> projectsItr = projects.iterator();	
			while (projectsItr.hasNext()) 
			{				
				Project project = projectsItr.next();						
				ProjectSummary projectSummary = getProjectSummary(project.getProjectID());	
				projectSummaryCollection.add(projectSummary);
			}

			projectSummaryList.setProjects(projectSummaryCollection);
			return projectSummaryList;
		}

	}


	public RelatedObjectList getRelatedObjects(long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID)
	{

		Collection<RelatedObject> relatedObjectCollection =  new ArrayList<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList(); 

		//		try{
		//			ProjectSummary projectSummary = projectService.getProjectSummary(projectID); 
		//	    	Company company = companyService.getCompany(projectSummary.getCompanyID());
		//	    	
		//	    	// TODO MOVE TO Service  			
		//			relatedObjectList.setRelatedObjects(relatedObjectCollection);
		//			return relatedObjectList;
		//			
		//		}catch(NoResultException e)
		//		{
		//			relatedObjectCollection.add(new RelatedObject(1,"Select a Row to View Details","", projectID, null));  
		//			relatedObjectList.setRelatedObjects(relatedObjectCollection);
		//			return relatedObjectList;
		//		}    	

		//		
		//	 if (cycleID != null)
		//	 {
		//		 long cycleID_long = Long.valueOf(cycleID).longValue();	
		//		 Cycle cycle = cycleService.getCycle(cycleID_long);
		//		 projects.add(getProject(cycle.getProjectID()));
		//	 }
		//	 else if (projectID != null)
		//	 {
		//		 long projectID_long = Long.valueOf(projectID).longValue();
		//		 projects.add(getProject(projectID_long));
		//	 }
		//	 else
		//	 {
		//		 projects.addAll(getAllProjectsByCompanyID(companyID));
		//	 }	
		//	// Remove all projects that are not associated with the following
		//		 if (testplanID != null){long testplanID_long = Long.valueOf(testplanID).longValue();	}
		//		 if (environmentID != null){long environmentID_long = Long.valueOf(environmentID).longValue();	}
		//		 if (requirementID != null){long requirementID_long = Long.valueOf(requirementID).longValue();	}
		//		 if (defectID != null){long defectID_long = Long.valueOf(defectID).longValue();	}
		//		 if (testrunID != null){long testrunID_long = Long.valueOf(testrunID).longValue();	}
		//		 if (userID != null){long userID_long = Long.valueOf(userID).longValue();	}
		//	 
		//	 
		//	 if(projects.isEmpty())
		//	 {
		//		 return null;
		//	 }
		//	 else
		//	 {
		//		 Iterator<Project> projectsItr = projects.iterator();	
		//		 while (projectsItr.hasNext()) 
		//		 {				
		//			 Project project = projectsItr.next();						
		//			 ProjectSummary projectSummary = getProjectSummary(project.getProjectID());	
		//			 projectSummaryCollection.add(projectSummary);
		//		 }
		//
		//		 projectSummaryList.setProjects(projectSummaryCollection);
		//		 return projectSummaryList;
		//	 }			
		return null;
	}

	///////////////////////////////////////////////////////


	/**
	 * Returns a collection of ChildProject for a project 
	 * Collection<Project>
	 * @return collection of ChildProject for a project
	 */
	public Collection<Project> getChildProjects(long projectID)
	{		
		Project project = getProject(projectID);
		if(!project.isParent())
		{   			 
			return null;
		}
		try{
			Collection<Project> childProjects = getAllChildProjects(projectID);
			if(childProjects == null || childProjects.isEmpty())
			{
				return null;
			}
			return childProjects;			
		}
		catch(NoResultException nre)			
		{
			return null;
		}
	} 

	/**
	 * Returns a Projects Parent Project  
	 * Project
	 * @return a Projects Parent Project, otherwise null
	 */	
	public Project getParentProject(long projectID)
	{		
		Project project = getProject(projectID);
		if(!project.isChild())
		{   			 
			return null;
		}
		try{
			Project parentProject = getProject(project.getParentID());
			if(parentProject == null)
			{
				return null;
			}
			return parentProject;			
		}
		catch(NoResultException nre)			
		{
			return null;
		}
	} 

	/**
	 * Returns total Number of All Cycles for a project incl child project cycles
	 * int
	 * @return total Number of All Cycles for a project incl child project cycles
	 */	
	public int getCascadedCyclesCount(long projectID)
	{
		Project project = getProject(projectID);
		int count = 0;    
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			count += project.getCycles().size();
		}     
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.isChild())
					{
						count += childProject.getCycles().size();	
					}
				}
			}
		}    	
		return count;		
	}
	/**
	 * Returns a collection of All Cycles in a project incl all child project cycles 
	 * Collection<Cycle>
	 * @return collection of All  Cycles in a project incl all child project cycles,
	 */	
	public Collection<Cycle> getCascadedCycles(long projectID)
	{
		Project project = getProject(projectID);
		Collection<Cycle> allCycles = new ArrayList<Cycle>();
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			allCycles.addAll(project.getCycles());				
		}  
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.isChild())
					{
						allCycles.addAll(childProject.getCycles());
					}														
				}
			}
		}    	
		return allCycles;		
	}
	/**
	 * Returns total Number of All Test Runs for a project
	 * int
	 * @return total Number of All Test Runs for a project
	 */	
	public int getCascadedTestRunsCount(long projectID)
	{
		Project project = getProject(projectID);
		int count = 0;    	
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			for(final Cycle cycle : project.getCycles())
			{    
				count += cycleService.getCascadedAllTestRunsCount(cycle.getCycleID());
			}  				
		}   
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					Collection<Cycle> childProjectCycles = childProject.getCycles();
					if(childProjectCycles != null && !childProjectCycles.isEmpty())
					{       
						if(childProject.isChild())
						{
							for(final Cycle childProjectCycle : childProjectCycles)
							{    
								count += cycleService.getCascadedAllTestRunsCount(childProjectCycle.getCycleID());
							}  
						}
					}
				}
			}
		}    	
		return count;		
	}

	/**
	 * Returns a collection of All Testruns in a project incl all child project cycles 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a project incl all child project cycles,
	 */	
	public Collection<Testrun> getCascadedAllTestRuns(long projectID)
	{
		Project project = getProject(projectID);
		Collection<Testrun> allTestruns = new ArrayList<Testrun>();  	
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			for(final Cycle cycle : project.getCycles())
			{    
				allTestruns = cycleService.getCascadedAllTestRuns(cycle.getCycleID());					
			}				
		} 
		if(project.isParent())
		{        	
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					Collection<Cycle> childProjectCycles = childProject.getCycles();
					if(childProjectCycles != null && !childProjectCycles.isEmpty())
					{       
						if(childProject.isChild())
						{
							for(final Cycle childProjectCycle : childProjectCycles)
							{    
								allTestruns = cycleService.getCascadedAllTestRuns(childProjectCycle.getCycleID());							
							}  
						}
					}
				}
			}
		}    	
		return allTestruns;		
	}
	/**
	 * Returns total Number of All Required Testruns for a project
	 * int
	 * @return total Number of All Required Testruns for a project
	 */		
	public int getCascadedRequiredTestRunsCount(long projectID)
	{
		Project project = getProject(projectID);
		int count = 0;    	
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			for(final Cycle cycle : project.getCycles())
			{
				count += cycleService.getCascadedRequiredTestRunsCount(cycle.getCycleID());
			}      			
		}  
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					Collection<Cycle> childProjectCycles = childProject.getCycles();
					if(childProjectCycles != null && !childProjectCycles.isEmpty())
					{            		
						for(final Cycle childProjectCycle : childProjectCycles)
						{        			
							count += cycleService.getCascadedRequiredTestRunsCount(childProjectCycle.getCycleID());
						}   
					}
				}
			}
		}    	
		return count;		
	}
	/**
	 * Returns a collection of Required Testruns in a project incl all child project cycles 
	 * Collection<Testrun>
	 * @return collection of Required Testruns in a project incl all child project cycles,
	 */	
	public Collection<Testrun> getCascadedRequiredTestRuns(long projectID)
	{
		Project project = getProject(projectID);
		Collection<Testrun> allTestruns = new ArrayList<Testrun>();  	
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			for(final Cycle cycle : project.getCycles())
			{    
				allTestruns = cycleService.getCascadedRequiredTestRuns(cycle.getCycleID());					
			}				
		}  
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					Collection<Cycle> childProjectCycles = childProject.getCycles();
					if(childProjectCycles != null && !childProjectCycles.isEmpty())
					{       
						if(childProject.isChild())
						{
							for(final Cycle childProjectCycle : childProjectCycles)
							{    
								allTestruns = cycleService.getCascadedRequiredTestRuns(childProjectCycle.getCycleID());		
							}  
						}
					}
				}
			}
		}    	
		return allTestruns;		
	}
	/**
	 * Returns a collection of Testplans in a project incl all child project cycles 
	 * Collection<Tesplan>
	 * @return collection of Testplans in a project incl all child project cycles,
	 */	
	public Collection<Testplan> getCascadedTestPlans(long projectID)
	{
		Project project = getProject(projectID);
		Collection<Testplan> allTestplans = new ArrayList<Testplan>();  	
		if(project.getTestplans() != null && !project.getTestplans().isEmpty())
		{
			allTestplans.addAll(project.getTestplans());										
		}   
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					allTestplans.addAll(childProject.getTestplans());
				}
			}
		}    	
		return allTestplans;		
	}
	/**
	 * Returns a collection of Testcases in a project incl all child project cycles 
	 * Collection<Testcase>
	 * @return collection of Testcases in a project incl all child project cycles,
	 */	
	public Collection<Testcase> getCascadedTestCases(long projectID)
	{
		Project project = getProject(projectID);
		Collection<Testcase> allTestcases = new ArrayList<Testcase>();  	
		if(project.getTestcases() != null && !project.getTestcases().isEmpty())
		{
			allTestcases.addAll(project.getTestcases());										
		}
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					allTestcases.addAll(childProject.getTestcases());
				}
			}
		}    	
		return allTestcases;		
	}
	// TODO finish from here
	private Collection<Defect> getSingleProjectDefects(Collection<Cycle> cycles)
	{
		Collection<Defect> defects = new ArrayList<Defect>();  			
		for(final Cycle cycle : cycles)
		{
			for(final Testrun testrun : cycleService.getCascadedRequiredTestRuns(cycle.getCycleID()))
			{
				defects.addAll(testrun.getDefects());	
				
				for(final Requirement requirement : testrun.getRequirements())
				{
					defects.addAll(requirement.getDefects());	
				}				
			}				
		}	
		return defects; 
	}	
	public Collection<Defect> getCascadedDefects(long projectID)
	{		
		Collection<Defect> defects = new ArrayList<Defect>();  			
		Project project = getProject(projectID);
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			defects.addAll(getSingleProjectDefects(project.getCycles()));			
		}
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.getCycles() != null && !childProject.getCycles().isEmpty())
					{
						defects.addAll(getSingleProjectDefects(childProject.getCycles()));
					}					
				}
			}
		}
		return defects; 
	}
	
	private Collection<Defect> getSingleProjectSev1Defects(Collection<Cycle> cycles)
	{
		Collection<Defect> defects = new ArrayList<Defect>();  			
		for(final Cycle cycle : cycles)
		{
			for(final Testrun testrun : cycleService.getCascadedRequiredTestRuns(cycle.getCycleID()))
			{
				for(final Defect testRunDefect : testrun.getDefects())
				{
					if(defectService.isSev1(testRunDefect.getDefectID()))
					{
						defects.add(testRunDefect);
					}
				}					
				for(final Requirement requirement : testrun.getRequirements())
				{
					for(final Defect requirementDefect : requirement.getDefects())
					{
						if(defectService.isSev1(requirementDefect.getDefectID()))
						{
							defects.add(requirementDefect);
						}
					}							
				}							
			}				
		}	
		return defects; 
	}
	private Collection<Defect> getSingleProjectSev2Defects(Collection<Cycle> cycles)
	{
		Collection<Defect> defects = new ArrayList<Defect>();  			
		for(final Cycle cycle : cycles)
		{
			for(final Testrun testrun : cycleService.getCascadedRequiredTestRuns(cycle.getCycleID()))
			{
				for(final Defect testRunDefect : testrun.getDefects())
				{
					if(defectService.isSev2(testRunDefect.getDefectID()))
					{
						defects.add(testRunDefect);
					}
				}					
				for(final Requirement requirement : testrun.getRequirements())
				{
					for(final Defect requirementDefect : requirement.getDefects())
					{
						if(defectService.isSev2(requirementDefect.getDefectID()))
						{
							defects.add(requirementDefect);
						}
					}							
				}						
			}				
		}	
		return defects; 
	}
	private Collection<Defect> getSingleProjectSev3Defects(Collection<Cycle> cycles)
	{
		Collection<Defect> defects = new ArrayList<Defect>();  			
		for(final Cycle cycle : cycles)
		{
			for(final Testrun testrun : cycleService.getCascadedRequiredTestRuns(cycle.getCycleID()))
			{
				for(final Defect testRunDefect : testrun.getDefects())
				{
					if(defectService.isSev3(testRunDefect.getDefectID()))
					{
						defects.add(testRunDefect);
					}
				}					
				for(final Requirement requirement : testrun.getRequirements())
				{
					for(final Defect requirementDefect : requirement.getDefects())
					{
						if(defectService.isSev3(requirementDefect.getDefectID()))
						{
							defects.add(requirementDefect);
						}
					}							
				}							
			}				
		}	
		return defects; 
	}
	private Collection<Defect> getSingleProjectSev4Defects(Collection<Cycle> cycles)
	{
		Collection<Defect> defects = new ArrayList<Defect>();  			
		for(final Cycle cycle : cycles)
		{
			for(final Testrun testrun : cycleService.getCascadedRequiredTestRuns(cycle.getCycleID()))
			{
				for(final Defect testRunDefect : testrun.getDefects())
				{
					if(defectService.isSev4(testRunDefect.getDefectID()))
					{
						defects.add(testRunDefect);
					}
				}					
				for(final Requirement requirement : testrun.getRequirements())
				{
					for(final Defect requirementDefect : requirement.getDefects())
					{
						if(defectService.isSev4(requirementDefect.getDefectID()))
						{
							defects.add(requirementDefect);
						}
					}							
				}							
			}				
		}	
		return defects; 
	}
	public Collection<Defect> getCascadedSev1Defects(long projectID)
	{		
		Collection<Defect> defects = new ArrayList<Defect>();  			
		Project project = getProject(projectID);
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			defects.addAll(getSingleProjectSev1Defects(project.getCycles()));			
		}
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.getCycles() != null && !childProject.getCycles().isEmpty())
					{
						defects.addAll(getSingleProjectSev1Defects(childProject.getCycles()));	
					}					
				}
			}
		}
		return defects; 
	}
	public Collection<Defect> getCascadedSev2Defects(long projectID)
	{		
		Collection<Defect> defects = new ArrayList<Defect>();  			
		Project project = getProject(projectID);
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			defects.addAll(getSingleProjectSev2Defects(project.getCycles()));			
		}
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.getCycles() != null && !childProject.getCycles().isEmpty())
					{
						defects.addAll(getSingleProjectSev2Defects(childProject.getCycles()));	
					}					
				}
			}
		}
		return defects; 
	}

	public Collection<Defect> getCascadedSev3Defects(long projectID)
	{		
		Collection<Defect> defects = new ArrayList<Defect>();  			
		Project project = getProject(projectID);
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			defects.addAll(getSingleProjectSev3Defects(project.getCycles()));			
		}
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.getCycles() != null && !childProject.getCycles().isEmpty())
					{
						defects.addAll(getSingleProjectSev3Defects(childProject.getCycles()));	
					}					
				}
			}
		}
		return defects; 
	}

	public Collection<Defect> getCascadedSev4Defects(long projectID)
	{		
		Collection<Defect> defects = new ArrayList<Defect>();  			
		Project project = getProject(projectID);
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			defects.addAll(getSingleProjectSev4Defects(project.getCycles()));			
		}
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.getCycles() != null && !childProject.getCycles().isEmpty())
					{
						defects.addAll(getSingleProjectSev4Defects(childProject.getCycles()));	
					}					
				}
			}
		}
		return defects; 
	}

	private Collection<Environment> getSingleProjectEnvironments(Collection<Cycle> cycles)
	{
		Collection<Environment> environments = new ArrayList<Environment>();  			
		for(final Cycle cycle : cycles)
		{
			for(final Testrun testrun : cycleService.getCascadedRequiredTestRuns(cycle.getCycleID()))
			{
				environments.addAll(testrun.getEnvironments());	
				
				for(final TestcenterUser user : testrun.getUsers())
				{
					environments.addAll(user.getEnvironments());	
				}
			}				
		}	
		return environments; 
	}	
	public Collection<Environment> getCascadedEnvironments(long projectID)
	{		
		Collection<Environment> environments = new ArrayList<Environment>();  			
		Project project = getProject(projectID);
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			environments.addAll(getSingleProjectEnvironments(project.getCycles()));			
		}
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.getCycles() != null && !childProject.getCycles().isEmpty())
					{
						environments.addAll(getSingleProjectEnvironments(childProject.getCycles()));
					}					
				}
			}
		}
		return environments; 
	}
	private Collection<Requirement> getSingleProjectRequirements(Collection<Cycle> cycles)
	{
		Collection<Requirement> requirements = new ArrayList<Requirement>();  			
		for(final Cycle cycle : cycles)
		{
			for(final Testrun testrun : cycleService.getCascadedRequiredTestRuns(cycle.getCycleID()))
			{
				requirements.addAll(testrun.getRequirements());	
				
				for(final TestcenterUser user : testrun.getUsers())
				{
					requirements.addAll(user.getRequirements());	
				}
				for(final Defect defect : testrun.getDefects())
				{
					requirements.addAll(defect.getRequirements());	
				}
			}				
		}	
		return requirements; 
	}	
	public Collection<Requirement> getCascadedRequirements(long projectID)
	{		
		Collection<Requirement> requirements = new ArrayList<Requirement>();  			
		Project project = getProject(projectID);
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{
			requirements.addAll(getSingleProjectRequirements(project.getCycles()));			
		}
		if(project.isParent())
		{  
			Collection<Project> childProjects = getChildProjects(projectID);
			if(childProjects != null && !childProjects.isEmpty())
			{
				for(final Project childProject : childProjects)
				{
					if(childProject.getCycles() != null && !childProject.getCycles().isEmpty())
					{
						requirements.addAll(getSingleProjectRequirements(childProject.getCycles()));
					}					
				}
			}
		}
		return requirements; 
	}
	

	public Collection<TestcenterUser> getCascadedTesters(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedSnrTesters(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedDevelopers(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedSnrDevelopers(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

}