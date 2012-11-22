/**
 * 
 */
package ie.cit.cloud.testcenter.service.testrun;

/**
 * @author byrnek1
 *
 */


import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.testrun.TestrunRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import javax.persistence.NoResultException;
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
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testrun getTestrun(long cycleID) {
		return testrunRepo.findById(cycleID);
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
	public void update(Testrun cycle) {
		testrunRepo.update(cycle);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long testrunID) {
		testrunRepo.delete(getTestrun(testrunID));
	}

}