/**
 * 
 */
package ie.cit.cloud.testcenter.service.testplan;

/**
 * @author byrnek1
 *
 */

import java.util.Set;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testrun;

/**
 * Peforms business operation on Test plan 
 */
public interface TestplanService {
 
	void addNewTestplan(Testplan testplan);

    Testplan getTestplan(long testplanID);   
    
    Testplan getTestplanByName(String testplanName);
    
    void update(Testplan testplan);
    
    void remove(long testplanID);  
    ///////////////////////////
    /**
	 * Returns a collection of All Testcases for a test plan
	 * Set<Testcase>
	 * @return collection of All Testcases for a test plan
	 */	
	 Set<Testcase> getAllTestCases(long testplanID);
	/**
	 * Returns a collection of All Compulsory Testcases for a test plan
	 * Set<Testplan>
	 * @return collection of All Compulsory Testcases for a test plan
	 */	
	 Set<Testcase> getCompulsoryTestCases(long testplanID);
	/**
	 * Returns a collection of All Optional Testcases for a test plan
	 * Set<Testcase>
	 * @return collection of All OptionalTestcases for a test plan
	 */	
	 Set<Testcase> getOptionalTestCases(long testplanID);
    /**
	 * Returns a collection of All Testruns in a Testplan 
	 * Set<Testrun>
	 * @return collection of All Testruns in a Testplan,
	 */	
	Set<Testrun> getAllTestRuns(long testplanID);
	/**
	 * Returns a collection of All compulsory Testruns in a Testplan
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a Testplan,
	 */	
	Set<Testrun> getCompulsoryTestRuns(long testplanID);
	/**
	 * Returns a collection of All Optional Testruns in a Testplan
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a Testplan,
	 */	
	Set<Testrun> getOptionalTestRuns(long testplanID);
	/**
	 * Returns true if any of a testcase testruns' in a tesplan priority is less than or equal to cycle priority 
	 * boolean
	 * @return true if any of a testcase testruns' in a tesplanpriority is less than or equal to cycle priority,otherwise false
	 */	
	boolean isRequired(long testcaseID);
	/**
	 * Returns a testplans Cycles
	 * Cycle
	 * @return a testplans Cycles
	 */		
	Set<Cycle> getCycles(long testplanID);	
	/**
	 * Returns a testplans Projects
	 * Project
	 * @return a testplans Projects
	 */	
	Set<Project> getProjects(long testplanID);	
	/**
	 * Returns a collection of Requirements in a Testplan
	 * Set<Requirement>
	 * @return collection of Requirements in a Testplan,
	 */		
	Set<Requirement> getRequirements(long testplanID) ;		
	/**
	 * Returns a collection of Environments in a Testplan
	 * Set<Environment>
	 * @return collection of Environments in a Testplan,
	 */		
	Set<Environment> getEnvironments(long testplanID) ;
	/**
	 * Returns a collection of All Defects in a Testplan 
	 * Set<Defect>
	 * @return collection of All Defects in a Testplan,
	 */		
	Set<Defect> getCascadedAllDefects(long testplanID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a Testplan,
	 */		
	Set<Defect> getCascadedSev1Defects(long testplanID);
	/**
	 * Returns a collection of Sev2 Defects in a Testplan 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a Testplan,
	 */		
	Set<Defect> getCascadedSev2Defects(long testplanID);
	/**
	 * Returns a collection of Sev 3 Defects in a Testplan 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a Testplan,
	 */		
	Set<Defect> getCascadedSev3Defects(long testplanID);
	/**
	 * Returns a collection of Sev 4 Defects in a Testplan 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a Testplan,
	 */		
	Set<Defect> getCascadedSev4Defects(long testplanID);
	
	Set<TestcenterUser> getCascadedTesters(long testplanID);
	Set<TestcenterUser> getCascadedSnrTesters(long testplanID);
	Set<TestcenterUser> getCascadedDevelopers(long testplanID);
	Set<TestcenterUser> getCascadedSnrDevelopers(long testplanID);

}