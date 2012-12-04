/**
 * 
 */
package ie.cit.cloud.testcenter.service.project;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.display.RelatedObjectList;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.NoResultException;

/**
 * Peforms business operation on project
 */
public interface ProjectService {

	Set<Project> getAllProjects();

	void addNewProject(Project project);

	Project getProject(Long projectID);   

	Project getProjectByName(String projectName);

	Set<Project> getAllChildProjects(Long projectID);

	void update(Project project);

	void remove(Long projectID);

	void updateProjectWithId(Long projectID, Project project);

	void updateProjectNameWithId(Long projectID, Project project,String projectName);

	boolean updateProject(Long projectID, Project project);

	Set<Project> getAllProjectsByCompanyID(Long companyID);
	///////////////////////////////////
	/**
	 * Returns a collection of ChildProject for a project 
	 * Set<Project>
	 * @return collection of ChildProject for a project
	 */
	Set<Project> getChildProjects(Long projectID);
	/**
	 * Returns a collection of All projects including the parent 
	 * Set<Project>
	 * @return collection of All projects including the parent 
	 */
	Set<Project> getParentAndChildProjects(Long projectID);
	/**
	 * Returns a Projects Parent Project  
	 * Project
	 * @return a Projects Parent Project, otherwise null
	 */	
	Project getParentProject(Long projectID);	
	/**
	 * Returns a collection of All Cycles in a project incl all child project cycles 
	 * Set<Cycle>
	 * @return collection of All  Cycles in a project incl all child project cycles,
	 */	
	Set<Cycle> getParentAndChildCycles(Long projectID);
	/**
	 * Returns a collection of All Testruns in a project incl all child project cycles 
	 * Set<Testrun>
	 * @return collection of All Testruns in a project incl all child project cycles,
	 */	
	Set<Testrun> getCascadedAllTestRuns(Long projectID);
	/**
	 * Returns a collection of Compulsory Testruns in a project incl all child project cycles 
	 * Set<Testrun>
	 * @return collection of Compulsory Testruns in a project incl all child project cycles,
	 */	
	Set<Testrun> getCascadedCompulsoryTestRuns(Long projectID);
	/**
	 * Returns a collection of Optional Testruns in a project incl all child project cycles 
	 * Set<Testrun>
	 * @return collection of Optional Testruns in a project incl all child project cycles,
	 */	
	Set<Testrun> getCascadedOptionalTestRuns(Long projectID);
	/**
	 * Returns a collection of Testcases in a project incl all child project cycles 
	 * Set<Testcase>
	 * @return collection of Testcases in a project incl all child project cycles,
	 */	
	Set<Testcase> getCascadedAllTestCases(Long projectID);
	/**
	 * Returns a collection of Compulsory Testcases in a project incl all child project cycles 
	 * Set<Testcase>
	 * @return collection of Compulsory Testcases in a project incl all child project cycles,
	 */	
	Set<Testcase> getCascadedCompulsoryTestCases(Long projectID);
	/**
	 * Returns a collection of Optional Testcases in a project incl all child project cycles 
	 * Set<Testcase>
	 * @return collection of Optional Testcases in a project incl all child project cycles,
	 */	
	Set<Testcase> getCascadedOptionalTestCases(Long projectID);
	/**
	 * Returns a collection of Testplans in a project incl all child project cycles 
	 * Set<Testcase>
	 * @return collection of Testplans in a project incl all child project cycles,
	 */	
	Set<Testplan> getCascadedAllTestPlans(Long projectID);
	/**
	 * Returns a collection of Compulsory Testplans in a project incl all child project cycles 
	 * Set<Testcase>
	 * @return collection of Compulsory Testplans in a project incl all child project cycles,
	 */		
	Set<Testplan> getCascadedCompulsoryTestPlans(Long projectID);
	/**
	 * Returns a collection of Optional Testplans in a project incl all child project cycles 
	 * Set<Testcase>
	 * @return collection of Optional Testplans in a project incl all child project cycles,
	 */	
	Set<Testplan> getCascadedOptionalTestPlans(Long projectID);
	/**
	 * Returns a collection of Defects in a project incl all child project cycles 
	 * Set<Tesplan>
	 * @return collection of Defects in a project incl all child project cycles,
	 */	
	Set<Defect> getCascadedDefects(Long projectID);
	/**
	 * Returns a collection of Sev1 Defects in a project incl all child project cycles 
	 * Set<Tesplan>
	 * @return collection of Sev1 Defects in a project incl all child project cycles,
	 */	
	Set<Defect> getCascadedSev1Defects(Long projectID);
	/**
	 * Returns a collection of Sev2 Defects in a project incl all child project cycles 
	 * Set<Tesplan>
	 * @return collection of Sev2 defects in a project incl all child project cycles,
	 */	
	Set<Defect> getCascadedSev2Defects(Long projectID);
	/**
	 * Returns a collection of Sev3 Defects in a project incl all child project cycles 
	 * Set<Tesplan>
	 * @return collection of Sev3 Defects in a project incl all child project cycles,
	 */	
	Set<Defect> getCascadedSev3Defects(Long projectID);
	/**
	 * Returns a collection of Sev4 Defects in a project incl all child project cycles 
	 * Set<Tesplan>
	 * @return collection of Sev4 Defects in a project incl all child project cycles,
	 */	
	Set<Defect> getCascadedSev4Defects(Long projectID);
	/**
	 * Returns a collection of Environments in a project incl all child project cycles 
	 * Set<Tesplan>
	 * @return collection of Environments in a project incl all child project cycles,
	 */	
	Set<Environment> getCascadedEnvironments(Long projectID);
	/**
	 * Returns a collection of Requirements in a project incl all child project cycles 
	 * Set<Tesplan>
	 * @return collection of Requirements in a project incl all child project cycles,
	 */	
	Set<Requirement> getCascadedRequirements(Long projectID);

	Set<TestcenterUser> getCascadedTesters(Long projectID);
	Set<TestcenterUser> getCascadedSnrTesters(Long projectID);
	Set<TestcenterUser> getCascadedDevelopers(Long projectID);
	Set<TestcenterUser> getCascadedSnrDevelopers(Long projectID);
	//////////////////////////////////
	
	//ProjectSummary getProjectSummary(Long companyID, Long projectID, String level  );

	ColModelAndNames getColumnModelAndNames(Long companyID);

	ProjectSummaryList getGridProjects(Long companyID, String projectID, String cycleID,
			String testplanID,String testcaseID, String testrunID, String defectID, 
			String requirementID, String environmentID, String userID, String levelName);
	
	Set<Project> getFilteredProjects(Long companyID, String projectID, String cycleID,
			String testplanID,String testcaseID, String testrunID, String defectID, 
			String requirementID, String environmentID, String userID, String levelName);

	RelatedObjectList getRelatedObjects(Long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);

}