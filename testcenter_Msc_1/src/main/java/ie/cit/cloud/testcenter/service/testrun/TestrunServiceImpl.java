/**
 * 
 */
package ie.cit.cloud.testcenter.service.testrun;

/**
 * @author byrnek1
 *
 */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.respository.testrun.TestrunRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
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
	

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testrun getTestrun(long testrunID) {
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
	public void remove(long testrunID) {
		testrunRepo.delete(getTestrun(testrunID));
	}
	/**
	 * Returns true if this testruns cycle is the latest cycle for a project 
	 * boolean
	 * @return true if this testruns cycle is the latest cycle for a project, otherwise false
	 */  
	public boolean isLatest(long testrunID)
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
	public Set<Testrun> getTestHistory(long testrunID) 
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

	public boolean isRequired(long testrunID) 
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

	public Cycle getCycle(long testrunID)
	{
		Testrun testrun = getTestrun(testrunID);
		try{
			return cycleService.getCycle(testrun.getCycleID());			
		}catch(NoResultException nre)
		{
			return null;
		}
	}

	public Testcase getTestcase(long testrunID) {
		Testrun testrun = getTestrun(testrunID);
		try{
			return testcaseService.getTestcase(testrun.getTestcaseID());			
		}catch(NoResultException nre)
		{
			return null;
		}
	}

	public Project getProject(long testrunID)
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

	public Testplan getTestPlan(long testrunID) {
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
	public Set<Defect> getCascadedAllDefects(long testrunID) 
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
		
	public Set<Defect> getCascadedSev1Defects(long testrunID) 
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
	public Set<Defect> getCascadedSev2Defects(long testrunID) 
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
		
	public Set<Defect> getCascadedSev3Defects(long testrunID) 
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
	public Set<Defect> getCascadedSev4Defects(long testrunID) 
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
	public Set<TestcenterUser> getCascadedTesters(long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrTesters(long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedDevelopers(long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<TestcenterUser> getCascadedSnrDevelopers(long defectID) {
		// TODO Auto-generated method stub
		return null;
	}

}