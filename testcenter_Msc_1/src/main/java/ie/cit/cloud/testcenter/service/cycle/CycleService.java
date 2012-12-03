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

    Long addNewCycle(Cycle cycle);

    Cycle getCycle(Long cycleID);   
    
    Cycle getCycleByName(String cycleName);
    
    void update(Cycle cycle);
    
    void remove(Long cycleID);
   
    boolean updateCycle(Cycle cycle);

	Set<Cycle> getAllCyclesByProjectID(Long projectID);

	int getMaxProjectPosNum(Long projectID);

	boolean isLatest(Long cycleID);
	//////////////////////////////////////////////

	/**
	 * Returns a Total collection of child Cycles including the parent
	 * Set<Cycle>
	 * @return Total collection of child Cycles including the parent
	 */
	 Set<Cycle> getParentAndChildCycles(Long cycleID);
	/**
	 * Returns a collection of Child cycles for a cycle 
	 * Set<Cycle>
	 * @return collection of Child cycles for a cycle
	 */	
	 Set<Cycle> getChildCycles(Long cycleID);
	/**
	 * Returns a Cycle Parent Cycle  
	 * Cycle
	 * @return a Cycle Parent Cycle, otherwise null
	 */	
	 Cycle getParentCycle(Long cycleID);
	/**
	 * Returns a collection of All Testruns in a cycle incl all child cycles 
	 * Set<Testrun>
	 * @return collection of All Testruns in a cycle incl all child cycles,
	 */	
	 Set<Testrun> getCascadedAllTestRuns(Long cycleID);
	 /**
	  * Returns a collection of All Testruns in a cycle incl all child cycles 
	  * Set<Testrun>
	  * @return collection of All Testruns in a cycle incl all child cycles,
	  */	
	 Set<Testrun> getCascadedAllTestRuns(Long cycleID, String level);
	/**
	 * Returns a collection of All compulsory Testruns in a cycle incl all child cycles 
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a cycle incl all child cycles,
	 */	
	 Set<Testrun> getCascadedCompulsoryTestRuns(Long cycleID);
	 /**
	  * Returns a collection of All compulsory Testruns in a cycle incl all child cycles 
	  * Set<Testrun>
	  * @return collection of All compulsory Testruns in a cycle incl all child cycles,
	  */	
	 Set<Testrun> getCascadedCompulsoryTestRuns(Long cycleID, String level);
	/**
	 * Returns a collection of All Optional Testruns in a cycle incl all child cycles 
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a cycle incl all child cycles,
	 */	
	 Set<Testrun> getCascadedOptionalTestRuns(Long cycleID);
	 /**
	  * Returns a collection of All Optional Testruns in a cycle incl all child cycles 
	  * Set<Testrun>
	  * @return collection of All Optional Testruns in a cycle incl all child cycles,
	  */	
	 Set<Testrun> getCascadedOptionalTestRuns(Long cycleID, String level);
	/**
	 * Returns a collection of All Testcases in a cycle incl all child cycles 
	 * Set<Testcase>
	 * @return collection of All Testcases in a cycle incl all child cycles,
	 */	
	 Set<Testcase> getCascadedAllTestCases(Long cycleID);
	/**
	 * Returns a collection of All Compulsory Testcases in a cycle incl all child cycles 
	 * Set<Testcase>
	 * @return collection of All Compulsory Testcases in a cycle incl all child cycles,
	 */	
	 Set<Testcase> getCascadedCompulsoryTestCases(Long cycleID);
	/**
	 * Returns a collection of All Optional Testcases in a cycle incl all child cycles 
	 * Set<Testcase>
	 * @return collection of All Optional Testcases in a cycle incl all child cycles,
	 */	
	 Set<Testcase> getCascadedOptionalTestCases(Long cycleID);
	/**
	 * Returns a collection of All Testplans in a cycle incl all child cycles 
	 * Set<Testplan>
	 * @return collection of All Testplans in a cycle incl all child cycles,
	 */	
	 Set<Testplan> getCascadedAllTestPlans(Long cycleID);
	/**
	 * Returns a collection of All Compulsory Testplans in a cycle incl all child cycles 
	 * Set<Testplan>
	 * @return collection of All Compulsory Testplans in a cycle incl all child cycles,
	 */	
	 Set<Testplan> getCascadedCompulsoryTestPlans(Long cycleID);
	/**
	 * Returns a collection of All Optional Testplans in a cycle incl all child cycles 
	 * Set<Testplan>
	 * @return collection of All Optional Testplans in a cycle incl all child cycles,
	 */	
	 Set<Testplan> getCascadedOptionalTestPlans(Long cycleID);
	/**
	 * Returns a collection of Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Defects in a cycle incl all child cycles,
	 */	
	 Set<Defect> getCascadedDefects(Long cycleID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	 Set<Defect> getCascadedSev1Defects(Long cycleID) ;
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	 Set<Defect> getCascadedSev2Defects(Long cycleID) ;
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	 Set<Defect> getCascadedSev3Defects(Long cycleID) ;
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	 Set<Defect> getCascadedSev4Defects(Long cycleID) ;
	/**
	 * Returns a collection of Environments in a cycle incl all child cycles 
	 * Set<Environment>
	 * @return collection of Environments in a cycle incl all child cycles,
	 */		
	 Set<Environment> getCascadedEnvironments(Long cycleID) ;
	/**
	 * Returns a collection of Requirements in a cycle incl all child cycles 
	 * Set<Requirement>
	 * @return collection of Requirements in a cycle incl all child cycles,
	 */		
	 Set<Requirement> getCascadedRequirements(Long cycleID) ;
	
	 Set<TestcenterUser> getCascadedTesters(Long cycleID);
	 Set<TestcenterUser> getCascadedSnrTesters(Long cycleID);
	 Set<TestcenterUser> getCascadedDevelopers(Long cycleID);
	 Set<TestcenterUser> getCascadedSnrDevelopers(Long cycleID);

	//////////////////////////////////////////////
	 
	Set<Cycle> getAllChildCycles(Long cycleID);
	 
	//CycleSummary getCycleSummary(Long companyID, Long cycleID, String level);
	 
	CycleSummaryList getGridCycles(Long companyID, String projectID, String cycleID,
			String testplanID,String testcaseID, String testrunID, String defectID, 
			String requirementID, String environmentID, String userID, String level );
	
	ColModelAndNames getColumnModelAndNames(Long companyID);	
	
	RelatedObjectList getRelatedObjects(Long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);


}