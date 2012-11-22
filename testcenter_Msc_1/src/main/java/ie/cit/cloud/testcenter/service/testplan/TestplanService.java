/**
 * 
 */
package ie.cit.cloud.testcenter.service.testplan;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Testplan;

/**
 * Peforms business operation on Test plan 
 */
public interface TestplanService {
 
	void addNewTestplan(Testplan testplan);

    Testplan getTestplan(long testplanID);   
    
    Testplan getTestplanByName(String testplanName);
    
    void update(Testplan testplan);
    
    void remove(long testplanID);  

}