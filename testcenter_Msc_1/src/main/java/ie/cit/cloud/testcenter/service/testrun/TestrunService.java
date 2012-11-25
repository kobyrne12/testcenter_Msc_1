/**
 * 
 */
package ie.cit.cloud.testcenter.service.testrun;

/**
 * @author byrnek1
 *
 */

import java.util.Collection;

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

/**
 * Peforms business operation on Test run 
 */
public interface TestrunService {

	void addNewTestrun(Testrun testrun);

	Testrun getTestrun(long testrunID);   

	Testrun getTestrunByName(String testrunName);

	void update(Testrun testrun);

	void remove(long testrunID);  

	boolean isLatest(long testrunID);

	Collection<Testrun> getTestHistory(long testrunID);

	/**
	 * Returns true if a testruns' priority is less than or equal to cycle priority 
	 * boolean
	 * @return true if a testruns' priority is less than or equal to cycle priority,otherwise false
	 */	
	boolean isRequired(long testrunID);	
	//////////////////////////////////////////////
	/**
	 * Returns a testruns Cycle
	 * Cycle
	 * @return a testruns Cycle
	 */		
	Cycle getCycle(long testrunID);
	/**
	 * Returns a testruns Testcase
	 * Testcase
	 * @return a testruns Testcase
	 */	
	Testcase getTestcase(long testrunID);
	/**
	 * Returns a testruns Project
	 * Project
	 * @return a testruns Project
	 */	
	Project getProject(long testrunID);
	/**
	 * Returns a collection of All Testplans in a testrun incl all child testruns 
	 * Collection<Testplan>
	 * @return collection of All Testplans in a testrun incl all child testruns,
	 */		 
	Testplan getTestPlan(long testrunID);	
	/**
	 * Returns a collection of All Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of All Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedAllDefects(long testrunID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev1Defects(long testrunID);
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev2Defects(long testrunID);
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev3Defects(long testrunID);
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev4Defects(long testrunID);

	Collection<TestcenterUser> getCascadedTesters(long defectID);
	Collection<TestcenterUser> getCascadedSnrTesters(long defectID);
	Collection<TestcenterUser> getCascadedDevelopers(long defectID);
	Collection<TestcenterUser> getCascadedSnrDevelopers(long defectID);
	///////////////////////
}