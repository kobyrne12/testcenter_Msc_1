/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Requirement;


import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class EnvironmentList {	
	
   
    private Set<Environment> environments = new LinkedHashSet<Environment>(); 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public EnvironmentList() 
    {    		
    	this.setEnvironments(environments);
    }


	/**
	 * @return the environments
	 */
	public Set<Environment> getEnvironments() {
		return environments;
	}


	/**
	 * @param environments the environments to set
	 */
	public void setEnvironments(Set<Environment> environments) {
		this.environments = environments;
	}   
	

  
}