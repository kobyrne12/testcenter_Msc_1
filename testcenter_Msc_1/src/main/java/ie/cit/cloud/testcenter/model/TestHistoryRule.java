/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "TestHistoryRule")
public class TestHistoryRule {

	@Id        
	@GeneratedValue
	@Column(name = "testHistoryRuleID")
	private Long testHistoryRuleID;	

	@Basic
	@Column(name="cycleID")
	private Long cycleID;    

	@Length(min = 2, max = 64, message = "Test History Rule Name must be between 2 to 64 characters.")
	@NotEmpty(message = "Test History Rule Name is required.")
	private String testHistoryRuleName;  	
	
	public TestHistoryRule() {		
	}

	/**
	 * @param cycleID
	 * @param testHistoryRuleName
	 */
	public TestHistoryRule(Long cycleID, String testHistoryRuleName) {
		this.cycleID = cycleID;
		this.testHistoryRuleName = testHistoryRuleName;
	}

	/**
	 * @return the cycleID
	 */
	public Long getCycleID() {
		return cycleID;
	}

	/**
	 * @param cycleID the cycleID to set
	 */
	public void setCycleID(Long cycleID) {
		this.cycleID = cycleID;
	}

	/**
	 * @return the testHistoryRuleName
	 */
	public String getTestHistoryRuleName() {
		return testHistoryRuleName;
	}

	/**
	 * @param testHistoryRuleName the testHistoryRuleName to set
	 */
	public void setTestHistoryRuleName(String testHistoryRuleName) {
		this.testHistoryRuleName = testHistoryRuleName;
	}

	/**
	 * @return the testHistoryRuleID
	 */
	public Long getTestHistoryRuleID() {
		return testHistoryRuleID;
	}

	
}