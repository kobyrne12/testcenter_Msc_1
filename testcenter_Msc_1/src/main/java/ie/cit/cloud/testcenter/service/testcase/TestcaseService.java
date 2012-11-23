/**
 * 
 */
package ie.cit.cloud.testcenter.service.testcase;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Testcase;

/**
 * Peforms business operation on Test case 
 */
public interface TestcaseService {
 
	void addNewTestcase(Testcase testcase);

    Testcase getTestcase(long testcaseID);   
    
    Testcase getTestcaseByName(String testcaseName);
    
    void update(Testcase testcase);
    
    void remove(long testcaseID);  
    
    long getLastTestRunID(long testcaseID);

}