/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.service.cycle.CycleService;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "Cycle")
public class Cycle {
	
	@Autowired @Transient
	private CycleService cycleService;

	@Id        
	@GeneratedValue
	@Column(name = "cycleID")
    private long cycleID;
	
	@Length(min = 2, max = 32, message = "Cycle name must be between 2 to 32 characters.")
	@NotEmpty(message = "Cycle Name is required.")
	private String cycleName;  	

	@Basic
	@Column(name="projectID")
	private long projectID;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Testrun.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "cycleID", referencedColumnName="cycleID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Testrun> testruns;   
	@Basic    
    private long parentID; 
	@Basic    
	private boolean parent;  
	@Basic    
	private boolean child; 
	@Basic    
	private int requiredPriority;
	@Basic    
	private int projectPosition;
	@Basic    
	private long totalCycleEstTime;
	@Basic    
	private String cycleStartDate;
	@Basic    
	private String cycleEndDate;
	@Basic    
	private int codeImpactRule;
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
    
	/**
	 * Returns total Number of All Test Runs for a cycle incl child cycles
	 * int
	 * @return total Number of All Test Runs for a cycle incl child cycles
	 */
	public int getCascadedAllTestRunsCount()
	{
		int count = 0;
		if(this.isParent())
		{    		
			if(testruns != null && !testruns.isEmpty())
			{
				count += testruns.size();				
			}        	
			Collection<Cycle> childCycles = cycleService.getAllChildCycles(cycleID);
			if(childCycles != null && !childCycles.isEmpty())
			{
				for(final Cycle childCycle : childCycles)
				{
					if(childCycle.isChild())
					{
						count += childCycle.testruns.size();	
					}
				}
			}
		}    	
		return count;		
	}
	/**
	 * Returns a collection of All Testruns in a project incl all child project cycles 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a project incl all child project cycles,
	 */
	public Collection<Testrun> getCascadedAllTestRuns()
	{
		Collection<Testrun> allTestruns = new ArrayList<Testrun>();
		if(this.isParent())
		{    		
			if(testruns != null && !testruns.isEmpty())
			{
				allTestruns.addAll(testruns);							
			}        	
			Collection<Cycle> childCycles = cycleService.getAllChildCycles(cycleID);
			if(childCycles != null && !childCycles.isEmpty())
			{
				for(final Cycle childCycle : childCycles)
				{
					if(childCycle.isChild())
					{
						allTestruns.addAll(childCycle.testruns);								
					}
				}
			}
		}    	
		return allTestruns;		
	}
	
	
	/////////////////////
	/**
	 * Returns total Number of required Test Runs for a cycle incl child cycles
	 * int
	 * @return total Number of required Test Runs for a cycle incl child cycles
	 */
	public int getCascadedRequiredTestRunsCount()
	{
		int count = 0;
		if(this.isParent())
		{    		
			if(testruns != null && !testruns.isEmpty())
			{
				for(final Testrun testrun : testruns)
				{
					if(testrun.getPriority() <= this.requiredPriority)
					{
						count++;
					}
				}							
			}        	
			Collection<Cycle> childCycles = cycleService.getAllChildCycles(cycleID);
			if(childCycles != null && !childCycles.isEmpty())
			{
				for(final Cycle childCycle : childCycles)
				{
					if(childCycle.isChild())
					{
						for(final Testrun childCycletestrun : childCycle.testruns)
						{
							if(childCycletestrun.getPriority() <= this.requiredPriority)
							{
								count++;
							}
						}							
					}
				}
			}
		}    	
		return count;		
	}
	/**
	 * Returns a collection of required Testruns in a project incl all child project cycles 
	 * Collection<Testrun>
	 * @return collection of required Testruns in a project incl all child project cycles,
	 */
	public Collection<Testrun> getCascadedRequiredTestRuns()
	{
		Collection<Testrun> requiredTestruns = new ArrayList<Testrun>();
		if(this.isParent())
		{    		
			if(testruns != null && !testruns.isEmpty())
			{
				for(final Testrun testrun : testruns)
				{
					if(testrun.getPriority() <= this.requiredPriority)
					{
						requiredTestruns.add(testrun);						
					}
				}							
			}        	
			Collection<Cycle> childCycles = cycleService.getAllChildCycles(cycleID);
			if(childCycles != null && !childCycles.isEmpty())
			{
				for(final Cycle childCycle : childCycles)
				{
					if(childCycle.isChild())
					{
						for(final Testrun childCycletestrun : childCycle.testruns)
						{
							if(childCycletestrun.getPriority() <= this.requiredPriority)
							{
								requiredTestruns.add(childCycletestrun);
							}
						}							
					}
				}
			}
		}    	
		return requiredTestruns;		
	}

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
	 * @param codeImpactRule
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
			int projectPosition, long totalCycleEstTime, String cycleStartDate,
			String cycleEndDate, int codeImpactRule, int defectRule,
			int testHistoryRule, int requirementRule, String creationDate,
			String createdBy, String lastModifiedDate, String lastModifiedBy) {
		this.cycleName = cycleName;
		this.projectID = projectID;
		this.parentID = parentID;
		this.testruns = testruns;
		this.requiredPriority = requiredPriority;
		this.projectPosition = projectPosition;
		this.totalCycleEstTime = totalCycleEstTime;
		this.cycleStartDate = cycleStartDate;
		this.cycleEndDate = cycleEndDate;
		this.codeImpactRule = codeImpactRule;
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
	 * @return the codeImpactRule
	 */
	public int getCodeImpactRule() {
		return codeImpactRule;
	}

	/**
	 * @param codeImpactRule the codeImpactRule to set
	 */
	public void setCodeImpactRule(int codeImpactRule) {
		this.codeImpactRule = codeImpactRule;
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
	 * @return the testruns
	 */
	public Collection<Testrun> getTestruns() {
		return testruns;
	}

	/**
	 * @param Testruns the testruns to set
	 */
	public void setTestruns(Collection<Testrun> testruns) {
		this.testruns = testruns;
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

	

}