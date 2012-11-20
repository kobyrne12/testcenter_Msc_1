/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

/**
 * @author byrnek1
 *
 */

public class ProjectSummary {	
	
    private long projectID;	   
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
    private long companyID;
    private int totalCycles;
    private int totalEnvironments;
    private int totalRequirements;
    private int totalRequiredTestruns;
    private int totalAllTestruns;
    private int totalTestcases;
    private int totalTestplans;
    private int totalTesters;
    private int totalSeniorTesters;
    private int totalDevelopers;
    private int totalSeniorDevelopers;
    private String lastModifiedBy;
    private String lastModifiedDate;    
    private String createdBy;
    private String creationDate;
 
    
    private String customObject1;
    private String customObject2;
    private String customObject3;
    private String customObject4;
    private String customObject5;
   // private int level = 0; 			
  //  private Boolean isLeaf = false;
    
    public ProjectSummary() {	
    }

	/**
	 * @return the projectID
	 */
	public long getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(long projectID) {
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

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the totalCycles
	 */
	public int getTotalCycles() {
		return totalCycles;
	}

	/**
	 * @param totalCycles the totalCycles to set
	 */
	public void setTotalCycles(int totalCycles) {
		this.totalCycles = totalCycles;
	}

	/**
	 * @return the totalEnvironments
	 */
	public int getTotalEnvironments() {
		return totalEnvironments;
	}

	/**
	 * @param totalEnvironments the totalEnvironments to set
	 */
	public void setTotalEnvironments(int totalEnvironments) {
		this.totalEnvironments = totalEnvironments;
	}

	/**
	 * @return the totalRequirements
	 */
	public int getTotalRequirements() {
		return totalRequirements;
	}

	/**
	 * @param totalRequirements the totalRequirements to set
	 */
	public void setTotalRequirements(int totalRequirements) {
		this.totalRequirements = totalRequirements;
	}

	/**
	 * @return the totalRequiredTestruns
	 */
	public int getTotalRequiredTestruns() {
		return totalRequiredTestruns;
	}

	/**
	 * @param totalRequiredTestruns the totalRequiredTestruns to set
	 */
	public void setTotalRequiredTestruns(int totalRequiredTestruns) {
		this.totalRequiredTestruns = totalRequiredTestruns;
	}

	/**
	 * @return the totalAllTestruns
	 */
	public int getTotalAllTestruns() {
		return totalAllTestruns;
	}

	/**
	 * @param totalAllTestruns the totalAllTestruns to set
	 */
	public void setTotalAllTestruns(int totalAllTestruns) {
		this.totalAllTestruns = totalAllTestruns;
	}

	/**
	 * @return the totalTestcases
	 */
	public int getTotalTestcases() {
		return totalTestcases;
	}

	/**
	 * @param totalTestcases the totalTestcases to set
	 */
	public void setTotalTestcases(int totalTestcases) {
		this.totalTestcases = totalTestcases;
	}

	/**
	 * @return the totalTestplans
	 */
	public int getTotalTestplans() {
		return totalTestplans;
	}

	/**
	 * @param totalTestplans the totalTestplans to set
	 */
	public void setTotalTestplans(int totalTestplans) {
		this.totalTestplans = totalTestplans;
	}

	/**
	 * @return the totalTesters
	 */
	public int getTotalTesters() {
		return totalTesters;
	}

	/**
	 * @param totalTesters the totalTesters to set
	 */
	public void setTotalTesters(int totalTesters) {
		this.totalTesters = totalTesters;
	}

	/**
	 * @return the totalSeniorTesters
	 */
	public int getTotalSeniorTesters() {
		return totalSeniorTesters;
	}

	/**
	 * @param totalSeniorTesters the totalSeniorTesters to set
	 */
	public void setTotalSeniorTesters(int totalSeniorTesters) {
		this.totalSeniorTesters = totalSeniorTesters;
	}

	/**
	 * @return the totalDevelopers
	 */
	public int getTotalDevelopers() {
		return totalDevelopers;
	}

	/**
	 * @param totalDevelopers the totalDevelopers to set
	 */
	public void setTotalDevelopers(int totalDevelopers) {
		this.totalDevelopers = totalDevelopers;
	}

	/**
	 * @return the totalSeniorDevelopers
	 */
	public int getTotalSeniorDevelopers() {
		return totalSeniorDevelopers;
	}

	/**
	 * @param totalSeniorDevelopers the totalSeniorDevelopers to set
	 */
	public void setTotalSeniorDevelopers(int totalSeniorDevelopers) {
		this.totalSeniorDevelopers = totalSeniorDevelopers;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the customObject1
	 */
	public String getCustomObject1() {
		return customObject1;
	}

	/**
	 * @param customObject1 the customObject1 to set
	 */
	public void setCustomObject1(String customObject1) {
		this.customObject1 = customObject1;
	}

	/**
	 * @return the customObject2
	 */
	public String getCustomObject2() {
		return customObject2;
	}

	/**
	 * @param customObject2 the customObject2 to set
	 */
	public void setCustomObject2(String customObject2) {
		this.customObject2 = customObject2;
	}

	/**
	 * @return the customObject3
	 */
	public String getCustomObject3() {
		return customObject3;
	}

	/**
	 * @param customObject3 the customObject3 to set
	 */
	public void setCustomObject3(String customObject3) {
		this.customObject3 = customObject3;
	}

	/**
	 * @return the customObject4
	 */
	public String getCustomObject4() {
		return customObject4;
	}

	/**
	 * @param customObject4 the customObject4 to set
	 */
	public void setCustomObject4(String customObject4) {
		this.customObject4 = customObject4;
	}

	/**
	 * @return the customObject5
	 */
	public String getCustomObject5() {
		return customObject5;
	}

	/**
	 * @param customObject5 the customObject5 to set
	 */
	public void setCustomObject5(String customObject5) {
		this.customObject5 = customObject5;
	}    
   
   
}