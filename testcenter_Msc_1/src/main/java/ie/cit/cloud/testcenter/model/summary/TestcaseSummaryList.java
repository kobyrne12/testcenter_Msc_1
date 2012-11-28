/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class TestcaseSummaryList {	
	
   
    private Set<TestcaseSummary> testcases; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public TestcaseSummaryList() 
    {    		
    	this.setTestcases(testcases);
    }


	/**
	 * @return the testcases
	 */
	public Set<TestcaseSummary> getTestcases() {
		return testcases;
	}


	/**
	 * @param testcases the testcases to set
	 */
	public void setTestcases(Set<TestcaseSummary> testcases) {
		this.testcases = testcases;
	}   
	

  
}