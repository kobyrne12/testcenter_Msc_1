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
	ProjectRepository projectRepo;    

	@Autowired
	CompanyService companyService;
	@Autowired
	CycleService cycleService;
	@Autowired
	TestplanService testplanService;
	@Autowired
	TestcaseService testcaseService;
	@Autowired
	TestrunService testrunService;
	@Autowired
	DefectService defectService;
	@Autowired
	RequirementService requirementService;
	@Autowired
	EnvironmentService environmentService;	

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

		colNames.add("Overall");
		columnModelSet.add(new GridAttributes("overAllState","setOverallBarChart","unSetBarChart", 80));
		colNames.add(company.getNewFeatureDisplayName());
		columnModelSet.add(new GridAttributes("newFeatureState","setNewFeatureBarChart","unSetBarChart",60));
		colNames.add(company.getRegressionDisplayName());
		columnModelSet.add(new GridAttributes("regressionState","setRegressionBarChart","unSetBarChart", 60));	 
		colNames.add(company.getSanityDisplayName());
		columnModelSet.add(new GridAttributes("sanityState","setSanityBarChart","unSetBarChart",60));

		colNames.add("Parent");
		columnModelSet.add(new GridAttributes("parentProjectName",25));
		colNames.add("Child "+ company.getProjectsDisplayName());
		columnModelSet.add(new GridAttributes("childProjects",15));

		colNames.add("Min " + company.getRegressionDisplayName());
		columnModelSet.add(new GridAttributes("regressionCompulsoryPercent","percentFmatter","unformatPercent",15));
		colNames.add("Current " + company.getRegressionDisplayName());
		columnModelSet.add(new GridAttributes("regressionCurrentPercent","percentFmatter","unformatPercent",15));
		colNames.add("Min " + company.getNewFeatureDisplayName());
		columnModelSet.add(new GridAttributes("newFeatureCompulsoryPercent","percentFmatter","unformatPercent",15));
		colNames.add("Current " + company.getNewFeatureDisplayName());
		columnModelSet.add(new GridAttributes("newFeatureCurrentPercent","percentFmatter","unformatPercent",15));
		colNames.add("Min " + company.getSanityDisplayName());
		columnModelSet.add(new GridAttributes("sanityCompulsoryPercent","percentFmatter","unformatPercent",15));
		colNames.add("Current " + company.getSanityDisplayName());
		columnModelSet.add(new GridAttributes("sanityCurrentPercent","percentFmatter","unformatPercent",15));

		colModelAndName.setColName(colNames);    	
		colModelAndName.setColModel(columnModelSet);
		return colModelAndName;
	}

	public ProjectMinSummaryList getGridProjects(long companyID, String projectID, String cycleID,
			String testplanID,String testcaseID, String testrunID, String defectID, 
			String requirementID, String environmentID, String userID )
	{		
		// Check which projects wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Project> projects = new LinkedHashSet<Project>();

		if (cycleID != null && !cycleID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{			
			if(cycleID.contains(","))
			{
				String[] cycleIDArray = cycleID.split(",");
				for(String eachCycleID : cycleIDArray)
				{
					Cycle cycle = cycleService.getCycle(Long.valueOf(eachCycleID).longValue());
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
			if(testplanID.contains(","))
			{
				String[] testplanIDArray = testplanID.split(",");
				for(String eachtestplanID : testplanIDArray)
				{
					if(testplanService.getProjects(Long.valueOf(eachtestplanID).longValue()) != null)
					{
						projects.retainAll(testplanService.getProjects(Long.valueOf(eachtestplanID).longValue()));
					}
				}
			}
			else
			{
				if(testplanService.getProjects(Long.valueOf(testplanID).longValue()) != null)
				{
					projects.retainAll(testplanService.getProjects(Long.valueOf(testplanID).longValue()));
				}
			}						
		}		
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Testcase projects
		if (testcaseID != null && !testcaseID.isEmpty()) 
		{		
			if(testcaseID.contains(","))
			{
				String[] testcaseIDArray = testcaseID.split(",");
				for(String eachtestcaseID : testcaseIDArray)
				{
					if(testcaseService.getProjects(Long.valueOf(eachtestcaseID).longValue()) != null)
					{
						projects.retainAll(testcaseService.getProjects(Long.valueOf(eachtestcaseID).longValue()));
					}
				}
			}
			else
			{
				if(testcaseService.getProjects(Long.valueOf(testcaseID).longValue()) != null)
				{
					projects.retainAll(testcaseService.getProjects(Long.valueOf(testcaseID).longValue()));
				}
			}							
		}		
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Testrun projects
		if (testrunID != null && !testrunID.isEmpty()) 
		{		
			if(testrunID.contains(","))
			{
				String[] testrunIDArray = testrunID.split(",");
				for(String eachtestrunID : testrunIDArray)
				{
					if(testrunService.getProject(Long.valueOf(eachtestrunID).longValue()) != null)
					{
						Set<Project> testrunProjects = new HashSet<Project>();
						testrunProjects.add(testrunService.getProject(Long.valueOf(eachtestrunID).longValue()));
						projects.retainAll(testrunProjects);				
					}					
				}
			}
			else
			{
				if(testrunService.getProject(Long.valueOf(testrunID).longValue()) != null)
				{
					Set<Project> testrunProjects = new HashSet<Project>();
					testrunProjects.add(testrunService.getProject(Long.valueOf(testrunID).longValue()));
					projects.retainAll(testrunProjects);				
				}					
			}	
					
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Defect projects
		if (defectID != null && !defectID.isEmpty()) // limit to projects that have this test plan id in it
		{	
			if(defectID.contains(","))
			{
				String[] defectIDArray = defectID.split(",");
				for(String eachdefectID : defectIDArray)
				{
					if(defectService.getCascadedProjects(Long.valueOf(eachdefectID).longValue()) != null)
					{
						projects.retainAll(defectService.getCascadedProjects(Long.valueOf(eachdefectID).longValue()));				
					}					
				}
			}
			else
			{
				if(defectService.getCascadedProjects(Long.valueOf(defectID).longValue()) != null)
				{
					projects.retainAll(defectService.getCascadedProjects(Long.valueOf(defectID).longValue()));				
				}					
			}	
					
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Requirement projects
		if (requirementID != null && !requirementID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(requirementID.contains(","))
			{
				String[] requirementIDArray = requirementID.split(",");
				for(String eachrequirementID : requirementIDArray)
				{
					if(requirementService.getProjects(Long.valueOf(eachrequirementID).longValue()) != null)
					{
						projects.retainAll(requirementService.getProjects(Long.valueOf(eachrequirementID).longValue()));				
					}					
				}
			}
			else
			{
				if(requirementService.getProjects(Long.valueOf(requirementID).longValue()) != null)
				{
					projects.retainAll(requirementService.getProjects(Long.valueOf(requirementID).longValue()));				
				}					
			}							
		}
		if(projects == null || projects.isEmpty()){return null;}

		// Retain Environment projects
		if (environmentID != null && !environmentID.isEmpty()) // limit to projects that have this test plan id in it
		{		
			if(requirementID.contains(","))
			{
				String[] environmentIDArray = environmentID.split(",");
				for(String eachenvironmentID : environmentIDArray)
				{
					if(requirementService.getProjects(Long.valueOf(eachenvironmentID).longValue()) != null)
					{
						projects.retainAll(requirementService.getProjects(Long.valueOf(eachenvironmentID).longValue()));				
					}					
				}
			}
			else
			{
				if(environmentService.getProjects(Long.valueOf(environmentID).longValue()) != null)
				{
					projects.retainAll(environmentService.getProjects(Long.valueOf(environmentID).longValue()));				
				}				
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

		Set<ProjectMinSummary> projectMinSummarySet = new LinkedHashSet<ProjectMinSummary>();
		ProjectMinSummaryList projectMinSummaryList = new ProjectMinSummaryList();
	

		for(final Project project : projects)
		{				
			ProjectMinSummary projectMinSummary = getProjectMinSummary(companyID, project.getProjectID(),
					cycleID, testplanID, testcaseID, testrunID, defectID, requirementID, environmentID,
					userID);	
			projectMinSummarySet.add(projectMinSummary);
		}
		
		projectMinSummaryList.setProjects(projectMinSummarySet);
		return projectMinSummaryList;
	}
	public ProjectMinSummary getProjectMinSummary(long companyID, long projectID, String cycleID,
			String testplanID,String testcaseID, String testrunID, String defectID, 
			String requirementID, String environmentID, String userID )
	{

		Project project = getProject(projectID);
		ProjectSummary projectSummary = new ProjectSummary();

		projectSummary.setProjectID(project.getProjectID());
		projectSummary.setCompanyID(companyID);
		projectSummary.setProjectName(project.getProjectName());	

		if(getParentProjectName(projectID) !=null)
		{
			projectSummary.setParentProjectName(getParentProjectName(projectID));
		}
		else		
		{
			projectSummary.setParentProjectName("NONE");
		}
		projectSummary.setChildProjects(getAllChildProjects(projectID).size());
		Cycle latestcycle = new Cycle();
		if(project.getCycles() != null && !project.getCycles().isEmpty())
		{  
			for(Cycle cycle : project.getCycles())
			{
				if(cycleService.isLatest(cycle.getCycleID()))
				{
					latestcycle = cycleService.getCycle(cycle.getCycleID());
				}
			}
		}
		if(latestcycle != null)	
		{			
			Set<Testrun> allTestruns = new HashSet<Testrun>();
			Set<Testrun> compulsoryTestruns = new HashSet<Testrun>();
			Set<Testrun> optionalTestruns = new HashSet<Testrun>();
			Set<Defect> allDefects = new HashSet<Defect>();
			Set<Defect> sev1Defects = new HashSet<Defect>();
			Set<Defect> sev2Defects = new HashSet<Defect>();
			Set<Defect> sev3Defects = new HashSet<Defect>();
			Set<Defect> sev4Defects = new HashSet<Defect>();	
			if(getCascadedAllTestRuns(projectID) != null)
			{
				allTestruns.addAll(getCascadedAllTestRuns(projectID));
			}
			if(getCascadedCompulsoryTestRuns(projectID) != null)
			{
				compulsoryTestruns.addAll(getCascadedCompulsoryTestRuns(projectID));
			}
			if(getCascadedOptionalTestRuns(projectID) != null)
			{
				optionalTestruns.addAll(getCascadedOptionalTestRuns(projectID));
			}
			
			if(getCascadedDefects(projectID) != null)
			{
				allDefects.addAll(getCascadedDefects(projectID));
			}
			if(getCascadedSev1Defects(projectID) != null)
			{
				sev1Defects.addAll(getCascadedSev1Defects(projectID));
			}
			if(getCascadedSev2Defects(projectID) != null)
			{
				sev2Defects.addAll(getCascadedSev2Defects(projectID));
			}
			if(getCascadedSev3Defects(projectID) != null)
			{
				sev3Defects.addAll(getCascadedSev3Defects(projectID));
			}
			if(getCascadedSev4Defects(projectID) != null)
			{
				sev4Defects.addAll(getCascadedSev4Defects(projectID));
			}
			
			if (cycleID != null && !cycleID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
			{
			if(allTestruns != null && !allTestruns.isEmpty())
			{
				if(cycleService.getCascadedAllTestRuns(cycleID_long) == null)
				{
					allTestruns.clear();
				}
				else
				{
					allTestruns.retainAll(cycleService.getCascadedAllTestRuns(cycleID_long));
				}
			}
			if(compulsoryTestruns != null && !compulsoryTestruns.isEmpty())
			{
				if(cycleService.getCascadedCompulsoryTestRuns(cycleID_long) == null)
				{
					compulsoryTestruns.clear();
				}
				else
				{
					compulsoryTestruns.retainAll(cycleService.getCascadedCompulsoryTestRuns(cycleID_long));
				}
			}
			if(optionalTestruns != null && !optionalTestruns.isEmpty())
			{
				if(cycleService.getCascadedOptionalTestRuns(cycleID_long) == null)
				{
					optionalTestruns.clear();
				}
				else
				{
					optionalTestruns.retainAll(cycleService.getCascadedOptionalTestRuns(cycleID_long));
				}
			}
			//////////////// Defects 	
			if(allDefects != null && !allDefects.isEmpty())
			{
				if(cycleService.getCascadedDefects(cycleID_long) == null)
				{
					allDefects.clear();
				}
				else
				{
					allDefects.retainAll(cycleService.getCascadedDefects(cycleID_long));
				}
			}
			if(sev1Defects != null && !sev1Defects.isEmpty())
			{
				if(cycleService.getCascadedSev1Defects(cycleID_long) == null)
				{
					sev1Defects.clear();
				}
				else
				{
					sev1Defects.retainAll(cycleService.getCascadedSev1Defects(cycleID_long));
				}
			}
			if(sev2Defects != null && !sev2Defects.isEmpty())
			{
				if(cycleService.getCascadedSev2Defects(cycleID_long) == null)
				{
					sev2Defects.clear();
				}
				else
				{
					sev2Defects.retainAll(cycleService.getCascadedSev2Defects(cycleID_long));
				}
			}
			if(sev3Defects != null && !sev1Defects.isEmpty())
			{
				if(cycleService.getCascadedSev3Defects(cycleID_long) == null)
				{
					sev3Defects.clear();
				}
				else
				{
					sev3Defects.retainAll(cycleService.getCascadedSev3Defects(cycleID_long));
				}
			}
			if(sev4Defects != null && !sev4Defects.isEmpty())
			{
				if(cycleService.getCascadedSev4Defects(cycleID_long) == null)
				{
					sev4Defects.clear();
				}
				else
				{
					sev4Defects.retainAll(cycleService.getCascadedSev4Defects(cycleID_long));
				}
			}
			}
		}

		/////////////////////////////////////////////////////////////////////////////////////
		
		
	
		
	
		return null;
		
	
		/////////////////////////////////////////////////////////////////////////////////////	
	}
	public ProjectSummary getProjectSummary(long companyID, long projectID, String cycleID,
			String testplanID,String testcaseID, String testrunID, String defectID, 
			String requirementID, String environmentID, String userID )
	{

		Project project = getProject(projectID);
		ProjectSummary projectSummary = new ProjectSummary();

		projectSummary.setProjectID(project.getProjectID());
		projectSummary.setCompanyID(companyID);
		projectSummary.setProjectName(project.getProjectName());	

		if(getParentProjectName(projectID) !=null)
		{
			projectSummary.setParentProjectName(getParentProjectName(projectID));
		}
		else		
		{
			projectSummary.setParentProjectName("NONE");
		}
		projectSummary.setChildProjects(getAllChildProjects(projectID).size());
		projectSummary.setLastModifiedBy(project.getLastModifiedBy());
		projectSummary.setLastModifiedDate(project.getLastModifiedDate());
		projectSummary.setCreatedBy(project.getCreatedBy());
		projectSummary.setCreationDate(project.getCreationDate());		

		/////////////////////////////////////////////////////////////////////////////////////
		Set<Testplan> allTestplans = new HashSet<Testplan>();
		Set<Testplan> compulsoryTestplans = new HashSet<Testplan>();
		Set<Testplan> optionalTestplans = new HashSet<Testplan>();	
		Set<Testcase> allTestcases = new HashSet<Testcase>();
		Set<Testcase> compulsoryTestcases = new HashSet<Testcase>();
		Set<Testcase> optionalTestcases = new HashSet<Testcase>();
		Set<Testrun> allTestruns = new HashSet<Testrun>();
		Set<Testrun> compulsoryTestruns = new HashSet<Testrun>();
		Set<Testrun> optionalTestruns = new HashSet<Testrun>();
		Set<Defect> allDefects = new HashSet<Defect>();
		Set<Defect> sev1Defects = new HashSet<Defect>();
		Set<Defect> sev2Defects = new HashSet<Defect>();
		Set<Defect> sev3Defects = new HashSet<Defect>();
		Set<Defect> sev4Defects = new HashSet<Defect>();	
		Set<Cycle> allCycles = new HashSet<Cycle>();
		Set<Environment> allEnvirnments = new HashSet<Environment>();
		Set<Requirement> allRequirements = new HashSet<Requirement>();
		Set<TestcenterUser> allTesters = new HashSet<TestcenterUser>();
		Set<TestcenterUser> allSnrTesters = new HashSet<TestcenterUser>();
		Set<TestcenterUser> allDevelopers = new HashSet<TestcenterUser>();
		Set<TestcenterUser> allSnrDeveloper = new HashSet<TestcenterUser>();
		
		if(getCascadedAllTestPlans(projectID) != null)
		{
			allTestplans.addAll(getCascadedAllTestPlans(projectID));
		}
		if(getCascadedCompulsoryTestPlans(projectID) != null)
		{
			compulsoryTestplans.addAll(getCascadedCompulsoryTestPlans(projectID));
		}
		if(getCascadedOptionalTestPlans(projectID) != null)
		{
			optionalTestplans.addAll(getCascadedOptionalTestPlans(projectID));
		}
		
		if(getCascadedAllTestCases(projectID) != null)
		{
			allTestcases.addAll(getCascadedAllTestCases(projectID));
		}
		if(getCascadedCompulsoryTestCases(projectID) != null)
		{
			compulsoryTestcases.addAll(getCascadedCompulsoryTestCases(projectID));
		}
		if(getCascadedOptionalTestCases(projectID) != null)
		{
			optionalTestcases.addAll(getCascadedOptionalTestCases(projectID));
		}
			
		
		if(getCascadedAllTestRuns(projectID) != null)
		{
			allTestruns.addAll(getCascadedAllTestRuns(projectID));
		}
		if(getCascadedCompulsoryTestRuns(projectID) != null)
		{
			compulsoryTestruns.addAll(getCascadedCompulsoryTestRuns(projectID));
		}
		if(getCascadedOptionalTestRuns(projectID) != null)
		{
			optionalTestruns.addAll(getCascadedOptionalTestRuns(projectID));
		}
		
		if(getCascadedDefects(projectID) != null)
		{
			allDefects.addAll(getCascadedDefects(projectID));
		}
		if(getCascadedSev1Defects(projectID) != null)
		{
			sev1Defects.addAll(getCascadedSev1Defects(projectID));
		}
		if(getCascadedSev2Defects(projectID) != null)
		{
			sev2Defects.addAll(getCascadedSev2Defects(projectID));
		}
		if(getCascadedSev3Defects(projectID) != null)
		{
			sev3Defects.addAll(getCascadedSev3Defects(projectID));
		}
		if(getCascadedSev4Defects(projectID) != null)
		{
			sev4Defects.addAll(getCascadedSev4Defects(projectID));
		}
		
		if(getParentAndChildCycles(projectID) != null)
		{
			allCycles.addAll(getParentAndChildCycles(projectID));
		}
		
		if(getCascadedEnvironments(projectID) != null)
		{
			allEnvirnments.addAll(getCascadedEnvironments(projectID));
		}
		if(getCascadedRequirements(projectID) != null)
		{
			allRequirements.addAll(getCascadedRequirements(projectID));
		}
			
		if(getCascadedTesters(projectID) != null)
		{
			allTesters.addAll(getCascadedTesters(projectID));
		}
		if(getCascadedSnrTesters(projectID) != null)
		{
			allSnrTesters.addAll(getCascadedSnrTesters(projectID));
		}
		if(getCascadedDevelopers(projectID) != null)
		{
			allDevelopers.addAll(getCascadedDevelopers(projectID));
		}
		if(getCascadedSnrDevelopers(projectID) != null)
		{
			allSnrDeveloper.addAll(getCascadedSnrDevelopers(projectID));
		}
	
		/////////////////////////////////////////////////////////////////////////////////////		
		
		if (cycleID != null && !cycleID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{			
			long cycleID_long = Long.valueOf(cycleID).longValue();			
			/////////////////////////////////////////////////////////////////////////////////////
			if(allTestplans != null && !allTestplans.isEmpty())
			{
				if(cycleService.getCascadedAllTestPlans(cycleID_long) == null)
				{
					allTestplans.clear();
				}
				else
				{
					allTestplans.retainAll(cycleService.getCascadedAllTestPlans(cycleID_long));
				}
			}
			if(compulsoryTestplans != null && !compulsoryTestplans.isEmpty())
			{
				if(cycleService.getCascadedCompulsoryTestPlans(cycleID_long) == null)
				{
					compulsoryTestplans.clear();
				}
				else
				{
					compulsoryTestplans.retainAll(cycleService.getCascadedCompulsoryTestPlans(cycleID_long));
				}
			}
			if(optionalTestplans != null && !optionalTestplans.isEmpty())
			{
				if(cycleService.getCascadedOptionalTestPlans(cycleID_long) == null)
				{
					optionalTestplans.clear();
				}
				else
				{
					optionalTestplans.retainAll(cycleService.getCascadedOptionalTestPlans(cycleID_long));
				}
			}
			
			if(allTestcases != null && !allTestcases.isEmpty())
			{
				if(cycleService.getCascadedAllTestCases(cycleID_long) == null)
				{
					allTestcases.clear();
				}
				else
				{
					allTestcases.retainAll(cycleService.getCascadedAllTestCases(cycleID_long));
				}
			}
			if(compulsoryTestcases != null && !compulsoryTestcases.isEmpty())
			{
				if(cycleService.getCascadedCompulsoryTestCases(cycleID_long) == null)
				{
					compulsoryTestcases.clear();
				}
				else
				{
					compulsoryTestcases.retainAll(cycleService.getCascadedCompulsoryTestCases(cycleID_long));
				}
			}
			if(optionalTestcases != null && !optionalTestcases.isEmpty())
			{
				if(cycleService.getCascadedOptionalTestCases(cycleID_long) == null)
				{
					optionalTestcases.clear();
				}
				else
				{
					optionalTestcases.retainAll(cycleService.getCascadedOptionalTestCases(cycleID_long));
				}
			}
			
			
			if(allTestruns != null && !allTestruns.isEmpty())
			{
				if(cycleService.getCascadedAllTestRuns(cycleID_long) == null)
				{
					allTestruns.clear();
				}
				else
				{
					allTestruns.retainAll(cycleService.getCascadedAllTestRuns(cycleID_long));
				}
			}
			if(compulsoryTestruns != null && !compulsoryTestruns.isEmpty())
			{
				if(cycleService.getCascadedCompulsoryTestRuns(cycleID_long) == null)
				{
					compulsoryTestruns.clear();
				}
				else
				{
					compulsoryTestruns.retainAll(cycleService.getCascadedCompulsoryTestRuns(cycleID_long));
				}
			}
			if(optionalTestruns != null && !optionalTestruns.isEmpty())
			{
				if(cycleService.getCascadedOptionalTestRuns(cycleID_long) == null)
				{
					optionalTestruns.clear();
				}
				else
				{
					optionalTestruns.retainAll(cycleService.getCascadedOptionalTestRuns(cycleID_long));
				}
			}
			//////////////// Defects 	
			if(allDefects != null && !allDefects.isEmpty())
			{
				if(cycleService.getCascadedDefects(cycleID_long) == null)
				{
					allDefects.clear();
				}
				else
				{
					allDefects.retainAll(cycleService.getCascadedDefects(cycleID_long));
				}
			}
			if(sev1Defects != null && !sev1Defects.isEmpty())
			{
				if(cycleService.getCascadedSev1Defects(cycleID_long) == null)
				{
					sev1Defects.clear();
				}
				else
				{
					sev1Defects.retainAll(cycleService.getCascadedSev1Defects(cycleID_long));
				}
			}
			if(sev2Defects != null && !sev2Defects.isEmpty())
			{
				if(cycleService.getCascadedSev2Defects(cycleID_long) == null)
				{
					sev2Defects.clear();
				}
				else
				{
					sev2Defects.retainAll(cycleService.getCascadedSev2Defects(cycleID_long));
				}
			}
			if(sev3Defects != null && !sev1Defects.isEmpty())
			{
				if(cycleService.getCascadedSev3Defects(cycleID_long) == null)
				{
					sev3Defects.clear();
				}
				else
				{
					sev3Defects.retainAll(cycleService.getCascadedSev3Defects(cycleID_long));
				}
			}
			if(sev4Defects != null && !sev4Defects.isEmpty())
			{
				if(cycleService.getCascadedSev4Defects(cycleID_long) == null)
				{
					sev4Defects.clear();
				}
				else
				{
					sev4Defects.retainAll(cycleService.getCascadedSev4Defects(cycleID_long));
				}
			}
			////////////////Cycles 	
			if(allCycles != null && !allCycles.isEmpty())
			{
				allCycles.clear();
				if(cycleService.getCycle(cycleID_long) != null)
				{
					allCycles.add(cycleService.getCycle(cycleID_long));
				}				
			}
			else
			{
				if(cycleService.getCycle(cycleID_long) != null)
				{
					allCycles.add(cycleService.getCycle(cycleID_long));
				}	
			}
			//////////////// Envirnments 	
			if(allEnvirnments != null && !allEnvirnments.isEmpty())
			{
				if(cycleService.getCascadedEnvironments(cycleID_long) == null)
				{
					allEnvirnments.clear();
				}
				else
				{
					allEnvirnments.retainAll(cycleService.getCascadedEnvironments(cycleID_long));
				}
			}
			//////////////// Requirements 	
			if(allRequirements != null && !allRequirements.isEmpty())
			{
				if(cycleService.getCascadedRequirements(cycleID_long) == null)
				{
					allRequirements.clear();
				}
				else
				{
					allRequirements.retainAll(cycleService.getCascadedRequirements(cycleID_long));
				}
			}
			//////////////// Testers 	
			if(allTesters != null && !allTesters.isEmpty())
			{
				if(cycleService.getCascadedTesters(cycleID_long) == null)
				{
					allTesters.clear();
				}
				else
				{
					allTesters.retainAll(cycleService.getCascadedTesters(cycleID_long));
				}
			}
			//////////////// SnrTesters 	
			if(allSnrTesters != null && !allSnrTesters.isEmpty())
			{
				if(cycleService.getCascadedSnrTesters(cycleID_long) == null)
				{
					allSnrTesters.clear();
				}
				else
				{
					allSnrTesters.retainAll(cycleService.getCascadedSnrTesters(cycleID_long));
				}
			}
			//////////////// Developers 	
			if(allDevelopers != null && !allDevelopers.isEmpty())
			{
				if(cycleService.getCascadedDevelopers(cycleID_long) == null)
				{
					allDevelopers.clear();
				}
				else
				{
					allDevelopers.retainAll(cycleService.getCascadedDevelopers(cycleID_long));
				}
			}
			//////////////// SnrDeveloper 	
			if(allSnrDeveloper != null && !allSnrDeveloper.isEmpty())
			{
				if(cycleService.getCascadedSnrDevelopers(cycleID_long) == null)
				{
					allSnrDeveloper.clear();
				}
				else
				{
					allSnrDeveloper.retainAll(cycleService.getCascadedSnrDevelopers(cycleID_long));
				}
			}

			/////////////////////////////////////////////////////////////////////////////////////			
		}
			
		
		// Retain Testplan projects
		if (testplanID != null && !testplanID.isEmpty()) 
		{						
		}		
		
		// Retain Testcase projects
		if (testcaseID != null && !testcaseID.isEmpty()) 
		{				
		}		
		
		// Retain Testrun projects
		if (testrunID != null && !testrunID.isEmpty()) 
		{							
		}
		
		// Retain Defect projects
		if (defectID != null && !defectID.isEmpty()) // limit to projects that have this test plan id in it
		{			
		}
		
		// Retain Requirement projects
		if (requirementID != null && !requirementID.isEmpty()) // limit to projects that have this test plan id in it
		{								
		}
		
		// Retain Environment projects
		if (environmentID != null && !environmentID.isEmpty()) // limit to projects that have this test plan id in it
		{								
		}
		
		// Retain User projects
		//		if (userID != null && !userID.isEmpty()) // limit to projects that have this test plan id in it
		//		{			
		//				
		//		}
		if(allDefects != null && !allDefects.isEmpty())
		{
			projectSummary.setTotalDefects(allDefects.size());
		}
		else
		{
			projectSummary.setTotalDefects(0);
		}
		if(sev1Defects != null && !sev1Defects.isEmpty())
		{
			projectSummary.setTotalCurrentSev1s(sev1Defects.size());
		}
		else
		{
			projectSummary.setTotalCurrentSev1s(0);
		}
		if(sev2Defects != null && !sev2Defects.isEmpty())
		{
			projectSummary.setTotalCurrentSev2s(sev2Defects.size());
		}
		else
		{
			projectSummary.setTotalCurrentSev2s(0);
		}
		if(sev3Defects != null && !sev3Defects.isEmpty())
		{
			projectSummary.setTotalCurrentSev3s(sev3Defects.size());
		}
		else
		{
			projectSummary.setTotalCurrentSev3s(0);
		}
		if(sev4Defects != null && !sev4Defects.isEmpty())
		{
			projectSummary.setTotalCurrentSev4s(sev4Defects.size());
		}
		else
		{
			projectSummary.setTotalCurrentSev4s(0);
		}
		// Testplans
		if(allTestplans != null && !allTestplans.isEmpty())
		{
			projectSummary.setTotalAllTestplans(allTestplans.size());
		}
		else
		{
			projectSummary.setTotalAllTestplans(0);
		}
		if(compulsoryTestplans != null && !compulsoryTestplans.isEmpty())
		{
			projectSummary.setTotalRequiredTestplans(compulsoryTestplans.size());
		}
		else
		{
			projectSummary.setTotalRequiredTestplans(0);
		}
		if(optionalTestplans != null && !optionalTestplans.isEmpty())
		{
			projectSummary.setTotalOptionalTestplans(optionalTestplans.size());
		}
		else
		{
			projectSummary.setTotalOptionalTestplans(0);
		}	
		// Testcases
		if(allTestcases != null && !allTestcases.isEmpty())
		{
			projectSummary.setTotalAllTestcases(allTestcases.size());
		}
		else
		{
			projectSummary.setTotalAllTestcases(0);
		}
		if(compulsoryTestcases != null && !compulsoryTestcases.isEmpty())
		{
			projectSummary.setTotalRequiredTestcases(compulsoryTestcases.size());
		}
		else
		{
			projectSummary.setTotalRequiredTestcases(0);
		}
		if(optionalTestcases != null && !optionalTestcases.isEmpty())
		{
			projectSummary.setTotalOptionalTestcases(optionalTestcases.size());
		}
		else
		{
			projectSummary.setTotalOptionalTestcases(0);
		}	
		// Testruns
		if(allTestruns != null && !allTestruns.isEmpty())
		{
			projectSummary.setTotalAllTestruns(allTestruns.size());
		}
		else
		{
			projectSummary.setTotalAllTestruns(0);
		}
		if(compulsoryTestruns != null && !compulsoryTestruns.isEmpty())
		{
			projectSummary.setTotalRequiredTestruns(compulsoryTestruns.size());
		}
		else
		{
			projectSummary.setTotalRequiredTestruns(0);
		}
		if(optionalTestruns != null && !optionalTestruns.isEmpty())
		{
			projectSummary.setTotalOptionalTestruns(optionalTestruns.size());
		}
		else
		{
			projectSummary.setTotalOptionalTestruns(0);
		}
		
		if(allCycles != null && !allCycles.isEmpty())
		{
			projectSummary.setTotalCycles(allCycles.size());
		}
		else
		{
			projectSummary.setTotalCycles(0);
		}	
		
		if(allEnvirnments != null && !allEnvirnments.isEmpty())
		{
			projectSummary.setTotalEnvironments(allEnvirnments.size());
		}
		else
		{
			projectSummary.setTotalEnvironments(0);
		}	
		
		if(allRequirements != null && !allRequirements.isEmpty())
		{
			projectSummary.setTotalRequirements(allRequirements.size());
		}
		else
		{
			projectSummary.setTotalRequirements(0);
		}	
		
		if(allTesters != null && !allTesters.isEmpty())
		{
			projectSummary.setTotalTesters(allTesters.size());
		}
		else
		{
			projectSummary.setTotalTesters(0);
		}
		if(allSnrTesters != null && !allSnrTesters.isEmpty())
		{
			projectSummary.setTotalSeniorTesters(allSnrTesters.size());
		}
		else
		{
			projectSummary.setTotalSeniorTesters(0);
		}	
		if(allDevelopers != null && !allDevelopers.isEmpty())
		{
			projectSummary.setTotalDevelopers(allDevelopers.size());
		}
		else
		{
			projectSummary.setTotalDevelopers(0);
		}		
		if(allSnrDeveloper != null && !allSnrDeveloper.isEmpty())
		{
			projectSummary.setTotalSeniorDevelopers(allSnrDeveloper.size());
		}
		else
		{
			projectSummary.setTotalSeniorDevelopers(0);
		}		
		
		projectSummary.setNewFeatureRequiredPercent(project.getNewFeatureRequiredPercent());
		int currentNewFeaturePercent = (int) (Math.random() * ((100 - 1) + 1)); // just a random number until all entities are populated 
		projectSummary.setNewFeatureCurrentPercent(currentNewFeaturePercent);
		projectSummary.setRegressionCurrentPercent(project.getRegressionRequiredPercent());
		int currentRegressionPercent = (int) (Math.random() * ((100 - 1) + 1)); // just a random number until all entities are populated 
		projectSummary.setRegressionRequiredPercent(currentRegressionPercent);

		projectSummary.setTotalAllowedSev1s(project.getAllowedSev1());
		projectSummary.setTotalAllowedSev2s(project.getAllowedSev2());
		projectSummary.setTotalAllowedSev3s(project.getAllowedSev3());
		projectSummary.setTotalAllowedSev4s(project.getAllowedSev4());
		
		return projectSummary;
		
	}

	public RelatedObjectList getRelatedObjects(long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID)
	{

		Set<RelatedObject> relatedObjectSet =  new HashSet<RelatedObject>();
		RelatedObjectList relatedObjectList = new RelatedObjectList(); 

		//		try{
		//			ProjectSummary projectSummary = projectService.getProjectSummary(projectID); 
		//	    	Company company = companyService.getCompany(projectSummary.getCompanyID());
		//	    	
		//	    	// TODO MOVE TO Service  			
		//			relatedObjectList.setRelatedObjects(relatedObjectSet);
		//			return relatedObjectList;
		//			
		//		}catch(NoResultException e)
		//		{
		//			relatedObjectSet.add(new RelatedObject(1,"Select a Row to View Details","", projectID, null));  
		//			relatedObjectList.setRelatedObjects(relatedObjectSet);
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
		//			 projectSummarySet.add(projectSummary);
		//		 }
		//
		//		 projectSummaryList.setProjects(projectSummarySet);
		//		 return projectSummaryList;
		//	 }			
		return null;
	}

	///////////////////////////////////////////////////////

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

	public Set<TestcenterUser> getCascadedTesters(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}

}