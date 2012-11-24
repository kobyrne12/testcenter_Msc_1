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
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.NoResultException;

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

	boolean isLatest(long cycleID);
	//////////////////////////////////////////////

	/**
	 * Returns a Total collection of child Cycles including the parent
	 * Collection<Cycle>
	 * @return Total collection of child Cycles including the parent
	 */
	 Collection<Cycle> getParentAndChildCycles(long cycleID);
	/**
	 * Returns a collection of Child cycles for a cycle 
	 * Collection<Cycle>
	 * @return collection of Child cycles for a cycle
	 */	
	 Collection<Cycle> getChildCycles(long cycleID);
	/**
	 * Returns a Cycle Parent Cycle  
	 * Cycle
	 * @return a Cycle Parent Cycle, otherwise null
	 */	
	 Cycle getParentCycle(long cycleID);
	/**
	 * Returns a collection of All Testruns in a cycle incl all child cycles 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a cycle incl all child cycles,
	 */	
	 Collection<Testrun> getCascadedAllTestRuns(long cycleID);
	/**
	 * Returns a collection of All compulsory Testruns in a cycle incl all child cycles 
	 * Collection<Testrun>
	 * @return collection of All compulsory Testruns in a cycle incl all child cycles,
	 */	
	 Collection<Testrun> getCascadedCompulsoryTestRuns(long cycleID);
	/**
	 * Returns a collection of All Optional Testruns in a cycle incl all child cycles 
	 * Collection<Testrun>
	 * @return collection of All Optional Testruns in a cycle incl all child cycles,
	 */	
	 Collection<Testrun> getCascadedOptionalTestRuns(long cycleID);
	/**
	 * Returns a collection of All Testcases in a cycle incl all child cycles 
	 * Collection<Testcase>
	 * @return collection of All Testcases in a cycle incl all child cycles,
	 */	
	 Collection<Testcase> getCascadedAllTestCases(long cycleID);
	/**
	 * Returns a collection of All Compulsory Testcases in a cycle incl all child cycles 
	 * Collection<Testcase>
	 * @return collection of All Compulsory Testcases in a cycle incl all child cycles,
	 */	
	 Collection<Testcase> getCascadedCompulsoryTestCases(long cycleID);
	/**
	 * Returns a collection of All Optional Testcases in a cycle incl all child cycles 
	 * Collection<Testcase>
	 * @return collection of All Optional Testcases in a cycle incl all child cycles,
	 */	
	 Collection<Testcase> getCascadedOptionalTestCases(long cycleID);
	/**
	 * Returns a collection of All Testplans in a cycle incl all child cycles 
	 * Collection<Testplan>
	 * @return collection of All Testplans in a cycle incl all child cycles,
	 */	
	 Collection<Testplan> getCascadedAllTestPlans(long cycleID);
	/**
	 * Returns a collection of All Compulsory Testplans in a cycle incl all child cycles 
	 * Collection<Testplan>
	 * @return collection of All Compulsory Testplans in a cycle incl all child cycles,
	 */	
	 Collection<Testplan> getCascadedCompulsoryTestPlans(long cycleID);
	/**
	 * Returns a collection of All Optional Testplans in a cycle incl all child cycles 
	 * Collection<Testplan>
	 * @return collection of All Optional Testplans in a cycle incl all child cycles,
	 */	
	 Collection<Testplan> getCascadedOptionalTestPlans(long cycleID);
	/**
	 * Returns a collection of Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Defects in a cycle incl all child cycles,
	 */	
	 Collection<Defect> getCascadedDefects(long cycleID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	 Collection<Defect> getCascadedSev1Defects(long cycleID) ;
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	 Collection<Defect> getCascadedSev2Defects(long cycleID) ;
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	 Collection<Defect> getCascadedSev3Defects(long cycleID) ;
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	 Collection<Defect> getCascadedSev4Defects(long cycleID) ;
	/**
	 * Returns a collection of Environments in a cycle incl all child cycles 
	 * Collection<Environment>
	 * @return collection of Environments in a cycle incl all child cycles,
	 */		
	 Collection<Environment> getCascadedEnvironments(long cycleID) ;
	/**
	 * Returns a collection of Requirements in a cycle incl all child cycles 
	 * Collection<Requirement>
	 * @return collection of Requirements in a cycle incl all child cycles,
	 */		
	 Collection<Requirement> getCascadedRequirements(long cycleID) ;
	
	 Collection<TestcenterUser> getCascadedTesters(long cycleID);
	 Collection<TestcenterUser> getCascadedSnrTesters(long cycleID);
	 Collection<TestcenterUser> getCascadedDevelopers(long cycleID);
	 Collection<TestcenterUser> getCascadedSnrDevelopers(long cycleID);

	//////////////////////////////////////////////
	CycleSummaryList getGridCycles(long companyID,String projectID, String testplanID,
			String testcaseID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);

	ColModelAndNames getColumnModelAndNames(Long companyID);
	
	Collection<Cycle> getAllChildCycles(long cycleID);


}