/**
 * 
 */
package ie.cit.cloud.testcenter.service.testcase;

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
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testrun;

/**
 * Peforms business operation on Test case 
 */
public interface TestcaseService {
 
	void addNewTestcase(Testcase testcase);

    Testcase getTestcase(long testcaseID);   
    
    Testcase getTestcaseByName(String testcaseName);
    
    void update(Testcase testcase);
    
    void remove(long testcaseID);  
    
    long getLastTestRunID(long testcaseID);

	Set<Testcase> getAllTestcases();
	
	/**
	 * Returns true if any of a testcase testruns' priority is less than or equal to cycle priority 
	 * boolean
	 * @return true if any of a testcase testruns' priority is less than or equal to cycle priority,otherwise false
	 */	
	boolean isRequired(long testcaseID);	
	/////////////////////////	
	
	/**
	 * Returns a collection of All Testruns in a Testcase 
	 * Set<Testrun>
	 * @return collection of All Testruns in a Testcase,
	 */	
	Set<Testrun> getAllTestRuns(long testcaseID);
	/**
	 * Returns a collection of All compulsory Testruns in a Testcase
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a Testcase,
	 */	
	Set<Testrun> getCompulsoryTestRuns(long testcaseID);
	/**
	 * Returns a collection of All Optional Testruns in a Testcase
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a Testcase,
	 */	
	Set<Testrun> getOptionalTestRuns(long testcaseID);
	/**
	 * Returns a testcases Cycles
	 * Cycle
	 * @return a testcases Cycles
	 */		
	Set<Cycle> getCycles(long testcaseID);	
	/**
	 * Returns a testcases Projects
	 * Project
	 * @return a testcases Projects
	 */	
	Set<Project> getProjects(long testcaseID);	
	/**
	 * Returns a collection of Requirements in a Testcase
	 * Set<Requirement>
	 * @return collection of Requirements in a Testcase,
	 */		
	Set<Requirement> getRequirements(long testcaseID) ;		
	/**
	 * Returns a collection of Environments in a Testcase
	 * Set<Environment>
	 * @return collection of Environments in a Testcase,
	 */		
	Set<Environment> getEnvironments(long testcaseID) ;
	/**
	 * Returns a collection of All Defects in a Testcase 
	 * Set<Defect>
	 * @return collection of All Defects in a Testcase,
	 */		
	Set<Defect> getCascadedAllDefects(long testcaseID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a Testcase,
	 */		
	Set<Defect> getCascadedSev1Defects(long testcaseID);
	/**
	 * Returns a collection of Sev2 Defects in a Testcase 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a Testcase,
	 */		
	Set<Defect> getCascadedSev2Defects(long testcaseID);
	/**
	 * Returns a collection of Sev 3 Defects in a Testcase 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a Testcase,
	 */		
	Set<Defect> getCascadedSev3Defects(long testcaseID);
	/**
	 * Returns a collection of Sev 4 Defects in a Testcase 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a Testcase,
	 */		
	Set<Defect> getCascadedSev4Defects(long testcaseID);
	
	Set<TestcenterUser> getCascadedTesters(long testcaseID);
	Set<TestcenterUser> getCascadedSnrTesters(long testcaseID);
	Set<TestcenterUser> getCascadedDevelopers(long testcaseID);
	Set<TestcenterUser> getCascadedSnrDevelopers(long testcaseID);

}