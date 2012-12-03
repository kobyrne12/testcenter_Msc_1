/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class TestplanSummaryList {	
	
   
    private Set<TestplanSummary> testplans; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public TestplanSummaryList() 
    {    		
    	this.setTestplans(testplans);
    }


	/**
	 * @return the testplans
	 */
	public Set<TestplanSummary> getTestplans() {
		return testplans;
	}


	/**
	 * @param testcases the testplans to set
	 */
	public void setTestplans(Set<TestplanSummary> testplans) {
		this.testplans = testplans;
	}   
	

  
}