/**
 * 
 */
package ie.cit.cloud.testcenter.service.defect;

/**
 * @author byrnek1
 *
 */

import java.util.ArrayList;
import java.util.Collection;

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
	public Defect getDefect(long defectID) {
		return defectRepo.findById(defectID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Defect getDefectByName(String defectName) {
		return defectRepo.findByName(defectName);
	}
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Defect> getAllChildDefects(long defectID) {
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
	public void remove(long defectID) {
		defectRepo.delete(getDefect(defectID));
	}

	public boolean isSev1(long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity() == 1)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev2(long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity()  == 2)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev3(long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity()  == 3)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev4(long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity() == 4)
		{
			return true;
		}
		return false;				
	}
	/////////////////////////
		
	public Collection<Defect> getParentAndChildDefects(long defectID)
	{		
		Defect defect = getDefect(defectID);
		Collection<Defect> defects = new ArrayList<Defect>(); 
		defects.add(defect);
		if(defect.isParent())
		{   	
			try{
				Collection<Defect> childDefects = getAllChildDefects(defectID);
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
	
	public Collection<Defect> getChildDefects(long defectID) {
		Defect defect = getDefect(defectID);
		if(!defect.isParent())
		{   			 
			return null;
		}
		try{
			Collection<Defect> childDefects = getAllChildDefects(defectID);
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

	public Defect getParentDefect(long defectID) {
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

	public Collection<Testrun> getCascadedAllTestRuns(long defectID) 
	{
		Collection<Defect> allDefects = getParentAndChildDefects(defectID);
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}			
		Collection<Testrun> allTestruns = new ArrayList<Testrun>();
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
	
	public Collection<Testrun> getCascadedCompulsoryTestRuns(long defectID)
	{
		Collection<Testrun> allTestruns = getCascadedAllTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Collection<Testrun> compulsoryTestruns = new ArrayList<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.isRequired(testrun.getTestrunID()))
			{
				compulsoryTestruns.add(testrun);
			}
		}		
		return compulsoryTestruns;		
	}	
	
	public Collection<Testrun> getCascadedOptionalTestRuns(long defectID)
	{
		Collection<Testrun> allTestruns = getCascadedAllTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Collection<Testrun> optionalTestruns = new ArrayList<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(!testrunService.isRequired(testrun.getTestrunID()))
			{
				optionalTestruns.add(testrun);
			}
		}		
		return optionalTestruns;		
	}
	
	public Collection<Testcase> getCascadedAllTestCases(long defectID)
	{
		Collection<Testrun> allTestruns = getCascadedAllTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Collection<Testcase> allTestcases = new ArrayList<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}
	
	public Collection<Testcase> getCascadedCompulsoryTestCases(long defectID)
	{
		Collection<Testrun> allTestruns = getCascadedCompulsoryTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Collection<Testcase> allTestcases = new ArrayList<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}
	
	public Collection<Testcase> getCascadedOptionalTestCases(long defectID)
	{
		Collection<Testrun> allTestruns = getCascadedOptionalTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Collection<Testcase> allTestcases = new ArrayList<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}
	
	public Collection<Testplan> getCascadedAllTestPlans(long defectID)
	{		
		Collection<Testcase> allTestcases = getCascadedAllTestCases(defectID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Collection<Testplan> allTestplans = new ArrayList<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}
	
	public Collection<Testplan> getCascadedCompulsoryTestPlans(long defectID)
	{		
		Collection<Testcase> allTestcases = getCascadedCompulsoryTestCases(defectID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Collection<Testplan> allTestplans = new ArrayList<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}
	
	public Collection<Testplan> getCascadedOptionalTestPlans(long defectID)
	{		
		Collection<Testcase> allTestcases = getCascadedOptionalTestCases(defectID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Collection<Testplan> allTestplans = new ArrayList<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}
	
	public Collection<Cycle> getCascadedCycles(long defectID) {
		Collection<Testrun> allTestruns = getCascadedCompulsoryTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Collection<Cycle> cycles = new ArrayList<Cycle>();
		for(final Testrun testrun : allTestruns)
		{				
			if(testrunService.getCycle(testrun.getTestrunID()) != null)
			{
				cycles.add(testrunService.getCycle(testrun.getTestrunID()));
			}				
		}		
		return cycles;
	}

	public Collection<Project> getCascadedProjects(long defectID)
	{
		Collection<Cycle> cycles =  getCascadedCycles(defectID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}	
		Collection<Project> projects = new ArrayList<Project>();
		for(final Cycle cycle : cycles)
		{	
			try{
				projects.add(projectService.getProject(cycle.getProjectID()));
			}catch(NoResultException nre)
			{}				
		}		
		return projects;
	}

	
	public Collection<Environment> getCascadedEnvironments(long defectID) {
		Collection<Testrun> allTestruns = getCascadedCompulsoryTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Collection<Environment> environments = new ArrayList<Environment>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getEnvironments() != null || !testrun.getEnvironments().isEmpty())			
			{
				environments.addAll(testrun.getEnvironments());
			}			
		}		
		return environments;
	}

	public Collection<Requirement> getCascadedRequirements(long defectID) 
	{
		Collection<Testrun> allTestruns = getCascadedCompulsoryTestRuns(defectID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getRequirements() != null || !testrun.getRequirements().isEmpty())			
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

	public Collection<TestcenterUser> getCascadedTesters(long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedSnrTesters(long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedDevelopers(long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedSnrDevelopers(long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

}