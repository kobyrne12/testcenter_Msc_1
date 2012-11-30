/**
 * 
 */
package ie.cit.cloud.testcenter.service.testcase;

/**
 * @author byrnek1
 *
 */

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
import ie.cit.cloud.testcenter.model.summary.TestcaseSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;

import ie.cit.cloud.testcenter.respository.testcase.TestcaseRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.environment.EnvironmentService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.requirement.RequirementService;
import ie.cit.cloud.testcenter.service.testplan.TestplanService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestcaseServiceImpl implements TestcaseService {

	@Autowired
	@Qualifier("hibernateTestcaseRespository")
	TestcaseRepository testcaseRepo;      

	@Autowired
	CompanyService companyService;
	@Autowired
	ProjectService projectService;
	@Autowired
	CycleService cycleService;
	@Autowired
	TestplanService testplanService;
	@Autowired
	TestrunService testrunService;
	@Autowired
	DefectService defectService;
	@Autowired
	RequirementService requirementService;
	@Autowired
	EnvironmentService environmentService;

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Set<Testcase> getAllTestcases() {
		return testcaseRepo.findAll();
	}
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testcase getTestcase(long testcaseID) {
		try{
			return testcaseRepo.findById(testcaseID);							
		}
		catch(NoResultException nre)			
		{	
			return null;
		}			
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testcase getTestcaseByName(String testcaseName) {
		return testcaseRepo.findTestcaseByName(testcaseName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewTestcase(Testcase testcase) {
		testcaseRepo.create(testcase);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Testcase testcase) {
		testcaseRepo.update(testcase);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long testcaseID) {
		testcaseRepo.delete(getTestcase(testcaseID));
	}

	/**
	 * Returns the ID of the latest testrun in the latest cycle for a project
	 * long
	 * @return the ID of the latest testrun in the latest cycle for a project
	 */	
	public long getLastTestRunID(long testcaseID)
	{		
		Testcase testcase = getTestcase(testcaseID);
		if(testcase.getTestruns() == null || testcase.getTestruns().isEmpty())
		{
			return 0;
		}
		for(final Testrun testrun : testcase.getTestruns())
		{
			if(testrunService.isLatest(testrun.getTestrunID()))
			{
				return testrun.getTestrunID();	
			}			
		}
		return 0;		
	}

	public boolean isRequired(long testcaseID) 
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testcaseID);
		if(compulsoryTestruns != null && !compulsoryTestruns.isEmpty())
		{
			return true;
		}
		return false;		
	}  
	///////////////////////////////////
	public int getAllTestRunsCount(long projectID)
	{	
		if(getAllTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getAllTestRuns(projectID).size();		
	}	
	public Set<Testrun> getAllTestRuns(long testcaseID)
	{
		Testcase testcase = getTestcase(testcaseID);		
		if(testcase == null)
		{
			return null;
		}	
		if(testcase.getTestruns() == null || testcase.getTestruns().isEmpty())
		{
			return null;
		}	
		return testcase.getTestruns();		
	}
	public int getCompulsoryTestRunsCount(long projectID)
	{	
		if(getCompulsoryTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getCompulsoryTestRuns(projectID).size();		
	}	
	public Set<Testrun> getCompulsoryTestRuns(long testcaseID) 
	{
		Set<Testrun> allTestruns = getAllTestRuns(testcaseID);		
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
	public int getOptionalTestRunsCount(long projectID)
	{	
		if(getOptionalTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getOptionalTestRuns(projectID).size();		
	}	
	public Set<Testrun> getOptionalTestRuns(long testcaseID) 
	{
		Set<Testrun> allTestruns = getAllTestRuns(testcaseID);	
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


	public int getCyclesCount(long projectID)
	{	
		if(getCycles(projectID) == null)
		{
			return 0;	
		}
		return getCycles(projectID).size();		
	}	
	public Set<Cycle> getCycles(long testcaseID)
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Cycle> allCycles = new HashSet<Cycle>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getCycle(testrun.getTestrunID()) != null)
			{
				allCycles.add(testrunService.getCycle(testrun.getCycleID()));				
			}
		}		
		return allCycles;
	}
	public int getProjectsCount(long projectID)
	{	
		if(getProjects(projectID) == null)
		{
			return 0;	
		}
		return getProjects(projectID).size();		
	}	
	public Set<Project> getProjects(long testcaseID)
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Project> allProjects = new HashSet<Project>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getProject(testrun.getTestrunID()) != null)
			{
				allProjects.add(testrunService.getProject(testrun.getTestrunID()));				
			}
		}		
		return allProjects;
	}
	public int getRequirementsCount(long projectID)
	{	
		if(getRequirements(projectID) == null)
		{
			return 0;	
		}
		return getRequirements(projectID).size();		
	}	
	public Set<Requirement> getRequirements(long testcaseID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Requirement> allRequirements = new HashSet<Requirement>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getRequirements() != null && !testrun.getRequirements().isEmpty())
			{
				allRequirements.addAll(testrun.getRequirements());				
			}
		}		
		return allRequirements;
	}
	public int getEnvironmentsCount(long projectID)
	{	
		if(getEnvironments(projectID) == null)
		{
			return 0;	
		}
		return getEnvironments(projectID).size();		
	}	
	public Set<Environment> getEnvironments(long testcaseID)
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Environment> allEnvironments = new HashSet<Environment>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrun.getEnvironments() != null && !testrun.getEnvironments().isEmpty())
			{
				allEnvironments.addAll(testrun.getEnvironments());				
			}
		}		
		return allEnvironments;
	}
	public int getCascadedAllDefectsCount(long projectID)
	{	
		if(getCascadedAllDefects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedAllDefects(projectID).size();		
	}	
	public Set<Defect> getCascadedAllDefects(long testcaseID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Defect> allDefects = new HashSet<Defect>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getCascadedAllDefects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedAllDefects(testrun.getTestrunID()).isEmpty())
			{
				allDefects.addAll(testrunService.getCascadedAllDefects(testrun.getTestrunID()));				
			}
		}		
		return allDefects;		
	}
	public int getCascadedSev1DefectsCount(long projectID)
	{	
		if(getCascadedSev1Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev1Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev1Defects(long testcaseID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Defect> allDefects = new HashSet<Defect>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getCascadedSev1Defects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedSev1Defects(testrun.getTestrunID()).isEmpty())
			{
				allDefects.addAll(testrunService.getCascadedSev1Defects(testrun.getTestrunID()));				
			}
		}		
		return allDefects;	
	}
	public int getCascadedSev2DefectsCount(long projectID)
	{	
		if(getCascadedSev2Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev2Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev2Defects(long testcaseID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Defect> allDefects = new HashSet<Defect>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getCascadedSev2Defects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedSev2Defects(testrun.getTestrunID()).isEmpty())
			{
				allDefects.addAll(testrunService.getCascadedSev2Defects(testrun.getTestrunID()));				
			}
		}		
		return allDefects;	
	}
	public int getCascadedSev3DefectsCount(long projectID)
	{	
		if(getCascadedSev3Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev3Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev3Defects(long testcaseID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Defect> allDefects = new HashSet<Defect>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getCascadedSev3Defects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedSev3Defects(testrun.getTestrunID()).isEmpty())
			{
				allDefects.addAll(testrunService.getCascadedSev3Defects(testrun.getTestrunID()));				
			}
		}		
		return allDefects;	
	}
	public int getCascadedSev4DefectsCount(long projectID)
	{	
		if(getCascadedSev4Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev4Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev4Defects(long testcaseID) 
	{
		Set<Testrun> allTestruns = getCompulsoryTestRuns(testcaseID);
		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}	
		Set<Defect> allDefects = new HashSet<Defect>();
		for(final Testrun testrun : allTestruns)
		{	
			if(testrunService.getCascadedSev4Defects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedSev4Defects(testrun.getTestrunID()).isEmpty())
			{
				allDefects.addAll(testrunService.getCascadedSev4Defects(testrun.getTestrunID()));				
			}
		}		
		return allDefects;	
	}
	public int getCascadedTestersCount(long projectID)
	{	
		if(getCascadedTesters(projectID) == null)
		{
			return 0;	
		}
		return getCascadedTesters(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedTesters(long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedSnrTestersCount(long projectID)
	{	
		if(getCascadedSnrTesters(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSnrTesters(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrTesters(long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedDevelopersCount(long projectID)
	{	
		if(getCascadedDevelopers(projectID) == null)
		{
			return 0;	
		}
		return getCascadedDevelopers(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedDevelopers(long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedSnrDevelopersCount(long projectID)
	{	
		if(getCascadedSnrDevelopers(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSnrDevelopers(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrDevelopers(long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
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
		columnModelSet.add(new GridAttributes("testcaseID",10));	
		colNames.add(company.getTestcaseDisplayName()+ " Name");
		columnModelSet.add(new GridAttributes("testcaseName",40));

		colNames.add(company.getTestplanDisplayName() + " Section");
		columnModelSet.add(new GridAttributes("testplanSection"));
		colNames.add("Level");
		columnModelSet.add(new GridAttributes("level"));
		colNames.add("Stage");
		columnModelSet.add(new GridAttributes("stage"));
		colNames.add("Est. Time");
		columnModelSet.add(new GridAttributes("estimatedTime"));

		colNames.add("Total "+ company.getDefectsDisplayName());
		columnModelSet.add(new GridAttributes("totalDefects"));
		colNames.add("Max Sev 1s");
		columnModelSet.add(new GridAttributes("totalAllowedSev1s",true));
		colNames.add("Current Sev 1s");
		columnModelSet.add(new GridAttributes("totalCurrentSev1s"));
		colNames.add("Max Sev 2s");
		columnModelSet.add(new GridAttributes("totalAllowedSev2s",true));
		colNames.add("Current Sev 2s");
		columnModelSet.add(new GridAttributes("totalCurrentSev2s"));
		colNames.add("Max Sev 3s");
		columnModelSet.add(new GridAttributes("totalAllowedSev3s",true));
		colNames.add("Current Sev 3s");
		columnModelSet.add(new GridAttributes("totalCurrentSev3s"));	
		colNames.add("Max Sev 4s");
		columnModelSet.add(new GridAttributes("totalAllowedSev4s",true));
		colNames.add("Current Sev 4s");
		columnModelSet.add(new GridAttributes("totalCurrentSev4s"));


		colNames.add(company.getCyclesDisplayName());
		columnModelSet.add(new GridAttributes("totalCycles",true));

		colNames.add("All "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalAllTestruns",10,true));		
		colNames.add("Compulsory "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalRequiredTestruns",10,true));	
		colNames.add("Optional "+company.getTestrunsDisplayName());
		columnModelSet.add(new GridAttributes("totalOptionalTestruns",10,true));	

		colNames.add("All "+company.getTestplansDisplayName());
		columnModelSet.add(new GridAttributes("totalAllTestplans",10,true));		
		colNames.add("Compulsory "+company.getTestplansDisplayName());
		columnModelSet.add(new GridAttributes("totalRequiredTestplans",10,true));	
		colNames.add("Optional "+company.getTestplansDisplayName());
		columnModelSet.add(new GridAttributes("totalOptionalTestplans",10,true));	

		colNames.add("All "+company.getTestcasesDisplayName());
		columnModelSet.add(new GridAttributes("totalAllTestcases",10,true));		
		colNames.add("Compulsory "+company.getTestcasesDisplayName());
		columnModelSet.add(new GridAttributes("totalRequiredTestcases",10,true));	
		colNames.add("Optional "+company.getTestcasesDisplayName());
		columnModelSet.add(new GridAttributes("totalOptionalTestcases",10,true));	

		colNames.add(company.getTestplansDisplayName());
		columnModelSet.add(new GridAttributes("totalTestplans",true));
		colNames.add(company.getTestcasesDisplayName());
		columnModelSet.add(new GridAttributes("totalTestcases",true));	 

		colNames.add(company.getEnvironmentsDisplayName());
		columnModelSet.add(new GridAttributes("totalEnvironments",true));
		colNames.add(company.getRequirementsDisplayName());
		columnModelSet.add(new GridAttributes("totalRequirements",true));

		colNames.add(company.getTestersDisplayName());
		columnModelSet.add(new GridAttributes("totalTesters",true));
		colNames.add(company.getSeniorTestersDisplayName());
		columnModelSet.add(new GridAttributes("totalSeniorTesters",true));
		colNames.add(company.getDevelopersDisplayName());
		columnModelSet.add(new GridAttributes("totalDevelopers",true));
		colNames.add(company.getSeniordevelopersDisplayName());
		columnModelSet.add(new GridAttributes("totalSeniorDevelopers",true));	 

		colNames.add("LastModifiedDate");
		columnModelSet.add(new GridAttributes("lastModifiedDate",true));
		colNames.add("LastModifiedBy");
		columnModelSet.add(new GridAttributes("lastModifiedBy",true));	 
		colNames.add("CreatedBy");
		columnModelSet.add(new GridAttributes("createdBy",true));
		colNames.add("CreationDate");
		columnModelSet.add(new GridAttributes("creationDate",true));

		colNames.add("Position");
		columnModelSet.add(new GridAttributes("testplanOrderNum"));
		colNames.add("Company ID");
		columnModelSet.add(new GridAttributes("companyID",true));
		// if customValue1 set then 	
		//colNames.add("customValue1");
		//columnModelSet.add(new GridAttributes("customColumn1",true));	 
		// if customValue2 set then 
		//colNames.add("customValue1");
		//columnModelSet.add(new GridAttributes("customColumn2",true));		
		// if customValue3 set then 
		//colNames.add("customValue3");
		//columnModelSet.add(new GridAttributes("customColumn3",true));		
		// if customValue4 set then 
		//colNames.add("customValue4");	
		//columnModelSet.add(new GridAttributes("customColumn4",true));		
		// if customValue5 set then 
		//colNames.add("customValue5");	
		//columnModelSet.add(new GridAttributes("customColumn5",true));	

		colModelAndName.setColName(colNames);    	
		colModelAndName.setColModel(columnModelSet);
		return colModelAndName;
	}

	public TestcaseSummaryList getGridTestcases(long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String level,String stage,String required)
	{
		// Check which testcases wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Testcase> testcases = company.getTestcases();

		if (projectID != null && !projectID.isEmpty()) 
		{			
			if(projectService.getCascadedAllTestCases(Long.valueOf(projectID).longValue()) != null)
			{
				testcases.retainAll(projectService.getCascadedAllTestCases(Long.valueOf(projectID).longValue()));
			}			
		}	
		if(testcases == null || testcases.isEmpty()){return null;}

		if (cycleID != null && !cycleID.isEmpty()) 
		{			
			if(cycleService.getCascadedAllTestCases(Long.valueOf(cycleID).longValue()) != null)
			{
				testcases.retainAll(cycleService.getCascadedAllTestCases(Long.valueOf(cycleID).longValue()));
			}			
		}			
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain Testplan testcases
		if (testplanID != null && !testplanID.isEmpty()) 
		{			
			if(testplanService.getAllTestCases(Long.valueOf(testplanID).longValue()) != null)
			{
				testcases.retainAll(testplanService.getAllTestCases(Long.valueOf(testplanID).longValue()));
			}			
		}		
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain Testrun testcases
		if (testrunID != null && !testrunID.isEmpty()) 
		{			
			if(testrunService.getProject(Long.valueOf(testrunID).longValue()) != null)
			{
				Set<Testcase> testrunTestcases = new HashSet<Testcase>();
				testrunTestcases.add(testrunService.getTestcase(Long.valueOf(testrunID).longValue()));
				testcases.retainAll(testrunTestcases);				
			}			
		}
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain Defect testcases
		if (defectID != null && !defectID.isEmpty()) // limit to testcases that have this test plan id in it
		{			
			if(defectService.getCascadedAllTestCases(Long.valueOf(defectID).longValue()) != null)
			{
				testcases.retainAll(defectService.getCascadedAllTestCases(Long.valueOf(defectID).longValue()));				
			}			
		}
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain Requirement testcases
		if (requirementID != null && !requirementID.isEmpty()) // limit to testcases that have this test plan id in it
		{			
			if(requirementService.getAllTestCases(Long.valueOf(requirementID).longValue()) != null)
			{
				testcases.retainAll(requirementService.getAllTestCases(Long.valueOf(requirementID).longValue()));				
			}				
		}
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain Environment testcases
		if (environmentID != null && !environmentID.isEmpty()) // limit to testcases that have this test plan id in it
		{			
			if(environmentService.getAllTestCases(Long.valueOf(environmentID).longValue()) != null)
			{
				testcases.retainAll(environmentService.getAllTestCases(Long.valueOf(environmentID).longValue()));				
			}			
		}
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain User testcases
		//		if (userID != null && !userID.isEmpty()) // limit to testcases that have this test plan id in it
		//		{			
		//			if(userService.getCascadedProjects(Long.valueOf(defectID).longValue()) != null)
		//			{
		//				testcases.retainAll(userService.getCascadedProjects(Long.valueOf(defectID).longValue()));				
		//			}			
		//		}
		//		if(testcases == null || testcases.isEmpty()){return null;}

		Set<TestcaseSummary> testcaseSummarySet = new HashSet<TestcaseSummary>();
		TestcaseSummaryList testcaseSummaryList = new TestcaseSummaryList();

		for(final Testcase testcase : testcases)
		{				
			TestcaseSummary testcaseSummary = getTestcaseSummary(companyID, testcase,
					level,stage,required);	
			testcaseSummarySet.add(testcaseSummary);

		}

		testcaseSummaryList.setTestcases(testcaseSummarySet);
		return testcaseSummaryList;
	}

	public TestcaseSummary getTestcaseSummary(long companyID,Testcase testcase, 
			String level,String stage,String required )
	{
		Long testcaseID =  testcase.getTestcaseID();
		TestcaseSummary testcaseSummary = new TestcaseSummary();

		testcaseSummary.setTestcaseID(testcase.getTestcaseID());
		testcaseSummary.setCompanyID(companyID);
		testcaseSummary.setTestcaseName(testcase.getTestcaseName());
		Testplan testplan = testplanService.getTestplan(testcase.getTestplanID());
		if(testplan != null)
		{
			testcaseSummary.setTestplanName(testplan.getTestplanName());
			testcaseSummary.setTestplanSection(
					testplanService.getTestplanSection(testcase.getTestplanSectionID()).getTestplanSectionName());
			testcaseSummary.setTestplanOrderNum(testcase.getTestplanOrderNum());
		}
		else
		{
			testcaseSummary.setTestplanSection("NONE");
			testcaseSummary.setTestplanOrderNum(0);
			testcaseSummary.setTestplanName("NONE");
		}
		testcaseSummary.setTotalProjects(getProjectsCount(testcaseID));
		testcaseSummary.setLevel(testcase.getLevel().getTestrunLevelName());
		testcaseSummary.setStage(testcase.getStage());
		testcaseSummary.setEstimatedTime(testcase.getEstimatedTime());
		
		testcaseSummary.setTotalAllTestruns(getAllTestRunsCount(testcaseID));
		testcaseSummary.setTotalRequiredTestruns(getCompulsoryTestRunsCount(testcaseID));
		testcaseSummary.setTotalOptionalTestruns(getOptionalTestRunsCount(testcaseID));

		testcaseSummary.setTotalCycles(getCyclesCount(testcaseID));
		testcaseSummary.setTotalEnvironments(getEnvironmentsCount(testcaseID));
		testcaseSummary.setTotalRequirements(getCascadedTestersCount(testcaseID));
		testcaseSummary.setTotalDefects(getCascadedAllDefectsCount(testcaseID));

		testcaseSummary.setTotalCurrentSev1s(getCascadedSev1DefectsCount(testcaseID));
		testcaseSummary.setTotalCurrentSev2s(getCascadedSev2DefectsCount(testcaseID));
		testcaseSummary.setTotalCurrentSev3s(getCascadedSev3DefectsCount(testcaseID));
		testcaseSummary.setTotalCurrentSev4s(getCascadedSev4DefectsCount(testcaseID));

		testcaseSummary.setTotalTesters(getCascadedTestersCount(testcaseID));
		testcaseSummary.setTotalSeniorTesters(getCascadedSnrDevelopersCount(testcaseID));
		testcaseSummary.setTotalDevelopers(getCascadedDevelopersCount(testcaseID));
		testcaseSummary.setTotalSeniorDevelopers(getCascadedSnrDevelopersCount(testcaseID));

		// get User name from ID testcase.getLastModifiedBy()
		testcaseSummary.setLastModifiedBy("USER");
		testcaseSummary.setLastModifiedDate(testcase.getLastModifiedDate());
		// get User name from ID testcase.getCreatedByUserID()
		testcaseSummary.setCreatedBy("USER");
		testcaseSummary.setCreationDate(testcase.getCreationDate());	


		return testcaseSummary;
	}

}