/**
 * 
 */

package ie.cit.cloud.testcenter.respository.project;
/**
 * @author byrnek1
 *
 */
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.model.TestPlan;
import ie.cit.cloud.testcenter.respository.project.ProjectRepository;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("hibernateProjectRespository")
public class HibernateProjectRespository implements ProjectRepository {

    @PersistenceContext
    private EntityManager em;

    public Project get(long projectID) {
	Query query = em.createQuery("from Project where projectID=:projectID");
//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
//	query.setParameter("user", getCurrentUser());
	query.setParameter("projectID", projectID);
	return (Project) query.getSingleResult();
    }

    public void create(Project project) {
//    	testplan.setUser(getCurrentUser());
    	em.persist(project);
    }

    public void update(Project project) {
	em.merge(project);
    }

    public void delete(Project project) {
	em.remove(project);
    }

    @SuppressWarnings("unchecked")
    public Collection<Project> findAll() {
    Query query = em.createQuery("from Project");	
//	Query query = em.createQuery("from TestPlan where user=:user");
//	query.setParameter("user", getCurrentUser());
 //   query.setParameter("testerName", "john");
	return (List<Project>) query.getResultList();
    }

    public Project findById(long projectID) {
	return get(projectID);
    }
    
    public Project findProjectByName(String projectName) {        
    	Query query = em.createQuery("from Project where projectName=:projectName");
    	query.setParameter("projectName", projectName);
    	return (Project) query.getSingleResult();
    }
//   private String getCurrentUser() {
//	return SecurityContextHolder.getContext().getAuthentication().getName();
//   }
    
	@SuppressWarnings("unchecked")
	public  Collection<Project> findAllProjectsByCompanyID(long companyID)
	{
		Query query = em.createQuery("from Project where companyID=:companyID"); 		
		query.setParameter("companyID", companyID);
		return ( Collection<Project>) query.getResultList();
	}
    
    @SuppressWarnings("unchecked")
	public  Collection<Project> findAllProjectsByCycleID(long cycleID)
	{
		Query query = em.createQuery("from Project where cycleID=:cycleID"); 		
		query.setParameter("cycleID", cycleID);
		return ( Collection<Project>) query.getResultList();
	}

	
}