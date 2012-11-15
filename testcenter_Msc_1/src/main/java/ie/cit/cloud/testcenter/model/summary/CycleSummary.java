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
	
	private int projectPosition;	
	private int requiredPriority;	
	
	private int requiredTestruns;  
	private int allTestruns;  	
	private int testrunsPassed; 
	private int testrunsFailed; 
	private int testrunsNotRun; 
	private int testrunsInProg; 
	private int testrunsDeferred; 
	private int testrunsBlocked;
	private int testrunsCompleted; 
	private int testrunsNotCompleted; 
	
	private double totalCycleEstTime;
	private String cycleStartDate;
	private String cycleEndDate;
	
	private int totalNumberOfDefectRules; 
	private int totalNumberOfTestHistoryRules; 
	private int totalNumberOfCodeImpactRules; 
	private int totalNumberOfReqRules; 	 
	
	private String creationDate;   
	private String createdBy; 
	private String lastModifiedDate;	
	private String lastModifiedBy;	   
    
    private int allowedSev1s;
    private int currentSev1s;
    private int allowedSev2s;
    private int currentSev2s;
    private int allowedSev3s;  
    private int currentSev3s;
    private int allowedSev4s;  
    private int currentSev4s;   
    private int totalDefects;  
    
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
    private int childCycles;
    
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
	 * @return the testrunsPassed
	 */
	public int getTestrunsPassed() {
		return testrunsPassed;
	}

	/**
	 * @param testrunsPassed the testrunsPassed to set
	 */
	public void setTestrunsPassed(int testrunsPassed) {
		this.testrunsPassed = testrunsPassed;
	}

	/**
	 * @return the testrunsFailed
	 */
	public int getTestrunsFailed() {
		return testrunsFailed;
	}

	/**
	 * @param testrunsFailed the testrunsFailed to set
	 */
	public void setTestrunsFailed(int testrunsFailed) {
		this.testrunsFailed = testrunsFailed;
	}

	/**
	 * @return the testrunsNotRun
	 */
	public int getTestrunsNotRun() {
		return testrunsNotRun;
	}

	/**
	 * @param testrunsNotRun the testrunsNotRun to set
	 */
	public void setTestrunsNotRun(int testrunsNotRun) {
		this.testrunsNotRun = testrunsNotRun;
	}

	/**
	 * @return the testrunsInProg
	 */
	public int getTestrunsInProg() {
		return testrunsInProg;
	}

	/**
	 * @param testrunsInProg the testrunsInProg to set
	 */
	public void setTestrunsInProg(int testrunsInProg) {
		this.testrunsInProg = testrunsInProg;
	}

	/**
	 * @return the testrunsDeferred
	 */
	public int getTestrunsDeferred() {
		return testrunsDeferred;
	}

	/**
	 * @param testrunsDeferred the testrunsDeferred to set
	 */
	public void setTestrunsDeferred(int testrunsDeferred) {
		this.testrunsDeferred = testrunsDeferred;
	}

	/**
	 * @return the testrunsBlocked
	 */
	public int getTestrunsBlocked() {
		return testrunsBlocked;
	}

	/**
	 * @param testrunsBlocked the testrunsBlocked to set
	 */
	public void setTestrunsBlocked(int testrunsBlocked) {
		this.testrunsBlocked = testrunsBlocked;
	}

	/**
	 * @return the totalNumberOfDefectRules
	 */
	public int getTotalNumberOfDefectRules() {
		return totalNumberOfDefectRules;
	}

	/**
	 * @param totalNumberOfDefectRules the totalNumberOfDefectRules to set
	 */
	public void setTotalNumberOfDefectRules(int totalNumberOfDefectRules) {
		this.totalNumberOfDefectRules = totalNumberOfDefectRules;
	}

	/**
	 * @return the totalNumberOfTestHistoryRules
	 */
	public int getTotalNumberOfTestHistoryRules() {
		return totalNumberOfTestHistoryRules;
	}

	/**
	 * @param totalNumberOfTestHistoryRules the totalNumberOfTestHistoryRules to set
	 */
	public void setTotalNumberOfTestHistoryRules(int totalNumberOfTestHistoryRules) {
		this.totalNumberOfTestHistoryRules = totalNumberOfTestHistoryRules;
	}

	/**
	 * @return the totalNumberOfCodeImpactRules
	 */
	public int getTotalNumberOfCodeImpactRules() {
		return totalNumberOfCodeImpactRules;
	}

	/**
	 * @param totalNumberOfCodeImpactRules the totalNumberOfCodeImpactRules to set
	 */
	public void setTotalNumberOfCodeImpactRules(int totalNumberOfCodeImpactRules) {
		this.totalNumberOfCodeImpactRules = totalNumberOfCodeImpactRules;
	}

	/**
	 * @return the totalNumberOfReqRules
	 */
	public int getTotalNumberOfReqRules() {
		return totalNumberOfReqRules;
	}

	/**
	 * @param totalNumberOfReqRules the totalNumberOfReqRules to set
	 */
	public void setTotalNumberOfReqRules(int totalNumberOfReqRules) {
		this.totalNumberOfReqRules = totalNumberOfReqRules;
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
	 * @return the allowedSev1s
	 */
	public int getAllowedSev1s() {
		return allowedSev1s;
	}

	/**
	 * @param allowedSev1s the allowedSev1s to set
	 */
	public void setAllowedSev1s(int allowedSev1s) {
		this.allowedSev1s = allowedSev1s;
	}

	/**
	 * @return the currentSev1s
	 */
	public int getCurrentSev1s() {
		return currentSev1s;
	}

	/**
	 * @param currentSev1s the currentSev1s to set
	 */
	public void setCurrentSev1s(int currentSev1s) {
		this.currentSev1s = currentSev1s;
	}

	/**
	 * @return the allowedSev2s
	 */
	public int getAllowedSev2s() {
		return allowedSev2s;
	}

	/**
	 * @param allowedSev2s the allowedSev2s to set
	 */
	public void setAllowedSev2s(int allowedSev2s) {
		this.allowedSev2s = allowedSev2s;
	}

	/**
	 * @return the currentSev2s
	 */
	public int getCurrentSev2s() {
		return currentSev2s;
	}

	/**
	 * @param currentSev2s the currentSev2s to set
	 */
	public void setCurrentSev2s(int currentSev2s) {
		this.currentSev2s = currentSev2s;
	}

	/**
	 * @return the allowedSev3s
	 */
	public int getAllowedSev3s() {
		return allowedSev3s;
	}

	/**
	 * @param allowedSev3s the allowedSev3s to set
	 */
	public void setAllowedSev3s(int allowedSev3s) {
		this.allowedSev3s = allowedSev3s;
	}

	/**
	 * @return the currentSev3s
	 */
	public int getCurrentSev3s() {
		return currentSev3s;
	}

	/**
	 * @param currentSev3s the currentSev3s to set
	 */
	public void setCurrentSev3s(int currentSev3s) {
		this.currentSev3s = currentSev3s;
	}

	/**
	 * @return the allowedSev4s
	 */
	public int getAllowedSev4s() {
		return allowedSev4s;
	}

	/**
	 * @param allowedSev4s the allowedSev4s to set
	 */
	public void setAllowedSev4s(int allowedSev4s) {
		this.allowedSev4s = allowedSev4s;
	}

	/**
	 * @return the currentSev4s
	 */
	public int getCurrentSev4s() {
		return currentSev4s;
	}

	/**
	 * @param currentSev4s the currentSev4s to set
	 */
	public void setCurrentSev4s(int currentSev4s) {
		this.currentSev4s = currentSev4s;
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
	public int getChildCycles() {
		return childCycles;
	}

	/**
	 * @param childCycles the childCycles to set
	 */
	public void setChildCycles(int childCycles) {
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

	/**
	 * @return the requiredTestruns
	 */
	public int getRequiredTestruns() {
		return requiredTestruns;
	}

	/**
	 * @param requiredTestruns the requiredTestruns to set
	 */
	public void setRequiredTestruns(int requiredTestruns) {
		this.requiredTestruns = requiredTestruns;
	}

	/**
	 * @return the allTestruns
	 */
	public int getAllTestruns() {
		return allTestruns;
	}

	/**
	 * @param allTestruns the allTestruns to set
	 */
	public void setAllTestruns(int allTestruns) {
		this.allTestruns = allTestruns;
	}

	/**
	 * @return the testrunsCompleted
	 */
	public int getTestrunsCompleted() {
		return testrunsCompleted;
	}

	/**
	 * @param testrunsCompleted the testrunsCompleted to set
	 */
	public void setTestrunsCompleted(int testrunsCompleted) {
		this.testrunsCompleted = testrunsCompleted;
	}

	/**
	 * @return the testrunsNoCompleted
	 */
	public int getTestrunsNotCompleted() {
		return testrunsNotCompleted;
	}

	/**
	 * @param testrunsNoCompleted the testrunsNoCompleted to set
	 */
	public void setTestrunsNotCompleted(int testrunsNotCompleted) {
		this.testrunsNotCompleted = testrunsNotCompleted;
	}	
   
}