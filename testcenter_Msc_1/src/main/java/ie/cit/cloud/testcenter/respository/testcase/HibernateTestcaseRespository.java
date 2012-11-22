/**
 * 
 */

package ie.cit.cloud.testcenter.respository.testcase;
/**
 * @author byrnek1
 *
 */
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.testcase.TestcaseRepository;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateTestcaseRespository")
public class HibernateTestcaseRespository implements TestcaseRepository {

    @PersistenceContext
    private EntityManager em;

    public Testcase get(long testcaseID) {
	Query query = em.createQuery("from Testcase where testcaseID=:testcaseID");
//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
//	query.setParameter("user", getCurrentUser());
	query.setParameter("testcaseID", testcaseID);
	return (Testcase) query.getSingleResult();
    }

    public void create(Testcase testcase) {
//    	testplan.setUser(getCurrentUser());
    	em.persist(testcase);
    }

    public void update(Testcase testcase) {
	em.merge(testcase);
    }

    public void delete(Testcase testcase) {
	em.remove(testcase);
    }

    @SuppressWarnings("unchecked")
    public Collection<Testcase> findAll() {
    Query query = em.createQuery("from Testcase");	
//	Query query = em.createQuery("from TestPlan where user=:user");
//	query.setParameter("user", getCurrentUser());
 //   query.setParameter("testerName", "john");
	return (List<Testcase>) query.getResultList();
    }

    public Testcase findById(long testcaseID) {
    	return get(testcaseID);
    }
    
    public Testcase findTestcaseByName(String testcaseName) {        
    	Query query = em.createQuery("from Testcase where testcaseName=:testcaseName");
    	query.setParameter("testcaseName", testcaseName);
    	return (Testcase) query.getSingleResult();
    }

	
}
