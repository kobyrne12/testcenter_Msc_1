/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class TestrunSummary
{		
	private TestcaseService testcaseService;
	private TestrunService testrunService;
	private DefectService defectService;		
	private Set<Defect> defects = new LinkedHashSet<Defect>();
	private Testrun testrun = new Testrun();
	
	////////////////////////////////////
	
	private Long testrunID = null;    
	private String testrunName = null;
	private String testcaseName = null;
	private String testplanName = null;

	private int testrunRequiredPriority = -1;
	private int testrunRecommendedPriority = -1;
	
	private String state = null; //passed/failed...etc	
	private String levelName = null;	
	private Double estimatedTime = null; // Draft/Ready For Review / Approved

	private int totalDefects = -1; 
	private int totalCurrentSev1s = -1;
	private int totalCurrentSev2s = -1;
	private int totalCurrentSev3s = -1;
	private int totalCurrentSev4s = -1; 

	private String cycleName = null;
	private String projectName = null;	
	private int totalEnvironments = -1;
	private int totalRequirements = -1;

	private int totalTesters = -1;
	private int totalSeniorTesters = -1;
	private int totalDevelopers = -1;
	private int totalSeniorDevelopers = -1;

	private String lastModifiedBy = null;
	private Date lastModifiedDate = null;    
	private String createdBy = null;
	private Date creationDate = null;

	private Long companyID = null;

	public TestrunSummary() {	
	}

	public TestrunSummary(Testrun testrun, String levelName, TestcaseService testcaseService, DefectService defectService,TestrunService testrunService )
	{
		this.testrunID = testrun.getTestrunID();
		if(levelName != null)
		{
			this.levelName = levelName;
		}		
		this.testrun = testrun;  	
		//this.projectService = projectService; 
		//this.cycleService = cycleService;
		this.testcaseService = testcaseService;
		this.defectService = defectService;		
		this.testrunService = testrunService;
		
		this.companyID = getCompanyID();
		
	}

	/**
	 * @return the testrunID
	 */
	public Long getTestrunID() {
		return testrunID;
	}

	/**
	 * @param testrunID the testrunID to set
	 */
	public void setTestrunID(Long testrunID) {
		this.testrunID = testrunID;
	}
	/**
	 * @return the testrunName
	 */
	public String getTestrunName() 
	{
		if(testrunName == null)
		{
			return testrun.getTestrunName();
		}
		return testrunName;
	}

	/**
	 * @param testrunName the testrunName to set
	 */
	public void setTestrunName(String testrunName) 
	{
		this.testrunName = testrunName;
	}
	/**
	 * @return the testcaseName
	 */
	public String getTestcaseName() 
	{
		if(testcaseName == null)
		{
			if(testrun != null)
			{
				testcaseName = testcaseService.getTestcase(testrun.getTestcaseID()).getTestcaseName();
			}
		}
		return testcaseName;
	}

	/**
	 * @param testcaseName the testcaseName to set
	 */
	public void setTestcaseName(String testcaseName) 
	{
		this.testcaseName = testcaseName;
	}

	/**
	 * @return the testplanName
	 */
	public String getTestplanName() 
	{
		if(testplanName == null)
		{
			if(testrun != null)
			{
				testplanName = testcaseService.getTestcaseTestplanName(testrun.getTestcaseID());
			}
		}
		return testplanName;
	}

	/**
	 * @param testplanName the testplanName to set
	 */
	public void setTestplanName(String testplanName) {
		this.testplanName = testplanName;
	}

	/**
	 * @return the state
	 */
	public String getState()
	{
		if(state == null)
		{
			if(testrun.isPassed())
			{
				return "Passed";
			}
			else if(testrun.isFailed())
			{
				return "Failed";
			}
			else if(testrun.isDeferred())
			{
				return "Deferred";
			}
			else if(testrun.isBlocked())
			{
				return "Blocked";
			}
			else if(testrun.isNotrun())
			{
				return "NotRun";
			}
			else if(testrun.isInprogress())
			{
				return "Inprogress";
			}
		}
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * @return the estimatedTime
	 */
	public Double getEstimatedTime()
	{
		if(estimatedTime == null)
		{
			return testrun.getEstimatedTime();
		}
		return estimatedTime;
	}

	/**
	 * @param estimatedTime the estimatedTime to set
	 */
	public void setEstimatedTime(Double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * @return the defects
	 */
	private Set<Defect> getDefects() 
	{		
		if(defects == null || defects.isEmpty())
		{
			if(testrun.getDefects() != null && !testrun.getDefects().isEmpty() )
			{
				return testrun.getDefects();
			}			
		}				
		return defects;
	}
	
	/**
	 * @return the totalDefects
	 */
	public int getTotalDefects() 
	{
		int count = 0;
		if(totalDefects == -1)
		{
			if(testrun.getDefects() != null && !testrun.getDefects().isEmpty() )
			{
				count = testrun.getDefects().size();
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
	 * @return the totalEnvironments
	 */
	public int getTotalEnvironments()
	{
		int count = 0;
		if(totalEnvironments == -1)
		{
			if(testrun.getEnvironments() != null && !testrun.getEnvironments().isEmpty() )
			{
				count = testrun.getEnvironments().size();
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
			if(testrun.getRequirements() != null && !testrun.getRequirements().isEmpty() )
			{
				count = testrun.getRequirements().size();
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
			if(testrun.getLastModifiedByUserID() == null)
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
			if(testrun.getLastModifiedDate() != null)			
			{
				lastModifiedDate = testrun.getLastModifiedDate();
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
			if(testrun.getCreatedByUserID() == null)
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
			if(testrun.getCreationDate() != null)			
			{
				creationDate = testrun.getCreationDate();
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
		if(testcaseService.getTestcase(testrun.getTestcaseID()).getCompanyID() != null)			
		{
			companyID = testcaseService.getTestcase(testrun.getTestcaseID()).getCompanyID();
		}	
		return companyID;
	}



	/**
	 * @return the cycleName
	 */
	public String getCycleName()
	{
		if(cycleName == null)
		{
			if(testrunService.getCycle(testrunID).getCycleName() != null)
			{
				cycleName = testrunService.getCycle(testrunID).getCycleName();
			}
		}
		return cycleName;
	}

	/**
	 * @param cycleName the cycleName to set
	 */
	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() 
	{
		if(projectName == null)
		{
			if(testrunService.getProject(testrunID).getProjectName() != null)
			{
				projectName = testrunService.getProject(testrunID).getProjectName();
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
	 * @return the testrunRequiredPriority
	 */
	public int getTestrunRequiredPriority() 
	{
		if(testrunRequiredPriority == -1)
		{
			return testrun.getPriority();
		}		
		return testrunRequiredPriority;		
	}

	/**
	 * @param testrunRequiredPriority the testrunRequiredPriority to set
	 */
	public void setTestrunRequiredPriority(int testrunRequiredPriority) {
		this.testrunRequiredPriority = testrunRequiredPriority;
	}

	/**
	 * @return the testrunRecommendedPriority 
	 */
	public int getTestrunRecommendedPriority() 
	{
		if(testrunRecommendedPriority == -1)
		{
			return testrun.getRecommendedPriority();
		}		
		return testrunRecommendedPriority;
	}

	/**
	 * @param testrunRecommendedPriority the testrunRecommendedPriority to set
	 */
	public void setTestrunRecommendedPriority(int testrunRecommendedPriority) {
		this.testrunRecommendedPriority = testrunRecommendedPriority;
	}	


}