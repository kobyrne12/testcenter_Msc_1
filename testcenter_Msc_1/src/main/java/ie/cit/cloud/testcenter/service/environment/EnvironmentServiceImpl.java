/**
 * 
 */
package ie.cit.cloud.testcenter.service.environment;

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
import ie.cit.cloud.testcenter.respository.environment.EnvironmentRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	@Qualifier("hibernateEnvironmentRespository")
	EnvironmentRepository environmentRepo;      
	
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
	public Environment getEnvironment(long environmentID) {
		try{
			return environmentRepo.findById(environmentID);								
		}
		catch(NoResultException nre)			
		{	
			return null;
		}		
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Environment getEnvironmentByName(String environmentName) {
		return environmentRepo.findByName(environmentName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewEnvironment(Environment environment) {
		environmentRepo.create(environment);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Environment environment) {
		environmentRepo.update(environment);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long environmentID) {
		environmentRepo.delete(getEnvironment(environmentID));
	}
	////////////////////////
	public Collection<Testrun> getAllTestRuns(long environmentID) 
	{
		Environment environment = getEnvironment(environmentID);
		if(environment == null)
		{
			return null;
		}			
		if(environment.getTestruns() == null || environment.getTestruns().isEmpty())
		{
			return null;
		}
		return environment.getTestruns();			
	} 
	
	public Collection<Testrun> getCompulsoryTestRuns(long environmentID)
	{
		Collection<Testrun> allTestruns = getAllTestRuns(environmentID);
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
	
	public Collection<Testrun> getOptionalTestRuns(long environmentID)
	{
		Collection<Testrun> allTestruns = getAllTestRuns(environmentID);
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
	
	public Collection<Testcase> getAllTestCases(long environmentID)
	{
		Collection<Testrun> allTestruns = getAllTestRuns(environmentID);
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
	
	public Collection<Testcase> getCompulsoryTestCases(long environmentID)
	{
		Collection<Testrun> allTestruns = getCompulsoryTestRuns(environmentID);
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
	
	public Collection<Testcase> getOptionalTestCases(long environmentID)
	{
		Collection<Testrun> allTestruns = getOptionalTestRuns(environmentID);
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
	
	public Collection<Testplan> getAllTestPlans(long environmentID)
	{		
		Collection<Testcase> allTestcases = getAllTestCases(environmentID);
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
	
	public Collection<Testplan> getCompulsoryTestPlans(long environmentID)
	{		
		Collection<Testcase> allTestcases = getCompulsoryTestCases(environmentID);
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
	
	public Collection<Testplan> getOptionalTestPlans(long environmentID)
	{		
		Collection<Testcase> allTestcases = getOptionalTestCases(environmentID);
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
	
	public Collection<Cycle> getCycles(long environmentID) {
		Collection<Testrun> allTestruns = getCompulsoryTestRuns(environmentID);
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

	public Collection<Project> getProjects(long environmentID)
	{
		Collection<Cycle> cycles =  getCycles(environmentID);
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

	public Collection<Requirement> getRequirements(long environmentID) 
	{
		Collection<Testrun> allTestruns = getCompulsoryTestRuns(environmentID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getRequirements() != null && !testrun.getRequirements().isEmpty())			
			{
				requirements.addAll(testrun.getRequirements());
			}			
		}	
		Defect defect = defectService.getDefect(environmentID);
		if(defect.getRequirements() == null || defect.getRequirements().isEmpty())
		{		
			requirements.addAll(defect.getRequirements());			
		}
		return requirements;		
	}	
	
	public Collection<Defect> getCascadedAllDefects(long environmentID) {
		Collection<Testrun> allTestruns = getCompulsoryTestRuns(environmentID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			
			return null;
		}	
		Collection<Defect> defects = new ArrayList<Defect>();
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
	public Collection<TestcenterUser> getCascadedTesters(long environmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedSnrTesters(long environmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedDevelopers(long environmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<TestcenterUser> getCascadedSnrDevelopers(
			long environmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}