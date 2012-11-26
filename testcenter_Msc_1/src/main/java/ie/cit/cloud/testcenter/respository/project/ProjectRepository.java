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

import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;

public interface ProjectRepository {

    /**
     * Returns existing Project given by its ID
     * Project
     * @param id
     *            Project ID
     * @return Project for given id, {@link EmptyResultDataAccessException} if no
     *         Project was found
     */
	Project get(long projectID);

    /**
     * Adds new Project into repository
     * 
     * @param Project
     */
    void create(Project project);

    /**
     * Updates existing Project. Project with the same ID as give Project is updated
     * 
     * @param testplan
     *            new Project values
     */
    void update(Project project);

    /**
     * Deletes Project item from repository.
     * 
     * @param testplan
     */
    void delete(Project project);

    // ================ various find-er methods ================
    /**
     * Returns list of all tests
     * 
     * @return all Projects
     */
    Set<Project> findAll();

    /**
     * Returns Project items given by its ID
     * 
     * @param id
     *            Project ID
     * @return Project for given id, null if test was not found
     */
    Project findById(long projectID);
    /**
     * Returns Project items given by its name
     * 
     * @param id
     *            Project ID
     * @return Project for given name, null if Project was not found
     */
    Project findProjectByName(String projectName);

	Set<Project> findAllProjectsByCompanyID(long companyID);

	Set<Project> findAllProjectsByCycleID(long cycleID);
	
	//////////////////////////////////////////////////

	Set<Project> findAllChildProjects(Long projectID);
    

}
