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

@Entity(name = "DefectRule")
public class DefectRule {

	@Id        
	@GeneratedValue
	@Column(name = "defectRuleID")
	private Long defectRuleID;	

	@Basic
	@Column(name="cycleID")
	private long cycleID;    

	@Length(min = 2, max = 64, message = "Defect Rule Name must be between 2 to 64 characters.")
	@NotEmpty(message = "Defect Rule Name is required.")
	private String defectRuleName;  	
	
	public DefectRule() {		
	}

	/**
	 * @param cycleID
	 * @param defectRuleName
	 */
	public DefectRule(long cycleID, String defectRuleName) {
		this.cycleID = cycleID;
		this.defectRuleName = defectRuleName;
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
	 * @return the defectRuleName
	 */
	public String getDefectRuleName() {
		return defectRuleName;
	}

	/**
	 * @param defectRuleName the defectRuleName to set
	 */
	public void setDefectRuleName(String defectRuleName) {
		this.defectRuleName = defectRuleName;
	}

	/**
	 * @return the defectRuleID
	 */
	public Long getDefectRuleID() {
		return defectRuleID;
	}

	
	
}