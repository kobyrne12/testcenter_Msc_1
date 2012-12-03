package ie.cit.cloud.testcenter.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TestrunLevel")
public class TestrunLevel 
{
	
	@Id        
	@GeneratedValue
	@Column(name = "TestrunLevelID", unique = true, nullable = false)
	private Long TestrunLevelID;

	@Basic    
	private String testrunLevelName;
	
	@Basic    
	private int testrunLevelRequiredPercent;

	@OneToOne(mappedBy="testrunLevel", fetch=FetchType.EAGER)		
    @PrimaryKeyJoinColumn	
	private Testrun testrun;
	
	@OneToOne(mappedBy="testrunLevel", fetch=FetchType.EAGER)		
    @PrimaryKeyJoinColumn
	private Testcase testcase;
	
	@ManyToMany(mappedBy="testrunLevels", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Project> projects = new LinkedHashSet<Project>();
	
	/**
	 * @param projectID
	 * @param testrunLevelName
	 * @param testrunLevelRequiredPercent
	 */
	public TestrunLevel(Long projectID, String testrunLevelName,
			int testrunLevelRequiredPercent) {		
		this.testrunLevelName = testrunLevelName;
		this.testrunLevelRequiredPercent = testrunLevelRequiredPercent;
	}

	/**
	 * @return the testrunLevelName
	 */
	public String getTestrunLevelName() {
		return testrunLevelName;
	}

	/**
	 * @param testrunLevelName the testrunLevelName to set
	 */
	public void setTestrunLevelName(String testrunLevelName) {
		this.testrunLevelName = testrunLevelName;
	}

	/**
	 * @return the testrunLevelRequiredPercent
	 */
	public int getTestrunLevelRequiredPercent() {
		return testrunLevelRequiredPercent;
	}

	/**
	 * @param testrunLevelRequiredPercent the testrunLevelRequiredPercent to set
	 */
	public void setTestrunLevelRequiredPercent(int testrunLevelRequiredPercent) {
		this.testrunLevelRequiredPercent = testrunLevelRequiredPercent;
	}

	/**
	 * @return the testrunLevelID
	 */
	public Long getTestrunLevelID() {
		return TestrunLevelID;
	}

	/**
	 * @return the testrun
	 */
	public Testrun getTestrun() {
		return testrun;
	}

	/**
	 * @param testrun the testrun to set
	 */
	public void setTestrun(Testrun testrun) {
		this.testrun = testrun;
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
	 * @return the testcase
	 */
	public Testcase getTestcase() {
		return testcase;
	}

	/**
	 * @param testcase the testcase to set
	 */
	public void setTestcase(Testcase testcase) {
		this.testcase = testcase;
	}
	
	

}
