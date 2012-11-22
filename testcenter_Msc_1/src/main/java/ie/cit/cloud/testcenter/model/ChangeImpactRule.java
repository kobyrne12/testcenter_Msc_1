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

@Entity(name = "ChangeImpactRule")
public class ChangeImpactRule {

	@Id
	@GeneratedValue
	@Column(name = "changeImpactRuleID")
	private Long changeImpactRuleID;

	@Basic
	@Column(name = "cycleID")
	private long cycleID;

	@Length(min = 2, max = 64, message = "Change Impact Rule Name must be between 2 to 64 characters.")
	@NotEmpty(message = "Chanage impact Rule Name is required.")
	private String changeImpactRuleName;

	public ChangeImpactRule() {
	}

	/**
	 * @param cycleID
	 * @param changeImpactRuleName
	 */
	public ChangeImpactRule(long cycleID, String changeImpactRuleName) {
		this.cycleID = cycleID;
		this.changeImpactRuleName = changeImpactRuleName;
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
	 * @return the changeImpactRuleName
	 */
	public String getChangeImpactRuleName() {
		return changeImpactRuleName;
	}

	/**
	 * @param changeImpactRuleName the changeImpactRuleName to set
	 */
	public void setChangeImpactRuleName(String changeImpactRuleName) {
		this.changeImpactRuleName = changeImpactRuleName;
	}

	/**
	 * @return the changeImpactRuleID
	 */
	public Long getChangeImpactRuleID() {
		return changeImpactRuleID;
	}

}