/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import ie.cit.cloud.testcenter.model.Testcase;

import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class TestcaseList {	
	
   
    private Set<Testcase> testcases; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public TestcaseList() 
    {    		
    	this.setTestcases(testcases);
    }


	/**
	 * @return the testcases
	 */
	public Set<Testcase> getTestcases() {
		return testcases;
	}


	/**
	 * @param testcases the testcases to set
	 */
	public void setTestcases(Set<Testcase> testcases) {
		this.testcases = testcases;
	}   
	

  
}