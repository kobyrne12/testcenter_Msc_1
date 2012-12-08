/**
 * 
 */
package ie.cit.cloud.testcenter.service.testrun;

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
import ie.cit.cloud.testcenter.model.TestrunLevel;
import ie.cit.cloud.testcenter.model.summary.TestplanSummary;
import ie.cit.cloud.testcenter.model.summary.TestplanSummaryList;
import ie.cit.cloud.testcenter.model.summary.TestrunSummary;
import ie.cit.cloud.testcenter.model.summary.TestrunSummaryList;
import ie.cit.cloud.testcenter.respository.testrun.TestrunRepository;
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
import javax.persistence.Transient;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestrunServiceImpl implements TestrunService {

	@Autowired
	@Qualifier("hibernateTestrunRespository")
	TestrunRepository testrunRepo;      

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
	RequirementService requirementService;
	@Autowired
	EnvironmentService environmentService;


	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testrun getTestrun(Long testrunID) {
		try{
			return testrunRepo.findById(testrunID);					
		}
		catch(NoResultException nre)			
		{	
			return null;
		}			
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testrun getTestrunByName(String testrunName) {
		return testrunRepo.findTestrunByName(testrunName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewTestrun(Testrun testrun) {
		testrunRepo.create(testrun);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Testrun testrun) {
		testrunRepo.update(testrun);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(Long testrunID) {
		testrunRepo.delete(getTestrun(testrunID));
	}

	public void addNewTestrunLevel(TestrunLevel testrunLevel) {
		testrunRepo.createTestrunLevel(testrunLevel);	

	}

	public TestrunLevel getTestrunLevel(Long testrunLevelID) {
		try{
			return testrunRepo.findTestrunLevelById(testrunLevelID);					
		}
		catch(NoResultException nre)			
		{	
			return null;
		}			
	}
	public TestrunLevel getTestrunLevelByName(String testrunLevelName) {
		try{
			return testrunRepo.findTestrunLevelByName(testrunLevelName);					
		}
		catch(NoResultException nre)			
		{	
			return null;
		}			
	}

	public void updateTestrunLevel(TestrunLevel testrunLevel) {
		testrunRepo.updateTestrunLevel(testrunLevel);
	}  

	public void removeTestrunLevel(Long testrunLevelID) {
		testrunRepo.deleteTestrunLevel(getTestrunLevel(testrunLevelID));

	}
	/**
	 * Returns true if this testruns cycle is the latest cycle for a project 
	 * boolean
	 * @return true if this testruns cycle is the latest cycle for a project, otherwise false
	 */  
	public boolean isLatest(Long testrunID)
	{		
		Testrun testrun= getTestrun(testrunID);
		try{
			if(cycleService.isLatest(testrun.getCycleID()))
			{
				return true;
			}
			else
			{
				return false;	
			}    
		}catch(NoResultException nre)
		{
			return false;
		}			
	}
	public Set<Testrun> getTestHistory(Long testrunID) 
	{
		Testrun testrun = getTestrun(testrunID);
		try{
			Testcase testcase = testcaseService.getTestcase(testrun.getTestcaseID());   
			return testcase.getTestruns();
		}catch(NoResultException nre)
		{
			return null;
		}		
	} 

	public boolean isRequired(Long testrunID) 
	{
		Testrun testrun = getTestrun(testrunID);
		try{
			Cycle cycle = cycleService.getCycle(testrun.getCycleID());
			if(testrun.getPriority() <= cycle.getRequiredPriority())
			{
				return true;
			}
			return false;  
		}catch(NoResultException nre)
		{
			return false;
		}
	}   
	//////////////////////////////////

	public Cycle getCycle(Long testrunID)
	{
		Testrun testrun = getTestrun(testrunID);
		try{
			return cycleService.getCycle(testrun.getCycleID());			
		}catch(NoResultException nre)
		{
			return null;
		}
	}

	public Testcase getTestcase(Long testrunID) {
		Testrun testrun = getTestrun(testrunID);
		try{
			return testcaseService.getTestcase(testrun.getTestcaseID());			
		}catch(NoResultException nre)
		{
			return null;
		}
	}

	public Project getProject(Long testrunID)
	{
		Cycle cycle = getCycle(testrunID);
		if(cycle == null)
		{
			return null;
		}
		try{
			return projectService.getProject(cycle.getProjectID());			
		}catch(NoResultException nre)
		{
			return null;
		}	
	}

	public Testplan getTestPlan(Long testrunID) {
		Testcase testcase = getTestcase(testrunID);
		if(testcase == null)
		{
			return null;
		}
		try{
			return testplanService.getTestplan(testcase.getTestplanID());			
		}catch(NoResultException nre)
		{
			return null;
		}	
	}		
	public Set<Defect> getCascadedAllDefects(Long testrunID) 
	{		
		Testrun testrun = getTestrun(testrunID);
		if(testrun == null)
		{
			return null;
		}
		Set<Defect> allDefects = new HashSet<Defect>(); 
		if(testrun.getDefects() != null && !testrun.getDefects().isEmpty())
		{
			allDefects.addAll(testrun.getDefects());			
		}
		///////////////////////////  ??????????????????
		if(testrun.getRequirements() != null && !testrun.getRequirements().isEmpty())
		{
			for(final Requirement requirement : testrun.getRequirements())
			{
				if(requirement.getDefects() != null && !requirement.getDefects().isEmpty())
				{
					allDefects.addAll(requirement.getDefects());
				}	
			}
			allDefects.addAll(testrun.getDefects());			
		}		
		return allDefects;
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
	public Set<TestcenterUser> getCascadedTesters(Long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(Long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(Long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(Long defectID) {
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
		columnModelSet.add(new GridAttributes("testrunID",10));	

		colNames.add(company.getTestrunDisplayName()+ " Name");		
		columnModelSet.add(new GridAttributes("testrunName",40));

		colNames.add(company.getTestcaseDisplayName()+ " Name");
		columnModelSet.add(new GridAttributes("testcaseName",40,true));

		colNames.add(company.getTestplanDisplayName());
		columnModelSet.add(new GridAttributes("testplanName",true));

		colNames.add("State");
		columnModelSet.add(new GridAttributes("state"));

		colNames.add("Level");
		columnModelSet.add(new GridAttributes("levelName"));

		colNames.add("Est. Time");
		columnModelSet.add(new GridAttributes("estimatedTime",true));

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

		colNames.add(company.getCycleDisplayName());
		columnModelSet.add(new GridAttributes("cycleName",true));
		colNames.add(company.getProjectDisplayName());
		columnModelSet.add(new GridAttributes("projectName",true));

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

		colNames.add("Company ID");
		columnModelSet.add(new GridAttributes("companyID",true));


		colModelAndName.setColName(colNames);    	
		colModelAndName.setColModel(columnModelSet);
		return colModelAndName;
	}

	public Set<Testrun> getFilteredTestruns(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
			{
		// Check which testplans wil be displayed 
		Company company = companyService.getCompany(companyID);
		Set<Testrun> testruns = new LinkedHashSet<Testrun>();
		boolean filterByTestcase = true;
		boolean filterByCycle = true;

		if (testcaseID != null && !testcaseID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{	
			Set<Testrun> testcaseTestruns = new LinkedHashSet<Testrun>();
			if(required == null)
			{
				testcaseTestruns = testcaseService.getAllTestRuns(Long.valueOf(testcaseID));
			}
			else
			{
				if(required.equalsIgnoreCase("required"))
				{
					testcaseTestruns = testcaseService.getRequiredTestRuns(Long.valueOf(testcaseID));
				}
				else if(required.equalsIgnoreCase("optional"))
				{
					testcaseTestruns = testcaseService.getOptionalTestRuns(Long.valueOf(testcaseID));
				}
				else
				{
					testcaseTestruns = testcaseService.getAllTestRuns(Long.valueOf(testcaseID));
				}				
			}
			if(testcaseTestruns == null || testcaseTestruns.isEmpty())
			{
				return null;
			}
			else
			{
				testruns.addAll(testcaseTestruns);
			}	
		}
		else
		{
			filterByTestcase = false;
		}

		if (cycleID != null && !cycleID.isEmpty()) // A cycle can only have one project hence we only need to add 1 project
		{		
			Set<Testrun> cycleTestruns = new LinkedHashSet<Testrun>();
			if(required == null)
			{
				cycleTestruns = cycleService.getCascadedAllTestRuns(Long.valueOf(cycleID));
			}
			else
			{
				if(required.equalsIgnoreCase("required"))
				{
					cycleTestruns = cycleService.getCascadedCompulsoryTestRuns(Long.valueOf(cycleID));
				}
				else if(required.equalsIgnoreCase("optional"))
				{
					cycleTestruns = cycleService.getCascadedOptionalTestRuns(Long.valueOf(cycleID));
				}
				else
				{
					cycleTestruns = cycleService.getCascadedAllTestRuns(Long.valueOf(cycleID));
				}
			}

			if(cycleTestruns == null || cycleTestruns.isEmpty())
			{
				return null;
			}
			else
			{
				testruns.addAll(cycleTestruns);
			}			
		}
		else
		{			
			filterByCycle = false;
		}

		if(!filterByTestcase && !filterByCycle)
		{
			if(required == null)
			{
				Set<Testrun> companyTestruns = companyService.getAllTestRuns(companyID);
				if(companyTestruns != null && !companyTestruns.isEmpty())
				{
					testruns.addAll(companyTestruns);
				}
			}
			else
			{
				if(required.equalsIgnoreCase("required"))
				{
					Set<Testrun> requiredTestruns = companyService.getCompulsoryTestRuns(companyID);
					if(requiredTestruns != null && !requiredTestruns.isEmpty())
					{
						testruns.addAll(requiredTestruns);
					}					
				}
				else if(required.equalsIgnoreCase("optional"))
				{
					Set<Testrun> optionalTestruns = companyService.getOptionalTestRuns(companyID);
					if(optionalTestruns != null && !optionalTestruns.isEmpty())
					{
						testruns.addAll(optionalTestruns);
					}			
				}
				else
				{	
					Set<Testrun> companyTestruns = companyService.getAllTestRuns(companyID);
					if(companyTestruns != null && !companyTestruns.isEmpty())
					{
						testruns.addAll(companyTestruns);
					}
				}
			}
		}		
		if(testruns == null || testruns.isEmpty()){return null;}

		if (testplanID != null && !testplanID.isEmpty()) // limit to testruns that have this test run id in it
		{	
			Set<Testrun> testplanTestruns = new LinkedHashSet<Testrun>();
			if(required == null)
			{
				testplanTestruns = testplanService.getAllTestRuns(Long.valueOf(testplanID));
			}
			else
			{
				if(required.equalsIgnoreCase("required"))
				{
					testplanTestruns = testplanService.getCompulsoryTestRuns(Long.valueOf(testplanID));
				}
				else if(required.equalsIgnoreCase("optional"))
				{
					testplanTestruns = testplanService.getOptionalTestRuns(Long.valueOf(testplanID));
				}
				else
				{
					testplanTestruns = testplanService.getAllTestRuns(Long.valueOf(testplanID));
				}					
			}
			if(testplanTestruns != null)
			{
				testruns.retainAll(testplanTestruns);				
			}
			else
			{
				testruns.clear();
			}
		}
		if(testruns == null || testruns.isEmpty()){return null;}

		if (projectID != null && !projectID.isEmpty()) 
		{		
			Set<Testrun> projectTestruns = new LinkedHashSet<Testrun>();
			if(required == null)
			{
				projectTestruns = projectService.getCascadedAllTestRuns(Long.valueOf(projectID));
			}
			else
			{
				if(required.equalsIgnoreCase("required"))
				{
					projectTestruns = projectService.getCascadedCompulsoryTestRuns(Long.valueOf(projectID));
				}
				else if(required.equalsIgnoreCase("optional"))
				{
					projectTestruns = projectService.getCascadedOptionalTestRuns(Long.valueOf(projectID));
				}
				else
				{
					projectTestruns = projectService.getCascadedAllTestRuns(Long.valueOf(projectID));
				}	
			}

			if(projectTestruns != null)
			{
				testruns.retainAll(projectTestruns);
			}	
			else
			{
				testruns.clear();
			}
		}	
		if(testruns == null || testruns.isEmpty()){return null;}		


		// Retain Defect testruns
		if (defectID != null && !defectID.isEmpty()) // limit to testruns that have this test run id in it
		{			
			Set<Testrun> defectTestruns = new LinkedHashSet<Testrun>();
			if(required == null)
			{
				defectTestruns = defectService.getCascadedAllTestRuns(Long.valueOf(defectID));
			}
			else
			{
				if(required.equalsIgnoreCase("required"))
				{
					defectTestruns = defectService.getCascadedCompulsoryTestRuns(Long.valueOf(defectID));
				}
				else if(required.equalsIgnoreCase("optional"))
				{
					defectTestruns = defectService.getCascadedOptionalTestRuns(Long.valueOf(defectID));
				}
				else
				{
					defectTestruns = defectService.getCascadedAllTestRuns(Long.valueOf(defectID));
				}
			}
			if(defectTestruns != null)
			{
				testruns.retainAll(defectTestruns);				
			}	
			else
			{
				testruns.clear();
			}
		}
		if(testruns == null || testruns.isEmpty()){return null;}

		// Retain Requirement testruns
		if (requirementID != null && !requirementID.isEmpty()) // limit to testruns that have this test run id in it
		{	
			Set<Testrun> requirementTestruns = new LinkedHashSet<Testrun>();
			if(required == null)
			{
				requirementTestruns = requirementService.getAllTestRuns(Long.valueOf(requirementID));
			}
			else
			{
				if(required.equalsIgnoreCase("required"))
				{
					requirementTestruns = requirementService.getCompulsoryTestRuns(Long.valueOf(requirementID));
				}
				else if(required.equalsIgnoreCase("optional"))
				{
					requirementTestruns = requirementService.getOptionalTestRuns(Long.valueOf(requirementID));
				}
				else
				{
					requirementTestruns = requirementService.getAllTestRuns(Long.valueOf(requirementID));
				}	
			}
			if(requirementTestruns != null)
			{
				testruns.retainAll(requirementTestruns);				
			}
			else
			{
				testruns.clear();
			}
		}
		if(testruns == null || testruns.isEmpty()){return null;}

		// Retain Environment testruns
		if (environmentID != null && !environmentID.isEmpty()) // limit to testruns that have this test run id in it
		{		
			Set<Testrun> environmentTestruns = new LinkedHashSet<Testrun>();
			if(required == null)
			{
				environmentTestruns = environmentService.getAllTestRuns(Long.valueOf(environmentID));
			}
			else
			{
				if(required.equalsIgnoreCase("required"))
				{
					environmentTestruns = environmentService.getCompulsoryTestRuns(Long.valueOf(environmentID));
				}
				else if(required.equalsIgnoreCase("optional"))
				{
					environmentTestruns = environmentService.getOptionalTestRuns(Long.valueOf(environmentID));
				}
				else
				{
					environmentTestruns = environmentService.getAllTestRuns(Long.valueOf(environmentID));
				}	
			}
			if(environmentTestruns != null)
			{
				testruns.retainAll(environmentTestruns);				
			}	
			else
			{
				testruns.clear();
			}
		}
		if(testruns == null || testruns.isEmpty()){return null;}

		// Retain User testruns
		//		if (userID != null && !userID.isEmpty()) // limit to testruns that have this test run id in it
		//		{			
		//			if(userService.getCascadedProjects(Long.valueOf(defectID)) != null)
		//			{
		//				testruns.retainAll(userService.getCascadedProjects(Long.valueOf(defectID)));				
		//			}	
		//		else
		//		{
		//			testruns.clear();
		//		}
		//		}
		//		if(testruns == null || testruns.isEmpty()){return null;}
		return testruns;
			}

	public TestrunSummaryList getGridTestruns(Long companyID, String projectID,
			String cycleID, String testplanID, String testcaseID,
			String testrunID, String defectID, String requirementID,
			String environmentID, String userID,String levelName,String stage,String required)
	{

		Set<Testrun> testruns = getFilteredTestruns(companyID, projectID,
				cycleID, testrunID, testcaseID,
				testrunID, defectID, requirementID,
				environmentID, userID,levelName, stage, required);

		Set<TestrunSummary> testrunSummarySet = new HashSet<TestrunSummary>();
		TestrunSummaryList testrunSummaryList = new TestrunSummaryList();
		if(testruns == null || testruns.isEmpty())
		{
			return null;
		}
		for(final Testrun testrun : testruns)
		{		
			testrunSummarySet.add(new TestrunSummary(testrun, levelName, testcaseService, defectService,this));
		}
		testrunSummaryList.setTestruns(testrunSummarySet);
		return testrunSummaryList;
	}


	public Set<Testrun> getExistingTestruns(Long companyID,String relatedItem, String ID, String required)
	{
		Set<Testrun> existingTestruns = new LinkedHashSet<Testrun>();
		if(relatedItem.equalsIgnoreCase("project"))
		{
			existingTestruns = getFilteredTestruns(companyID,ID,
					null, null, null,null, null, null,null, null,null,null,required);
		}
		else if(relatedItem.equalsIgnoreCase("cycle"))
		{
			existingTestruns = getFilteredTestruns(companyID,null,ID,
					null, null,null, null, null,null, null,null,null,required);
		}
		else if(relatedItem.equalsIgnoreCase("testcase"))
		{
			existingTestruns = getFilteredTestruns(companyID,null,null,
					null, ID,null, null, null,null, null,null,null,required);
		}
		else if(relatedItem.equalsIgnoreCase("defect"))
		{
			existingTestruns = getFilteredTestruns(companyID,null,null,
					null, null,null, ID, null,null, null,null,null,required);
		}
		else if(relatedItem.equalsIgnoreCase("requirement"))
		{
			existingTestruns = getFilteredTestruns(companyID,null,null,
					null, null,null, null, ID,null, null,null,null,required);
		}
		else if(relatedItem.equalsIgnoreCase("environment"))
		{
			existingTestruns = getFilteredTestruns(companyID,null,null,
					null, null,null, null, null, ID, null,null,null,required);
		}
		else if(relatedItem.equalsIgnoreCase("user"))
		{
			existingTestruns = getFilteredTestruns(companyID,null,null,
					null, null,null, null, null, null, ID,null,null,required);
		}		
		return existingTestruns;		
	}

	public Set<Testrun> getAvailableTestruns(Long companyID, String relatedItem,String ID, String required)
	{

		Set<Testrun> allTestruns = new LinkedHashSet<Testrun>();
		if(required.equalsIgnoreCase("required"))
		{
			allTestruns = companyService.getCompulsoryTestRuns(companyID);
		}
		else if(required.equalsIgnoreCase("optional"))
		{
			allTestruns = companyService.getOptionalTestRuns(companyID);
		}
		else
		{
			allTestruns = companyService.getAllTestRuns(companyID);
		}			

		if(allTestruns == null || allTestruns.isEmpty())
		{
			return null;
		}
		Set<Testrun> availableTestruns = new LinkedHashSet<Testrun>();

		if(relatedItem.equalsIgnoreCase("defect"))
		{// return testruns the dfect is not already apart of

			Defect defect = defectService.getDefect(Long.valueOf(ID));
			for(final Testrun testrun : allTestruns)
			{
				boolean defectFound = false;
				Set<Defect> testrunDefects = testrun.getDefects();
				if(testrunDefects != null && !testrunDefects.isEmpty())
				{
					for(final Defect testrunDefect : testrunDefects )
					{
						if(testrunDefect.getDefectID() == defect.getDefectID())
						{
							defectFound = true;
						}
					}
				}					
				if(!defectFound)
				{
					availableTestruns.add(testrun);
				}				
			}			
		}
		else if(relatedItem.equalsIgnoreCase("requirement"))
		{
			Requirement requirement = requirementService.getRequirement(Long.valueOf(ID));
			for(final Testrun testrun : allTestruns)
			{
				boolean requirementFound = false;
				Set<Requirement> testrunRequirements = testrun.getRequirements();
				if(testrunRequirements != null && !testrunRequirements.isEmpty())
				{
					for(final Requirement testrunRequirement : testrunRequirements )
					{
						if(testrunRequirement.getRequirementID() == requirement.getRequirementID())
						{
							requirementFound = true;
						}
					}
				}					
				if(!requirementFound)
				{
					availableTestruns.add(testrun);
				}				
			}				
		}
		else if(relatedItem.equalsIgnoreCase("environment"))
		{
			Environment environment = environmentService.getEnvironment(Long.valueOf(ID));
			for(final Testrun testrun : allTestruns)
			{
				boolean environmentFound = false;
				Set<Environment> testrunEnvironments = testrun.getEnvironments();
				if(testrunEnvironments != null && !testrunEnvironments.isEmpty())
				{
					for(final Environment testrunEnvironment : testrunEnvironments )
					{
						if(testrunEnvironment.getEnvironmentID() == environment.getEnvironmentID())
						{
							environmentFound = true;
						}
					}
				}					
				if(!environmentFound)
				{
					availableTestruns.add(testrun);
				}				
			}				
		}
		else if(relatedItem.equalsIgnoreCase("user"))
		{
			//TestcenterUser testcenterUser = userService.getTestcenterUser(Long.valueOf(ID));
			for(final Testrun testrun : allTestruns)
			{
				boolean testcenterUserFound = false;
				Set<TestcenterUser> testrunTestcenterUsers = testrun.getUsers();
				if(testrunTestcenterUsers != null && !testrunTestcenterUsers.isEmpty())
				{
					for(final TestcenterUser testrunTestcenterUser : testrunTestcenterUsers )
					{
						//						if(testrunTestcenterUser.getUserID() == testcenterUser.getUserID())
						//						{
						//							testcenterUserFound = true;
						//						}
					}
				}					
				if(!testcenterUserFound)
				{
					availableTestruns.add(testrun);
				}				
			}					
		}
		return availableTestruns;
	}
}