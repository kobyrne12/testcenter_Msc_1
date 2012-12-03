/**
 * 
 */
package ie.cit.cloud.testcenter.service.user;

/**
 * @author byrnek1
 *
 */

import java.util.Collection;

import ie.cit.cloud.testcenter.model.TestcenterUser;;

/**
 * Peforms business operation on Test run 
 */
public interface UserService {
 
	void addNewUser(TestcenterUser user);

	TestcenterUser getUser(Long userID);   
    
	TestcenterUser getUserByName(String userName);
    
    void update(TestcenterUser user);
    
    void remove(Long userID);  

}