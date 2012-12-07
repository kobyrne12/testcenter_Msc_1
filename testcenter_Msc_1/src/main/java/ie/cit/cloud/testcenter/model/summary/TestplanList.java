/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import ie.cit.cloud.testcenter.model.Testplan;

import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class TestplanList {	
	
   
    private Set<Testplan> testplans; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public TestplanList() 
    {    		
    	this.setTestplans(testplans);
    }


	/**
	 * @return the testplans
	 */
	public Set<Testplan> getTestplans() {
		return testplans;
	}


	/**
	 * @param testplans the testplans to set
	 */
	public void setTestplans(Set<Testplan> testplans) {
		this.testplans = testplans;
	}   
	

  
}