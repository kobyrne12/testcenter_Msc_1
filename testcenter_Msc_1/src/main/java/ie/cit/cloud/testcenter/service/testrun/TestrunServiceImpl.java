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
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.testrun.TestrunRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
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
    	if(cycleService.isLatest(testrun.getCycleID()))
    	{
    		return true;
    	}
    	else
    	{
    		return false;	
    	}    
    }
    public Collection<Testrun> getTestHistory(long testrunID) {
    	Testrun testrun = getTestrun(testrunID);
    	Testcase testcase = testcaseService.getTestcase(testrun.getTestcaseID());    	
		return testcase.getTestruns();
	} 

}