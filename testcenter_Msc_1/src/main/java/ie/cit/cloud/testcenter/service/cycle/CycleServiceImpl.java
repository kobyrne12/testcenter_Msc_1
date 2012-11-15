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

    private Collection<Cycle> getChildCycles(long cycleID) {
    	return cycleRepo.findAllCyclesByParentID(cycleID);
	}


    public long getMaxProjectPosNum(long projectID) {		
    	return cycleRepo.getMaxProjectPosNum(projectID) + 1;
    }	   

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public CycleSummary getCycleSummary(long cycleID) {
		
		Cycle cycle = getCycle(cycleID);		
		Project project = projectService.getProject(cycle.getProjectID());
		
		CycleSummary cycleSummary = new CycleSummary();
		
		cycleSummary.setCycleID(cycleID);
		cycleSummary.setCycleName(cycle.getCycleName());	
		cycleSummary.setRequiredPriority(cycle.getRequiredPriority());
		//cycleSummary.setAllTestruns(cycle.getTestruns().size());
		cycleSummary.setAllTestruns(400);
			
		//int requiredTestruns = testrunService.getRequiredTestruns(cycleID).size();
			int requiredTestruns = 300 ;		
		cycleSummary.setRequiredTestruns(requiredTestruns);
		
		//int testrunsPassed = testrunService.getRequiredTestrunsPassed(cycleID,cycle.getRequiredPriority());
			int testrunsPassed = 50;
		cycleSummary.setTestrunsPassed(testrunsPassed);
		//int testrunsFailed = testrunService.getRequiredTestrunsFailed(cycleID,cycle.getRequiredPriority());
			int testrunsFailed = 50;
		cycleSummary.setTestrunsFailed(testrunsFailed);
		//int testrunsDeferred = testrunService.getRequiredTestrunsDeferred(cycleID,cycle.getRequiredPriority());
			int testrunsDeferred = 50;
		cycleSummary.setTestrunsDeferred(testrunsDeferred);
		//int testrunsBlocked = testrunService.getRequiredTestrunsBlocked(cycleID,cycle.getRequiredPriority());
			int testrunsBlocked = 50;
		cycleSummary.setTestrunsBlocked(testrunsBlocked);
		
		//int testrunsNotRun = testrunService.getRequiredTestrunsNotRun(cycleID,cycle.getRequiredPriority());
			int testrunsNotRun = 50;
		cycleSummary.setTestrunsPassed(testrunsNotRun);
		//int testrunsInProg = testrunService.getRequiredTestrunsInProg(cycleID,cycle.getRequiredPriority());
			int testrunsInProg = 50;
		cycleSummary.setTestrunsInProg(testrunsInProg);			
		
		int testrunsCompleted = testrunsPassed + testrunsFailed + testrunsDeferred + testrunsBlocked;		
		cycleSummary.setTestrunsCompleted(testrunsCompleted);
		int testrunsNotCompleted = requiredTestruns - testrunsCompleted;
		cycleSummary.setTestrunsNotCompleted(testrunsNotCompleted);
		
		//double totalCycleEstTime = testrunService.getTotalCycleEstTime(cycleID,cycle.getRequiredPriority());
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
				
		cycleSummary.setChildCycles((getChildCycles(cycleID)) != null ? getChildCycles(cycleID).size() : 0);
			
		//int totalDefects = sort out the many to many tables then: 
		//Collection<Defect> defects = defectService.getDefectsByCycle(cycleID)
		// All distinct defects for each required test run
			int totalDefects = 100;
		cycleSummary.setTotalDefects(totalDefects);
		cycleSummary.setAllowedSev1s(project.getAllowedSev1());
		cycleSummary.setAllowedSev2s(project.getAllowedSev2());
		cycleSummary.setAllowedSev3s(project.getAllowedSev3());
		cycleSummary.setAllowedSev4s(project.getAllowedSev4());
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
		cycleSummary.setCurrentSev1s(currentSev1);
		currentSev2 = 30;
		cycleSummary.setCurrentSev2s(currentSev2);
		currentSev3 = 25;
		cycleSummary.setCurrentSev3s(currentSev3);
		currentSev4 = 25;
		cycleSummary.setCurrentSev4s(currentSev4);
		
		cycleSummary.setCompanyID(project.getCompanyID());	
		cycleSummary.setProjectID(project.getProjectID());
		
		/////////////////////////////////////////////////////////
		
		//		 colNames.add(company.getDefectDisplayName()+" Rules");
		//		 columnModelCollection.add(new GridAttributes("totalNumberOfDefectRules",true));
		//		 colNames.add("Test History Rules");
		//		 columnModelCollection.add(new GridAttributes("totalNumberOfTestHistoryRules",true));
		//		 colNames.add("Code Impact Rules");
		//		 columnModelCollection.add(new GridAttributes("totalNumberOfCodeImpactRules",true));
		//		 colNames.add(company.getRequirementDisplayName()+" Rules");
		//		 columnModelCollection.add(new GridAttributes("totalNumberOfReqRules",true));		 
		//		 colNames.add("All "+company.getTestrunsDisplayName());
		//		 columnModelCollection.add(new GridAttributes("allTestruns",10,true));	 
		//		 colNames.add(company.getTestplansDisplayName());
		//		 columnModelCollection.add(new GridAttributes("testplans",true));
		//		 colNames.add(company.getTestcasesDisplayName());
		//		 columnModelCollection.add(new GridAttributes("testcases",true));			 
		//		 colNames.add(company.getEnvironmentsDisplayName());
		//		 columnModelCollection.add(new GridAttributes("environments",true));
		//		 colNames.add(company.getRequirementsDisplayName());
		//		 columnModelCollection.add(new GridAttributes("requirements",true));		 
		//		 colNames.add(company.getTestersDisplayName());
		//		 columnModelCollection.add(new GridAttributes("testers",true));
		//		 colNames.add(company.getSeniorTestersDisplayName());
		//		 columnModelCollection.add(new GridAttributes("seniorTesters",true));
		//		 colNames.add(company.getDevelopersDisplayName());
		//		 columnModelCollection.add(new GridAttributes("developers",true));
		//		 colNames.add(company.getSeniordevelopersDisplayName());
		//		 columnModelCollection.add(new GridAttributes("seniorDevelopers",true));
		//		 
		//		 colNames.add("Position");
		//		 columnModelCollection.add(new GridAttributes("projectPosition",true));	
		
		 /////////////////////////////////////////////////////////
		 
		// env
		int numOfEnvs = 10;
		if(numOfEnvs == 1)
		{
			cycleSummary.setEnvironments("GET Env NAME");	
		}
		else
		{			
			cycleSummary.setEnvironments(Integer.toString(numOfEnvs));
		}
		// requirements
		int numbOfReqs = 10;
		if(numbOfReqs == 1)
		{
			cycleSummary.setRequirements("GET REQS NAME");	
		}
		else
		{			
			cycleSummary.setRequirements(Integer.toString(numbOfReqs));
		}		
		// Test Case
		int numOfTestCases = 1900;
		if(numOfTestCases == 1)
		{
			cycleSummary.setTestcases("GET TEST CASE NAME");	
		}
		else
		{			
			cycleSummary.setTestcases(Integer.toString(numOfTestCases));
		}
		// Test Plan
		int numOfTestPlans = 22;
		if(numOfTestPlans == 1)
		{
			cycleSummary.setTestplans("GET TEST Plan NAME");	
		}
		else
		{			
			cycleSummary.setTestplans(Integer.toString(numOfTestPlans));
		}
		// Testers
		int numOfTesters = 22;
		if(numOfTesters == 1)
		{
			cycleSummary.setTesters("GET TESTERS NAME");	
		}
		else
		{			
			cycleSummary.setTesters(Integer.toString(numOfTesters));
		}
		// SNR Testers
		int numOfSnrTesters = 22;
		if(numOfSnrTesters == 1)
		{
			cycleSummary.setSeniorTesters("GET SNR TESTERS NAME");	
		}
		else
		{			
			cycleSummary.setSeniorTesters(Integer.toString(numOfSnrTesters));
		}
		// Developers
		int numOfDevs = 22;
		if(numOfDevs == 1)
		{
			cycleSummary.setDevelopers("GET DEV NAME");	
		}
		else
		{			
			cycleSummary.setDevelopers(Integer.toString(numOfDevs));
		}
		// SNR Developers
		int numOfSnrDevs = 22;
		if(numOfSnrDevs == 1)
		{
			cycleSummary.setSeniorDevelopers("GET SNR DEV NAME");	
		}
		else
		{			
			cycleSummary.setSeniorDevelopers(Integer.toString(numOfSnrDevs));
		}

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
	 columnModelCollection.add(new GridAttributes("overAllState","setOverallBarChart","unSetBarChart", 80));	
	
	 colNames.add("Total");
	 columnModelCollection.add(new GridAttributes("testRuns",10,true));
	 colNames.add("Complete");
	 columnModelCollection.add(new GridAttributes("testrunsCompleted",10,true));
	 colNames.add("Passed");
	 columnModelCollection.add(new GridAttributes("testrunsPassed",10,true));
	 colNames.add("Failed");
	 columnModelCollection.add(new GridAttributes("testrunsFailed",10,true));	
	 colNames.add("Deferred");
	 columnModelCollection.add(new GridAttributes("testrunsDeferred",10,true));
	 colNames.add("Blocked");
	 columnModelCollection.add(new GridAttributes("testrunsBlocked",10,true));
	 colNames.add("Not Complete");
	 columnModelCollection.add(new GridAttributes("testrunsNotCompleted",10,true));
	 colNames.add("Not Run");
	 columnModelCollection.add(new GridAttributes("testrunsNotRun",10,true));
	 colNames.add("In Prog");
	 columnModelCollection.add(new GridAttributes("testrunsInProg",10,true));
	 
	 colNames.add("Est.Time");
	 columnModelCollection.add(new GridAttributes("totalCycleEstTime",15,true));
	 
	 colNames.add("Start Date");
	 columnModelCollection.add(new GridAttributes("cycleStartDate",20,true));
	 
	 colNames.add("End Date");
	 columnModelCollection.add(new GridAttributes("cycleEndDate",20,true));	
		
	 colNames.add("Parent");
	 columnModelCollection.add(new GridAttributes("parentCycleName",25,true));

	 colNames.add("Child "+ company.getProjectsDisplayName());	
	 columnModelCollection.add(new GridAttributes("childCycles",15,true));	
	
	 colNames.add("Total "+ company.getDefectsDisplayName());
	 columnModelCollection.add(new GridAttributes("totalDefects"));
	 colNames.add("Max Sev 1s");
	 columnModelCollection.add(new GridAttributes("allowedSev1s",true));
	 colNames.add("Current Sev 1s");
	 columnModelCollection.add(new GridAttributes("currentSev1s"));
	 colNames.add("Max Sev 2s");
	 columnModelCollection.add(new GridAttributes("allowedSev2s",true));
	 colNames.add("Current <BR/> Sev 2s");
	 columnModelCollection.add(new GridAttributes("currentSev2s"));
	 colNames.add("Max Sev 3s");
	 columnModelCollection.add(new GridAttributes("allowedSev3s",true));
	 colNames.add("Current Sev 3s");
	 columnModelCollection.add(new GridAttributes("currentSev3s"));	
	 colNames.add("Max Sev 4s");
	 columnModelCollection.add(new GridAttributes("allowedSev4s",true));
	 colNames.add("Current <BR/> Sev 4s");
	 columnModelCollection.add(new GridAttributes("currentSev4s"));

	 colNames.add("Company ID");
	 columnModelCollection.add(new GridAttributes("companyID",true));
	 colNames.add("Project ID");
	 columnModelCollection.add(new GridAttributes("projectID",true));

	 colNames.add(company.getProjectDisplayName());
	 columnModelCollection.add(new GridAttributes("project",true));

	 colNames.add(company.getDefectDisplayName()+" Rules");
	 columnModelCollection.add(new GridAttributes("totalNumberOfDefectRules",true));
	 colNames.add("Test History Rules");
	 columnModelCollection.add(new GridAttributes("totalNumberOfTestHistoryRules",true));
	 colNames.add("Code Impact Rules");
	 columnModelCollection.add(new GridAttributes("totalNumberOfCodeImpactRules",true));
	 colNames.add(company.getRequirementDisplayName()+" Rules");
	 columnModelCollection.add(new GridAttributes("totalNumberOfReqRules",true));	
	 
	 colNames.add("All "+company.getTestrunsDisplayName());
	 columnModelCollection.add(new GridAttributes("allTestruns",10,true));	 
	 colNames.add(company.getTestplansDisplayName());
	 columnModelCollection.add(new GridAttributes("testplans",true));
	 colNames.add(company.getTestcasesDisplayName());
	 columnModelCollection.add(new GridAttributes("testcases",true));	 
	 
	 colNames.add(company.getEnvironmentsDisplayName());
	 columnModelCollection.add(new GridAttributes("environments",true));
	 colNames.add(company.getRequirementsDisplayName());
	 columnModelCollection.add(new GridAttributes("requirements",true));
	 
	 colNames.add(company.getTestersDisplayName());
	 columnModelCollection.add(new GridAttributes("testers",true));
	 colNames.add(company.getSeniorTestersDisplayName());
	 columnModelCollection.add(new GridAttributes("seniorTesters",true));
	 colNames.add(company.getDevelopersDisplayName());
	 columnModelCollection.add(new GridAttributes("developers",true));
	 colNames.add(company.getSeniordevelopersDisplayName());
	 columnModelCollection.add(new GridAttributes("seniorDevelopers",true));
	 
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


 public CycleSummaryList getGridCycles(long companyID, String projectID,
		 String testplanID, String userID, String environmentID,
		 String requirementID, String defectID, String testrunID) {
	 
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
	 if (testplanID != null){long testplanID_long = Long.valueOf(testplanID).longValue();	}
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
			 Cycle cycle = cyclesItr.next();						
			 CycleSummary cycleSummary = getCycleSummary(cycle.getCycleID());	
			 cycleSummaryCollection.add(cycleSummary);
		 }
		 
		 cycleSummaryList.setCycles(cycleSummaryCollection);
		 return cycleSummaryList;
	 }
 }

}