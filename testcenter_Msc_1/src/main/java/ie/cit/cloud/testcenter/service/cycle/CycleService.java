/**
 * 
 */
package ie.cit.cloud.testcenter.service.cycle;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.RelatedObjectList;
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
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;


import java.util.ArrayList;
import java.util.Set;

import javax.persistence.NoResultException;

/**
 * Peforms business operation on cycle
 */
public interface CycleService {

    Set<Cycle> getAllCycles();

    long addNewCycle(Cycle cycle);

    Cycle getCycle(long cycleID);   
    
    Cycle getCycleByName(String cycleName);
    
    void update(Cycle cycle);
    
    void remove(long cycleID);
   
    boolean updateCycle(Cycle cycle);

	Set<Cycle> getAllCyclesByProjectID(long projectID);

	int getMaxProjectPosNum(long projectID);

	boolean isLatest(long cycleID);
	//////////////////////////////////////////////

	/**
	 * Returns a Total collection of child Cycles including the parent
	 * Set<Cycle>
	 * @return Total collection of child Cycles including the parent
	 */
	 Set<Cycle> getParentAndChildCycles(long cycleID);
	/**
	 * Returns a collection of Child cycles for a cycle 
	 * Set<Cycle>
	 * @return collection of Child cycles for a cycle
	 */	
	 Set<Cycle> getChildCycles(long cycleID);
	/**
	 * Returns a Cycle Parent Cycle  
	 * Cycle
	 * @return a Cycle Parent Cycle, otherwise null
	 */	
	 Cycle getParentCycle(long cycleID);
	/**
	 * Returns a collection of All Testruns in a cycle incl all child cycles 
	 * Set<Testrun>
	 * @return collection of All Testruns in a cycle incl all child cycles,
	 */	
	 Set<Testrun> getCascadedAllTestRuns(long cycleID);
	 /**
	  * Returns a collection of All Testruns in a cycle incl all child cycles 
	  * Set<Testrun>
	  * @return collection of All Testruns in a cycle incl all child cycles,
	  */	
	 Set<Testrun> getCascadedAllTestRuns(long cycleID, String level);
	/**
	 * Returns a collection of All compulsory Testruns in a cycle incl all child cycles 
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a cycle incl all child cycles,
	 */	
	 Set<Testrun> getCascadedCompulsoryTestRuns(long cycleID);
	 /**
	  * Returns a collection of All compulsory Testruns in a cycle incl all child cycles 
	  * Set<Testrun>
	  * @return collection of All compulsory Testruns in a cycle incl all child cycles,
	  */	
	 Set<Testrun> getCascadedCompulsoryTestRuns(long cycleID, String level);
	/**
	 * Returns a collection of All Optional Testruns in a cycle incl all child cycles 
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a cycle incl all child cycles,
	 */	
	 Set<Testrun> getCascadedOptionalTestRuns(long cycleID);
	 /**
	  * Returns a collection of All Optional Testruns in a cycle incl all child cycles 
	  * Set<Testrun>
	  * @return collection of All Optional Testruns in a cycle incl all child cycles,
	  */	
	 Set<Testrun> getCascadedOptionalTestRuns(long cycleID, String level);
	/**
	 * Returns a collection of All Testcases in a cycle incl all child cycles 
	 * Set<Testcase>
	 * @return collection of All Testcases in a cycle incl all child cycles,
	 */	
	 Set<Testcase> getCascadedAllTestCases(long cycleID);
	/**
	 * Returns a collection of All Compulsory Testcases in a cycle incl all child cycles 
	 * Set<Testcase>
	 * @return collection of All Compulsory Testcases in a cycle incl all child cycles,
	 */	
	 Set<Testcase> getCascadedCompulsoryTestCases(long cycleID);
	/**
	 * Returns a collection of All Optional Testcases in a cycle incl all child cycles 
	 * Set<Testcase>
	 * @return collection of All Optional Testcases in a cycle incl all child cycles,
	 */	
	 Set<Testcase> getCascadedOptionalTestCases(long cycleID);
	/**
	 * Returns a collection of All Testplans in a cycle incl all child cycles 
	 * Set<Testplan>
	 * @return collection of All Testplans in a cycle incl all child cycles,
	 */	
	 Set<Testplan> getCascadedAllTestPlans(long cycleID);
	/**
	 * Returns a collection of All Compulsory Testplans in a cycle incl all child cycles 
	 * Set<Testplan>
	 * @return collection of All Compulsory Testplans in a cycle incl all child cycles,
	 */	
	 Set<Testplan> getCascadedCompulsoryTestPlans(long cycleID);
	/**
	 * Returns a collection of All Optional Testplans in a cycle incl all child cycles 
	 * Set<Testplan>
	 * @return collection of All Optional Testplans in a cycle incl all child cycles,
	 */	
	 Set<Testplan> getCascadedOptionalTestPlans(long cycleID);
	/**
	 * Returns a collection of Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Defects in a cycle incl all child cycles,
	 */	
	 Set<Defect> getCascadedDefects(long cycleID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	 Set<Defect> getCascadedSev1Defects(long cycleID) ;
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	 Set<Defect> getCascadedSev2Defects(long cycleID) ;
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	 Set<Defect> getCascadedSev3Defects(long cycleID) ;
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	 Set<Defect> getCascadedSev4Defects(long cycleID) ;
	/**
	 * Returns a collection of Environments in a cycle incl all child cycles 
	 * Set<Environment>
	 * @return collection of Environments in a cycle incl all child cycles,
	 */		
	 Set<Environment> getCascadedEnvironments(long cycleID) ;
	/**
	 * Returns a collection of Requirements in a cycle incl all child cycles 
	 * Set<Requirement>
	 * @return collection of Requirements in a cycle incl all child cycles,
	 */		
	 Set<Requirement> getCascadedRequirements(long cycleID) ;
	
	 Set<TestcenterUser> getCascadedTesters(long cycleID);
	 Set<TestcenterUser> getCascadedSnrTesters(long cycleID);
	 Set<TestcenterUser> getCascadedDevelopers(long cycleID);
	 Set<TestcenterUser> getCascadedSnrDevelopers(long cycleID);

	//////////////////////////////////////////////
	 
	Set<Cycle> getAllChildCycles(long cycleID);
	 
	//CycleSummary getCycleSummary(long companyID, long cycleID, String level);
	 
	CycleSummaryList getGridCycles(long companyID, String projectID, String cycleID,
			String testplanID,String testcaseID, String testrunID, String defectID, 
			String requirementID, String environmentID, String userID, String level );
	
	ColModelAndNames getColumnModelAndNames(Long companyID);	
	
	RelatedObjectList getRelatedObjects(long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);


}