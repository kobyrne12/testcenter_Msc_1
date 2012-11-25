/**
 * 
 */
package ie.cit.cloud.testcenter.service.environment;

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
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;

/**
 * Peforms business operation on environment 
 */
public interface EnvironmentService {

	void addNewEnvironment(Environment environment);

	Environment getEnvironment(long environmentID);   

	Environment getEnvironmentByName(String environmentName);

	void update(Environment environment);

	void remove(long environmentID);  

	//////////////////////////////////
	
	/**
	 * Returns a collection of All Testruns in a environment incl all child environments 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a environment incl all child environments,
	 */	
	Collection<Testrun> getAllTestRuns(long environmentID);
	/**
	 * Returns a collection of All compulsory Testruns in a environment incl all child environments 
	 * Collection<Testrun>
	 * @return collection of All compulsory Testruns in a environment incl all child environments,
	 */	
	Collection<Testrun> getCompulsoryTestRuns(long environmentID);
	/**
	 * Returns a collection of All Optional Testruns in a environment incl all child environments 
	 * Collection<Testrun>
	 * @return collection of All Optional Testruns in a environment incl all child environments,
	 */	
	Collection<Testrun> getOptionalTestRuns(long environmentID);
	/**
	 * Returns a collection of All Testcases in a environment incl all child environments 
	 * Collection<Testcase>
	 * @return collection of All Testcases in a environment incl all child environments,
	 */	
	Collection<Testcase> getAllTestCases(long environmentID);
	/**
	 * Returns a collection of All Compulsory Testcases in a environment incl all child environments 
	 * Collection<Testcase>
	 * @return collection of All Compulsory Testcases in a environment incl all child environments,
	 */	
	Collection<Testcase> getCompulsoryTestCases(long environmentID);
	/**
	 * Returns a collection of All Optional Testcases in a environment incl all child environments 
	 * Collection<Testcase>
	 * @return collection of All Optional Testcases in a environment incl all child environments,
	 */	
	Collection<Testcase> getOptionalTestCases(long environmentID);
	/**
	 * Returns a collection of All Testplans in a environment incl all child environments 
	 * Collection<Testplan>
	 * @return collection of All Testplans in a environment incl all child environments,
	 */	
	Collection<Testplan> getAllTestPlans(long environmentID);
	/**
	 * Returns a collection of All Compulsory Testplans in a environment incl all child environments 
	 * Collection<Testplan>
	 * @return collection of All Compulsory Testplans in a environment incl all child environments,
	 */	
	Collection<Testplan> getCompulsoryTestPlans(long environmentID);
	/**
	 * Returns a collection of All Optional Testplans in a environment incl all child environments 
	 * Collection<Testplan>
	 * @return collection of All Optional Testplans in a environment incl all child environments,
	 */	
	Collection<Testplan> getOptionalTestPlans(long environmentID);
	/**
	 * Returns a collection of Projects in a environment incl all child environments 
	 * Collection<Project>
	 * @return collection of Projects in a environment incl all child environments,
	 */		
	Collection<Project> getProjects(long environmentID) ;
	/**
	 * Returns a collection of Cycles in a environment incl all child environments 
	 * Collection<Cycle>
	 * @return collection of Cycles in a environment incl all child environments,
	 */		
	Collection<Cycle> getCycles(long environmentID);	
	/**
	 * Returns a collection of Requirements in a environment incl all child environments 
	 * Collection<Requirement>
	 * @return collection of Requirements in a environment incl all child environments,
	 */		
	Collection<Requirement> getRequirements(long environmentID) ;
	/**
	 * Returns a collection of All Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of All Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedAllDefects(long environmentID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev1Defects(long environmentID);
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev2Defects(long environmentID);
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev3Defects(long environmentID);
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev4Defects(long environmentID);

	Collection<TestcenterUser> getCascadedTesters(long environmentID);
	Collection<TestcenterUser> getCascadedSnrTesters(long environmentID);
	Collection<TestcenterUser> getCascadedDevelopers(long environmentID);
	Collection<TestcenterUser> getCascadedSnrDevelopers(long environmentID);

}