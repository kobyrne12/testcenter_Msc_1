/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class ProjectMinSummary {		

	private Long projectID;	   
	private String projectName;  
	private String parentProjectName;
	private int childProjects;
	private int regressionRequiredPercent;
	private int regressionCurrentPercent;
	private int newFeatureRequiredPercent;
	private int newFeatureCurrentPercent;
	private int sanityRequiredPercent;
	private int sanityCurrentPercent;
	private int totalAllowedSev1s;
	private int totalCurrentSev1s;
	private int totalAllowedSev2s;
	private int totalCurrentSev2s;
	private int totalAllowedSev3s;
	private int totalCurrentSev3s;
	private int totalAllowedSev4s;  
	private int totalCurrentSev4s;   
	private int totalDefects;  

	public ProjectMinSummary() {	
	}

	/**
	 * @return the projectID
	 */
	public Long getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the parentProjectName
	 */
	public String getParentProjectName() {
		return parentProjectName;
	}

	/**
	 * @param parentProjectName the parentProjectName to set
	 */
	public void setParentProjectName(String parentProjectName) {
		this.parentProjectName = parentProjectName;
	}

	/**
	 * @return the childProjects
	 */
	public int getChildProjects() {
		return childProjects;
	}

	/**
	 * @param childProjects the childProjects to set
	 */
	public void setChildProjects(int childProjects) {
		this.childProjects = childProjects;
	}

	/**
	 * @return the regressionRequiredPercent
	 */
	public int getRegressionRequiredPercent() {
		return regressionRequiredPercent;
	}

	/**
	 * @param regressionRequiredPercent the regressionRequiredPercent to set
	 */
	public void setRegressionRequiredPercent(int regressionRequiredPercent) {
		this.regressionRequiredPercent = regressionRequiredPercent;
	}

	/**
	 * @return the regressionCurrentPercent
	 */
	public int getRegressionCurrentPercent() {
		return regressionCurrentPercent;
	}

	/**
	 * @param regressionCurrentPercent the regressionCurrentPercent to set
	 */
	public void setRegressionCurrentPercent(int regressionCurrentPercent) {
		this.regressionCurrentPercent = regressionCurrentPercent;
	}

	/**
	 * @return the newFeatureRequiredPercent
	 */
	public int getNewFeatureRequiredPercent() {
		return newFeatureRequiredPercent;
	}

	/**
	 * @param newFeatureRequiredPercent the newFeatureRequiredPercent to set
	 */
	public void setNewFeatureRequiredPercent(int newFeatureRequiredPercent) {
		this.newFeatureRequiredPercent = newFeatureRequiredPercent;
	}

	/**
	 * @return the newFeatureCurrentPercent
	 */
	public int getNewFeatureCurrentPercent() {
		return newFeatureCurrentPercent;
	}

	/**
	 * @param newFeatureCurrentPercent the newFeatureCurrentPercent to set
	 */
	public void setNewFeatureCurrentPercent(int newFeatureCurrentPercent) {
		this.newFeatureCurrentPercent = newFeatureCurrentPercent;
	}

	/**
	 * @return the sanityRequiredPercent
	 */
	public int getSanityRequiredPercent() {
		return sanityRequiredPercent;
	}

	/**
	 * @param sanityRequiredPercent the sanityRequiredPercent to set
	 */
	public void setSanityRequiredPercent(int sanityRequiredPercent) {
		this.sanityRequiredPercent = sanityRequiredPercent;
	}

	/**
	 * @return the sanityCurrentPercent
	 */
	public int getSanityCurrentPercent() {
		return sanityCurrentPercent;
	}

	/**
	 * @param sanityCurrentPercent the sanityCurrentPercent to set
	 */
	public void setSanityCurrentPercent(int sanityCurrentPercent) {
		this.sanityCurrentPercent = sanityCurrentPercent;
	}

	/**
	 * @return the totalAllowedSev1s
	 */
	public int getTotalAllowedSev1s() {
		return totalAllowedSev1s;
	}

	/**
	 * @param totalAllowedSev1s the totalAllowedSev1s to set
	 */
	public void setTotalAllowedSev1s(int totalAllowedSev1s) {
		this.totalAllowedSev1s = totalAllowedSev1s;
	}

	/**
	 * @return the totalCurrentSev1s
	 */
	public int getTotalCurrentSev1s() {
		return totalCurrentSev1s;
	}

	/**
	 * @param totalCurrentSev1s the totalCurrentSev1s to set
	 */
	public void setTotalCurrentSev1s(int totalCurrentSev1s) {
		this.totalCurrentSev1s = totalCurrentSev1s;
	}

	/**
	 * @return the totalAllowedSev2s
	 */
	public int getTotalAllowedSev2s() {
		return totalAllowedSev2s;
	}

	/**
	 * @param totalAllowedSev2s the totalAllowedSev2s to set
	 */
	public void setTotalAllowedSev2s(int totalAllowedSev2s) {
		this.totalAllowedSev2s = totalAllowedSev2s;
	}

	/**
	 * @return the totalCurrentSev2s
	 */
	public int getTotalCurrentSev2s() {
		return totalCurrentSev2s;
	}

	/**
	 * @param totalCurrentSev2s the totalCurrentSev2s to set
	 */
	public void setTotalCurrentSev2s(int totalCurrentSev2s) {
		this.totalCurrentSev2s = totalCurrentSev2s;
	}

	/**
	 * @return the totalAllowedSev3s
	 */
	public int getTotalAllowedSev3s() {
		return totalAllowedSev3s;
	}

	/**
	 * @param totalAllowedSev3s the totalAllowedSev3s to set
	 */
	public void setTotalAllowedSev3s(int totalAllowedSev3s) {
		this.totalAllowedSev3s = totalAllowedSev3s;
	}

	/**
	 * @return the totalCurrentSev3s
	 */
	public int getTotalCurrentSev3s() {
		return totalCurrentSev3s;
	}

	/**
	 * @param totalCurrentSev3s the totalCurrentSev3s to set
	 */
	public void setTotalCurrentSev3s(int totalCurrentSev3s) {
		this.totalCurrentSev3s = totalCurrentSev3s;
	}

	/**
	 * @return the totalAllowedSev4s
	 */
	public int getTotalAllowedSev4s() {
		return totalAllowedSev4s;
	}

	/**
	 * @param totalAllowedSev4s the totalAllowedSev4s to set
	 */
	public void setTotalAllowedSev4s(int totalAllowedSev4s) {
		this.totalAllowedSev4s = totalAllowedSev4s;
	}

	/**
	 * @return the totalCurrentSev4s
	 */
	public int getTotalCurrentSev4s() {
		return totalCurrentSev4s;
	}

	/**
	 * @param totalCurrentSev4s the totalCurrentSev4s to set
	 */
	public void setTotalCurrentSev4s(int totalCurrentSev4s) {
		this.totalCurrentSev4s = totalCurrentSev4s;
	}

	/**
	 * @return the totalDefects
	 */
	public int getTotalDefects() {
		return totalDefects;
	}

	/**
	 * @param totalDefects the totalDefects to set
	 */
	public void setTotalDefects(int totalDefects) {
		this.totalDefects = totalDefects;
	}
}

