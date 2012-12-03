/**
 * 
 */
package ie.cit.cloud.testcenter.respository.testcase;

/**
 * @author byrnek1
 *
 */

import java.util.Set;

import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import org.springframework.dao.EmptyResultDataAccessException;

public interface TestcaseRepository {

    /**
     * Returns existing Testcase given by its ID
     * Testcase
     * @param id
     *            Testcase ID
     * @return Testcase for given id, {@link EmptyResultDataAccessException} if no
     *         Testcase was found
     */
	Testcase get(Long testcaseID);

    /**
     * Adds new Testcase into repository
     * 
     * @param Testcase
     */
    void create(Testcase testcase);

    /**
     * Updates existing Testcase. Testcase with the same ID as give Testcase is updated
     * 
     * @param testplan
     *            new Testcase values
     */
    void update(Testcase testcase);

    /**
     * Deletes Testcase item from repository.
     * 
     * @param testplan
     */
    void delete(Testcase testcase);

    /**
     * Returns Testcase items given by its ID
     * 
     * @param id
     *            Testcase ID
     * @return Testcase for given id, null if test was not found
     */
    Testcase findById(Long testcaseID);
    /**
     * Returns Testcase items given by its name
     * 
     * @param id
     *            Testcase ID
     * @return Testcase for given name, null if Testcase was not found
     */
    Testcase findTestcaseByName(String testcaseName);
    /**
     * Returns Testcase items given by its name
     * 
     * @param id
     *            Testcase ID
     * @return Testcase for given name, null if Testcase was not found
     */

	Set<Testcase> findAll();	
}
