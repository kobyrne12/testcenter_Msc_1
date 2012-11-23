/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.ArrayList;
import java.util.Collection;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "Testcase")
public class Testcase {

	@Id    
	@GeneratedValue
	@Column(name="testcaseID")
	private long testcaseID;  

	@Basic
	@Column(name="companyID")
	private long companyID;    

	@Basic
	@Length(min = 2, max = 254, message = "Testcase name must be between 2 to 254 characters.")
	@NotEmpty(message = "TestCase Name is required.")
	private String testcaseName;

	@Basic
	@Column(name="testplanID")
	private long testplanID;
	
	@ManyToMany(mappedBy="testcases", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Collection<Project> projects = new ArrayList<Project>();
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Testrun.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "testcaseID", referencedColumnName="testcaseID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Testrun> testruns = new ArrayList<Testrun>();   
  
	@Basic
	private long testplanSectionID;
	@Basic
	private int testplanOrderNum;

	@Basic
	private String level; // Regression/New Feature
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
	private String seniorTester;

	
	public Testcase() {	
	}

	/**
	 * @param testcaseName
	 * @param companyID		
	 * @param level
	 * @param stage	
	 * @param testcaseSummary
	 * @param testcasePreCondition
	 * @param testcaseSteps
	 * @param testcasePassCondition
	 * @param tester
	 * @param seniorTester	
	 */	
	public Testcase(String testcaseName, long companyID,
			
			String level, String stage,
			String testcaseSummary,	String testcasePreCondition, String testcaseSteps,String testcasePassCondition,
			String tester, String seniorTester)
	{
		this(testcaseName,0,companyID,0,0,level, stage,0.15,
				testcaseSummary,testcasePreCondition,testcaseSteps,testcasePassCondition,
				tester,seniorTester,null);
	}
	
	/**
	 * @param testcaseName
	 * @param testplanID
	 * @param companyID	
	 * @param testplanSectionID
	 * @param testplanOrderNum
	 * @param level
	 * @param stage	
	 * @param testcaseSummary
	 * @param testcasePreCondition
	 * @param testcaseSteps
	 * @param testcasePassCondition
	 * @param tester
	 * @param seniorTester	
	 */
	
	public Testcase(String testcaseName, long testplanID, long companyID,
			long testplanSectionID,int testplanOrderNum,
			String level, String stage, Double estimatedTime,
			String testcaseSummary,	String testcasePreCondition, String testcaseSteps,String testcasePassCondition,
			String tester, String seniorTester)
	{
		this(testcaseName,testplanID,companyID,
				testplanSectionID,testplanOrderNum,
				level, stage, 0.15,
				testcaseSummary,testcasePreCondition,testcaseSteps,testcasePassCondition,
				tester,seniorTester,null);
	}
	
	/**
	 * @param testcaseName
	 * @param testplanID
	 * @param companyID	
	 * @param testcaseSection
	 * @param testplanOrderNum
	 * @param level
	 * @param stage
	 * @param estimatedTime
	 * @param testcaseSummary
	 * @param testcasePreCondition
	 * @param testcaseSteps
	 * @param testcasePassCondition
	 * @param tester
	 * @param seniorTester
	 * @param testruns 
	 */
	public Testcase(String testcaseName, long testplanID, long companyID,
			long testplanSectionID, int testplanOrderNum,
			String level, String stage, Double estimatedTime, String testcaseSummary,
			String testcasePreCondition, String testcaseSteps,
			String testcasePassCondition, String tester, String seniorTester, Collection<Testrun> testruns) {
		this.testcaseName = testcaseName;
		this.testplanID = testplanID;
		this.testruns = testruns;
		this.companyID = companyID;		
		this.testplanSectionID = testplanSectionID;
		this.testplanOrderNum = testplanOrderNum;
		this.level = level;
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
	public long getTestplanID() {
		return testplanID;
	}

	/**
	 * @param testplanID the testplanID to set
	 */
	public void setTestplanID(long testplanID) {
		this.testplanID = testplanID;
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
	public long getTestcaseID() {
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
	 * @return the testruns
	 */
	public Collection<Testrun> getTestruns() {
		return testruns;
	}
	/**
	 * @param testruns the testruns to set
	 */
	public void setTestruns(Collection<Testrun> testruns) {
		this.testruns = testruns;
	}

	/**
	 * @return the testplanSectionID
	 */
	public long getTestplanSectionID() {
		return testplanSectionID;
	}

	/**
	 * @param testplanSectionID the testplanSectionID to set
	 */
	public void setTestplanSectionID(long testplanSectionID) {
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
	public Collection<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}

	

}