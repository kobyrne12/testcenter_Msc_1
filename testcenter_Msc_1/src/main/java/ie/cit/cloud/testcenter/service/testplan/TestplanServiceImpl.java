/**
 * 
 */
package ie.cit.cloud.testcenter.service.testplan;

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
import ie.cit.cloud.testcenter.model.TestplanSection;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummary;
import ie.cit.cloud.testcenter.model.summary.TestcaseSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestplanSummary;
import ie.cit.cloud.testcenter.model.summary.TestplanSummaryList;
import ie.cit.cloud.testcenter.respository.testplan.TestplanRepository;
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
public class TestplanServiceImpl implements TestplanService {

	@Autowired
	@Qualifier("hibernateTestplanRespository")
	TestplanRepository testplanRepo;      

	@Autowired
	CompanyService companyService;
	@Autowired
	ProjectService projectService;
	@Autowired
	CycleService cycleService;
	@Autowired
	TestcaseService testcaseService;	
	@Autowired
	TestrunService testrunService;
	@Autowired
	DefectService defectService;
	@Autowired
	RequirementService requirementService;
	@Autowired
	EnvironmentService environmentService;


	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testplan getTestplan(Long testplanID) {
		try{
			return testplanRepo.findById(testplanID);						
		}
		catch(NoResultException nre)			
		{	
			return null;
		}		
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testplan getTestplanByName(String testplanName) {
		return testplanRepo.findTestplanByName(testplanName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewTestplan(Testplan testplan) {
		testplanRepo.create(testplan);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Testplan testplan) {
		testplanRepo.update(testplan);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(Long testplanID) {
		testplanRepo.delete(getTestplan(testplanID));
	}

	public TestplanSection getTestplanSection(Long testplanSectionID) {
		try{
			return testplanRepo.findTestplanSectionById(testplanSectionID);						
		}
		catch(NoResultException nre)			
		{	
			return null;
		}	
	}

	public void addNewTestplanSection(TestplanSection testplanSection) {
		testplanRepo.createTestplanSection(testplanSection);	

	}

	public void updateTestplanSection(TestplanSection testplanSection) {
		testplanRepo.updateTestplanSection(testplanSection);

	}

	public void removeTestplanSection(Long testplanSectionID) {
		testplanRepo.deleteTestplanSection(getTestplanSection(testplanSectionID));

	}
	////////////////

	public Set<Testcase> getAllTestCases(Long testplanID) 
	{		 
		Testplan testplan = getTestplan(testplanID)	;
		if(testplan == null)
		{
			return null;
		}	
		if(testplan.getTestcases() == null ||testplan.getTestcases().isEmpty())
		{
			return null;
		}	
		return testplan.getTestcases();			
	}

	public Set<Testcase> getCompulsoryTestCases(Long testplanID) 
	{
		Set<Testcase> allTestcases = getAllTestCases(testplanID);
		if(allTestcases == null)
		{
			return null;
		}	
		Set<Testcase> compulsoryTestcases= new HashSet<Testcase>();
		for(final Testcase testcase : allTestcases)
		{
			if(isRequired(testcase.getTestcaseID()))
			{
				compulsoryTestcases.add(testcase);
			}
		}		
		return compulsoryTestcases;
	}

	public Set<Testcase> getOptionalTestCases(Long testplanID)
	{
		Set<Testcase> allTestcases = getAllTestCases(testplanID);
		if(allTestcases == null)
		{
			return null;
		}	
		Set<Testcase> optionalTestcases= new HashSet<Testcase>();
		for(final Testcase testcase : allTestcases)
		{
			if(!isRequired(testcase.getTestcaseID()))
			{
				optionalTestcases.add(testcase);
			}
		}		
		return optionalTestcases;
	}

	public Set<Testrun> getAllTestRuns(Long testplanID) 
	{
		Set<Testcase> allTestcases = getAllTestCases(testplanID);
		if(allTestcases == null)
		{
			return null;
		}	
		Set<Testrun> allTestruns = new HashSet<Testrun>();
		for(final Testcase testcase : allTestcases)
		{
			if(testcase.getTestruns() != null && !testcase.getTestruns().isEmpty())
			{
				allTestruns.addAll(testcase.getTestruns());
			}			
		}	
		return allTestruns;
	}

	public Set<Testrun> getCompulsoryTestRuns(Long testplanID)
	{
		Set<Testcase> compulsoryTestcases = getCompulsoryTestCases(testplanID);
		if(compulsoryTestcases == null)
		{
			return null;
		}	
		Set<Testrun> compulsoryTestruns = new HashSet<Testrun>();
		for(final Testcase compulsoryTestcase : compulsoryTestcases)
		{
			if(compulsoryTestcase.getTestruns() != null && !compulsoryTestcase.getTestruns().isEmpty())
			{
				compulsoryTestruns.addAll(compulsoryTestcase.getTestruns());
			}			
		}	
		return compulsoryTestruns;
	}

	public Set<Testrun> getOptionalTestRuns(Long testplanID)
	{
		Set<Testcase> optionalTestcases = getOptionalTestCases(testplanID);
		if(optionalTestcases == null)
		{
			return null;
		}	
		Set<Testrun> optionalTestruns = new HashSet<Testrun>();
		for(final Testcase optionalTestcase : optionalTestcases)
		{
			if(optionalTestcase.getTestruns() != null && !optionalTestcase.getTestruns().isEmpty())
			{
				optionalTestruns.addAll(optionalTestcase.getTestruns());
			}			
		}	
		return optionalTestruns;		
	}
	public boolean isRequired(Long testcaseID) 
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testcaseID);
		if(compulsoryTestruns != null && !compulsoryTestruns.isEmpty())
		{
			return true;
		}
		return false;		
	}   
	public Set<Cycle> getCycles(Long testplanID) 
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Cycle> cycles = new HashSet<Cycle>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrunService.getCycle(testrun.getTestrunID()) != null )
			{
				cycles.add(testrunService.getCycle(testrun.getTestrunID()));
			}			
		}	
		return cycles;
	}

	public Set<Project> getProjects(Long testplanID)
	{
		Testplan testplan = getTestplan(testplanID);
		if(testplan.getProjects() == null || testplan.getProjects().isEmpty() )
		{
			return null;
		}
		return testplan.getProjects();		
	}

	public Set<Requirement> getRequirements(Long testplanID)
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Requirement> requirements = new HashSet<Requirement>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrun.getRequirements() != null && !testrun.getRequirements().isEmpty() )
			{
				requirements.addAll(testrun.getRequirements());
			}			
		}	
		return requirements;
	}

	public Set<Environment> getEnvironments(Long testplanID)
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Environment> environments = new HashSet<Environment>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrun.getEnvironments() != null && !testrun.getEnvironments().isEmpty() )
			{
				environments.addAll(testrun.getEnvironments());
			}			
		}	
		return environments;
	}

	public Set<Defect> getCascadedAllDefects(Long testplanID) 
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrunService.getCascadedAllDefects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedAllDefects(testrun.getTestrunID()).isEmpty() )
			{
				defects.addAll(testrunService.getCascadedAllDefects(testrun.getTestrunID()));
			}			
		}	
		return defects;
	}

	public Set<Defect> getCascadedSev1Defects(Long testplanID) 
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrunService.getCascadedSev1Defects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedSev1Defects(testrun.getTestrunID()).isEmpty() )
			{
				defects.addAll(testrunService.getCascadedSev1Defects(testrun.getTestrunID()));
			}			
		}	
		return defects;
	}

	public Set<Defect> getCascadedSev2Defects(Long testplanID)
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrunService.getCascadedSev2Defects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedSev2Defects(testrun.getTestrunID()).isEmpty() )
			{
				defects.addAll(testrunService.getCascadedSev2Defects(testrun.getTestrunID()));
			}			
		}	
		return defects;
	}

	public Set<Defect> getCascadedSev3Defects(Long testplanID)
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrunService.getCascadedSev3Defects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedSev3Defects(testrun.getTestrunID()).isEmpty() )
			{
				defects.addAll(testrunService.getCascadedSev3Defects(testrun.getTestrunID()));
			}			
		}	
		return defects;
	}

	public Set<Defect> getCascadedSev4Defects(Long testplanID) 
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Defect> defects = new HashSet<Defect>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrunService.getCascadedSev4Defects(testrun.getTestrunID()) != null && 
					!testrunService.getCascadedSev4Defects(testrun.getTestrunID()).isEmpty() )
			{
				defects.addAll(testrunService.getCascadedSev4Defects(testrun.getTestrunID()));
			}			
		}	
		return defects;
	}
	
	public Set<TestcenterUser> getCascadedUsers(Long testplanID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedTesters(Long testplanID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(Long testplanID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(Long testplanID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(Long testplanID) {
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
		columnModelSet.add(new GridAttributes("testplanID",10));	
		colNames.add(company.getTestplanDisplayName()+ " Name");
		columnModelSet.add(new GridAttributes("testplanName",40));

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

		colNames.add(company.getTestcasesDisplayName());
		columnModelSet.add(new GridAttributes("totalTestcases",true));
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

	public Set<Testplan> getFilteredTestplans(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
			{
		// Check which testplans wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Testplan> testplans = new LinkedHashSet<Testplan>();

		if (testcaseID != null && !testcaseID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{			
			Testcase testcase = testcaseService.getTestcase(Long.valueOf(testcaseID));
			testplans.add(getTestplan(testcase.getTestplanID()));			
		}
		else
		{
			testplans.addAll(company.getTestplans());
		}		
		if(testplans == null || testplans.isEmpty()){return null;}

		if (projectID != null && !projectID.isEmpty()) 
		{			
			if(projectService.getCascadedAllTestPlans(Long.valueOf(projectID)) != null)
			{
				testplans.retainAll(projectService.getCascadedAllTestPlans(Long.valueOf(projectID)));
			}	
			else
			{
				testplans.clear();
			}
		}	
		if(testplans == null || testplans.isEmpty()){return null;}

		if (cycleID != null && !cycleID.isEmpty()) 
		{			
			if(cycleService.getCascadedAllTestPlans(Long.valueOf(cycleID)) != null)
			{
				testplans.retainAll(cycleService.getCascadedAllTestPlans(Long.valueOf(cycleID)));
			}	
			else
			{
				testplans.clear();
			}
		}			
		if(testplans == null || testplans.isEmpty()){return null;}

		// Retain Testrun testplans
		if (testrunID != null && !testrunID.isEmpty()) 
		{			
			if(testrunService.getProject(Long.valueOf(testrunID)) != null)
			{
				Set<Testplan> testrunTestplans = new HashSet<Testplan>();
				testrunTestplans.add(testrunService.getTestPlan(Long.valueOf(testrunID)));
				testplans.retainAll(testrunTestplans);				
			}
			else
			{
				testplans.clear();
			}
		}
		if(testplans == null || testplans.isEmpty()){return null;}

		// Retain Defect testplans
		if (defectID != null && !defectID.isEmpty()) // limit to testplans that have this test plan id in it
		{			
			if(defectService.getCascadedAllTestPlans(Long.valueOf(defectID)) != null)
			{
				testplans.retainAll(defectService.getCascadedAllTestPlans(Long.valueOf(defectID)));				
			}	
			else
			{
				testplans.clear();
			}
		}
		if(testplans == null || testplans.isEmpty()){return null;}

		// Retain Requirement testplans
		if (requirementID != null && !requirementID.isEmpty()) // limit to testplans that have this test plan id in it
		{			
			if(requirementService.getAllTestPlans(Long.valueOf(requirementID)) != null)
			{
				testplans.retainAll(requirementService.getAllTestPlans(Long.valueOf(requirementID)));				
			}
			else
			{
				testplans.clear();
			}
		}
		if(testplans == null || testplans.isEmpty()){return null;}

		// Retain Environment testplans
		if (environmentID != null && !environmentID.isEmpty()) // limit to testplans that have this test plan id in it
		{			
			if(environmentService.getAllTestPlans(Long.valueOf(environmentID)) != null)
			{
				testplans.retainAll(environmentService.getAllTestPlans(Long.valueOf(environmentID)));				
			}	
			else
			{
				testplans.clear();
			}
		}
		if(testplans == null || testplans.isEmpty()){return null;}

		// Retain User testplans
		//		if (userID != null && !userID.isEmpty()) // limit to testplans that have this test plan id in it
		//		{			
		//			if(userService.getCascadedProjects(Long.valueOf(defectID)) != null)
		//			{
		//				testplans.retainAll(userService.getCascadedProjects(Long.valueOf(defectID)));				
		//			}	
//		else
//		{
//			testplans.clear();
//		}
		//		}
		//		if(testplans == null || testplans.isEmpty()){return null;}
		return testplans;
			}
	
	public TestplanSummaryList getGridTestplans(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
	{
		
		Set<Testplan> testplans = getFilteredTestplans(companyID, projectID,
				cycleID, testplanID, testcaseID,
				testrunID, defectID, requirementID,
				environmentID, userID,levelName, stage, required);

		Set<TestplanSummary> testplanSummarySet = new HashSet<TestplanSummary>();
		TestplanSummaryList testplanSummaryList = new TestplanSummaryList();
		if(testplans == null || testplans.isEmpty())
		{
			return null;
		}
		for(final Testplan testplan : testplans)
		{						
			testplanSummarySet.add(new TestplanSummary(testplan, levelName, testrunService, defectService));
		}

		testplanSummaryList.setTestplans(testplanSummarySet);
		return testplanSummaryList;
	}
	
	
	public Set<Testplan> getExistingTestplans(Long companyID,String relatedItem, String ID)
	{
		Set<Testplan> existingTestplans = new LinkedHashSet<Testplan>();
		if(relatedItem.equalsIgnoreCase("project"))
		{
			existingTestplans = getFilteredTestplans(companyID,ID,
				null, null, null,null, null, null,null, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("cycle"))
		{
			existingTestplans = getFilteredTestplans(companyID,null,ID,
				null, null,null, null, null,null, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("defect"))
		{
			existingTestplans = getFilteredTestplans(companyID,null,null,
				null, null,null, ID, null,null, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("requirement"))
		{
			existingTestplans = getFilteredTestplans(companyID,null,null,
				null, null,null, null, ID,null, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("environment"))
		{
			existingTestplans = getFilteredTestplans(companyID,null,null,
				null, null,null, null, null, ID, null,null,null,null);
		}
		else if(relatedItem.equalsIgnoreCase("user"))
		{
			existingTestplans = getFilteredTestplans(companyID,null,null,
				null, null,null, null, null, null, ID,null,null,null);
		}		
		return existingTestplans;		
	}

	public Set<Testplan> getAvailableTestplans(Long companyID, String relatedItem,String iD)
	{
		Set<Testplan> allTestplans = companyService.getAllTestPlans(companyID);
		if(allTestplans == null || allTestplans.isEmpty())
		{
			return null;
		}
		Set<Testplan> availableTestplans = new LinkedHashSet<Testplan>();
		if(relatedItem.equalsIgnoreCase("project"))
		{
			for(final Testplan testplan : allTestplans)
			{
				if(testplan.getProjects() == null || testplan.getProjects().isEmpty())
				{
					availableTestplans.add(testplan);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("cycle"))
		{
			for(final Testplan testplan : allTestplans)
			{
				if(getCycles(testplan.getTestplanID()) == null || 
						getCycles(testplan.getTestplanID()).isEmpty())
				{
					availableTestplans.add(testplan);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("defect"))
		{
			for(final Testplan testplan : allTestplans)
			{
				if(getCascadedAllDefects(testplan.getTestplanID()) == null || 
						!getCascadedAllDefects(testplan.getTestplanID()).isEmpty())
				{
					availableTestplans.add(testplan);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("requirement"))
		{
			for(final Testplan testplan : allTestplans)
			{
				if(getRequirements(testplan.getTestplanID())  == null || 
						!getRequirements(testplan.getTestplanID()).isEmpty())
				{
					availableTestplans.add(testplan);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("environment"))
		{
			for(final Testplan testplan : allTestplans)
			{
				if(getEnvironments(testplan.getTestplanID())  == null || 
						!getEnvironments(testplan.getTestplanID()).isEmpty())
				{
					availableTestplans.add(testplan);
				}
			}			
		}
		else if(relatedItem.equalsIgnoreCase("user"))
		{
			for(final Testplan testplan : allTestplans)
			{
				if(getCascadedUsers(testplan.getTestplanID())  == null || 
						!getCascadedUsers(testplan.getTestplanID()).isEmpty())
				{
					availableTestplans.add(testplan);
				}
			}			
		}
		return availableTestplans;
	}
}