/**
 * 
 */
package ie.cit.cloud.testcenter.service.requirement;

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
import ie.cit.cloud.testcenter.respository.requirement.RequirementRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequirementServiceImpl implements RequirementService {

	@Autowired
	@Qualifier("hibernateRequirementRespository")
	RequirementRepository requirementRepo;      

	@Autowired
	CompanyService companyService;	
	@Autowired
	ProjectService projectService;
	@Autowired
	TestplanService testplanService;	
	@Autowired
	TestcaseService testcaseService;
	@Autowired
	TestrunService testrunService;
	@Autowired
	DefectService defectService;

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Requirement getRequirement(Long requirementID) {
		try{
			return requirementRepo.findById(requirementID);						
		}
		catch(NoResultException nre)			
		{	
			return null;
		}	
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Requirement getRequirementByName(String requirementName) {
		return requirementRepo.findByName(requirementName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewRequirement(Requirement requirement) {
		requirementRepo.create(requirement);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Requirement requirement) {
		requirementRepo.update(requirement);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(Long requirementID) {
		requirementRepo.delete(getRequirement(requirementID));
	}
	////////////////////////
	public Set<Testrun> getAllTestRuns(Long requirementID) 
	{
		Requirement requirement = getRequirement(requirementID);
		if(requirement == null)
		{
			return null;
		}			
		if(requirement.getTestruns() == null || requirement.getTestruns().isEmpty())
		{
			return null;
		}
		return requirement.getTestruns();			
	} 

	public Set<Testrun> getCompulsoryTestRuns(Long requirementID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(requirementID);
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

	public Set<Testrun> getOptionalTestRuns(Long requirementID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(requirementID);
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

	public Set<Testcase> getAllTestCases(Long requirementID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(requirementID);
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

	public Set<Testcase> getCompulsoryTestCases(Long requirementID)
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
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

	public Set<Testcase> getOptionalTestCases(Long requirementID)
	{
		Set<Testrun> allTestruns = getOptionalTestRuns(requirementID);
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

	public Set<Testplan> getAllTestPlans(Long requirementID)
	{		
		Set<Testcase> allTestcases = getAllTestCases(requirementID);
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

	public Set<Testplan> getCompulsoryTestPlans(Long requirementID)
	{		
		Set<Testcase> allTestcases = getCompulsoryTestCases(requirementID);
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

	public Set<Testplan> getOptionalTestPlans(Long requirementID)
	{		
		Set<Testcase> allTestcases = getOptionalTestCases(requirementID);
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

	public Set<Cycle> getCycles(Long requirementID) {
		Set<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
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

	public Set<Project> getProjects(Long requirementID)
	{
		Set<Cycle> cycles =  getCycles(requirementID);
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

	public Set<Environment> getEnvironments(Long requirementID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
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

	public Set<Defect> getCascadedAllDefects(Long requirementID)
	{
		Requirement requirement = getRequirement(requirementID);
		if(requirement == null)
		{
			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();
		if(requirement.getDefects() != null && !requirement.getDefects().isEmpty())			
		{
			defects.addAll(requirement.getDefects());
		}
		Set<Testrun> allTestruns = getAllTestRuns(requirementID);
		if(allTestruns != null && !allTestruns.isEmpty())
		{
			for(final Testrun testrun : allTestruns)
			{	
				if(testrunService.getCascadedAllDefects(testrun.getTestrunID()) != null &&
						!testrunService.getCascadedAllDefects(testrun.getTestrunID()).isEmpty())
				{
					defects.addAll(testrunService.getCascadedAllDefects(testrun.getTestrunID()));
				}							
			}			
		}		
		return defects;				
	}
	public Set<Defect> getCascadedSev1Defects(Long testrunID) 
	{		
		Set<Defect> allDefects = getCascadedAllDefects(testrunID);		
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
	public Set<Defect> getCascadedSev2Defects(Long testrunID) 
	{		
		Set<Defect> allDefects = getCascadedAllDefects(testrunID);		
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
		
	public Set<Defect> getCascadedSev3Defects(Long testrunID) 
	{		
		Set<Defect> allDefects = getCascadedAllDefects(testrunID);		
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
	public Set<Defect> getCascadedSev4Defects(Long testrunID) 
	{		
		Set<Defect> allDefects = getCascadedAllDefects(testrunID);		
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
	public Set<TestcenterUser> getCascadedTesters(Long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(Long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(Long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(
			Long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

}