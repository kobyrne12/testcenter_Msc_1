/**
 * 
 */
package ie.cit.cloud.testcenter.service.requirement;

/**
 * @author byrnek1
 *
 */

import java.util.Set;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.RequirementSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestrunSummaryList;

/**
 * Peforms business operation on requirement 
 */
public interface RequirementService {

	void addNewRequirement(Requirement requirement);

	Requirement getRequirement(Long requirementID);   

	Requirement getRequirementByName(String requirementName);

	void update(Requirement requirement);

	void remove(Long requirementID); 
	//////////////////////////////////

	/**
	 * Returns a collection of All Testruns in a environment incl all child environments 
	 * Set<Testrun>
	 * @return collection of All Testruns in a environment incl all child environments,
	 */	
	Set<Testrun> getAllTestRuns(Long requirementID);
	/**
	 * Returns a collection of All compulsory Testruns in a environment incl all child environments 
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a environment incl all child environments,
	 */	
	Set<Testrun> getCompulsoryTestRuns(Long requirementID);
	/**
	 * Returns a collection of All Optional Testruns in a environment incl all child environments 
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a environment incl all child environments,
	 */	
	Set<Testrun> getOptionalTestRuns(Long requirementID);
	/**
	 * Returns a collection of All Testcases in a environment incl all child environments 
	 * Set<Testcase>
	 * @return collection of All Testcases in a environment incl all child environments,
	 */	
	Set<Testcase> getAllTestCases(Long requirementID);
	/**
	 * Returns a collection of All Compulsory Testcases in a environment incl all child environments 
	 * Set<Testcase>
	 * @return collection of All Compulsory Testcases in a environment incl all child environments,
	 */	
	Set<Testcase> getCompulsoryTestCases(Long requirementID);
	/**
	 * Returns a collection of All Optional Testcases in a environment incl all child environments 
	 * Set<Testcase>
	 * @return collection of All Optional Testcases in a environment incl all child environments,
	 */	
	Set<Testcase> getOptionalTestCases(Long requirementID);
	/**
	 * Returns a collection of All Testplans in a environment incl all child environments 
	 * Set<Testplan>
	 * @return collection of All Testplans in a environment incl all child environments,
	 */	
	Set<Testplan> getAllTestPlans(Long requirementID);
	/**
	 * Returns a collection of All Compulsory Testplans in a environment incl all child environments 
	 * Set<Testplan>
	 * @return collection of All Compulsory Testplans in a environment incl all child environments,
	 */	
	Set<Testplan> getCompulsoryTestPlans(Long requirementID);
	/**
	 * Returns a collection of All Optional Testplans in a environment incl all child environments 
	 * Set<Testplan>
	 * @return collection of All Optional Testplans in a environment incl all child environments,
	 */	
	Set<Testplan> getOptionalTestPlans(Long requirementID);
	/**
	 * Returns a collection of Projects in a environment incl all child environments 
	 * Set<Project>
	 * @return collection of Projects in a environment incl all child environments,
	 */		
	Set<Project> getProjects(Long requirementID) ;
	/**
	 * Returns a collection of Cycles in a environment incl all child environments 
	 * Set<Cycle>
	 * @return collection of Cycles in a environment incl all child environments,
	 */		
	Set<Cycle> getCycles(Long requirementID);	
	/**
	 * Returns a collection of Environments in a environment incl all child environments 
	 * Set<Environment>
	 * @return collection of Environments in a environment incl all child environments,
	 */		
	Set<Environment> getEnvironments(Long requirementID) ;
	/**
	 * Returns a collection of All Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of All Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedAllDefects(Long requirementID);
	/**
	 * Returns a collection of Sev1 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 1 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev1Defects(Long requirementID);
	/**
	 * Returns a collection of Sev2 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 2 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev2Defects(Long requirementID);
	/**
	 * Returns a collection of Sev 3 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 3 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev3Defects(Long requirementID);
	/**
	 * Returns a collection of Sev 4 Defects in a cycle incl all child cycles 
	 * Set<Defect>
	 * @return collection of Sev 4 Defects in a cycle incl all child cycles,
	 */		
	Set<Defect> getCascadedSev4Defects(Long requirementID);

	Set<TestcenterUser> getCascadedTesters(Long environmentID);
	Set<TestcenterUser> getCascadedSnrTesters(Long environmentID);
	Set<TestcenterUser> getCascadedDevelopers(Long environmentID);
	Set<TestcenterUser> getCascadedSnrDevelopers(Long environmentID);
	
	ColModelAndNames getColumnModelAndNames(Long companyID);
	 
	Set<Requirement> getFilteredRequirements(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required);
	
	RequirementSummaryList getGridRequirments(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required);

}