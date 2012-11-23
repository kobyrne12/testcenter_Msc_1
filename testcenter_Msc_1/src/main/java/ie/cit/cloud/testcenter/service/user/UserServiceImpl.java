/**
 * 
 */
package ie.cit.cloud.testcenter.service.user;

/**
 * @author byrnek1
 *
 */


import java.util.Collection;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.respository.user.UserRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import javax.persistence.NoResultException;
import javax.persistence.Transient;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("hibernateUserRespository")
	UserRepository userRepo;      
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	ProjectService projectService;	
	
	@Autowired
	CycleService cycleService;	
	
	@Autowired
	TestcaseService testcaseService;	
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public TestcenterUser getUser(long userID) {
		return userRepo.findById(userID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public TestcenterUser getUserByName(String userName) {
		return userRepo.getUserByUserName(userName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewUser(TestcenterUser user) {
		userRepo.create(user);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(TestcenterUser user) {
		userRepo.update(user);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long userID) {
		userRepo.delete(getUser(userID));
	}

}