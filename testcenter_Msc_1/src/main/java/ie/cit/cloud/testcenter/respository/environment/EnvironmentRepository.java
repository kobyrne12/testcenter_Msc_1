/**
 * 
 */
package ie.cit.cloud.testcenter.respository.environment;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Environment;
import org.springframework.dao.EmptyResultDataAccessException;

public interface EnvironmentRepository {

    /**
     * Returns existing Environment given by its ID
     * Environment
     * @param id
     *            Environment ID
     * @return Environment for given id, {@link EmptyResultDataAccessException} if no
     *         Environment was found
     */
	Environment get(long environmentID);

    /**
     * Adds new Environment into repository
     * 
     * @param Environment
     */
    void create(Environment environment);

    /**
     * Updates existing Environment. Environment with the same ID as give Environment is updated
     * 
     * @param environment
     *            new Environment values
     */
    void update(Environment environment);

    /**
     * Deletes Environment item from repository.
     * 
     * @param environment
     */
    void delete(Environment environment);

    /**
     * Returns Environment items given by its ID
     * 
     * @param id
     *            Environment ID
     * @return Environment for given id, null if test was not found
     */
    Environment findById(long environmentID);
    /**
     * Returns Environment items given by its name
     * 
     * @param id
     *            Environment ID
     * @return Environment for given name, null if Environment was not found
     */
    Environment findByName(String environmentName);
    /**
     * Returns Environment items given by its name
     * 
     * @param id
     *            Environment ID
     * @return Environment for given name, null if Environment was not found
     */	
}
