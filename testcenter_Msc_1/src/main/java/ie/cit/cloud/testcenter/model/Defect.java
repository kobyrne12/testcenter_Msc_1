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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
		this(companyID,defectSummary,severity,defectDetails,defectSectionID,defectType,
				null,null,null);
	}

	/**
	 * @param companyID
	 * @param defectSummary
	 * @param severity
	 * @param defectDetails
	 * @param defectSectionID
	 * @param defectType
	 * @param testruns
	 * @param requirements
	 * @param users
	 */
	public Defect(Long companyID, String defectSummary, int severity,
			String defectDetails, Long defectSectionID, String defectType,
			Collection<Testrun> testruns, Collection<Requirement> requirements,
			Collection<TestcenterUser> users) {
		this.companyID = companyID;
		this.defectSummary = defectSummary;
		this.severity = severity;
		this.defectDetails = defectDetails;
		this.defectSectionID = defectSectionID;
		this.defectType = defectType;
		this.testruns = testruns;
		this.requirements = requirements;
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
	
}