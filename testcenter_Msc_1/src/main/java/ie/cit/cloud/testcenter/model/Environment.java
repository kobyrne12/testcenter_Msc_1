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


@Entity(name = "Environment")
public class Environment {

	@Id        
	@GeneratedValue
	@Column(name = "environmentID")
	private Long environmentID;

	@Length(min = 2, max = 200, message = "Summary must be between 2 to 200 characters.")
	@NotEmpty(message = "Summary is required.")
	private String environmentSummary;  	   

	@NotEmpty(message = "Details are required.")	
	private String environmentDetails;   		

	@NotEmpty(message = "Section is required.")
	private String  environmentSection; 

	@Basic    
	private String environmentType;

	// @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	//@JoinColumn(name="defectID",nullable = false)  
	//@Transient
	//private Defect defect;

	@Basic
	@Column(name="companyID")
	private Long companyID;


	// @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")

	public Environment() {	
	}

	//    public Environment(Company company,long companyID,String environmentName,long parentID,String lastModifiedDate,String lastModifiedBy ) {
	//    	this(company,companyID,environmentName,parentID,96,94,10,25,50,100, lastModifiedDate, lastModifiedBy);
	//    }

	public Environment(long companyID,long environmentID, String environmentSummary,String environmentDetails, String environmentType)
	{
		this.companyID = companyID;
		this.environmentID = environmentID;
		this.environmentSummary = environmentSummary;
		this.environmentDetails = environmentDetails;
		this.environmentType = environmentType;    
	}

	// Get companyID
	public long getTestPlanID() {
		return this.companyID;
	} 
	public void setTestPlanID(long companyID) {
		this.companyID = companyID;
	}

	// environment ID 
	public long getEnvironmentID() {
		return environmentID;
	}

	/**
	 * @return the environmentSummary
	 */
	public String getEnvironmentSummary() {
		return environmentSummary;
	}

	/**
	 * @param environmentSummary the environmentSummary to set
	 */
	public void setEnvironmentSummary(String environmentSummary) {
		this.environmentSummary = environmentSummary;
	}

	/**
	 * @return the environmentDetails
	 */
	public String getEnvironmentDetails() {
		return environmentDetails;
	}

	/**
	 * @param environmentDetails the environmentDetails to set
	 */
	public void setEnvironmentDetails(String environmentDetails) {
		this.environmentDetails = environmentDetails;
	}

	/**
	 * @return the environmentSection
	 */
	public String getEnvironmentSection() {
		return environmentSection;
	}

	/**
	 * @param environmentSection the environmentSection to set
	 */
	public void setEnvironmentSection(String environmentSection) {
		this.environmentSection = environmentSection;
	}

	/**
	 * @return the environmentType
	 */
	public String getEnvironmentType() {
		return environmentType;
	}

	/**
	 * @param environmentType the environmentType to set
	 */
	public void setEnvironmentType(String environmentType) {
		this.environmentType = environmentType;
	}



}