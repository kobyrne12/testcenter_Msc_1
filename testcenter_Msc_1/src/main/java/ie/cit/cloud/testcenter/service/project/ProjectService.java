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
import java.util.Collection;

import javax.persistence.NoResultException;

/**
 * Peforms business operation on project
 */
public interface ProjectService {

    Collection<Project> getAllProjects();

    void addNewProject(Project project);

    Project getProject(long projectID);   
    
    Project getProjectByName(String projectName);
    
    Collection<Project> getAllChildProjects(Long projectID);
    
    void update(Project project);
    
    void remove(long projectID);

    void updateProjectWithId(long projectID, Project project);
    
    void updateProjectNameWithId(long projectID, Project project,String projectName);
    
    boolean updateProject(long projectID, Project project);

	Collection<Project> getAllProjectsByCompanyID(long companyID);
	///////////////////////////////////

	/**
	 * Returns a collection of ChildProject for a project 
	 * Collection<Project>
	 * @return collection of ChildProject for a project
	 */
	Collection<Project> getChildProjects(long projectID);
	/**
	 * Returns a Projects Parent Project  
	 * Project
	 * @return a Projects Parent Project, otherwise null
	 */	
	Project getParentProject(long projectID);
	/**
	 * Returns total Number of All Cycles for a project incl child project cycles
	 * int
	 * @return total Number of All Cycles for a project incl child project cycles
	 */	
	int getCascadedCyclesCount(long projectID);
	/**
	 * Returns a collection of All Cycles in a project incl all child project cycles 
	 * Collection<Cycle>
	 * @return collection of All  Cycles in a project incl all child project cycles,
	 */	
	Collection<Cycle> getCascadedCycles(long projectID);
	/**
	 * Returns total Number of All Test Runs for a project
	 * int
	 * @return total Number of All Test Runs for a project
	 */	
	int getCascadedTestRunsCount(long projectID);
	/**
	 * Returns a collection of All Testruns in a project incl all child project cycles 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a project incl all child project cycles,
	 */	
	Collection<Testrun> getCascadedAllTestRuns(long projectID);
	/**
	 * Returns total Number of All Required Testruns for a project
	 * int
	 * @return total Number of All Required Testruns for a project
	 */		
	int getCascadedRequiredTestRunsCount(long projectID);
	/**
	 * Returns a collection of Required Testruns in a project incl all child project cycles 
	 * Collection<Testrun>
	 * @return collection of Required Testruns in a project incl all child project cycles,
	 */	
	Collection<Testrun> getCascadedRequiredTestRuns(long projectID);
	/**
	 * Returns a collection of Testplans in a project incl all child project cycles 
	 * Collection<Tesplan>
	 * @return collection of Testplans in a project incl all child project cycles,
	 */	
	Collection<Testplan> getCascadedTestPlans(long projectID);
	/**
	 * Returns a collection of Testcases in a project incl all child project cycles 
	 * Collection<Testcase>
	 * @return collection of Testcases in a project incl all child project cycles,
	 */	
	Collection<Testcase> getCascadedTestCases(long projectID);
	/**
	 * Returns a collection of Defects in a project incl all child project cycles 
	 * Collection<Defect>
	 * @return collection of Defects in a project incl all child project cycles,
	 */	
	Collection<Defect> getCascadedDefects(long projectID);
	/**
	 * Returns a collection of Sev 1 Defects in a project incl all child project cycles 
	 * Collection<Defect>
	 * @return collection of  Sev 1 Defects in a project incl all child project cycles,
	 */	
	Collection<Defect> getCascadedSev1Defects(long projectID);
	/**
	 * Returns a collection of Sev 2 Defects in a project incl all child project cycles 
	 * Collection<Defect>
	 * @return collection of  Sev 2 Defects in a project incl all child project cycles,
	 */	
	Collection<Defect> getCascadedSev2Defects(long projectID);
	/**
	 * Returns a collection of Sev 3 Defects in a project incl all child project cycles 
	 * Collection<Defect>
	 * @return collection of  Sev 3 Defects in a project incl all child project cycles,
	 */	
	Collection<Defect> getCascadedSev3Defects(long projectID);
	/**
	 * Returns a collection of Sev 4 Defects in a project incl all child project cycles 
	 * Collection<Defect>
	 * @return collection of  Sev 4 Defects in a project incl all child project cycles,
	 */	
	Collection<Defect> getCascadedSev4Defects(long projectID);
	/**
	 * Returns a collection of Environments in a project incl all child project cycles 
	 * Collection<Environment>
	 * @return collection of Environments in a project incl all child project cycles,
	 */	
	Collection<Environment> getCascadedEnvironments(long projectID);
	/**
	 * Returns a collection of Requirements in a project incl all child project cycles 
	 * Collection<Requirement>
	 * @return collection of Requirements in a project incl all child project cycles,
	 */	
	Collection<Requirement> getCascadedRequirements(long projectID);
	/**
	 * Returns a collection of Testers in a project incl all child project cycles 
	 * Collection<User>
	 * @return collection of Testers in a project incl all child project cycles,
	 */	
	Collection<TestcenterUser> getCascadedTesters(long projectID);
	/**
	 * Returns a collection of Senior Testers in a project incl all child project cycles 
	 * Collection<User>
	 * @return collection of Senior Testers in a project incl all child project cycles,
	 */	
	Collection<TestcenterUser> getCascadedSnrTesters(long projectID);
	/**
	 * Returns a collection of Developers in a project incl all child project cycles 
	 * Collection<User>
	 * @return collection of Developers in a project incl all child project cycles,
	 */	
	Collection<TestcenterUser> getCascadedDevelopers(long projectID);
	/**
	 * Returns a collection of Senior Developers in a project incl all child project cycles 
	 * Collection<User>
	 * @return collection of Senior Developers in a project incl all child project cycles,
	 */	
	Collection<TestcenterUser> getCascadedSnrDevelopers(long projectID);
	//////////////////////////////////
	
	ProjectSummary getProjectSummary(long projectID);	
	
	ColModelAndNames getColumnModelAndNames(Long companyID);

	ProjectSummaryList getGridProjects(long companyID,String projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);

	RelatedObjectList getRelatedObjects(long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);
	
}