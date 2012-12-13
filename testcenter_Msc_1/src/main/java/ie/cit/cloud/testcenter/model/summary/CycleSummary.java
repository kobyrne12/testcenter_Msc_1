/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.NoResultException;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

/**
 * @author byrnek1
 *
 */

public class CycleSummary 
{	
	private ProjectService projectService;
	private CycleService cycleService;
	private TestrunService testrunService;
	private DefectService defectService;	

	//private ProjectService projectService;
	//private Set<Project> cascadedProjects = new LinkedHashSet<Project>();
	private Set<Cycle> cascadedCycles = new LinkedHashSet<Cycle>();	

	private Set<Testrun> cascadedTestruns = new LinkedHashSet<Testrun>();
	private Set<Testrun> requiredTestruns = new LinkedHashSet<Testrun>();
	private Set<Testrun> completedTestruns = new LinkedHashSet<Testrun>();
	private Set<Testrun> incompleteTestruns = new LinkedHashSet<Testrun>();
	private Set<Defect> defects = new LinkedHashSet<Defect>();

	private Project project = new Project();
	private Cycle cycle = new Cycle();
	///////////////////////////////////

	private Long cycleID = null;	    
	private String cycleName = null;
	private String parentCycleName = null;
	private boolean parentCycle;

	private int totalChildCycles = -1;

	private String levelName = null;

	private int requiredPriority = -1;	

	private int requiredPercent = -1;

	private int totalTestrunsPassed = -1; 
	private int totalTestrunsFailed = -1; 
	private int totalTestrunsNotRun = -1; 
	private int totalTestrunsInProg = -1; 
	private int totalTestrunsDeferred = -1; 
	private int totalTestrunsBlocked = -1;
	private int totalTestrunsCompleted = -1;
	private int totalTestrunsInComplete = -1; 

	private double totalCycleEstTime = -1;
	private String cycleStartDate = null;
	private String cycleEndDate = null;

	private int totalDefectRules = -1; 
	private int totalTestHistoryRules = -1;
	private int totalCodeImpactRules = -1; 
	private int totalReqRules = -1; 	 

	private int totalDefects = -1;
	private int totalCurrentSev1s = -1;
	private int totalCurrentSev2s = -1;
	private int totalCurrentSev3s = -1;
	private int totalCurrentSev4s = -1;

	private int totalAllowedSev1s = -1;	
	private int totalAllowedSev2s = -1;	
	private int totalAllowedSev3s = -1;	 
	private int totalAllowedSev4s = -1; 	  	

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

	private String lastModifiedBy = null;
	private String lastModifiedDate = null;    
	private String createdBy = null;
	private String creationDate = null; 

	private int projectPosition = -1;

	private Long companyID = null;
	private Long projectID = null;
	private String projectName = null;

	public CycleSummary()
	{
	}
	
	public CycleSummary(Cycle cycle, String levelName, ProjectService projectService, CycleService cycleService,TestrunService testrunService, DefectService defectService )
	{
		this.cycleID = cycle.getCycleID();
		if(levelName != null)
		{
			this.levelName = levelName;
		}
		this.cycle = cycle;  	
		this.projectService = projectService; 
		this.cycleService = cycleService;
		this.testrunService = testrunService;
		this.defectService = defectService;		
	}
//	/**
//	 * @return the cycleService
//	 */
//	private CycleService getCycleService() {
//		return cycleService;
//	}
//	/**
//	 * @param cycleService the cycleService to set
//	 */
//	private void setCycleService(CycleService cycleService) {
//		this.cycleService = cycleService;
//	}
//	/**
//	 * @return the testrunService
//	 */
//	private TestrunService getTestrunService() {
//		return testrunService;
//	}
//	/**
//	 * @param testrunService the testrunService to set
//	 */
//	private void setTestrunService(TestrunService testrunService) {
//		this.testrunService = testrunService;
//	}
//	/**
//	 * @return the defectService
//	 */
//	private DefectService getDefectService() {
//		return defectService;
//	}
//	/**
//	 * @param defectService the defectService to set
//	 */
//	private void setDefectService(DefectService defectService) {
//		this.defectService = defectService;
//	}
	/**
	 * @return the cascadedCycles
	 */
	private Set<Cycle> getCascadedCycles() 
	{
		if(cascadedCycles == null || cascadedCycles.isEmpty())
		{				
			if(cycleService.getParentAndChildCycles(cycleID) != null && 
					!cycleService.getParentAndChildCycles(cycleID).isEmpty())
			{
				cascadedCycles = cycleService.getParentAndChildCycles(cycleID);		
			}
		}			
		return cascadedCycles;
	}
	/**
	 * @param cascadedCycles the cascadedCycles to set
	 */
	public void setCascadedCycles(Set<Cycle> cascadedCycles) {
		this.cascadedCycles = cascadedCycles;
	}
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
	 * @return the incompleteTestruns
	 */
	private Set<Testrun> getIncompleteTestruns()
	{
		if(incompleteTestruns == null || incompleteTestruns.isEmpty())
		{
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				for(final Testrun testrun : requiredTestruns)
				{
					if(testrun.isNotrun() || testrun.isInprogress())
					{
						incompleteTestruns.add(testrun);										
					}					
				}				
			}
		}	
		return incompleteTestruns;		
	}
//	/**
//	 * @param incompleteTestruns the incompleteTestruns to set
//	 */
//	private void setIncompleteTestruns(Set<Testrun> incompleteTestruns) {
//		this.incompleteTestruns = incompleteTestruns;
//	}
	/**
	 * @return the completedTestruns
	 */
	private Set<Testrun> getCompletedTestruns() 
	{
		if(completedTestruns == null || completedTestruns.isEmpty())
		{
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				for(final Testrun testrun : requiredTestruns)
				{
					if(testrun.isPassed() || testrun.isFailed() || testrun.isDeferred() || testrun.isBlocked())
					{
						completedTestruns.add(testrun);										
					}					
				}				
			}
		}	
		return completedTestruns;
	}
//	/**
//	 * @param completedTestruns the completedTestruns to set
//	 */
//	private void setCompletedTestruns(Set<Testrun> completedTestruns) {
//		this.completedTestruns = completedTestruns;
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
//	/**
//	 * @param defects the defects to set
//	 */
//	private void setDefects(Set<Defect> defects) {
//		this.defects = defects;
//	}

	/**
	 * @return the cycleID
	 */
	public Long getCycleID() {
		return cycleID;
	}
	/**
	 * @param cycleID the cycleID to set
	 */
	public void setCycleID(Long cycleID) {
		this.cycleID = cycleID;
	}
	/**
	 * @return the cycleName
	 */
	public String getCycleName() 
	{
		if(cycleName == null)
		{
			if(cycle != null)
			{
				cycleName = cycle.getCycleName();
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
	 * @return the parentCycle
	 */
	public boolean isParentCycle() 
	{
		return cycle.isParent();		
	}
	/**
	 * @param parentCycle the parentCycle to set
	 */
	public void setParentCycle(boolean parentCycle) {
		this.parentCycle = parentCycle;
	}
	/**
	 * @return the totalChildCycles
	 */
	public int getTotalChildCycles() 
	{
		int count = 0;
		if(totalChildCycles == -1)
		{		
			if(cycle.isParent())
			{   
				try{count = cycleService.getAllChildCycles(cycleID).size();}
				catch(NoResultException nre){}
			}
		}
		return count;
	}
	/**
	 * @param totalChildCycles the totalChildCycles to set
	 */
	public void setTotalChildCycles(int totalChildCycles) {
		this.totalChildCycles = totalChildCycles;
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
	 * @return the requiredPriority
	 */
	public int getRequiredPriority()
	{		
		if(requiredPriority == -1)
		{	
			return cycle.getRequiredPriority();
		}
		return requiredPriority;
	}
	/**
	 * @param requiredPriority the requiredPriority to set
	 */
	public void setRequiredPriority(int requiredPriority) {
		this.requiredPriority = requiredPriority;
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
	 * @return the totalTestrunsPassed
	 */
	public int getTotalTestrunsPassed() 
	{
		int count = 0;
		if(totalTestrunsPassed == -1)
		{
			if(completedTestruns == null || completedTestruns.isEmpty() )
			{
				completedTestruns = getCompletedTestruns();
			}
			if(completedTestruns != null && !completedTestruns.isEmpty())
			{
				for(final Testrun testrun : completedTestruns)
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
	 * @param totalTestrunsPassed the totalTestrunsPassed to set
	 */
	public void setTotalTestrunsPassed(int totalTestrunsPassed) {
		this.totalTestrunsPassed = totalTestrunsPassed;
	}
	/**
	 * @return the totalTestrunsFailed
	 */
	public int getTotalTestrunsFailed() 
	{
		int count = 0;
		if(totalTestrunsFailed == -1)
		{
			if(completedTestruns == null || completedTestruns.isEmpty() )
			{
				completedTestruns = getCompletedTestruns();
			}
			if(completedTestruns != null && !completedTestruns.isEmpty())
			{
				for(final Testrun testrun : completedTestruns)
				{
					if(testrun.isFailed())
					{
						count++;
					}					
				}	
			}
		}
		return count;				
	}
	/**
	 * @param totalTestrunsFailed the totalTestrunsFailed to set
	 */
	public void setTotalTestrunsFailed(int totalTestrunsFailed) {
		this.totalTestrunsFailed = totalTestrunsFailed;
	}
	/**
	 * @return the totalTestrunsDeferred
	 */
	public int getTotalTestrunsDeferred()
	{
		int count = 0;
		if(totalTestrunsDeferred == -1)
		{
			if(completedTestruns == null || completedTestruns.isEmpty() )
			{
				completedTestruns = getCompletedTestruns();
			}
			if(completedTestruns != null && !completedTestruns.isEmpty())
			{
				for(final Testrun testrun : completedTestruns)
				{
					if(testrun.isDeferred())
					{
						count++;
					}					
				}	
			}
		}
		return count;			
	}
	/**
	 * @param totalTestrunsDeferred the totalTestrunsDeferred to set
	 */
	public void setTotalTestrunsDeferred(int totalTestrunsDeferred) {
		this.totalTestrunsDeferred = totalTestrunsDeferred;
	}
	/**
	 * @return the totalTestrunsBlocked
	 */
	public int getTotalTestrunsBlocked() 
	{
		int count = 0;
		if(totalTestrunsBlocked == -1)
		{
			if(completedTestruns == null || completedTestruns.isEmpty() )
			{
				completedTestruns = getCompletedTestruns();
			}
			if(completedTestruns != null && !completedTestruns.isEmpty())
			{
				for(final Testrun testrun : completedTestruns)
				{
					if(testrun.isBlocked())
					{
						count++;
					}					
				}	
			}
		}
		return count;		
	}
	/**
	 * @param totalTestrunsBlocked the totalTestrunsBlocked to set
	 */
	public void setTotalTestrunsBlocked(int totalTestrunsBlocked) {
		this.totalTestrunsBlocked = totalTestrunsBlocked;
	}
	/**
	 * @return the totalTestrunsNotRun
	 */
	public int getTotalTestrunsNotRun() 
	{
		int count = 0;
		if(totalTestrunsNotRun == -1)
		{
			if(incompleteTestruns == null || incompleteTestruns.isEmpty() )
			{
				incompleteTestruns = getIncompleteTestruns();
			}
			if(incompleteTestruns != null && !incompleteTestruns.isEmpty())
			{
				for(final Testrun testrun : incompleteTestruns)
				{
					if(testrun.isNotrun())
					{
						count++;
					}					
				}	
			}
		}
		return count;	
	}
	/**
	 * @param totalTestrunsNotRun the totalTestrunsNotRun to set
	 */
	public void setTotalTestrunsNotRun(int totalTestrunsNotRun) {
		this.totalTestrunsNotRun = totalTestrunsNotRun;
	}
	/**
	 * @return the totalTestrunsInProg
	 */
	public int getTotalTestrunsInProg() 
	{
		int count = 0;
		if(totalTestrunsInProg == -1)
		{
			if(incompleteTestruns == null || incompleteTestruns.isEmpty() )
			{
				incompleteTestruns = getIncompleteTestruns();
			}
			if(incompleteTestruns != null && !incompleteTestruns.isEmpty())
			{
				for(final Testrun testrun : incompleteTestruns)
				{
					if(testrun.isInprogress())
					{
						count++;
					}					
				}	
			}
		}
		return count;			
	}
	/**
	 * @param totalTestrunsInProg the totalTestrunsInProg to set
	 */
	public void setTotalTestrunsInProg(int totalTestrunsInProg) {
		this.totalTestrunsInProg = totalTestrunsInProg;
	}

	/**
	 * @return the totalTestrunsCompleted
	 */
	public int getTotalTestrunsCompleted() 
	{
		int count = 0;
		if(totalTestrunsCompleted == -1)
		{
			if(completedTestruns == null || completedTestruns.isEmpty() )
			{
				completedTestruns = getCompletedTestruns();
			}
			if(completedTestruns != null && !completedTestruns.isEmpty())
			{
				count = completedTestruns.size();	
			}
		}
		return count;
	}
	/**
	 * @param totalTestrunsCompleted the totalTestrunsCompleted to set
	 */
	public void setTotalTestrunsCompleted(int totalTestrunsCompleted) {
		this.totalTestrunsCompleted = totalTestrunsCompleted;
	}
	/**
	 * @return the totalTestrunsInComplete
	 */
	public int getTotalTestrunsInComplete() 
	{
		int count = 0;
		if(totalTestrunsInComplete == -1)
		{
			if(incompleteTestruns == null || incompleteTestruns.isEmpty() )
			{
				incompleteTestruns = getIncompleteTestruns();
			}
			if(incompleteTestruns != null && !incompleteTestruns.isEmpty())
			{
				count = incompleteTestruns.size();				
			}
		}
		return count;				
	}
	/**
	 * @param totalTestrunsInComplete the totalTestrunsInComplete to set
	 */
	public void setTotalTestrunsInComplete(int totalTestrunsInComplete) {
		this.totalTestrunsInComplete = totalTestrunsInComplete;
	}
	/**
	 * @return the totalCycleEstTime
	 */
	public double getTotalCycleEstTime() 
	{
		double estTime = 0.00;
		if(totalCycleEstTime == -1)
		{
			if(requiredTestruns == null || requiredTestruns.isEmpty())
			{
				requiredTestruns = getRequiredTestruns();
			}
			if(requiredTestruns != null && !requiredTestruns.isEmpty())
			{
				for(final Testrun testrun : requiredTestruns)
				{
					if(testrun.getEstimatedTime() != null && testrun.getEstimatedTime() != -1)
					{
						estTime += testrun.getEstimatedTime();											
					}					
				}				
			}
		}	
		return estTime;		
	}
	/**
	 * @param totalCycleEstTime the totalCycleEstTime to set
	 */
	public void setTotalCycleEstTime(double totalCycleEstTime) {
		this.totalCycleEstTime = totalCycleEstTime;
	}
	/**
	 * @return the cycleStartDate
	 */
	public String getCycleStartDate() 
	{		
		if(cycleStartDate == null)
		{
			if(cycle.getCycleStartDate() == null)
			{
				cycleStartDate = "n/a";
			}
			else
			{
				cycleStartDate = cycle.getCycleStartDate();	
			}				
		}
		return cycleStartDate;	
	}
	/**
	 * @param cycleStartDate the cycleStartDate to set
	 */
	public void setCycleStartDate(String cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}
	/**
	 * @return the cycleEndDate
	 */
	public String getCycleEndDate() 
	{
		if(cycleEndDate == null)
		{
			if(cycle.getCycleEndDate() == null)
			{
				cycleEndDate = "n/a";
			}
			else
			{
				cycleEndDate = cycle.getCycleEndDate();	
			}				
		}
		return cycleStartDate;		
	}
	/**
	 * @param cycleEndDate the cycleEndDate to set
	 */
	public void setCycleEndDate(String cycleEndDate) {
		this.cycleEndDate = cycleEndDate;
	}
	/**
	 * @return the totalDefectRules
	 */
	public int getTotalDefectRules()
	{		
		int count = 0;
		if(totalDefectRules == -1)
		{
			if(cascadedCycles == null || cascadedCycles.isEmpty())
			{
				cascadedCycles = getCascadedCycles(); 
			}
			if(cascadedCycles != null && !cascadedCycles.isEmpty())
			{
				for(final Cycle cascadedCycle : cascadedCycles)
				{
					if(cascadedCycle.getDefectRules() != null || !cascadedCycle.getDefectRules().isEmpty())
					{
						count += cascadedCycle.getDefectRules().size();
					}	
				}
			}			
		}			
		return count;
	}
	/**
	 * @param totalDefectRules the totalDefectRules to set
	 */
	public void setTotalDefectRules(int totalDefectRules) {
		this.totalDefectRules = totalDefectRules;
	}
	/**
	 * @return the totalTestHistoryRules
	 */
	public int getTotalTestHistoryRules() 
	{
		int count = 0;
		if(totalTestHistoryRules == -1)
		{
			if(cascadedCycles == null || cascadedCycles.isEmpty())
			{
				cascadedCycles = getCascadedCycles(); 
			}
			if(cascadedCycles != null && !cascadedCycles.isEmpty())
			{
				for(final Cycle cascadedCycle : cascadedCycles)
				{
					if(cascadedCycle.getTesthistoryRules() != null || !cascadedCycle.getTesthistoryRules().isEmpty())
					{
						count += cascadedCycle.getTesthistoryRules().size();
					}	
				}
			}			
		}			
		return count;		
	}
	/**
	 * @param totalTestHistoryRules the totalTestHistoryRules to set
	 */
	public void setTotalTestHistoryRules(int totalTestHistoryRules) {
		this.totalTestHistoryRules = totalTestHistoryRules;
	}
	/**
	 * @return the totalCodeImpactRules
	 */
	public int getTotalCodeImpactRules()
	{
		int count = 0;
		if(totalCodeImpactRules == -1)
		{
			if(cascadedCycles == null || cascadedCycles.isEmpty())
			{
				cascadedCycles = getCascadedCycles(); 
			}
			if(cascadedCycles != null && !cascadedCycles.isEmpty())
			{
				for(final Cycle cascadedCycle : cascadedCycles)
				{
					if(cascadedCycle.getChangeImpactRules() != null || !cascadedCycle.getChangeImpactRules().isEmpty())
					{
						count += cascadedCycle.getChangeImpactRules().size();
					}	
				}
			}			
		}			
		return count;			
	}
	/**
	 * @param totalCodeImpactRules the totalCodeImpactRules to set
	 */
	public void setTotalCodeImpactRules(int totalCodeImpactRules) {
		this.totalCodeImpactRules = totalCodeImpactRules;
	}
	/**
	 * @return the totalReqRules
	 */
	public int getTotalReqRules() 
	{
		int count = 0;
		if(totalReqRules == -1)
		{
			if(cascadedCycles == null || cascadedCycles.isEmpty())
			{
				cascadedCycles = getCascadedCycles(); 
			}
			if(cascadedCycles != null && !cascadedCycles.isEmpty())
			{
				for(final Cycle cascadedCycle : cascadedCycles)
				{
					if(cascadedCycle.getRequirementRules() != null || !cascadedCycle.getRequirementRules().isEmpty())
					{
						count += cascadedCycle.getRequirementRules().size();
					}	
				}
			}			
		}			
		return count;		
	}
	/**
	 * @param totalReqRules the totalReqRules to set
	 */
	public void setTotalReqRules(int totalReqRules) {
		this.totalReqRules = totalReqRules;
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
	 * @return the totalAllowedSev1s
	 */
	public int getTotalAllowedSev1s() 
	{
		int count = 0;
		if(totalAllowedSev1s == -1)
		{
			if(project == null)
			{
				setProject(getProject());				
			}
			if(project != null)
			{
				count = project.getAllowedSev1();	
			}
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
	 * @return the totalAllowedSev2s
	 */
	public int getTotalAllowedSev2s() 
	{
		int count = 0;
		if(totalAllowedSev2s == -1)
		{
			if(project == null)
			{
				setProject(getProject());	
			}
			if(project != null)
			{
				count = project.getAllowedSev2();	
			}
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
	 * @return the totalAllowedSev3s
	 */
	public int getTotalAllowedSev3s()
	{
		int count = 0;
		if(totalAllowedSev3s == -1)
		{
			if(project == null)
			{
				setProject(getProject());	
			}
			if(project != null)
			{
				count = project.getAllowedSev3();	
			}
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
	 * @return the totalAllowedSev4s
	 */
	public int getTotalAllowedSev4s()
	{
		int count = 0;
		if(totalAllowedSev4s == -1)
		{
			if(project == null)
			{
				setProject(getProject());	
			}
			if(project != null)
			{
				count = project.getAllowedSev4();	
			}
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
			if(cascadedTestruns == null || cascadedTestruns.isEmpty())
			{
				cascadedTestruns = getCascadedTestruns();
			}
			if(cascadedTestruns != null && !cascadedTestruns.isEmpty())
			{
				Set<Testplan> testplans = new LinkedHashSet<Testplan>();
				for(final Testrun testrun : cascadedTestruns)
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
			if(cycle.getLastModifiedBy() == null)
			{
				lastModifiedBy = "n/a";
			}
			else
			{
				lastModifiedBy = cycle.getLastModifiedBy();
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
	public String getLastModifiedDate()
	{
		if(lastModifiedDate == null)
		{
			if(cycle.getLastModifiedDate() == null)
			{
				lastModifiedDate = "n/a";
			}
			else
			{
				lastModifiedDate = cycle.getLastModifiedDate();
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
	public String getCreatedBy() 
	{
		if(createdBy == null)
		{
			if(cycle.getCreatedBy() == null)
			{
				createdBy = "n/a";
			}
			else
			{
				createdBy = cycle.getCreatedBy();
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
			if(cycle.getCreationDate() == null)
			{
				creationDate = "n/a";
			}
			else
			{
				creationDate = cycle.getCreationDate();
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
	 * @return the projectPosition
	 */
	public int getProjectPosition() 
	{
		if(cycle != null)
		{
			return cycle.getProjectPosition();
		}		
		return projectPosition;
	}
	/**
	 * @param projectPosition the projectPosition to set
	 */
	public void setProjectPosition(int projectPosition) {
		this.projectPosition = projectPosition;
	}
	/**
	 * @return the companyID
	 */
	public Long getCompanyID() 
	{
		if(companyID == null)
		{
			if(projectID == null)
			{
				projectID = getProjectID();
			}
			if(projectID != null)
			{
				if(projectService.getProject(projectID) != null)
				{
					companyID = projectService.getProject(projectID).getCompanyID();
				}
			}
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
	 * @return the projectID
	 */
	public Long getProjectID() 
	{
		if(cycle != null)
		{
			return cycle.getProjectID();
		}
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
			if(projectID == null)
			{
				projectID = getProjectID();
			}
			if(projectID != null)
			{
				if(projectService.getProject(projectID) != null)
				{
					projectName = projectService.getProject(projectID).getProjectName();
				}
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
	 * @return the project
	 */
	private Project getProject() 
	{
		if(project == null)
		{
			if(projectID == null)
			{
				projectID = getProjectID();
			}
			if(projectID != null)
			{
				if(projectService.getParentProject(projectID) != null)
				{
					project = projectService.getParentProject(projectID);
				}
			}
		}
		return project;
	}
	/**
	 * @param project the project to set
	 */
	private void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the parentCycleName
	 */
	public String getParentCycleName() 
	{
		if(parentCycleName == null)
		{
			if(cycle.getParentID() != null)
			{
				if(cycleService.getCycle(cycle.getParentID()) != null)
				{
					parentCycleName = cycleService.getCycle(cycle.getParentID()).getCycleName();
				}
			}
		}
		return parentCycleName;
	}
		

	/**
	 * @param parentCycleName the parentCycleName to set
	 */
	public void setParentCycleName(String parentCycleName) {
		this.parentCycleName = parentCycleName;
	}


}