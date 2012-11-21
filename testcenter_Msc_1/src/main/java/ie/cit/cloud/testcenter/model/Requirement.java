/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "Requirement")
public class Requirement {

	@Id        
	@GeneratedValue
	@Column(name = "requirementID")
	private Long requirementID;
	
	@Basic
	@Column(name="companyID")
	private Long companyID;

	@Length(min = 2, max = 200, message = "Summary must be between 2 to 200 characters.")
	@NotEmpty(message = "Summary is required.")
	private String requirementSummary;  	   

	@NotEmpty(message = "Details are required.")	
	private String requirementDetails;   		

	@NotEmpty(message = "Section is required.")
	private String  requirementSection; 
	
	@Temporal(TemporalType.DATE)
	@Column(name = "creationDate", nullable = false, length = 10) 
	private Date creationDate;		
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate", nullable = false, length = 10) 
	private Date lastModifiedDate;
	
	@Basic    
	private String createdBy;
	@Basic    
	private String lastModifiedBy;

	public Requirement() {	
	}

	/**
	 * @param companyID
	 * @param requirementSummary
	 * @param requirementDetails
	 * @param requirementSection
	 * @param creationDate
	 * @param lastModifiedDate
	 * @param createdBy
	 * @param lastModifiedBy
	 */
	public Requirement(Long companyID, String requirementSummary,
			String requirementDetails, String requirementSection,
			Date creationDate, Date lastModifiedDate, String createdBy,
			String lastModifiedBy) {
		this.companyID = companyID;
		this.requirementSummary = requirementSummary;
		this.requirementDetails = requirementDetails;
		this.requirementSection = requirementSection;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
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
	 * @return the requirementSummary
	 */
	public String getRequirementSummary() {
		return requirementSummary;
	}

	/**
	 * @param requirementSummary the requirementSummary to set
	 */
	public void setRequirementSummary(String requirementSummary) {
		this.requirementSummary = requirementSummary;
	}

	/**
	 * @return the requirementDetails
	 */
	public String getRequirementDetails() {
		return requirementDetails;
	}

	/**
	 * @param requirementDetails the requirementDetails to set
	 */
	public void setRequirementDetails(String requirementDetails) {
		this.requirementDetails = requirementDetails;
	}

	/**
	 * @return the requirementSection
	 */
	public String getRequirementSection() {
		return requirementSection;
	}

	/**
	 * @param requirementSection the requirementSection to set
	 */
	public void setRequirementSection(String requirementSection) {
		this.requirementSection = requirementSection;
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
	 * @return the requirementID
	 */
	public Long getRequirementID() {
		return requirementID;
	}
	
	
	
}