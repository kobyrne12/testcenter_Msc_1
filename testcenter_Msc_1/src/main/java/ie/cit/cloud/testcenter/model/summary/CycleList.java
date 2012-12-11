/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Environment;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class CycleList {	
	
   
    private Set<Cycle> cycles = new LinkedHashSet<Cycle>();  
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public CycleList() 
    {    		
    	this.setCycles(cycles);
    }


	/**
	 * @return the cycles
	 */
	public Set<Cycle> getCycles() {
		return cycles;
	}


	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(Set<Cycle> cycles) {
		this.cycles = cycles;
	}   
	

  
}