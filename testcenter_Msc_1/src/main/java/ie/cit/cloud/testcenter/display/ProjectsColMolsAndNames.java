/**
 * 
 */
package ie.cit.cloud.testcenter.display;


import java.util.Collection;

/**
 * @author byrnek1
 *
 */

public class ProjectsColMolsAndNames {	
	
     
    private Collection<ProjectsDisplay> colModel; 
    private Collection<String> colNames;
   
    /**
	 * @return Column Model List for project Page
	 */
    public ProjectsColMolsAndNames() 
    {
    	
    }


	/**
	 * @return the colModel
	 */
	public Collection<ProjectsDisplay> getColModel() {
		return colModel;
	}


	/**
	 * @param colModel the colModel to set
	 */
	public void setColModel(Collection<ProjectsDisplay> colModel) {
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