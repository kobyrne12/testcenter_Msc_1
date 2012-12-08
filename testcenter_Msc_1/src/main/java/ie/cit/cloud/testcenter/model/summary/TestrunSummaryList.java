/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class TestrunSummaryList {	
	
   
    private Set<TestrunSummary> testruns = new LinkedHashSet<TestrunSummary>(); 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public TestrunSummaryList() 
    {    		
    	this.setTestruns(testruns);
    }


	/**
	 * @return the testruns
	 */
	public Set<TestrunSummary> getTestruns() {
		return testruns;
	}


	/**
	 * @param testruns the testruns to set
	 */
	public void setTestruns(Set<TestrunSummary> testruns) {
		this.testruns = testruns;
	}   
	

  
}