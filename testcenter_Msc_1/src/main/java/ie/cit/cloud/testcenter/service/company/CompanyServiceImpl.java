/**
 * 
 */
package ie.cit.cloud.testcenter.service.company;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.respository.company.CompanyRepository;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
    @Qualifier("hibernateCompanyRespository")
    CompanyRepository repo;   
	
	@Autowired   
    ProjectService projectService;   
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<Company> getAllCompanies() {
	return repo.findAll();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Company getCompany(long companyID) {
	return repo.findById(companyID);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Company getCompanyByName(String companyName) {
    	return repo.findCompanyByName(companyName);
    }
    
  //  @Secured("ROLE_ADMIN")
    @Transactional(rollbackFor=ConstraintViolationException.class)   
    public void addNewCompany(Company company) {
	repo.create(company);		
    }
   
  //  @Secured("ROLE_ADMIN")
    public void update(Company company) {
	repo.update(company);
    }  
    
  //  @Secured("ROLE_ADMIN")
    public void remove(long companyID) {
	repo.delete(repo.get(companyID));
    }
       
  //  @Secured("ROLE_ADMIN")    
    public void updateCompanyWithId(long companyID, Company company) {
    	Company oldCompany = repo.findById(companyID);
    	oldCompany.setCompanyName(company.getCompanyName());
    	// TODO Finish update for all values
    	oldCompany.setCycleDisplayName(company.getCycleDisplayName());    			
    	repo.update(oldCompany);
    }
 //   @Secured("ROLE_ADMIN")
    public void updateCompanyNameWithId(long companyID, Company company, String companyName) {
    	Company oldCompany = repo.findById(companyID);
    	oldCompany.setCompanyName(company.getCompanyName());
    	// TODO Finish update for all values
    	repo.update(oldCompany);
    }

	public boolean updateCompany(long companyID, Company company) {
		// TODO Auto-generated method stub
		return false;
	}	

}