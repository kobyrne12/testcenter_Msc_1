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

@Entity(name = "RequirementRule")
public class RequirementRule {

	@Id        
	@GeneratedValue
	@Column(name = "requirementRuleID")
	private Long requirementRuleID;	

	@Basic
	@Column(name="cycleID")
	private Long cycleID;    

	@Length(min = 2, max = 64, message = "Requirement Rule Name must be between 2 to 64 characters.")
	@NotEmpty(message = "Requirement Rule Name is required.")
	private String requirementRuleName;  	
	
	public RequirementRule() {		
	}

	/**
	 * @param cycleID
	 * @param requirementRuleName
	 */
	public RequirementRule(Long cycleID, String requirementRuleName) {
		this.cycleID = cycleID;
		this.requirementRuleName = requirementRuleName;
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
	 * @return the requirementRuleName
	 */
	public String getRequirementRuleName() {
		return requirementRuleName;
	}

	/**
	 * @param requirementRuleName the requirementRuleName to set
	 */
	public void setRequirementRuleName(String requirementRuleName) {
		this.requirementRuleName = requirementRuleName;
	}

	/**
	 * @return the requirementRuleID
	 */
	public Long getRequirementRuleID() {
		return requirementRuleID;
	}

}