/**
 * 
 */
package ie.cit.cloud.testcenter.service.testrun;

/**
 * @author byrnek1
 *
 */

import java.util.Set;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.TestrunLevel;

/**
 * Peforms business operation on Test run 
 */
public interface TestrunService {

	void addNewTestrun(Testrun testrun);

	Testrun getTestrun(Long testrunID);   

	Testrun getTestrunByName(String testrunName);

	void update(Testrun testrun);

	void remove(Long testrunID);  

	boolean isLatest(Long testrunID);

	Set<Testrun> getTestHistory(Long testrunID);
	
	
	 
	void addNewTestrunLevel(TestrunLevel testrunLevel);
	TestrunLevel getTestrunLevel(Long testrunLevelID); 
	TestrunLevel getTestrunLevelByName(String testrunLevelName); 
	void updateTestrunLevel(TestrunLevel testrunLevel);
	void removeTestrunLevel(Long testrunLevelID);  

	/**
	 * Returns true if a testruns' priority is less than or equal to cycle priority 
	 * boolean
	 * @return true if a testruns' priority is less than or equal to cycle priority,otherwise false
	 */	
	boolean isRequired(Long testrunID);	
	//////////////////////////////////////////////
	/**
	 * Returns a testruns Cycle
	 * Cycle
	 * @return a testruns Cycle
	 */		
	Cycle getCycle(Long testrunID);
	/**
	 * Returns a testruns Testcase
	 * Testcase
	 * @return a testruns Testcase
	 */	
	Testcase getTestcase(Long testrunID);
	/**
	 * Returns a testruns Project
	 * Project
	 * @return a testruns Project
	 */	
	Project getProject(Long testrunID);
	/**
	 * Returns a collection of All Testplans in a testrun incl all child testruns 
	 * Set<Testplan>
	 * @return collection of All Testplans in a testrun incl all child testruns,
	 */		 
	Testplan getTestPlan(Long testrunID);	
	/**
	 * Returns a collection of All Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of All Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedAllDefects(Long testrunID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev1Defects(Long testrunID);
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev2Defects(Long testrunID);
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev3Defects(Long testrunID);
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev4Defects(Long testrunID);

	Set<TestcenterUser> getCascadedTesters(Long defectID);
	Set<TestcenterUser> getCascadedSnrTesters(Long defectID);
	Set<TestcenterUser> getCascadedDevelopers(Long defectID);
	Set<TestcenterUser> getCascadedSnrDevelopers(Long defectID);
	///////////////////////
}