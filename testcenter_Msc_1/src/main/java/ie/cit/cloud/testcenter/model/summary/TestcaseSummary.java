/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.service.defect.DefectService;
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

public class TestcaseSummary
{	

	//private ProjectService projectService;
	//private CycleService cycleService;
	//private TestcaseService testcaseService;
	private TestrunService testrunService;
	private DefectService defectService;	
	private TestplanService testplanService;

	private Set<Testrun> allTestruns = new LinkedHashSet<Testrun>();
	private Set<Testrun> requiredTestruns = new LinkedHashSet<Testrun>();
	//private Set<Testrun> completedTestruns = new LinkedHashSet<Testrun>();
	//private Set<Testrun> incompleteTestruns = new LinkedHashSet<Testrun>();
	private Set<Defect> defects = new LinkedHashSet<Defect>();

	//private Project project = new Project();
	//private Cycle cycle = new Cycle();
	private Testcase testcase = new Testcase();
	////////////////////////////////////
	private Long testcaseID = null;    
	private String testcaseName = null;
	private String testplanName = null;
	private String testplanSection = null;
	private Integer testplanOrderNum = -1;

	private String levelName = null;
	private String stage = null; // Draft/Ready For Review / Approved	
	private Double estimatedTime = null; // Draft/Ready For Review / Approved

	private int totalDefects = -1; 
	private int totalCurrentSev1s = -1;
	private int totalCurrentSev2s = -1;
	private int totalCurrentSev3s = -1;
	private int totalCurrentSev4s = -1; 

	private int totalCycles = -1;
	private int totalProjects = -1;
	private int totalEnvironments = -1;
	private int totalRequirements = -1;

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

	private String customObject1;
	private String customObject2;
	private String customObject3;
	private String customObject4;
	private String customObject5;

	public TestcaseSummary() {	
	}

	public TestcaseSummary(Testcase testcase, String levelName, TestrunService testrunService, DefectService defectService,TestplanService testplanService )
	{
		this.testcaseID = testcase.getTestcaseID();
		if(levelName != null)
		{
			this.levelName = levelName;
		}		
		this.testcase = testcase;  	
		//this.projectService = projectService; 
		//this.cycleService = cycleService;
		this.testrunService = testrunService;
		this.defectService = defectService;		
		this.testplanService = testplanService;
		
		this.companyID = getCompanyID();
		
	}


	/**
	 * @return the allTestruns
	 */
	private Set<Testrun> getAllTestruns()
	{
		if(allTestruns == null || allTestruns.isEmpty())
		{
			if(testcase.getTestruns() != null || !testcase.getTestruns().isEmpty())
			{
				allTestruns = testcase.getTestruns();
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
						if(defects == null || defects.isEmpty())
						{
							defects = testrun.getDefects();
						}
						else
						{
							defects.addAll(testrun.getDefects());
						}						
					}					
				}				
			}
		}	
		return defects;		
	}

	/**
	 * @param defects the defects to set
	 */
	private void setDefects(Set<Defect> defects) {
		this.defects = defects;
	}

	/**
	 * @return the testcaseID
	 */
	public Long getTestcaseID() {
		return testcaseID;
	}

	/**
	 * @param testcaseID the testcaseID to set
	 */
	public void setTestcaseID(Long testcaseID) {
		this.testcaseID = testcaseID;
	}

	/**
	 * @return the testcaseName
	 */
	public String getTestcaseName()
	{
		if(testcaseName == null)
		{
			if(testcase != null)
			{
				testcaseName = testcase.getTestcaseName();
			}
		}
		return testcaseName;		
	}

	/**
	 * @param testcaseName the testcaseName to set
	 */
	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
	}

	/**
	 * @return the testplanName
	 */
	public String getTestplanName()
	{
		if(testplanName == null)
		{
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				testplanName = testplanService.getTestplan(testcase.getTestplanID()).getTestplanName();
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
	 * @return the testplanSection
	 */
	public String getTestplanSection() 
	{
		if(testplanSection == null)
		{
			if(testplanService.getTestplanSection(testcase.getTestplanSectionID()) != null)
			{
				testplanSection = testplanService.getTestplanSection(testcase.getTestplanSectionID()).getTestplanSectionName();
			}
		}
		return testplanSection;
	}

	/**
	 * @param testplanSection the testplanSection to set
	 */
	public void setTestplanSection(String testplanSection) {
		this.testplanSection = testplanSection;
	}

	/**
	 * @return the testplanOrderNum
	 */
	public int getTestplanOrderNum() 
	{
		if(testcaseName == null)
		{
			if(testcase.getTestplanOrderNum() != -1)
			{
				testplanOrderNum = testcase.getTestplanOrderNum();
			}
		}		
		return testplanOrderNum;
	}

	/**
	 * @param testplanOrderNum the testplanOrderNum to set
	 */
	public void setTestplanOrderNum(int testplanOrderNum) {
		this.testplanOrderNum = testplanOrderNum;
	}

	/**
	 * @return the levelName
	 */
	public String getLevelName()
	{
		return levelName;
	}

	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * @return the stage
	 */
	public String getStage() 
	{
		if(stage == null)
		{
			if(testcase.getStage() != null)
			{
				stage = testcase.getStage();
			}
		}			
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}

	/**
	 * @return the estimatedTime
	 */
	public Double getEstimatedTime() 
	{
		if(estimatedTime == null)
		{
			if(testcase.getEstimatedTime() != null)
			{
				estimatedTime = testcase.getEstimatedTime();
			}
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
	 * @return the totalCycles
	 */
	public int getTotalCycles() 
	{
		int count = 0;
		if (totalCycles == -1)
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
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				if(testplanService.getTestplan(testcase.getTestplanID()).getProjects() != null &&
						!testplanService.getTestplan(testcase.getTestplanID()).getProjects().isEmpty())
				{
					count = testplanService.getTestplan(testcase.getTestplanID()).getProjects().size();
				}
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
			if(testcase.getLastModifiedByUserID() == null)
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
			if(testcase.getLastModifiedDate() != null)			
			{
				lastModifiedDate = testcase.getLastModifiedDate();
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
			if(testcase.getCreatedByUserID() == null)
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
			if(testcase.getCreationDate() != null)			
			{
				creationDate = testcase.getCreationDate();
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
		if(testcase.getCompanyID() != null)			
		{
			companyID = testcase.getCompanyID();
		}	
		return companyID;
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


}