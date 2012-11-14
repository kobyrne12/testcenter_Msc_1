/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Collection;

/**
 * @author byrnek1
 *
 */

public class CycleSummaryList {		
   
    private Collection<CycleSummary> cycles;     
   
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
	public Collection<CycleSummary> getCycles() {
		return cycles;
	}


	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(Collection<CycleSummary> cycles) {
		this.cycles = cycles;
	}
    
	

	
  
}