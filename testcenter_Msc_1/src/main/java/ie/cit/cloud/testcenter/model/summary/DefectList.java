/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;


import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class DefectList {	
	
   
    private Set<Defect> defects = new LinkedHashSet<Defect>(); 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public DefectList() 
    {    		
    	this.setDefects(defects);
    }


	/**
	 * @return the defects
	 */
	public Set<Defect> getDefects() {
		return defects;
	}


	/**
	 * @param defects the defects to set
	 */
	public void setDefects(Set<Defect> defects) {
		this.defects = defects;
	}   
	

  
}