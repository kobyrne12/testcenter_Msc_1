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
    private String childProjects;
    private Long regressionRequiredPercent;
    private Long regressionCurrentPercent;
    private Long newFeatureRequiredPercent;
    private Long newFeatureCurrentPercent;
    private Long allowedSev1s;
    private Long currentSev1s;
    private Long allowedSev2s;
    private Long currentSev2s;
    private Long allowedSev3s;
    private Long currentSev3s;
    private Long allowedSev4s;  
    private Long currentSev4s;   
    private Long totalDefects;  
    private long companyID;
    private String cycles;
    private String environments;
    private String requirements;
    private String testRuns;
    private String testcases;
    private String testplans;
    private String testers;
    private String seniorTesters;
    private String developers;
    private String seniorDevelopers;
    private String lastModifiedBy;
    private String lastModifiedDate;    
    private String createdBy;
    private String creationDate;
    private String parentProjectName;
    
    private String customObject1;
    private String customObject2;
    private String customObject3;
    private String customObject4;
    private String customObject5;
   // private Long level = 0; 			
  //  private Boolean isLeaf = false;
    
    public ProjectSummary() {	
    }    
   
    public ProjectSummary(long projectID) 
    {
    	this.projectID = projectID;     	
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
	 * @return the childProjects
	 */
	public String getChildProjects() {
		return childProjects;
	}

	/**
	 * @param childProjects the childProjects to set
	 */
	public void setChildProjects(String childProjects) {
		this.childProjects = childProjects;
	}

	/**
	 * @return the regressionRequiredPercent
	 */
	public Long getRegressionRequiredPercent() {
		return regressionRequiredPercent;
	}

	/**
	 * @param regressionRequiredPercent the regressionRequiredPercent to set
	 */
	public void setRegressionRequiredPercent(Long regressionRequiredPercent) {
		this.regressionRequiredPercent = regressionRequiredPercent;
	}

	/**
	 * @return the regressionCurrentPercent
	 */
	public Long getRegressionCurrentPercent() {
		return regressionCurrentPercent;
	}

	/**
	 * @param regressionCurrentPercent the regressionCurrentPercent to set
	 */
	public void setRegressionCurrentPercent(Long regressionCurrentPercent) {
		this.regressionCurrentPercent = regressionCurrentPercent;
	}

	/**
	 * @return the newFeatureRequiredPercent
	 */
	public Long getNewFeatureRequiredPercent() {
		return newFeatureRequiredPercent;
	}

	/**
	 * @param newFeatureRequiredPercent the newFeatureRequiredPercent to set
	 */
	public void setNewFeatureRequiredPercent(Long newFeatureRequiredPercent) {
		this.newFeatureRequiredPercent = newFeatureRequiredPercent;
	}

	

	/**
	 * @return the allowedSev1s
	 */
	public Long getAllowedSev1s() {
		return allowedSev1s;
	}

	/**
	 * @param long1 the allowedSev1s to set
	 */
	public void setAllowedSev1s(Long long1) {
		this.allowedSev1s = long1;
	}

	/**
	 * @return the currentSev1s
	 */
	public Long getCurrentSev1s() {
		return currentSev1s;
	}

	/**
	 * @param currentSev1s the currentSev1s to set
	 */
	public void setCurrentSev1s(Long currentSev1s) {
		this.currentSev1s = currentSev1s;
	}

	/**
	 * @return the allowedSev2s
	 */
	public Long getAllowedSev2s() {
		return allowedSev2s;
	}

	/**
	 * @param allowedSev2s the allowedSev2s to set
	 */
	public void setAllowedSev2s(Long allowedSev2s) {
		this.allowedSev2s = allowedSev2s;
	}

	/**
	 * @return the currentSev2s
	 */
	public Long getCurrentSev2s() {
		return currentSev2s;
	}

	/**
	 * @param currentSev2s the currentSev2s to set
	 */
	public void setCurrentSev2s(Long currentSev2s) {
		this.currentSev2s = currentSev2s;
	}

	/**
	 * @return the allowedSev3s
	 */
	public Long getAllowedSev3s() {
		return allowedSev3s;
	}

	/**
	 * @param allowedSev3s the allowedSev3s to set
	 */
	public void setAllowedSev3s(Long allowedSev3s) {
		this.allowedSev3s = allowedSev3s;
	}

	/**
	 * @return the currentSev3s
	 */
	public Long getCurrentSev3s() {
		return currentSev3s;
	}

	/**
	 * @param currentSev3s the currentSev3s to set
	 */
	public void setCurrentSev3s(Long currentSev3s) {
		this.currentSev3s = currentSev3s;
	}

	/**
	 * @return the allowedSev4s
	 */
	public Long getAllowedSev4s() {
		return allowedSev4s;
	}

	/**
	 * @param allowedSev4s the allowedSev4s to set
	 */
	public void setAllowedSev4s(Long allowedSev4s) {
		this.allowedSev4s = allowedSev4s;
	}

	/**
	 * @return the currentSev4s
	 */
	public Long getCurrentSev4s() {
		return currentSev4s;
	}

	/**
	 * @param currentSev4s the currentSev4s to set
	 */
	public void setCurrentSev4s(Long currentSev4s) {
		this.currentSev4s = currentSev4s;
	}

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID2 the companyID to set
	 */
	public void setCompanyID(long companyID2) {
		this.companyID = companyID2;
	}

	/**
	 * @return the cycles
	 */
	public String getCycles() {
		return cycles;
	}

	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(String cycles) {
		this.cycles = cycles;
	}

	/**
	 * @return the environments
	 */
	public String getEnvironments() {
		return environments;
	}

	/**
	 * @param environments the environments to set
	 */
	public void setEnvironments(String environments) {
		this.environments = environments;
	}

	/**
	 * @return the testRuns
	 */
	public String getTestRuns() {
		return testRuns;
	}

	/**
	 * @param testRuns the testRuns to set
	 */
	public void setTestRuns(String testRuns) {
		this.testRuns = testRuns;
	}

	/**
	 * @return the testcases
	 */
	public String getTestcases() {
		return testcases;
	}

	/**
	 * @param testcases the testcases to set
	 */
	public void setTestcases(String testcases) {
		this.testcases = testcases;
	}

	/**
	 * @return the testplans
	 */
	public String getTestplans() {
		return testplans;
	}

	/**
	 * @param testplans the testplans to set
	 */
	public void setTestplans(String testplans) {
		this.testplans = testplans;
	}

	/**
	 * @return the testers
	 */
	public String getTesters() {
		return testers;
	}

	/**
	 * @param testers the testers to set
	 */
	public void setTesters(String testers) {
		this.testers = testers;
	}

	/**
	 * @return the seniorTesters
	 */
	public String getSeniorTesters() {
		return seniorTesters;
	}

	/**
	 * @param seniorTesters the seniorTesters to set
	 */
	public void setSeniorTesters(String seniorTesters) {
		this.seniorTesters = seniorTesters;
	}

	/**
	 * @return the developers
	 */
	public String getDevelopers() {
		return developers;
	}

	/**
	 * @param developers the developers to set
	 */
	public void setDevelopers(String developers) {
		this.developers = developers;
	}

	/**
	 * @return the seniorDevelopers
	 */
	public String getSeniorDevelopers() {
		return seniorDevelopers;
	}

	/**
	 * @param seniorDevelopers the seniorDevelopers to set
	 */
	public void setSeniorDevelopers(String seniorDevelopers) {
		this.seniorDevelopers = seniorDevelopers;
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
	 * @return the newFeatureCurrentPercent
	 */
	public Long getNewFeatureCurrentPercent() {
		return newFeatureCurrentPercent;
	}

	/**
	 * @param newFeatureCurrentPercent the newFeatureCurrentPercent to set
	 */
	public void setNewFeatureCurrentPercent(Long newFeatureCurrentPercent) {
		this.newFeatureCurrentPercent = newFeatureCurrentPercent;
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

	public Long getTotalDefects() {
		return totalDefects;
	}

	public void setTotalDefects(Long totalDefects) {
		this.totalDefects = totalDefects;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	
  
}