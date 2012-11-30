/**
 * 
 */

package ie.cit.cloud.testcenter.respository.testrun;
/**
 * @author byrnek1
 *
 */


import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.TestrunLevel;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateTestrunRespository")
public class HibernateTestrunRespository implements TestrunRepository {

	@PersistenceContext
	private EntityManager em;

	public Testrun get(long testrunID) {
		Query query = em.createQuery("from Testrun where testrunID=:testrunID");
		//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
		//	query.setParameter("user", getCurrentUser());
		query.setParameter("testrunID", testrunID);
		return (Testrun) query.getSingleResult();
	}

	public void create(Testrun testrun) {
		//    	testrun.setUser(getCurrentUser());
		em.persist(testrun);
	}

	public void update(Testrun testrun) {
		em.merge(testrun);
	}

	public void delete(Testrun testrun) {
		em.remove(testrun);
	}

	@SuppressWarnings("unchecked")
	public Set<Testrun> findAll() {
		Query query = em.createQuery("from Testrun");	
		//	Query query = em.createQuery("from TestPlan where user=:user");
		//	query.setParameter("user", getCurrentUser());
		//   query.setParameter("testerName", "john");
		List<Testrun> list = query.getResultList();
		Set<Testrun> set = new HashSet<Testrun>(list);  
		return set;	
	}

	public Testrun findById(long testrunID) {
		return get(testrunID);
	}

	public Testrun findTestrunByName(String testrunName) {        
		Query query = em.createQuery("from Testrun where testrunName=:testrunName");
		query.setParameter("testrunName", testrunName);
		return (Testrun) query.getSingleResult();
	}
	
	///////// testrunLevel
	public TestrunLevel findTestrunLevelById(long testrunLevelID) {
		Query query = em.createQuery("from TestrunLevel where testrunLevelID=:testrunLevelID");		
		query.setParameter("testrunLevelID", testrunLevelID);
		return (TestrunLevel) query.getSingleResult();	
	}
	public TestrunLevel findTestrunLevelByName(String testrunLevelName) {
		Query query = em.createQuery("from TestrunLevel where testrunLevelName=:testrunLevelName");		
		query.setParameter("testrunLevelName", testrunLevelName);
		return (TestrunLevel) query.getSingleResult();	
	}
	public void deleteTestrunLevel(TestrunLevel testrunLevel) {
		em.remove(testrunLevel);
		
	}

	public void updateTestrunLevel(TestrunLevel testrunLevel) {
		em.merge(testrunLevel);
		
	}



	public void createTestrunLevel(TestrunLevel testrunLevel) {
		em.persist(testrunLevel);		
	}
	

}
