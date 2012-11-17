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
    private String projectName;
    private long companyID;    
	
	private int projectPosition;	
	private int requiredPriority;	
	
	private int totalRequiredTestruns;  
	private int totalAllTestruns;  	
	private int totalTestrunsPassed; 
	private int totalTestrunsFailed; 
	private int totalTestrunsNotRun; 
	private int totalTestrunsInProg; 
	private int totalTestrunsDeferred; 
	private int totalTestrunsBlocked;
	private int totalTestrunsCompleted; 
	private int totalTestrunsNotCompleted; 
	
	private double totalCycleEstTime;
	private String cycleStartDate;
	private String cycleEndDate;
	
	private int totalDefectRules; 
	private int totalTestHistoryRules;
	private int totalCodeImpactRules; 
	private int totalReqRules; 	 
	
	private String creationDate;   
	private String createdBy; 
	private String lastModifiedDate;	
	private String lastModifiedBy;	   
    
    private int totalAllowedSev1s;
    private int totalCurrentSev1s;
    private int totalAllowedSev2s;
    private int totalCurrentSev2s;
    private int totalAllowedSev3s;  
    private int totalCurrentSev3s;
    private int totalAllowedSev4s;  
    private int totalCurrentSev4s;   
    private int totalDefects;  
    
    private int totalEnvironments;
    private int totalRequirements;
    private int totalTestcases;
    private int totalTestplans;
    private int totalTesters;
    private int totalSeniorTesters;
    private int totalDevelopers;
    private int totalSeniorDevelopers;
    
    private String parentCycleName;
    private int totalChildCycles;
    
//    private String customObject1;
//    private String customObject2;
//    private String customObject3;
//    private String customObject4;
//    private String customObject5;
	
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
	 * @return the projectPosition
	 */
	public int getProjectPosition() {
		return projectPosition;
	}

	/**
	 * @param projectPosition the projectPosition to set
	 */
	public void setProjectPosition(int projectPosition) {
		this.projectPosition = projectPosition;
	}

	/**
	 * @return the requiredPriority
	 */
	public int getRequiredPriority() {
		return requiredPriority;
	}

	/**
	 * @param requiredPriority the requiredPriority to set
	 */
	public void setRequiredPriority(int requiredPriority) {
		this.requiredPriority = requiredPriority;
	}

	/**
	 * @return the totalCycleEstTime
	 */
	public double getTotalCycleEstTime() {
		return totalCycleEstTime;
	}

	/**
	 * @param totalCycleEstTime the totalCycleEstTime to set
	 */
	public void setTotalCycleEstTime(double totalCycleEstTime) {
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
	 * @return the totalTestrunsPassed
	 */
	public int getTotalTestrunsPassed() {
		return totalTestrunsPassed;
	}

	/**
	 * @param totalTestrunsPassed the totalTestrunsPassed to set
	 */
	public void setTotalTestrunsPassed(int totalTestrunsPassed) {
		this.totalTestrunsPassed = totalTestrunsPassed;
	}

	/**
	 * @return the totalTestrunsFailed
	 */
	public int getTotalTestrunsFailed() {
		return totalTestrunsFailed;
	}

	/**
	 * @param totalTestrunsFailed the totalTestrunsFailed to set
	 */
	public void setTotalTestrunsFailed(int totalTestrunsFailed) {
		this.totalTestrunsFailed = totalTestrunsFailed;
	}

	/**
	 * @return the totalTestrunsNotRun
	 */
	public int getTotalTestrunsNotRun() {
		return totalTestrunsNotRun;
	}

	/**
	 * @param totalTestrunsNotRun the totalTestrunsNotRun to set
	 */
	public void setTotalTestrunsNotRun(int totalTestrunsNotRun) {
		this.totalTestrunsNotRun = totalTestrunsNotRun;
	}

	/**
	 * @return the totalTestrunsInProg
	 */
	public int getTotalTestrunsInProg() {
		return totalTestrunsInProg;
	}

	/**
	 * @param totalTestrunsInProg the totalTestrunsInProg to set
	 */
	public void setTotalTestrunsInProg(int totalTestrunsInProg) {
		this.totalTestrunsInProg = totalTestrunsInProg;
	}

	/**
	 * @return the totalTestrunsDeferred
	 */
	public int getTotalTestrunsDeferred() {
		return totalTestrunsDeferred;
	}

	/**
	 * @param totalTestrunsDeferred the totalTestrunsDeferred to set
	 */
	public void setTotalTestrunsDeferred(int totalTestrunsDeferred) {
		this.totalTestrunsDeferred = totalTestrunsDeferred;
	}

	/**
	 * @return the totalTestrunsBlocked
	 */
	public int getTotalTestrunsBlocked() {
		return totalTestrunsBlocked;
	}

	/**
	 * @param totalTestrunsBlocked the totalTestrunsBlocked to set
	 */
	public void setTotalTestrunsBlocked(int totalTestrunsBlocked) {
		this.totalTestrunsBlocked = totalTestrunsBlocked;
	}

	/**
	 * @return the totalTestrunsCompleted
	 */
	public int getTotalTestrunsCompleted() {
		return totalTestrunsCompleted;
	}

	/**
	 * @param totalTestrunsCompleted the totalTestrunsCompleted to set
	 */
	public void setTotalTestrunsCompleted(int totalTestrunsCompleted) {
		this.totalTestrunsCompleted = totalTestrunsCompleted;
	}

	/**
	 * @return the totalTestrunsNotCompleted
	 */
	public int getTotalTestrunsNotCompleted() {
		return totalTestrunsNotCompleted;
	}

	/**
	 * @param totalTestrunsNotCompleted the totalTestrunsNotCompleted to set
	 */
	public void setTotalTestrunsNotCompleted(int totalTestrunsNotCompleted) {
		this.totalTestrunsNotCompleted = totalTestrunsNotCompleted;
	}

	/**
	 * @return the totalDefectRules
	 */
	public int getTotalDefectRules() {
		return totalDefectRules;
	}

	/**
	 * @param totalDefectRules the totalDefectRules to set
	 */
	public void setTotalDefectRules(int totalDefectRules) {
		this.totalDefectRules = totalDefectRules;
	}

	/**
	 * @return the totalTestHistoryRules
	 */
	public int getTotalTestHistoryRules() {
		return totalTestHistoryRules;
	}

	/**
	 * @param totalTestHistoryRules the totalTestHistoryRules to set
	 */
	public void setTotalTestHistoryRules(int totalTestHistoryRules) {
		this.totalTestHistoryRules = totalTestHistoryRules;
	}

	/**
	 * @return the totalCodeImpactRules
	 */
	public int getTotalCodeImpactRules() {
		return totalCodeImpactRules;
	}

	/**
	 * @param totalCodeImpactRules the totalCodeImpactRules to set
	 */
	public void setTotalCodeImpactRules(int totalCodeImpactRules) {
		this.totalCodeImpactRules = totalCodeImpactRules;
	}

	/**
	 * @return the totalReqRules
	 */
	public int getTotalReqRules() {
		return totalReqRules;
	}

	/**
	 * @param totalReqRules the totalReqRules to set
	 */
	public void setTotalReqRules(int totalReqRules) {
		this.totalReqRules = totalReqRules;
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
	 * @return the totalTotalDefects
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
	 * @return the totalChildCycles
	 */
	public int getTotalChildCycles() {
		return totalChildCycles;
	}

	/**
	 * @param totalChildCycles the totalChildCycles to set
	 */
	public void setTotalChildCycles(int totalChildCycles) {
		this.totalChildCycles = totalChildCycles;
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
//	/**
//	 * @return the customObject1
//	 */
//	public String getCustomObject1() {
//		return customObject1;
//	}
//
//	/**
//	 * @param customObject1 the customObject1 to set
//	 */
//	public void setCustomObject1(String customObject1) {
//		this.customObject1 = customObject1;
//	}
//
//	/**
//	 * @return the customObject2
//	 */
//	public String getCustomObject2() {
//		return customObject2;
//	}
//
//	/**
//	 * @param customObject2 the customObject2 to set
//	 */
//	public void setCustomObject2(String customObject2) {
//		this.customObject2 = customObject2;
//	}
//
//	/**
//	 * @return the customObject3
//	 */
//	public String getCustomObject3() {
//		return customObject3;
//	}
//
//	/**
//	 * @param customObject3 the customObject3 to set
//	 */
//	public void setCustomObject3(String customObject3) {
//		this.customObject3 = customObject3;
//	}
//
//	/**
//	 * @return the customObject4
//	 */
//	public String getCustomObject4() {
//		return customObject4;
//	}
//
//	/**
//	 * @param customObject4 the customObject4 to set
//	 */
//	public void setCustomObject4(String customObject4) {
//		this.customObject4 = customObject4;
//	}
//
//	/**
//	 * @return the customObject5
//	 */
//	public String getCustomObject5() {
//		return customObject5;
//	}
//
//	/**
//	 * @param customObject5 the customObject5 to set
//	 */
//	public void setCustomObject5(String customObject5) {
//		this.customObject5 = customObject5;
//	}
	

	
}