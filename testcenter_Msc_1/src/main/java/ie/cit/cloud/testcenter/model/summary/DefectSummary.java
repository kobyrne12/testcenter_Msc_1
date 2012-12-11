/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;

/**
 * @author byrnek1
 *
 */

public class DefectSummary
{	

	//private ProjectService projectService;
	//private CycleService cycleService;
	//private TestcaseService testcaseService;
	private TestrunService testrunService;
	private DefectService defectService;	
	private TestplanService testplanService;
	//private ProjectService projectService;

	private Set<Testrun> allTestruns = new LinkedHashSet<Testrun>();
	private Set<Testrun> requiredTestruns = new LinkedHashSet<Testrun>();
	//private Set<Testrun> completedTestruns = new LinkedHashSet<Testrun>();
	//private Set<Testrun> incompleteTestruns = new LinkedHashSet<Testrun>();
	private Set<Defect> defects = new LinkedHashSet<Defect>();
	private Set<Cycle> cycles = new LinkedHashSet<Cycle>();

	//private Project project = new Project();
	//private Cycle cycle = new Cycle();
	private Defect defect = new Defect();
	////////////////////////////////////
	private Long defectID = null;    
	private String defectSummary = null;
	private String defectDetails = null;
	private int defectSeverity = -1;	
	private String defectSection = null;

	private int totalProjects = -1;
	private int totalCycles = -1;
	private int totalTestplans = -1;
	private int totalTestcases = -1;
	private int totalRequirements = -1;
	private int totalEnvironments = -1;
	
	private int totalAllTestruns = -1;
	private int totalRequiredTestruns = -1;
	private int totalOptionalTestruns = -1;   

	private int totalTesters = -1;
	private int totalSeniorTesters = -1;
	private int totalDevelopers = -1;
	private int totalSeniorDevelopers = -1;

	private String lastModifiedBy = null;
	private Date lastModifiedDate = null;    
	private String createdBy = null;
	private Date creationDate = null;

	private Long companyID = null;

	public DefectSummary() {	
	}

	public DefectSummary(Defect defect, TestrunService testrunService, 
			DefectService defectService,TestplanService testplanService)
	{
		this.defectID = defect.getDefectID();			
		this.defect = defect;  	
		//this.projectService = projectService; 
		//this.cycleService = cycleService;
		this.testrunService = testrunService;
		this.defectService = defectService;		
		this.testplanService = testplanService;		
		//this.projectService = projectService;
		this.companyID = getCompanyID();		
	}


	/**
	 * @return the allTestruns
	 */
	private Set<Testrun> getAllTestruns()
	{
		if(allTestruns == null || allTestruns.isEmpty())
		{
			if(defect.getTestruns() != null || !defect.getTestruns().isEmpty())
			{
				allTestruns = defect.getTestruns();
			}	
		}			
		return allTestruns;
	}

	/**
	 * @param allTestruns the allTestruns to set
	 */
	private void setAllTestruns(Set<Testrun> allTestruns) {
		this.allTestruns = allTestruns;
	}

	/**
	 * @return the requiredTestruns
	 */
	private Set<Testrun> getRequiredTestruns() 
	{
		if(requiredTestruns == null || requiredTestruns.isEmpty() )
		{
			if(allTestruns == null || allTestruns.isEmpty())
			{
				allTestruns = getAllTestruns(); 
			}
			if(allTestruns != null && !allTestruns.isEmpty())
			{			
				for(final Testrun testrun : allTestruns)
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

	/**
	 * @param requiredTestruns the requiredTestruns to set
	 */
	private void setRequiredTestruns(Set<Testrun> requiredTestruns) {
		this.requiredTestruns = requiredTestruns;
	}

	

	/**
	 * @return the defectID
	 */
	public Long defectID() {
		return defectID;
	}

	/**
	 * @param defectID the defectID to set
	 */
	public void setDefectID(Long defectID) {
		this.defectID = defectID;
	}

	/**
	 * @return the defectSummary
	 */
	public String getDefectSummary()
	{
		if(defectSummary == null)
		{
			if(defect != null)
			{
				defectSummary = defect.getDefectSummary();
			}
		}
		return defectSummary;		
	}

	/**
	 * @param defectSummary the testcaseName to set
	 */
	public void setDefectSummary(String defectSummary) {
		this.defectSummary= defectSummary;
	}
	
	/**
	 * @return the defectDetails
	 */
	public String getDefectDetails()
	{
		if(defectDetails == null)
		{
			if(defect != null)
			{
				defectDetails = defect.getDefectDetails();
			}
		}
		return defectDetails;		
	}

	/**
	 * @param defectDetails to set
	 */
	public void setDefectDetails(String defectDetails) {
		this.defectDetails= defectDetails;
	}

	
	/**
	 * @return the defectSeverity
	 */
	public int getDefectSeverity()
	{
		if(defectSeverity == -1)
		{
			if(defect != null)
			{
				defectSeverity = defect.getSeverity();
			}
		}		
		return defectSeverity;
	}

	/**
	 * @param defectSeverity the defectSeverity to set
	 */
	public void setDefectSeverity(int defectSeverity) {
		this.defectSeverity = defectSeverity;
	}

	/**
	 * @return the defectSection
	 */
	public String getDefectSection()
	{
		if(defectSection == null)
		{
			if(defect != null)
			{
				Long defectSectionID = defect.getDefectSectionID();
				//TODO::  Look up sections and get name for ID
			}
		}			
		return defectSection;
	}

	/**
	 * @param defectSection the defectSection to set
	 */
	public void setDefectSection(String defectSection) {
		this.defectSection = defectSection;
	}

	/**
	 * @return the cycles
	 */
	private Set<Cycle> getCycles() 
	{				
		if(cycles == null || cycles.isEmpty())
		{					
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}			
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{				
				for(final Testrun testrun : requiredTestruns)
				{
					Cycle testruncycle = testrunService.getCycle(testrun.getTestrunID());
					if(testruncycle!= null)
					{
						cycles.add(testruncycle);										
					}					
				}				
			}
		}	
		return cycles;		
	}
	
	/**
	 * @return the totalCycles
	 */
	public int getTotalCycles() 
	{
		int count = 0;
		if (totalCycles == -1)
		{
			if(cycles == null || cycles.isEmpty())
			{
				cycles = getCycles();
			}
			if(cycles != null && !cycles.isEmpty())
			{
				count = cycles.size();					
			}
		}	
		return count;					
	}

	/**
	 * @param totalCycles the totalCycles to set
	 */
	public void setTotalCycles(int totalCycles) {
		this.totalCycles = totalCycles;
	}

	/**
	 * @return the totalProjects
	 */
	public int getTotalProjects()
	{
		int count = 0;
		if (totalProjects == -1)
		{
			Set<Long> projectIDs = new LinkedHashSet<Long>();
			if(cycles == null || cycles.isEmpty())
			{
				cycles = getCycles();
			}
			
			if(cycles != null && !cycles.isEmpty())
			{
				
				for(final Cycle cycle : cycles)
				{					
					projectIDs.add(cycle.getProjectID());					
				}
			}
			if(projectIDs != null && !projectIDs.isEmpty())
			{
				count = projectIDs.size();
			}						
		}
		return count;			
	}

	/**
	 * @param totalProjects the totalProjects to set
	 */
	public void setTotalProjects(int totalProjects) {
		this.totalProjects = totalProjects;
	}

	/**
	 * @return the totalTestcases
	 */
	public int getTotalTestcases() 
	{
		int count = 0;
		if(totalTestcases == -1)
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
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				Set<Testplan> testplans = new LinkedHashSet<Testplan>();
				for(final Testrun testrun : requiredTestruns)
				{
					if(testrunService.getTestPlan(testrun.getTestrunID()) != null)
					{
						testplans.add(testrunService.getTestPlan(testrun.getTestrunID()));						
					}
				}	
				if(testplans != null & !testplans.isEmpty())
				{
					count = testplans.size();
				}				
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
			if(defect != null)
			{				
				count = defect.getRequirements().size();
			}			
		}
		return count;			
	}

	/**
	 * @param totalRequirements the totalRequirements to set
	 */
	public void setTotalRequirement(int totalRequirements) {
		this.totalRequirements = totalRequirements;
	}	

	/**
	 * @return the totalAllTestruns
	 */
	public int getTotalAllTestruns()
	{
		int count = 0;
		if (totalAllTestruns == -1)
		{
			if(allTestruns == null || allTestruns.isEmpty())
			{
				allTestruns = getAllTestruns();
			}
			if(allTestruns != null && !allTestruns.isEmpty())
			{			
				count = allTestruns.size();
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
			if(allTestruns == null || allTestruns.isEmpty())
			{
				allTestruns = getAllTestruns();
			}
			if(allTestruns != null && !allTestruns.isEmpty())
			{			
				for(final Testrun testrun : allTestruns)
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
	 * @return the totalTesters
	 */
	public int getTotalTesters() {
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
	public String getLastModifiedBy() 
	{
		if(lastModifiedBy == null)
		{			
			if(defect.getLastModifiedByUserID() == null)
			{
				lastModifiedBy = "n/a";
			}
			else
			{
				//TODO look up user name testcase.getLastModifiedByUserID()
				lastModifiedBy = "USERNAME";
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
	public Date getLastModifiedDate() 
	{
		if(lastModifiedDate == null)
		{
			if(defect.getLastModifiedDate() != null)			
			{
				lastModifiedDate = defect.getLastModifiedDate();
			}				
		}
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy()
	{
		if(createdBy == null)
		{			
			if(defect.getCreatedByUserID() == null)
			{
				createdBy = "n/a";
			}
			else
			{
				//TODO look up user name testcase.getCreatedByUserID()
				createdBy = "USERNAME";
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
	public Date getCreationDate()
	{
		if(creationDate == null)
		{
			if(defect.getCreationDate() != null)			
			{
				creationDate = defect.getCreationDate();
			}				
		}
		return creationDate;		
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the companyID
	 */
	public Long getCompanyID() 
	{
		if(defect.getCompanyID() != null)			
		{
			companyID = defect.getCompanyID();
		}	
		return companyID;
	}	

	

}