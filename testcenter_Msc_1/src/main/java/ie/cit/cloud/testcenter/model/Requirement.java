/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */


import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "Requirement")
public class Requirement {

	@Id        
	@GeneratedValue
	@Column(name = "requirementID")
	private Long requirementID;

	@Length(min = 2, max = 200, message = "Summary must be between 2 to 200 characters.")
	@NotEmpty(message = "Summary is required.")
	private String requirementSummary;  	   

	@NotEmpty(message = "Details are required.")	
	private String requirementDetails;   		

	@NotEmpty(message = "Section is required.")
	private String  requirementSection; 

	@Basic    
	private String requirementType;

	// @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	//@JoinColumn(name="defectID",nullable = false)  
	//@Transient
	//private Defect defect;

	@Basic
	@Column(name="companyID")
	private Long companyID;


	// @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")

	public Requirement() {	
	}

	//    public Requirement(Company company,long companyID,String requirementName,long parentID,String lastModifiedDate,String lastModifiedBy ) {
	//    	this(company,companyID,requirementName,parentID,96,94,10,25,50,100, lastModifiedDate, lastModifiedBy);
	//    }

	public Requirement(long companyID,long requirementID, String requirementSummary,String requirementDetails, String requirementType)
	{
		this.companyID = companyID;
		this.requirementID = requirementID;
		this.requirementSummary = requirementSummary;
		this.requirementDetails = requirementDetails;
		this.requirementType = requirementType;    
	}

	// Get companyID
	public long getTestPlanID() {
		return this.companyID;
	} 
	public void setTestPlanID(long companyID) {
		this.companyID = companyID;
	}

	// requirement ID 
	public long getRequirementID() {
		return requirementID;
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
	 * @return the requirementType
	 */
	public String getRequirementType() {
		return requirementType;
	}

	/**
	 * @param requirementType the requirementType to set
	 */
	public void setRequirementType(String requirementType) {
		this.requirementType = requirementType;
	}



}