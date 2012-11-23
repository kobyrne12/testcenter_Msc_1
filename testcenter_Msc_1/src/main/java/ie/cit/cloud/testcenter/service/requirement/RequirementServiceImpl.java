/**
 * 
 */
package ie.cit.cloud.testcenter.service.requirement;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.respository.requirement.RequirementRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
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
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Requirement getRequirement(long requirementID) {
		return requirementRepo.findById(requirementID);
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

}