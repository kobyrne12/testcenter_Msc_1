/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name = "Cycle")
public class Cycle {

	

	@Id        
	@GeneratedValue
	@Column(name = "cycleID")
    private long cycleID;
	
	@Length(min = 2, max = 32, message = "Cycle name must be between 2 to 32 characters.")
	@NotEmpty(message = "Cycle Name is required.")
	private String cycleName;  	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "projectID", nullable = false)
//	private Project project;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "projectID", nullable = false)
//	private Company project;
	
	@Basic
	@Column(name="projectID")
	private long projectID;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Testrun.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "cycleID", referencedColumnName="cycleID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Testrun> Testruns;   
	@Basic    
    private long parentID; 
	@Basic    
	private int requiredPriority;
	@Basic    
	private long projectPosition;
	@Basic    
	private long totalCycleEstTime;
	@Basic    
	private String cycleStartDate;
	@Basic    
	private String cycleEndDate;
	@Basic    
	private int codeChangeRule;
	@Basic    
	private int defectRule;
	@Basic    
	private int testHistoryRule;
	@Basic    
	private int RequirementRule; 
	@Basic    
	private String creationDate;
	@Basic    
	private String createdBy;
//	@Temporal(TemporalType.DATE)
//	@Column(name = "DATE", unique = true, nullable = false, length = 10)
	@Basic    
	private String lastModifiedDate;
	@Basic    
	private String lastModifiedBy;
    
    public Cycle() {	
    }
    /**
	 * @param cycleName
	 * @param projectID
	 * @param testruns
	 * @param requiredPriority
	 * @param projectPosition
	 * @param totalCycleEstTime
	 * @param cycleStartDate
	 * @param cycleEndDate
	 * @param codeChangeRule
	 * @param defectRule
	 * @param testHistoryRule
	 * @param requirementRule
	 * @param creationDate
	 * @param createdBy
	 * @param lastModifiedDate
	 * @param lastModifiedBy
	 */
	public Cycle(String cycleName, long projectID, long parentID,
			Collection<Testrun> testruns, int requiredPriority,
			long projectPosition, long totalCycleEstTime, String cycleStartDate,
			String cycleEndDate, int codeChangeRule, int defectRule,
			int testHistoryRule, int requirementRule, String creationDate,
			String createdBy, String lastModifiedDate, String lastModifiedBy) {
		this.cycleName = cycleName;
		this.projectID = projectID;
		this.parentID = parentID;
		this.Testruns = testruns;
		this.requiredPriority = requiredPriority;
		this.projectPosition = projectPosition;
		this.totalCycleEstTime = totalCycleEstTime;
		this.cycleStartDate = cycleStartDate;
		this.cycleEndDate = cycleEndDate;
		this.codeChangeRule = codeChangeRule;
		this.defectRule = defectRule;
		this.testHistoryRule = testHistoryRule;
		this.RequirementRule = requirementRule;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
	}
	
	/**
	 * @return the cycleID
	 */
	public long getCycleID() {
		return cycleID;
	}

	/**
	 * @param cycleID the cycleID to set
	 */
	public void setCycleID(long cycleID) {
		this.cycleID = cycleID;
	}

	/**
	 * @return the cycleName
	 */
	public String getCycleName() {
		return cycleName;
	}

	/**
	 * @param cycleName the cycleName to set
	 */
	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(String lastModifiedDate) {		
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
	 * @return the requiredPriority
	 */
	public int getRequiredPriority() {
		return requiredPriority;
	}

	/**
	 * @param requiredPriority the requiredPriority to set
	 */
	public void setRequiredPriority(int requiredPriority) {
		this.requiredPriority = requiredPriority;
	}

	/**
	 * @return the projectPosition
	 */
	public long getProjectPosition() {
		return projectPosition;
	}

	/**
	 * @param projectPosition the projectPosition to set
	 */
	public void setProjectPosition(long projectPosition) {
		this.projectPosition = projectPosition;
	}

	/**
	 * @return the totalCycleEstTime
	 */
	public long getTotalCycleEstTime() {
		return totalCycleEstTime;
	}

	/**
	 * @param totalCycleEstTime the totalCycleEstTime to set
	 */
	public void setTotalCycleEstTime(long totalCycleEstTime) {
		this.totalCycleEstTime = totalCycleEstTime;
	}

	/**
	 * @return the cycleStartDate
	 */
	public String getCycleStartDate() {
		return cycleStartDate;
	}

	/**
	 * @param cycleStartDate the cycleStartDate to set
	 */
	public void setCycleStartDate(String cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}

	/**
	 * @return the cycleEndDate
	 */
	public String getCycleEndDate() {
		return cycleEndDate;
	}

	/**
	 * @param cycleEndDate the cycleEndDate to set
	 */
	public void setCycleEndDate(String cycleEndDate) {
		this.cycleEndDate = cycleEndDate;
	}

	/**
	 * @return the codeChangeRule
	 */
	public int getCodeChangeRule() {
		return codeChangeRule;
	}

	/**
	 * @param codeChangeRule the codeChangeRule to set
	 */
	public void setCodeChangeRule(int codeChangeRule) {
		this.codeChangeRule = codeChangeRule;
	}

	/**
	 * @return the defectRule
	 */
	public int getDefectRule() {
		return defectRule;
	}

	/**
	 * @param defectRule the defectRule to set
	 */
	public void setDefectRule(int defectRule) {
		this.defectRule = defectRule;
	}

	/**
	 * @return the testHistoryRule
	 */
	public int getTestHistoryRule() {
		return testHistoryRule;
	}

	/**
	 * @param testHistoryRule the testHistoryRule to set
	 */
	public void setTestHistoryRule(int testHistoryRule) {
		this.testHistoryRule = testHistoryRule;
	}

	/**
	 * @return the requirementRule
	 */
	public int getRequirementRule() {
		return RequirementRule;
	}

	/**
	 * @param requirementRule the requirementRule to set
	 */
	public void setRequirementRule(int requirementRule) {
		RequirementRule = requirementRule;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
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
	 * @return the projectID
	 */
	public long getProjectID() {
		return projectID;
	}	
	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}	
	
	/**
	 * @return the Testruns
	 */
	public Collection<Testrun> getTestruns() {
		return Testruns;
	}

	/**
	 * @param Testruns the Testruns to set
	 */
	public void setTestruns(Collection<Testrun> Testruns) {
		this.Testruns = Testruns;
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

	

}