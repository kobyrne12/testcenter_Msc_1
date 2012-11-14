/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import javax.persistence.Column;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "Testrun")
public class Testrun {

   
	@Id    
    @GeneratedValue
    @Column(name="testrunID")
    private long testrunID;   
    
    @Basic
    @Length(min = 2, max = 254, message = " name must be between 2 to 254 characters.")
	@NotEmpty(message = " Name is required.")
    private String TestrunName;
    
   /* @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="testplanID",nullable = false)  
    @Transient
    private TestPlan testplan;  */
    
    @Basic
    @Column(name="testcaseID")
    private long testcaseID;
    @Basic
    @Column(name="cycleID")
    private long cycleID;
  
    @Basic
    private boolean notrun;
    @Basic
    private boolean passed;
    @Basic
    private boolean failed;
    @Basic
    private boolean inprogress;
    @Basic
    private boolean deferred;
    @Basic
    private boolean blocked;
    
    @Basic
    private int priority;
    @Basic
    private int recommendedPriority;
    
    @Basic
    private long estimatedTime;
    @Basic
    private String level; // Regression/New Feature
    
  
    @Basic
    private String previousRun;
    @Basic
    private String previousRunDate;
    @Basic
    private String previousRunDateBy;
    
    @Basic    
    private String creationDate;
    @Basic    
    private String createdBy;
    @Basic    
    private String lastModifiedDate;
    @Basic    
    private String lastModifiedBy;
    
    @Basic
    private String tester;
    @Basic
    private String seniorTester;
    
    public Testrun() {	
    }    
   
    /**
   	 * @param testrunName
   	 * @param testcaseID
   	 * @param cycleID
   	 * @param notrun
   	 * @param passed
   	 * @param failed
   	 * @param inprogress
   	 * @param deferred
   	 * @param blocked
   	 * @param priority
   	 * @param recommendedPriority
   	 * @param estimatedTime
   	 * @param level
   	 * @param previousRun
   	 * @param previousRunDate
   	 * @param previousRunDateBy
   	 * @param creationDate
   	 * @param createdBy
   	 * @param lastModifiedDate
   	 * @param lastModifiedBy
   	 * @param tester
   	 * @param seniorTester
   	 */
   	public Testrun(String testrunName, long testcaseID, long cycleID,
   			boolean notrun, boolean passed, boolean failed, boolean inprogress,
   			boolean deferred, boolean blocked, int priority,
   			int recommendedPriority, long estimatedTime, String level,
   			String previousRun, String previousRunDate,
   			String previousRunDateBy, String creationDate, String createdBy,
   			String lastModifiedDate, String lastModifiedBy, String tester,
   			String seniorTester) 
   	{
   		
   		this.TestrunName = testrunName;
   		this.testcaseID = testcaseID;
   		this.cycleID = cycleID;
   		this.notrun = notrun;
   		this.passed = passed;
   		this.failed = failed;
   		this.inprogress = inprogress;
   		this.deferred = deferred;
   		this.blocked = blocked;
   		this.priority = priority;
   		this.recommendedPriority = recommendedPriority;
   		this.estimatedTime = estimatedTime;
   		this.level = level;
   		this.previousRun = previousRun;
   		this.previousRunDate = previousRunDate;
   		this.previousRunDateBy = previousRunDateBy;
   		this.creationDate = creationDate;
   		this.createdBy = createdBy;
   		this.lastModifiedDate = lastModifiedDate;
   		this.lastModifiedBy = lastModifiedBy;
   		this.tester = tester;
   		this.seniorTester = seniorTester;
   	}

	/**
	 * @return the testrunName
	 */
	public String getTestrunName() {
		return TestrunName;
	}

	/**
	 * @param testrunName the testrunName to set
	 */
	public void setTestrunName(String testrunName) {
		TestrunName = testrunName;
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
	 * @return the notrun
	 */
	public boolean isNotrun() {
		return notrun;
	}

	/**
	 * @param notrun the notrun to set
	 */
	public void setNotrun(boolean notrun) {
		this.notrun = notrun;
	}

	/**
	 * @return the passed
	 */
	public boolean isPassed() {
		return passed;
	}

	/**
	 * @param passed the passed to set
	 */
	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	/**
	 * @return the failed
	 */
	public boolean isFailed() {
		return failed;
	}

	/**
	 * @param failed the failed to set
	 */
	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	/**
	 * @return the inprogress
	 */
	public boolean isInprogress() {
		return inprogress;
	}

	/**
	 * @param inprogress the inprogress to set
	 */
	public void setInprogress(boolean inprogress) {
		this.inprogress = inprogress;
	}

	/**
	 * @return the deferred
	 */
	public boolean isDeferred() {
		return deferred;
	}

	/**
	 * @param deferred the deferred to set
	 */
	public void setDeferred(boolean deferred) {
		this.deferred = deferred;
	}

	/**
	 * @return the blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * @param blocked the blocked to set
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the recommendedPriority
	 */
	public int getRecommendedPriority() {
		return recommendedPriority;
	}

	/**
	 * @param recommendedPriority the recommendedPriority to set
	 */
	public void setRecommendedPriority(int recommendedPriority) {
		this.recommendedPriority = recommendedPriority;
	}

	/**
	 * @return the estimatedTime
	 */
	public long getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * @param estimatedTime the estimatedTime to set
	 */
	public void setEstimatedTime(long estimatedTime) {
		this.estimatedTime = estimatedTime;
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
	 * @return the previousRun
	 */
	public String getPreviousRun() {
		return previousRun;
	}

	/**
	 * @param previousRun the previousRun to set
	 */
	public void setPreviousRun(String previousRun) {
		this.previousRun = previousRun;
	}

	/**
	 * @return the previousRunDate
	 */
	public String getPreviousRunDate() {
		return previousRunDate;
	}

	/**
	 * @param previousRunDate the previousRunDate to set
	 */
	public void setPreviousRunDate(String previousRunDate) {
		this.previousRunDate = previousRunDate;
	}

	/**
	 * @return the previousRunDateBy
	 */
	public String getPreviousRunDateBy() {
		return previousRunDateBy;
	}

	/**
	 * @param previousRunDateBy the previousRunDateBy to set
	 */
	public void setPreviousRunDateBy(String previousRunDateBy) {
		this.previousRunDateBy = previousRunDateBy;
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
	 * @return the tester
	 */
	public String getTester() {
		return tester;
	}

	/**
	 * @param tester the tester to set
	 */
	public void setTester(String tester) {
		this.tester = tester;
	}

	/**
	 * @return the seniorTester
	 */
	public String getSeniorTester() {
		return seniorTester;
	}

	/**
	 * @param seniorTester the seniorTester to set
	 */
	public void setSeniorTester(String seniorTester) {
		this.seniorTester = seniorTester;
	}

	/**
	 * @return the testrunID
	 */
	public long getTestrunID() {
		return testrunID;
	}

}