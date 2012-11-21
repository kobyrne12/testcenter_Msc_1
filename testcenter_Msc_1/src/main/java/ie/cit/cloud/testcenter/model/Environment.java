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


@Entity(name = "Environment")
public class Environment {

	@Id        
	@GeneratedValue
	@Column(name = "environmentID")
	private Long environmentID;
	
	@Basic
	@Column(name="companyID")
	private Long companyID;
	
	@Length(min = 2, max = 64, message = "Summary must be between 2 to 64 characters.")
	@NotEmpty(message = "Environment Name is required.")
	private String environmentName;  
	
	@NotEmpty(message = "Environment Operating System is required.")
	private String environmentOs;   		

	@NotEmpty(message = "Environment Operating System Version is required.")
	private String  environmentOsVersion; 

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
	
	@Basic    
	private String environmentOwner;

	public Environment() {	
	}

	
	/**
	 * @param companyID
	 * @param environmentName
	 * @param environmentOs
	 * @param environmentOsVersion
	 * @param creationDate
	 * @param createdBy
	 * @param lastModifiedDate
	 * @param lastModifiedBy
	 * @param environmentOwner
	 */
	public Environment(Long companyID, String environmentName,
			String environmentOs, String environmentOsVersion,
			Date creationDate, String createdBy, Date lastModifiedDate,
			String lastModifiedBy, String environmentOwner) {
		this.companyID = companyID;
		this.environmentName = environmentName;
		this.environmentOs = environmentOs;
		this.environmentOsVersion = environmentOsVersion;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
		this.environmentOwner = environmentOwner;
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
	 * @return the environmentName
	 */
	public String getEnvironmentName() {
		return environmentName;
	}

	/**
	 * @param environmentName the environmentName to set
	 */
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}

	/**
	 * @return the environmentOs
	 */
	public String getEnvironmentOs() {
		return environmentOs;
	}

	/**
	 * @param environmentOs the environmentOs to set
	 */
	public void setEnvironmentOs(String environmentOs) {
		this.environmentOs = environmentOs;
	}

	/**
	 * @return the environmentOsVersion
	 */
	public String getEnvironmentOsVersion() {
		return environmentOsVersion;
	}

	/**
	 * @param environmentOsVersion the environmentOsVersion to set
	 */
	public void setEnvironmentOsVersion(String environmentOsVersion) {
		this.environmentOsVersion = environmentOsVersion;
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
	 * @return the environmentOwner
	 */
	public String getEnvironmentOwner() {
		return environmentOwner;
	}

	/**
	 * @param environmentOwner the environmentOwner to set
	 */
	public void setEnvironmentOwner(String environmentOwner) {
		this.environmentOwner = environmentOwner;
	}

	/**
	 * @return the environmentID
	 */
	public Long getEnvironmentID() {
		return environmentID;
	}
	

}