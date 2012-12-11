/**
 * 
 */
package ie.cit.cloud.testcenter.model.summary;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author byrnek1
 *
 */

public class EnvironmentSummary
{	
	private TestrunService testrunService;
	private DefectService defectService;		
	private Set<Testrun> allTestruns = new LinkedHashSet<Testrun>();
	private Set<Testrun> requiredTestruns = new LinkedHashSet<Testrun>();	
	private Set<Defect> defects = new LinkedHashSet<Defect>();
	private Set<Cycle> cycles = new LinkedHashSet<Cycle>();
	private Environment environment = new Environment();
	
	////////////////////////////////////
	
	private Long environmentID = null;    
	private String environmentName = null;
	private String environmentOS = null;	

	private int totalDefects = -1; 
	private int totalCurrentSev1s = -1;
	private int totalCurrentSev2s = -1;
	private int totalCurrentSev3s = -1;
	private int totalCurrentSev4s = -1; 

	private int totalProjects = -1;
	private int totalCycles = -1;
	private int totalTestplans = -1;
	private int totalTestcases = -1;
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

	public EnvironmentSummary() {	
	}

	public EnvironmentSummary(Environment environment, TestrunService testrunService, 
			DefectService defectService)
	{
		this.environmentID = environment.getEnvironmentID();			
		this.environment = environment;  		
		this.testrunService = testrunService;
		this.defectService = defectService;			
		this.companyID = getCompanyID();		
	}


	/**
	 * @return the allTestruns
	 */
	private Set<Testrun> getAllTestruns()
	{
		if(allTestruns == null || allTestruns.isEmpty())
		{
			if(environment.getTestruns() != null || !environment.getTestruns().isEmpty())
			{
				allTestruns = environment.getTestruns();
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
	 * @return the environmentID
	 */
	public Long getEnvironmentID() 
	{
		if(environment != null)
		{
			environmentID = environment.getEnvironmentID();
		}
		return environmentID;
	}

	/**
	 * @param environmentID the environmentID to set
	 */
	public void setEnvironmentID(Long environmentID) {
		this.environmentID = environmentID;
	}

	/**
	 * @return the environmentName
	 */
	public String getEnvironmentName()
	{
		if(environmentName == null)
		{
			if(environment != null)
			{
				environmentName = environment.getEnvironmentName();
			}
		}
		return environmentName;		
	}

	/**
	 * @param environmentName the testcaseName to set
	 */
	public void setEnvironmentName(String environmentName) {
		this.environmentName= environmentName;
	}
	
	/**
	 * @return the environmentOS
	 */
	public String getEnvironmentOS()
	{
		if(environmentOS == null)
		{
			if(environment != null)
			{
				environmentOS = environment.getEnvironmentOs();
			}
		}
		return environmentOS;		
	}

	/**
	 * @param environmentOS to set
	 */
	public void setEnvironmentOS(String environmentOS) {
		this.environmentOS= environmentOS;
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
			if(environment.getLastModifiedByUserID() == null)
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
			if(environment.getLastModifiedDate() != null)			
			{
				lastModifiedDate = environment.getLastModifiedDate();
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
			if(environment.getCreatedByUserID() == null)
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
			if(environment.getCreationDate() != null)			
			{
				creationDate = environment.getCreationDate();
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
		if(environment.getCompanyID() != null)			
		{
			companyID = environment.getCompanyID();
		}	
		return companyID;
	}	

	

}