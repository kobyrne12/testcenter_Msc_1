/**
 * 
 */

package ie.cit.cloud.testcenter.respository.environment;
/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.respository.environment.EnvironmentRepository;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateEnvironmentRespository")
public class HibernateEnvironmentRespository implements EnvironmentRepository {

	@PersistenceContext
	private EntityManager em;

	public Environment get(long environmentID) {
		Query query = em.createQuery("from Environment where environmentID=:environmentID");
		//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
		//	query.setParameter("user", getCurrentUser());
		query.setParameter("environmentID", environmentID);
		return (Environment) query.getSingleResult();
	}

	public void create(Environment environment) {
		//    	environment.setUser(getCurrentUser());
		em.persist(environment);
	}

	public void update(Environment environment) {
		em.merge(environment);
	}

	public void delete(Environment environment) {
		em.remove(environment);
	}

	@SuppressWarnings("unchecked")
	public Collection<Environment> findAll() {
		Query query = em.createQuery("from Environment");	
		//	Query query = em.createQuery("from TestPlan where user=:user");
		//	query.setParameter("user", getCurrentUser());
		//   query.setParameter("testerName", "john");
		return (List<Environment>) query.getResultList();
	}

	public Environment findById(long environmentID) {
		return get(environmentID);
	}

	public Environment findByName(String environmentName) {        
		Query query = em.createQuery("from Environment where environmentName=:environmentName");
		query.setParameter("environmentName", environmentName);
		return (Environment) query.getSingleResult();
	}


}
