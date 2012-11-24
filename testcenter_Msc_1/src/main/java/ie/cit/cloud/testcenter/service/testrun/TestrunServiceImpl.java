/**
 * 
 */
package ie.cit.cloud.testcenter.service.testrun;

/**
 * @author byrnek1
 *
 */


import java.util.Collection;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.testrun.TestrunRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
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
public class TestrunServiceImpl implements TestrunService {

	@Autowired
	@Qualifier("hibernateTestrunRespository")
	TestrunRepository testrunRepo;      

	@Autowired
	CompanyService companyService;
	@Autowired
	ProjectService projectService;	
	@Autowired
	CycleService cycleService;	
	@Autowired
	TestcaseService testcaseService;
	@Autowired
	TestplanService testplanService;	

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testrun getTestrun(long testrunID) {
		return testrunRepo.findById(testrunID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testrun getTestrunByName(String testrunName) {
		return testrunRepo.findTestrunByName(testrunName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewTestrun(Testrun testrun) {
		testrunRepo.create(testrun);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Testrun testrun) {
		testrunRepo.update(testrun);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long testrunID) {
		testrunRepo.delete(getTestrun(testrunID));
	}
	/**
	 * Returns true if this testruns cycle is the latest cycle for a project 
	 * boolean
	 * @return true if this testruns cycle is the latest cycle for a project, otherwise false
	 */  
	public boolean isLatest(long testrunID)
	{		
		Testrun testrun= getTestrun(testrunID);
		try{
			if(cycleService.isLatest(testrun.getCycleID()))
			{
				return true;
			}
			else
			{
				return false;	
			}    
		}catch(NoResultException nre)
		{
			return false;
		}			
	}
	public Collection<Testrun> getTestHistory(long testrunID) 
	{
		Testrun testrun = getTestrun(testrunID);
		try{
			Testcase testcase = testcaseService.getTestcase(testrun.getTestcaseID());   
			return testcase.getTestruns();
		}catch(NoResultException nre)
		{
			return null;
		}		
	} 

	public boolean isRequired(long testrunID) 
	{
		Testrun testrun = getTestrun(testrunID);
		try{
			Cycle cycle = cycleService.getCycle(testrun.getCycleID());
			if(testrun.getPriority() <= cycle.getRequiredPriority())
			{
				return true;
			}
			return false;  
		}catch(NoResultException nre)
		{
			return false;
		}
	}   
	//////////////////////////////////

	public Cycle getCycle(long testrunID)
	{
		Testrun testrun = getTestrun(testrunID);
		try{
			return cycleService.getCycle(testrun.getCycleID());			
		}catch(NoResultException nre)
		{
			return null;
		}
	}

	public Testcase getTestcase(long testrunID) {
		Testrun testrun = getTestrun(testrunID);
		try{
			return testcaseService.getTestcase(testrun.getTestcaseID());			
		}catch(NoResultException nre)
		{
			return null;
		}
	}

	public Project getProject(long testrunID)
	{
		Cycle cycle = getCycle(testrunID);
		if(cycle == null)
		{
			return null;
		}
		try{
			return projectService.getProject(cycle.getProjectID());			
		}catch(NoResultException nre)
		{
			return null;
		}	
	}

	public Testplan getTestPlan(long testrunID) {
		Testcase testcase = getTestcase(testrunID);
		if(testcase == null)
		{
			return null;
		}
		try{
			return testplanService.getTestplan(testcase.getTestplanID());			
		}catch(NoResultException nre)
		{
			return null;
		}	
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