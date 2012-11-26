/**
 * 
 */

package ie.cit.cloud.testcenter.respository.company;
/**
 * @author byrnek1
 *
 */
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Project;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateCompanyRespository")
public class HibernateCompanyRespository implements CompanyRepository {

    @PersistenceContext
    private EntityManager em;

    public Company get(long companyID) {
	Query query = em.createQuery("from Company where companyID=:companyID");
//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
//	query.setParameter("user", getCurrentUser());
	query.setParameter("companyID", companyID);
	return (Company) query.getSingleResult();
    }

    public void create(Company company) {
//    	testplan.setUser(getCurrentUser());
	em.persist(company);
    }

    public void update(Company company) {
	em.merge(company);
    }

    public void delete(Company company) {
	em.remove(company);
    }

    @SuppressWarnings("unchecked")
    public Set<Company> findAll() {
    Query query = em.createQuery("from Company");	
//	Query query = em.createQuery("from TestPlan where user=:user");
//	query.setParameter("user", getCurrentUser());
 //   query.setParameter("testerName", "john");
    List<Company> companylist = query.getResultList();
    Set<Company> companySet = new HashSet<Company>(companylist);  
	return companySet;
    }

    public Company findById(long companyID) {
	return get(companyID);
    }
    
    public Company findCompanyByName(String companyName) {        
    	Query query = em.createQuery("from Company where companyName=:companyName");
    	query.setParameter("companyName", companyName);
    	return (Company) query.getSingleResult();
    }
    
//   private String getCurrentUser() {
//	return SecurityContextHolder.getContext().getAuthentication().getName();
//   }

	
}
