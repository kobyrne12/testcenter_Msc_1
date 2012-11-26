/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class ProjectSummaryList {	
	
    //private long companyID;   
    private Set<ProjectSummary> projects; 
    
   
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
	public Set<ProjectSummary> getProjects() {
		return projects;
	}

	/**
	 * @param projectSummaries the projectSummaries to set
	 */
	public void setProjects(Set<ProjectSummary> projects) {
		this.projects = projects;
	}	
  
}