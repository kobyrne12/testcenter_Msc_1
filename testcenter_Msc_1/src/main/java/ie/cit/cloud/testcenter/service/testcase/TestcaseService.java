/**
 * 
 */
package ie.cit.cloud.testcenter.service.testcase;

/**
 * @author byrnek1
 *
 */

import java.util.Set;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;

/**
 * Peforms business operation on Test case 
 */
public interface TestcaseService {
 
	void addNewTestcase(Testcase testcase);

    Testcase getTestcase(Long testcaseID);   
    
    Testcase getTestcaseByName(String testcaseName);
    
    void update(Testcase testcase);
    
    void remove(Long testcaseID);  
    
    Long getLastTestRunID(Long testcaseID);

	Set<Testcase> getAllTestcases();
	
	/**
	 * Returns true if any of a testcase testruns' priority is less than or equal to cycle priority 
	 * boolean
	 * @return true if any of a testcase testruns' priority is less than or equal to cycle priority,otherwise false
	 */	
	boolean isRequired(Long testcaseID);	
	/////////////////////////	
	
	/**
	 * Returns a collection of All Testruns in a Testcase 
	 * Set<Testrun>
	 * @return collection of All Testruns in a Testcase,
	 */	
	Set<Testrun> getAllTestRuns(Long testcaseID);
	/**
	 * Returns a collection of All compulsory Testruns in a Testcase
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a Testcase,
	 */	
	Set<Testrun> getRequiredTestRuns(Long testcaseID);
	/**
	 * Returns a collection of All Optional Testruns in a Testcase
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a Testcase,
	 */	
	Set<Testrun> getOptionalTestRuns(Long testcaseID);
	/**
	 * Returns a testcases Cycles
	 * Cycle
	 * @return a testcases Cycles
	 */		
	Set<Cycle> getAllCycles(Long testcaseID);	
	Set<Cycle> getRequiredCycles(Long testcaseID);
	/**
	 * Returns a testcases Projects
	 * Project
	 * @return a testcases Projects
	 */	
	Set<Project> getProjects(Long testcaseID);	
	/**
	 * Returns a collection of Requirements in a Testcase
	 * Set<Requirement>
	 * @return collection of Requirements in a Testcase,
	 */		
	Set<Requirement> getRequirements(Long testcaseID) ;		
	/**
	 * Returns a collection of Environments in a Testcase
	 * Set<Environment>
	 * @return collection of Environments in a Testcase,
	 */		
	Set<Environment> getEnvironments(Long testcaseID) ;
	/**
	 * Returns a collection of All Defects in a Testcase 
	 * Set<Defect>
	 * @return collection of All Defects in a Testcase,
	 */		
	Set<Defect> getCascadedAllDefects(Long testcaseID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a Testcase,
	 */		
	Set<Defect> getCascadedSev1Defects(Long testcaseID);
	/**
	 * Returns a collection of Sev2 Defects in a Testcase 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a Testcase,
	 */		
	Set<Defect> getCascadedSev2Defects(Long testcaseID);
	/**
	 * Returns a collection of Sev 3 Defects in a Testcase 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a Testcase,
	 */		
	Set<Defect> getCascadedSev3Defects(Long testcaseID);
	/**
	 * Returns a collection of Sev 4 Defects in a Testcase 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a Testcase,
	 */		
	Set<Defect> getCascadedSev4Defects(Long testcaseID);
	
	Set<TestcenterUser> getCascadedTesters(Long testcaseID);
	Set<TestcenterUser> getCascadedSnrTesters(Long testcaseID);
	Set<TestcenterUser> getCascadedDevelopers(Long testcaseID);
	Set<TestcenterUser> getCascadedSnrDevelopers(Long testcaseID);
	
	ColModelAndNames getColumnModelAndNames(Long companyID);
	
	TestcaseSummaryList getGridTestcases(Long companyID, String projectID,
				String cycleID, String testplanID, String testcaseID,
				String testrunID, String defectID, String requirementID,
				String environmentID, String userID,String levelName,String stage,String required);

	Testplan getTestcaseTestplan(Long testcaseID);
	String getTestcaseTestplanName(Long testcaseID);

	
	//TestcaseSummary getTestcaseSummary(Long companyID, Testcase testcase,
	//		String level,String stage,String required);

}