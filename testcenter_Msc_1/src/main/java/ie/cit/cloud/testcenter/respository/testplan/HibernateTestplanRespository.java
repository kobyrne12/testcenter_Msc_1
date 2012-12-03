/**
 * 
 */

package ie.cit.cloud.testcenter.respository.testplan;
/**
 * @author byrnek1
 *
 */
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.TestplanSection;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.testplan.TestplanRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateTestplanRespository")
public class HibernateTestplanRespository implements TestplanRepository {

	@PersistenceContext
	private EntityManager em;

	public Testplan get(Long testplanID) {
		Query query = em.createQuery("from Testplan where testplanID=:testplanID");
		//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
		//	query.setParameter("user", getCurrentUser());
		query.setParameter("testplanID", testplanID);
		return (Testplan) query.getSingleResult();
	}

	public void create(Testplan testplan) {
		//    	testplan.setUser(getCurrentUser());
		em.persist(testplan);
	}

	public void update(Testplan testplan) {
		em.merge(testplan);
	}

	public void delete(Testplan testplan) {
		em.remove(testplan);
	}

	@SuppressWarnings("unchecked")
	public Set<Testplan> findAll() {
		Query query = em.createQuery("from Testplan");	
		//	Query query = em.createQuery("from TestPlan where user=:user");
		//	query.setParameter("user", getCurrentUser());
		//   query.setParameter("testerName", "john");
		List<Testplan> list = query.getResultList();
		Set<Testplan> set = new HashSet<Testplan>(list);  
		return set;	
	}

	public Testplan findById(Long testplanID) {
		return get(testplanID);
	}

	public Testplan findTestplanByName(String testplanName) {        
		Query query = em.createQuery("from Testplan where testplanName=:testplanName");
		query.setParameter("testplanName", testplanName);
		return (Testplan) query.getSingleResult();
	}

	public TestplanSection findTestplanSectionById(Long testplanSectionID) {
		Query query = em.createQuery("from TestplanSection where testplanSectionID=:testplanSectionID");
		query.setParameter("testplanSectionID", testplanSectionID);
		return (TestplanSection) query.getSingleResult();	
	}

	public void createTestplanSection(TestplanSection testplanSection) {
		em.persist(testplanSection);		
	}

	public void updateTestplanSection(TestplanSection testplanSection) {
		em.merge(testplanSection);
		
	}

	public void deleteTestplanSection(TestplanSection testplanSection) {
		em.remove(testplanSection);
		
	}


}
