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


@Entity(name = "TestCase")
public class Testcase {

  	@Id    
    @GeneratedValue
    @Column(name="testcaseID")
    private long testcaseID;  
    
    @Basic
    @Length(min = 2, max = 254, message = "Testcase name must be between 2 to 254 characters.")
	@NotEmpty(message = "TestCase Name is required.")
    private String testcaseName;
    
    @Basic
    @Column(name="testplanID")
    private long testplanID;
    @Basic
    @Column(name="companyID")
    private long companyID;    
    
    @Basic    
    private long parentID;  
    
    @Basic
    private String testcaseSection;
    @Basic
    private long testplanOrderNum;
    
    @Basic
    private String level; // Regression/New Feature
    @Basic
    private String stage; // Draft/Ready For Review / Approved
    
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
  	 * @param testplanID
  	 * @param companyID
  	 * @param parentID
  	 * @param testcaseSection
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
  			long parentID, String testcaseSection, long testplanOrderNum,
  			String level, String stage, String testcaseSummary,
  			String testcasePreCondition, String testcaseSteps,
  			String testcasePassCondition, String tester, String seniorTester) {
  		this.testcaseName = testcaseName;
  		this.testplanID = testplanID;
  		this.companyID = companyID;
  		this.parentID = parentID;
  		this.testcaseSection = testcaseSection;
  		this.testplanOrderNum = testplanOrderNum;
  		this.level = level;
  		this.stage = stage;
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
	 * @return the parentID
	 */
	public long getParentID() {
		return parentID;
	}

	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(long parentID) {
		this.parentID = parentID;
	}

	/**
	 * @return the testcaseSection
	 */
	public String getTestcaseSection() {
		return testcaseSection;
	}

	/**
	 * @param testcaseSection the testcaseSection to set
	 */
	public void setTestcaseSection(String testcaseSection) {
		this.testcaseSection = testcaseSection;
	}

	/**
	 * @return the testplanOrderNum
	 */
	public long getTestplanOrderNum() {
		return testplanOrderNum;
	}

	/**
	 * @param testplanOrderNum the testplanOrderNum to set
	 */
	public void setTestplanOrderNum(long testplanOrderNum) {
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

}