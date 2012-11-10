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


@Entity(name = "Defect")
public class Defect {

	@Id        
	@GeneratedValue
	@Column(name = "defectID")
	private Long defectID;

	@Length(min = 2, max = 200, message = "Summary must be between 2 to 200 characters.")
	@NotEmpty(message = "Summary is required.")
	private String defectSummary;  	   

	@NotEmpty(message = "Details are required.")	
	private String defectDetails;   		

	@NotEmpty(message = "Email is required.")
	private String  defectSection; 

	@Basic    
	private String defectType;

	// @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	//@JoinColumn(name="requirementID",nullable = false)  
	//@Transient
	//private Requirement requirement;

	@Basic
	@Column(name="companyID")
	private Long companyID;


	// @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")

	public Defect() {	
	}

	//    public Defect(Company company,long companyID,String defectName,long parentID,String lastModifiedDate,String lastModifiedBy ) {
	//    	this(company,companyID,defectName,parentID,96,94,10,25,50,100, lastModifiedDate, lastModifiedBy);
	//    }

	public Defect(long companyID,long defectID, String defectSummary,String defectDetails, String defectType)
	{
		this.companyID = companyID;
		this.defectID = defectID;
		this.defectSummary = defectSummary;
		this.defectDetails = defectDetails;
		this.defectType = defectType;    
	}

	// Get companyID
	public long getTestPlanID() {
		return this.companyID;
	} 
	public void setTestPlanID(long companyID) {
		this.companyID = companyID;
	}

	// defect ID 
	public long getDefectID() {
		return defectID;
	}

	/**
	 * @return the defectSummary
	 */
	public String getDefectSummary() {
		return defectSummary;
	}

	/**
	 * @param defectSummary the defectSummary to set
	 */
	public void setDefectSummary(String defectSummary) {
		this.defectSummary = defectSummary;
	}

	/**
	 * @return the defectDetails
	 */
	public String getDefectDetails() {
		return defectDetails;
	}

	/**
	 * @param defectDetails the defectDetails to set
	 */
	public void setDefectDetails(String defectDetails) {
		this.defectDetails = defectDetails;
	}

	/**
	 * @return the defectSection
	 */
	public String getDefectSection() {
		return defectSection;
	}

	/**
	 * @param defectSection the defectSection to set
	 */
	public void setDefectSection(String defectSection) {
		this.defectSection = defectSection;
	}

	/**
	 * @return the defectType
	 */
	public String getDefectType() {
		return defectType;
	}

	/**
	 * @param defectType the defectType to set
	 */
	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}


}