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
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestplanSummaryList;

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
import java.util.LinkedHashSet;
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
	public Testcase getTestcase(Long testcaseID) {
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
	public void remove(Long testcaseID) {
		testcaseRepo.delete(getTestcase(testcaseID));
	}

	/**
	 * Returns the ID of the latest testrun in the latest cycle for a project
	 * Long
	 * @return the ID of the latest testrun in the latest cycle for a project
	 */	
	public Long getLastTestRunID(Long testcaseID)
	{		
		Testcase testcase = getTestcase(testcaseID);
		if(testcase.getTestruns() == null || testcase.getTestruns().isEmpty())
		{
			return null;
		}
		for(final Testrun testrun : testcase.getTestruns())
		{
			if(testrunService.isLatest(testrun.getTestrunID()))
			{
				return testrun.getTestrunID();	
			}			
		}
		return null;		
	}

	public boolean isRequired(Long testcaseID) 
	{
		Set<Testrun> compulsoryTestruns = getRequiredTestRuns(testcaseID);
		if(compulsoryTestruns != null && !compulsoryTestruns.isEmpty())
		{
			return true;
		}
		return false;		
	}  
	///////////////////////////////////
	public Testplan getTestcaseTestplan(Long testcaseID)
	{
		Testcase testcase = getTestcase(testcaseID);
		if(testplanService.getTestplan(testcase.getTestplanID()) != null)
		{
			return testplanService.getTestplan(testcase.getTestplanID());
		}		
		return null;
	}
	public String getTestcaseTestplanName(Long testcaseID)
	{
		Testcase testcase = getTestcase(testcaseID);
		if(testplanService.getTestplan(testcase.getTestplanID()) != null)
		{
			return testplanService.getTestplan(testcase.getTestplanID()).getTestplanName();
		}		
		return null;
	}
	public int getAllTestRunsCount(Long projectID)
	{	
		if(getAllTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getAllTestRuns(projectID).size();		
	}	
	public Set<Testrun> getAllTestRuns(Long testcaseID)
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
	public int getRequiredTestRunsCount(Long projectID)
	{	
		if(getRequiredTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getRequiredTestRuns(projectID).size();		
	}	
	public Set<Testrun> getRequiredTestRuns(Long testcaseID) 
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
	public int getOptionalTestRunsCount(Long projectID)
	{	
		if(getOptionalTestRuns(projectID) == null)
		{
			return 0;	
		}
		return getOptionalTestRuns(projectID).size();		
	}	
	public Set<Testrun> getOptionalTestRuns(Long testcaseID) 
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

	
	public Set<Cycle> getAllCycles(Long testcaseID)
	{
		Set<Testrun> allTestruns = getAllTestRuns(testcaseID);
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
	public Set<Cycle> getRequiredCycles(Long testcaseID)
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getProjectsCount(Long projectID)
	{	
		if(getProjects(projectID) == null)
		{
			return 0;	
		}
		return getProjects(projectID).size();		
	}	
	public Set<Project> getProjects(Long testcaseID)
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getRequirementsCount(Long projectID)
	{	
		if(getRequirements(projectID) == null)
		{
			return 0;	
		}
		return getRequirements(projectID).size();		
	}	
	public Set<Requirement> getRequirements(Long testcaseID) 
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getEnvironmentsCount(Long projectID)
	{	
		if(getEnvironments(projectID) == null)
		{
			return 0;	
		}
		return getEnvironments(projectID).size();		
	}	
	public Set<Environment> getEnvironments(Long testcaseID)
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getCascadedAllDefectsCount(Long projectID)
	{	
		if(getCascadedAllDefects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedAllDefects(projectID).size();		
	}	
	public Set<Defect> getCascadedAllDefects(Long testcaseID) 
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getCascadedSev1DefectsCount(Long projectID)
	{	
		if(getCascadedSev1Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev1Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev1Defects(Long testcaseID) 
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getCascadedSev2DefectsCount(Long projectID)
	{	
		if(getCascadedSev2Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev2Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev2Defects(Long testcaseID) 
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getCascadedSev3DefectsCount(Long projectID)
	{	
		if(getCascadedSev3Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev3Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev3Defects(Long testcaseID) 
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getCascadedSev4DefectsCount(Long projectID)
	{	
		if(getCascadedSev4Defects(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSev4Defects(projectID).size();		
	}	
	public Set<Defect> getCascadedSev4Defects(Long testcaseID) 
	{
		Set<Testrun> allTestruns = getRequiredTestRuns(testcaseID);
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
	public int getCascadedTestersCount(Long projectID)
	{	
		if(getCascadedTesters(projectID) == null)
		{
			return 0;	
		}
		return getCascadedTesters(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedTesters(Long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedSnrTestersCount(Long projectID)
	{	
		if(getCascadedSnrTesters(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSnrTesters(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrTesters(Long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedDevelopersCount(Long projectID)
	{	
		if(getCascadedDevelopers(projectID) == null)
		{
			return 0;	
		}
		return getCascadedDevelopers(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedDevelopers(Long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getCascadedSnrDevelopersCount(Long projectID)
	{	
		if(getCascadedSnrDevelopers(projectID) == null)
		{
			return 0;	
		}
		return getCascadedSnrDevelopers(projectID).size();		
	}	
	public Set<TestcenterUser> getCascadedSnrDevelopers(Long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Set<TestcenterUser> getCascadedUsers(Long testcaseID) {
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
		columnModelSet.add(new GridAttributes("testcaseID",10));	
		colNames.add(company.getTestcaseDisplayName()+ " Name");
		columnModelSet.add(new GridAttributes("testcaseName",40));

		colNames.add(company.getTestplanDisplayName());
		columnModelSet.add(new GridAttributes("testplanName"));
		colNames.add(company.getTestplanDisplayName() + " Section");
		columnModelSet.add(new GridAttributes("testplanSection"));
		
		colNames.add("Level");
		columnModelSet.add(new GridAttributes("levelName"));
		colNames.add("Stage");
		columnModelSet.add(new GridAttributes("stage"));
		colNames.add("Est. Time");
		columnModelSet.add(new GridAttributes("estimatedTime"));

		colNames.add(company.getDefectsDisplayName());
		columnModelSet.add(new GridAttributes("totalDefects"));		
		colNames.add("Sev 1s");
		columnModelSet.add(new GridAttributes("totalCurrentSev1s"));		
		colNames.add("Sev 2s");
		columnModelSet.add(new GridAttributes("totalCurrentSev2s"));		
		colNames.add("Sev 3s");
		columnModelSet.add(new GridAttributes("totalCurrentSev3s"));	
		colNames.add("Sev 4s");
		columnModelSet.add(new GridAttributes("totalCurrentSev4s"));

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

	public Set<Testcase> getFilteredTestcases(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
	{
		// Check which testcases wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Testcase> testcases = new LinkedHashSet<Testcase>();
		
		// Retain Testplan testcases
		if (testplanID != null && !testplanID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{						
			Testplan testplan = testplanService.getTestplan(Long.valueOf(testplanID));
			if(testplan == null)
			{
				return null;
			}
			if(testplan.getTestcases() == null || testplan.getTestcases().isEmpty())
			{
				return null;
			}	
			testcases.addAll(testplan.getTestcases());						
		}
		else
		{
			if(companyService.getAllTestCases(companyID) == null)
			{
				return null;
			}
			testcases.addAll(companyService.getAllTestCases(companyID));
		}		
		if(testcases == null || testcases.isEmpty()){return null;}
		///////////////////////////////
		
		if (projectID != null && !projectID.isEmpty()) 
		{			
			if(projectService.getCascadedAllTestCases(Long.valueOf(projectID)) != null)
			{
				testcases.retainAll(projectService.getCascadedAllTestCases(Long.valueOf(projectID)));
			}		
			else
			{
				testcases.clear();
			}
		}	
		if(testcases == null || testcases.isEmpty()){return null;}

		if (cycleID != null && !cycleID.isEmpty()) 
		{			
			if(cycleService.getCascadedAllTestCases(Long.valueOf(cycleID)) != null)
			{
				testcases.retainAll(cycleService.getCascadedAllTestCases(Long.valueOf(cycleID)));
			}
			else
			{
				testcases.clear();
			}
		}			
		if(testcases == null || testcases.isEmpty()){return null;}

		
		// Retain Testrun testcases
		if (testrunID != null && !testrunID.isEmpty()) 
		{			
			if(testrunService.getProject(Long.valueOf(testrunID)) != null)
			{
				Set<Testcase> testrunTestcases = new HashSet<Testcase>();
				testrunTestcases.add(testrunService.getTestcase(Long.valueOf(testrunID)));
				testcases.retainAll(testrunTestcases);				
			}
			else
			{
				testcases.clear();
			}
		}
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain Defect testcases
		if (defectID != null && !defectID.isEmpty()) // limit to testcases that have this test plan id in it
		{			
			if(defectService.getCascadedAllTestCases(Long.valueOf(defectID)) != null)
			{
				testcases.retainAll(defectService.getCascadedAllTestCases(Long.valueOf(defectID)));				
			}
			else
			{
				testcases.clear();
			}
		}
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain Requirement testcases
		if (requirementID != null && !requirementID.isEmpty()) // limit to testcases that have this test plan id in it
		{			
			if(requirementService.getAllTestCases(Long.valueOf(requirementID)) != null)
			{
				testcases.retainAll(requirementService.getAllTestCases(Long.valueOf(requirementID)));				
			}	
			else
			{
				testcases.clear();
			}
		}
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain Environment testcases
		if (environmentID != null && !environmentID.isEmpty()) // limit to testcases that have this test plan id in it
		{			
			if(environmentService.getAllTestCases(Long.valueOf(environmentID)) != null)
			{
				testcases.retainAll(environmentService.getAllTestCases(Long.valueOf(environmentID)));				
			}	
			else
			{
				testcases.clear();
			}
		}
		if(testcases == null || testcases.isEmpty()){return null;}

		// Retain User testcases
		//		if (userID != null && !userID.isEmpty()) // limit to testcases that have this test plan id in it
		//		{			
		//			if(userService.getCascadedProjects(Long.valueOf(defectID)) != null)
		//			{
		//				testcases.retainAll(userService.getCascadedProjects(Long.valueOf(defectID)));				
		//			}			
		//		}
		//		if(testcases == null || testcases.isEmpty()){return null;}
		return testcases;
	}
	public TestcaseSummaryList getGridTestcases(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)	
	{
		Set<Testcase> testcases = getFilteredTestcases(companyID, projectID,
				cycleID, testplanID, testcaseID,
				testrunID, defectID, requirementID,
				environmentID, userID,levelName,stage,required);
		
		Set<TestcaseSummary> testcaseSummarySet = new HashSet<TestcaseSummary>();
		TestcaseSummaryList testcaseSummaryList = new TestcaseSummaryList();
		
		if(testcases == null || testcases.isEmpty()){return null;}
		
		for(final Testcase testcase : testcases)
		{				
			testcaseSummarySet.add(new TestcaseSummary(testcase, levelName, testrunService, defectService, testplanService));			
		}

		testcaseSummaryList.setTestcases(testcaseSummarySet);
		return testcaseSummaryList;
	}

	public Set<Testcase> getExistingTestcases(Long companyID,String relatedItem, String ID)
	{
		Set<Testcase> existingTestcases = new LinkedHashSet<Testcase>();
		if(relatedItem.equalsIgnoreCase("testplan"))
		{
			existingTestcases = testplanService.getTestplan(Long.valueOf(ID)).getTestcases();
		}
		else if(relatedItem.equalsIgnoreCase("project"))
		{
			existingTestcases = getFilteredTestcases(companyID,ID,
				null, null, null,null, null, null,null, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("cycle"))
		{
			existingTestcases = getFilteredTestcases(companyID,null,ID,
				null, null,null, null, null,null, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("defect"))
		{
			existingTestcases = getFilteredTestcases(companyID,null,null,
				null, null,null, ID, null,null, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("requirement"))
		{
			existingTestcases = getFilteredTestcases(companyID,null,null,
				null, null,null, null, ID,null, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("environment"))
		{
			existingTestcases = getFilteredTestcases(companyID,null,null,
				null, null,null, null, null, ID, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("user"))
		{
			existingTestcases = getFilteredTestcases(companyID,null,null,
				null, null,null, null, null, null, ID,null,null,null);
		}		
		return existingTestcases;		
	}
	public Set<Testcase> getAvailableTestcases(Long companyID, String relatedItem,String ID)
	{
		Set<Testcase> allTestcases = companyService.getAllTestCases(companyID);
		if(allTestcases == null || allTestcases.isEmpty())
		{
			return null;
		}
		Set<Testcase> availableTestcases = new LinkedHashSet<Testcase>();
		if(relatedItem.equalsIgnoreCase("testplan"))
		{
			return null;	
		}
		else if(relatedItem.equalsIgnoreCase("project"))
		{
			for(final Testcase testcase : allTestcases)
			{
				if(getProjects(testcase.getTestcaseID()) == null || getProjects(testcase.getTestcaseID()).isEmpty())
				{
					availableTestcases.add(testcase);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("cycle"))
		{
			for(final Testcase testcase : allTestcases)
			{
				if(getRequiredCycles(testcase.getTestcaseID()) == null || 
						getRequiredCycles(testcase.getTestcaseID()).isEmpty())
				{
					availableTestcases.add(testcase);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("defect"))
		{
			for(final Testcase testcase : allTestcases)
			{
				if(getCascadedAllDefects(testcase.getTestcaseID()) == null || 
						(getCascadedAllDefects(testcase.getTestcaseID()).isEmpty()))
				{
					availableTestcases.add(testcase);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("requirement"))
		{
			for(final Testcase testcase : allTestcases)
			{
				if(getRequirements(testcase.getTestcaseID())  == null || 
						(getRequirements(testcase.getTestcaseID()).isEmpty()))
				{
					availableTestcases.add(testcase);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("environment"))
		{
			for(final Testcase testcase : allTestcases)
			{
				if(getEnvironments(testcase.getTestcaseID())  == null || 
						(getEnvironments(testcase.getTestcaseID()).isEmpty()))
				{
					availableTestcases.add(testcase);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("user"))
		{
			for(final Testcase testcase : allTestcases)
			{
				if(getCascadedUsers(testcase.getTestcaseID())  == null || 
						(getCascadedUsers(testcase.getTestcaseID()).isEmpty()))
				{
					availableTestcases.add(testcase);
				}
			}			
		}
		return availableTestcases;
	}
	
}