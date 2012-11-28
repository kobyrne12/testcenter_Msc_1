/**
 * 
 */
package ie.cit.cloud.testcenter.service.cycle;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
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
import ie.cit.cloud.testcenter.respository.cycle.CycleRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CycleServiceImpl implements CycleService {

	@Autowired
	@Qualifier("hibernateCycleRespository")
	CycleRepository cycleRepo;      
	@Autowired
	CompanyService companyService;
	@Autowired
	ProjectService projectService;	
	@Autowired
	TestcaseService testcaseService;	
	@Autowired
	DefectService defectService;	
	@Autowired
	EnvironmentService environmentService;
	@Autowired
	RequirementService requirementService;
	@Autowired
	TestrunService testrunService;	
	@Autowired
	TestplanService testplanService;	


	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Cycle> getAllCycles() {
		return cycleRepo.findAll();
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Cycle getCycle(long cycleID) {
		try{
			return cycleRepo.findById(cycleID);			
		}
		catch(NoResultException nre)			
		{	
			return null;
		}			
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Cycle getCycleByName(String cycleName) {
		return cycleRepo.findCycleByName(cycleName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public long addNewCycle(Cycle cycle) {
		cycleRepo.create(cycle);	
		return cycle.getCycleID();
	}

	// @Secured("ROLE_ADMIN")
	public void update(Cycle cycle) {
		cycleRepo.update(cycle);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long cycleID) {
		cycleRepo.delete(cycleRepo.get(cycleID));
	}


	public boolean updateCycle(Cycle cycle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Cycle> getAllCyclesByProjectID(long projectID) {
		return cycleRepo.findAllCyclesByProjectID(projectID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Cycle> getAllCyclesByCompanyID(long companyID) {
		Set<Cycle> cycles = new HashSet<Cycle>();
		Company company = companyService.getCompany(companyID);
		Set<Project> projects = company.getProjects();
		Iterator<Project> projectsItr = projects.iterator();	
		while (projectsItr.hasNext()) 
		{				
			Project project = projectsItr.next();				 
			cycles.addAll(project.getCycles());			
		}		 
		return cycles;
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Cycle> getAllChildCycles(long cycleID) {
		return cycleRepo.findAllCyclesByParentID(cycleID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public int getMaxProjectPosNum(long projectID) {		
		return cycleRepo.getMaxProjectPosNum(projectID);
	}			

	/**
	 * Returns true if this cycle is the latest cycle for a project 
	 * boolean
	 * @return true if this cycle is the latest cycle for a project, otherwise false
	 */	
	public boolean isLatest(long cycleID)
	{			
		try{
			Cycle cycle = getCycle(cycleID);
			int maxProjectPosition = getMaxProjectPosNum(cycle.getProjectID());				
			if(maxProjectPosition == cycle.getProjectPosition())
			{
				return true;
			}		
			return false;			
		}
		catch(Exception nre)			
		{
			return true;
		}
	} 

	public String getParentCycleName(long cycleID)
	{		
		Cycle cycle = getCycle(cycleID);
		if(!cycle.isChild())
		{   			 
			return "NONE";
		}
		try{
			Cycle parentCycle = getCycle(cycle.getParentID());
			if(parentCycle == null)
			{
				return "NONE";
			}
			return parentCycle.getCycleName();			
		}
		catch(NoResultException nre)			
		{
			return "NONE";
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////              Related Objects             ////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public CycleSummaryList getGridCycles(long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID, String level)
	{
		// Check which projects wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Cycle> cycles = new HashSet<Cycle>();				


		if (projectID != null && !projectID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{						
			Project project = projectService.getProject(Long.valueOf(projectID).longValue());
			if(project == null)
			{
				return null;
			}
			if(project.getCycles() == null || project.getCycles().isEmpty())
			{
				return null;
			}	
			cycles.addAll(project.getCycles());						
		}
		else
		{
			if(companyService.getAllCycles(company.getCompanyID()) == null)
			{
				return null;
			}
			cycles.addAll(companyService.getAllCycles(company.getCompanyID()));
		}		
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Testplan cycles
		if (testplanID != null && !testplanID.isEmpty()) 
		{			
			if(testplanService.getCycles(Long.valueOf(testplanID).longValue()) != null)
			{
				cycles.retainAll(testplanService.getCycles(Long.valueOf(testplanID).longValue()));
			}			
		}		
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Testcase cycles
		if (testcaseID != null && !testcaseID.isEmpty()) 
		{			
			if(testcaseService.getCycles(Long.valueOf(testcaseID).longValue()) != null)
			{
				cycles.retainAll(testcaseService.getCycles(Long.valueOf(testcaseID).longValue()));
			}			
		}		
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Testrun cycles
		if (testrunID != null && !testrunID.isEmpty()) 
		{			
			if(testrunService.getCycle(Long.valueOf(testrunID).longValue()) != null)
			{
				Set<Cycle> testrunCycles = new HashSet<Cycle>();
				testrunCycles.add(testrunService.getCycle(Long.valueOf(testrunID).longValue()));
				cycles.retainAll(testrunCycles);				
			}			
		}
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Defect cycles
		if (defectID != null && !defectID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(defectService.getCascadedCycles(Long.valueOf(defectID).longValue()) != null)
			{
				cycles.retainAll(defectService.getCascadedCycles(Long.valueOf(defectID).longValue()));				
			}			
		}
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Requirement cycles
		if (requirementID != null && !requirementID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(requirementService.getCycles(Long.valueOf(requirementID).longValue()) != null)
			{
				cycles.retainAll(requirementService.getCycles(Long.valueOf(requirementID).longValue()));				
			}				
		}
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Environment cycles
		if (environmentID != null && !environmentID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(environmentService.getCycles(Long.valueOf(environmentID).longValue()) != null)
			{
				cycles.retainAll(environmentService.getCycles(Long.valueOf(environmentID).longValue()));				
			}			
		}
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain User cycles
		//		if (userID != null && !userID.isEmpty()) // limit to projects that have this test plan id in it
		//		{			
		//			if(userService.getCascadedProjects(Long.valueOf(defectID).longValue()) != null)
		//			{
		//				projects.retainAll(userService.getCascadedProjects(Long.valueOf(defectID).longValue()));				
		//			}			
		//		}
		//		if(projects == null || projects.isEmpty()){return null;}

		Set<CycleSummary> cycleSummarySet = new HashSet<CycleSummary>();
		CycleSummaryList cycleSummaryList = new CycleSummaryList();

		for(final Cycle cycle : cycles)
		{				
			CycleSummary cycleSummary = getCycleSummary(companyID, cycle.getCycleID(),level);	
			cycleSummarySet.add(cycleSummary);

		}

		cycleSummaryList.setCycles(cycleSummarySet);
		return cycleSummaryList;

	}


	public CycleSummary getCycleSummary(long companyID, long cycleID, String level)
	{
		Cycle cycle = getCycle(cycleID);		
		Project project = projectService.getProject(cycle.getProjectID());		
		CycleSummary cycleSummary = new CycleSummary();

		// Cycle info only 
		cycleSummary.setCycleID(cycleID);
		cycleSummary.setCycleName(cycle.getCycleName());	
		cycleSummary.setRequiredPriority(cycle.getRequiredPriority());		
		
		cycleSummary.setTotalAllTestruns(getCascadedAllTestRunsCount(cycleID, level));		
		cycleSummary.setTotalRequiredTestruns(getCascadedCompulsoryTestRunsCount(cycleID, level));
		cycleSummary.setTotalOptionalTestruns(getCascadedOptionalTestRunsCount(cycleID, level));
		
		cycleSummary.setTotalAllTestcases(getCascadedAllTestCasesCount(cycleID, level));		
		cycleSummary.setTotalRequiredTestcases(getCascadedCompulsoryTestCasesCount(cycleID, level));
		cycleSummary.setTotalOptionalTestcases(getCascadedOptionalTestCasesCount(cycleID, level));
		
		cycleSummary.setTotalAllTestplans(getCascadedAllTestPlansCount(cycleID, level));		
		cycleSummary.setTotalRequiredTestplans(getCascadedCompulsoryTestPlansCount(cycleID, level));
		cycleSummary.setTotalOptionalTestplans(getCascadedOptionalTestPlansCount(cycleID, level));
	
		cycleSummary.setTotalTestrunsPassed(getTestRunsPassedCount(cycleID, level));	
		cycleSummary.setTotalTestrunsFailed(getTestRunsFailedCount(cycleID, level));		
		cycleSummary.setTotalTestrunsDeferred(getTestRunsDeferredCount(cycleID, level));		
		cycleSummary.setTotalTestrunsBlocked(getTestRunsBlockedCount(cycleID, level));
		cycleSummary.setTotalTestrunsNotRun(getTestRunsNotrunCount(cycleID, level));	
		cycleSummary.setTotalTestrunsInProg(getTestRunsInprogressCount(cycleID, level));		
		cycleSummary.setTotalTestrunsCompleted(getTestRunsCompletedCount(cycleID, level));	
		cycleSummary.setTotalTestrunsNotCompleted(getTestRunsInCompletedCount(cycleID, level));

		cycleSummary.setTotalCycleEstTime(getTotalTestRunsEstTime(cycleID,level));
		
		cycleSummary.setCycleStartDate(cycle.getCycleStartDate());
		cycleSummary.setCycleEndDate(cycle.getCycleEndDate());
		
		cycleSummary.setParentCycleName(getParentCycleName(cycleID));	
		cycleSummary.setTotalChildCycles((getAllChildCycles(cycleID)) != null ? getAllChildCycles(cycleID).size() : 0);
	
		cycleSummary.setTotalDefects(getCascadedDefectsCount(cycleID));
		cycleSummary.setTotalAllowedSev1s(project.getAllowedSev1());
		cycleSummary.setTotalAllowedSev2s(project.getAllowedSev2());
		cycleSummary.setTotalAllowedSev3s(project.getAllowedSev3());
		cycleSummary.setTotalAllowedSev4s(project.getAllowedSev4());
		
		cycleSummary.setTotalCurrentSev1s(getCascadedSev1DefectsCount(cycleID));		
		cycleSummary.setTotalCurrentSev2s(getCascadedSev2DefectsCount(cycleID));
		cycleSummary.setTotalCurrentSev3s(getCascadedSev3DefectsCount(cycleID));
		cycleSummary.setTotalCurrentSev4s(getCascadedSev4DefectsCount(cycleID));

		cycleSummary.setCompanyID(project.getCompanyID());	
		cycleSummary.setProjectID(project.getProjectID());
		cycleSummary.setProjectName(project.getProjectName());
		
		cycleSummary.setTotalDefectRules((cycle.getDefectRules() != null) ? cycle.getDefectRules().size() : 0);
		cycleSummary.setTotalTestHistoryRules((cycle.getTesthistoryRules() != null) ? cycle.getTesthistoryRules().size() : 0);
		cycleSummary.setTotalCodeImpactRules((cycle.getChangeImpactRules() != null) ? cycle.getChangeImpactRules().size() : 0);
		cycleSummary.setTotalReqRules((cycle.getRequirementRules() != null) ? cycle.getRequirementRules().size() : 0);

		cycleSummary.setTotalEnvironments(getCascadedEnvironmentsCount(cycleID));		
		cycleSummary.setTotalRequirements(getCascadedRequirementsCount(cycleID));	
		
		cycleSummary.setTotalTesters(getCascadedTestersCount(cycleID));
		cycleSummary.setTotalSeniorTesters(getCascadedSnrTestersCount(cycleID));
		cycleSummary.setTotalDevelopers(getCascadedDevelopersCount(cycleID));	
		cycleSummary.setTotalSeniorDevelopers(getCascadedSnrDevelopersCount(cycleID));

		cycleSummary.setProjectPosition(cycle.getProjectPosition());		

		cycleSummary.setLastModifiedBy(cycle.getLastModifiedBy());
		cycleSummary.setLastModifiedDate(cycle.getLastModifiedDate());
		cycleSummary.setCreatedBy(cycle.getCreatedBy());
		cycleSummary.setCreationDate(cycle.getCreationDate());

		return cycleSummary;
	}
	public ColModelAndNames getColumnModelAndNames(Long companyID)
	{
		Company company = companyService.getCompany(companyID);
		Collection<String> colNames = new ArrayList<String>();
		ColModelAndNames colModelAndName = new ColModelAndNames();
		Set<GridAttributes> columnModelSet =  new HashSet<GridAttributes>();	

		colNames.add("ID");
		columnModelSet.add(new GridAttributes("cycleID",10));	

		colNames.add(company.getCycleDisplayName()+ " Name");
		columnModelSet.add(new GridAttributes("cycleName",40));	 

		colNames.add("Required Priority");
		columnModelSet.add(new GridAttributes("requiredPriority",10));	 	

		colNames.add("State");
		columnModelSet.add(new GridAttributes("cycleState","setCycleStateBarChart","unSetBarChart", 80));

		colNames.add("Required "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalRequiredTestruns",10,true));	

		colNames.add("All "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalAllTestruns",10,true));		

		colNames.add("Complete");
		columnModelSet.add(new GridAttributes("totalTestrunsCompleted",10,true));
		colNames.add("Passed");
		columnModelSet.add(new GridAttributes("totalTestrunsPassed",10,true));
		colNames.add("Failed");
		columnModelSet.add(new GridAttributes("totalTestrunsFailed",10,true));	
		colNames.add("Deferred");
		columnModelSet.add(new GridAttributes("totalTestrunsDeferred",10,true));
		colNames.add("Blocked");
		columnModelSet.add(new GridAttributes("totalTestrunsBlocked",10,true));
		colNames.add("Not Complete");
		columnModelSet.add(new GridAttributes("totalTestrunsNotCompleted",10,true));
		colNames.add("Not Run");
		columnModelSet.add(new GridAttributes("totalTestrunsNotRun",10,true));
		colNames.add("In Prog");
		columnModelSet.add(new GridAttributes("totalTestrunsInProg",10,true));

		colNames.add("Est.Time");
		columnModelSet.add(new GridAttributes("totalCycleEstTime",15,true));

		colNames.add("Start Date");
		columnModelSet.add(new GridAttributes("cycleStartDate",20,true));

		colNames.add("End Date");
		columnModelSet.add(new GridAttributes("cycleEndDate",20,true));	

		colNames.add("Parent");
		columnModelSet.add(new GridAttributes("parentCycleName",25,true));

		colNames.add("Child "+ company.getCyclesDisplayName());	
		columnModelSet.add(new GridAttributes("totalChildCycles",15,true));	

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
		colNames.add("Project ID");
		columnModelSet.add(new GridAttributes("projectID",true));
		colNames.add("Project Name");
		columnModelSet.add(new GridAttributes("projectName",true));

		colNames.add(company.getDefectDisplayName()+" Rules");
		columnModelSet.add(new GridAttributes("totalDefectRules",true));
		colNames.add("Test History Rules");
		columnModelSet.add(new GridAttributes("totalTestHistoryRules",true));
		colNames.add("Code Impact Rules");
		columnModelSet.add(new GridAttributes("totalCodeImpactRules",true));
		colNames.add(company.getRequirementDisplayName()+" Rules");
		columnModelSet.add(new GridAttributes("totalReqRules",true));	


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
		columnModelSet.add(new GridAttributes("projectPosition",true));	 

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
	/**
	 * Returns a Total collection of child Cycles including the parent
	 * Set<Cycle>
	 * @return Total collection of child Cycles including the parent
	 */
	public Set<Cycle> getParentAndChildCycles(long cycleID)
	{ 
		Cycle cycle = getCycle(cycleID);
		Set<Cycle> cycles = new HashSet<Cycle>(); 
		cycles.add(cycle);
		if(cycle.isParent())
		{   	
			try{
				Set<Cycle> childcyles = getAllChildCycles(cycleID);
				if(childcyles != null && !childcyles.isEmpty())
				{
					cycles.addAll(childcyles);				
				}						
			}
			catch(NoResultException nre)			
			{			
			}			
		}
		return cycles;	
	}
	/**
	 * Returns a collection of Child cycles for a cycle 
	 * Set<Cycle>
	 * @return collection of Child cycles for a cycle
	 */	
	public Set<Cycle> getChildCycles(long cycleID)
	{		
		Cycle cycle = getCycle(cycleID);
		if(!cycle.isParent())
		{   			 
			return null;
		}
		try{
			Set<Cycle> childCycles = getAllChildCycles(cycleID);
			if(childCycles == null || childCycles.isEmpty())
			{
				return null;
			}
			return childCycles;			
		}
		catch(NoResultException nre)			
		{
			return null;
		}
	} 

	/**
	 * Returns a Cycle Parent Cycle  
	 * Cycle
	 * @return a Cycle Parent Cycle, otherwise null
	 */	
	public Cycle getParentCycle(long cycleID)
	{		
		Cycle cycle = getCycle(cycleID);
		if(!cycle.isChild())
		{   			 
			return null;
		}
		try{
			Cycle parentCycle = getCycle(cycle.getParentID());
			if(parentCycle == null)
			{
				return null;
			}
			return parentCycle;			
		}
		catch(NoResultException nre)			
		{
			return null;
		}
	} 	
	/**
	 * Returns a collection of All Testruns in a cycle incl all child cycles 
	 * Set<Testrun>
	 * @return collection of All Testruns in a cycle incl all child cycles,
	 */	
	public Set<Testrun> getCascadedAllTestRuns(long cycleID)
	{
		Set<Cycle> cycles = getParentAndChildCycles(cycleID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testrun> allTestruns = new HashSet<Testrun>();
		for(final Cycle cycle : cycles)
		{		
			if(cycle.getTestruns() != null && !cycle.getTestruns().isEmpty())
			{
				allTestruns.addAll(cycle.getTestruns());	
			}  			
		}
		return allTestruns;		
	}
	public int getCascadedAllTestRunsCount(long cycleID,String level)
	{	
		if(getCascadedAllTestRuns(cycleID,level) == null)
		{
			return 0;	
		}
		return getCascadedAllTestRuns(cycleID,level).size();		
	}	
	public Set<Testrun> getCascadedAllTestRuns(long cycleID, String level)
	{
		Set<Cycle> cycles = getParentAndChildCycles(cycleID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testrun> allTestruns = new HashSet<Testrun>();
		for(final Cycle cycle : cycles)
		{		
			if(cycle.getTestruns() != null && !cycle.getTestruns().isEmpty())
			{	
				if(level == null || level.equalsIgnoreCase("all"))
				{
					allTestruns.addAll(cycle.getTestruns());
				}
				else
				{
					for(final Testrun testrun : cycle.getTestruns())
					{
						if(testrun.getLevel().equalsIgnoreCase(level))
						{
							allTestruns.add(testrun);
						}						
					}
				}

			}  			
		}
		return allTestruns;		
	}
	public int getCascadedCompulsoryTestRunsCount(long cycleID)
	{	
		if(getCascadedCompulsoryTestRuns(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestRuns(cycleID).size();		
	}	
	public Set<Testrun> getCascadedCompulsoryTestRuns(long cycleID)
	{
		Set<Testrun> allTestruns = getCascadedAllTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> compulsoryTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.isRequired(testrun.getTestrunID()))
			{
				compulsoryTestruns.add(testrun);
			}
		}		
		return compulsoryTestruns;		
	}
	public int getCascadedCompulsoryTestRunsCount(long cycleID,String level)
	{	
		if(getCascadedCompulsoryTestRuns(cycleID,level) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestRuns(cycleID,level).size();		
	}	
	public Set<Testrun> getCascadedCompulsoryTestRuns(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedAllTestRuns(cycleID,level);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> compulsoryTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.isRequired(testrun.getTestrunID()))
			{
				compulsoryTestruns.add(testrun);
			}
		}		
		return compulsoryTestruns;		
	}

	public int getCascadedOptionalTestRunsCount(long cycleID)
	{	
		if(getCascadedOptionalTestRuns(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestRuns(cycleID).size();		
	}	
	public Set<Testrun> getCascadedOptionalTestRuns(long cycleID)
	{
		Set<Testrun> allTestruns = getCascadedAllTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> optionalTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(!testrunService.isRequired(testrun.getTestrunID()))
			{
				optionalTestruns.add(testrun);
			}
		}		
		return optionalTestruns;		
	}
	public int getCascadedOptionalTestRunsCount(long cycleID,String level)
	{	
		if(getCascadedOptionalTestRuns(cycleID,level) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestRuns(cycleID,level).size();		
	}	
	public Set<Testrun> getCascadedOptionalTestRuns(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedAllTestRuns(cycleID, level);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> optionalTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(!testrunService.isRequired(testrun.getTestrunID()))
			{
				optionalTestruns.add(testrun);
			}
		}		
		return optionalTestruns;		
	}
	
	
	
	public int getCascadedAllTestCasesCount(long cycleID, String level)
	{	
		if(getCascadedAllTestCases(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestCases(cycleID).size();		
	}		
	public Set<Testcase> getCascadedAllTestCases(long cycleID)
	{
		Set<Testrun> allTestruns = getCascadedAllTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Testcase> allTestcases = new HashSet<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}
	public int getCascadedCompulsoryTestCasesCount(long cycleID, String level)
	{	
		if(getCascadedCompulsoryTestCases(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestCases(cycleID).size();		
	}	
	public Set<Testcase> getCascadedCompulsoryTestCases(long cycleID)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Testcase> allTestcases = new HashSet<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}
	public int getCascadedOptionalTestCasesCount(long cycleID, String level)
	{	
		if(getCascadedOptionalTestCases(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestCases(cycleID).size();		
	}	
	public Set<Testcase> getCascadedOptionalTestCases(long cycleID)
	{
		Set<Testrun> allTestruns = getCascadedOptionalTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Testcase> allTestcases = new HashSet<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}
	public int getCascadedAllTestPlansCount(long cycleID, String level)
	{	
		if(getCascadedAllTestPlans(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestPlans(cycleID).size();		
	}	
	public Set<Testplan> getCascadedAllTestPlans(long cycleID)
	{		
		Set<Testcase> allTestcases = getCascadedAllTestCases(cycleID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Set<Testplan> allTestplans = new HashSet<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}
	public int getCascadedCompulsoryTestPlansCount(long cycleID, String level)
	{	
		if(getCascadedCompulsoryTestPlans(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestPlans(cycleID).size();		
	}
	public Set<Testplan> getCascadedCompulsoryTestPlans(long cycleID)
	{		
		Set<Testcase> allTestcases = getCascadedCompulsoryTestCases(cycleID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Set<Testplan> allTestplans = new HashSet<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}
	public int getCascadedOptionalTestPlansCount(long cycleID, String level)
	{	
		if(getCascadedOptionalTestPlans(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestPlans(cycleID).size();		
	}
	public Set<Testplan> getCascadedOptionalTestPlans(long cycleID)
	{		
		Set<Testcase> allTestcases = getCascadedOptionalTestCases(cycleID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Set<Testplan> allTestplans = new HashSet<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}	
	public int getCascadedDefectsCount(long cycleID)
	{	
		if(getCascadedDefects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedDefects(cycleID).size();		
	}
	public Set<Defect> getCascadedDefects(long cycleID)
	{				
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();  
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getCascadedAllDefects(testrun.getTestrunID()) != null &&
					!testrunService.getCascadedAllDefects(testrun.getTestrunID()).isEmpty())
			{
				defects.addAll(testrunService.getCascadedAllDefects(testrun.getTestrunID()));
			}					
		}		
		return defects;					
	}
	public int getCascadedSev1DefectsCount(long cycleID)
	{	
		if(getCascadedSev1Defects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSev1Defects(cycleID).size();		
	}		
	public Set<Defect> getCascadedSev1Defects(long cycleID) 
	{		
		Set<Defect> allDefects = getCascadedDefects(cycleID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Set<Defect> sev1defects = new HashSet<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev1(defect.getDefectID()))
			{
				sev1defects.add(defect);						
			}
		}	
		return sev1defects;
	}
	public int getCascadedSev2DefectsCount(long cycleID)
	{	
		if(getCascadedSev2Defects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSev2Defects(cycleID).size();		
	}	
	public Set<Defect> getCascadedSev2Defects(long cycleID) 
	{		
		Set<Defect> allDefects = getCascadedDefects(cycleID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Set<Defect> sev2defects = new HashSet<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev2(defect.getDefectID()))
			{
				sev2defects.add(defect);						
			}
		}	
		return sev2defects;
	}
	public int getCascadedSev3DefectsCount(long cycleID)
	{	
		if(getCascadedSev3Defects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSev3Defects(cycleID).size();		
	}		
	public Set<Defect> getCascadedSev3Defects(long cycleID) 
	{		
		Set<Defect> allDefects = getCascadedDefects(cycleID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Set<Defect> sev3defects = new HashSet<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev3(defect.getDefectID()))
			{
				sev3defects.add(defect);						
			}
		}	
		return sev3defects;
	}
	public int getCascadedSev4DefectsCount(long cycleID)
	{	
		if(getCascadedSev4Defects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSev4Defects(cycleID).size();		
	}	
	public Set<Defect> getCascadedSev4Defects(long cycleID) 
	{		
		Set<Defect> allDefects = getCascadedDefects(cycleID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Set<Defect> sev4defects = new HashSet<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev4(defect.getDefectID()))
			{
				sev4defects.add(defect);						
			}
		}	
		return sev4defects;
	}
	public int getTestRunsPassedCount(long cycleID,String level)
	{	
		if(getTestRunsPassed(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsPassed(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsPassed(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> passedTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.isPassed())
			{
				passedTestruns.add(testrun);
			}
		}		
		return passedTestruns;		
	}
	public int getTestRunsFailedCount(long cycleID,String level)
	{	
		if(getTestRunsFailed(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsFailed(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsFailed(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> failedTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.isFailed())
			{
				failedTestruns.add(testrun);
			}
		}		
		return failedTestruns;		
	}
	public int getTestRunsDeferredCount(long cycleID,String level)
	{	
		if(getTestRunsDeferred(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsDeferred(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsDeferred(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> deferredTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.isDeferred())
			{
				deferredTestruns.add(testrun);
			}
		}		
		return deferredTestruns;		
	}
	public int getTestRunsBlockedCount(long cycleID,String level)
	{	
		if(getTestRunsBlocked(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsBlocked(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsBlocked(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> blockedTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.isBlocked())
			{
				blockedTestruns.add(testrun);
			}
		}		
		return blockedTestruns;		
	}
	public int getTestRunsNotrunCount(long cycleID,String level)
	{	
		if(getTestRunsNotrun(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsNotrun(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsNotrun(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> notrunTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.isNotrun())
			{
				notrunTestruns.add(testrun);
			}
		}		
		return notrunTestruns;		
	}
	public int getTestRunsInprogressCount(long cycleID,String level)
	{	
		if(getTestRunsInprogress(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsInprogress(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsInprogress(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> inprogressTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.isInprogress())
			{
				inprogressTestruns.add(testrun);
			}
		}		
		return inprogressTestruns;		
	}
	public int getTestRunsCompletedCount(long cycleID,String level)
	{	
		if(getTestRunsCompleted(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsCompleted(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsCompleted(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> completedTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(!testrun.isInprogress() || !testrun.isNotrun())
			{
				completedTestruns.add(testrun);
			}
		}		
		return completedTestruns;		
	}
	public int getTestRunsInCompletedCount(long cycleID,String level)
	{	
		if(getTestRunsInCompleted(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsInCompleted(cycleID,level).size();		
	}	
	
	public Set<Testrun> getTestRunsInCompleted(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> incompletedTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.isInprogress() || testrun.isNotrun())
			{
				incompletedTestruns.add(testrun);
			}
		}		
		return incompletedTestruns;		
	}
		
	public Double getTotalTestRunsEstTime(long cycleID,String level)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		Double totalEstTime = 0.00;
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return totalEstTime;
		}			
		
		for(final Testrun testrun : allTestruns)
		{	
			
			if(testrun.getEstimatedTime() != null)
			{
				totalEstTime = totalEstTime + testrun.getEstimatedTime();				
			}
		}		
		return totalEstTime;		
	}
	public int getCascadedEnvironmentsCount(long cycleID)
	{	
		if(getCascadedEnvironments(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedEnvironments(cycleID).size();		
	}
	public Set<Environment> getCascadedEnvironments(long cycleID) 
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Environment> environements = new HashSet<Environment>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getEnvironments() != null && !testrun.getEnvironments().isEmpty())
			{
				environements.addAll(testrun.getEnvironments());
			}
		}
		return environements;		
	}
	
	public int getCascadedRequirementsCount(long cycleID)
	{	
		if(getCascadedRequirements(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedRequirements(cycleID).size();		
	}		
	public Set<Requirement> getCascadedRequirements(long cycleID) 
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(cycleID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Requirement> requirements = new HashSet<Requirement>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getRequirements() != null && !testrun.getRequirements().isEmpty())
			{
				requirements.addAll(testrun.getRequirements());
			}
		}
		return requirements;		
	}

	public int getCascadedTestersCount(long cycleID)
	{	
		if(getCascadedTesters(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedTesters(cycleID).size();		
	}
	public Set<TestcenterUser> getCascadedTesters(long cycleID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedSnrTestersCount(long cycleID)
	{	
		if(getCascadedSnrTesters(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSnrTesters(cycleID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrTesters(long cycleID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCascadedDevelopersCount(long cycleID)
	{	
		if(getCascadedDevelopers(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedDevelopers(cycleID).size();		
	}	
	public Set<TestcenterUser> getCascadedDevelopers(long cycleID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCascadedSnrDevelopersCount(long cycleID)
	{	
		if(getCascadedSnrDevelopers(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSnrDevelopers(cycleID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrDevelopers(long cycleID) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public RelatedObjectList getRelatedObjects(long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID) {
		// TODO Auto-generated method stub
		return null;
	}
}