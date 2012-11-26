/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class ProjectMinSummaryList {	

	//private long companyID;   
	private Set<ProjectMinSummary> projects; 


	/**
	 * @return List of Projects for a Company
	 */
	public ProjectMinSummaryList() 
	{    		
		this.setProjects(projects);
	}   


	/**
	 * @return the projectSummaries
	 */
	public Set<ProjectMinSummary> getProjects() {
		return projects;
	}

	/**
	 * @param projectSummaries the projectSummaries to set
	 */
	public void setProjects(Set<ProjectMinSummary> projects2) {
		this.projects = projects2;
	}	

}