/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class DefectSummaryList {	
	
   
    private Set<DefectSummary> defects; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public DefectSummaryList() 
    {    		
    	this.setDefects(defects);
    }


	/**
	 * @return the defects
	 */
	public Set<DefectSummary> getDefects() {
		return defects;
	}


	/**
	 * @param defects the defects to set
	 */
	public void setDefects(Set<DefectSummary> defects) {
		this.defects = defects;
	}   
	

  
}