/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

/**
 * @author byrnek1
 *
 */

public class CycleSummary {	
	
    private long cycleID;	   
    private int totalNumberOfTestRuns;   
    
    public CycleSummary() {	
    }    
   
    public CycleSummary(long cycleID) 
    {
    	this.cycleID = cycleID; 
    	this.setTotalNumberOfTestRuns(totalNumberOfTestRuns);    	
    }

	/**
	 * @return the cycleID
	 */
	public long getCycleID() {
		return cycleID;
	}

	/**
	 * @param cycleID the cycleID to set
	 */
	public void setCycleID(long cycleID) {
		this.cycleID = cycleID;
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
  
}