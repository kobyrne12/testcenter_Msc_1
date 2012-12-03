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
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.cycle.CycleRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateCycleRespository")
public class HibernateCycleRespository implements CycleRepository {

    @PersistenceContext
    private EntityManager em;

    public Cycle get(Long cycleID) {
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
    public Set<Cycle> findAll() {
    Query query = em.createQuery("from Cycle");	
//	Query query = em.createQuery("from TestPlan where user=:user");
//	query.setParameter("user", getCurrentUser());
 //   query.setParameter("testerName", "john");
	return (Set<Cycle>) query.getResultList();
    }

    public Cycle findById(Long cycleID) {
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
	public Set<Cycle> findAllCyclesByProjectID(Long projectID)
	{
		Query query = em.createQuery("from Cycle where projectID=:projectID"); 		
		query.setParameter("projectID", projectID);
		List<Cycle> cycleslist = query.getResultList();
	    Set<Cycle> cyclesSet = new HashSet<Cycle>(cycleslist);  
		return cyclesSet;	
	}
    @SuppressWarnings("unchecked")
	public  Set<Cycle> findAllCyclesByParentID(Long cycleID)
	{
		Query query = em.createQuery("from Cycle where parentID=:parentID"); 		
		query.setParameter("parentID", cycleID);
		List<Cycle> childCycleslist = query.getResultList();
	    Set<Cycle> childCyclesSet = new HashSet<Cycle>(childCycleslist);  
		return childCyclesSet;
	}	

    public int getMaxProjectPosNum(Long projectID)
    {
    	Query query = em.createQuery("select max(projectPosition) from Cycle where projectID=:projectID");
    	query.setParameter("projectID", projectID);
    	return (query.getSingleResult()) != null ? (Integer)query.getSingleResult() : 0 ;    	
    }

}
