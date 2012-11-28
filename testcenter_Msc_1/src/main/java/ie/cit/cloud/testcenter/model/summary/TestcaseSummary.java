/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

import java.util.Date;

import javax.persistence.Basic;

/**
 * @author byrnek1
 *
 */

public class TestcaseSummary {	
	
    private long testcaseID;
    private long companyID;
    private String testcaseName;
    private String testplanName;
	private String testplanSection;	
	private int testplanOrderNum;	
	private String level; // Regression/New Feature	
	private String stage; // Draft/Ready For Review / Approved	
	private Double estimatedTime; // Draft/Ready For Review / Approved
    private int totalCurrentSev1s;
    private int totalCurrentSev2s;
    private int totalCurrentSev3s; 
    private int totalCurrentSev4s;   
    private int totalDefects;  
    private int totalCycles;
    private int totalProjects;
    private int totalEnvironments;
    private int totalRequirements;
    private int totalAllTestruns;
    private int totalRequiredTestruns;
    private int totalOptionalTestruns;   
    private int totalTesters;
    private int totalSeniorTesters;
    private int totalDevelopers;
    private int totalSeniorDevelopers;
    private String lastModifiedBy;
    private Date lastModifiedDate;    
    private String createdBy;
    private Date creationDate; 
    
    private String customObject1;
    private String customObject2;
    private String customObject3;
    private String customObject4;
    private String customObject5;
   // private int level = 0; 			
  //  private Boolean isLeaf = false;
    
    public TestcaseSummary() {	
    }

	/**
	 * @return the testcaseID
	 */
	public long getTestcaseID() {
		return testcaseID;
	}

	/**
	 * @param testcaseID the testcaseID to set
	 */
	public void setTestcaseID(long testcaseID) {
		this.testcaseID = testcaseID;
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
	 * @return the testcaseName
	 */
	public String getTestcaseName() {
		return testcaseName;
	}

	/**
	 * @param testcaseName the testcaseName to set
	 */
	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
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
	 * @return the totalOptionalTestruns
	 */
	public int getTotalOptionalTestruns() {
		return totalOptionalTestruns;
	}

	/**
	 * @param totalOptionalTestruns the totalOptionalTestruns to set
	 */
	public void setTotalOptionalTestruns(int totalOptionalTestruns) {
		this.totalOptionalTestruns = totalOptionalTestruns;
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
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param date the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date date) {
		this.lastModifiedDate = date;
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
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
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

	/**
	 * @return the testplanSection
	 */
	public String getTestplanSection() {
		return testplanSection;
	}

	/**
	 * @param testplanSection the testplanSection to set
	 */
	public void setTestplanSection(String testplanSection) {
		this.testplanSection = testplanSection;
	}

	/**
	 * @return the testplanOrderNum
	 */
	public int getTestplanOrderNum() {
		return testplanOrderNum;
	}

	/**
	 * @param testplanOrderNum the testplanOrderNum to set
	 */
	public void setTestplanOrderNum(int testplanOrderNum) {
		this.testplanOrderNum = testplanOrderNum;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}

	/**
	 * @return the estimatedTime
	 */
	public Double getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * @param estimatedTime the estimatedTime to set
	 */
	public void setEstimatedTime(Double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * @return the testplanName
	 */
	public String getTestplanName() {
		return testplanName;
	}

	/**
	 * @param testplanName the testplanName to set
	 */
	public void setTestplanName(String testplanName) {
		this.testplanName = testplanName;
	}

	/**
	 * @return the totalProjects
	 */
	public int getTotalProjects() {
		return totalProjects;
	}

	/**
	 * @param totalProjects the totalProjects to set
	 */
	public void setTotalProjects(int totalProjects) {
		this.totalProjects = totalProjects;
	}


   
}