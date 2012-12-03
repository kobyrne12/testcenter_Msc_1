/**
 * 
 */
package ie.cit.cloud.testcenter.service.defect;

/**
 * @author byrnek1
 *
 */

import java.util.HashSet;
import java.util.Set;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.defect.DefectRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import javax.persistence.NoResultException;
import javax.persistence.Transient;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefectServiceImpl implements DefectService {

	@Autowired
	@Qualifier("hibernateDefectRespository")
	DefectRepository defectRepo;      

	@Autowired
	CompanyService companyService;
	@Autowired
	ProjectService projectService;
	@Autowired
	CycleService cycleService;
	@Autowired
	TestplanService testplanService;	
	@Autowired
	TestcaseService testcaseService;	
	@Autowired
	TestrunService testrunService;	
	@Autowired
	EnvironmentService environmentService;	
	@Autowired
	RequirementService requirementService;	


	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Defect getDefect(Long defectID) {
		return defectRepo.findById(defectID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Defect getDefectByName(String defectName) {
		return defectRepo.findByName(defectName);
	}
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Defect> getAllChildDefects(Long defectID) {
		return defectRepo.findAllDefectsByParentID(defectID);
	}
	
	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewDefect(Defect defect) {
		defectRepo.create(defect);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Defect defect) {
		defectRepo.update(defect);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(Long defectID) {
		defectRepo.delete(getDefect(defectID));
	}

	public boolean isSev1(Long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity() == 1)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev2(Long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity()  == 2)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev3(Long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity()  == 3)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev4(Long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity() == 4)
		{
			return true;
		}
		return false;				
	}
	/////////////////////////
		
	public Set<Defect> getParentAndChildDefects(Long defectID)
	{		
		Defect defect = getDefect(defectID);
		Set<Defect> defects = new HashSet<Defect>(); 
		defects.add(defect);
		if(defect.isParent())
		{   	
			try{
				Set<Defect> childDefects = getAllChildDefects(defectID);
				if(childDefects != null && !childDefects.isEmpty())
				{
					defects.addAll(childDefects);				
				}						
			}
			catch(NoResultException nre)			
			{			
			}			
		}
		return defects;	
	}
	
	public Set<Defect> getChildDefects(Long defectID) {
		Defect defect = getDefect(defectID);
		if(!defect.isParent())
		{   			 
			return null;
		}
		try{
			Set<Defect> childDefects = getAllChildDefects(defectID);
			if(childDefects == null || childDefects.isEmpty())
			{
				return null;
			}
			return childDefects;			
		}
		catch(NoResultException nre)			
		{
			return null;
		}
	}

	public Defect getParentDefect(Long defectID) {
		Defect defect = getDefect(defectID);
		if(!defect.isChild())
		{   			 
			return null;
		}
		try{
			Defect parentDefect = getDefect(defect.getParentID());
			if(parentDefect == null)
			{
				return null;
			}
			return parentDefect;			
		}
		catch(NoResultException nre)			
		{
			return null;
		}
	}

	public Set<Testrun> getCascadedAllTestRuns(Long defectID) 
	{
		Set<Defect> allDefects = getParentAndChildDefects(defectID);
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}			
		Set<Testrun> allTestruns = new HashSet<Testrun>();
		for(final Defect defect : allDefects)
		{		
			if(defect.getTestruns() != null && !defect.getTestruns().isEmpty())
			{
				allTestruns.addAll(defect.getTestruns());
			}
			if(defect.getRequirements() != null && !defect.getRequirements().isEmpty())
			{
				for(final Requirement requirement : defect.getRequirements())
				{
					if(requirement.getTestruns() != null && !requirement.getTestruns().isEmpty())
					{
						allTestruns.addAll(requirement.getTestruns());
					}
				}				
			}
		}		
		return allTestruns;		
	} 
	
	public Set<Testrun> getCascadedCompulsoryTestRuns(Long defectID)
	{
		Set<Testrun> allTestruns = getCascadedAllTestRuns(defectID);
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
	
	public Set<Testrun> getCascadedOptionalTestRuns(Long defectID)
	{
		Set<Testrun> allTestruns = getCascadedAllTestRuns(defectID);
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
	
	public Set<Testcase> getCascadedAllTestCases(Long defectID)
	{
		Set<Testrun> allTestruns = getCascadedAllTestRuns(defectID);
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
	
	public Set<Testcase> getCascadedCompulsoryTestCases(Long defectID)
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(defectID);
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
	
	public Set<Testcase> getCascadedOptionalTestCases(Long defectID)
	{
		Set<Testrun> allTestruns = getCascadedOptionalTestRuns(defectID);
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
	
	public Set<Testplan> getCascadedAllTestPlans(Long defectID)
	{		
		Set<Testcase> allTestcases = getCascadedAllTestCases(defectID);
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
	
	public Set<Testplan> getCascadedCompulsoryTestPlans(Long defectID)
	{		
		Set<Testcase> allTestcases = getCascadedCompulsoryTestCases(defectID);
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
	
	public Set<Testplan> getCascadedOptionalTestPlans(Long defectID)
	{		
		Set<Testcase> allTestcases = getCascadedOptionalTestCases(defectID);
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
	
	public Set<Cycle> getCascadedCycles(Long defectID) {
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Cycle> cycles = new HashSet<Cycle>();
		for(final Testrun testrun : allTestruns)
		{				
			if(testrunService.getCycle(testrun.getTestrunID()) != null)
			{
				cycles.add(testrunService.getCycle(testrun.getTestrunID()));
			}				
		}		
		return cycles;
	}

	public Set<Project> getCascadedProjects(Long defectID)
	{
		Set<Cycle> cycles =  getCascadedCycles(defectID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}	
		Set<Project> projects = new HashSet<Project>();
		for(final Cycle cycle : cycles)
		{	
			try{
				projects.add(projectService.getProject(cycle.getProjectID()));
			}catch(NoResultException nre)
			{}				
		}		
		return projects;
	}

	
	public Set<Environment> getCascadedEnvironments(Long defectID) {
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Environment> environments = new HashSet<Environment>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getEnvironments() != null && !testrun.getEnvironments().isEmpty())			
			{
				environments.addAll(testrun.getEnvironments());
			}			
		}		
		return environments;
	}

	public Set<Requirement> getCascadedRequirements(Long defectID) 
	{
		Set<Testrun> allTestruns = getCascadedCompulsoryTestRuns(defectID);
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
		Defect defect = getDefect(defectID);
		if(defect.getRequirements() == null || defect.getRequirements().isEmpty())
		{		
			requirements.addAll(defect.getRequirements());			
		}
		return requirements;		
	}

	public Set<TestcenterUser> getCascadedTesters(Long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(Long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(Long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(Long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

}