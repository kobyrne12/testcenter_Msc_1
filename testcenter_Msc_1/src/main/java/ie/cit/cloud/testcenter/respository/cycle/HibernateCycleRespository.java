/**
 * 
 */

package ie.cit.cloud.testcenter.respository.cycle;
/**
 * @author byrnek1
 *
 */
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.model.TestPlan;
import ie.cit.cloud.testcenter.respository.cycle.CycleRepository;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateCycleRespository")
public class HibernateCycleRespository implements CycleRepository {

    @PersistenceContext
    private EntityManager em;

    public Cycle get(long cycleID) {
	Query query = em.createQuery("from Cycle where cycleID=:cycleID");
//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
//	query.setParameter("user", getCurrentUser());
	query.setParameter("cycleID", cycleID);
	return (Cycle) query.getSingleResult();
    }

    public void create(Cycle cycle) {
//    	testplan.setUser(getCurrentUser());
    	em.persist(cycle);
    }

    public void update(Cycle cycle) {
	em.merge(cycle);
    }

    public void delete(Cycle cycle) {
	em.remove(cycle);
    }

    @SuppressWarnings("unchecked")
    public Collection<Cycle> findAll() {
    Query query = em.createQuery("from Cycle");	
//	Query query = em.createQuery("from TestPlan where user=:user");
//	query.setParameter("user", getCurrentUser());
 //   query.setParameter("testerName", "john");
	return (List<Cycle>) query.getResultList();
    }

    public Cycle findById(long cycleID) {
    	return get(cycleID);
    }
    
    public Cycle findCycleByName(String cycleName) {        
    	Query query = em.createQuery("from Cycle where cycleName=:cycleName");
    	query.setParameter("cycleName", cycleName);
    	return (Cycle) query.getSingleResult();
    }
//   private String getCurrentUser() {
//	return SecurityContextHolder.getContext().getAuthentication().getName();
//   }	
    
    @SuppressWarnings("unchecked")
	public  Collection<Cycle> findAllCyclesByProjectID(long projectID)
	{
		Query query = em.createQuery("from Cycle where projectID=:projectID"); 		
		query.setParameter("projectID", projectID);
		return ( Collection<Cycle>) query.getResultList();
	}	
	
}
