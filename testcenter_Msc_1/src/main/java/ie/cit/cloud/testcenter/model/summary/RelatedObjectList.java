/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author byrnek1
 *
 */

public class RelatedObjectList 
{	
	@Autowired
	CompanyService companyService;
	@Autowired
	CycleService cycleService;
	@Autowired
	TestplanService testplanService;
	@Autowired
	TestcaseService testcaseService;
	@Autowired
	TestrunService testrunService;
	@Autowired
	DefectService defectService;
	@Autowired
	RequirementService requirementService;
	@Autowired
	EnvironmentService environmentService;	
	
	private int allTestRunsCount;
	private Set<Testrun> allTestRuns;
	private int requiredTestRunsCount;
	private Set<Testrun> requiredTestRuns = new HashSet<Testrun>();
	private int optionalTestRunsCount;
	private Set<Testrun> optionalTestRuns = new HashSet<Testrun>();
	
	private int allTestCasesCount;
	private Set<Testcase> allTestCases = new HashSet<Testcase>();
	private int requiredTestCasesCount;
	private Set<Testcase> requiredTestCases = new HashSet<Testcase>();
	private int optionalTestCasesCount;
	private Set<Testcase> optionalTestCases = new HashSet<Testcase>();
	
	private int allTestPlansCount;
	private Set<Testplan> allTestPlans = new HashSet<Testplan>();
	private int requiredTestPlansCount;
	private Set<Testplan> requiredTestPlans = new HashSet<Testplan>();
	private int optionalTestPlansCount;
	private Set<Testplan> optionalTestPlans = new HashSet<Testplan>();
	
	private int projectsCount;
	private Set<Project> projects;
	private int cyclesCount;
	private Set<Cycle> cycles;
	private int requirementsCount;
	private Set<Requirement> requirements;
	private int environmentsCount;
	private Set<Environment> environments;
	
	private int defectsCount;
	private Set<Defect> defects;
	private int sev1defectsCount;
	private Set<Defect> sev1defects;
	private int sev2defectsCount;
	private Set<Defect> sev2defects;
	private int sev3defectsCount;
	private Set<Defect> sev3defects;
	private int sev4defectsCount;
	private Set<Defect> sev4defects;
	
	private int testersCount;
	private Set<TestcenterUser> testers;
	private int snrTestersCount;
	private Set<TestcenterUser> snrTesters;
	private int developersCount;
	private Set<TestcenterUser> developers;
	private int sndDevelopersCount;
	private Set<TestcenterUser> sndDevelopers;
	
	RelatedObjectList (Set<Testrun> allTestruns)
	{	
		this.allTestRuns = allTestruns;
	}
	
	
	/**
	 * @return the allTestRunsCount
	 */
	public int getAllTestRunsCount() 
	{
		if(getAllTestRuns() == null)
		{
			return 0;	
		}		
		return getAllTestRuns().size();
	}
	/**
	 * @param allTestRunsCount the allTestRunsCount to set
	 */
	public void setAllTestRunsCount(int allTestRunsCount) {
		this.allTestRunsCount = allTestRunsCount;
	}
	/**
	 * @return the allTestRuns
	 */
	public Set<Testrun> getAllTestRuns() 
	{
		return allTestRuns;
	}
	/**
	 * @param allTestRuns the allTestRuns to set
	 */
	public void setAllTestRuns(Set<Testrun> allTestRuns)
	{
		this.allTestRuns = allTestRuns;
	}
	/**
	 * @return the requiredTestRunsCount
	 */
	public int getRequiredTestRunsCount() 
	{
		if(getRequiredTestRuns() == null)
		{
			return 0;	
		}
		return getRequiredTestRuns().size();			
	}
	/**
	 * @param requiredTestRunsCount the requiredTestRunsCount to set
	 */
	public void setRequiredTestRunsCount(int requiredTestRunsCount)
	{
		this.requiredTestRunsCount = requiredTestRunsCount;
	}
	/**
	 * @return the requiredTestRuns
	 */
	public Set<Testrun> getRequiredTestRuns()
	{
		return allTestRuns;
		
	}
	/**
	 * @param requiredTestRuns the requiredTestRuns to set
	 */
	public void setRequiredTestRuns(Set<Testrun> requiredTestRuns) {
		this.requiredTestRuns = requiredTestRuns;
	}
	/**
	 * @return the optionalTestRunsCount
	 */
	public int getOptionalTestRunsCount() {
		return optionalTestRunsCount;
	}
	/**
	 * @param optionalTestRunsCount the optionalTestRunsCount to set
	 */
	public void setOptionalTestRunsCount(int optionalTestRunsCount) {
		this.optionalTestRunsCount = optionalTestRunsCount;
	}
	/**
	 * @return the optionalTestRuns
	 */
	public Set<Testrun> getOptionalTestRuns() {
		return optionalTestRuns;
	}
	/**
	 * @param optionalTestRuns the optionalTestRuns to set
	 */
	public void setOptionalTestRuns(Set<Testrun> optionalTestRuns) {
		this.optionalTestRuns = optionalTestRuns;
	}
	/**
	 * @return the allTestCasesCount
	 */
	public int getAllTestCasesCount() {
		return allTestCasesCount;
	}
	/**
	 * @param allTestCasesCount the allTestCasesCount to set
	 */
	public void setAllTestCasesCount(int allTestCasesCount) {
		this.allTestCasesCount = allTestCasesCount;
	}
	/**
	 * @return the allTestCases
	 */
	public Set<Testcase> getAllTestCases() {
		return allTestCases;
	}
	/**
	 * @param allTestCases the allTestCases to set
	 */
	public void setAllTestCases(Set<Testcase> allTestCases) {
		this.allTestCases = allTestCases;
	}
	/**
	 * @return the requiredTestCasesCount
	 */
	public int getRequiredTestCasesCount() {
		return requiredTestCasesCount;
	}
	/**
	 * @param requiredTestCasesCount the requiredTestCasesCount to set
	 */
	public void setRequiredTestCasesCount(int requiredTestCasesCount) {
		this.requiredTestCasesCount = requiredTestCasesCount;
	}
	/**
	 * @return the requiredTestCases
	 */
	public Set<Testcase> getRequiredTestCases() {
		return requiredTestCases;
	}
	/**
	 * @param requiredTestCases the requiredTestCases to set
	 */
	public void setRequiredTestCases(Set<Testcase> requiredTestCases) {
		this.requiredTestCases = requiredTestCases;
	}
	/**
	 * @return the optionalTestCasesCount
	 */
	public int getOptionalTestCasesCount() {
		return optionalTestCasesCount;
	}
	/**
	 * @param optionalTestCasesCount the optionalTestCasesCount to set
	 */
	public void setOptionalTestCasesCount(int optionalTestCasesCount) {
		this.optionalTestCasesCount = optionalTestCasesCount;
	}
	/**
	 * @return the optionalTestCases
	 */
	public Set<Testcase> getOptionalTestCases() {
		return optionalTestCases;
	}
	/**
	 * @param optionalTestCases the optionalTestCases to set
	 */
	public void setOptionalTestCases(Set<Testcase> optionalTestCases) {
		this.optionalTestCases = optionalTestCases;
	}
	/**
	 * @return the allTestPlansCount
	 */
	public int getAllTestPlansCount() {
		return allTestPlansCount;
	}
	/**
	 * @param allTestPlansCount the allTestPlansCount to set
	 */
	public void setAllTestPlansCount(int allTestPlansCount) {
		this.allTestPlansCount = allTestPlansCount;
	}
	/**
	 * @return the allTestPlans
	 */
	public Set<Testplan> getAllTestPlans() {
		return allTestPlans;
	}
	/**
	 * @param allTestPlans the allTestPlans to set
	 */
	public void setAllTestPlans(Set<Testplan> allTestPlans) {
		this.allTestPlans = allTestPlans;
	}
	/**
	 * @return the requiredTestPlansCount
	 */
	public int getRequiredTestPlansCount() {
		return requiredTestPlansCount;
	}
	/**
	 * @param requiredTestPlansCount the requiredTestPlansCount to set
	 */
	public void setRequiredTestPlansCount(int requiredTestPlansCount) {
		this.requiredTestPlansCount = requiredTestPlansCount;
	}
	/**
	 * @return the requiredTestPlans
	 */
	public Set<Testplan> getRequiredTestPlans() {
		return requiredTestPlans;
	}
	/**
	 * @param requiredTestPlans the requiredTestPlans to set
	 */
	public void setRequiredTestPlans(Set<Testplan> requiredTestPlans) {
		this.requiredTestPlans = requiredTestPlans;
	}
	/**
	 * @return the optionalTestPlansCount
	 */
	public int getOptionalTestPlansCount() {
		return optionalTestPlansCount;
	}
	/**
	 * @param optionalTestPlansCount the optionalTestPlansCount to set
	 */
	public void setOptionalTestPlansCount(int optionalTestPlansCount) {
		this.optionalTestPlansCount = optionalTestPlansCount;
	}
	/**
	 * @return the optionalTestPlans
	 */
	public Set<Testplan> getOptionalTestPlans() {
		return optionalTestPlans;
	}
	/**
	 * @param optionalTestPlans the optionalTestPlans to set
	 */
	public void setOptionalTestPlans(Set<Testplan> optionalTestPlans) {
		this.optionalTestPlans = optionalTestPlans;
	}
	/**
	 * @return the projectsCount
	 */
	public int getProjectsCount() {
		return projectsCount;
	}
	/**
	 * @param projectsCount the projectsCount to set
	 */
	public void setProjectsCount(int projectsCount) {
		this.projectsCount = projectsCount;
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
	 * @return the cyclesCount
	 */
	public int getCyclesCount() {
		return cyclesCount;
	}
	/**
	 * @param cyclesCount the cyclesCount to set
	 */
	public void setCyclesCount(int cyclesCount) {
		this.cyclesCount = cyclesCount;
	}
	/**
	 * @return the cycles
	 */
	public Set<Cycle> getCycles() {
		return cycles;
	}
	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(Set<Cycle> cycles) {
		this.cycles = cycles;
	}
	/**
	 * @return the requirementsCount
	 */
	public int getRequirementsCount() {
		return requirementsCount;
	}
	/**
	 * @param requirementsCount the requirementsCount to set
	 */
	public void setRequirementsCount(int requirementsCount) {
		this.requirementsCount = requirementsCount;
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
	 * @return the environmentsCount
	 */
	public int getEnvironmentsCount() {
		return environmentsCount;
	}
	/**
	 * @param environmentsCount the environmentsCount to set
	 */
	public void setEnvironmentsCount(int environmentsCount) {
		this.environmentsCount = environmentsCount;
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
	 * @return the defectsCount
	 */
	public int getDefectsCount() {
		return defectsCount;
	}
	/**
	 * @param defectsCount the defectsCount to set
	 */
	public void setDefectsCount(int defectsCount) {
		this.defectsCount = defectsCount;
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
	 * @return the sev1defectsCount
	 */
	public int getSev1defectsCount() {
		return sev1defectsCount;
	}
	/**
	 * @param sev1defectsCount the sev1defectsCount to set
	 */
	public void setSev1defectsCount(int sev1defectsCount) {
		this.sev1defectsCount = sev1defectsCount;
	}
	/**
	 * @return the sev1defects
	 */
	public Set<Defect> getSev1defects() {
		return sev1defects;
	}
	/**
	 * @param sev1defects the sev1defects to set
	 */
	public void setSev1defects(Set<Defect> sev1defects) {
		this.sev1defects = sev1defects;
	}
	/**
	 * @return the sev2defectsCount
	 */
	public int getSev2defectsCount() {
		return sev2defectsCount;
	}
	/**
	 * @param sev2defectsCount the sev2defectsCount to set
	 */
	public void setSev2defectsCount(int sev2defectsCount) {
		this.sev2defectsCount = sev2defectsCount;
	}
	/**
	 * @return the sev2defects
	 */
	public Set<Defect> getSev2defects() {
		return sev2defects;
	}
	/**
	 * @param sev2defects the sev2defects to set
	 */
	public void setSev2defects(Set<Defect> sev2defects) {
		this.sev2defects = sev2defects;
	}
	/**
	 * @return the sev3defectsCount
	 */
	public int getSev3defectsCount() {
		return sev3defectsCount;
	}
	/**
	 * @param sev3defectsCount the sev3defectsCount to set
	 */
	public void setSev3defectsCount(int sev3defectsCount) {
		this.sev3defectsCount = sev3defectsCount;
	}
	/**
	 * @return the sev3defects
	 */
	public Set<Defect> getSev3defects() {
		return sev3defects;
	}
	/**
	 * @param sev3defects the sev3defects to set
	 */
	public void setSev3defects(Set<Defect> sev3defects) {
		this.sev3defects = sev3defects;
	}
	/**
	 * @return the sev4defectsCount
	 */
	public int getSev4defectsCount() {
		return sev4defectsCount;
	}
	/**
	 * @param sev4defectsCount the sev4defectsCount to set
	 */
	public void setSev4defectsCount(int sev4defectsCount) {
		this.sev4defectsCount = sev4defectsCount;
	}
	/**
	 * @return the sev4defects
	 */
	public Set<Defect> getSev4defects() {
		return sev4defects;
	}
	/**
	 * @param sev4defects the sev4defects to set
	 */
	public void setSev4defects(Set<Defect> sev4defects) {
		this.sev4defects = sev4defects;
	}
	/**
	 * @return the testersCount
	 */
	public int getTestersCount() {
		return testersCount;
	}
	/**
	 * @param testersCount the testersCount to set
	 */
	public void setTestersCount(int testersCount) {
		this.testersCount = testersCount;
	}
	/**
	 * @return the testers
	 */
	public Set<TestcenterUser> getTesters() {
		return testers;
	}
	/**
	 * @param testers the testers to set
	 */
	public void setTesters(Set<TestcenterUser> testers) {
		this.testers = testers;
	}
	/**
	 * @return the snrTestersCount
	 */
	public int getSnrTestersCount() {
		return snrTestersCount;
	}
	/**
	 * @param snrTestersCount the snrTestersCount to set
	 */
	public void setSnrTestersCount(int snrTestersCount) {
		this.snrTestersCount = snrTestersCount;
	}
	/**
	 * @return the snrTesters
	 */
	public Set<TestcenterUser> getSnrTesters() {
		return snrTesters;
	}
	/**
	 * @param snrTesters the snrTesters to set
	 */
	public void setSnrTesters(Set<TestcenterUser> snrTesters) {
		this.snrTesters = snrTesters;
	}
	/**
	 * @return the developersCount
	 */
	public int getDevelopersCount() {
		return developersCount;
	}
	/**
	 * @param developersCount the developersCount to set
	 */
	public void setDevelopersCount(int developersCount) {
		this.developersCount = developersCount;
	}
	/**
	 * @return the developers
	 */
	public Set<TestcenterUser> getDevelopers() {
		return developers;
	}
	/**
	 * @param developers the developers to set
	 */
	public void setDevelopers(Set<TestcenterUser> developers) {
		this.developers = developers;
	}
	/**
	 * @return the sndDevelopersCount
	 */
	public int getSndDevelopersCount() {
		return sndDevelopersCount;
	}
	/**
	 * @param sndDevelopersCount the sndDevelopersCount to set
	 */
	public void setSndDevelopersCount(int sndDevelopersCount) {
		this.sndDevelopersCount = sndDevelopersCount;
	}
	/**
	 * @return the sndDevelopers
	 */
	public Set<TestcenterUser> getSndDevelopers() {
		return sndDevelopers;
	}
	/**
	 * @param sndDevelopers the sndDevelopers to set
	 */
	public void setSndDevelopers(Set<TestcenterUser> sndDevelopers) {
		this.sndDevelopers = sndDevelopers;
	}
	

	
}