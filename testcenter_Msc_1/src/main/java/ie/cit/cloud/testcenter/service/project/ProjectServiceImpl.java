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
import ie.cit.cloud.testcenter.model.summary.ProjectMinSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectMinSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.respository.project.ProjectRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
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
	private ProjectRepository projectRepo;    

	@Autowired
	private CompanyService companyService;
	@Autowired
	private CycleService cycleService;
	@Autowired
	private TestplanService testplanService;
	@Autowired
	private TestcaseService testcaseService;
	@Autowired
	private TestrunService testrunService;
	@Autowired
	private DefectService defectService;
	@Autowired
	private RequirementService requirementService;
	@Autowired
	private EnvironmentService environmentService;	

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Project> getAllChildProjects(Long projectID) {
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
	public Set<Project> getAllProjectsByCompanyID(long companyID) {
		return projectRepo.findAllProjectsByCompanyID(companyID);
	}
	//////////////////////////////////////////////////////////////////


	public ColModelAndNames getColumnModelAndNames(Long companyID) 
	{
		// Constructor in order
		// name;index;hidden;width;align;
		// sortable;resizable;search;sorttype;jsonmap;key;
		Company company = companyService.getCompany(companyID);
		Collection<String> colNames = new ArrayList<String>();
		ColModelAndNames colModelAndName = new ColModelAndNames();
		Collection<GridAttributes> columnModelSet =  new ArrayList<GridAttributes>();	

		colNames.add("ID");	 
		columnModelSet.add(new GridAttributes("projectID",10));	
		
		colNames.add(company.getProjectDisplayName()+ " Name");
		columnModelSet.add(new GridAttributes("projectName",40));

		colNames.add("State");
		columnModelSet.add(new GridAttributes("overAllState","setOverallBarChart","unSetBarChart", 80));
		
		colNames.add("Parent");
		columnModelSet.add(new GridAttributes("parentProjectName",25));
		
		colNames.add("Child "+ company.getProjectsDisplayName());
		columnModelSet.add(new GridAttributes("childProjects",15));

		colNames.add("Level");
		columnModelSet.add(new GridAttributes("level",15));

		//for each level
		//{
		colNames.add("Min %");
		columnModelSet.add(new GridAttributes("requiredPercent","percentFmatter","unformatPercent",15));
		
		colNames.add("Current %");
		columnModelSet.add(new GridAttributes("currentPercent","percentFmatter","unformatPercent",15));
		//}
		colNames.add("Total "+ company.getDefectsDisplayName());
		columnModelSet.add(new GridAttributes("totalDefects"));
		
		colNames.add("Max Sev 1s");
		columnModelSet.add(new GridAttributes("totalAllowedSev1s",true));
		colNames.add("Current Sev 1s");
		columnModelSet.add(new GridAttributes("totalCurrentSev1s"));
		colNames.add("Max Sev 2s");
		columnModelSet.add(new GridAttributes("totalAllowedSev2s",true));
		colNames.add("Current Sev 2s");
		columnModelSet.add(new GridAttributes("totalCurrentSev2s"));
		colNames.add("Max Sev 3s");
		columnModelSet.add(new GridAttributes("totalAllowedSev3s",true));
		colNames.add("Current Sev 3s");
		columnModelSet.add(new GridAttributes("totalCurrentSev3s"));	
		colNames.add("Max Sev 4s");
		columnModelSet.add(new GridAttributes("totalAllowedSev4s",true));
		colNames.add("Current Sev 4s");
		columnModelSet.add(new GridAttributes("totalCurrentSev4s"));

		colNames.add("Company ID");
		columnModelSet.add(new GridAttributes("companyID",true));

		colNames.add(company.getCyclesDisplayName());
		columnModelSet.add(new GridAttributes("totalCycles",true));

		colNames.add("All "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalAllTestruns",10,true));		
		colNames.add("Compulsory "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalRequiredTestruns",10,true));	
		colNames.add("Optional "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalOptionalTestruns",10,true));	

		colNames.add(company.getTestplansDisplayName());
		columnModelSet.add(new GridAttributes("totalTestplans",true));
		colNames.add(company.getTestcasesDisplayName());
		columnModelSet.add(new GridAttributes("totalTestcases",true));	 

		colNames.add(company.getEnvironmentsDisplayName());
		columnModelSet.add(new GridAttributes("totalEnvironments",true));
		colNames.add(company.getRequirementsDisplayName());
		columnModelSet.add(new GridAttributes("totalRequirements",true));

		colNames.add(company.getTestersDisplayName());
		columnModelSet.add(new GridAttributes("totalTesters",true));
		colNames.add(company.getSeniorTestersDisplayName());
		columnModelSet.add(new GridAttributes("totalSeniorTesters",true));
		colNames.add(company.getDevelopersDisplayName());
		columnModelSet.add(new GridAttributes("totalDevelopers",true));
		colNames.add(company.getSeniordevelopersDisplayName());
		columnModelSet.add(new GridAttributes("totalSeniorDevelopers",true));	 

		colNames.add("LastModifiedDate");
		columnModelSet.add(new GridAttributes("lastModifiedDate",true));
		colNames.add("LastModifiedBy");
		columnModelSet.add(new GridAttributes("lastModifiedBy",true));	 
		colNames.add("CreatedBy");
		columnModelSet.add(new GridAttributes("createdBy",true));
		colNames.add("CreationDate");
		columnModelSet.add(new GridAttributes("creationDate",true));
		colNames.add("Position");
		columnModelSet.add(new GridAttributes("companyPosition",true));	

		// if customValue1 set then 	
		//colNames.add("customValue1");
		//columnModelSet.add(new GridAttributes("customColumn1",true));	 
		// if customValue2 set then 
		//colNames.add("customValue1");
		//columnModelSet.add(new GridAttributes("customColumn2",true));		
		// if customValue3 set then 
		//colNames.add("customValue3");
		//columnModelSet.add(new GridAttributes("customColumn3",true));		
		// if customValue4 set then 
		//colNames.add("customValue4");	
		//columnModelSet.add(new GridAttributes("customColumn4",true));		
		// if customValue5 set then 
		//colNames.add("customValue5");	
		//columnModelSet.add(new GridAttributes("customColumn5",true));	

		colModelAndName.setColName(colNames);    	
		colModelAndName.setColModel(columnModelSet);
		return colModelAndName;
	}

	public ProjectSummaryList getGridProjects(long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName)
	{
		// Check which projects wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Project> projects = new HashSet<Project>();

		if (cycleID != null && !cycleID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{			
			if(cycleID.contains(","))
			{
				String[] cycleIDArray = cycleID.split(",");
				for(String eachCycle : cycleIDArray)
				{
					Cycle cycle = cycleService.getCycle(Long.valueOf(eachCycle).longValue());
					projects.add(getProject(cycle.getProjectID()));
				}
			}
			else
			{
				Cycle cycle = cycleService.getCycle(Long.valueOf(cycleID).longValue());
				projects.add(getProject(cycle.getProjectID()));
			}
		}
		else
		{
			projects.addAll(company.getProjects());
		}		
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Testplan projects
		if (testplanID != null && !testplanID.isEmpty()) 
		{			
			if(testplanService.getProjects(Long.valueOf(testplanID).longValue()) != null)
			{
				projects.retainAll(testplanService.getProjects(Long.valueOf(testplanID).longValue()));
			}			
		}		
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Testcase projects
		if (testcaseID != null && !testcaseID.isEmpty()) 
		{			
			if(testcaseService.getProjects(Long.valueOf(testcaseID).longValue()) != null)
			{
				projects.retainAll(testcaseService.getProjects(Long.valueOf(testcaseID).longValue()));
			}			
		}		
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Testrun projects
		if (testrunID != null && !testrunID.isEmpty()) 
		{			
			if(testrunService.getProject(Long.valueOf(testrunID).longValue()) != null)
			{
				Set<Project> testrunProjects = new HashSet<Project>();
				testrunProjects.add(testrunService.getProject(Long.valueOf(testrunID).longValue()));
				projects.retainAll(testrunProjects);				
			}			
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Defect projects
		if (defectID != null && !defectID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(defectService.getCascadedProjects(Long.valueOf(defectID).longValue()) != null)
			{
				projects.retainAll(defectService.getCascadedProjects(Long.valueOf(defectID).longValue()));				
			}			
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Requirement projects
		if (requirementID != null && !requirementID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(requirementService.getProjects(Long.valueOf(requirementID).longValue()) != null)
			{
				projects.retainAll(requirementService.getProjects(Long.valueOf(requirementID).longValue()));				
			}				
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Environment projects
		if (environmentID != null && !environmentID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(environmentService.getProjects(Long.valueOf(environmentID).longValue()) != null)
			{
				projects.retainAll(environmentService.getProjects(Long.valueOf(environmentID).longValue()));				
			}			
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain User projects
		//		if (userID != null && !userID.isEmpty()) // limit to projects that have this test plan id in it
		//		{			
		//			if(userService.getCascadedProjects(Long.valueOf(defectID).longValue()) != null)
		//			{
		//				projects.retainAll(userService.getCascadedProjects(Long.valueOf(defectID).longValue()));				
		//			}			
		//		}
		//		if(projects == null || projects.isEmpty()){return null;}

		Set<ProjectSummary> projectSummarySet = new HashSet<ProjectSummary>();
		ProjectSummaryList projectSummaryList = new ProjectSummaryList();

		for(final Project project : projects)
		{						
			//projectSummarySet.add(getProjectSummary(new ProjectSummary(project, levelName)));
			projectSummarySet.add(new ProjectSummary(project, levelName, this, testrunService, defectService));
		}

		projectSummaryList.setProjects(projectSummarySet);
		return projectSummaryList;
	}

//	public ProjectSummary getProjectSummary(ProjectSummary oldProjectSummary )
//	{
//
//		ProjectSummary newProjectSummary = new ProjectSummary();
//
//		newProjectSummary.setProjectID(oldProjectSummary.getProjectID());
//		newProjectSummary.setCompanyID(oldProjectSummary.getCompanyID());
//		newProjectSummary.setProjectName(oldProjectSummary.getProjectName());	
//		newProjectSummary.setParentProjectName(oldProjectSummary.getParentProjectName());
//
//		newProjectSummary.setTotalChildProjects(oldProjectSummary.getTotalChildProjects());
//
//		newProjectSummary.setCurrentPercent(oldProjectSummary.getCurrentPercent());
//		newProjectSummary.setRequiredPercent(oldProjectSummary.getRequiredPercent());
//
//		newProjectSummary.setTotalCycles(oldProjectSummary.getTotalCycles());
//		newProjectSummary.setTotalEnvironments(oldProjectSummary.getTotalEnvironments());
//		newProjectSummary.setTotalRequirements(oldProjectSummary.getTotalRequirements());
//
//		newProjectSummary.setTotalAllTestruns(oldProjectSummary.getTotalAllTestruns());
//		newProjectSummary.setTotalRequiredTestruns(oldProjectSummary.getTotalRequiredTestruns());
//		newProjectSummary.setTotalOptionalTestruns(oldProjectSummary.getTotalOptionalTestruns());
//
//		newProjectSummary.setTotalTestplans(oldProjectSummary.getTotalTestplans());
//
//		newProjectSummary.setTotalTestcases(oldProjectSummary.getTotalTestcases());
//
//		newProjectSummary.setTotalDefects(oldProjectSummary.getTotalDefects());
//
//		newProjectSummary.setTotalCurrentSev1s(oldProjectSummary.getTotalCurrentSev1s());
//		newProjectSummary.setTotalCurrentSev2s(oldProjectSummary.getTotalCurrentSev2s());
//		newProjectSummary.setTotalCurrentSev3s(oldProjectSummary.getTotalCurrentSev3s());
//		newProjectSummary.setTotalCurrentSev4s(oldProjectSummary.getTotalCurrentSev4s());
//
//		newProjectSummary.setTotalAllowedSev1s(oldProjectSummary.getTotalAllowedSev1s());
//		newProjectSummary.setTotalAllowedSev2s(oldProjectSummary.getTotalAllowedSev2s());
//		newProjectSummary.setTotalAllowedSev3s(oldProjectSummary.getTotalAllowedSev3s());
//		newProjectSummary.setTotalAllowedSev4s(oldProjectSummary.getTotalAllowedSev4s());
//
//		newProjectSummary.setTotalTesters(oldProjectSummary.getTotalTesters());
//		newProjectSummary.setTotalSeniorTesters(oldProjectSummary.getTotalSeniorTesters());
//		newProjectSummary.setTotalDevelopers(oldProjectSummary.getTotalDevelopers());
//		newProjectSummary.setTotalSeniorDevelopers(oldProjectSummary.getTotalSeniorDevelopers());
//
//		newProjectSummary.setLastModifiedBy(oldProjectSummary.getLastModifiedBy());
//		newProjectSummary.setLastModifiedDate(oldProjectSummary.getLastModifiedDate());
//		newProjectSummary.setCreatedBy(oldProjectSummary.getCreatedBy());
//		newProjectSummary.setCreationDate(oldProjectSummary.getCreationDate());	
//
//		return newProjectSummary;
//	}
//	

	public RelatedObjectList getRelatedObjects(long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID) {
		// TODO Auto-generated method stub
		return null;
	}


	///////////////////////////////////////////////////////
	public int getChildProjectsCount(long projectID)
	{	
		if(getChildProjects(projectID) == null)
		{
			return 0;	
		}
		return getChildProjects(projectID).size();		
	}	
	public Set<Project> getChildProjects(long projectID)
	{		
		Project project = getProject(projectID);
		if(!project.isParent())
		{   			 
			return null;
		}
		try{
			Set<Project> childProjects = getAllChildProjects(projectID);
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

	public Set<Project> getParentAndChildProjects(long projectID)
	{		
		Project project = getProject(projectID);
		Set<Project> projects = new HashSet<Project>(); 
		projects.add(project);
		if(project.isParent())
		{   	
			try{
				Set<Project> childProjects = getAllChildProjects(projectID);
				if(childProjects != null && !childProjects.isEmpty())
				{
					projects.addAll(childProjects);				
				}						
			}
			catch(NoResultException nre)			
			{			
			}			
		}
		return projects;		
	} 

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

	public String getParentProjectName(long projectID)
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
			return parentProject.getProjectName();			
		}
		catch(NoResultException nre)			
		{
			return null;
		}
	}
	public int getParentAndChildCyclesCount(long projectID)
	{	
		if(getParentAndChildCycles(projectID) == null)
		{
			return 0;	
		}
		return getParentAndChildCycles(projectID).size();		
	}	
	public Set<Cycle> getParentAndChildCycles(long projectID)
	{
		Set<Project> projects = getParentAndChildProjects(projectID);
		if(projects == null || projects.isEmpty())
		{
			return null;
		}
		Set<Cycle> allCycles = new HashSet<Cycle>();
		for(final Project project : projects)
		{
			if(project.getCycles() != null && !project.getCycles().isEmpty())
			{
				allCycles.addAll(project.getCycles());	
			}
		}		
		return allCycles;		
	}
	public int getCascadedAllTestRunsCount(long projectID)
	{	
		if(getCascadedAllTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestRuns(projectID).size();		
	}	
	public Set<Testrun> getCascadedAllTestRuns(long projectID)
	{		
		Set<Testrun> allTestruns = new HashSet<Testrun>();  	
		if(getParentAndChildCycles(projectID) != null && !getParentAndChildCycles(projectID).isEmpty())
		{
			for(final Cycle cycle : getParentAndChildCycles(projectID))
			{    
				if(cycleService.getCascadedAllTestRuns(cycle.getCycleID()) != null &&
						!cycleService.getCascadedAllTestRuns(cycle.getCycleID()).isEmpty())
				{
					allTestruns.addAll(cycleService.getCascadedAllTestRuns(cycle.getCycleID()));	
				}
			}				
		} 		
		return allTestruns;		
	}	
	public int getCascadedCompulsoryTestRunsCount(long projectID)
	{	
		if(getCascadedCompulsoryTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestRuns(projectID).size();		
	}	
	public Set<Testrun> getCascadedCompulsoryTestRuns(long projectID)
	{
		Set<Testrun> allTestruns = new HashSet<Testrun>();  	
		if(getParentAndChildCycles(projectID) != null && !getParentAndChildCycles(projectID).isEmpty())
		{
			for(final Cycle cycle : getParentAndChildCycles(projectID))
			{    
				if(cycleService.getCascadedCompulsoryTestRuns(cycle.getCycleID()) != null &&
						!cycleService.getCascadedCompulsoryTestRuns(cycle.getCycleID()).isEmpty())
				{
					allTestruns.addAll(cycleService.getCascadedCompulsoryTestRuns(cycle.getCycleID()));	
				}
			}				
		} 		
		return allTestruns;		
	}
	public int getCascadedOptionalTestRunsCount(long projectID)
	{	
		if(getCascadedOptionalTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestRuns(projectID).size();		
	}	
	public Set<Testrun> getCascadedOptionalTestRuns(long projectID)
	{
		Set<Testrun> allTestruns = new HashSet<Testrun>();  	
		if(getParentAndChildCycles(projectID) != null && !getParentAndChildCycles(projectID).isEmpty())
		{
			for(final Cycle cycle : getParentAndChildCycles(projectID))
			{    
				if(cycleService.getCascadedOptionalTestRuns(cycle.getCycleID()) != null &&
						!cycleService.getCascadedOptionalTestRuns(cycle.getCycleID()).isEmpty())
				{
					allTestruns.addAll(cycleService.getCascadedOptionalTestRuns(cycle.getCycleID()));	
				}
			}				
		} 		
		return allTestruns;		
	}
	public int getCascadedAllTestCasesCount(long projectID)
	{	
		if(getCascadedAllTestCases(projectID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestCases(projectID).size();		
	}	
	public Set<Testcase> getCascadedAllTestCases(long projectID)
	{	
		Set<Project> projects = getParentAndChildProjects(projectID);
		if(projects == null || projects.isEmpty())
		{
			return null;
		}
		Set<Testcase> allTestcases = new HashSet<Testcase>();  
		for(final Project project : projects)
		{
			if(project.getTestcases() != null && !project.getTestcases().isEmpty())
			{
				allTestcases.addAll(project.getTestcases());	
			}				
		}			
		return allTestcases;			
	}
	public int getCascadedCompulsoryTestCasesCount(long projectID)
	{	
		if(getCascadedCompulsoryTestCases(projectID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestCases(projectID).size();		
	}	
	public Set<Testcase> getCascadedCompulsoryTestCases(long projectID)
	{
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testcase> compulsoryTestcases = new HashSet<Testcase>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedCompulsoryTestCases(cycle.getCycleID()) != null && 
					!cycleService.getCascadedCompulsoryTestCases(cycle.getCycleID()).isEmpty())
			{
				compulsoryTestcases.addAll(cycleService.getCascadedCompulsoryTestCases(cycle.getCycleID()));
			}						
		}			
		return compulsoryTestcases;			
	}
	public int getCascadedOptionalTestCasesCount(long projectID)
	{	
		if(getCascadedOptionalTestCases(projectID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestCases(projectID).size();		
	}	
	public Set<Testcase> getCascadedOptionalTestCases(long projectID)
	{
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testcase> compulsoryTestcases = new HashSet<Testcase>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedOptionalTestCases(cycle.getCycleID()) != null && 
					!cycleService.getCascadedOptionalTestCases(cycle.getCycleID()).isEmpty())
			{
				compulsoryTestcases.addAll(cycleService.getCascadedOptionalTestCases(cycle.getCycleID()));
			}						
		}			
		return compulsoryTestcases;			
	}
	public int getCascadedAllTestPlansCount(long projectID)
	{	
		if(getCascadedAllTestPlans(projectID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestPlans(projectID).size();		
	}	
	public Set<Testplan> getCascadedAllTestPlans(long projectID)
	{	
		Set<Project> projects = getParentAndChildProjects(projectID);
		if(projects == null || projects.isEmpty())
		{
			return null;
		}
		Set<Testplan> allTestplans = new HashSet<Testplan>();  
		for(final Project project : projects)
		{
			if(project.getTestplans() != null && !project.getTestplans().isEmpty())
			{
				allTestplans.addAll(project.getTestplans());	
			}				
		}			
		return allTestplans;			
	}
	public int getCascadedCompulsoryTestPlansCount(long projectID)
	{	
		if(getCascadedCompulsoryTestPlans(projectID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestPlans(projectID).size();		
	}		
	public Set<Testplan> getCascadedCompulsoryTestPlans(long projectID)
	{
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testplan> compulsoryTestplans = new HashSet<Testplan>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedCompulsoryTestPlans(cycle.getCycleID()) != null && 
					!cycleService.getCascadedCompulsoryTestPlans(cycle.getCycleID()).isEmpty())
			{
				compulsoryTestplans.addAll(cycleService.getCascadedCompulsoryTestPlans(cycle.getCycleID()));
			}						
		}			
		return compulsoryTestplans;			
	}
	public int getCascadedOptionalTestPlansCount(long projectID)
	{	
		if(getCascadedOptionalTestPlans(projectID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestPlans(projectID).size();		
	}	
	public Set<Testplan> getCascadedOptionalTestPlans(long projectID)
	{
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testplan> compulsoryTestplans = new HashSet<Testplan>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedOptionalTestPlans(cycle.getCycleID()) != null && 
					!cycleService.getCascadedOptionalTestPlans(cycle.getCycleID()).isEmpty())
			{
				compulsoryTestplans.addAll(cycleService.getCascadedOptionalTestPlans(cycle.getCycleID()));
			}						
		}			
		return compulsoryTestplans;			
	}	
	public int getCascadedDefectsCount(long projectID)
	{	
		if(getCascadedDefects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedDefects(projectID).size();		
	}	
	public Set<Defect> getCascadedDefects(long projectID)
	{		
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Defect> defects = new HashSet<Defect>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedDefects(cycle.getCycleID()) != null && 
					!cycleService.getCascadedDefects(cycle.getCycleID()).isEmpty())
			{
				defects.addAll(cycleService.getCascadedDefects(cycle.getCycleID()));
			}						
		}			
		return defects;				
	}
	public int getCascadedSev1DefectsCount(long projectID)
	{	
		if(getCascadedSev1Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev1Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev1Defects(long projectID)
	{		
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Defect> defects = new HashSet<Defect>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedSev1Defects(cycle.getCycleID()) != null && 
					!cycleService.getCascadedSev1Defects(cycle.getCycleID()).isEmpty())
			{
				defects.addAll(cycleService.getCascadedSev1Defects(cycle.getCycleID()));
			}						
		}			
		return defects;				
	}
	public int getCascadedSev2DefectsCount(long projectID)
	{	
		if(getCascadedSev2Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev2Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev2Defects(long projectID)
	{		
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Defect> defects = new HashSet<Defect>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedSev2Defects(cycle.getCycleID()) != null && 
					!cycleService.getCascadedSev2Defects(cycle.getCycleID()).isEmpty())
			{
				defects.addAll(cycleService.getCascadedSev2Defects(cycle.getCycleID()));
			}						
		}			
		return defects;				
	}
	public int getCascadedSev3DefectsCount(long projectID)
	{	
		if(getCascadedSev3Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev3Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev3Defects(long projectID)
	{		
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Defect> defects = new HashSet<Defect>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedSev3Defects(cycle.getCycleID()) != null && 
					!cycleService.getCascadedSev3Defects(cycle.getCycleID()).isEmpty())
			{
				defects.addAll(cycleService.getCascadedSev3Defects(cycle.getCycleID()));
			}						
		}			
		return defects;				
	}
	public int getCascadedSev4DefectsCount(long projectID)
	{	
		if(getCascadedSev4Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev4Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev4Defects(long projectID)
	{		
		Set<Cycle> cycles = getParentAndChildCycles(projectID);	
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Defect> defects = new HashSet<Defect>();  
		for(final Cycle cycle : cycles)
		{
			if(cycleService.getCascadedSev4Defects(cycle.getCycleID()) != null && 
					!cycleService.getCascadedSev4Defects(cycle.getCycleID()).isEmpty())
			{
				defects.addAll(cycleService.getCascadedSev4Defects(cycle.getCycleID()));
			}						
		}			
		return defects;				
	}	
	public int getCascadedEnvironmentsCount(long projectID)
	{	
		if(getCascadedEnvironments(projectID) == null)
		{
			return 0;	
		}
		return getCascadedEnvironments(projectID).size();		
	}	
	public Set<Environment> getCascadedEnvironments(long projectID)
	{		
		Set<Testrun> testruns = getCascadedCompulsoryTestRuns(projectID);		
		if(testruns == null || testruns.isEmpty())
		{
			return null;	
		}
		Set<Environment> environments = new HashSet<Environment>(); 
		for(final Testrun testrun : testruns)
		{
			environments.addAll(testrun.getEnvironments());
		}		
		return environments; 
	}
	public int getCascadedRequirementsCount(long projectID)
	{	
		if(getCascadedRequirements(projectID) == null)
		{
			return 0;	
		}
		return getCascadedRequirements(projectID).size();		
	}	

	public Set<Requirement> getCascadedRequirements(long projectID)
	{		
		Set<Testrun> testruns = getCascadedCompulsoryTestRuns(projectID);		
		if(testruns == null || testruns.isEmpty())
		{
			return null;	
		}
		Set<Requirement> requirements = new HashSet<Requirement>(); 
		for(final Testrun testrun : testruns)
		{
			requirements.addAll(testrun.getRequirements());
		}		
		return requirements; 
	}	
	public int getCascadedTestersCount(long projectID)
	{	
		if(getCascadedTesters(projectID) == null)
		{
			return 0;	
		}
		return getCascadedTesters(projectID).size();		
	}
	public Set<TestcenterUser> getCascadedTesters(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedSnrTestersCount(long projectID)
	{	
		if(getCascadedSnrTesters(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSnrTesters(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrTesters(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCascadedDevelopersCount(long projectID)
	{	
		if(getCascadedDevelopers(projectID) == null)
		{
			return 0;	
		}
		return getCascadedDevelopers(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedDevelopers(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCascadedSnrDevelopersCount(long projectID)
	{	
		if(getCascadedSnrDevelopers(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSnrDevelopers(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrDevelopers(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}


}