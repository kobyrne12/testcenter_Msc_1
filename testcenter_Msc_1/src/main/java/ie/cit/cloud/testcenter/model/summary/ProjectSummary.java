/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.NoResultException;
import org.springframework.stereotype.Component;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

/**
 * @author byrnek1
 *
 */
@Component
public class ProjectSummary 
{			

	private ProjectService projectService;
	private TestrunService testrunService;
	private DefectService defectService;	

	private Set<Project> cascadedProjects = new LinkedHashSet<Project>();
	private Set<Cycle> cascadedCycles = new LinkedHashSet<Cycle>();	

	private Set<Testrun> cascadedTestruns = new LinkedHashSet<Testrun>();
	private Set<Testrun> requiredTestruns = new LinkedHashSet<Testrun>();
	private Set<Defect> defects = new LinkedHashSet<Defect>();

	private Project project = new Project();	

	///////////////////////////////////	

	private Long projectID = null;	    
	private String projectName = null;  
	private boolean parentProject;
	private String parentProjectName = null;  

	private Long companyID = null;
	private int totalChildProjects = -1;

	private String levelName = null;

	private int requiredPercent = -1;
	private int currentPercent = -1;

	private int totalAllowedSev1s = -1;
	private int totalCurrentSev1s = -1;
	private int totalAllowedSev2s = -1;
	private int totalCurrentSev2s = -1;
	private int totalAllowedSev3s = -1;
	private int totalCurrentSev3s = -1;
	private int totalAllowedSev4s = -1; 
	private int totalCurrentSev4s = -1;   
	private int totalDefects = -1;

	private int totalCycles = -1;
	private int totalEnvironments = -1;
	private int totalRequirements = -1;
	private int totalAllTestruns = -1;
	private int totalRequiredTestruns = -1;
	private int totalOptionalTestruns = -1;
	private int totalTestcases = -1;  
	private int totalTestplans = -1;

	private int totalTesters = -1;
	private int totalSeniorTesters = -1;
	private int totalDevelopers = -1;
	private int totalSeniorDevelopers = -1;

	private String lastModifiedBy;
	private String lastModifiedDate;    
	private String createdBy;
	private String creationDate; 

	private String customObject1;
	private String customObject2;
	private String customObject3;
	private String customObject4;
	private String customObject5;

	private int companyPosition = -1;

	public ProjectSummary()
	{

	}

	//    public ProjectSummary(Long projectID)
	//    {
	//    	this.projectID = projectID;      	
	//    }
	//    public ProjectSummary(Project project)
	//    {
	//    	this.projectID = project.getProjectID();    	
	//    	this.project = project;     	
	//    }

	public ProjectSummary(Project project, String levelName, ProjectService projectService,TestrunService testrunService, DefectService defectService )
	{
		this.projectID = project.getProjectID();
		if(levelName != null)
		{
			this.levelName = levelName;
		}
		this.project = project;  	
		this.projectService = projectService;
		this.testrunService = testrunService;
		this.defectService = defectService;
		//setCascadedProjects();
	}
	/**
	 * @return the cascadedProjects
	 */
	private Set<Project> getCascadedProjects() 
	{
		if(cascadedProjects == null || cascadedProjects.isEmpty())
		{		

			if(projectService.getParentAndChildProjects(projectID) != null && 
					!projectService.getParentAndChildProjects(projectID).isEmpty())
			{
				cascadedProjects = projectService.getParentAndChildProjects(projectID);		
			}
		}	
		return cascadedProjects;
	}

	//	/**
	//	 * @param cascadedProjects the cascadedProjects to set
	//	 */
	//	private void setCascadedProjects(Set<Project> cascadedProjects) 
	//	{		
	//		this.cascadedProjects = cascadedProjects;
	//	}

	/**
	 * @return the cascadedCycles
	 */
	private Set<Cycle> getCascadedCycles() 
	{		
		if(cascadedCycles == null || cascadedCycles.isEmpty())
		{		
			if(cascadedProjects == null || cascadedProjects.isEmpty())
			{
				cascadedProjects = getCascadedProjects(); 
			}
			if(cascadedProjects != null && !cascadedProjects.isEmpty())
			{
				for(final Project project : cascadedProjects)
				{
					if(project.getCycles() != null && !project.getCycles().isEmpty())
					{
						if(cascadedCycles == null || cascadedCycles.isEmpty())
						{
							cascadedCycles = project.getCycles();
						}
						else
						{
							cascadedCycles.addAll(project.getCycles());
						}
					}
				}
			}
			//			if(projectService.getParentAndChildCycles(projectID) != null && 
			//					!projectService.getParentAndChildCycles(projectID).isEmpty())
			//			{
			//			cascadedCycles = projectService.getParentAndChildCycles(projectID);  
			//			}
		}	
		return cascadedCycles;
	}
	//	/**
	//	 * @param cascadedCycles the cascadedCycles to set
	//	 */
	//	private void setCascadedCycles(Set<Cycle> cascadedCycles) {
	//		this.cascadedCycles = cascadedCycles;
	//	}

	/**
	 * @return the cascadedTestruns
	 */
	private Set<Testrun> getCascadedTestruns() 
	{
		if(cascadedTestruns == null || cascadedTestruns.isEmpty())
		{	
			if(cascadedCycles == null || cascadedCycles.isEmpty())
			{
				cascadedCycles = getCascadedCycles(); 
			}
			if(cascadedCycles != null && !cascadedCycles.isEmpty())
			{
				for(final Cycle cycle : cascadedCycles)
				{
					if(cycle.getTestruns() != null && !cycle.getTestruns().isEmpty())
					{
						if(cascadedTestruns == null || cascadedCycles.isEmpty())
						{
							cascadedTestruns = cycle.getTestruns();
						}
						else
						{
							cascadedTestruns.addAll(cycle.getTestruns());
						}
					}
				}
			}
		}
		//		if(cascadedTestruns == null || cascadedTestruns.isEmpty())
		//		{		
		//			if(projectService.getCascadedAllTestRuns(projectID) != null && 
		//					!projectService.getCascadedAllTestRuns(projectID).isEmpty())
		//			{
		//				cascadedTestruns = projectService.getCascadedAllTestRuns(projectID);  		
		//			}
		//		}
		return cascadedTestruns;
	}

	//	/**
	//	 * @param cascadedTestruns the cascadedTestruns to set
	//	 */
	//	private void setCascadedTestruns(Set<Testrun> cascadedTestruns) {
	//		this.cascadedTestruns = cascadedTestruns;
	//	}

	/**
	 * @return the requiredTestruns
	 */
	private Set<Testrun> getRequiredTestruns() 
	{
		if(requiredTestruns == null || requiredTestruns.isEmpty() )
		{
			if(cascadedTestruns == null || cascadedTestruns.isEmpty())
			{
				cascadedTestruns = getCascadedTestruns(); 
			}
			if(cascadedTestruns != null && !cascadedTestruns.isEmpty())
			{			
				for(final Testrun testrun : cascadedTestruns)
				{
					if(testrunService.isRequired(testrun.getTestrunID()))
					{
						this.requiredTestruns.add(testrun);
					}
				}			

			}
		}
		return requiredTestruns;
	}

	//	/**
	//	 * @param requiredTestruns the requiredTestruns to set
	//	 */
	//	private void setRequiredTestruns(Set<Testrun> requiredTestruns) {
	//		this.requiredTestruns = requiredTestruns;
	//	}

	/**
	 * @return the defects
	 */
	private Set<Defect> getDefects() 
	{
		if(defects == null || defects.isEmpty())
		{
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				for(final Testrun testrun : requiredTestruns)
				{
					if(testrun.getDefects() != null && !testrun.getDefects().isEmpty())
					{
						defects.addAll(testrun.getDefects());
					}					
				}				
			}

		}	
		return defects;
	}

	//	/**
	//	 * @param defects the defects to set
	//	 */
	//	private void setDefects(Set<Defect> defects) {
	//		this.defects = defects;
	//	}
	//
	//	/**
	//	 * @return the project
	//	 */
	//	private Project getProject() {
	//		return project;
	//	}
	//
	//	/**
	//	 * @param project the project to set
	//	 */
	//	private void setProject(Project project) {
	//		this.project = project;
	//	}
	/**
	 * @return the projectID
	 */
	public Long getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() 
	{
		if(projectName == null)
		{
			if(project != null)
			{
				projectName = project.getProjectName();
			}
		}
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	/**
	 * @return the companyID
	 */
	public Long getCompanyID() 
	{
		if(companyID == null)
		{
			companyID = project.getCompanyID();
		}
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}
	/**
	 * @return the parentProject
	 */
	public boolean isParentProject() 
	{		
		return project.isParent();
	}

	/**
	 * @param parentProject the parentProject to set
	 */
	public void setParentProject(boolean parentProject) {
		this.parentProject = parentProject;
	}

	/**
	 * @return the totalChildProjects
	 */
	public int getTotalChildProjects() 
	{		
		int count = 0;
		if(totalChildProjects == -1)
		{		
			if(project.isParent())
			{   
				try{count = projectService.getAllChildProjects(projectID).size();}
				catch(NoResultException nre){}
			}
		}
		return count;
	}

	/**
	 * @param totalChildProjects the totalChildProjects to set
	 */
	public void setTotalChildProjects(int totalChildProjects) {
		this.totalChildProjects = totalChildProjects;
	}

	/**
	 * @return the level
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * @return the requiredPercent
	 */
	public int getRequiredPercent() 
	{	
		int count = 94;
		if(requiredPercent == -1)
		{					
			if(levelName != null)
			{
				try{
					count = testrunService.getTestrunLevelByName(levelName).getTestrunLevelRequiredPercent();
				}
				catch(NoResultException nre){}
			}		
		}		
		return count;
	}

	/**
	 * @param requiredPercent the requiredPercent to set
	 */
	public void setRequiredPercent(int requiredPercent) {
		this.requiredPercent = requiredPercent;
	}

	/**
	 * @return the currentPercent "Passed"
	 */
	public int getCurrentPercent()
	{
		int count = 0;
		if(currentPercent == -1)
		{
			if(requiredTestruns == null || requiredTestruns.isEmpty() )
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				for(final Testrun testrun : requiredTestruns)
				{
					if(testrun.isPassed())
					{
						count++;
					}					
				}				

			}
		}
		return count;
	}

	/**
	 * @param currentPercent the currentPercent to set
	 */
	public void setCurrentPercent(int currentPercent) {
		this.currentPercent = currentPercent;
	}

	/**
	 * @return the totalAllowedSev1s
	 */
	public int getTotalAllowedSev1s() 
	{
		int count = 0;
		if(totalAllowedSev1s == -1)
		{
			totalAllowedSev1s = project.getAllowedSev1();			
		}
		return count;		
	}

	/**
	 * @param totalAllowedSev1s the totalAllowedSev1s to set
	 */
	public void setTotalAllowedSev1s(int totalAllowedSev1s) {
		this.totalAllowedSev1s = totalAllowedSev1s;
	}

	/**
	 * @return the totalCurrentSev1s
	 */
	public int getTotalCurrentSev1s() 
	{
		int count = 0;
		if(totalCurrentSev1s == -1)
		{
			if(defects == null || defects.isEmpty())
			{
				defects = getDefects();
			}
			if(defects != null && !defects.isEmpty())
			{
				for(final Defect defect : defects)
				{
					if(defectService.isSev1(defect.getDefectID()))
					{
						count ++;
					}					
				}				

			}
		}
		return count;		
	}

	/**
	 * @param totalCurrentSev1s the totalCurrentSev1s to set
	 */
	public void setTotalCurrentSev1s(int totalCurrentSev1s) {
		this.totalCurrentSev1s = totalCurrentSev1s;
	}

	/**
	 * @return the totalAllowedSev2s
	 */
	public int getTotalAllowedSev2s() {
		int count = 0;
		if(totalAllowedSev2s == -1)
		{
			totalAllowedSev2s = project.getAllowedSev2();			
		}
		return count;
	}

	/**
	 * @param totalAllowedSev2s the totalAllowedSev2s to set
	 */
	public void setTotalAllowedSev2s(int totalAllowedSev2s) {
		this.totalAllowedSev2s = totalAllowedSev2s;
	}

	/**
	 * @return the totalCurrentSev2s
	 */
	public int getTotalCurrentSev2s()
	{
		int count = 0;
		if(totalCurrentSev2s == -1)
		{
			if(defects == null || defects.isEmpty())
			{
				defects = getDefects();
			}
			if(defects != null && !defects.isEmpty())
			{
				for(final Defect defect : defects)
				{
					if(defectService.isSev2(defect.getDefectID()))
					{
						count ++;
					}					
				}

			}		
		}
		return count;		

	}

	/**
	 * @param totalCurrentSev2s the totalCurrentSev2s to set
	 */
	public void setTotalCurrentSev2s(int totalCurrentSev2s) {
		this.totalCurrentSev2s = totalCurrentSev2s;
	}

	/**
	 * @return the totalAllowedSev3s
	 */
	public int getTotalAllowedSev3s() {
		int count = 0;
		if(totalAllowedSev3s == -1)
		{
			totalAllowedSev3s = project.getAllowedSev3();			
		}
		return count;
	}

	/**
	 * @param totalAllowedSev3s the totalAllowedSev3s to set
	 */
	public void setTotalAllowedSev3s(int totalAllowedSev3s) {
		this.totalAllowedSev3s = totalAllowedSev3s;
	}

	/**
	 * @return the totalCurrentSev3s
	 */
	public int getTotalCurrentSev3s()
	{
		int count = 0;
		if(totalCurrentSev3s == -1)
		{
			if(defects == null || defects.isEmpty())
			{
				defects = getDefects();
			}
			if(defects != null && !defects.isEmpty())
			{
				for(final Defect defect : defects)
				{
					if(defectService.isSev3(defect.getDefectID()))
					{
						count ++;
					}					
				}				

			}
		}
		return count;		
	}

	/**
	 * @param totalCurrentSev3s the totalCurrentSev3s to set
	 */
	public void setTotalCurrentSev3s(int totalCurrentSev3s) {
		this.totalCurrentSev3s = totalCurrentSev3s;
	}

	/**
	 * @return the totalAllowedSev4s
	 */
	public int getTotalAllowedSev4s() {
		int count = 0;
		if(totalAllowedSev4s == -1)
		{
			totalAllowedSev4s = project.getAllowedSev4();			
		}
		return count;
	}

	/**
	 * @param totalAllowedSev4s the totalAllowedSev4s to set
	 */
	public void setTotalAllowedSev4s(int totalAllowedSev4s) {
		this.totalAllowedSev4s = totalAllowedSev4s;
	}

	/**
	 * @return the totalCurrentSev4s
	 */
	public int getTotalCurrentSev4s() 
	{
		int count = 0;
		if(totalCurrentSev4s == -1)
		{
			if(defects == null || defects.isEmpty())
			{
				defects = getDefects();
			}
			if(defects != null && !defects.isEmpty())
			{
				for(final Defect defect : defects)
				{
					if(defectService.isSev4(defect.getDefectID()))
					{
						count ++;
					}					
				}

			}		
		}
		return count;		
	}

	/**
	 * @param totalCurrentSev4s the totalCurrentSev4s to set
	 */
	public void setTotalCurrentSev4s(int totalCurrentSev4s) {
		this.totalCurrentSev4s = totalCurrentSev4s;
	}

	/**
	 * @return the totalDefects
	 */
	public int getTotalDefects() 
	{
		int count = 0;
		if(totalDefects == -1)
		{
			if(defects == null || defects.isEmpty())
			{
				defects = getDefects();
			}
			if(defects != null && !defects.isEmpty())
			{
				count = defects.size();
			}
		}
		return count;			
	}

	/**
	 * @param totalDefects the totalDefects to set
	 */
	public void setTotalDefects(int totalDefects) {
		this.totalDefects = totalDefects;
	}

	/**
	 * @return the totalCycles
	 */
	public int getTotalCycles() 
	{
		int totalCycles = 0;
		if(cascadedCycles == null || cascadedCycles.isEmpty())	
		{
			cascadedCycles = getCascadedCycles();
		}
		if(cascadedCycles != null && !cascadedCycles.isEmpty())
		{
			totalCycles = cascadedCycles.size();
		}		
		return totalCycles;
	}

	/**
	 * @param totalCycles the totalCycles to set
	 */
	public void setTotalCycles(int totalCycles) {
		this.totalCycles = totalCycles;
	}

	/**
	 * @return the totalEnvironments
	 */
	public int getTotalEnvironments()
	{
		int count = 0;
		if(totalEnvironments == -1)
		{
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				for(final Testrun testrun : requiredTestruns)
				{
					if(testrun.getEnvironments() != null && !testrun.getEnvironments().isEmpty())
					{
						count += testrun.getEnvironments().size();
					}					
				}				
			}		
		}
		return count;
	}

	/**
	 * @param totalEnvironments the totalEnvironments to set
	 */
	public void setTotalEnvironments(int totalEnvironments) {
		this.totalEnvironments = totalEnvironments;
	}

	/**
	 * @return the totalRequirements
	 */
	public int getTotalRequirements() 
	{
		int count = 0;
		if(totalRequirements == -1)
		{
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				for(final Testrun testrun : requiredTestruns)
				{
					if(testrun.getRequirements() != null && !testrun.getRequirements().isEmpty())
					{
						count += testrun.getRequirements().size();
					}
				}

			}		
		}
		return count;
	}

	/**
	 * @param totalRequirements the totalRequirements to set
	 */
	public void setTotalRequirements(int totalRequirements) {
		this.totalRequirements = totalRequirements;
	}

	/**
	 * @return the totalAllTestruns
	 */
	public int getTotalAllTestruns() 
	{
		int count = 0;
		if(totalAllTestruns == -1)
		{
			if(cascadedTestruns == null || cascadedTestruns.isEmpty())
			{
				cascadedTestruns = getCascadedTestruns();
			}
			if(cascadedTestruns != null && !cascadedTestruns.isEmpty())
			{			
				count = cascadedTestruns.size();
			}
		}
		return count;		
	}

	/**
	 * @param totalAllTestruns the totalAllTestruns to set
	 */
	public void setTotalAllTestruns(int totalAllTestruns) {
		this.totalAllTestruns = totalAllTestruns;
	}


	/**
	 * @return the totalRequiredTestruns
	 */
	public int getTotalRequiredTestruns() 
	{
		int count = 0;
		if(totalRequiredTestruns == -1)
		{
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				count = requiredTestruns.size();
			}

		}
		return count;
	}

	/**
	 * @param totalRequiredTestruns the totalRequiredTestruns to set
	 */
	public void setTotalRequiredTestruns(int totalRequiredTestruns) {
		this.totalRequiredTestruns = totalRequiredTestruns;
	}

	/**
	 * @return the totalOptionalTestruns
	 */
	public int getTotalOptionalTestruns() 
	{
		int count = 0;
		if (totalOptionalTestruns == -1)
		{
			if(cascadedTestruns == null || cascadedTestruns.isEmpty())
			{
				cascadedTestruns = getCascadedTestruns();
			}
			if(cascadedTestruns != null && !cascadedTestruns.isEmpty())
			{			
				for(final Testrun testrun : cascadedTestruns)
				{
					if(!testrunService.isRequired(testrun.getTestrunID()))
					{
						count++;
					}
				}			
			}
		}
		return count;		
	}

	/**
	 * @param totalOptionalTestruns the totalOptionalTestruns to set
	 */
	public void setTotalOptionalTestruns(int totalOptionalTestruns) {
		this.totalOptionalTestruns = totalOptionalTestruns;
	}

	/**
	 * @return the totalTestcases
	 */
	public int getTotalTestcases() 
	{
		int count = 0;
		if(totalTestcases == -1)
		{
			if(project.getTestplans() != null && !project.getTestplans().isEmpty())
			{
				for(final Testplan testplan : project.getTestplans())
				{
					if(testplan.getTestcases() != null && !testplan.getTestcases().isEmpty())
					{
						count += testplan.getTestcases().size();
					}
				}
			}			
		}
		return count;
	}

	/**
	 * @param totalTestcases the totalTestcases to set
	 */
	public void setTotalTestcases(int totalTestcases) {
		this.totalTestcases = totalTestcases;
	}

	/**
	 * @return the totalTestplans
	 */
	public int getTotalTestplans() 
	{
		int count = 0;
		if(totalTestplans == -1)
		{
			if(project.getTestplans() != null && !project.getTestplans().isEmpty())
			{
				count = project.getTestplans().size();
			}		
		}
		return count;
	}

	/**
	 * @param totalTestplans the totalTestplans to set
	 */
	public void setTotalTestplans(int totalTestplans) {
		this.totalTestplans = totalTestplans;
	}

	/**
	 * @return the totalTesters
	 */
	public int getTotalTesters() {
		//TODO : get users
		totalTesters = 100;
		return totalTesters;
	}

	/**
	 * @param totalTesters the totalTesters to set
	 */
	public void setTotalTesters(int totalTesters) {
		this.totalTesters = totalTesters;
	}

	/**
	 * @return the totalSeniorTesters
	 */
	public int getTotalSeniorTesters() {
		//TODO : get users
		totalSeniorTesters = 100;
		return totalSeniorTesters;
	}

	/**
	 * @param totalSeniorTesters the totalSeniorTesters to set
	 */
	public void setTotalSeniorTesters(int totalSeniorTesters) {
		this.totalSeniorTesters = totalSeniorTesters;
	}

	/**
	 * @return the totalDevelopers
	 */
	public int getTotalDevelopers() {
		//TODO : get users
		totalDevelopers = 100;
		return totalDevelopers;
	}

	/**
	 * @param totalDevelopers the totalDevelopers to set
	 */
	public void setTotalDevelopers(int totalDevelopers) {
		this.totalDevelopers = totalDevelopers;
	}

	/**
	 * @return the totalSeniorDevelopers
	 */
	public int getTotalSeniorDevelopers() {
		//TODO : get users
		totalSeniorDevelopers = 100;
		return totalSeniorDevelopers;
	}

	/**
	 * @param totalSeniorDevelopers the totalSeniorDevelopers to set
	 */
	public void setTotalSeniorDevelopers(int totalSeniorDevelopers) {
		this.totalSeniorDevelopers = totalSeniorDevelopers;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		if(lastModifiedBy == null)
		{			
			if(project.getLastModifiedBy() == null)
			{
				lastModifiedBy = "n/a";
			}
			else
			{
				lastModifiedBy = project.getLastModifiedBy();
			}	
		}
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public String getLastModifiedDate() {
		if(lastModifiedDate == null)
		{
			if(project.getLastModifiedDate() == null)
			{
				lastModifiedDate = "n/a";
			}
			else
			{
				lastModifiedDate = project.getLastModifiedDate();
			}				
		}
		return lastModifiedDate;		
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		if(createdBy == null)
		{
			if(project.getCreatedBy() == null)
			{
				createdBy = "n/a";
			}
			else
			{
				createdBy = project.getCreatedBy();
			}		
		}
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() 
	{
		if(creationDate == null)
		{
			if(project.getCreationDate() == null)
			{
				creationDate = "n/a";
			}
			else
			{
				creationDate = project.getCreationDate();
			}
		}
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the customObject1
	 */
	public String getCustomObject1() {
		return customObject1;
	}

	/**
	 * @param customObject1 the customObject1 to set
	 */
	public void setCustomObject1(String customObject1) {
		this.customObject1 = customObject1;
	}

	/**
	 * @return the customObject2
	 */
	public String getCustomObject2() {
		return customObject2;
	}

	/**
	 * @param customObject2 the customObject2 to set
	 */
	public void setCustomObject2(String customObject2) {
		this.customObject2 = customObject2;
	}

	/**
	 * @return the customObject3
	 */
	public String getCustomObject3() {
		return customObject3;
	}

	/**
	 * @param customObject3 the customObject3 to set
	 */
	public void setCustomObject3(String customObject3) {
		this.customObject3 = customObject3;
	}

	/**
	 * @return the customObject4
	 */
	public String getCustomObject4() {
		return customObject4;
	}

	/**
	 * @param customObject4 the customObject4 to set
	 */
	public void setCustomObject4(String customObject4) {
		this.customObject4 = customObject4;
	}

	/**
	 * @return the customObject5
	 */
	public String getCustomObject5() {
		return customObject5;
	}

	/**
	 * @param customObject5 the customObject5 to set
	 */
	public void setCustomObject5(String customObject5) {
		this.customObject5 = customObject5;
	}
	//	/**
	//	 * @return the projectService
	//	 */
	//	private ProjectService getProjectService() {
	//		return projectService;
	//	}
	//
	//	/**
	//	 * @param projectService the projectService to set
	//	 */
	//	private void setProjectService(ProjectService projectService) {
	//		this.projectService = projectService;
	//	}
	//
	//	/**
	//	 * @return the testrunService
	//	 */
	//	private TestrunService getTestrunService() {
	//		return testrunService;
	//	}
	//
	//	/**
	//	 * @param testrunService the testrunService to set
	//	 */
	//	private void setTestrunService(TestrunService testrunService) {
	//		this.testrunService = testrunService;
	//	}
	//
	//	/**
	//	 * @return the defectService
	//	 */
	//	private DefectService getDefectService() {
	//		return defectService;
	//	}
	//
	//	/**
	//	 * @param defectService the defectService to set
	//	 */
	//	private void setDefectService(DefectService defectService) {
	//		this.defectService = defectService;
	//	}

	/**
	 * @return the companyPosition
	 */
	public int getCompanyPosition()
	{		
		int count = 0;
		if(totalTestplans == -1)
		{
			// TODO : handle project position
			count = 555;
		}
		return count;
	}

	/**
	 * @param companyPosition the companyPosition to set
	 */
	public void setCompanyPosition(int companyPosition) {
		this.companyPosition = companyPosition;
	}

	/**
	 * @return the parentProjectName
	 */
	public String getParentProjectName()
	{
		if(parentProjectName == null)
		{
			if(project.getParentID() != null)
			{
				if(projectService.getProject(project.getParentID()) != null)
				{
					parentProjectName = projectService.getProject(project.getParentID()).getProjectName();
				}
			}
		}
		return parentProjectName;
	}

	/**
	 * @param parentProjectName the parentProjectName to set
	 */
	public void setParentProjectName(String parentProjectName) {
		this.parentProjectName = parentProjectName;
	}


}