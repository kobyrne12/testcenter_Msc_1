/**
 * 
 */

package ie.cit.cloud.testcenter.respository.user;
/**
 * @author byrnek1
 *
 */
import ie.cit.cloud.testcenter.model.User;
import ie.cit.cloud.testcenter.respository.user.UserRepository;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateUserRespository")
public class HibernateUserRespository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    public User get(long userID) {
	Query query = em.createQuery("from User where userID=:userID");
//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
//	query.setParameter("user", getCurrentUser());
	query.setParameter("userID", userID);
	return (User) query.getSingleResult();
    }

    public void create(User user) {
//    	testplan.setUser(getCurrentUser());
	em.persist(user);
    }

    public void update(User user) {
	em.merge(user);
    }

    public void delete(User user) {
	em.remove(user);
    }

    @SuppressWarnings("unchecked")
    public Collection<User> findAll() {
    Query query = em.createQuery("from User");	
//	Query query = em.createQuery("from TestPlan where user=:user");
//	query.setParameter("user", getCurrentUser());
 //   query.setParameter("testerName", "john");
	return (List<User>) query.getResultList();
    }

    public User findById(long userID) {
	return get(userID);
    }
    
    public User findUserByName(String userName) {        
    	Query query = em.createQuery("from User where userName=:userName");
    	query.setParameter("userName", userName);
    	return (User) query.getSingleResult();
    }
//   private String getCurrentUser() {
//	return SecurityContextHolder.getContext().getAuthentication().getName();
//   }

	
}
