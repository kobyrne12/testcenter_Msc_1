/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.Date;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "Testplan")
public class Testplan {

	@Id        
	@GeneratedValue
	@Column(name = "testplanID")
	private Long testplanID;	

	@Basic
	@Column(name="companyID")
	private Long companyID;    

	@Length(min = 2, max = 32, message = "Testplan name must be between 2 to 32 characters.")
	@NotEmpty(message = "TestPlan Name is required.")
	private String testplanName;  
	
	@ManyToMany(mappedBy="testplans", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
    private Set<Project> projects = new HashSet<Project>();
	
	@OneToMany(fetch=FetchType.EAGER, 
			targetEntity=Testcase.class, 
			cascade=(CascadeType.ALL))
	@JoinColumn(name = "testplanID", referencedColumnName="testplanID")		
	@Fetch(value = FetchMode.SUBSELECT)
	
	private Set<Testcase> testcases;    
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=TestplanSection.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "testplanID", referencedColumnName="testplanID")		
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<TestplanSection> testplanSections;   
	
	@Temporal(TemporalType.DATE)
	@Column(name = "creationDate", nullable = false, length = 10) 
	private Date creationDate = new Date();	
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate", nullable = false, length = 10) 
	private Date lastModifiedDate = new Date();

	@Basic    
	private Long createdByUserID;
	@Basic    
	private Long lastModifiedByUserID;

	public Testplan() {		
	}
	/**
	 * @param companyID
	 * @param testplanName
	 */
	public Testplan(Long companyID,String testplanName) {
		this.companyID = companyID;
		this.testplanName = testplanName;	
	}
	/**
	 * @param companyID
	 * @param testplanName
	 * @param testcases
	 */
	public Testplan(Long companyID,String testplanName,Set<Testcase> testcases) {
		this.companyID = companyID;
		this.testplanName = testplanName;	
		this.testcases = testcases;
	}
	/**
	 * @param companyID
	 * @param testplanName
	 * @param testcases
	 * @param testplanSections
	 */
	public Testplan(Long companyID,String testplanName,Set<Testcase> testcases,
			Set<TestplanSection> testplanSections) {
		this.companyID = companyID;
		this.testplanName = testplanName;	
		this.testcases = testcases;
		this.testplanSections = testplanSections;
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
	 * @return the testplanName
	 */
	public String getTestplanName() {
		return testplanName;
	}

	/**
	 * @param testplanName the testplanName to set
	 */
	public void setTestplanName(String testplanName) {
		this.testplanName = testplanName;
	}

	/**
	 * @return the testcases
	 */
	public Set<Testcase> getTestcases() {
		return testcases;
	}

	/**
	 * @param testcases the testcases to set
	 */
	public void setTestcases(Set<Testcase> testcases) {
		this.testcases = testcases;
	}

	/**
	 * @return the testplanID
	 */
	public Long getTestplanID() {
		return testplanID;
	}
	/**
	 * @return the testplanSections
	 */
	public Set<TestplanSection> getTestplanSections() {
		return testplanSections;
	}
	/**
	 * @param testplanSections the testplanSections to set
	 */
	public void setTestplanSections(Set<TestplanSection> testplanSections) {
		this.testplanSections = testplanSections;
	}
	/**
	 * @return the projects
	 */
	public Set<Project> getProjects() {
		return projects;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
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
	
}