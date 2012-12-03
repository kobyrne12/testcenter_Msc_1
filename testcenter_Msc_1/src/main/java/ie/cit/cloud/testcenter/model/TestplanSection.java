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

@Entity(name = "TestplanSection")
public class TestplanSection {

	@Id        
	@GeneratedValue
	@Column(name = "testplanSectionID")
	private Long testplanSectionID;	

	@Basic
	@Column(name="testplanID")
	private Long testplanID;    

	@Length(min = 2, max = 64, message = "Testplan Section name must be between 2 to 64 characters.")
	@NotEmpty(message = "TestPlan Section Name is required.")
	private String testplanSectionName;  	
	
	public TestplanSection() {		
	}

	/**
	 * @param testplanID
	 * @param testplanSectionName
	 */
	public TestplanSection(Long testplanID, String testplanSectionName) {
		this.testplanID = testplanID;
		this.testplanSectionName = testplanSectionName;
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
	 * @return the testplanSectionName
	 */
	public String getTestplanSectionName() {
		return testplanSectionName;
	}

	/**
	 * @param testplanSectionName the testplanSectionName to set
	 */
	public void setTestplanSectionName(String testplanSectionName) {
		this.testplanSectionName = testplanSectionName;
	}

	/**
	 * @return the testplanSectionID
	 */
	public Long getTestplanSectionID() {
		return testplanSectionID;
	}	
	
}