/**
 * 
 */
package ie.cit.cloud.testcenter.service.cycle;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.model.TestPlan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.respository.cycle.CycleRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CycleServiceImpl implements CycleService {

	@Autowired
	@Qualifier("hibernateCycleRespository")
	CycleRepository cycleRepo;      
	@Autowired
	CompanyService companyService;
	@Autowired
	ProjectService projectService;	

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Cycle> getAllCycles() {
		return cycleRepo.findAll();
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Cycle getCycle(long cycleID) {
		return cycleRepo.findById(cycleID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Cycle getCycleByName(String cycleName) {
		return cycleRepo.findCycleByName(cycleName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public long addNewCycle(Cycle cycle) {
		cycleRepo.create(cycle);	
		return cycle.getCycleID();
	}

	// @Secured("ROLE_ADMIN")
	public void update(Cycle cycle) {
		cycleRepo.update(cycle);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long cycleID) {
		cycleRepo.delete(cycleRepo.get(cycleID));
	}


	public boolean updateCycle(Cycle cycle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Cycle> getAllCyclesByProjectID(long projectID) {
		return cycleRepo.findAllCyclesByProjectID(projectID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Cycle> getAllCyclesByCompanyID(long companyID) {
		Collection<Cycle> cycles = new ArrayList<Cycle>();
		Company company = companyService.getCompany(companyID);
		Collection<Project> projects = company.getProjects();
		Iterator<Project> projectsItr = projects.iterator();	
		while (projectsItr.hasNext()) 
		{				
			Project project = projectsItr.next();				 
			cycles.addAll(project.getCycles());			
		}		 
		return cycles;
	}

	public Collection<Cycle> getChildCycles(long cycleID) {
		return cycleRepo.findAllCyclesByParentID(cycleID);
	}


	public int getMaxProjectPosNum(long projectID) {		
		return cycleRepo.getMaxProjectPosNum(projectID) + 1;
	}	   
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public CycleSummary getCycleSummary(long cycleID) {
		Collection<Cycle> childCycles = getChildCycles(cycleID);
		return getAllCycleDetails(cycleID,childCycles);		
	}
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	private CycleSummary getAllCycleDetails(long cycleID, Collection<Cycle> childCycles) {

		Cycle cycle = getCycle(cycleID);		
		Project project = projectService.getProject(cycle.getProjectID());		
		CycleSummary cycleSummary = new CycleSummary();
		
// Cycle info only 
		cycleSummary.setCycleID(cycleID);
		cycleSummary.setCycleName(cycle.getCycleName());	
		cycleSummary.setRequiredPriority(cycle.getRequiredPriority());
//child Cycle applicable
		
		//Collection<Testrun> allTestruns = testrunService.getAllTestruns(cycleID)
		//Collection<Testrun> requiredTestruns = testrunService.getRequiredTestruns(cycleID)
		
		
//		if (childCycles != null)
//		{
//			CycleSummary tempCycleSummary = new CycleSummary();
//			Iterator<Cycle> childCyclesItr = childCycles.iterator();	
//			while (childCyclesItr.hasNext()) 
//			{ // for each child  cycle add the totals to the parent cycle 
//				
//				Cycle childCycle = childCyclesItr.next();
//				tempCycleSummary = getCycleSummary(childCycle.getCycleID());
//				allTestruns.addAll(testrunService.getAllTestruns(cycleID));
//				requiredTestruns.addAll(testrunService.getAllTestruns(cycleID));
//				
//				
//			}
//		}
			
		//int numOfAllTestruns = allTestruns.size();
			int numOfAllTestruns = 500 ;		
		cycleSummary.setTotalAllTestruns(numOfAllTestruns);		

		//int requiredTestruns = requiredTestruns.size();
		int requiredTestruns = 400 ;		
		cycleSummary.setTotalRequiredTestruns(requiredTestruns);

		//int testrunsPassed = testrunService.getRequiredTestrunsPassed(requiredTestruns,cycle.getRequiredPriority());
		int testrunsPassed = 200;
		cycleSummary.setTotalTestrunsPassed(testrunsPassed);
		//int testrunsFailed = testrunService.getRequiredTestrunsFailed(requiredTestruns,cycle.getRequiredPriority());
		int testrunsFailed = 80;
		cycleSummary.setTotalTestrunsFailed(testrunsFailed);
		//int testrunsDeferred = testrunService.getRequiredTestrunsDeferred(requiredTestruns,cycle.getRequiredPriority());
		int testrunsDeferred = 10;
		cycleSummary.setTotalTestrunsDeferred(testrunsDeferred);
		//int testrunsBlocked = testrunService.getRequiredTestrunsBlocked(requiredTestruns,cycle.getRequiredPriority());
		int testrunsBlocked = 10;
		cycleSummary.setTotalTestrunsBlocked(testrunsBlocked);

		//int testrunsNotRun = testrunService.getRequiredTestrunsNotRun(requiredTestruns,cycle.getRequiredPriority());
		int testrunsNotRun = 80;
		cycleSummary.setTotalTestrunsNotRun(testrunsNotRun);
		//int testrunsInProg = testrunService.getRequiredTestrunsInProg(requiredTestruns,cycle.getRequiredPriority());
		int testrunsInProg = 20;
		cycleSummary.setTotalTestrunsInProg(testrunsInProg);			

		int testrunsCompleted = testrunsPassed + testrunsFailed + testrunsDeferred + testrunsBlocked;		
		cycleSummary.setTotalTestrunsCompleted(testrunsCompleted);
		
		int testrunsNotCompleted = testrunsNotRun + testrunsInProg;
		cycleSummary.setTotalTestrunsNotCompleted(testrunsNotCompleted);

		//double totalCycleEstTime = testrunService.getTotalCycleEstTime(requiredTestruns,cycle.getRequiredPriority());
		double totalCycleEstTime = 0.15;
		cycleSummary.setTotalCycleEstTime(totalCycleEstTime);
		cycleSummary.setCycleStartDate(cycle.getCycleStartDate());
		cycleSummary.setCycleEndDate(cycle.getCycleEndDate());

		long parentCycleID = cycle.getParentID();
		if(parentCycleID != 0)
		{
			Cycle parentCycle = getCycle(parentCycleID);
			cycleSummary.setParentCycleName(parentCycle.getCycleName());		
		}
		else
		{			
			cycleSummary.setParentCycleName("");
		}

		cycleSummary.setTotalChildCycles((getChildCycles(cycleID)) != null ? getChildCycles(cycleID).size() : 0);

		//int totalDefects = sort out the many to many tables then: 
		//Collection<Defect> defects = defectService.getDefectsByTestruns(requiredTestruns)
		// All distinct defects for each required test run
		int totalDefects = 100;
		cycleSummary.setTotalDefects(totalDefects);
		cycleSummary.setTotalAllowedSev1s(project.getAllowedSev1());
		cycleSummary.setTotalAllowedSev2s(project.getAllowedSev2());
		cycleSummary.setTotalAllowedSev3s(project.getAllowedSev3());
		cycleSummary.setTotalAllowedSev4s(project.getAllowedSev4());
		int currentSev1 = 0;
		int currentSev2 = 0;
		int currentSev3 = 0;
		int currentSev4 = 0;
		//for(String defect : defects)
		// {
		//	 if(defect.getSeverity = 1){currentSev1++;}
		//	 else if(defect.getSeverity = 2){currentSev2++;}
		//	 else if(defect.getSeverity = 3){currentSev3++;}
		//	 else{{currentSev4++;}
		// }		
		currentSev1 = 20;
		cycleSummary.setTotalCurrentSev1s(currentSev1);
		currentSev2 = 30;
		cycleSummary.setTotalCurrentSev2s(currentSev2);
		currentSev3 = 25;
		cycleSummary.setTotalCurrentSev3s(currentSev3);
		currentSev4 = 25;
		cycleSummary.setTotalCurrentSev4s(currentSev4);

		cycleSummary.setCompanyID(project.getCompanyID());	
		cycleSummary.setProjectID(project.getProjectID());
		cycleSummary.setProjectName(project.getProjectName());
		cycleSummary.setTotalDefectRules(cycle.getDefectRule());
		cycleSummary.setTotalTestHistoryRules(cycle.getTestHistoryRule());
		cycleSummary.setTotalCodeImpactRules(cycle.getCodeImpactRule());
		cycleSummary.setTotalReqRules(cycle.getRequirementRule());

		
		//Collection<Testcase> testcases = testplanService.getTestcasesByTestruns(requiredTestruns)
		//int numOfTestcases = testcases.size(); 
		int numOfTestcases = 50;
		cycleSummary.setTotalTestcases(numOfTestcases);		

		//Collection<Testplan> testplans = testplanService.getTestplansByTestcases(testcases)
		//int numOfTestplans = testplans.size(); 
		int numOfTestplans = 50;
		cycleSummary.setTotalTestplans(numOfTestplans);		

		//Collection<Environment> environments = environmentService.getEnvironmentByTestruns(requiredTestruns)
		//int numOfTotalEnvironments = environments.size(); 
		int numOfTotalEnvironments = 100;
		cycleSummary.setTotalEnvironments(numOfTotalEnvironments);

		//Collection<Requirement> requirements = environmentService.getEnvironmentByTestruns(requiredTestruns)
		//int numOftotalEnvironments = requirements.size(); 
		int numOftotalRequirements = 100;
		cycleSummary.setTotalRequirements(numOftotalRequirements);

		//		Collection<User> testers = new ArrayList<User>();		
		//		if(testRunTesters != null) {testers.addAll(userService.getTestersByTestruns(requiredTestruns));}
		//		if(defectTesters != null) {testers.addAll(userService.getTestersByDefects(defects));}
		//		if(environmentTesters != null) {testers.addAll(userService.getTestersByEnvironments(environments));}		
		//		if(requirementTesters != null) {testers.addAll(userService.getTestersByRequirements(requirements));}
		//int numOfTesters = testers.size(); 
		int numOfTesters = 100;
		cycleSummary.setTotalTesters(numOfTesters);

		//		Collection<User> seniorTesters = new ArrayList<User>();		
		//		if(testRunTesters != null) {seniorTesters.addAll(userService.getSnrTestersByTestruns(requiredTestruns));}
		//		if(defectTesters != null) {seniorTesters.addAll(userService.getSnrTestersByDefects(defects));}
		//		if(environmentTesters != null) {seniorTesters.addAll(userService.getSnrTestersByEnvironments(environments));}		
		//		if(requirementTesters != null) {seniorTesters.addAll(userService.getSnrTestersByRequirements(requirements));}
		//int numOfSeniorTesters = seniorTesters.size(); 
		int numOfSeniorTesters = 100;
		cycleSummary.setTotalSeniorTesters(numOfSeniorTesters);		

		//		Collection<User> developers = new ArrayList<User>();		
		//		if(testRunTesters != null) {developers.addAll(userService.getUsersByTestruns(requiredTestruns));}
		//		if(defectTesters != null) {developers.addAll(userService.getUsersByDefects(defects));}
		//		if(environmentTesters != null) {developers.addAll(userService.getUsersByEnvironments(environments));}		
		//		if(requirementTesters != null) {developers.addAll(userService.getUsersByRequirements(requirements));}
		//int numOfDevelopers = developers.size(); 
		int numOfDevelopers = 100;
		cycleSummary.setTotalDevelopers(numOfDevelopers);		

		//		Collection<User> seniorDevelopers = new ArrayList<User>();		
		//		if(testRunTesters != null) {seniorDevelopers.addAll(userService.getSnrTestersByTestruns(requiredTestruns));}
		//		if(defectTesters != null) {seniorDevelopers.addAll(userService.getSnrTestersByDefects(defects));}
		//		if(environmentTesters != null) {seniorDevelopers.addAll(userService.getSnrTestersByEnvironments(environments));}		
		//		if(requirementTesters != null) {seniorDevelopers.addAll(userService.getSnrTestersByRequirements(requirements));}
		//int numOfSeniorDevelopers = seniorDevelopers.size(); 
		int numOfSeniorDevelopers = 100;
		cycleSummary.setTotalSeniorDevelopers(numOfSeniorDevelopers);	

		cycleSummary.setProjectPosition(cycle.getProjectPosition());		

		cycleSummary.setLastModifiedBy(cycle.getLastModifiedBy());
		cycleSummary.setLastModifiedDate(cycle.getLastModifiedDate());
		cycleSummary.setCreatedBy(cycle.getCreatedBy());
		cycleSummary.setCreationDate(cycle.getCreationDate());

		return cycleSummary;
	}


	public ColModelAndNames getColumnModelAndNames(Long companyID)
	{
		Company company = companyService.getCompany(companyID);
		Collection<String> colNames = new ArrayList<String>();
		ColModelAndNames colModelAndName = new ColModelAndNames();
		Collection<GridAttributes> columnModelCollection =  new ArrayList<GridAttributes>();	

		colNames.add("ID");
		columnModelCollection.add(new GridAttributes("cycleID",10));	
		
		colNames.add(company.getCycleDisplayName()+ " Name");
		columnModelCollection.add(new GridAttributes("cycleName",40));	 

		colNames.add("Required Priority");
		columnModelCollection.add(new GridAttributes("requiredPriority",10));	 	

		colNames.add("State");
		columnModelCollection.add(new GridAttributes("cycleState","setCycleStateBarChart","unSetBarChart", 80));
		
		colNames.add("Required "+company.getTestrunsDisplayName());
		columnModelCollection.add(new GridAttributes("totalRequiredTestruns",10,true));	
		
		colNames.add("All "+company.getTestrunsDisplayName());
		columnModelCollection.add(new GridAttributes("totalAllTestruns",10,true));		
		
		colNames.add("Complete");
		columnModelCollection.add(new GridAttributes("totalTestrunsCompleted",10,true));
		colNames.add("Passed");
		columnModelCollection.add(new GridAttributes("totalTestrunsPassed",10,true));
		colNames.add("Failed");
		columnModelCollection.add(new GridAttributes("totalTestrunsFailed",10,true));	
		colNames.add("Deferred");
		columnModelCollection.add(new GridAttributes("totalTestrunsDeferred",10,true));
		colNames.add("Blocked");
		columnModelCollection.add(new GridAttributes("totalTestrunsBlocked",10,true));
		colNames.add("Not Complete");
		columnModelCollection.add(new GridAttributes("totalTestrunsNotCompleted",10,true));
		colNames.add("Not Run");
		columnModelCollection.add(new GridAttributes("totalTestrunsNotRun",10,true));
		colNames.add("In Prog");
		columnModelCollection.add(new GridAttributes("totalTestrunsInProg",10,true));

		colNames.add("Est.Time");
		columnModelCollection.add(new GridAttributes("totalCycleEstTime",15,true));

		colNames.add("Start Date");
		columnModelCollection.add(new GridAttributes("cycleStartDate",20,true));

		colNames.add("End Date");
		columnModelCollection.add(new GridAttributes("cycleEndDate",20,true));	

		colNames.add("Parent");
		columnModelCollection.add(new GridAttributes("parentCycleName",25,true));

		colNames.add("Child "+ company.getCyclesDisplayName());	
		columnModelCollection.add(new GridAttributes("totalChildCycles",15,true));	

		colNames.add("Total "+ company.getDefectsDisplayName());
		columnModelCollection.add(new GridAttributes("totalDefects"));
		colNames.add("Max Sev 1s");
		columnModelCollection.add(new GridAttributes("totalAllowedSev1s",true));
		colNames.add("Current Sev 1s");
		columnModelCollection.add(new GridAttributes("totalCurrentSev1s"));
		colNames.add("Max Sev 2s");
		columnModelCollection.add(new GridAttributes("totalAllowedSev2s",true));
		colNames.add("Current Sev 2s");
		columnModelCollection.add(new GridAttributes("totalCurrentSev2s"));
		colNames.add("Max Sev 3s");
		columnModelCollection.add(new GridAttributes("totalAllowedSev3s",true));
		colNames.add("Current Sev 3s");
		columnModelCollection.add(new GridAttributes("totalCurrentSev3s"));	
		colNames.add("Max Sev 4s");
		columnModelCollection.add(new GridAttributes("totalAllowedSev4s",true));
		colNames.add("Current Sev 4s");
		columnModelCollection.add(new GridAttributes("totalCurrentSev4s"));

		colNames.add("Company ID");
		columnModelCollection.add(new GridAttributes("companyID",true));
		colNames.add("Project ID");
		columnModelCollection.add(new GridAttributes("projectID",true));
		colNames.add("Project Name");
		columnModelCollection.add(new GridAttributes("projectName",true));

		colNames.add(company.getDefectDisplayName()+" Rules");
		columnModelCollection.add(new GridAttributes("totalDefectRules",true));
		colNames.add("Test History Rules");
		columnModelCollection.add(new GridAttributes("totalTestHistoryRules",true));
		colNames.add("Code Impact Rules");
		columnModelCollection.add(new GridAttributes("totalCodeImpactRules",true));
		colNames.add(company.getRequirementDisplayName()+" Rules");
		columnModelCollection.add(new GridAttributes("totalReqRules",true));	

		 
		colNames.add(company.getTestplansDisplayName());
		columnModelCollection.add(new GridAttributes("totalTestplans",true));
		colNames.add(company.getTestcasesDisplayName());
		columnModelCollection.add(new GridAttributes("totalTestcases",true));	 

		colNames.add(company.getEnvironmentsDisplayName());
		columnModelCollection.add(new GridAttributes("totalEnvironments",true));
		colNames.add(company.getRequirementsDisplayName());
		columnModelCollection.add(new GridAttributes("totalRequirements",true));

		colNames.add(company.getTestersDisplayName());
		columnModelCollection.add(new GridAttributes("totalTesters",true));
		colNames.add(company.getSeniorTestersDisplayName());
		columnModelCollection.add(new GridAttributes("totalSeniorTesters",true));
		colNames.add(company.getDevelopersDisplayName());
		columnModelCollection.add(new GridAttributes("totalDevelopers",true));
		colNames.add(company.getSeniordevelopersDisplayName());
		columnModelCollection.add(new GridAttributes("totalSeniorDevelopers",true));

		colNames.add("LastModifiedDate");
		columnModelCollection.add(new GridAttributes("lastModifiedDate",true));
		colNames.add("LastModifiedBy");
		columnModelCollection.add(new GridAttributes("lastModifiedBy",true));	
		colNames.add("CreatedBy");
		columnModelCollection.add(new GridAttributes("createdBy",true));
		colNames.add("CreationDate");
		columnModelCollection.add(new GridAttributes("creationDate",true));
		colNames.add("Position");
		columnModelCollection.add(new GridAttributes("projectPosition",true));	 

		// if customValue1 set then 	
		//colNames.add("customValue1");
		//columnModelCollection.add(new GridAttributes("customColumn1",true));	 
		// if customValue2 set then 
		//colNames.add("customValue1");
		//columnModelCollection.add(new GridAttributes("customColumn2",true));		
		// if customValue3 set then 
		//colNames.add("customValue3");
		//columnModelCollection.add(new GridAttributes("customColumn3",true));		
		// if customValue4 set then 
		//colNames.add("customValue4");	
		//columnModelCollection.add(new GridAttributes("customColumn4",true));		
		// if customValue5 set then 
		//colNames.add("customValue5");	
		//columnModelCollection.add(new GridAttributes("customColumn5",true));	

		colModelAndName.setColName(colNames);    	
		colModelAndName.setColModel(columnModelCollection);
		return colModelAndName;
	}

	public CycleSummaryList getGridCycles(long companyID,String projectID, String testplanID,
			String testcaseID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID)
	{

		Collection<CycleSummary> cycleSummaryCollection = new ArrayList<CycleSummary>();
		CycleSummaryList cycleSummaryList = new CycleSummaryList();

		Collection<Cycle> cycles = getAllCyclesByCompanyID(companyID);

		if (projectID != null)
		{
			long projectID_long = Long.valueOf(projectID).longValue();
			Collection<Cycle> projectCycles = getAllCyclesByProjectID(projectID_long);			 
			cycles.retainAll(projectCycles);
		}
		// TODO: Finish filter when entities are created
		if (testcaseID != null)
		{
			long testcaseID_long = Long.valueOf(testcaseID).longValue();			
			
		}	
		if (testplanID != null)
		{
			long testplanID_long = Long.valueOf(testplanID).longValue();			
			
		}	
		if (environmentID != null){long environmentID_long = Long.valueOf(environmentID).longValue();	}
		if (requirementID != null){long requirementID_long = Long.valueOf(requirementID).longValue();	}
		if (defectID != null){long defectID_long = Long.valueOf(defectID).longValue();	}
		if (testrunID != null){long testrunID_long = Long.valueOf(testrunID).longValue();	}
		if (userID != null){long userID_long = Long.valueOf(userID).longValue();	}

		if(cycles.isEmpty())
		{
			return null;
		}
		else
		{
			Iterator<Cycle> cyclesItr = cycles.iterator();	
			while (cyclesItr.hasNext()) 
			{				
				CycleSummary cycleSummary = new CycleSummary();
				Cycle cycle = cyclesItr.next();					
				cycleSummary = getCycleSummary(cycle.getCycleID());					
				cycleSummaryCollection.add(cycleSummary);
			}

			cycleSummaryList.setCycles(cycleSummaryCollection);
			return cycleSummaryList;
		}
	}
//	if(childCycles != null)
//	{
//		CycleSummary tempCycleSummary = new CycleSummary();
//		Iterator<Cycle> childCyclesItr = childCycles.iterator();	
//		while (childCyclesItr.hasNext()) 
//		{ // for each child  cycle add the totals to the parent cycle 
//			Cycle childCycle = childCyclesItr.next();
//			tempCycleSummary = getCycleSummary(childCycle.getCycleID());
//			
//			cycleSummary.setTotalAllTestruns(cycleSummary.getTotalAllTestruns() + tempCycleSummary.getTotalAllTestruns());
//			cycleSummary.setTotalRequiredTestruns(cycleSummary.getTotalRequiredTestruns() + tempCycleSummary.getTotalRequiredTestruns());
//			
//			cycleSummary.setTotalCurrentSev1s(cycleSummary.getTotalCurrentSev1s() + tempCycleSummary.getTotalCurrentSev1s());
//			cycleSummary.setTotalCurrentSev2s(cycleSummary.getTotalCurrentSev2s() + tempCycleSummary.getTotalCurrentSev2s());
//			cycleSummary.setTotalCurrentSev3s(cycleSummary.getTotalCurrentSev3s() + tempCycleSummary.getTotalCurrentSev3s());
//			cycleSummary.setTotalCurrentSev4s(cycleSummary.getTotalCurrentSev4s() + tempCycleSummary.getTotalCurrentSev4s());
//			
//			cycleSummary.setTotalCodeImpactRules(cycleSummary.getTotalCodeImpactRules() + tempCycleSummary.getTotalCodeImpactRules());
//			cycleSummary.setTotalDefectRules(totalDefectRules)(cycleSummary.getTotalCodeImpactRules() + tempCycleSummary.getTotalCodeImpactRules());
//			cycleSummary.setTotalCodeImpactRules(cycleSummary.getTotalCodeImpactRules() + tempCycleSummary.getTotalCodeImpactRules());
//			cycleSummary.setTotalCodeImpactRules(cycleSummary.getTotalCodeImpactRules() + tempCycleSummary.getTotalCodeImpactRules());
//			
//			
//		}
//		cycleSummary = tempCycleSummary;
//	}
	

}