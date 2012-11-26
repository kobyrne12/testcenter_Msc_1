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

import ie.cit.cloud.testcenter.model.Testrun;

import ie.cit.cloud.testcenter.respository.testcase.TestcaseRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import ie.cit.cloud.testcenter.service.testrun.TestrunService;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
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
	TestrunService testrunService;	
	
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
	
	
	///////////////////////////////////
	
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
	
	public boolean isRequired(long testcaseID) 
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testcaseID);
		if(compulsoryTestruns != null && !compulsoryTestruns.isEmpty())
		{
			return true;
		}
		return false;		
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
	public Set<TestcenterUser> getCascadedTesters(long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public Set<TestcenterUser> getCascadedSnrTesters(long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public Set<TestcenterUser> getCascadedDevelopers(long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	public Set<TestcenterUser> getCascadedSnrDevelopers(long testcaseID) {
		// TODO Auto-generated method stub
		return null;
	}
	

}