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
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "Defect")
public class Defect {

	@Id        
	@GeneratedValue
	@Column(name = "defectID")
	private Long defectID;
	
	@Basic
	@Column(name="companyID")
	private Long companyID;

	@Length(min = 2, max = 200, message = "Summary must be between 2 to 200 characters.")
	@NotEmpty(message = "Summary is required.")
	private String defectSummary;  	   

	@Basic 
	private int severity;   
	@Basic 
	private String defectDetails;   
	@Basic 
	private String  defectSection; 
	@Basic    
	private String defectType;
	
	@Transient	
	public boolean isSev1()
	{
		if(this.severity == 1)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	@Transient
	public boolean isSev2()
	{
		if(this.severity == 2)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	@Transient
	public boolean isSev3()
	{
		if(this.severity == 3)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	@Transient
	public boolean isSev4()
	{
		if(this.severity == 4)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	public Defect() {		
	}
	
	/**
	 * @param defectSummary
	 * @param defectDetails
	 * @param defectSection
	 * @param defectType
	 */
	public Defect(Long companyID, String defectSummary, String defectDetails,
			String defectSection, String defectType,int severity) {
		this.companyID = companyID;		
		this.defectSummary = defectSummary;
		this.severity = severity;
		this.defectDetails = defectDetails;
		this.defectSection = defectSection;
		this.defectType = defectType;
		
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
	/**
	 * @return the defectID
	 */
	public Long getDefectID() {
		return defectID;
	}

	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(int severity) {
		this.severity = severity;
	}	
	
	
}