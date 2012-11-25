/**
 * 
 */
package ie.cit.cloud.testcenter.service.requirement;

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
 * Peforms business operation on requirement 
 */
public interface RequirementService {

	void addNewRequirement(Requirement requirement);

	Requirement getRequirement(long requirementID);   

	Requirement getRequirementByName(String requirementName);

	void update(Requirement requirement);

	void remove(long requirementID); 
	//////////////////////////////////

	/**
	 * Returns a collection of All Testruns in a environment incl all child environments 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a environment incl all child environments,
	 */	
	Collection<Testrun> getAllTestRuns(long requirementID);
	/**
	 * Returns a collection of All compulsory Testruns in a environment incl all child environments 
	 * Collection<Testrun>
	 * @return collection of All compulsory Testruns in a environment incl all child environments,
	 */	
	Collection<Testrun> getCompulsoryTestRuns(long requirementID);
	/**
	 * Returns a collection of All Optional Testruns in a environment incl all child environments 
	 * Collection<Testrun>
	 * @return collection of All Optional Testruns in a environment incl all child environments,
	 */	
	Collection<Testrun> getOptionalTestRuns(long requirementID);
	/**
	 * Returns a collection of All Testcases in a environment incl all child environments 
	 * Collection<Testcase>
	 * @return collection of All Testcases in a environment incl all child environments,
	 */	
	Collection<Testcase> getAllTestCases(long requirementID);
	/**
	 * Returns a collection of All Compulsory Testcases in a environment incl all child environments 
	 * Collection<Testcase>
	 * @return collection of All Compulsory Testcases in a environment incl all child environments,
	 */	
	Collection<Testcase> getCompulsoryTestCases(long requirementID);
	/**
	 * Returns a collection of All Optional Testcases in a environment incl all child environments 
	 * Collection<Testcase>
	 * @return collection of All Optional Testcases in a environment incl all child environments,
	 */	
	Collection<Testcase> getOptionalTestCases(long requirementID);
	/**
	 * Returns a collection of All Testplans in a environment incl all child environments 
	 * Collection<Testplan>
	 * @return collection of All Testplans in a environment incl all child environments,
	 */	
	Collection<Testplan> getAllTestPlans(long requirementID);
	/**
	 * Returns a collection of All Compulsory Testplans in a environment incl all child environments 
	 * Collection<Testplan>
	 * @return collection of All Compulsory Testplans in a environment incl all child environments,
	 */	
	Collection<Testplan> getCompulsoryTestPlans(long requirementID);
	/**
	 * Returns a collection of All Optional Testplans in a environment incl all child environments 
	 * Collection<Testplan>
	 * @return collection of All Optional Testplans in a environment incl all child environments,
	 */	
	Collection<Testplan> getOptionalTestPlans(long requirementID);
	/**
	 * Returns a collection of Projects in a environment incl all child environments 
	 * Collection<Project>
	 * @return collection of Projects in a environment incl all child environments,
	 */		
	Collection<Project> getProjects(long requirementID) ;
	/**
	 * Returns a collection of Cycles in a environment incl all child environments 
	 * Collection<Cycle>
	 * @return collection of Cycles in a environment incl all child environments,
	 */		
	Collection<Cycle> getCycles(long requirementID);	
	/**
	 * Returns a collection of Environments in a environment incl all child environments 
	 * Collection<Environment>
	 * @return collection of Environments in a environment incl all child environments,
	 */		
	Collection<Environment> getEnvironments(long requirementID) ;
	/**
	 * Returns a collection of All Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of All Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedAllDefects(long requirementID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev1Defects(long requirementID);
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev2Defects(long requirementID);
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev3Defects(long requirementID);
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Collection<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	Collection<Defect> getCascadedSev4Defects(long requirementID);

	Collection<TestcenterUser> getCascadedTesters(long environmentID);
	Collection<TestcenterUser> getCascadedSnrTesters(long environmentID);
	Collection<TestcenterUser> getCascadedDevelopers(long environmentID);
	Collection<TestcenterUser> getCascadedSnrDevelopers(long environmentID);

}