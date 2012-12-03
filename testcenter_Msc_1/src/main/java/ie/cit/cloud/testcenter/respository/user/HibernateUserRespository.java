/**
 * 
 */

package ie.cit.cloud.testcenter.respository.user;
/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.user.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateUserRespository")
public class HibernateUserRespository implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	public TestcenterUser get(Long userID) {
		Query query = em.createQuery("from User where userID=:userID");
		//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
		//	query.setParameter("user", getCurrentUser());
		query.setParameter("userID", userID);
		return (TestcenterUser) query.getSingleResult();
	}    

	public TestcenterUser getUserByUserName(String username)
	{
		Query query = em.createQuery("from User where username=:username");
		query.setParameter("username", username);
		return (TestcenterUser) query.getSingleResult();
	}
	public void create(TestcenterUser user) {
		//    	testplan.setUser(getCurrentUser());
		em.persist(user);
	}

	public void update(TestcenterUser user) {
		em.merge(user);
	}

	public void delete(TestcenterUser user) {
		em.remove(user);
	}

	@SuppressWarnings("unchecked")
	public Set<TestcenterUser> findAll() {
		Query query = em.createQuery("from User");	
		//	Query query = em.createQuery("from TestPlan where user=:user");
		//	query.setParameter("user", getCurrentUser());
		//   query.setParameter("testerName", "john");
		List<TestcenterUser> list = query.getResultList();
		Set<TestcenterUser> set = new HashSet<TestcenterUser>(list);  
		return set;
	}

	public TestcenterUser findById(Long userID) {
		return get(userID);
	}

	//   private String getCurrentUser() {
	//	return SecurityContextHolder.getContext().getAuthentication().getName();
	//   }	
}