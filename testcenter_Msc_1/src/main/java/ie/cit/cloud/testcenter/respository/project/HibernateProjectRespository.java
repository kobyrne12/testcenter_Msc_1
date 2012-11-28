/**
 * 
 */

package ie.cit.cloud.testcenter.respository.project;
/**
 * @author byrnek1
 *
 */
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.respository.project.ProjectRepository;

import java.util.HashSet;
import java.util.Set;
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
	public Set<Project> findAll() 
	{
		Query query = em.createQuery("from Project");	
		//	Query query = em.createQuery("from TestPlan where user=:user");
		//	query.setParameter("user", getCurrentUser());
		//   query.setParameter("testerName", "john");
		List<Project> list = query.getResultList();
		Set<Project> set = new HashSet<Project>(list);  
		return set;	
	}
	@SuppressWarnings("unchecked")
	public Set<Project> findAllChildProjects(Long projectID)
	{
		Query query = em.createQuery("from Project where parentID=:projectID");
		query.setParameter("projectID", projectID);
		List<Project> list = query.getResultList();
		Set<Project> set = new HashSet<Project>(list);  
		return set;		    	
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
	public  Set<Project> findAllProjectsByCompanyID(long companyID)
	{
		Query query = em.createQuery("from Project where companyID=:companyID"); 		
		query.setParameter("companyID", companyID);
		List<Project> projectlist = query.getResultList();
		Set<Project> projectSet = new HashSet<Project>(projectlist);  
		return projectSet;	
	}

	@SuppressWarnings("unchecked")
	public  Set<Project> findAllProjectsByCycleID(long cycleID)
	{
		Query query = em.createQuery("from Project where cycleID=:cycleID"); 		
		query.setParameter("cycleID", cycleID);
		List<Project> projectlist = query.getResultList();
		Set<Project> projectSet = new HashSet<Project>(projectlist);  
		return projectSet;	
	}


}
