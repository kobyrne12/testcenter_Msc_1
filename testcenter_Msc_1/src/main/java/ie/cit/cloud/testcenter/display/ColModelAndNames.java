/**
 * 
 */
package ie.cit.cloud.testcenter.display;


import java.util.Collection;

/**
 * @author byrnek1
 *
 */

public class ColModelAndNames {	
	
     
    private Collection<GridAttributes> colModel; 
    private Collection<String> colNames;
   
    /**
	 * @return Column Model List for project Page
	 */
    public ColModelAndNames() 
    {
    	
    }


	/**
	 * @return the colModel
	 */
	public Collection<GridAttributes> getColModel() {
		return colModel;
	}


	/**
	 * @param colModel the colModel to set
	 */
	public void setColModel(Collection<GridAttributes> colModel) {
		this.colModel = colModel;
	}


	/**
	 * @return the colName
	 */
	public Collection<String> getColName() {
		return colNames;
	}


	/**
	 * @param colName the colName to set
	 */
	public void setColName(Collection<String> colNames) {
		this.colNames = colNames;
	}



}