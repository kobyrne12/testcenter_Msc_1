/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class EnvironmentSummaryList {	
	
   
    private Set<EnvironmentSummary> environments; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public EnvironmentSummaryList() 
    {    		
    	this.setEnvironments(environments);
    }


	/**
	 * @return the environments
	 */
	public Set<EnvironmentSummary> getEnvironments() {
		return environments;
	}


	/**
	 * @param environments the environments to set
	 */
	public void setEnvironments(Set<EnvironmentSummary> environments) {
		this.environments = environments;
	}   
	

  
}