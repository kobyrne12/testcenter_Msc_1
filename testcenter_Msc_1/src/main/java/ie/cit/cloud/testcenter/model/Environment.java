/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@JsonIgnoreProperties({ "testruns" ,"users" })
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

	@Temporal(TemporalType.DATE)
	@Column(name = "creationDate", nullable = false, length = 10) 
	private Date creationDate;	
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate", nullable = false, length = 10) 
	private Date lastModifiedDate;
	
	@Basic    
	private Long createdByUserID;	
	@Basic    
	private Long lastModifiedByUserID;	
	@Basic    
	private Long currentOwnerUserID;

	@ManyToMany(mappedBy="environments", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<Testrun> testruns = new HashSet<Testrun>();
	
	@ManyToMany(mappedBy="environments", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<TestcenterUser> users = new HashSet<TestcenterUser>();
	
	public Environment() {	
	}

	/**
	 * @param companyID
	 * @param environmentName
	 * @param environmentOs
	 */
	public Environment(Long companyID, String environmentName,
			String environmentOs) 
	{
		this(companyID, environmentName, environmentOs,
				new Date(),new Date(),null,null,null,null,null);		
	}
	
	/**
	 * @param companyID
	 * @param environmentName
	 * @param environmentOs
	 * @param creationDate
	 * @param lastModifiedDate
	 * @param createdByUserID
	 * @param lastModifiedByUserID
	 * @param currentOwnerUserID
	 * @param testruns
	 * @param users
	 */
	public Environment(Long companyID, String environmentName,
			String environmentOs, Date creationDate, Date lastModifiedDate, Long createdByUserID,
			Long lastModifiedByUserID, Long currentOwnerUserID,
			Set<Testrun> testruns, Set<TestcenterUser> users) {
		this.companyID = companyID;
		this.environmentName = environmentName;
		this.environmentOs = environmentOs;		
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdByUserID = createdByUserID;
		this.lastModifiedByUserID = lastModifiedByUserID;
		this.currentOwnerUserID = currentOwnerUserID;
		this.testruns = testruns;
		this.users = users;
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
	 * @return the createdByUserID
	 */
	public Long getCreatedByUserID() {
		return createdByUserID;
	}

	/**
	 * @param createdByUserID the createdByUserID to set
	 */
	public void setCreatedByUserID(Long createdByUserID) {
		this.createdByUserID = createdByUserID;
	}

	/**
	 * @return the lastModifiedByUserID
	 */
	public Long getLastModifiedByUserID() {
		return lastModifiedByUserID;
	}

	/**
	 * @param lastModifiedByUserID the lastModifiedByUserID to set
	 */
	public void setLastModifiedByUserID(Long lastModifiedByUserID) {
		this.lastModifiedByUserID = lastModifiedByUserID;
	}

	/**
	 * @return the currentOwnerUserID
	 */
	public Long getCurrentOwnerUserID() {
		return currentOwnerUserID;
	}

	/**
	 * @param currentOwnerUserID the currentOwnerUserID to set
	 */
	public void setCurrentOwnerUserID(Long currentOwnerUserID) {
		this.currentOwnerUserID = currentOwnerUserID;
	}

	/**
	 * @return the testruns
	 */
	public Set<Testrun> getTestruns() {
		return testruns;
	}

	/**
	 * @param testruns the testruns to set
	 */
	public void setTestruns(Set<Testrun> testruns) {
		this.testruns = testruns;
	}

	/**
	 * @return the users
	 */
	public Set<TestcenterUser> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<TestcenterUser> users) {
		this.users = users;
	}

	/**
	 * @return the environmentID
	 */
	public Long getEnvironmentID() {
		return environmentID;
	}	

}