/**
 * 
 */
package ie.cit.cloud.testcenter.respository.user;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.User;

import java.util.Collection;

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
	User get(long userID);

    /**
     * Adds new User into repository
     * 
     * @param User
     */
    void create(User user);

    /**
     * Updates existing User. User with the same ID as give User is updated
     * 
     * @param testplan
     *            new User values
     */
    void update(User user);

    /**
     * Deletes User item from repository.
     * 
     * @param testplan
     */
    void delete(User user);

    // ================ various find-er methods ================
    /**
     * Returns list of all tests
     * 
     * @return all Users
     */
    Collection<User> findAll();

    /**
     * Returns User items given by its ID
     * 
     * @param id
     *            User ID
     * @return User for given id, null if test was not found
     */
    User findById(long userID);
    /**
     * Returns User items given by its name
     * 
     * @param id
     *            User ID
     * @return User for given name, null if User was not found
     */
    User findUserByName(String userName);
    

}
