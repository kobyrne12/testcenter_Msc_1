/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.Date;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "Testcase")
public class Testcase {

	@Id    
	@GeneratedValue
	@Column(name="testcaseID")
	private Long testcaseID;  

	@Basic
	@Column(name="companyID")
	private Long companyID;
	
	@Basic
	@Length(min = 2, max = 254, message = "Testcase name must be between 2 to 254 characters.")
	@NotEmpty(message = "TestCase Name is required.")
	private String testcaseName;

	@Basic
	@Column(name="testplanID")
	private Long testplanID;
	
//	@Transient
	@ManyToMany(mappedBy="testcases", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
    private Set<Project> projects = new HashSet<Project>();
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Testrun.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "testcaseID", referencedColumnName="testcaseID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Testrun> testruns = new HashSet<Testrun>();   
  
	@Basic
	private Long testplanSectionID;
	@Basic
	private int testplanOrderNum;

	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private TestrunLevel testrunLevel; 
	
	@Basic
	private String stage; // Draft/Ready For Review / Approved
	@Basic
	private Double estimatedTime; // Draft/Ready For Review / Approved
	
	@Basic
	private String testcaseSummary;
	@Basic
	private String testcasePreCondition;
	@Basic
	@Column(length=10000)
	private String testcaseSteps;
	@Basic
	private String testcasePassCondition;
	@Basic
	private String tester;
	@Basic
	private String seniorTester ;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "creationDate", nullable = false, length = 10) 
	private Date creationDate = new Date();	
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate", nullable = false, length = 10) 
	private Date lastModifiedDate = new Date();

	@Basic    
	private Long createdByUserID;
	@Basic    
	private Long lastModifiedByUserID;
	
	public Testcase() {	
	}
	
	/**
	 * @param companyID		
	 * @param testplanID	
	 * @param testcaseName		
	 * @param level
	 * @param stage	
	 * @param testcaseSummary
	 * @param testcasePreCondition
	 * @param testcaseSteps
	 * @param testcasePassCondition
	 * @param tester
	 * @param seniorTester
	 */	
	public Testcase(Long companyID,Long testplanID, String testcaseName,TestrunLevel level, String stage,
			String testcaseSummary,	String testcasePreCondition, String testcaseSteps,
			String testcasePassCondition,String tester, String seniorTester)
	{
		this(companyID,testplanID, testcaseName,level,stage,0.15,
				testcaseSummary,testcasePreCondition,testcaseSteps,testcasePassCondition,
				tester,seniorTester);
	}

	
	/**
	 * @param companyID
	 * @param testplanID
	 * @param testcaseName	
	 * @param testruns
	 * @param level
	 * @param stage
	 * @param estimatedTime
	 * @param testcaseSummary
	 * @param testcasePreCondition
	 * @param testcaseSteps
	 * @param testcasePassCondition
	 * @param tester
	 * @param seniorTester
	 */
	public Testcase(Long companyID, Long testplanID, String testcaseName, TestrunLevel testrunLevel,
			String stage, Double estimatedTime, String testcaseSummary,
			String testcasePreCondition, String testcaseSteps,
			String testcasePassCondition, String tester, String seniorTester) {
	
		this.companyID = companyID;		
		this.testplanID= testplanID; 
		this.testcaseName = testcaseName;		
		this.testrunLevel = testrunLevel;
		this.stage = stage;
		this.estimatedTime = estimatedTime;
		this.testcaseSummary = testcaseSummary;
		this.testcasePreCondition = testcasePreCondition;
		this.testcaseSteps = testcaseSteps;
		this.testcasePassCondition = testcasePassCondition;
		this.tester = tester;
		this.seniorTester = seniorTester;
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
	 * @return the testplanID
	 */
	public Long getTestplanID() {
		return testplanID;
	}

	/**
	 * @param testplanID the testplanID to set
	 */
	public void setTestplanID(Long testplanID) {
		this.testplanID = testplanID;
	}

	/**
	 * @return the companyID
	 */
	public Long getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the testcaseSummary
	 */
	public String getTestcaseSummary() {
		return testcaseSummary;
	}

	/**
	 * @param testcaseSummary the testcaseSummary to set
	 */
	public void setTestcaseSummary(String testcaseSummary) {
		this.testcaseSummary = testcaseSummary;
	}

	/**
	 * @return the testcasePreCondition
	 */
	public String getTestcasePreCondition() {
		return testcasePreCondition;
	}

	/**
	 * @param testcasePreCondition the testcasePreCondition to set
	 */
	public void setTestcasePreCondition(String testcasePreCondition) {
		this.testcasePreCondition = testcasePreCondition;
	}

	/**
	 * @return the testcaseSteps
	 */
	public String getTestcaseSteps() {
		return testcaseSteps;
	}

	/**
	 * @param testcaseSteps the testcaseSteps to set
	 */
	public void setTestcaseSteps(String testcaseSteps) {
		this.testcaseSteps = testcaseSteps;
	}

	/**
	 * @return the testcasePassCondition
	 */
	public String getTestcasePassCondition() {
		return testcasePassCondition;
	}

	/**
	 * @param testcasePassCondition the testcasePassCondition to set
	 */
	public void setTestcasePassCondition(String testcasePassCondition) {
		this.testcasePassCondition = testcasePassCondition;
	}

	/**
	 * @return the tester
	 */
	public String getTester() {
		return tester;
	}

	/**
	 * @param string the tester to set
	 */
	public void setTester(String string) {
		this.tester = string;
	}

	/**
	 * @return the testcaseID
	 */
	public Long getTestcaseID() {
		return testcaseID;
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
	public TestrunLevel getTestrunLevel() {
		return testrunLevel;
	}

	/**
	 * @param level the testrunLevel to set
	 */
	public void setTestrunLevel(TestrunLevel testrunLevel) {
		this.testrunLevel = testrunLevel;
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
	 * @return the testruns
	 */
	public Set<Testrun> getTestruns() {
		return testruns;
	}
	/**
	 * @param testruns the testruns to set
	 */
	public void setTestruns(Set<Testrun> testruns) {
		this.testruns = testruns;
	}

	/**
	 * @return the testplanSectionID
	 */
	public Long getTestplanSectionID() {
		return testplanSectionID;
	}

	/**
	 * @param testplanSectionID the testplanSectionID to set
	 */
	public void setTestplanSectionID(Long testplanSectionID) {
		this.testplanSectionID = testplanSectionID;
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
	 * @return the projects
	 */
	public Set<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
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
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the createdByUserID
	 */
	public Long getCreatedByUserID() {
		return createdByUserID;
	}

	/**
	 * @param createdByUserID the createdByUserID to set
	 */
	public void setCreatedByUserID(Long createdByUserID) {
		this.createdByUserID = createdByUserID;
	}

	/**
	 * @return the lastModifiedByUserID
	 */
	public Long getLastModifiedByUserID() {
		return lastModifiedByUserID;
	}

	/**
	 * @param lastModifiedByUserID the lastModifiedByUserID to set
	 */
	public void setLastModifiedByUserID(Long lastModifiedByUserID) {
		this.lastModifiedByUserID = lastModifiedByUserID;
	}



}