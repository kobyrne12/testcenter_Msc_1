/**
 * 
 */
package ie.cit.cloud.testcenter.service.environment;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Environment;

/**
 * Peforms business operation on environment 
 */
public interface EnvironmentService {
 
	void addNewEnvironment(Environment environment);

	Environment getEnvironment(long environmentID);   
    
	Environment getEnvironmentByName(String environmentName);
    
    void update(Environment environment);
    
    void remove(long environmentID);  

}