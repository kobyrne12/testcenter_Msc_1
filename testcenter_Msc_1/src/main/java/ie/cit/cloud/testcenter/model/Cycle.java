/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.service.cycle.CycleService;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "Cycle")
public class Cycle {

	@Id        
	@GeneratedValue
	@Column(name = "cycleID")
	private Long cycleID;

	@Length(min = 2, max = 32, message = "Cycle name must be between 2 to 32 characters.")
	@NotEmpty(message = "Cycle Name is required.")
	private String cycleName;  	

	@Basic
	@Column(name="projectID")
	private Long projectID;

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Testrun.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "cycleID", referencedColumnName="cycleID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Testrun> testruns = new HashSet<Testrun>();   

	@Basic    
	private boolean latest;  
	
	@Basic    
	private Long parentID;
	@Basic    
	private boolean parent;  
	@Basic    
	private boolean child; 
	@Basic    
	private int requiredPriority;
	@Basic    
	private int projectPosition;
	@Basic    
	private String cycleStartDate;
	@Basic    
	private String cycleEndDate;

	@OneToMany(fetch=FetchType.EAGER, targetEntity=ChangeImpactRule.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "cycleID", referencedColumnName="cycleID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<ChangeImpactRule> changeImpactRules = new HashSet<ChangeImpactRule>();  

	@OneToMany(fetch=FetchType.EAGER, targetEntity=DefectRule.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "cycleID", referencedColumnName="cycleID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<DefectRule> defectRules = new HashSet<DefectRule>();   

	@OneToMany(fetch=FetchType.EAGER, targetEntity=RequirementRule.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "cycleID", referencedColumnName="cycleID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<RequirementRule> requirementRules = new HashSet<RequirementRule>();   

	@OneToMany(fetch=FetchType.EAGER, targetEntity=TestHistoryRule.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "cycleID", referencedColumnName="cycleID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<TestHistoryRule> testhistoryRules = new HashSet<TestHistoryRule>();   

	@Basic    
	private String creationDate;

	public void setTesthistoryRules(Set<TestHistoryRule> testhistoryRules) {
		this.testhistoryRules = testhistoryRules;
	}
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
	 * @param requiredPriority
	 * @param projectPosition
	 * @param cycleStartDate
	 * @param cycleEndDate	
	 */
	public Cycle(String cycleName, Long projectID, int requiredPriority, int projectPosition,
			String cycleStartDate, String cycleEndDate)
	{	
		this(cycleName,projectID,null,null,false,false,requiredPriority,projectPosition,cycleStartDate,cycleEndDate,
				null,null,null,null,"DATE","USER","DATE","USER",true);	
	}


	/**
	 * @param cycleName
	 * @param projectID
	 * @param testruns
	 * @param parentID
	 * @param parent
	 * @param child
	 * @param requiredPriority
	 * @param projectPosition
	 * @param cycleStartDate
	 * @param cycleEndDate
	 * @param changeImpactRules
	 * @param defectRules
	 * @param requirementRules
	 * @param testhistoryRules
	 * @param creationDate
	 * @param createdBy
	 * @param lastModifiedDate
	 * @param lastModifiedBy
	 */
	public Cycle(String cycleName, Long projectID,
			Set<Testrun> testruns, Long parentID, boolean parent,
			boolean child, int requiredPriority, int projectPosition,
			String cycleStartDate, String cycleEndDate,
			Set<ChangeImpactRule> changeImpactRules,
			Set<DefectRule> defectRules,
			Set<RequirementRule> requirementRules,
			Set<TestHistoryRule> testhistoryRules, String creationDate,
			String createdBy, String lastModifiedDate, String lastModifiedBy,boolean latest) {
		this.cycleName = cycleName;
		this.projectID = projectID;
		this.testruns = testruns;
		this.parentID = parentID;
		this.parent = parent;
		this.child = child;
		this.requiredPriority = requiredPriority;
		this.projectPosition = projectPosition;
		this.cycleStartDate = cycleStartDate;
		this.cycleEndDate = cycleEndDate;
		this.changeImpactRules = changeImpactRules;
		this.defectRules = defectRules;
		this.requirementRules = requirementRules;
		this.testhistoryRules = testhistoryRules;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
		this.latest = latest;
	}

	/**
	 * @return the cycleID
	 */
	public Long getCycleID() {
		return cycleID;
	}

	/**
	 * @param cycleID the cycleID to set
	 */
	public void setCycleID(Long cycleID) {
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
	public int getProjectPosition() {
		return projectPosition;
	}

	/**
	 * @param projectPosition the projectPosition to set
	 */
	public void setProjectPosition(int projectPosition) {
		this.projectPosition = projectPosition;
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
	public Long getProjectID() {
		return projectID;
	}	
	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}	

	/**
	 * @return the testruns
	 */
	public Set<Testrun> getTestruns() {
		return testruns;
	}

	/**
	 * @param Testruns the testruns to set
	 */
	public void setTestruns(Set<Testrun> testruns) {
		this.testruns = testruns;
	}
	/**
	 * @return the parentID
	 */
	public Long getParentID() {
		return parentID;
	}
	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(Long parentID) {
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
	 * @return the changeImpactRules
	 */
	public Set<ChangeImpactRule> getChangeImpactRules() {
		return changeImpactRules;
	}

	/**
	 * @param changeImpactRules the changeImpactRules to set
	 */
	public void setChangeImpactRules(Set<ChangeImpactRule> changeImpactRules) {
		this.changeImpactRules = changeImpactRules;
	}

	/**
	 * @return the defectRules
	 */
	public Set<DefectRule> getDefectRules() {
		return defectRules;
	}

	/**
	 * @param defectRules the defectRules to set
	 */
	public void setDefectRules(Set<DefectRule> defectRules) {
		this.defectRules = defectRules;
	}

	/**
	 * @return the requirementRules
	 */
	public Set<RequirementRule> getRequirementRules() {
		return requirementRules;
	}

	/**
	 * @param requirementRules the requirementRules to set
	 */
	public void setRequirementRules(Set<RequirementRule> requirementRules) {
		this.requirementRules = requirementRules;
	}

	/**
	 * @return the testhistoryRules
	 */
	public Set<TestHistoryRule> getTesthistoryRules() {
		return testhistoryRules;
	}

	/**
	 * @param testhistoryRules the testhistoryRules to set
	 */	
	public void setTestHistoryRules(Set<TestHistoryRule> testhistoryRules) {
		this.testhistoryRules = testhistoryRules;
	}
	//	/**
	//	 * Returns true if this cycle or any of its children are the latest cycle for a project 
	//	 * boolean
	//	 * @return true if this cycle or any of its children are the latest cycle for a project, otherwise false
	//	 */
	//	public boolean isLatest()
	//	{				
	//		try{
	//			int maxProjectPosition = cycleService.getMaxProjectPosNum(projectID);				
	//			if(maxProjectPosition == projectPosition)
	//			{
	//				return true;
	//			}
	//			if(parent)
	//			{  
	//				Set<Cycle> childCycles = getChildCycles();
	//				if(childCycles != null && !childCycles.isEmpty())
	//				{		
	//					for(final Cycle childCycle : childCycles)
	//					{
	//						if(childCycle.isChild())
	//						{
	//							if(maxProjectPosition == childCycle.getProjectPosition())
	//							{
	//								return true;
	//							}							
	//						}
	//					}					
	//				}
	//			}
	//			return false;			
	//		}
	//		catch(NoResultException nre)			
	//		{
	//			return false;
	//		}
	//	} 
	/**
	 * @return the latest
	 */
	public boolean isLatest() {
		return latest;
	}
	/**
	 * @param latest the latest to set
	 */
	public void setLatest(boolean latest) {
		this.latest = latest;
	}

	
}