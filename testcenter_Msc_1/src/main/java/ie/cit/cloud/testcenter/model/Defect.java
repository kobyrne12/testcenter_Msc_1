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
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
	private long parentID; 
	@Basic    
	private boolean parent;  
	@Basic    
	private boolean child; 
	
	@Basic 
	private int severity;   	
	@Basic 
	private String defectDetails;  
	
	@Basic 
	@Column(name = "defectSectionID")
	private Long defectSectionID;	
	
	@Basic    
	private String defectType;
	
	@ManyToMany(mappedBy="defects", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Collection<Testrun> testruns = new ArrayList<Testrun>();
	
	@ManyToMany(mappedBy="defects", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Collection<Requirement> requirements = new ArrayList<Requirement>();
	
	@ManyToMany(mappedBy="defects", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Collection<TestcenterUser> users = new ArrayList<TestcenterUser>();
	
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
	@Basic    
	private long currentOwnerUserID;
	
	public Defect() {		
	}
	/**
	 * @param companyID
	 * @param defectSummary
	 * @param severity
	 * @param defectDetails
	 * @param defectSectionID
	 * @param defectType	
	 */
	public Defect(Long companyID, String defectSummary, int severity,
			String defectDetails, Long defectSectionID, String defectType)
	{
		this(companyID,defectSummary,0,false,false,severity,defectDetails,defectSectionID,defectType,
				null,null,null,new Date(),new Date(),0,0,0);
	}	
	/**
	 * @param companyID
	 * @param defectSummary
	 * @param parentID
	 * @param parent
	 * @param child
	 * @param severity
	 * @param defectDetails
	 * @param defectSectionID
	 * @param defectType
	 * @param testruns
	 * @param requirements
	 * @param users
	 * @param creationDate
	 * @param lastModifiedDate
	 * @param createdByUserID
	 * @param lastModifiedByUserID
	 * @param currentOwnerUserID
	 */
	public Defect(Long companyID, String defectSummary, long parentID,
			boolean parent, boolean child, int severity, String defectDetails,
			Long defectSectionID, String defectType,
			Collection<Testrun> testruns, Collection<Requirement> requirements,
			Collection<TestcenterUser> users, Date creationDate,
			Date lastModifiedDate, long createdByUserID,
			long lastModifiedByUserID, long currentOwnerUserID) {
		this.companyID = companyID;
		this.defectSummary = defectSummary;
		this.parentID = parentID;
		this.parent = parent;
		this.child = child;
		this.severity = severity;
		this.defectDetails = defectDetails;
		this.defectSectionID = defectSectionID;
		this.defectType = defectType;
		this.testruns = testruns;
		this.requirements = requirements;
		this.users = users;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdByUserID = createdByUserID;
		this.lastModifiedByUserID = lastModifiedByUserID;
		this.currentOwnerUserID = currentOwnerUserID;
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
	 * @return the defectSectionID
	 */
	public Long getDefectSectionID() {
		return defectSectionID;
	}
	/**
	 * @param defectSectionID the defectSectionID to set
	 */
	public void setDefectSectionID(Long defectSectionID) {
		this.defectSectionID = defectSectionID;
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
	 * @return the requirements
	 */
	public Collection<Requirement> getRequirements() {
		return requirements;
	}
	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(Collection<Requirement> requirements) {
		this.requirements = requirements;
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
	 * @return the defectID
	 */
	public Long getDefectID() {
		return defectID;
	}
	/**
	 * @return the parentID
	 */
	public long getParentID() {
		return parentID;
	}
	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(long parentID) {
		this.parentID = parentID;
	}
	/**
	 * @return the parent
	 */
	public boolean isParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(boolean parent) {
		this.parent = parent;
	}
	/**
	 * @return the child
	 */
	public boolean isChild() {
		return child;
	}
	/**
	 * @param child the child to set
	 */
	public void setChild(boolean child) {
		this.child = child;
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
	 * @return the currentOwnerUserID
	 */
	public long getCurrentOwnerUserID() {
		return currentOwnerUserID;
	}
	/**
	 * @param currentOwnerUserID the currentOwnerUserID to set
	 */
	public void setCurrentOwnerUserID(long currentOwnerUserID) {
		this.currentOwnerUserID = currentOwnerUserID;
	}	
	
}