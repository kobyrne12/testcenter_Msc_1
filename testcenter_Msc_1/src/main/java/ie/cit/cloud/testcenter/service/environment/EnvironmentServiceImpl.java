/**
 * 
 */
package ie.cit.cloud.testcenter.service.environment;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.respository.environment.EnvironmentRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
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
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Environment getEnvironment(long environmentID) {
		return environmentRepo.findById(environmentID);
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

}