/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;


/**
 * @author byrnek1
 *
 */

public class CycleSummary {	
	
    private long cycleID;
    private String cycleName;
    private long projectID;
    private long companyID;    
	
	private long projectPosition;
	
	private long requiredPriority;
	private long totalCycleEstTime;
	
	private String cycleStartDate;
	private String cycleEndDate;
	
	private String testRuns;  
	private Long testrunsPassed; 
	private Long testrunsFailed; 
	private Long testrunsNotRun; 
	private Long testrunsInProg; 
	private Long testrunsDeferred; 
	private Long testrunsBlocked;
	
	private Long totalNumberOfDefectRules; 
	private Long totalNumberOfTestHistoryRules; 
	private Long totalNumberOfCodeImpactRules; 
	private Long totalNumberOfReqRules; 	 
	
	private String creationDate;   
	private String createdBy; 
	private String lastModifiedDate;	
	private String lastModifiedBy;	
   
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
    
    private String projects;
    private String environments;
    private String requirements;
    private String testcases;
    private String testplans;
    private String testers;
    private String seniorTesters;
    private String developers;
    private String seniorDevelopers;
    
    private String parentCycleName;
    private String childCycles;
    
    private String customObject1;
    private String customObject2;
    private String customObject3;
    private String customObject4;
    private String customObject5;
	
    public CycleSummary() {	
    }    
   
    public CycleSummary(long cycleID) 
    {
    	this.cycleID = cycleID;     	
    } 
    
    /**
	 * @return the cycleID
	 */
	public long getCycleID() {
		return cycleID;
	}

	/**
	 * @param cycleID the cycleID to set
	 */
	public void setCycleID(long cycleID) {
		this.cycleID = cycleID;
	}

	/**
	 * @return the cycleName
	 */
	public String getCycleName() {
		return cycleName;
	}

	/**
	 * @param cycleName the cycleName to set
	 */
	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
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
	 * @return the requiredPriority
	 */
	public Long getRequiredPriority() {
		return requiredPriority;
	}

	/**
	 * @param requiredPriority the requiredPriority to set
	 */
	public void setRequiredPriority(Long requiredPriority) {
		this.requiredPriority = requiredPriority;
	}

	/**
	 * @return the projectPosition
	 */
	public long getProjectPosition() {
		return projectPosition;
	}

	/**
	 * @param projectPosition the projectPosition to set
	 */
	public void setProjectPosition(long projectPosition) {
		this.projectPosition = projectPosition;
	}

	/**
	 * @return the totalCycleEstTime
	 */
	public long getTotalCycleEstTime() {
		return totalCycleEstTime;
	}

	/**
	 * @param totalCycleEstTime the totalCycleEstTime to set
	 */
	public void setTotalCycleEstTime(long totalCycleEstTime) {
		this.totalCycleEstTime = totalCycleEstTime;
	}

	/**
	 * @return the cycleStartDate
	 */
	public String getCycleStartDate() {
		return cycleStartDate;
	}

	/**
	 * @param cycleStartDate the cycleStartDate to set
	 */
	public void setCycleStartDate(String cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}

	/**
	 * @return the cycleEndDate
	 */
	public String getCycleEndDate() {
		return cycleEndDate;
	}

	/**
	 * @param cycleEndDate the cycleEndDate to set
	 */
	public void setCycleEndDate(String cycleEndDate) {
		this.cycleEndDate = cycleEndDate;
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
	 * @return the testRuns
	 */
	public String getTestRuns() {
		return testRuns;
	}

	/**
	 * @param string the testRuns to set
	 */
	public void setTestRuns(String testRuns) {
		this.testRuns = testRuns;
	}

	/**
	 * @return the testrunsPassed
	 */
	public Long getTestrunsPassed() {
		return testrunsPassed;
	}

	/**
	 * @param testrunsPassed the testrunsPassed to set
	 */
	public void setTestrunsPassed(Long testrunsPassed) {
		this.testrunsPassed = testrunsPassed;
	}

	/**
	 * @return the testrunsFailed
	 */
	public Long getTestrunsFailed() {
		return testrunsFailed;
	}

	/**
	 * @param testrunsFailed the testrunsFailed to set
	 */
	public void setTestrunsFailed(Long testrunsFailed) {
		this.testrunsFailed = testrunsFailed;
	}

	/**
	 * @return the testrunsNotRun
	 */
	public Long getTestrunsNotRun() {
		return testrunsNotRun;
	}

	/**
	 * @param testrunsNotRun the testrunsNotRun to set
	 */
	public void setTestrunsNotRun(Long testrunsNotRun) {
		this.testrunsNotRun = testrunsNotRun;
	}

	/**
	 * @return the testrunsInProg
	 */
	public Long getTestrunsInProg() {
		return testrunsInProg;
	}

	/**
	 * @param testrunsInProg the testrunsInProg to set
	 */
	public void setTestrunsInProg(Long testrunsInProg) {
		this.testrunsInProg = testrunsInProg;
	}

	/**
	 * @return the testrunsDeferred
	 */
	public Long getTestrunsDeferred() {
		return testrunsDeferred;
	}

	/**
	 * @param testrunsDeferred the testrunsDeferred to set
	 */
	public void setTestrunsDeferred(Long testrunsDeferred) {
		this.testrunsDeferred = testrunsDeferred;
	}

	/**
	 * @return the testrunsBlocked
	 */
	public Long getTestrunsBlocked() {
		return testrunsBlocked;
	}

	/**
	 * @param testrunsBlocked the testrunsBlocked to set
	 */
	public void setTestrunsBlocked(Long testrunsBlocked) {
		this.testrunsBlocked = testrunsBlocked;
	}

	/**
	 * @param requiredPriority the requiredPriority to set
	 */
	public void setRequiredPriority(long requiredPriority) {
		this.requiredPriority = requiredPriority;
	}

	/**
	 * @return the totalNumberOfDefectRules
	 */
	public Long getTotalNumberOfDefectRules() {
		return totalNumberOfDefectRules;
	}

	/**
	 * @param totalNumberOfDefectRules the totalNumberOfDefectRules to set
	 */
	public void setTotalNumberOfDefectRules(Long totalNumberOfDefectRules) {
		this.totalNumberOfDefectRules = totalNumberOfDefectRules;
	}

	/**
	 * @return the totalNumberOfTestHistoryRules
	 */
	public Long getTotalNumberOfTestHistoryRules() {
		return totalNumberOfTestHistoryRules;
	}

	/**
	 * @param totalNumberOfTestHistoryRules the totalNumberOfTestHistoryRules to set
	 */
	public void setTotalNumberOfTestHistoryRules(Long totalNumberOfTestHistoryRules) {
		this.totalNumberOfTestHistoryRules = totalNumberOfTestHistoryRules;
	}

	/**
	 * @return the totalNumberOfCodeImpactRules
	 */
	public Long getTotalNumberOfCodeImpactRules() {
		return totalNumberOfCodeImpactRules;
	}

	/**
	 * @param totalNumberOfCodeImpactRules the totalNumberOfCodeImpactRules to set
	 */
	public void setTotalNumberOfCodeImpactRules(Long totalNumberOfCodeImpactRules) {
		this.totalNumberOfCodeImpactRules = totalNumberOfCodeImpactRules;
	}

	/**
	 * @return the totalNumberOfReqRules
	 */
	public Long getTotalNumberOfReqRules() {
		return totalNumberOfReqRules;
	}

	/**
	 * @param totalNumberOfReqRules the totalNumberOfReqRules to set
	 */
	public void setTotalNumberOfReqRules(Long totalNumberOfReqRules) {
		this.totalNumberOfReqRules = totalNumberOfReqRules;
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
	 * @return the allowedSev1s
	 */
	public Long getAllowedSev1s() {
		return allowedSev1s;
	}

	/**
	 * @param allowedSev1s the allowedSev1s to set
	 */
	public void setAllowedSev1s(Long allowedSev1s) {
		this.allowedSev1s = allowedSev1s;
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
	 * @return the totalDefects
	 */
	public Long getTotalDefects() {
		return totalDefects;
	}

	/**
	 * @param totalDefects the totalDefects to set
	 */
	public void setTotalDefects(Long totalDefects) {
		this.totalDefects = totalDefects;
	}

	/**
	 * @return the projects
	 */
	public String getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(String projects) {
		this.projects = projects;
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
	 * @return the requirements
	 */
	public String getRequirements() {
		return requirements;
	}

	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(String requirements) {
		this.requirements = requirements;
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
	 * @return the parentCycleName
	 */
	public String getParentCycleName() {
		return parentCycleName;
	}

	/**
	 * @param parentCycleName the parentCycleName to set
	 */
	public void setParentCycleName(String parentCycleName) {
		this.parentCycleName = parentCycleName;
	}

	/**
	 * @return the childCycles
	 */
	public String getChildCycles() {
		return childCycles;
	}

	/**
	 * @param childCycles the childCycles to set
	 */
	public void setChildCycles(String childCycles) {
		this.childCycles = childCycles;
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