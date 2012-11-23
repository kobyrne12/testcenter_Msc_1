/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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

	@Basic 
	@Column(name = "requirementSectionID")
	private Long requirementSectionID;	

	@Temporal(TemporalType.DATE)
	@Column(name = "creationDate", nullable = false, length = 10) 
	private Date creationDate;		
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate", nullable = false, length = 10) 
	private Date lastModifiedDate;

	@Basic    
	private long createdByUserID;
	@Basic    
	private long lastModifiedByUserID;

	@ManyToMany(mappedBy="requirements", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Testrun> testruns = new ArrayList<Testrun>();

	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "REQUIREMENTS_JOIN_USERS", joinColumns = { @JoinColumn(name = "requirementID") }, inverseJoinColumns = { @JoinColumn(name = "userID") })
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<TestcenterUser> users  = new ArrayList<TestcenterUser>();  

	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "REQUIREMENTS_JOIN_DEFECTS", joinColumns = { @JoinColumn(name = "requirementID") }, inverseJoinColumns = { @JoinColumn(name = "defectID") })
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Defect> defects  = new ArrayList<Defect>();   

	public Requirement() {	
	}

	/**
	 * @param companyID
	 * @param requirementSummary
	 * @param requirementDetails
	 * @param requirementSectionID	
	 */
	public Requirement(Long companyID, String requirementSummary,
			String requirementDetails, long requirementSectionID) {
		this(companyID,requirementSummary,requirementDetails,requirementSectionID,
				new Date(),new Date(),(long) 0,(long) 0,null,null,null);	
	}

	/**
	 * @param companyID
	 * @param requirementSummary
	 * @param requirementDetails
	 * @param requirementSectionID
	 * @param creationDate
	 * @param lastModifiedDate
	 * @param createdByUserID
	 * @param lastModifiedByUserID
	 * @param testruns
	 * @param users
	 * @param defects
	 */
	public Requirement(Long companyID, String requirementSummary,
			String requirementDetails, Long requirementSectionID,
			Date creationDate, Date lastModifiedDate, long createdByUserID,
			long lastModifiedByUserID, Collection<Testrun> testruns,
			Collection<TestcenterUser> users, Collection<Defect> defects) {
		this.companyID = companyID;
		this.requirementSummary = requirementSummary;
		this.requirementDetails = requirementDetails;
		this.requirementSectionID = requirementSectionID;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdByUserID = createdByUserID;
		this.lastModifiedByUserID = lastModifiedByUserID;
		this.testruns = testruns;
		this.users = users;
		this.defects = defects;
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
	 * @return the requirementSectionID
	 */
	public Long getRequirementSectionID() {
		return requirementSectionID;
	}

	/**
	 * @param requirementSectionID the requirementSectionID to set
	 */
	public void setRequirementSectionID(Long requirementSectionID) {
		this.requirementSectionID = requirementSectionID;
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
	public long getCreatedByUserID() {
		return createdByUserID;
	}

	/**
	 * @param createdByUserID the createdByUserID to set
	 */
	public void setCreatedByUserID(long createdByUserID) {
		this.createdByUserID = createdByUserID;
	}

	/**
	 * @return the lastModifiedByUserID
	 */
	public long getLastModifiedByUserID() {
		return lastModifiedByUserID;
	}

	/**
	 * @param lastModifiedByUserID the lastModifiedByUserID to set
	 */
	public void setLastModifiedByUserID(long lastModifiedByUserID) {
		this.lastModifiedByUserID = lastModifiedByUserID;
	}

	/**
	 * @return the testruns
	 */
	public Collection<Testrun> getTestruns() {
		return testruns;
	}

	/**
	 * @param testruns the testruns to set
	 */
	public void setTestruns(Collection<Testrun> testruns) {
		this.testruns = testruns;
	}

	/**
	 * @return the users
	 */
	public Collection<TestcenterUser> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Collection<TestcenterUser> users) {
		this.users = users;
	}

	/**
	 * @return the defects
	 */
	public Collection<Defect> getDefects() {
		return defects;
	}

	/**
	 * @param defects the defects to set
	 */
	public void setDefects(Collection<Defect> defects) {
		this.defects = defects;
	}

	/**
	 * @return the requirementID
	 */
	public Long getRequirementID() {
		return requirementID;
	}
	
	
}