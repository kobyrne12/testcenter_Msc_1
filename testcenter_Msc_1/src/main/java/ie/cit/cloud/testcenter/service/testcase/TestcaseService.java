/**
 * 
 */
package ie.cit.cloud.testcenter.service.testcase;

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

	Collection<Testcase> getAllTestcases();
	
	/**
	 * Returns true if any of a testcase testruns' priority is less than or equal to cycle priority 
	 * boolean
	 * @return true if any of a testcase testruns' priority is less than or equal to cycle priority,otherwise false
	 */	
	boolean isRequired(long testcaseID);	
	/////////////////////////	
	
	/**
	 * Returns a collection of All Testruns in a Testcase 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a Testcase,
	 */	
	Collection<Testrun> getAllTestRuns(long testcaseID);
	/**
	 * Returns a collection of All compulsory Testruns in a Testcase
	 * Collection<Testrun>
	 * @return collection of All compulsory Testruns in a Testcase,
	 */	
	Collection<Testrun> getCompulsoryTestRuns(long testcaseID);
	/**
	 * Returns a collection of All Optional Testruns in a Testcase
	 * Collection<Testrun>
	 * @return collection of All Optional Testruns in a Testcase,
	 */	
	Collection<Testrun> getOptionalTestRuns(long testcaseID);
	/**
	 * Returns a testcases Cycles
	 * Cycle
	 * @return a testcases Cycles
	 */		
	Collection<Cycle> getCycles(long testcaseID);	
	/**
	 * Returns a testcases Projects
	 * Project
	 * @return a testcases Projects
	 */	
	Collection<Project> getProjects(long testcaseID);	
	/**
	 * Returns a collection of Requirements in a Testcase
	 * Collection<Requirement>
	 * @return collection of Requirements in a Testcase,
	 */		
	Collection<Requirement> getRequirements(long testcaseID) ;		
	/**
	 * Returns a collection of Environments in a Testcase
	 * Collection<Environment>
	 * @return collection of Environments in a Testcase,
	 */		
	Collection<Environment> getEnvironments(long testcaseID) ;
	/**
	 * Returns a collection of All Defects in a Testcase 
	 * Collection<Defect>
	 * @return collection of All Defects in a Testcase,
	 */		
	Collection<Defect> getCascadedAllDefects(long testcaseID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle
	 * Collection<Defect>
	 * @return collection of Sev 1 Defects in a Testcase,
	 */		
	Collection<Defect> getCascadedSev1Defects(long testcaseID);
	/**
	 * Returns a collection of Sev2 Defects in a Testcase 
	 * Collection<Defect>
	 * @return collection of Sev 2 Defects in a Testcase,
	 */		
	Collection<Defect> getCascadedSev2Defects(long testcaseID);
	/**
	 * Returns a collection of Sev 3 Defects in a Testcase 
	 * Collection<Defect>
	 * @return collection of Sev 3 Defects in a Testcase,
	 */		
	Collection<Defect> getCascadedSev3Defects(long testcaseID);
	/**
	 * Returns a collection of Sev 4 Defects in a Testcase 
	 * Collection<Defect>
	 * @return collection of Sev 4 Defects in a Testcase,
	 */		
	Collection<Defect> getCascadedSev4Defects(long testcaseID);
	
	Collection<TestcenterUser> getCascadedTesters(long testcaseID);
	Collection<TestcenterUser> getCascadedSnrTesters(long testcaseID);
	Collection<TestcenterUser> getCascadedDevelopers(long testcaseID);
	Collection<TestcenterUser> getCascadedSnrDevelopers(long testcaseID);

}