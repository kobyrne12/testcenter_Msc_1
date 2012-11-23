/**
 * 
 */
package ie.cit.cloud.testcenter.service.cycle;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;


import java.util.Collection;

/**
 * Peforms business operation on cycle
 */
public interface CycleService {

    Collection<Cycle> getAllCycles();

    long addNewCycle(Cycle cycle);

    Cycle getCycle(long cycleID);   
    
    Cycle getCycleByName(String cycleName);
    
    void update(Cycle cycle);
    
    void remove(long cycleID);
   
    boolean updateCycle(Cycle cycle);

	Collection<Cycle> getAllCyclesByProjectID(long projectID);
	
	CycleSummary getCycleSummary(long cycleID);

	int getMaxProjectPosNum(long projectID);
	//////////////////////////////////////////////

	/**
	 * Returns a collection of ChildCycles for a Cycles 
	 * Collection<Cycle>
	 * @return collection of ChildCycles for a Cycles
	 */
	Collection<Cycle> getChildCycles(long cycleID);  
	/**
	 * Returns a Cycles Parent Cycle  
	 * Cycle
	 * @return a Cycles Parent Cycle, otherwise null
	 */	
	Cycle getParentCycle(long cycleID);  	
	/**
	 * Returns total Number of All Test Runs for a Cycle
	 * int
	 * @return total Number of All Test Runs for a Cycle
	 */	
	int getCascadedAllTestRunsCount(long cycleID);  
	/**
	 * Returns a collection of All Testruns in a Cycle incl all child Cycle cycles 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Testrun> getCascadedAllTestRuns(long cycleID);  
	/**
	 * Returns total Number of All Required Testruns for a Cycle
	 * int
	 * @return total Number of All Required Testruns for a Cycle
	 */		
	int getCascadedRequiredTestRunsCount(long cycleID);  
	/**
	 * Returns a collection of Required Testruns in a Cycle incl all child Cycle cycles 
	 * Collection<Testrun>
	 * @return collection of Required Testruns in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Testrun> getCascadedRequiredTestRuns(long cycleID);  
	/**
	 * Returns a collection of Testplans in a Cycle incl all child Cycle cycles 
	 * Collection<Tesplan>
	 * @return collection of Testplans in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Testplan> getCascadedTestPlans(long cycleID);  
	/**
	 * Returns a collection of Testcases in a Cycle incl all child Cycle cycles 
	 * Collection<Testcase>
	 * @return collection of Testcases in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Testcase> getCascadedTestCases(long cycleID);  
	/**
	 * Returns a collection of Defects in a Cycle incl all child Cycle cycles 
	 * Collection<Defect>
	 * @return collection of Defects in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Defect> getCascadedDefects(long cycleID);  
	/**
	 * Returns a collection of Sev 1 Defects in a Cycle incl all child Cycle cycles 
	 * Collection<Defect>
	 * @return collection of  Sev 1 Defects in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Defect> getCascadedSev1Defects(long cycleID);  
	/**
	 * Returns a collection of Sev 2 Defects in a Cycle incl all child Cycle cycles 
	 * Collection<Defect>
	 * @return collection of  Sev 2 Defects in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Defect> getCascadedSev2Defects(long cycleID);  
	/**
	 * Returns a collection of Sev 3 Defects in a Cycle incl all child Cycle cycles 
	 * Collection<Defect>
	 * @return collection of  Sev 3 Defects in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Defect> getCascadedSev3Defects(long cycleID);  
	/**
	 * Returns a collection of Sev 4 Defects in a Cycle incl all child Cycle cycles 
	 * Collection<Defect>
	 * @return collection of  Sev 4 Defects in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Defect> getCascadedSev4Defects(long cycleID);  
	/**
	 * Returns a collection of Environments in a Cycle incl all child Cycle cycles 
	 * Collection<Environment>
	 * @return collection of Environments in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Environment> getCascadedEnvironments(long cycleID);  
	/**
	 * Returns a collection of Requirements in a Cycle incl all child Cycle cycles 
	 * Collection<Requirement>
	 * @return collection of Requirements in a Cycle incl all child Cycle cycles,
	 */	
	Collection<Environment> getCascadedRequirements(long cycleID);  
	/**
	 * Returns a collection of Testers in a Cycle incl all child Cycle cycles 
	 * Collection<User>
	 * @return collection of Testers in a Cycle incl all child Cycle cycles,
	 */	
	Collection<TestcenterUser> getCascadedTesters(long cycleID);  
	/**
	 * Returns a collection of Senior Testers in a Cycle incl all child Cycle cycles 
	 * Collection<User>
	 * @return collection of Senior Testers in a Cycle incl all child Cycle cycles,
	 */	
	Collection<TestcenterUser> getCascadedSnrTesters(long cycleID);  
	/**
	 * Returns a collection of Developers in a Cycle incl all child Cycle cycles 
	 * Collection<User>
	 * @return collection of Developers in a Cycle incl all child Cycle cycles,
	 */	
	Collection<TestcenterUser> getCascadedDevelopers(long cycleID);  
	/**
	 * Returns a collection of Senior Developers in a Cycle incl all child Cycle cycles 
	 * Collection<User>
	 * @return collection of Senior Developers in a Cycle incl all child Cycle cycles,
	 */	
	Collection<TestcenterUser> getCascadedSnrDevelopers(long cycleID);  
	/**
	 * Returns a collection of Code impact Rules in a Cycle incl all child Cycle cycles 
	 * Collection<User>
	 * @return collection of Code impact Rules in a Cycle incl all child Cycle cycles,
	 */	
	Collection<TestcenterUser> getCascadedCodeImpactRules(long cycleID);  
	/**
	 * Returns a collection of Code Requirement Rules in a Cycle incl all child Cycle cycles 
	 * Collection<User>
	 * @return collection of Code Requirement Rules in a Cycle incl all child Cycle cycles,
	 */	
	Collection<TestcenterUser> getCascadedRequirementRules(long cycleID);  
	/**
	 * Returns a collection of Test History Rules in a Cycle incl all child Cycle cycles 
	 * Collection<User>
	 * @return collection of Test History Rules in a Cycle incl all child Cycle cycles,
	 */	
	Collection<TestcenterUser> getCascadedTestHistoryRules(long cycleID);  
	/**
	 * Returns a collection of Defect Rules in a Cycle incl all child Cycle cycles 
	 * Collection<User>
	 * @return collection of Defect Rules in a Cycle incl all child Cycle cycles,
	 * 
	 */	
	Collection<TestcenterUser> getCascadedDefectRules(long cycleID);  
	
	//////////////////////////////////////////////
	CycleSummaryList getGridCycles(long companyID,String projectID, String testplanID,
			String testcaseID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);

	ColModelAndNames getColumnModelAndNames(Long companyID);
	
	Collection<Cycle> getAllChildCycles(long cycleID);

	boolean isLatest(long cycleID);

}