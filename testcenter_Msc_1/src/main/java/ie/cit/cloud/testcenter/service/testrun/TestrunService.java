/**
 * 
 */
package ie.cit.cloud.testcenter.service.testrun;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Testrun;

/**
 * Peforms business operation on Test run 
 */
public interface TestrunService {
 
	void addNewTestrun(Testrun testrun);

    Testrun getTestrun(long testrunID);   
    
    Testrun getTestrunByName(String testrunName);
    
    void update(Testrun testrun);
    
    void remove(long testrunID);  

}