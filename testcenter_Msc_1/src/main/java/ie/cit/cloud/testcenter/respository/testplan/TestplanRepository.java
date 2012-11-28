/**
 * 
 */
package ie.cit.cloud.testcenter.respository.testplan;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.TestplanSection;

import org.springframework.dao.EmptyResultDataAccessException;

public interface TestplanRepository {

    /**
     * Returns existing Testplan given by its ID
     * Testplan
     * @param id
     *            Testplan ID
     * @return Testplan for given id, {@link EmptyResultDataAccessException} if no
     *         Testplan was found
     */
	Testplan get(long testplanID);

    /**
     * Adds new Testplan into repository
     * 
     * @param Testplan
     */
    void create(Testplan testplan);

    /**
     * Updates existing Testplan. Testplan with the same ID as give Testplan is updated
     * 
     * @param testplan
     *            new Testplan values
     */
    void update(Testplan testplan);

    /**
     * Deletes Testplan item from repository.
     * 
     * @param testplan
     */
    void delete(Testplan testplan);

    /**
     * Returns Testplan items given by its ID
     * 
     * @param id
     *            Testplan ID
     * @return Testplan for given id, null if test was not found
     */
    Testplan findById(long testplanID);
    /**
     * Returns Testplan items given by its name
     * 
     * @param id
     *            Testplan ID
     * @return Testplan for given name, null if Testplan was not found
     */
    Testplan findTestplanByName(String testplanName);
    /**
     * Returns Testplan items given by its name
     * 
     * @param id
     *            Testplan ID
     * @return Testplan for given name, null if Testplan was not found
     */

	TestplanSection findTestplanSectionById(long testplanSectionID);

	void createTestplanSection(TestplanSection testplanSection);

	void updateTestplanSection(TestplanSection testplanSection);

	void deleteTestplanSection(TestplanSection testplanSection);	
}
