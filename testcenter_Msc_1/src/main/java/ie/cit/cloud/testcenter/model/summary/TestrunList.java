/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import ie.cit.cloud.testcenter.model.Testrun;

import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class TestrunList {	
	
   
    private Set<Testrun> testruns; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public TestrunList() 
    {    		
    	this.setTestruns(testruns);
    }


	/**
	 * @return the testruns
	 */
	public Set<Testrun> getTestruns() {
		return testruns;
	}


	/**
	 * @param testruns the testruns to set
	 */
	public void setTestruns(Set<Testrun> testruns) {
		this.testruns = testruns;
	}   
	

  
}