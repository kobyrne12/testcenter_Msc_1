/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class RequirementSummaryList {	
	
   
    private Set<RequirementSummary> requirements; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public RequirementSummaryList() 
    {    		
    	this.setRequirements(requirements);
    }


	/**
	 * @return the requirements
	 */
	public Set<RequirementSummary> getRequirements() {
		return requirements;
	}


	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(Set<RequirementSummary> requirements) {
		this.requirements = requirements;
	}   
	

  
}