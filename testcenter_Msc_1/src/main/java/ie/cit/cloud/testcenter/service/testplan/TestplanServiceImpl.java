/**
 * 
 */
package ie.cit.cloud.testcenter.service.testplan;

/**
 * @author byrnek1
 *
 */

import java.util.HashSet;
import java.util.Set;

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
import ie.cit.cloud.testcenter.respository.testplan.TestplanRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
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
	TestcaseService testcaseService;
	@Autowired
	TestrunService testrunService;
	
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testplan getTestplan(long testplanID) {
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
	public void remove(long testplanID) {
		testplanRepo.delete(getTestplan(testplanID));
	}
	
	public TestplanSection getTestplanSection(long testplanSectionID) {
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

	public void removeTestplanSection(long testplanSectionID) {
		testplanRepo.deleteTestplanSection(getTestplanSection(testplanSectionID));
		
	}
	////////////////

	public Set<Testcase> getAllTestCases(long testplanID) 
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

	public Set<Testcase> getCompulsoryTestCases(long testplanID) 
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

	public Set<Testcase> getOptionalTestCases(long testplanID)
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
	
	public Set<Testrun> getAllTestRuns(long testplanID) 
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

	public Set<Testrun> getCompulsoryTestRuns(long testplanID)
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

	public Set<Testrun> getOptionalTestRuns(long testplanID)
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
	public boolean isRequired(long testcaseID) 
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testcaseID);
		if(compulsoryTestruns != null && !compulsoryTestruns.isEmpty())
		{
			return true;
		}
		return false;		
	}   
	public Set<Cycle> getCycles(long testplanID) 
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

	public Set<Project> getProjects(long testplanID)
	{
		Set<Testrun> compulsoryTestruns = getCompulsoryTestRuns(testplanID);		
		if(compulsoryTestruns == null)
		{
			return null;
		}	
		Set<Project> projects = new HashSet<Project>();
		for(final Testrun testrun : compulsoryTestruns)
		{
			if(testrunService.getProject(testrun.getTestrunID()) != null )
			{
				projects.add(testrunService.getProject(testrun.getTestrunID()));
			}			
		}	
		return projects;
	}

	public Set<Requirement> getRequirements(long testplanID)
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

	public Set<Environment> getEnvironments(long testplanID)
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

	public Set<Defect> getCascadedAllDefects(long testplanID) 
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

	public Set<Defect> getCascadedSev1Defects(long testplanID) 
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

	public Set<Defect> getCascadedSev2Defects(long testplanID)
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

	public Set<Defect> getCascadedSev3Defects(long testplanID)
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

	public Set<Defect> getCascadedSev4Defects(long testplanID) 
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

	public Set<TestcenterUser> getCascadedTesters(long testplanID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(long testplanID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(long testplanID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(long testplanID) {
		// TODO Auto-generated method stub
		return null;
	}



}