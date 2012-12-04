/**
 * 
 */
package ie.cit.cloud.testcenter.service.testplan;

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
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.TestplanSection;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestplanSummaryList;

/**
 * Peforms business operation on Test plan 
 */
public interface TestplanService {
 
	void addNewTestplan(Testplan testplan);

    Testplan getTestplan(Long testplanID);     
    
    Testplan getTestplanByName(String testplanName);    
    
    void update(Testplan testplan);    
    
    void remove(Long testplanID);  
    
    TestplanSection getTestplanSection(Long testplanSectionID);
    void addNewTestplanSection(TestplanSection testplanSection);
    void updateTestplanSection(TestplanSection testplanSection);    
    void removeTestplanSection(Long testplanSectionID);  
    ///////////////////////////
    /**
	 * Returns a collection of All Testcases for a test plan
	 * Set<Testcase>
	 * @return collection of All Testcases for a test plan
	 */	
	 Set<Testcase> getAllTestCases(Long testplanID);
	/**
	 * Returns a collection of All Compulsory Testcases for a test plan
	 * Set<Testplan>
	 * @return collection of All Compulsory Testcases for a test plan
	 */	
	 Set<Testcase> getCompulsoryTestCases(Long testplanID);
	/**
	 * Returns a collection of All Optional Testcases for a test plan
	 * Set<Testcase>
	 * @return collection of All OptionalTestcases for a test plan
	 */	
	 Set<Testcase> getOptionalTestCases(Long testplanID);
    /**
	 * Returns a collection of All Testruns in a Testplan 
	 * Set<Testrun>
	 * @return collection of All Testruns in a Testplan,
	 */	
	Set<Testrun> getAllTestRuns(Long testplanID);
	/**
	 * Returns a collection of All compulsory Testruns in a Testplan
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a Testplan,
	 */	
	Set<Testrun> getCompulsoryTestRuns(Long testplanID);
	/**
	 * Returns a collection of All Optional Testruns in a Testplan
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a Testplan,
	 */	
	Set<Testrun> getOptionalTestRuns(Long testplanID);
	/**
	 * Returns true if any of a testcase testruns' in a tesplan priority is less than or equal to cycle priority 
	 * boolean
	 * @return true if any of a testcase testruns' in a tesplanpriority is less than or equal to cycle priority,otherwise false
	 */	
	boolean isRequired(Long testcaseID);
	/**
	 * Returns a testplans Cycles
	 * Cycle
	 * @return a testplans Cycles
	 */		
	Set<Cycle> getCycles(Long testplanID);	
	/**
	 * Returns a testplans Projects
	 * Project
	 * @return a testplans Projects
	 */	
	Set<Project> getProjects(Long testplanID);	
	/**
	 * Returns a collection of Requirements in a Testplan
	 * Set<Requirement>
	 * @return collection of Requirements in a Testplan,
	 */		
	Set<Requirement> getRequirements(Long testplanID) ;		
	/**
	 * Returns a collection of Environments in a Testplan
	 * Set<Environment>
	 * @return collection of Environments in a Testplan,
	 */		
	Set<Environment> getEnvironments(Long testplanID) ;
	/**
	 * Returns a collection of All Defects in a Testplan 
	 * Set<Defect>
	 * @return collection of All Defects in a Testplan,
	 */		
	Set<Defect> getCascadedAllDefects(Long testplanID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a Testplan,
	 */		
	Set<Defect> getCascadedSev1Defects(Long testplanID);
	/**
	 * Returns a collection of Sev2 Defects in a Testplan 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a Testplan,
	 */		
	Set<Defect> getCascadedSev2Defects(Long testplanID);
	/**
	 * Returns a collection of Sev 3 Defects in a Testplan 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a Testplan,
	 */		
	Set<Defect> getCascadedSev3Defects(Long testplanID);
	/**
	 * Returns a collection of Sev 4 Defects in a Testplan 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a Testplan,
	 */		
	Set<Defect> getCascadedSev4Defects(Long testplanID);
	
	Set<TestcenterUser> getCascadedUsers(Long testplanID);
	Set<TestcenterUser> getCascadedTesters(Long testplanID);
	Set<TestcenterUser> getCascadedSnrTesters(Long testplanID);
	Set<TestcenterUser> getCascadedDevelopers(Long testplanID);
	Set<TestcenterUser> getCascadedSnrDevelopers(Long testplanID);
	
	ColModelAndNames getColumnModelAndNames(Long companyID);
	
	TestplanSummaryList getGridTestplans(Long companyID, String projectID,
				String cycleID, String testplanID, String testcaseID,
				String testrunID, String defectID, String requirementID,
				String environmentID, String userID,String levelName,String stage,String required);
	
	Set<Testplan> getFilteredTestplans(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required);
	
	Set<Testplan> getExistingTestplans(Long companyID,String relatedItem, String ID);

	Set<Testplan> getAvailableTestplans(Long companyID, String relatedItem, String iD);

}