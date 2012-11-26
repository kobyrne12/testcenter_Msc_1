/**
 * 
 */

package ie.cit.cloud.testcenter.respository.requirement;
/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.respository.requirement.RequirementRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateRequirementRespository")
public class HibernateRequirementRespository implements RequirementRepository {

	@PersistenceContext
	private EntityManager em;

	public Requirement get(long requirementID) {
		Query query = em.createQuery("from Requirement where requirementID=:requirementID");
		//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
		//	query.setParameter("user", getCurrentUser());
		query.setParameter("requirementID", requirementID);
		return (Requirement) query.getSingleResult();
	}

	public void create(Requirement requirement) {
		//    	requirement.setUser(getCurrentUser());
		em.persist(requirement);
	}

	public void update(Requirement requirement) {
		em.merge(requirement);
	}

	public void delete(Requirement requirement) {
		em.remove(requirement);
	}

	@SuppressWarnings("unchecked")
	public Set<Requirement> findAll() {
		Query query = em.createQuery("from Requirement");	
		//	Query query = em.createQuery("from TestPlan where user=:user");
		//	query.setParameter("user", getCurrentUser());
		//   query.setParameter("testerName", "john");
		List<Requirement> list = query.getResultList();
	    Set<Requirement> set = new HashSet<Requirement>(list);  
		return set;	
	}

	public Requirement findById(long requirementID) {
		return get(requirementID);
	}

	public Requirement findByName(String requirementName) {        
		Query query = em.createQuery("from Requirement where requirementName=:requirementName");
		query.setParameter("requirementName", requirementName);
		return (Requirement) query.getSingleResult();
	}


}
