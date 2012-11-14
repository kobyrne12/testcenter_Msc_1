/**
 * 
 */
package ie.cit.cloud.testcenter.service.cycle;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ProjectsColMolsAndNames;
import ie.cit.cloud.testcenter.display.ProjectsDisplay;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.model.TestPlan;
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
    CycleRepository repo;      
	@Autowired
	CompanyService companyService;
	@Autowired
	ProjectService projectService;	
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<Cycle> getAllCycles() {
	return repo.findAll();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Cycle getCycle(long cycleID) {
	return repo.findById(cycleID);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Cycle getCycleByName(String cycleName) {
    	return repo.findCycleByName(cycleName);
    }
    
   // @Secured("ROLE_ADMIN")
    @Transactional(rollbackFor=ConstraintViolationException.class)   
    public long addNewCycle(Cycle cycle) {
    	repo.create(cycle);	
	return cycle.getCycleID();
    }
   
   // @Secured("ROLE_ADMIN")
    public void update(Cycle cycle) {
    	repo.update(cycle);
    }  
    
  //  @Secured("ROLE_ADMIN")
    public void remove(long cycleID) {
    	repo.delete(repo.get(cycleID));
    }
       
 
	public boolean updateCycle(long cycleID, Cycle cycle) {
		// TODO Auto-generated method stub
		return false;
	}
		
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Cycle> getAllCyclesByProjectID(long projectID) {
		return repo.findAllCyclesByProjectID(projectID);
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

	public void updateCycleWithId(long cycleID, Cycle cycle) {
		// TODO Auto-generated method stub
		
	}

	public void updateCycleNameWithId(long cycleID, Cycle cycle,
			String cycleName) {
		// TODO Auto-generated method stub
		
	}

	public long getMaxProjectPosNum(long projectID) {		
		return repo.getMaxProjectPosNum(projectID) + 1;
	}	
	

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public CycleSummary getCycleSummary(long cycleID) {
		long totalDefects = 0;
		Cycle cycle = getCycle(cycleID);
		Project project = projectService.getProject(cycle.getProjectID());
		CycleSummary cycleSummary = new CycleSummary();
		
		cycleSummary.setCycleID(cycleID);
		cycleSummary.setCycleName(cycle.getCycleName());	
					
		// Child Projects
		int numbOfChildCycles = 55;
		if(numbOfChildCycles == 1)
		{
			cycleSummary.setChildCycles("GET ChildCycle NAME");		
		}
		else
		{			
			cycleSummary.setChildCycles(Integer.toString(numbOfChildCycles));
		}
		
		cycleSummary.setNewFeatureRequiredPercent(project.getNewFeatureRequiredPercent());
		Long currentNewFeaturePercent = (long) (1 + (double)(Math.random() * ((100 - 1) + 1))); // just a random number until all entities are populated 
		cycleSummary.setNewFeatureCurrentPercent(currentNewFeaturePercent);
		cycleSummary.setRegressionCurrentPercent(project.getRegressionRequiredPercent());
		Long currentRegressionPercent = (long) (1 + (double)(Math.random() * ((100 - 1) + 1))); // just a random number until all entities are populated 
		cycleSummary.setRegressionRequiredPercent(currentRegressionPercent);
		
		cycleSummary.setAllowedSev1s(project.getAllowedSev1());
		cycleSummary.setAllowedSev2s(project.getAllowedSev2());
		cycleSummary.setAllowedSev3s(project.getAllowedSev3());
		cycleSummary.setAllowedSev4s(project.getAllowedSev4());
		
		Long currentSev1 = (long) (1 + (double)(Math.random() * ((50 - 1) + 1))); // just a random number until all entities are populated 
		cycleSummary.setCurrentSev1s(currentSev1);
		Long currentSev2 = (long) (1 + (double)(Math.random() * ((70 - 1) + 1))); // just a random number until all entities are populated 
		cycleSummary.setCurrentSev2s(currentSev2);
		Long currentSev3 = (long) (1 + (double)(Math.random() * ((100 - 1) + 1))); // just a random number until all entities are populated 
		cycleSummary.setCurrentSev3s(currentSev3);
		Long currentSev4 = (long) (1 + (double)(Math.random() * ((200 - 1) + 1))); // just a random number until all entities are populated 
		cycleSummary.setCurrentSev4s(currentSev4);
		
		totalDefects = currentSev1 + currentSev2 + currentSev3 + currentSev4;
		
		cycleSummary.setTotalDefects(totalDefects);
		cycleSummary.setCompanyID(project.getCompanyID());	
		
		// Cycles
		cycleSummary.setProjects(project.getProjectName());
		
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
		// TestRun
		int numOfTestRuns = 1585;
		if(numOfTestRuns == 1)
		{
			cycleSummary.setTestRuns("GET TEST RUN NAME");	
		}
		else
		{			
			cycleSummary.setTestRuns(Integer.toString(numOfTestRuns));
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
		
		cycleSummary.setLastModifiedBy(project.getLastModifiedBy());
		cycleSummary.setLastModifiedDate(project.getLastModifiedDate());
		cycleSummary.setCreatedBy(project.getCreatedBy());
		cycleSummary.setCreationDate(project.getCreationDate());
		
		cycleSummary.setParentCycleName("NOT DONE");	
		
		return cycleSummary;
	}

	

 private Collection<ProjectsDisplay> getProjectsColumnModel(long companyID)
 {
	 // Constructor in order
	 // name;index;hidden;width;align;
	 // sortable;resizable;search;sorttype;jsonmap;key;
	 
	// Company company = companyService.getCompany(companyID);
	 Collection<ProjectsDisplay> columnModelCollection =  new ArrayList<ProjectsDisplay>();		 
	 columnModelCollection.add(new ProjectsDisplay("projectID",10));	
	 columnModelCollection.add(new ProjectsDisplay("projectName",40));
	 columnModelCollection.add(new ProjectsDisplay("overAllState","setOverallBarChart","unSetBarChart", 80));		
	 columnModelCollection.add(new ProjectsDisplay("regressionState","setRegressionBarChart","unSetBarChart", 60));	
	 columnModelCollection.add(new ProjectsDisplay("newFeatureState","setNewFeatureBarChart","unSetBarChart",60));	
	 columnModelCollection.add(new ProjectsDisplay("parentProjectName",25));
	 columnModelCollection.add(new ProjectsDisplay("childProjects",15));
	 columnModelCollection.add(new ProjectsDisplay("regressionRequiredPercent","percentFmatter","unformatPercent",15));
	 columnModelCollection.add(new ProjectsDisplay("regressionCurrentPercent","percentFmatter","unformatPercent",15));
	 columnModelCollection.add(new ProjectsDisplay("newFeatureRequiredPercent","percentFmatter","unformatPercent",15));
	 columnModelCollection.add(new ProjectsDisplay("newFeatureCurrentPercent","percentFmatter","unformatPercent",15));
	 columnModelCollection.add(new ProjectsDisplay("allowedSev1s",true));
	 columnModelCollection.add(new ProjectsDisplay("currentSev1s",true));
	 columnModelCollection.add(new ProjectsDisplay("allowedSev2s",true));
	 columnModelCollection.add(new ProjectsDisplay("currentSev2s",true));
	 columnModelCollection.add(new ProjectsDisplay("allowedSev3s",true));
	 columnModelCollection.add(new ProjectsDisplay("currentSev3s",true));	 
	 columnModelCollection.add(new ProjectsDisplay("allowedSev4s",true));
	 columnModelCollection.add(new ProjectsDisplay("currentSev4s",true));
	 columnModelCollection.add(new ProjectsDisplay("companyID",true));
	 columnModelCollection.add(new ProjectsDisplay("cycles",true));
	 columnModelCollection.add(new ProjectsDisplay("environments",true));
	 columnModelCollection.add(new ProjectsDisplay("testRuns",true));
	 columnModelCollection.add(new ProjectsDisplay("testcases",true));
	 columnModelCollection.add(new ProjectsDisplay("testplans",true));	 
	 columnModelCollection.add(new ProjectsDisplay("testers",true));
	 columnModelCollection.add(new ProjectsDisplay("seniorTesters",true));	 
	 columnModelCollection.add(new ProjectsDisplay("developers",true));
	 columnModelCollection.add(new ProjectsDisplay("seniorDevelopers",true));
	 columnModelCollection.add(new ProjectsDisplay("lastModifiedDate",true));
	 columnModelCollection.add(new ProjectsDisplay("lastModifiedBy",true));	 
	 columnModelCollection.add(new ProjectsDisplay("createdBy",true));
	 columnModelCollection.add(new ProjectsDisplay("creationDate",true));	
	// if customValue1 set then 
	//columnModelCollection.add(getColumnModel("customColumn1"));	
	// if customValue2 set then 
	//columnModelCollection.add(getColumnModel("customColumn2"));	
	// if customValue3 set then 
	//columnModelCollection.add(getColumnModel("customColumn3"));
	// if customValue4 set then 
	//columnModelCollection.add(getColumnModel("customColumn4"));	
	// if customValue5 set then 
	//columnModelCollection.add(getColumnModel("customColumn5"));	
	 return columnModelCollection;
 }
 
 private Collection<String> getProjectsColumnNames(long companyID)
 {
	 Company company = companyService.getCompany(companyID);
	 Collection<String> colNames = new ArrayList<String>();
	 
	 colNames.add("ID");
	 colNames.add(company.getCycleDisplayName()+ " Name");
	 colNames.add("Overall");
	 colNames.add(company.getRegressionDisplayName());
	 colNames.add(company.getNewFeatureDisplayName());
	 colNames.add("Parent");
	 colNames.add("Child<BR/>"+ company.getProjectsDisplayName());
	 colNames.add("Min<BR/>" + company.getRegressionDisplayName());
	 colNames.add("Current<BR/>" + company.getRegressionDisplayName());
	 colNames.add("Min<BR/>" + company.getNewFeatureDisplayName());
	 colNames.add("Current<BR/>" + company.getNewFeatureDisplayName());
	 colNames.add("Max <BR/> Sev 1s");
	 colNames.add("Current <BR/> Sev 1s");
	 colNames.add("Max <BR/> Sev 2s");
	 colNames.add("Current <BR/> Sev 2s");
	 colNames.add("Max <BR/> Sev 3s");
	 colNames.add("Current <BR/> Sev 3s");
	 colNames.add("Max <BR/> Sev 4s");
	 colNames.add("Current <BR/> Sev 4s");
	 colNames.add("Company ID");
	 colNames.add(company.getCyclesDisplayName());
	 colNames.add(company.getEnvironmentsDisplayName());
	 colNames.add(company.getTestrunsDisplayName());
	 colNames.add(company.getTestcasesDisplayName());
	 colNames.add(company.getTestplansDisplayName());
	 colNames.add(company.getTestersDisplayName());
	 colNames.add(company.getSeniorTestersDisplayName());
	 colNames.add(company.getDevelopersDisplayName());
	 colNames.add(company.getSeniordevelopersDisplayName());
	 colNames.add("LastModifiedDate");
	 colNames.add("LastModifiedBy");
	 colNames.add("CreatedBy");
	 colNames.add("CreationDate");
	 // if customValue1 set then 
	 //colNames.add("CreationDate");
	 // if customValue2 set then 
	 //colNames.add("CreationDate");	
	 // if customValue3 set then 
	 //colNames.add("CreationDate");
	 // if customValue4 set then 
	 //colNames.add("CreationDate");	
	 // if customValue5 set then 
	 //colNames.add("CreationDate");	
	 return colNames;
 }
	
 public ProjectsColMolsAndNames getProjectColumnModelAndNames(long companyID)
 {
 	ProjectsColMolsAndNames colModelAndName = new ProjectsColMolsAndNames();
 	colModelAndName.setColName(getProjectsColumnNames(companyID));    	
 	colModelAndName.setColModel(getProjectsColumnModel(companyID));
 	return colModelAndName;
 }


 public CycleSummaryList getsummaryList(long companyID, String projectID,
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