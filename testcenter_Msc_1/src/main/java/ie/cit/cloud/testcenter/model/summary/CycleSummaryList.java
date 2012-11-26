/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class CycleSummaryList {		
   
    private Set<CycleSummary> cycles;     
   
    /**
	 * @return List of Cycles for a Company
	 */
    public CycleSummaryList() 
    {    	
       	this.setCycles(cycles);
    }

	/**
	 * @return the cycles
	 */
	public Set<CycleSummary> getCycles() {
		return cycles;
	}


	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(Set<CycleSummary> cycles) {
		this.cycles = cycles;
	}
    
	

	
  
}