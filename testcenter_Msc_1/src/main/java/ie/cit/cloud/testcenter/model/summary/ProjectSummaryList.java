/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Collection;

/**
 * @author byrnek1
 *
 */

public class ProjectSummaryList {	
	
    //private long companyID;   
    private Collection<ProjectSummary> projects; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public ProjectSummaryList() 
    {    		
    	this.setProjects(projects);
    }   
	
	
	/**
	 * @return the projectSummaries
	 */
	public Collection<ProjectSummary> getProjects() {
		return projects;
	}

	/**
	 * @param projectSummaries the projectSummaries to set
	 */
	public void setProjects(Collection<ProjectSummary> projects) {
		this.projects = projects;
	}	
  
}