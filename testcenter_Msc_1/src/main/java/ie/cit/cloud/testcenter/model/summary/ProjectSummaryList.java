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
    private int totalNumberOfProjects;     
    private Collection<ProjectSummary> projects; 
    
   
    /**
	 * @return List of Projects for a Company
	 */
    public ProjectSummaryList() 
    {
    	//this.companyID = companyID; 
    	this.setTotalNumberOfProjects(totalNumberOfProjects);      	
    	this.setProjects(projects);
    }
    
	
	/**
	 * @return the totalNumberOfProjects
	 */
	public int getTotalNumberOfProjects() {
		return totalNumberOfProjects;
	}

	/**
	 * @param totalNumberOfProjects the totalNumberOfProjects to set
	 */
	public void setTotalNumberOfProjects(int totalNumberOfProjects) {
		this.totalNumberOfProjects = totalNumberOfProjects;
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