/**
 * 
 */
package ie.cit.cloud.testcenter.respository.user;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.TestcenterUser;

import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;

public interface UserRepository {

    /**
     * Returns existing User given by its ID
     * User
     * @param id
     *            User ID
     * @return User for given id, {@link EmptyResultDataAccessException} if no
     *         User was found
     */
	TestcenterUser get(Long userID);

    /**
     * Adds new User into repository
     * 
     * @param TestcenterUser
     */
    void create(TestcenterUser user);

    /**
     * Updates existing User. User with the same ID as give User is updated
     * 
     * @param testplan
     *            new User values
     */
    void update(TestcenterUser user);

    /**
     * Deletes User item from repository.
     * 
     * @param testplan
     */
    void delete(TestcenterUser user);

    // ================ various find-er methods ================
    /**
     * Returns list of all tests
     * 
     * @return all Users
     */
    Set<TestcenterUser> findAll();

    /**
     * Returns User items given by its ID
     * 
     * @param id
     *            User ID
     * @return User for given id, null if test was not found
     */
    TestcenterUser findById(Long userID);
    /**
     * Returns User items given by its name
     * 
     * @param id
     *            User ID
     * @return User for given name, null if User was not found
     */   
    
    TestcenterUser getUserByUserName(String userName);

}
