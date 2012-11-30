/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TESTRUNS_JOIN_DEFECTS", joinColumns = { @JoinColumn(name = "testrunID") }, inverseJoinColumns = { @JoinColumn(name = "defectID") })
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Defect> defects  = new HashSet<Defect>();    	

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TESTRUNS_JOIN_REQUIREMENTS", joinColumns = { @JoinColumn(name = "testrunID") }, inverseJoinColumns = { @JoinColumn(name = "requirementID") })
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Requirement> requirements  = new HashSet<Requirement>();    	

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TESTRUNS_JOIN_ENVIRONMENTS", joinColumns = { @JoinColumn(name = "testrunID") }, inverseJoinColumns = { @JoinColumn(name = "environmentID") })
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Environment> environments  = new HashSet<Environment>();    	

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TESTRUNS_JOIN_USERS", joinColumns = { @JoinColumn(name = "testrunID") }, inverseJoinColumns = { @JoinColumn(name = "userID") })
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<TestcenterUser> users  = new HashSet<TestcenterUser>(); 
    
    @OneToOne(fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
   	private TestrunLevel level; 
    
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
    private Double estimatedTime;
    
    @Basic
    private long previousTestrunID; 
    
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
   	 * @param estimatedTime
   	 * @param level
   	 * @param previousTestrunID   
   	 * @param creationDate
   	 * @param createdBy
   	 * @param lastModifiedDate
   	 * @param lastModifiedBy
   	 * @param tester
   	 * @param seniorTester
   	 */
   	public Testrun(String testrunName, long testcaseID, long cycleID, 
   			Double estimatedTime,TestrunLevel level,long previousTestrunID, String tester,String seniorTester)   
   	{
   		this(testrunName,testcaseID,cycleID,
   				false,false,false,false,false,false,1,1,
   				estimatedTime, level, previousTestrunID,
   				"CURRENT_DATE", "CURRENT_USER", "CURRENT_DATE","CURRENT_USER",
   				tester,seniorTester   		
   				);
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
   	 * @param previousTestrunID   
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
   			int recommendedPriority, Double estimatedTime, TestrunLevel level,
   			long previousTestrunID,String creationDate, String createdBy,
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
   		this.previousTestrunID = previousTestrunID;   		
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
	 * @return the level
	 */
	public TestrunLevel getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(TestrunLevel level) {
		this.level = level;
	}

	/**
	 * @return the previousRun
	 */
	public long getPreviousTestrunID() {
		return previousTestrunID;
	}

	/**
	 * @param previousRun the previousRun to set
	 */
	public void setPreviousTestrunID(long previousTestrunID) {
		this.previousTestrunID = previousTestrunID;
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

	/**
	 * @return the requirements
	 */
	public Set<Requirement> getRequirements() {
		return requirements;
	}

	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}

	/**
	 * @return the environments
	 */
	public Set<Environment> getEnvironments() {
		return environments;
	}

	/**
	 * @param environments the environments to set
	 */
	public void setEnvironments(Set<Environment> environments) {
		this.environments = environments;
	}

	/**
	 * @return the users
	 */
	public Set<TestcenterUser> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<TestcenterUser> users) {
		this.users = users;
	}
	/**
	 * @return the defects
	 */
	public Set<Defect> getDefects( ) {
		return defects;
	}
	/**
	 * @param defects the defects to set
	 */
	public void setDefects(Set<Defect> defects) {
		this.defects = defects;
	}

}