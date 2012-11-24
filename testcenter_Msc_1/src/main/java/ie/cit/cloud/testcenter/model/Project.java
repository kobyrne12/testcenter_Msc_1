/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.service.project.ProjectService;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "Project")
public class Project {
	
	@Id        
	@GeneratedValue
	@Column(name = "projectID", unique = true, nullable = false)
	private long projectID;

	@Basic
	@Column(name="companyID")
	private Long companyID;

	@Length(min = 2, max = 32, message = "Project name must be between 2 to 32 characters.")
	@NotEmpty(message = "Project Name is required.")
	private String projectName;  	 

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Cycle.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "projectID", referencedColumnName="projectID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Cycle> cycles = new ArrayList<Cycle>();   

	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "PROJECT_JOIN_TESTPLANS", joinColumns = { @JoinColumn(name = "projectID") }, inverseJoinColumns = { @JoinColumn(name = "testplanID") })
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Testplan> testplans  = new ArrayList<Testplan>();    	

	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "PROJECT_JOIN_TESTCASES",
	joinColumns = { @JoinColumn(name = "projectID") },
	inverseJoinColumns = { @JoinColumn(name = "testcaseID") })
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Testcase> testcases  = new ArrayList<Testcase>();    	

	@Basic    
	private long parentID;   
	@Basic    
	private boolean parent;  
	@Basic    
	private boolean child; 
	@Basic    
	private int regressionRequiredPercent;
	@Basic    
	private int newFeatureRequiredPercent;
	@Basic    
	private int allowedSev1;
	@Basic    
	private int allowedSev2;
	@Basic    
	private int allowedSev3;
	@Basic    
	private int allowedSev4;
	@Basic    
	private String creationDate;
	@Basic    
	private String createdBy;
	@Basic    
	private String lastModifiedDate;
	@Basic    
	private String lastModifiedBy;   
	
	// @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")

	public Project() {	
	}

	public Project(long companyID, String projectName,long parentID,int regressionRequiredPercent,int newFeatureRequiredPercent, int allowedSev1,int allowedSev2,int allowedSev3,int allowedSev4,String lastModifiedDate,String lastModifiedBy ) {
		this(companyID,projectName,
				null,null,null,parentID,
				regressionRequiredPercent,newFeatureRequiredPercent,
				allowedSev1,allowedSev2,allowedSev3,allowedSev4, 
				lastModifiedDate, lastModifiedBy);
	}

	public Project(long companyID, String projectName,
			Collection<Cycle> cycles,Collection<Testplan> tesplans,	Collection<Testcase> testcases, long parentID,
			int regressionRequiredPercent,int newFeatureRequiredPercent,
			int allowedSev1,int allowedSev2,int allowedSev3,int allowedSev4,
			String lastModifiedDate,String lastModifiedBy) {
		this.companyID = companyID;    	
		this.projectName = projectName;    	
		this.cycles = cycles;
		this.testplans = tesplans;
		this.testcases = testcases;
		this.parentID = parentID;		
		this.regressionRequiredPercent = regressionRequiredPercent;
		this.newFeatureRequiredPercent = newFeatureRequiredPercent;
		this.allowedSev1 = allowedSev1;
		this.allowedSev2 = allowedSev2;
		this.allowedSev3 = allowedSev3;
		this.allowedSev4 = allowedSev4;	
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;

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
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the cycles
	 */
	public Collection<Cycle> getCycles() {
		return cycles;
	}
	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(Collection<Cycle> cycles) {
		this.cycles = cycles;
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
	 * @return the regressionRequiredPercent
	 */
	public int getRegressionRequiredPercent() {
		return regressionRequiredPercent;
	}
	/**
	 * @param regressionRequiredPercent the regressionRequiredPercent to set
	 */
	public void setRegressionRequiredPercent(int regressionRequiredPercent) {
		this.regressionRequiredPercent = regressionRequiredPercent;
	}
	/**
	 * @return the newFeatureRequiredPercent
	 */
	public int getNewFeatureRequiredPercent() {
		return newFeatureRequiredPercent;
	}
	/**
	 * @param newFeatureRequiredPercent the newFeatureRequiredPercent to set
	 */
	public void setNewFeatureRequiredPercent(int newFeatureRequiredPercent) {
		this.newFeatureRequiredPercent = newFeatureRequiredPercent;
	}
	/**
	 * @return the allowedSev1
	 */
	public int getAllowedSev1() {
		return allowedSev1;
	}
	/**
	 * @param allowedSev1 the allowedSev1 to set
	 */
	public void setAllowedSev1(int allowedSev1) {
		this.allowedSev1 = allowedSev1;
	}
	/**
	 * @return the allowedSev2
	 */
	public int getAllowedSev2() {
		return allowedSev2;
	}
	/**
	 * @param allowedSev2 the allowedSev2 to set
	 */
	public void setAllowedSev2(int allowedSev2) {
		this.allowedSev2 = allowedSev2;
	}
	/**
	 * @return the allowedSev3
	 */
	public int getAllowedSev3() {
		return allowedSev3;
	}
	/**
	 * @param allowedSev3 the allowedSev3 to set
	 */
	public void setAllowedSev3(int allowedSev3) {
		this.allowedSev3 = allowedSev3;
	}
	/**
	 * @return the allowedSev4
	 */
	public int getAllowedSev4() {
		return allowedSev4;
	}
	/**
	 * @param allowedSev4 the allowedSev4 to set
	 */
	public void setAllowedSev4(int allowedSev4) {
		this.allowedSev4 = allowedSev4;
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
	 * @return the projectID
	 */
	public long getProjectID() {
		return projectID;
	}
	/**
	 * @return the testplans
	 */
	public Collection<Testplan> getTestplans() {
		return testplans;
	}
	/**
	 * @param testplans the testplans to set
	 */
	public void setTestplans(Collection<Testplan> testplans) {
		this.testplans = testplans;
	}
	/**
	 * @return the testcases
	 */
	public Collection<Testcase> getTestcases() {
		return testcases;
	}
	/**
	 * @param testcases the testcases to set
	 */
	public void setTestcases(Collection<Testcase> testcases) {
		this.testcases = testcases;
	}


}