/**
 * 
 */
package ie.cit.cloud.testcenter.service.environment;

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
import ie.cit.cloud.testcenter.model.summary.EnvironmentSummary;
import ie.cit.cloud.testcenter.model.summary.EnvironmentSummaryList;
import ie.cit.cloud.testcenter.model.summary.RequirementSummary;
import ie.cit.cloud.testcenter.model.summary.RequirementSummaryList;
import ie.cit.cloud.testcenter.respository.environment.EnvironmentRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testcase.TestcaseService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	@Qualifier("hibernateEnvironmentRespository")
	EnvironmentRepository environmentRepo;      

	@Autowired
	CompanyService companyService;	
	@Autowired
	ProjectService projectService;
	@Autowired
	CycleService cycleService;
	@Autowired
	TestplanService testplanService;	
	@Autowired
	TestcaseService testcaseService;
	@Autowired
	TestrunService testrunService;
	@Autowired
	DefectService defectService;
	@Autowired
	RequirementService requirementService;

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Environment getEnvironment(Long environmentID) {
		try{
			return environmentRepo.findById(environmentID);								
		}
		catch(NoResultException nre)			
		{	
			return null;
		}		
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Environment getEnvironmentByName(String environmentName) {
		return environmentRepo.findByName(environmentName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewEnvironment(Environment environment) {
		environmentRepo.create(environment);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Environment environment) {
		environmentRepo.update(environment);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(Long environmentID) {
		environmentRepo.delete(getEnvironment(environmentID));
	}
	////////////////////////
	public Set<Testrun> getAllTestRuns(Long environmentID) 
	{
		Environment environment = getEnvironment(environmentID);
		if(environment == null)
		{
			return null;
		}			
		if(environment.getTestruns() == null || environment.getTestruns().isEmpty())
		{
			return null;
		}
		return environment.getTestruns();			
	} 

	public Set<Testrun> getCompulsoryTestRuns(Long environmentID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(environmentID);
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

	public Set<Testrun> getOptionalTestRuns(Long environmentID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(environmentID);
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

	public Set<Testcase> getAllTestCases(Long environmentID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(environmentID);
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

	public Set<Testcase> getCompulsoryTestCases(Long environmentID)
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(environmentID);
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

	public Set<Testcase> getOptionalTestCases(Long environmentID)
	{
		Set<Testrun> allTestruns = getOptionalTestRuns(environmentID);
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

	public Set<Testplan> getAllTestPlans(Long environmentID)
	{		
		Set<Testcase> allTestcases = getAllTestCases(environmentID);
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

	public Set<Testplan> getCompulsoryTestPlans(Long environmentID)
	{		
		Set<Testcase> allTestcases = getCompulsoryTestCases(environmentID);
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

	public Set<Testplan> getOptionalTestPlans(Long environmentID)
	{		
		Set<Testcase> allTestcases = getOptionalTestCases(environmentID);
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

	public Set<Cycle> getCycles(Long environmentID) {
		Set<Testrun> allTestruns = getCompulsoryTestRuns(environmentID);
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

	public Set<Project> getProjects(Long environmentID)
	{
		Set<Cycle> cycles =  getCycles(environmentID);
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

	public Set<Requirement> getRequirements(Long environmentID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(environmentID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Requirement> requirements = new HashSet<Requirement>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getRequirements() != null && !testrun.getRequirements().isEmpty())			
			{
				requirements.addAll(testrun.getRequirements());
			}			
		}	
		Defect defect = defectService.getDefect(environmentID);
		if(defect.getRequirements() == null || defect.getRequirements().isEmpty())
		{		
			requirements.addAll(defect.getRequirements());			
		}
		return requirements;		
	}	

	public Set<Defect> getCascadedAllDefects(Long environmentID) {
		Set<Testrun> allTestruns = getCompulsoryTestRuns(environmentID);
		if(allTestruns == null || allTestruns.isEmpty())
		{

			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getCascadedAllDefects(testrun.getTestrunID()) != null &&
					!testrunService.getCascadedAllDefects(testrun.getTestrunID()).isEmpty())
			{
				defects.addAll(testrunService.getCascadedAllDefects(testrun.getTestrunID()));
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
	public Set<TestcenterUser> getCascadedTesters(Long environmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(Long environmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(Long environmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(
			Long environmentID) {
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
		columnModelSet.add(new GridAttributes("environmentID",10));	

		colNames.add("Name");		
		columnModelSet.add(new GridAttributes("environmentName",40));

		colNames.add("OS");
		columnModelSet.add(new GridAttributes("environmentOS",40));


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

		colNames.add(company.getRequirementsDisplayName());
		columnModelSet.add(new GridAttributes("totalRequirements",true));	

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

	public Set<Environment> getFilteredEnvironments(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
	{

		Company company = companyService.getCompany(companyID);
		Set<Environment> environments = new LinkedHashSet<Environment>();
		boolean filteredByTestrun = false;
		
		if (testrunID != null && !testrunID.isEmpty()) 
		{	
			Set<Environment> testrunEnvironments = testrunService.getTestrun(Long.valueOf(testrunID)).getEnvironments();
			if(testrunEnvironments == null || testrunEnvironments.isEmpty())
			{
				return null;
			}
			environments.addAll(testrunEnvironments);
			filteredByTestrun = true;
		}
		if(!filteredByTestrun)
		{
			Set<Environment> companyEnvironments = company.getEnvironments();
			if(companyEnvironments == null || companyEnvironments.isEmpty())
			{
				return null;
			}
			environments.addAll(companyEnvironments);
		}
		
		if (projectID != null && !projectID.isEmpty()) 
		{	
			Set<Environment> projectEnvironments = projectService.getCascadedEnvironments(Long.valueOf(projectID));
			if(projectEnvironments == null || projectEnvironments.isEmpty())
			{
				environments.clear();
			}	
			else
			{
				environments.retainAll(projectEnvironments);
			}
		}
		if(environments == null || environments.isEmpty()){return null;}

		if (cycleID != null && !cycleID.isEmpty()) 
		{	
			Set<Environment> cycleEnvironments = cycleService.getCascadedEnvironments(Long.valueOf(cycleID));
			
			if(cycleEnvironments == null || cycleEnvironments.isEmpty())
			{
				environments.clear();
			}	
			else
			{
				environments.retainAll(cycleEnvironments);
			}
		}
		if(environments == null || environments.isEmpty()){return null;}

		if (testplanID != null && !testplanID.isEmpty()) 
		{	
			Set<Environment> testplanEnvironments = testplanService.getEnvironments(Long.valueOf(testplanID));
			if(testplanEnvironments == null || testplanEnvironments.isEmpty())
			{
				environments.clear();
			}	
			else
			{
				environments.retainAll(testplanEnvironments);
			}
		}
		if(environments == null || environments.isEmpty()){return null;}

		if (testcaseID != null && !testcaseID.isEmpty()) 
		{	
			Set<Environment> testcaseEnvironments = testcaseService.getEnvironments(Long.valueOf(testcaseID));
			if(testcaseEnvironments == null || testcaseEnvironments.isEmpty())
			{
				environments.clear();
			}	
			else
			{
				environments.retainAll(testcaseEnvironments);
			}
		}
		if(environments == null || environments.isEmpty()){return null;}

		if (requirementID != null && !requirementID.isEmpty()) 
		{	
			Set<Environment> requirementEnvironments = requirementService.getEnvironments(Long.valueOf(requirementID));
			if(requirementEnvironments == null || requirementEnvironments.isEmpty())
			{
				environments.clear();
			}	
			else
			{
				environments.retainAll(requirementEnvironments);
			}
		}
		if(environments == null || environments.isEmpty()){return null;}
		
		if (defectID != null && !defectID.isEmpty()) 
		{	
			Set<Environment> defectEnvironments = defectService.getCascadedEnvironments(Long.valueOf(defectID));
			
			if(defectEnvironments == null || defectEnvironments.isEmpty())
			{
				environments.clear();
			}	
			else
			{
				environments.retainAll(defectEnvironments);
			}					
		}		
		if(environments == null || environments.isEmpty()){return null;}
		
		return environments;
	}

	public EnvironmentSummaryList getGridEnvironments(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
	{

		Set<Environment> environments = getFilteredEnvironments(companyID, projectID,
				cycleID, testrunID, testcaseID,
				testrunID, defectID, requirementID,
				environmentID, userID,levelName, stage, required);

		Set<EnvironmentSummary> environmentSummarySet = new HashSet<EnvironmentSummary>();
		EnvironmentSummaryList environmentSummaryList = new EnvironmentSummaryList();
		if(environments == null || environments.isEmpty())
		{
			return null;
		}
		for(final Environment environment : environments)
		{		
			environmentSummarySet.add(new EnvironmentSummary(environment, testrunService, defectService));
		}
		environmentSummaryList.setEnvironments(environmentSummarySet);
		return environmentSummaryList;
	}

}