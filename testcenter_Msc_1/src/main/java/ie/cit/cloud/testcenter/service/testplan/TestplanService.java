/**
 * 
 */
package ie.cit.cloud.testcenter.service.testplan;

/**
 * @author byrnek1
 *
 */

import java.util.Collection;

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
	 * Collection<Testcase>
	 * @return collection of All Testcases for a test plan
	 */	
	 Collection<Testcase> getAllTestCases(long testplanID);
	/**
	 * Returns a collection of All Compulsory Testcases for a test plan
	 * Collection<Testplan>
	 * @return collection of All Compulsory Testcases for a test plan
	 */	
	 Collection<Testcase> getCompulsoryTestCases(long testplanID);
	/**
	 * Returns a collection of All Optional Testcases for a test plan
	 * Collection<Testcase>
	 * @return collection of All OptionalTestcases for a test plan
	 */	
	 Collection<Testcase> getOptionalTestCases(long testplanID);
    /**
	 * Returns a collection of All Testruns in a Testplan 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a Testplan,
	 */	
	Collection<Testrun> getAllTestRuns(long testplanID);
	/**
	 * Returns a collection of All compulsory Testruns in a Testplan
	 * Collection<Testrun>
	 * @return collection of All compulsory Testruns in a Testplan,
	 */	
	Collection<Testrun> getCompulsoryTestRuns(long testplanID);
	/**
	 * Returns a collection of All Optional Testruns in a Testplan
	 * Collection<Testrun>
	 * @return collection of All Optional Testruns in a Testplan,
	 */	
	Collection<Testrun> getOptionalTestRuns(long testplanID);
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
	Collection<Cycle> getCycles(long testplanID);	
	/**
	 * Returns a testplans Projects
	 * Project
	 * @return a testplans Projects
	 */	
	Collection<Project> getProjects(long testplanID);	
	/**
	 * Returns a collection of Requirements in a Testplan
	 * Collection<Requirement>
	 * @return collection of Requirements in a Testplan,
	 */		
	Collection<Requirement> getRequirements(long testplanID) ;		
	/**
	 * Returns a collection of Environments in a Testplan
	 * Collection<Environment>
	 * @return collection of Environments in a Testplan,
	 */		
	Collection<Environment> getEnvironments(long testplanID) ;
	/**
	 * Returns a collection of All Defects in a Testplan 
	 * Collection<Defect>
	 * @return collection of All Defects in a Testplan,
	 */		
	Collection<Defect> getCascadedAllDefects(long testplanID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle
	 * Collection<Defect>
	 * @return collection of Sev 1 Defects in a Testplan,
	 */		
	Collection<Defect> getCascadedSev1Defects(long testplanID);
	/**
	 * Returns a collection of Sev2 Defects in a Testplan 
	 * Collection<Defect>
	 * @return collection of Sev 2 Defects in a Testplan,
	 */		
	Collection<Defect> getCascadedSev2Defects(long testplanID);
	/**
	 * Returns a collection of Sev 3 Defects in a Testplan 
	 * Collection<Defect>
	 * @return collection of Sev 3 Defects in a Testplan,
	 */		
	Collection<Defect> getCascadedSev3Defects(long testplanID);
	/**
	 * Returns a collection of Sev 4 Defects in a Testplan 
	 * Collection<Defect>
	 * @return collection of Sev 4 Defects in a Testplan,
	 */		
	Collection<Defect> getCascadedSev4Defects(long testplanID);
	
	Collection<TestcenterUser> getCascadedTesters(long testplanID);
	Collection<TestcenterUser> getCascadedSnrTesters(long testplanID);
	Collection<TestcenterUser> getCascadedDevelopers(long testplanID);
	Collection<TestcenterUser> getCascadedSnrDevelopers(long testplanID);

}