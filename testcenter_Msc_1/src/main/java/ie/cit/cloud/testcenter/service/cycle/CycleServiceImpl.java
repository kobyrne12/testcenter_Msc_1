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
import java.util.LinkedHashSet;
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
	public Cycle getCycle(Long cycleID) {
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
	public Long addNewCycle(Cycle cycle) {
		cycleRepo.create(cycle);	
		return cycle.getCycleID();
	}

	// @Secured("ROLE_ADMIN")
	public void update(Cycle cycle) {
		cycleRepo.update(cycle);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(Long cycleID) {
		cycleRepo.delete(cycleRepo.get(cycleID));
	}


	public boolean updateCycle(Cycle cycle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Cycle> getAllCyclesByProjectID(Long projectID) {
		return cycleRepo.findAllCyclesByProjectID(projectID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Cycle> getAllCyclesByCompanyID(Long companyID) {
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
	public Set<Cycle> getAllChildCycles(Long cycleID) {
		return cycleRepo.findAllCyclesByParentID(cycleID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public int getMaxProjectPosNum(Long projectID) {		
		return cycleRepo.getMaxProjectPosNum(projectID);
	}			

	/**
	 * Returns true if this cycle is the latest cycle for a project 
	 * boolean
	 * @return true if this cycle is the latest cycle for a project, otherwise false
	 */	
	public boolean isLatest(Long cycleID)
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

	public String getParentCycleName(Long cycleID)
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


	

	/**
	 * Returns a Total collection of child Cycles including the parent
	 * Set<Cycle>
	 * @return Total collection of child Cycles including the parent
	 */
	public Set<Cycle> getParentAndChildCycles(Long cycleID)
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
	public Set<Cycle> getChildCycles(Long cycleID)
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
	public Cycle getParentCycle(Long cycleID)
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
	public Set<Testrun> getCascadedAllTestRuns(Long cycleID)
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
	public int getCascadedAllTestRunsCount(Long cycleID,String level)
	{	
		if(getCascadedAllTestRuns(cycleID,level) == null)
		{
			return 0;	
		}
		return getCascadedAllTestRuns(cycleID,level).size();		
	}	
	public Set<Testrun> getCascadedAllTestRuns(Long cycleID, String level)
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
						allTestruns.add(testrun);
												
					}
				}
			}  			
		}
		return allTestruns;		
	}
	
	public int getCascadedCompulsoryTestRunsCount(Long cycleID)
	{	
		if(getCascadedCompulsoryTestRuns(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestRuns(cycleID).size();		
	}	
	
	public Set<Testrun> getCascadedCompulsoryTestRuns(Long cycleID)
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
	
	public int getCascadedCompulsoryTestRunsCount(Long cycleID,String level)
	{	
		if(getCascadedCompulsoryTestRuns(cycleID,level) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestRuns(cycleID,level).size();		
	}
	
	public Set<Testrun> getCascadedCompulsoryTestRuns(Long cycleID,String level)
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

	public int getCascadedOptionalTestRunsCount(Long cycleID)
	{	
		if(getCascadedOptionalTestRuns(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestRuns(cycleID).size();		
	}	
	
	public Set<Testrun> getCascadedOptionalTestRuns(Long cycleID)
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
	public int getCascadedOptionalTestRunsCount(Long cycleID,String level)
	{	
		if(getCascadedOptionalTestRuns(cycleID,level) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestRuns(cycleID,level).size();		
	}	
	public Set<Testrun> getCascadedOptionalTestRuns(Long cycleID,String level)
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
	
	
	public Set<Testcase> getAvailTestcases(Long cycleID) 
	{
		Cycle cycle = getCycle(cycleID);
		Set<Testcase> projectTestcases = projectService.getCascadedAllTestCases(cycle.getProjectID());
		Set<Testcase> availTestcases = new LinkedHashSet<Testcase>();
		if(projectTestcases == null || projectTestcases.isEmpty())
		{
			return null;
		}		
		for(final Testcase testcase : projectTestcases)
		{
			boolean testcaseHasATestRun = false;
			Set<Cycle> testcaseCycles = testcaseService.getAllCycles(testcase.getTestcaseID());
			if(testcaseCycles != null && !testcaseCycles.isEmpty())
			{
				for(final Cycle testcasecycle : testcaseCycles)
				{
					if(testcasecycle.getCycleID() == cycleID)
					{
						testcaseHasATestRun = true;
					}
				}
			}
			if(!testcaseHasATestRun)
			{
				availTestcases.add(testcase);
			}
		}
		return availTestcases;		
	}

	public int getCascadedAllTestCasesCount(Long cycleID, String level)
	{	
		if(getCascadedAllTestCases(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestCases(cycleID).size();		
	}		
	public Set<Testcase> getCascadedAllTestCases(Long cycleID)
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
	public int getCascadedCompulsoryTestCasesCount(Long cycleID, String level)
	{	
		if(getCascadedCompulsoryTestCases(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestCases(cycleID).size();		
	}	
	public Set<Testcase> getCascadedCompulsoryTestCases(Long cycleID)
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
	public int getCascadedOptionalTestCasesCount(Long cycleID, String level)
	{	
		if(getCascadedOptionalTestCases(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestCases(cycleID).size();		
	}	
	public Set<Testcase> getCascadedOptionalTestCases(Long cycleID)
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
	public int getCascadedAllTestPlansCount(Long cycleID, String level)
	{	
		if(getCascadedAllTestPlans(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedAllTestPlans(cycleID).size();		
	}	
	public Set<Testplan> getCascadedAllTestPlans(Long cycleID)
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
	public int getCascadedCompulsoryTestPlansCount(Long cycleID, String level)
	{	
		if(getCascadedCompulsoryTestPlans(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedCompulsoryTestPlans(cycleID).size();		
	}
	public Set<Testplan> getCascadedCompulsoryTestPlans(Long cycleID)
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
	public int getCascadedOptionalTestPlansCount(Long cycleID, String level)
	{	
		if(getCascadedOptionalTestPlans(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedOptionalTestPlans(cycleID).size();		
	}
	public Set<Testplan> getCascadedOptionalTestPlans(Long cycleID)
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
	public int getCascadedDefectsCount(Long cycleID)
	{	
		if(getCascadedDefects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedDefects(cycleID).size();		
	}
	public Set<Defect> getCascadedDefects(Long cycleID)
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
	public int getCascadedSev1DefectsCount(Long cycleID)
	{	
		if(getCascadedSev1Defects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSev1Defects(cycleID).size();		
	}		
	public Set<Defect> getCascadedSev1Defects(Long cycleID) 
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
	public int getCascadedSev2DefectsCount(Long cycleID)
	{	
		if(getCascadedSev2Defects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSev2Defects(cycleID).size();		
	}	
	public Set<Defect> getCascadedSev2Defects(Long cycleID) 
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
	public int getCascadedSev3DefectsCount(Long cycleID)
	{	
		if(getCascadedSev3Defects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSev3Defects(cycleID).size();		
	}		
	public Set<Defect> getCascadedSev3Defects(Long cycleID) 
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
	public int getCascadedSev4DefectsCount(Long cycleID)
	{	
		if(getCascadedSev4Defects(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSev4Defects(cycleID).size();		
	}	
	public Set<Defect> getCascadedSev4Defects(Long cycleID) 
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
	public int getTestRunsPassedCount(Long cycleID,String level)
	{	
		if(getTestRunsPassed(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsPassed(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsPassed(Long cycleID,String level)
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
	public int getTestRunsFailedCount(Long cycleID,String level)
	{	
		if(getTestRunsFailed(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsFailed(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsFailed(Long cycleID,String level)
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
	public int getTestRunsDeferredCount(Long cycleID,String level)
	{	
		if(getTestRunsDeferred(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsDeferred(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsDeferred(Long cycleID,String level)
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
	public int getTestRunsBlockedCount(Long cycleID,String level)
	{	
		if(getTestRunsBlocked(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsBlocked(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsBlocked(Long cycleID,String level)
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
	public int getTestRunsNotrunCount(Long cycleID,String level)
	{	
		if(getTestRunsNotrun(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsNotrun(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsNotrun(Long cycleID,String level)
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
	public int getTestRunsInprogressCount(Long cycleID,String level)
	{	
		if(getTestRunsInprogress(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsInprogress(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsInprogress(Long cycleID,String level)
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
	public int getTestRunsCompletedCount(Long cycleID,String level)
	{	
		if(getTestRunsCompleted(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsCompleted(cycleID,level).size();		
	}	
	public Set<Testrun> getTestRunsCompleted(Long cycleID,String level)
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
	public int getTestRunsInCompletedCount(Long cycleID,String level)
	{	
		if(getTestRunsInCompleted(cycleID,level) == null)
		{
			return 0;	
		}
		return getTestRunsInCompleted(cycleID,level).size();		
	}	
	
	public Set<Testrun> getTestRunsInCompleted(Long cycleID,String level)
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
		
	public Double getTotalTestRunsEstTime(Long cycleID,String level)
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
	public int getCascadedEnvironmentsCount(Long cycleID)
	{	
		if(getCascadedEnvironments(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedEnvironments(cycleID).size();		
	}
	public Set<Environment> getCascadedEnvironments(Long cycleID) 
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
	
	public int getCascadedRequirementsCount(Long cycleID)
	{	
		if(getCascadedRequirements(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedRequirements(cycleID).size();		
	}		
	public Set<Requirement> getCascadedRequirements(Long cycleID) 
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

	public int getCascadedTestersCount(Long cycleID)
	{	
		if(getCascadedTesters(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedTesters(cycleID).size();		
	}
	public Set<TestcenterUser> getCascadedTesters(Long cycleID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedSnrTestersCount(Long cycleID)
	{	
		if(getCascadedSnrTesters(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSnrTesters(cycleID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrTesters(Long cycleID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCascadedDevelopersCount(Long cycleID)
	{	
		if(getCascadedDevelopers(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedDevelopers(cycleID).size();		
	}	
	public Set<TestcenterUser> getCascadedDevelopers(Long cycleID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCascadedSnrDevelopersCount(Long cycleID)
	{	
		if(getCascadedSnrDevelopers(cycleID) == null)
		{
			return 0;	
		}
		return getCascadedSnrDevelopers(cycleID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrDevelopers(Long cycleID) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public RelatedObjectList getRelatedObjects(Long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	public ColModelAndNames getColumnModelAndNames(Long companyID)
	{
		Company company = companyService.getCompany(companyID);
		Collection<String> colNames = new ArrayList<String>();
		ColModelAndNames colModelAndName = new ColModelAndNames();
		Collection<GridAttributes> columnModelSet =  new ArrayList<GridAttributes>();	

		colNames.add("ID");
		columnModelSet.add(new GridAttributes("cycleID",10));	

		colNames.add(company.getCycleDisplayName()+ " Name");
		columnModelSet.add(new GridAttributes("cycleName",40));	 
		
		colNames.add("State");
		columnModelSet.add(new GridAttributes("cycleState","setCycleStateBarChart","unSetBarChart", 80));
		
		colNames.add("Parent");
		columnModelSet.add(new GridAttributes("parentCycle",10));
		colNames.add("Child "+ company.getCyclesDisplayName());	
		columnModelSet.add(new GridAttributes("totalChildCycles",15,true));			
		colNames.add("Parent "+ company.getCyclesDisplayName());	
		columnModelSet.add(new GridAttributes("parentCycleName",15,true));	

		colNames.add("Complete");
		columnModelSet.add(new GridAttributes("totalTestrunsCompleted",10));
		colNames.add("Passed");
		columnModelSet.add(new GridAttributes("totalTestrunsPassed",10));
		colNames.add("Failed");
		columnModelSet.add(new GridAttributes("totalTestrunsFailed",10));	
		colNames.add("Deferred");
		columnModelSet.add(new GridAttributes("totalTestrunsDeferred",10));
		colNames.add("Blocked");
		columnModelSet.add(new GridAttributes("totalTestrunsBlocked",10));
		colNames.add("Incomplete");
		columnModelSet.add(new GridAttributes("totalTestrunsInComplete",10));
		colNames.add("Not Run");
		columnModelSet.add(new GridAttributes("totalTestrunsNotRun",10));
		colNames.add("In Prog");
		columnModelSet.add(new GridAttributes("totalTestrunsInProg",10));
		
		colNames.add("Req. Priority");
		columnModelSet.add(new GridAttributes("requiredPriority",10,true));
		
		colNames.add(company.getProjectDisplayName() +" Req. %");
		columnModelSet.add(new GridAttributes("requiredPercent",10,true));
		
		colNames.add(company.getDefectsDisplayName());
		columnModelSet.add(new GridAttributes("totalDefects"));		
		
		colNames.add("Sev 1s");
		columnModelSet.add(new GridAttributes("totalCurrentSev1s",true));
		colNames.add("Sev 2s");
		columnModelSet.add(new GridAttributes("totalCurrentSev2s",true));	
		colNames.add("Sev 3s");
		columnModelSet.add(new GridAttributes("totalCurrentSev3s",true));
		colNames.add("Sev 4s");
		columnModelSet.add(new GridAttributes("totalCurrentSev4s",true));
		
		colNames.add("Max Sev 1s");
		columnModelSet.add(new GridAttributes("totalAllowedSev1s",true));
		colNames.add("Max Sev 2s");
		columnModelSet.add(new GridAttributes("totalAllowedSev2s",true));
		colNames.add("Max Sev 3s");
		columnModelSet.add(new GridAttributes("totalAllowedSev3s",true));
		colNames.add("Max Sev 4s");
		columnModelSet.add(new GridAttributes("totalAllowedSev4s",true));		

		
		colNames.add("Req. "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalRequiredTestruns",10,true));
		colNames.add("Optional "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalOptionalTestruns",10,true));	
		colNames.add("All "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalAllTestruns",10,true));	

		colNames.add("Est.Time");
		columnModelSet.add(new GridAttributes("totalCycleEstTime",15,true));
		colNames.add("Start Date");
		columnModelSet.add(new GridAttributes("cycleStartDate",20,true));
		colNames.add("End Date");
		columnModelSet.add(new GridAttributes("cycleEndDate",20,true));	

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
	
		colNames.add("Project ID");
		columnModelSet.add(new GridAttributes("projectID",true));
		colNames.add("Project Name");
		columnModelSet.add(new GridAttributes("projectName",true));
		colNames.add("Company ID");
		columnModelSet.add(new GridAttributes("companyID",true));

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
	public CycleSummaryList getGridCycles(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID, String levelName)
	{
		// Check which projects wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Cycle> cycles = new HashSet<Cycle>();				


		if (projectID != null && !projectID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{						
			Project project = projectService.getProject(Long.valueOf(projectID));
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
			if(testplanService.getCycles(Long.valueOf(testplanID)) != null)
			{
				cycles.retainAll(testplanService.getCycles(Long.valueOf(testplanID)));
			}			
		}		
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Testcase cycles
		if (testcaseID != null && !testcaseID.isEmpty()) 
		{			
			if(testcaseService.getAllCycles(Long.valueOf(testcaseID)) != null)
			{
				cycles.retainAll(testcaseService.getAllCycles(Long.valueOf(testcaseID)));
			}			
		}		
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Testrun cycles
		if (testrunID != null && !testrunID.isEmpty()) 
		{			
			if(testrunService.getCycle(Long.valueOf(testrunID)) != null)
			{
				Set<Cycle> testrunCycles = new HashSet<Cycle>();
				testrunCycles.add(testrunService.getCycle(Long.valueOf(testrunID)));
				cycles.retainAll(testrunCycles);				
			}			
		}
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Defect cycles
		if (defectID != null && !defectID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(defectService.getCascadedCycles(Long.valueOf(defectID)) != null)
			{
				cycles.retainAll(defectService.getCascadedCycles(Long.valueOf(defectID)));				
			}			
		}
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Requirement cycles
		if (requirementID != null && !requirementID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(requirementService.getCycles(Long.valueOf(requirementID)) != null)
			{
				cycles.retainAll(requirementService.getCycles(Long.valueOf(requirementID)));				
			}				
		}
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain Environment cycles
		if (environmentID != null && !environmentID.isEmpty()) // limit to projects that have this test plan id in it
		{			
			if(environmentService.getCycles(Long.valueOf(environmentID)) != null)
			{
				cycles.retainAll(environmentService.getCycles(Long.valueOf(environmentID)));				
			}			
		}
		if(cycles == null || cycles.isEmpty()){return null;}

		// Retain User cycles
		//		if (userID != null && !userID.isEmpty()) // limit to projects that have this test plan id in it
		//		{			
		//			if(userService.getCascadedProjects(Long.valueOf(defectID)) != null)
		//			{
		//				projects.retainAll(userService.getCascadedProjects(Long.valueOf(defectID)));				
		//			}			
		//		}
		//		if(projects == null || projects.isEmpty()){return null;}

		Set<CycleSummary> cycleSummarySet = new HashSet<CycleSummary>();
		CycleSummaryList cycleSummaryList = new CycleSummaryList();

		for(final Cycle cycle : cycles)
		{		
			cycleSummarySet.add(new CycleSummary(cycle, levelName,projectService, this, testrunService, defectService));
			//CycleSummary cycleSummary = getCycleSummary(companyID, cycle.getCycleID(),level);	
			//cycleSummarySet.add(cycleSummary);

		}

		cycleSummaryList.setCycles(cycleSummarySet);
		return cycleSummaryList;	

	}


}