/**
 * 
 */
package ie.cit.cloud.testcenter.service.requirement;

/**
 * @author byrnek1
 *
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.RequirementSummary;
import ie.cit.cloud.testcenter.model.summary.RequirementSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestrunSummary;
import ie.cit.cloud.testcenter.model.summary.TestrunSummaryList;
import ie.cit.cloud.testcenter.respository.requirement.RequirementRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequirementServiceImpl implements RequirementService {

	@Autowired
	@Qualifier("hibernateRequirementRespository")
	RequirementRepository requirementRepo;      
	
	@Autowired
	TestrunService testrunService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ProjectService projectService;
	@Autowired
	CycleService cycleService;
	@Autowired
	TestcaseService testcaseService;	
	@Autowired
	TestplanService testplanService;
	@Autowired
	DefectService defectService;	
	@Autowired
	EnvironmentService environmentService;
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Requirement getRequirement(Long requirementID) {
		try{
			return requirementRepo.findById(requirementID);						
		}
		catch(NoResultException nre)			
		{	
			return null;
		}	
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Requirement getRequirementByName(String requirementName) {
		return requirementRepo.findByName(requirementName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewRequirement(Requirement requirement) {
		requirementRepo.create(requirement);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Requirement requirement) {
		requirementRepo.update(requirement);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(Long requirementID) {
		requirementRepo.delete(getRequirement(requirementID));
	}
	////////////////////////
	public Set<Testrun> getAllTestRuns(Long requirementID) 
	{
		Requirement requirement = getRequirement(requirementID);
		if(requirement == null)
		{
			return null;
		}			
		if(requirement.getTestruns() == null || requirement.getTestruns().isEmpty())
		{
			return null;
		}
		return requirement.getTestruns();			
	} 

	public Set<Testrun> getCompulsoryTestRuns(Long requirementID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(requirementID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> compulsoryTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.isRequired(testrun.getTestrunID()))
			{
				compulsoryTestruns.add(testrun);
			}
		}		
		return compulsoryTestruns;		
	}	

	public Set<Testrun> getOptionalTestRuns(Long requirementID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(requirementID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}			
		Set<Testrun> optionalTestruns = new HashSet<Testrun>();
		for(final Testrun testrun : allTestruns)
		{	
			if(!testrunService.isRequired(testrun.getTestrunID()))
			{
				optionalTestruns.add(testrun);
			}
		}		
		return optionalTestruns;		
	}

	public Set<Testcase> getAllTestCases(Long requirementID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(requirementID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Testcase> allTestcases = new HashSet<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}

	public Set<Testcase> getCompulsoryTestCases(Long requirementID)
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Testcase> allTestcases = new HashSet<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}

	public Set<Testcase> getOptionalTestCases(Long requirementID)
	{
		Set<Testrun> allTestruns = getOptionalTestRuns(requirementID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Testcase> allTestcases = new HashSet<Testcase>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testcaseService.getTestcase(testrun.getTestcaseID()) != null)
			{
				allTestcases.add(testcaseService.getTestcase(testrun.getTestcaseID()));	
			}
		}		
		return allTestcases;
	}

	public Set<Testplan> getAllTestPlans(Long requirementID)
	{		
		Set<Testcase> allTestcases = getAllTestCases(requirementID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Set<Testplan> allTestplans = new HashSet<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}

	public Set<Testplan> getCompulsoryTestPlans(Long requirementID)
	{		
		Set<Testcase> allTestcases = getCompulsoryTestCases(requirementID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Set<Testplan> allTestplans = new HashSet<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}

	public Set<Testplan> getOptionalTestPlans(Long requirementID)
	{		
		Set<Testcase> allTestcases = getOptionalTestCases(requirementID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}	
		Set<Testplan> allTestplans = new HashSet<Testplan>();
		for(final Testcase testcase : allTestcases)
		{				
			if(testplanService.getTestplan(testcase.getTestplanID()) != null)
			{
				allTestplans.add(testplanService.getTestplan(testcase.getTestplanID()));	
			}
		}		
		return allTestplans;
	}

	public Set<Cycle> getCycles(Long requirementID) {
		Set<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Cycle> cycles = new HashSet<Cycle>();
		for(final Testrun testrun : allTestruns)
		{				
			if(testrunService.getCycle(testrun.getTestrunID()) != null)
			{
				cycles.add(testrunService.getCycle(testrun.getTestrunID()));
			}				
		}		
		return cycles;
	}

	public Set<Project> getProjects(Long requirementID)
	{
		Set<Cycle> cycles =  getCycles(requirementID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}	
		Set<Project> projects = new HashSet<Project>();
		for(final Cycle cycle : cycles)
		{	
			try{
				projects.add(projectService.getProject(cycle.getProjectID()));
			}catch(NoResultException nre)
			{}				
		}		
		return projects;
	}

	public Set<Environment> getEnvironments(Long requirementID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(requirementID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Environment> environments = new HashSet<Environment>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getEnvironments() != null && !testrun.getEnvironments().isEmpty())			
			{
				environments.addAll(testrun.getEnvironments());
			}			
		}
		return environments;
	}	

	public Set<Defect> getCascadedAllDefects(Long requirementID)
	{
		Requirement requirement = getRequirement(requirementID);
		if(requirement == null)
		{
			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();
		if(requirement.getDefects() != null && !requirement.getDefects().isEmpty())			
		{
			defects.addAll(requirement.getDefects());
		}
		Set<Testrun> allTestruns = getAllTestRuns(requirementID);
		if(allTestruns != null && !allTestruns.isEmpty())
		{
			for(final Testrun testrun : allTestruns)
			{	
				if(testrunService.getCascadedAllDefects(testrun.getTestrunID()) != null &&
						!testrunService.getCascadedAllDefects(testrun.getTestrunID()).isEmpty())
				{
					defects.addAll(testrunService.getCascadedAllDefects(testrun.getTestrunID()));
				}							
			}			
		}		
		return defects;				
	}
	public Set<Defect> getCascadedSev1Defects(Long testrunID) 
	{		
		Set<Defect> allDefects = getCascadedAllDefects(testrunID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Set<Defect> sev1defects = new HashSet<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev1(defect.getDefectID()))
			{
				sev1defects.add(defect);						
			}
		}	
		return sev1defects;
	}
	public Set<Defect> getCascadedSev2Defects(Long testrunID) 
	{		
		Set<Defect> allDefects = getCascadedAllDefects(testrunID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Set<Defect> sev2defects = new HashSet<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev2(defect.getDefectID()))
			{
				sev2defects.add(defect);						
			}
		}	
		return sev2defects;
	}
		
	public Set<Defect> getCascadedSev3Defects(Long testrunID) 
	{		
		Set<Defect> allDefects = getCascadedAllDefects(testrunID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Set<Defect> sev3defects = new HashSet<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev3(defect.getDefectID()))
			{
				sev3defects.add(defect);						
			}
		}	
		return sev3defects;
	}	
	public Set<Defect> getCascadedSev4Defects(Long testrunID) 
	{		
		Set<Defect> allDefects = getCascadedAllDefects(testrunID);		
		if(allDefects == null || allDefects.isEmpty())
		{
			return null;
		}	
		Set<Defect> sev4defects = new HashSet<Defect>();  
		for(final Defect defect : allDefects)
		{
			if(defectService.isSev4(defect.getDefectID()))
			{
				sev4defects.add(defect);						
			}
		}	
		return sev4defects;
	}
	public Set<TestcenterUser> getCascadedTesters(Long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(Long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(Long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(
			Long requirementID) {
		// TODO Auto-generated method stub
		return null;
	}
	////////////////////////////////////////////////////////////////////////////////////////

	public ColModelAndNames getColumnModelAndNames(Long companyID) 
	{
		// Constructor in order
		// name;index;hidden;width;align;
		// sortable;resizable;search;sorttype;jsonmap;key;
		Company company = companyService.getCompany(companyID);
		Collection<String> colNames = new ArrayList<String>();
		ColModelAndNames colModelAndName = new ColModelAndNames();
		Collection<GridAttributes> columnModelSet =  new ArrayList<GridAttributes>();	

		colNames.add("ID");	 
		columnModelSet.add(new GridAttributes("requirementID",10));	

		colNames.add("Summary");		
		columnModelSet.add(new GridAttributes("requirementSummary",40));

		colNames.add("Priority");
		columnModelSet.add(new GridAttributes("requirementPriority",40));
		
		colNames.add("Section");
		columnModelSet.add(new GridAttributes("requirementSection",40,true));

		colNames.add("Details");
		columnModelSet.add(new GridAttributes("requirementDetails",true));
		
		colNames.add("All "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalAllTestruns",10,true));		
		colNames.add("Required "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalRequiredTestruns",10,true));	
		colNames.add("Optional "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalOptionalTestruns",10,true));
		
		colNames.add(company.getCyclesDisplayName());
		columnModelSet.add(new GridAttributes("totalCycles",true));
		colNames.add(company.getProjectsDisplayName());
		columnModelSet.add(new GridAttributes("totalProjects",true));
		colNames.add(company.getTestplansDisplayName());
		columnModelSet.add(new GridAttributes("totalTestplans",true));
		colNames.add(company.getTestcasesDisplayName());
		columnModelSet.add(new GridAttributes("totalTestcases",true));		
		
		colNames.add(company.getDefectsDisplayName());
		columnModelSet.add(new GridAttributes("totalDefects"));		
		colNames.add("Sev 1s");
		columnModelSet.add(new GridAttributes("totalCurrentSev1s",true));	
		colNames.add("Sev 2s");
		columnModelSet.add(new GridAttributes("totalCurrentSev2s",true));	
		colNames.add("Sev 3s");
		columnModelSet.add(new GridAttributes("totalCurrentSev3s",true));	
		colNames.add("Sev 4s");
		columnModelSet.add(new GridAttributes("totalCurrentSev4s",true));
		
		colNames.add(company.getEnvironmentsDisplayName());
		columnModelSet.add(new GridAttributes("totalEnvironments",true));	
		
		colNames.add(company.getTestersDisplayName());
		columnModelSet.add(new GridAttributes("totalTesters",true));
		colNames.add(company.getSeniorTestersDisplayName());
		columnModelSet.add(new GridAttributes("totalSeniorTesters",true));
		colNames.add(company.getDevelopersDisplayName());
		columnModelSet.add(new GridAttributes("totalDevelopers",true));
		colNames.add(company.getSeniorDevelopersDisplayName());
		columnModelSet.add(new GridAttributes("totalSeniorDevelopers",true));	 

		colNames.add("LastModifiedDate");
		columnModelSet.add(new GridAttributes("lastModifiedDate",true));
		colNames.add("LastModifiedBy");
		columnModelSet.add(new GridAttributes("lastModifiedBy",true));	 
		colNames.add("CreatedBy");
		columnModelSet.add(new GridAttributes("createdBy",true));
		colNames.add("CreationDate");
		columnModelSet.add(new GridAttributes("creationDate",true));

		colNames.add("Company ID");
		columnModelSet.add(new GridAttributes("companyID",true));


		colModelAndName.setColName(colNames);    	
		colModelAndName.setColModel(columnModelSet);
		return colModelAndName;
	}

	public Set<Requirement> getFilteredRequirements(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
	{
	
		Company company = companyService.getCompany(companyID);
		Set<Requirement> requirements = new LinkedHashSet<Requirement>();
		boolean filteredByTestrun = false;
		boolean filteredByDefect = false;
		if (testrunID != null && !testrunID.isEmpty()) 
		{	
			Set<Requirement> testrunRequirements = testrunService.getTestrun(Long.valueOf(testrunID)).getRequirements();
			if(testrunRequirements == null || testrunRequirements.isEmpty())
			{
				return null;
			}
			requirements.addAll(testrunRequirements);
			filteredByTestrun = true;
		}
		if (defectID != null && !defectID.isEmpty()) 
		{	
			Set<Requirement> defectRequirements = defectService.getDefect(Long.valueOf(defectID)).getRequirements();
			if(defectRequirements == null || defectRequirements.isEmpty())
			{
				return null;
			}
			requirements.addAll(defectRequirements);
			filteredByDefect = true;
		}
		if(!filteredByDefect && !filteredByTestrun)
		{
			Set<Requirement> companyRequirements = company.getRequirements();
			if(companyRequirements == null || companyRequirements.isEmpty())
			{
				return null;
			}
			requirements.addAll(companyRequirements);
		}
		if(requirements == null || requirements.isEmpty()){return null;}
		

		if (projectID != null && !projectID.isEmpty()) 
		{	
			Set<Requirement> projectRequirements = projectService.getCascadedRequirements(Long.valueOf(projectID));
			if(projectRequirements == null || projectRequirements.isEmpty())
			{
				requirements.clear();
			}	
			else
			{
				requirements.retainAll(projectRequirements);
			}
		}
		if(requirements == null || requirements.isEmpty()){return null;}
		
		if (cycleID != null && !cycleID.isEmpty()) 
		{	
			Set<Requirement> cycleRequirements = cycleService.getCascadedRequirements(Long.valueOf(cycleID));
			if(cycleRequirements == null || cycleRequirements.isEmpty())
			{
				requirements.clear();
			}	
			else
			{
				requirements.retainAll(cycleRequirements);
			}
		}
		if(requirements == null || requirements.isEmpty()){return null;}
		
		if (testplanID != null && !testplanID.isEmpty()) 
		{	
			Set<Requirement> testplanRequirements = testplanService.getRequirements(Long.valueOf(testplanID));
			if(testplanRequirements == null || testplanRequirements.isEmpty())
			{
				requirements.clear();
			}	
			else
			{
				requirements.retainAll(testplanRequirements);
			}
		}
		if(requirements == null || requirements.isEmpty()){return null;}
		
		if (testcaseID != null && !testcaseID.isEmpty()) 
		{	
			Set<Requirement> testcaseRequirements = testcaseService.getRequirements(Long.valueOf(testcaseID));
			if(testcaseRequirements == null || testcaseRequirements.isEmpty())
			{
				requirements.clear();
			}	
			else
			{
				requirements.retainAll(testcaseRequirements);
			}
		}
		if(requirements == null || requirements.isEmpty()){return null;}
		
		if (environmentID != null && !environmentID.isEmpty()) 
		{	
			Set<Requirement> environmentRequirements = environmentService.getRequirements(Long.valueOf(environmentID));
			if(environmentRequirements == null || environmentRequirements.isEmpty())
			{
				requirements.clear();
			}	
			else
			{
				requirements.retainAll(environmentRequirements);
			}
		}
		if(requirements == null || requirements.isEmpty()){return null;}		

		return requirements;
	}

	public RequirementSummaryList getGridRequirments(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
	{

		Set<Requirement> requirements = getFilteredRequirements(companyID, projectID,
				cycleID, testrunID, testcaseID,
				testrunID, defectID, requirementID,
				environmentID, userID,levelName, stage, required);

		Set<RequirementSummary> requirementSummarySet = new HashSet<RequirementSummary>();
		RequirementSummaryList requirementSummaryList = new RequirementSummaryList();
		if(requirements == null || requirements.isEmpty())
		{
			return null;
		}
		for(final Requirement requirement : requirements)
		{		
			requirementSummarySet.add(new RequirementSummary(requirement, testrunService,
					defectService,testplanService));
		}
		requirementSummaryList.setRequirements(requirementSummarySet);
		return requirementSummaryList;
	}

}