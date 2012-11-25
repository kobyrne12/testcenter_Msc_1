/**
 * 
 */
package ie.cit.cloud.testcenter.service.requirement;

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
	public Requirement getRequirement(long requirementID) {
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
	public void remove(long requirementID) {
		requirementRepo.delete(getRequirement(requirementID));
	}
	////////////////////////
	public Collection<Testrun> getAllTestRuns(long requirementID) 
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

	public Collection<Testrun> getCompulsoryTestRuns(long requirementID)
	{
		Collection<Testrun> allTestruns = getAllTestRuns(requirementID);
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

	public Collection<Testrun> getOptionalTestRuns(long requirementID)
	{
		Collection<Testrun> allTestruns = getAllTestRuns(requirementID);
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

	public Collection<Testcase> getAllTestCases(long requirementID)
	{
		Collection<Testrun> allTestruns = getAllTestRuns(requirementID);
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

	public Collection<Testcase> getCompulsoryTestCases(long requirementID)
	{
		Collection<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
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

	public Collection<Testcase> getOptionalTestCases(long requirementID)
	{
		Collection<Testrun> allTestruns = getOptionalTestRuns(requirementID);
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

	public Collection<Testplan> getAllTestPlans(long requirementID)
	{		
		Collection<Testcase> allTestcases = getAllTestCases(requirementID);
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

	public Collection<Testplan> getCompulsoryTestPlans(long requirementID)
	{		
		Collection<Testcase> allTestcases = getCompulsoryTestCases(requirementID);
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

	public Collection<Testplan> getOptionalTestPlans(long requirementID)
	{		
		Collection<Testcase> allTestcases = getOptionalTestCases(requirementID);
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

	public Collection<Cycle> getCycles(long requirementID) {
		Collection<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
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

	public Collection<Project> getProjects(long requirementID)
	{
		Collection<Cycle> cycles =  getCycles(requirementID);
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

	public Collection<Environment> getEnvironments(long requirementID) 
	{
		Collection<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Collection<Environment> environments = new ArrayList<Environment>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getEnvironments() != null && !testrun.getEnvironments().isEmpty())			
			{
				environments.addAll(testrun.getEnvironments());
			}			
		}
		return environments;
	}	

	public Collection<Defect> getCascadedAllDefects(long requirementID)
	{
		Requirement requirement = getRequirement(requirementID);
		if(requirement == null)
		{
			return null;
		}	
		Collection<Defect> defects = new ArrayList<Defect>();
		if(requirement.getDefects() != null && !requirement.getDefects().isEmpty())			
		{
			defects.addAll(requirement.getDefects());
		}
		Collection<Testrun> allTestruns = getAllTestRuns(requirementID);
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
	public Collection<Defect> getCascadedSev1Defects(long testrunID) 
	{		
		Collection<Defect> allDefects = getCascadedAllDefects(testrunID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Collection<Defect> sev1defects = new ArrayList<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev1(defect.getDefectID()))
			{
				sev1defects.add(defect);						
			}
		}	
		return sev1defects;
	}
	public Collection<Defect> getCascadedSev2Defects(long testrunID) 
	{		
		Collection<Defect> allDefects = getCascadedAllDefects(testrunID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Collection<Defect> sev2defects = new ArrayList<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev2(defect.getDefectID()))
			{
				sev2defects.add(defect);						
			}
		}	
		return sev2defects;
	}
		
	public Collection<Defect> getCascadedSev3Defects(long testrunID) 
	{		
		Collection<Defect> allDefects = getCascadedAllDefects(testrunID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Collection<Defect> sev3defects = new ArrayList<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev3(defect.getDefectID()))
			{
				sev3defects.add(defect);						
			}
		}	
		return sev3defects;
	}	
	public Collection<Defect> getCascadedSev4Defects(long testrunID) 
	{		
		Collection<Defect> allDefects = getCascadedAllDefects(testrunID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Collection<Defect> sev4defects = new ArrayList<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev4(defect.getDefectID()))
			{
				sev4defects.add(defect);						
			}
		}	
		return sev4defects;
	}
	public Collection<TestcenterUser> getCascadedTesters(long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedSnrTesters(long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedDevelopers(long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedSnrDevelopers(
			long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

}