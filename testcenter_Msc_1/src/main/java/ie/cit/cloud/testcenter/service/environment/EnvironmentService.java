/**
 * 
 */
package ie.cit.cloud.testcenter.service.environment;

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
	 * Set<Testrun>
	 * @return collection of All Testruns in a environment incl all child environments,
	 */	
	Set<Testrun> getAllTestRuns(long environmentID);
	/**
	 * Returns a collection of All compulsory Testruns in a environment incl all child environments 
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a environment incl all child environments,
	 */	
	Set<Testrun> getCompulsoryTestRuns(long environmentID);
	/**
	 * Returns a collection of All Optional Testruns in a environment incl all child environments 
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a environment incl all child environments,
	 */	
	Set<Testrun> getOptionalTestRuns(long environmentID);
	/**
	 * Returns a collection of All Testcases in a environment incl all child environments 
	 * Set<Testcase>
	 * @return collection of All Testcases in a environment incl all child environments,
	 */	
	Set<Testcase> getAllTestCases(long environmentID);
	/**
	 * Returns a collection of All Compulsory Testcases in a environment incl all child environments 
	 * Set<Testcase>
	 * @return collection of All Compulsory Testcases in a environment incl all child environments,
	 */	
	Set<Testcase> getCompulsoryTestCases(long environmentID);
	/**
	 * Returns a collection of All Optional Testcases in a environment incl all child environments 
	 * Set<Testcase>
	 * @return collection of All Optional Testcases in a environment incl all child environments,
	 */	
	Set<Testcase> getOptionalTestCases(long environmentID);
	/**
	 * Returns a collection of All Testplans in a environment incl all child environments 
	 * Set<Testplan>
	 * @return collection of All Testplans in a environment incl all child environments,
	 */	
	Set<Testplan> getAllTestPlans(long environmentID);
	/**
	 * Returns a collection of All Compulsory Testplans in a environment incl all child environments 
	 * Set<Testplan>
	 * @return collection of All Compulsory Testplans in a environment incl all child environments,
	 */	
	Set<Testplan> getCompulsoryTestPlans(long environmentID);
	/**
	 * Returns a collection of All Optional Testplans in a environment incl all child environments 
	 * Set<Testplan>
	 * @return collection of All Optional Testplans in a environment incl all child environments,
	 */	
	Set<Testplan> getOptionalTestPlans(long environmentID);
	/**
	 * Returns a collection of Projects in a environment incl all child environments 
	 * Set<Project>
	 * @return collection of Projects in a environment incl all child environments,
	 */		
	Set<Project> getProjects(long environmentID) ;
	/**
	 * Returns a collection of Cycles in a environment incl all child environments 
	 * Set<Cycle>
	 * @return collection of Cycles in a environment incl all child environments,
	 */		
	Set<Cycle> getCycles(long environmentID);	
	/**
	 * Returns a collection of Requirements in a environment incl all child environments 
	 * Set<Requirement>
	 * @return collection of Requirements in a environment incl all child environments,
	 */		
	Set<Requirement> getRequirements(long environmentID) ;
	/**
	 * Returns a collection of All Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of All Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedAllDefects(long environmentID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev1Defects(long environmentID);
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev2Defects(long environmentID);
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev3Defects(long environmentID);
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev4Defects(long environmentID);

	Set<TestcenterUser> getCascadedTesters(long environmentID);
	Set<TestcenterUser> getCascadedSnrTesters(long environmentID);
	Set<TestcenterUser> getCascadedDevelopers(long environmentID);
	Set<TestcenterUser> getCascadedSnrDevelopers(long environmentID);

}