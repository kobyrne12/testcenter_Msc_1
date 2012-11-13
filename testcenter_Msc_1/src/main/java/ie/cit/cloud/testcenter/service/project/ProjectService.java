/**
 * 
 */
package ie.cit.cloud.testcenter.service.project;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ProjectsColMolsAndNames;
import ie.cit.cloud.testcenter.display.ProjectsDisplay;
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
    
    void update(Project project);
    
    void remove(long projectID);

    void updateProjectWithId(long projectID, Project project);
    
    void updateProjectNameWithId(long projectID, Project project,String projectName);
    
    boolean updateProject(long projectID, Project project);

	Collection<Project> getAllProjectsByCompanyID(long companyID);
	
	ProjectSummary getProjectSummary(long projectID);
	
	Collection<ProjectSummary> getAllProjectSummaryForCompany(long companyID);

	Collection<ProjectSummary> getAllProjectSummaryForCycle(long cycleID);
	
	ProjectsColMolsAndNames getProjectColumnModelAndNames(long companyID);

	ProjectSummaryList getsummaryList(long companyID, String cycleID,
			String testplanID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);
	
}