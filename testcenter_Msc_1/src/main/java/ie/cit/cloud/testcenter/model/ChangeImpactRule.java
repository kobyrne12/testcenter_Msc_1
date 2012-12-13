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
	private Long cycleID;

	@Length(min = 2, max = 64, message = "Change Impact Rule Name must be between 2 to 64 characters.")
	@NotEmpty(message = "Chanage impact Rule Name is required.")
	private String changeImpactRuleName;
	
	@Basic	
	private String changeImpactRuleLevel;
	@Basic	
	private int changeImpactRuleRequirementPriority;
	@Basic	
	private String changeImpactRuleTestrunChoice;
	@Basic	
	private int changeImpactRuleTestrunPriority;

	
	public ChangeImpactRule() {
	}

	

	/**
	 * @param cycleID
	 * @param changeImpactRuleName
	 * @param changeImpactRuleLevel
	 * @param changeImpactRuleRequirementPriority
	 * @param changeImpactRuleTestrunChoice
	 * @param changeImpactRuleTestrunPriority
	 */
	public ChangeImpactRule(Long cycleID, String changeImpactRuleName,
			String changeImpactRuleLevel,
			int changeImpactRuleRequirementPriority,
			String changeImpactRuleTestrunChoice,
			int changeImpactRuleTestrunPriority) {
		this.cycleID = cycleID;
		this.changeImpactRuleName = changeImpactRuleName;
		this.changeImpactRuleLevel = changeImpactRuleLevel;
		this.changeImpactRuleRequirementPriority = changeImpactRuleRequirementPriority;
		this.changeImpactRuleTestrunChoice = changeImpactRuleTestrunChoice;
		this.changeImpactRuleTestrunPriority = changeImpactRuleTestrunPriority;
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

	/**
	 * @return the changeImpactRuleLevel
	 */
	public String getChangeImpactRuleLevel() {
		return changeImpactRuleLevel;
	}

	/**
	 * @param changeImpactRuleLevel the changeImpactRuleLevel to set
	 */
	public void setChangeImpactRuleLevel(String changeImpactRuleLevel) {
		this.changeImpactRuleLevel = changeImpactRuleLevel;
	}

	/**
	 * @return the changeImpactRuleRequirementPriority
	 */
	public int getChangeImpactRuleRequirementPriority() {
		return changeImpactRuleRequirementPriority;
	}

	/**
	 * @param changeImpactRuleRequirementPriority the changeImpactRuleRequirementPriority to set
	 */
	public void setChangeImpactRuleRequirementPriority(
			int changeImpactRuleRequirementPriority) {
		this.changeImpactRuleRequirementPriority = changeImpactRuleRequirementPriority;
	}

	/**
	 * @return the changeImpactRuleTestrunChoice
	 */
	public String getChangeImpactRuleTestrunChoice() {
		return changeImpactRuleTestrunChoice;
	}

	/**
	 * @param changeImpactRuleTestrunChoice the changeImpactRuleTestrunChoice to set
	 */
	public void setChangeImpactRuleTestrunChoice(
			String changeImpactRuleTestrunChoice) {
		this.changeImpactRuleTestrunChoice = changeImpactRuleTestrunChoice;
	}

	/**
	 * @return the changeImpactRuleTestrunPriority
	 */
	public int getChangeImpactRuleTestrunPriority() {
		return changeImpactRuleTestrunPriority;
	}

	/**
	 * @param changeImpactRuleTestrunPriority the changeImpactRuleTestrunPriority to set
	 */
	public void setChangeImpactRuleTestrunPriority(
			int changeImpactRuleTestrunPriority) {
		this.changeImpactRuleTestrunPriority = changeImpactRuleTestrunPriority;
	}


}