/**
 * 
 */
package ie.cit.cloud.testcenter.service.testplan;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.respository.testplan.TestplanRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestplanServiceImpl implements TestplanService {

	@Autowired
	@Qualifier("hibernateTestplanRespository")
	TestplanRepository testplanRepo;      
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	ProjectService projectService;	
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testplan getTestplan(long cycleID) {
		return testplanRepo.findById(cycleID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testplan getTestplanByName(String testplanName) {
		return testplanRepo.findTestplanByName(testplanName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewTestplan(Testplan testplan) {
		testplanRepo.create(testplan);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Testplan cycle) {
		testplanRepo.update(cycle);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long testplanID) {
		testplanRepo.delete(getTestplan(testplanID));
	}

}