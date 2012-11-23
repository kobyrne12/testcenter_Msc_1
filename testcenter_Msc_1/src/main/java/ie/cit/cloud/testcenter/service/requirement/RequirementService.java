/**
 * 
 */
package ie.cit.cloud.testcenter.service.requirement;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Requirement;

/**
 * Peforms business operation on requirement 
 */
public interface RequirementService {
 
	void addNewRequirement(Requirement requirement);

	Requirement getRequirement(long requirementID);   
    
	Requirement getRequirementByName(String requirementName);
    
    void update(Requirement requirement);
    
    void remove(long requirementID);  

}