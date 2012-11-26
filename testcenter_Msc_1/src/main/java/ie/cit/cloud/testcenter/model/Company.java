/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.service.company.CompanyService;

import java.util.Set;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "Company")
public class Company
{
	
	@Autowired @Transient
	private CompanyService companyService;
	
	@Id        
	@GeneratedValue
	@Column(name = "companyID", unique = true, nullable = false)
	private long companyID;

	@Length(min = 2, max = 32, message = "Company name must be between 2 to 32 characters.")
	@NotEmpty(message = "Company Name is required.")
	@Column(name = "companyName", unique = true, nullable = false)
	private String companyName; 	

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Project.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "companyID", referencedColumnName="companyID")	
	private Set<Project> projects; 

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Testplan.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "companyID", referencedColumnName="companyID")	
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Testplan> testplans;   

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Testcase.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "companyID", referencedColumnName="companyID")	
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Testcase> testcases; 

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Defect.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "companyID", referencedColumnName="companyID")	
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Defect> defects;   

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Environment.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "companyID", referencedColumnName="companyID")	
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Environment> environments;  

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Requirement.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "companyID", referencedColumnName="companyID")	
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Requirement> requirements;  

	@Basic    
	private String projectDisplayName;
	@Basic    
	private String projectsDisplayName;
	@Basic    
	private String reportDisplayName;
	@Basic    
	private String reportsDisplayName;
	@Basic    
	private String defectDisplayName;
	@Basic    
	private String defectsDisplayName;
	@Basic    
	private String requirementDisplayName;
	@Basic    
	private String requirementsDisplayName;
	@Basic    
	private String cycleDisplayName;
	@Basic    
	private String cyclesDisplayName;
	@Basic    
	private String userDisplayName;
	@Basic    
	private String usersDisplayName;
	@Basic    
	private String environmentDisplayName;
	@Basic    
	private String environmentsDisplayName;
	@Basic    
	private String testplanDisplayName;
	@Basic    
	private String testplansDisplayName;
	@Basic    
	private String testcaseDisplayName;
	@Basic    
	private String testcasesDisplayName;
	@Basic    
	private String testrunDisplayName;
	@Basic    
	private String testrunsDisplayName;
	@Basic    
	private String testerDisplayName;
	@Basic    
	private String testersDisplayName;
	@Basic    
	private String developerDisplayName;
	@Basic    
	private String developersDisplayName;
	@Basic    
	private String seniorTesterDisplayName;
	@Basic    
	private String seniorTestersDisplayName;
	@Basic    
	private String seniordeveloperDisplayName;
	@Basic    
	private String seniordevelopersDisplayName;
	@Basic    
	private String testLibraryDisplayName;  
	@Basic    
	private String regressionDisplayName;  
	@Basic    
	private String newFeatureDisplayName;  
	@Basic    
	private String sanityDisplayName;  

	@Temporal(TemporalType.DATE)
	@Column(name = "creationDate", nullable = false, length = 10) 
	private Date creationDate;	
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate", nullable = false, length = 10) 
	private Date lastModifiedDate;

	@Basic    
	private String createdBy;	
	@Basic    
	private String lastModifiedBy;
		
	public Company() {	
	}

	public Company(String companyName,Date creationDate,String createdBy) 
	{
		this(companyName,null,null, null, null, null, null, 
				"Project","Projects",
				"Report","Reports",
				"Defect","Defects",
				"Requirement","Requirements",
				"Cycle","Cycles",
				"User","Users",
				"Environment","Environments",
				"Test Plan","Test Plans",
				"Test Case","Test Cases",
				"Test Run","Test Runs",
				"Tester","Testers",
				"Senior Tester","Senior Testers",
				"Developer","Developers",
				"Senior Developer","Senior Developers",
				"Test Library",
				"Regression",
				"New Feature",
				"Sanity",
				creationDate, creationDate, createdBy,createdBy);    	
	}

	/**
	 * @param companyName
	 * @param projects
	 * @param testplans
	 * @param testcases
	 * @param defects
	 * @param environments
	 * @param requirements
	 * @param projectDisplayName
	 * @param projectsDisplayName
	 * @param reportDisplayName
	 * @param reportsDisplayName
	 * @param defectDisplayName
	 * @param defectsDisplayName
	 * @param requirementDisplayName
	 * @param requirementsDisplayName
	 * @param cycleDisplayName
	 * @param cyclesDisplayName
	 * @param userDisplayName
	 * @param usersDisplayName
	 * @param environmentDisplayName
	 * @param environmentsDisplayName
	 * @param testplanDisplayName
	 * @param testplansDisplayName
	 * @param testcaseDisplayName
	 * @param testcasesDisplayName
	 * @param testrunDisplayName
	 * @param testrunsDisplayName
	 * @param testerDisplayName
	 * @param testersDisplayName
	 * @param developerDisplayName
	 * @param developersDisplayName
	 * @param seniorTesterDisplayName
	 * @param seniorTestersDisplayName
	 * @param seniordeveloperDisplayName
	 * @param seniordevelopersDisplayName
	 * @param testLibraryDisplayName
	 * @param regressionDisplayName
	 * @param newFeatureDisplayName
	 * @param sanityDisplayName
	 * @param creationDate
	 * @param lastModifiedDate
	 * @param createdBy
	 * @param lastModifiedBy
	 */
	public Company(String companyName, Set<Project> projects,
			Set<Testplan> testplans, Set<Testcase> testcases,
			Set<Defect> defects, Set<Environment> environments,
			Set<Requirement> requirements, String projectDisplayName,
			String projectsDisplayName, String reportDisplayName,
			String reportsDisplayName, String defectDisplayName,
			String defectsDisplayName, String requirementDisplayName,
			String requirementsDisplayName, String cycleDisplayName,
			String cyclesDisplayName, String userDisplayName,
			String usersDisplayName, String environmentDisplayName,
			String environmentsDisplayName, String testplanDisplayName,
			String testplansDisplayName, String testcaseDisplayName,
			String testcasesDisplayName, String testrunDisplayName,
			String testrunsDisplayName, String testerDisplayName,
			String testersDisplayName, String developerDisplayName,
			String developersDisplayName, String seniorTesterDisplayName,
			String seniorTestersDisplayName, String seniordeveloperDisplayName,
			String seniordevelopersDisplayName, String testLibraryDisplayName,
			String regressionDisplayName, String newFeatureDisplayName,
			String sanityDisplayName, Date creationDate, Date lastModifiedDate,
			String createdBy, String lastModifiedBy) {
		this.companyName = companyName;
		this.projects = projects;
		this.testplans = testplans;
		this.testcases = testcases;
		this.defects = defects;
		this.environments = environments;
		this.requirements = requirements;
		this.projectDisplayName = projectDisplayName;
		this.projectsDisplayName = projectsDisplayName;
		this.reportDisplayName = reportDisplayName;
		this.reportsDisplayName = reportsDisplayName;
		this.defectDisplayName = defectDisplayName;
		this.defectsDisplayName = defectsDisplayName;
		this.requirementDisplayName = requirementDisplayName;
		this.requirementsDisplayName = requirementsDisplayName;
		this.cycleDisplayName = cycleDisplayName;
		this.cyclesDisplayName = cyclesDisplayName;
		this.userDisplayName = userDisplayName;
		this.usersDisplayName = usersDisplayName;
		this.environmentDisplayName = environmentDisplayName;
		this.environmentsDisplayName = environmentsDisplayName;
		this.testplanDisplayName = testplanDisplayName;
		this.testplansDisplayName = testplansDisplayName;
		this.testcaseDisplayName = testcaseDisplayName;
		this.testcasesDisplayName = testcasesDisplayName;
		this.testrunDisplayName = testrunDisplayName;
		this.testrunsDisplayName = testrunsDisplayName;
		this.testerDisplayName = testerDisplayName;
		this.testersDisplayName = testersDisplayName;
		this.developerDisplayName = developerDisplayName;
		this.developersDisplayName = developersDisplayName;
		this.seniorTesterDisplayName = seniorTesterDisplayName;
		this.seniorTestersDisplayName = seniorTestersDisplayName;
		this.seniordeveloperDisplayName = seniordeveloperDisplayName;
		this.seniordevelopersDisplayName = seniordevelopersDisplayName;
		this.testLibraryDisplayName = testLibraryDisplayName;
		this.regressionDisplayName = regressionDisplayName;
		this.newFeatureDisplayName = newFeatureDisplayName;
		this.sanityDisplayName = sanityDisplayName;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * @return the testplans
	 */
	public Set<Testplan> getTestplans() {
		return testplans;
	}

	/**
	 * @param testplans the testplans to set
	 */
	public void setTestplans(Set<Testplan> testplans) {
		this.testplans = testplans;
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
	 * @return the defects
	 */
	public Set<Defect> getDefects() {
		return defects;
	}

	/**
	 * @param defects the defects to set
	 */
	public void setDefects(Set<Defect> defects) {
		this.defects = defects;
	}

	/**
	 * @return the environments
	 */
	public Set<Environment> getEnvironments() {
		return environments;
	}

	/**
	 * @param environments the environments to set
	 */
	public void setEnvironments(Set<Environment> environments) {
		this.environments = environments;
	}

	/**
	 * @return the requirements
	 */
	public Set<Requirement> getRequirements() {
		return requirements;
	}

	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}

	/**
	 * @return the projectDisplayName
	 */
	public String getProjectDisplayName() {
		return projectDisplayName;
	}

	/**
	 * @param projectDisplayName the projectDisplayName to set
	 */
	public void setProjectDisplayName(String projectDisplayName) {
		this.projectDisplayName = projectDisplayName;
	}

	/**
	 * @return the projectsDisplayName
	 */
	public String getProjectsDisplayName() {
		return projectsDisplayName;
	}

	/**
	 * @param projectsDisplayName the projectsDisplayName to set
	 */
	public void setProjectsDisplayName(String projectsDisplayName) {
		this.projectsDisplayName = projectsDisplayName;
	}

	/**
	 * @return the reportDisplayName
	 */
	public String getReportDisplayName() {
		return reportDisplayName;
	}

	/**
	 * @param reportDisplayName the reportDisplayName to set
	 */
	public void setReportDisplayName(String reportDisplayName) {
		this.reportDisplayName = reportDisplayName;
	}

	/**
	 * @return the reportsDisplayName
	 */
	public String getReportsDisplayName() {
		return reportsDisplayName;
	}

	/**
	 * @param reportsDisplayName the reportsDisplayName to set
	 */
	public void setReportsDisplayName(String reportsDisplayName) {
		this.reportsDisplayName = reportsDisplayName;
	}

	/**
	 * @return the defectDisplayName
	 */
	public String getDefectDisplayName() {
		return defectDisplayName;
	}

	/**
	 * @param defectDisplayName the defectDisplayName to set
	 */
	public void setDefectDisplayName(String defectDisplayName) {
		this.defectDisplayName = defectDisplayName;
	}

	/**
	 * @return the defectsDisplayName
	 */
	public String getDefectsDisplayName() {
		return defectsDisplayName;
	}

	/**
	 * @param defectsDisplayName the defectsDisplayName to set
	 */
	public void setDefectsDisplayName(String defectsDisplayName) {
		this.defectsDisplayName = defectsDisplayName;
	}

	/**
	 * @return the requirementDisplayName
	 */
	public String getRequirementDisplayName() {
		return requirementDisplayName;
	}

	/**
	 * @param requirementDisplayName the requirementDisplayName to set
	 */
	public void setRequirementDisplayName(String requirementDisplayName) {
		this.requirementDisplayName = requirementDisplayName;
	}

	/**
	 * @return the requirementsDisplayName
	 */
	public String getRequirementsDisplayName() {
		return requirementsDisplayName;
	}

	/**
	 * @param requirementsDisplayName the requirementsDisplayName to set
	 */
	public void setRequirementsDisplayName(String requirementsDisplayName) {
		this.requirementsDisplayName = requirementsDisplayName;
	}

	/**
	 * @return the cycleDisplayName
	 */
	public String getCycleDisplayName() {
		return cycleDisplayName;
	}

	/**
	 * @param cycleDisplayName the cycleDisplayName to set
	 */
	public void setCycleDisplayName(String cycleDisplayName) {
		this.cycleDisplayName = cycleDisplayName;
	}

	/**
	 * @return the cyclesDisplayName
	 */
	public String getCyclesDisplayName() {
		return cyclesDisplayName;
	}

	/**
	 * @param cyclesDisplayName the cyclesDisplayName to set
	 */
	public void setCyclesDisplayName(String cyclesDisplayName) {
		this.cyclesDisplayName = cyclesDisplayName;
	}

	/**
	 * @return the userDisplayName
	 */
	public String getUserDisplayName() {
		return userDisplayName;
	}

	/**
	 * @param userDisplayName the userDisplayName to set
	 */
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	/**
	 * @return the usersDisplayName
	 */
	public String getUsersDisplayName() {
		return usersDisplayName;
	}

	/**
	 * @param usersDisplayName the usersDisplayName to set
	 */
	public void setUsersDisplayName(String usersDisplayName) {
		this.usersDisplayName = usersDisplayName;
	}

	/**
	 * @return the environmentDisplayName
	 */
	public String getEnvironmentDisplayName() {
		return environmentDisplayName;
	}

	/**
	 * @param environmentDisplayName the environmentDisplayName to set
	 */
	public void setEnvironmentDisplayName(String environmentDisplayName) {
		this.environmentDisplayName = environmentDisplayName;
	}

	/**
	 * @return the environmentsDisplayName
	 */
	public String getEnvironmentsDisplayName() {
		return environmentsDisplayName;
	}

	/**
	 * @param environmentsDisplayName the environmentsDisplayName to set
	 */
	public void setEnvironmentsDisplayName(String environmentsDisplayName) {
		this.environmentsDisplayName = environmentsDisplayName;
	}

	/**
	 * @return the testplanDisplayName
	 */
	public String getTestplanDisplayName() {
		return testplanDisplayName;
	}

	/**
	 * @param testplanDisplayName the testplanDisplayName to set
	 */
	public void setTestplanDisplayName(String testplanDisplayName) {
		this.testplanDisplayName = testplanDisplayName;
	}

	/**
	 * @return the testplansDisplayName
	 */
	public String getTestplansDisplayName() {
		return testplansDisplayName;
	}

	/**
	 * @param testplansDisplayName the testplansDisplayName to set
	 */
	public void setTestplansDisplayName(String testplansDisplayName) {
		this.testplansDisplayName = testplansDisplayName;
	}

	/**
	 * @return the testcaseDisplayName
	 */
	public String getTestcaseDisplayName() {
		return testcaseDisplayName;
	}

	/**
	 * @param testcaseDisplayName the testcaseDisplayName to set
	 */
	public void setTestcaseDisplayName(String testcaseDisplayName) {
		this.testcaseDisplayName = testcaseDisplayName;
	}

	/**
	 * @return the testcasesDisplayName
	 */
	public String getTestcasesDisplayName() {
		return testcasesDisplayName;
	}

	/**
	 * @param testcasesDisplayName the testcasesDisplayName to set
	 */
	public void setTestcasesDisplayName(String testcasesDisplayName) {
		this.testcasesDisplayName = testcasesDisplayName;
	}

	/**
	 * @return the testrunDisplayName
	 */
	public String getTestrunDisplayName() {
		return testrunDisplayName;
	}

	/**
	 * @param testrunDisplayName the testrunDisplayName to set
	 */
	public void setTestrunDisplayName(String testrunDisplayName) {
		this.testrunDisplayName = testrunDisplayName;
	}

	/**
	 * @return the testrunsDisplayName
	 */
	public String getTestrunsDisplayName() {
		return testrunsDisplayName;
	}

	/**
	 * @param testrunsDisplayName the testrunsDisplayName to set
	 */
	public void setTestrunsDisplayName(String testrunsDisplayName) {
		this.testrunsDisplayName = testrunsDisplayName;
	}

	/**
	 * @return the testerDisplayName
	 */
	public String getTesterDisplayName() {
		return testerDisplayName;
	}

	/**
	 * @param testerDisplayName the testerDisplayName to set
	 */
	public void setTesterDisplayName(String testerDisplayName) {
		this.testerDisplayName = testerDisplayName;
	}

	/**
	 * @return the testersDisplayName
	 */
	public String getTestersDisplayName() {
		return testersDisplayName;
	}

	/**
	 * @param testersDisplayName the testersDisplayName to set
	 */
	public void setTestersDisplayName(String testersDisplayName) {
		this.testersDisplayName = testersDisplayName;
	}

	/**
	 * @return the developerDisplayName
	 */
	public String getDeveloperDisplayName() {
		return developerDisplayName;
	}

	/**
	 * @param developerDisplayName the developerDisplayName to set
	 */
	public void setDeveloperDisplayName(String developerDisplayName) {
		this.developerDisplayName = developerDisplayName;
	}

	/**
	 * @return the developersDisplayName
	 */
	public String getDevelopersDisplayName() {
		return developersDisplayName;
	}

	/**
	 * @param developersDisplayName the developersDisplayName to set
	 */
	public void setDevelopersDisplayName(String developersDisplayName) {
		this.developersDisplayName = developersDisplayName;
	}

	/**
	 * @return the seniorTesterDisplayName
	 */
	public String getSeniorTesterDisplayName() {
		return seniorTesterDisplayName;
	}

	/**
	 * @param seniorTesterDisplayName the seniorTesterDisplayName to set
	 */
	public void setSeniorTesterDisplayName(String seniorTesterDisplayName) {
		this.seniorTesterDisplayName = seniorTesterDisplayName;
	}

	/**
	 * @return the seniorTestersDisplayName
	 */
	public String getSeniorTestersDisplayName() {
		return seniorTestersDisplayName;
	}

	/**
	 * @param seniorTestersDisplayName the seniorTestersDisplayName to set
	 */
	public void setSeniorTestersDisplayName(String seniorTestersDisplayName) {
		this.seniorTestersDisplayName = seniorTestersDisplayName;
	}

	/**
	 * @return the seniordeveloperDisplayName
	 */
	public String getSeniordeveloperDisplayName() {
		return seniordeveloperDisplayName;
	}

	/**
	 * @param seniordeveloperDisplayName the seniordeveloperDisplayName to set
	 */
	public void setSeniordeveloperDisplayName(String seniordeveloperDisplayName) {
		this.seniordeveloperDisplayName = seniordeveloperDisplayName;
	}

	/**
	 * @return the seniordevelopersDisplayName
	 */
	public String getSeniordevelopersDisplayName() {
		return seniordevelopersDisplayName;
	}

	/**
	 * @param seniordevelopersDisplayName the seniordevelopersDisplayName to set
	 */
	public void setSeniordevelopersDisplayName(String seniordevelopersDisplayName) {
		this.seniordevelopersDisplayName = seniordevelopersDisplayName;
	}

	/**
	 * @return the testLibraryDisplayName
	 */
	public String getTestLibraryDisplayName() {
		return testLibraryDisplayName;
	}

	/**
	 * @param testLibraryDisplayName the testLibraryDisplayName to set
	 */
	public void setTestLibraryDisplayName(String testLibraryDisplayName) {
		this.testLibraryDisplayName = testLibraryDisplayName;
	}

	/**
	 * @return the regressionDisplayName
	 */
	public String getRegressionDisplayName() {
		return regressionDisplayName;
	}

	/**
	 * @param regressionDisplayName the regressionDisplayName to set
	 */
	public void setRegressionDisplayName(String regressionDisplayName) {
		this.regressionDisplayName = regressionDisplayName;
	}

	/**
	 * @return the newFeatureDisplayName
	 */
	public String getNewFeatureDisplayName() {
		return newFeatureDisplayName;
	}

	/**
	 * @param newFeatureDisplayName the newFeatureDisplayName to set
	 */
	public void setNewFeatureDisplayName(String newFeatureDisplayName) {
		this.newFeatureDisplayName = newFeatureDisplayName;
	}

	/**
	 * @return the sanityDisplayName
	 */
	public String getSanityDisplayName() {
		return sanityDisplayName;
	}

	/**
	 * @param sanityDisplayName the sanityDisplayName to set
	 */
	public void setSanityDisplayName(String sanityDisplayName) {
		this.sanityDisplayName = sanityDisplayName;
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
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}	
	
}