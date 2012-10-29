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
	
    //private long companyID;	   
    private int totalNumberOfCycles;      
    private int totalNumberOfTestRuns; 
    private Collection<CycleSummary> cycles; 
    
   
    /**
	 * @return List of Cycles for a Company
	 */
    public CycleSummaryList() 
    {    	
    	this.setTotalNumberOfCycles(totalNumberOfCycles); 
    	this.setTotalNumberOfTestRuns(totalNumberOfTestRuns);
    	this.setCycles(cycles);
    }


	/**
	 * @return the totalNumberOfCycles
	 */
	public int getTotalNumberOfCycles() {
		return totalNumberOfCycles;
	}


	/**
	 * @param totalNumberOfCycles the totalNumberOfCycles to set
	 */
	public void setTotalNumberOfCycles(int totalNumberOfCycles) {
		this.totalNumberOfCycles = totalNumberOfCycles;
	}


	/**
	 * @return the totalNumberOfTestRuns
	 */
	public int getTotalNumberOfTestRuns() {
		return totalNumberOfTestRuns;
	}


	/**
	 * @param totalNumberOfTestRuns the totalNumberOfTestRuns to set
	 */
	public void setTotalNumberOfTestRuns(int totalNumberOfTestRuns) {
		this.totalNumberOfTestRuns = totalNumberOfTestRuns;
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