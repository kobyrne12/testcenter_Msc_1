/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import ie.cit.cloud.testcenter.model.Requirement;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class RequirementList {	
	
   
    private Set<Requirement> requirements = new LinkedHashSet<Requirement>();  
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public RequirementList() 
    {    		
    	this.setRequirements(requirements);
    }


	/**
	 * @return the requirements
	 */
	public Set<Requirement> getRequirements() {
		return requirements;
	}


	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}   
	

  
}