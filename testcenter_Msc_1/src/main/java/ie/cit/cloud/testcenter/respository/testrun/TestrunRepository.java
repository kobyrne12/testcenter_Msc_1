/**
 * 
 */
package ie.cit.cloud.testcenter.respository.testrun;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.TestrunLevel;

import org.springframework.dao.EmptyResultDataAccessException;

public interface TestrunRepository {

    /**
     * Returns existing Testrun given by its ID
     * Testrun
     * @param id
     *            Testrun ID
     * @return Testrun for given id, {@link EmptyResultDataAccessException} if no
     *         Testrun was found
     */
	Testrun get(long testrunID);

    /**
     * Adds new Testrun into repository
     * 
     * @param Testrun
     */
    void create(Testrun testrun);

    /**
     * Updates existing Testrun. Testrun with the same ID as give Testrun is updated
     * 
     * @param testrun
     *            new Testrun values
     */
    void update(Testrun testrun);

    /**
     * Deletes Testrun item from repository.
     * 
     * @param testrun
     */
    void delete(Testrun testrun);

    /**
     * Returns Testrun items given by its ID
     * 
     * @param id
     *            Testrun ID
     * @return Testrun for given id, null if test was not found
     */
    Testrun findById(long testrunID);
    /**
     * Returns Testrun items given by its name
     * 
     * @param id
     *            Testrun ID
     * @return Testrun for given name, null if Testrun was not found
     */
    Testrun findTestrunByName(String testrunName);
    /**
     * Returns Testrun items given by its name
     * 
     * @param id
     *            Testrun ID
     * @return Testrun for given name, null if Testrun was not found
     */

	void deleteTestrunLevel(TestrunLevel testrunLevel);

	void updateTestrunLevel(TestrunLevel testrunLevel);

	TestrunLevel findTestrunLevelById(long testrunLevelID);

	void createTestrunLevel(TestrunLevel testrunLevel);

	TestrunLevel findTestrunLevelByName(String testrunLevelName);	
}
