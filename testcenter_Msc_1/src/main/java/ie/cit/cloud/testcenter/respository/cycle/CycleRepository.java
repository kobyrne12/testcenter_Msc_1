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
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Testrun;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;

public interface CycleRepository {

    /**
     * Returns existing Cycle given by its ID
     * Cycle
     * @param id
     *            Cycle ID
     * @return Cycle for given id, {@link EmptyResultDataAccessException} if no
     *         Cycle was found
     */
	Cycle get(long cycleID);

    /**
     * Adds new Cycle into repository
     * 
     * @param Cycle
     */
    void create(Cycle cycle);

    /**
     * Updates existing Cycle. Cycle with the same ID as give Cycle is updated
     * 
     * @param testplan
     *            new Cycle values
     */
    void update(Cycle cycle);

    /**
     * Deletes Cycle item from repository.
     * 
     * @param testplan
     */
    void delete(Cycle cycle);

    // ================ various find-er methods ================
    /**
     * Returns list of all tests
     * 
     * @return all Cycles
     */
    Collection<Cycle> findAll();

    /**
     * Returns Cycle items given by its ID
     * 
     * @param id
     *            Cycle ID
     * @return Cycle for given id, null if test was not found
     */
    Cycle findById(long cycleID);
    /**
     * Returns Cycle items given by its name
     * 
     * @param id
     *            Cycle ID
     * @return Cycle for given name, null if Cycle was not found
     */
    Cycle findCycleByName(String cycleName);
    /**
     * Returns Cycle items given by its name
     * 
     * @param id
     *            Cycle ID
     * @return Cycle for given name, null if Cycle was not found
     */

	Collection<Cycle> findAllCyclesByProjectID(long projectID);
	
	 /**
     * Returns the highest project position number for a project 
     * 
     * @param id
     *            projectID
     * @return highest position number for project, null if no other cycle exists
     *      */

	int getMaxProjectPosNum(long projectID);

	Collection<Cycle> findAllCyclesByParentID(long cycleID);
	
}
