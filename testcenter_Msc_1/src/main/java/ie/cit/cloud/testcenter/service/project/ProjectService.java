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
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;

import java.util.Collection;

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
	
	ProjectSummary getProjectSummary(long projectID);	
	
	ColModelAndNames getColumnModelAndNames(Long companyID);

	ProjectSummaryList getGridProjects(long companyID,String projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);

	RelatedObjectList getRelatedObjects(long projectID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);
	
}