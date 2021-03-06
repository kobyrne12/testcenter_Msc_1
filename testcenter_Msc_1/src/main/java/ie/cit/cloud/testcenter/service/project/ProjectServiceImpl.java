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
	public Project getProject(Long projectID) {
		try{
			return projectRepo.findById(projectID);			
		}
		catch(NoResultException nre)			
		{	
			return null;
		}			
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
	public void remove(Long projectID) {
		projectRepo.delete(projectRepo.get(projectID));
	}

	//  @Secured("ROLE_ADMIN")    
	public void updateProjectWithId(Long projectID, Project project) {
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
	public void updateProjectNameWithId(Long projectID, Project project, String projectName) {
		Project oldProject = projectRepo.findById(projectID);
		oldProject.setProjectName(project.getProjectName());   
		projectRepo.update(oldProject);
	}

	public boolean updateProject(Long projectID, Project project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Project> getAllProjectsByCompanyID(Long companyID) {
		return projectRepo.findAllProjectsByCompanyID(companyID);
	}
	//////////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////
	public String getCurrentCycleName(Long projectID)
	{
		Set<Cycle> projectCycles = getProject(projectID).getCycles();
		if(projectCycles != null && !projectCycles.isEmpty()) 
		{
			for(final Cycle cycle : projectCycles)
			{
				if(cycle.isLatest())
				{
					return cycle.getCycleName();
				}
			}			
		}
		return "n/a";
	}
	public Cycle getCurrentCycle(Long projectID)
	{
		Set<Cycle> projectCycles = getProject(projectID).getCycles();
		if(projectCycles == null || projectCycles.isEmpty()) 
		{
			return null;
		}
		for(final Cycle cycle : projectCycles)
		{
			if(cycle.isLatest())
			{
				return cycle;
			}
		}	
		return null;
	}

	public int getChildProjectsCount(Long projectID)
	{	
		if(getChildProjects(projectID) == null)
		{
			return 0;	
		}
		return getChildProjects(projectID).size();		
	}	
	public Set<Project> getChildProjects(Long projectID)
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

	public Set<Project> getParentAndChildProjects(Long projectID)
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

	public Project getParentProject(Long projectID)
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

	public String getParentProjectName(Long projectID)
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
	public int getParentAndChildCyclesCount(Long projectID)
	{	
		if(getParentAndChildCycles(projectID) == null)
		{
			return 0;	
		}
		return getParentAndChildCycles(projectID).size();		
	}	
	public Set<Cycle> getParentAndChildCycles(Long projectID)
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
	public int getCascadedAllTestRunsCount(Long projectID)
	{	
		if(getCascadedAllTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestRuns(projectID).size();		
	}	
	public Set<Testrun> getCascadedAllTestRuns(Long projectID)
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
	public int getCascadedCompulsoryTestRunsCount(Long projectID)
	{	
		if(getCascadedCompulsoryTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestRuns(projectID).size();		
	}	
	public Set<Testrun> getCascadedCompulsoryTestRuns(Long projectID)
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
	public int getCascadedOptionalTestRunsCount(Long projectID)
	{	
		if(getCascadedOptionalTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestRuns(projectID).size();		
	}	
	public Set<Testrun> getCascadedOptionalTestRuns(Long projectID)
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
	public int getCascadedAllTestCasesCount(Long projectID)
	{	
		if(getCascadedAllTestCases(projectID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestCases(projectID).size();		
	}	
	public Set<Testcase> getCascadedAllTestCases(Long projectID)
	{	
		Set<Project> projects = getParentAndChildProjects(projectID);
		if(projects == null || projects.isEmpty())
		{
			return null;
		}
		Set<Testcase> allTestcases = new HashSet<Testcase>();  
		for(final Project project : projects)
		{
			if(project.getTestplans() != null && !project.getTestplans().isEmpty())
			{
				for(final Testplan testplan : project.getTestplans())
				{
					if(testplan.getTestcases() != null && !testplan.getTestcases().isEmpty())
					{
						allTestcases.addAll(testplan.getTestcases());	
					}	
				}
			}
		}			
		return allTestcases;			
	}
	public int getCascadedCompulsoryTestCasesCount(Long projectID)
	{	
		if(getCascadedCompulsoryTestCases(projectID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestCases(projectID).size();		
	}	
	public Set<Testcase> getCascadedCompulsoryTestCases(Long projectID)
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
	public int getCascadedOptionalTestCasesCount(Long projectID)
	{	
		if(getCascadedOptionalTestCases(projectID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestCases(projectID).size();		
	}	
	public Set<Testcase> getCascadedOptionalTestCases(Long projectID)
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
	public int getCascadedAllTestPlansCount(Long projectID)
	{	
		if(getCascadedAllTestPlans(projectID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestPlans(projectID).size();		
	}	
	public Set<Testplan> getCascadedAllTestPlans(Long projectID)
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
	public int getCascadedCompulsoryTestPlansCount(Long projectID)
	{	
		if(getCascadedCompulsoryTestPlans(projectID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestPlans(projectID).size();		
	}		
	public Set<Testplan> getCascadedCompulsoryTestPlans(Long projectID)
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
	public int getCascadedOptionalTestPlansCount(Long projectID)
	{	
		if(getCascadedOptionalTestPlans(projectID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestPlans(projectID).size();		
	}	
	public Set<Testplan> getCascadedOptionalTestPlans(Long projectID)
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
	public int getCascadedDefectsCount(Long projectID)
	{	
		if(getCascadedDefects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedDefects(projectID).size();		
	}	
	public Set<Defect> getCascadedDefects(Long projectID)
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
	public int getCascadedSev1DefectsCount(Long projectID)
	{	
		if(getCascadedSev1Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev1Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev1Defects(Long projectID)
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
	public int getCascadedSev2DefectsCount(Long projectID)
	{	
		if(getCascadedSev2Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev2Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev2Defects(Long projectID)
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
	public int getCascadedSev3DefectsCount(Long projectID)
	{	
		if(getCascadedSev3Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev3Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev3Defects(Long projectID)
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
	public int getCascadedSev4DefectsCount(Long projectID)
	{	
		if(getCascadedSev4Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev4Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev4Defects(Long projectID)
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
	public int getCascadedEnvironmentsCount(Long projectID)
	{	
		if(getCascadedEnvironments(projectID) == null)
		{
			return 0;	
		}
		return getCascadedEnvironments(projectID).size();		
	}	
	public Set<Environment> getCascadedEnvironments(Long projectID)
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
	public int getCascadedRequirementsCount(Long projectID)
	{	
		if(getCascadedRequirements(projectID) == null)
		{
			return 0;	
		}
		return getCascadedRequirements(projectID).size();		
	}	

	public Set<Requirement> getCascadedRequirements(Long projectID)
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
	public int getCascadedTestersCount(Long projectID)
	{	
		if(getCascadedTesters(projectID) == null)
		{
			return 0;	
		}
		return getCascadedTesters(projectID).size();		
	}
	public Set<TestcenterUser> getCascadedTesters(Long projectID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedSnrTestersCount(Long projectID)
	{	
		if(getCascadedSnrTesters(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSnrTesters(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrTesters(Long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCascadedDevelopersCount(Long projectID)
	{	
		if(getCascadedDevelopers(projectID) == null)
		{
			return 0;	
		}
		return getCascadedDevelopers(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedDevelopers(Long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCascadedSnrDevelopersCount(Long projectID)
	{	
		if(getCascadedSnrDevelopers(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSnrDevelopers(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrDevelopers(Long projectID) {
		// TODO Auto-generated method stub
		return null;
	}
	/////////////////////////////////////////////////////////////////////////////////

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

		colNames.add("Current "+company.getCycleDisplayName());
		columnModelSet.add(new GridAttributes("currentCycleName"));

		colNames.add("State");
		columnModelSet.add(new GridAttributes("cycleState","setCycleStateBarChart","unSetBarChart", 60));

		colNames.add("Parent");
		columnModelSet.add(new GridAttributes("parentProject",25,true));

		colNames.add("Child "+ company.getProjectsDisplayName());
		columnModelSet.add(new GridAttributes("totalChildProjects",15,true));		

		colNames.add("Level");
		columnModelSet.add(new GridAttributes("levelName",15,true));

		colNames.add("Cycle company.getTestrunsDisplayName()");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsTotal",true));
		colNames.add("Complete");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsComplete",true));
		colNames.add("Passed");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsPassed",true));
		colNames.add("Failed");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsFailed",true));
		colNames.add("Deferred");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsDeferred",true));
		colNames.add("Blocked");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsBlocked",true));
		colNames.add("Incomplete");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsInComplete",true));
		colNames.add("Not Run");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsNotrun",true));
		colNames.add("In Progress");
		columnModelSet.add(new GridAttributes("currentCycleTestrunsInprogress",true));
		
		colNames.add(company.getCyclesDisplayName());
		columnModelSet.add(new GridAttributes("totalCycles"));
		
		
		colNames.add("Total "+ company.getDefectsDisplayName());
		columnModelSet.add(new GridAttributes("totalDefects"));		

		colNames.add("Current Sev 1s");
		columnModelSet.add(new GridAttributes("totalCurrentSev1s"));		
		colNames.add("Current Sev 2s");
		columnModelSet.add(new GridAttributes("totalCurrentSev2s"));		
		colNames.add("Current Sev 3s");
		columnModelSet.add(new GridAttributes("totalCurrentSev3s"));		
		colNames.add("Current Sev 4s");
		columnModelSet.add(new GridAttributes("totalCurrentSev4s"));		

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
		colNames.add(company.getSeniorDevelopersDisplayName());
		columnModelSet.add(new GridAttributes("totalSeniorDevelopers",true));	 

		colNames.add("Max Sev 1s");
		columnModelSet.add(new GridAttributes("totalAllowedSev1s",true));
		colNames.add("Max Sev 2s");
		columnModelSet.add(new GridAttributes("totalAllowedSev2s",true));
		colNames.add("Max Sev 3s");
		columnModelSet.add(new GridAttributes("totalAllowedSev3s",true));
		colNames.add("Max Sev 4s");
		columnModelSet.add(new GridAttributes("totalAllowedSev4s",true));

		colNames.add("LastModifiedDate");
		columnModelSet.add(new GridAttributes("lastModifiedDate",true));
		colNames.add("LastModifiedBy");
		columnModelSet.add(new GridAttributes("lastModifiedBy",true));	 
		colNames.add("CreatedBy");
		columnModelSet.add(new GridAttributes("createdBy",true));
		colNames.add("CreationDate");
		columnModelSet.add(new GridAttributes("creationDate",true));

		colNames.add("Company ID");
		columnModelSet.add(new GridAttributes("companyID",true));

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

	public Set<Project> getFilteredProjects(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName)
			{
		// Check which projects wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Project> projects = new HashSet<Project>();

		if (cycleID != null && !cycleID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{			
			Cycle cycle = cycleService.getCycle(Long.valueOf(cycleID));
			projects.add(getProject(cycle.getProjectID()));			
		}
		else
		{
			projects.addAll(company.getProjects());
		}		
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Testplan projects
		if (testplanID != null && !testplanID.isEmpty()) 
		{			
			if(testplanService.getProjects(Long.valueOf(testplanID)) != null)
			{
				projects.retainAll(testplanService.getProjects(Long.valueOf(testplanID)));
			}
			else
			{
				projects.clear();
			}
		}		
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Testcase projects
		if (testcaseID != null && !testcaseID.isEmpty()) 
		{			
			if(testcaseService.getProjects(Long.valueOf(testcaseID)) != null)
			{
				projects.retainAll(testcaseService.getProjects(Long.valueOf(testcaseID)));
			}
			else
			{
				projects.clear();
			}
		}		
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Testrun projects
		if (testrunID != null && !testrunID.isEmpty()) 
		{			
			if(testrunService.getProject(Long.valueOf(testrunID)) != null)
			{
				Set<Project> testrunProjects = new HashSet<Project>();
				testrunProjects.add(testrunService.getProject(Long.valueOf(testrunID)));
				projects.retainAll(testrunProjects);				
			}	
			else
			{
				projects.clear();
			}
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Defect projects
		if (defectID != null && !defectID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(defectService.getCascadedProjects(Long.valueOf(defectID)) != null)
			{
				projects.retainAll(defectService.getCascadedProjects(Long.valueOf(defectID)));				
			}
			else
			{
				projects.clear();
			}
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Requirement projects
		if (requirementID != null && !requirementID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(requirementService.getProjects(Long.valueOf(requirementID)) != null)
			{
				projects.retainAll(requirementService.getProjects(Long.valueOf(requirementID)));				
			}	
			else
			{
				projects.clear();
			}
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Environment projects
		if (environmentID != null && !environmentID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(environmentService.getProjects(Long.valueOf(environmentID)) != null)
			{
				projects.retainAll(environmentService.getProjects(Long.valueOf(environmentID)));				
			}
			else
			{
				projects.clear();
			}
		}
		if(projects == null || projects.isEmpty()){return null;}
		// Retain User projects
		//		if (userID != null && !userID.isEmpty()) // limit to projects that have this test plan id in it
		//		{			
		//			if(userService.getCascadedProjects(Long.valueOf(defectID)) != null)
		//			{
		//				projects.retainAll(userService.getCascadedProjects(Long.valueOf(defectID)));				
		//			}			
		//		}
		//		if(projects == null || projects.isEmpty()){return null;

		return projects;
			}
	public ProjectSummaryList getGridProjects(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName)
	{
		Set<Project> projects = getFilteredProjects(companyID, projectID,
				cycleID, testplanID, testcaseID,
				testrunID, defectID, requirementID,
				environmentID, userID, levelName);
		Set<ProjectSummary> projectSummarySet = new HashSet<ProjectSummary>();
		ProjectSummaryList projectSummaryList = new ProjectSummaryList();
		if(projects == null || projects.isEmpty())
		{
			return null;
		}
		for(final Project project : projects)
		{						
			//projectSummarySet.add(getProjectSummary(new ProjectSummary(project, levelName)));
			projectSummarySet.add(new ProjectSummary(project, levelName, this, testrunService, defectService
					,cycleService));
		}

		projectSummaryList.setProjects(projectSummarySet);
		return projectSummaryList;
	}



}