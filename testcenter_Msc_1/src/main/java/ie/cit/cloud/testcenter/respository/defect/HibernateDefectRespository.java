/**
 * 
 */

package ie.cit.cloud.testcenter.respository.defect;
/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.respository.defect.DefectRepository;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateDefectRespository")
public class HibernateDefectRespository implements DefectRepository {

	@PersistenceContext
	private EntityManager em;

	public Defect get(long defectID) {
		Query query = em.createQuery("from Defect where defectID=:defectID");
		//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
		//	query.setParameter("user", getCurrentUser());
		query.setParameter("defectID", defectID);
		return (Defect) query.getSingleResult();
	}

	public void create(Defect defect) {
		//    	defect.setUser(getCurrentUser());
		em.persist(defect);
	}

	public void update(Defect defect) {
		em.merge(defect);
	}

	public void delete(Defect defect) {
		em.remove(defect);
	}

	@SuppressWarnings("unchecked")
	public Collection<Defect> findAll() {
		Query query = em.createQuery("from Defect");	
		//	Query query = em.createQuery("from TestPlan where user=:user");
		//	query.setParameter("user", getCurrentUser());
		//   query.setParameter("testerName", "john");
		return (List<Defect>) query.getResultList();
	}

	public Defect findById(long defectID) {
		return get(defectID);
	}

	public Defect findByName(String defectName) {        
		Query query = em.createQuery("from Defect where defectName=:defectName");
		query.setParameter("defectName", defectName);
		return (Defect) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public  Collection<Defect> findAllDefectsByParentID(long defectID)
	{
		Query query = em.createQuery("from Defect where parentID=:parentID"); 		
		query.setParameter("parentID", defectID);
		return ( Collection<Defect>) query.getResultList();
	}	


}
