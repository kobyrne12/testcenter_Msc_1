/**
 * 
 */
package ie.cit.cloud.testcenter.service.company;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.respository.company.CompanyRepository;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.defect.DefectService;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.NoResultException;
import javax.persistence.Transient;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
    @Qualifier("hibernateCompanyRespository")
    CompanyRepository repo;   
	
	@Autowired   
    ProjectService projectService;   
	@Autowired   
    CycleService cycleService;  
	@Autowired   
    DefectService defectService;  
	 
	
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<Company> getAllCompanies() {
	return repo.findAll();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Company getCompany(long companyID) {
	return repo.findById(companyID);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Company getCompanyByName(String companyName) {
    	return repo.findCompanyByName(companyName);
    }
    
  //  @Secured("ROLE_ADMIN")
    @Transactional(rollbackFor=ConstraintViolationException.class)   
    public void addNewCompany(Company company) {
	repo.create(company);		
    }
   
  //  @Secured("ROLE_ADMIN")
    public void update(Company company) {
	repo.update(company);
    }  
    
  //  @Secured("ROLE_ADMIN")
    public void remove(long companyID) {
	repo.delete(repo.get(companyID));
    }
       
  //  @Secured("ROLE_ADMIN")    
    public void updateCompanyWithId(long companyID, Company company) {
    	Company oldCompany = repo.findById(companyID);
    	oldCompany.setCompanyName(company.getCompanyName());
    	// TODO Finish update for all values
    	oldCompany.setCycleDisplayName(company.getCycleDisplayName());    			
    	repo.update(oldCompany);
    }
 //   @Secured("ROLE_ADMIN")
    public void updateCompanyNameWithId(long companyID, Company company, String companyName) {
    	Company oldCompany = repo.findById(companyID);
    	oldCompany.setCompanyName(company.getCompanyName());
    	// TODO Finish update for all values
    	repo.update(oldCompany);
    }

	public boolean updateCompany(long companyID, Company company) {
		// TODO Auto-generated method stub
		return false;
	}	
///////////////////////////////////////////////////////////////////////////////////////////////////////
		
	/**
	 * Returns a collection of cycles in a company 
	 * Collection<Cycle>
	 * @return collection of cycles in a company,
	 */	
	public Collection<Cycle> getAllCycles(long companyID)
	{		
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}
		Collection<Cycle> cycles = new ArrayList<Cycle>();
		for(final Project project : company.getProjects())
		{			
			if(project.getCycles() != null && !project.getCycles().isEmpty())
			{
				cycles.addAll(project.getCycles());
			}
		}
		return cycles;
	}	
	/**
	 * Returns a collection of All Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a company,
	 */

	public Collection<Testrun> getAllTestRuns(long companyID)
	{		
		Collection<Cycle> cycles = getAllCycles(companyID);		
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Collection<Testrun> testruns = new ArrayList<Testrun>();
		for(final Cycle cycle : cycles)
		{
			if(cycle.getTestruns() != null && !cycle.getTestruns().isEmpty())
			{
				testruns.addAll(cycle.getTestruns());
			}
		}
		return testruns;
	}		
	/**
	 * Returns a collection of All Compulsory Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Compulsory Testruns in a company,
	 */	
	public Collection<Testrun> getCompulsoryTestRuns(long companyID)
	{
		Collection<Cycle> cycles = getAllCycles(companyID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Collection<Testrun> testruns = new ArrayList<Testrun>();
		for(final Cycle cycle : cycles)
		{		
			if(cycleService.getCascadedCompulsoryTestRuns(cycle.getCycleID()) != null && 
					!cycleService.getCascadedCompulsoryTestRuns(cycle.getCycleID()).isEmpty())
			{
				testruns.addAll(cycleService.getCascadedCompulsoryTestRuns(cycle.getCycleID()));
			}
		}
		return testruns;
	}
	/**
	 * Returns a collection of All Optional Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Optional Testruns in a company,
	 */	
	public Collection<Testrun> getOptionalTestRuns(long companyID)
	{
		Collection<Cycle> cycles = getAllCycles(companyID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Collection<Testrun> testruns = new ArrayList<Testrun>();
		for(final Cycle cycle : cycles)
		{		
			if(cycleService.getCascadedOptionalTestRuns(cycle.getCycleID()) != null && 
					!cycleService.getCascadedOptionalTestRuns(cycle.getCycleID()).isEmpty())
			{
				testruns.addAll(cycleService.getCascadedOptionalTestRuns(cycle.getCycleID()));
			}
		}
		return testruns;
	}
	////////////////

	/**
	 * Returns a collection of Testcases in a company
	 * Collection<Testcase>
	 * @return collection of Testcases in a company
	 */	
	public Collection<Testcase> getAllTestCases(long companyID)
	{	
		Company company = getCompany(companyID);
		if(company.getTestcases() == null || company.getTestcases().isEmpty())
		{
			return null;
		}
		return company.getTestcases();				
	}
	/**
	 * Returns a collection of Compulsory Testcases in a company
	 * Collection<Testcase>
	 * @return collection of Compulsory Testcases in a company,
	 */	
	public Collection<Testcase> getCompulsoryTestCases(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}		
		Collection<Testcase> compulsoryTestcases = new ArrayList<Testcase>();  
		for(final Project project : company.getProjects())
		{			
			if(projectService.getCascadedCompulsoryTestCases(project.getProjectID()) != null && 
					!projectService.getCascadedCompulsoryTestCases(project.getProjectID()).isEmpty())
			{
				compulsoryTestcases.addAll(projectService.getCascadedCompulsoryTestCases(project.getProjectID()));
			}						
		}			
		return compulsoryTestcases;			
	}
	/**
	 * Returns a collection of Optional Testcases in a company
	 * Collection<Testcase>
	 * @return collection of Optional Testcases in a company,
	 */	
	public Collection<Testcase> getOptionalTestCases(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}		
		Collection<Testcase> optionalTestcases = new ArrayList<Testcase>();  
		for(final Project project : company.getProjects())
		{			
			if(projectService.getCascadedOptionalTestCases(project.getProjectID()) != null && 
					!projectService.getCascadedOptionalTestCases(project.getProjectID()).isEmpty())
			{
				optionalTestcases.addAll(projectService.getCascadedOptionalTestCases(project.getProjectID()));
			}						
		}			
		return optionalTestcases;			
	}
	/**
	 * Returns a collection of Testplans in a company
	 * Collection<Testplan>
	 * @return collection of Testplans in a company
	 */	
	public Collection<Testplan> getAllTestPlans(long companyID)
	{	
		Company company = getCompany(companyID);
		if(company.getTestplans() == null || company.getTestplans().isEmpty())
		{
			return null;
		}
		return company.getTestplans();				
	}
	/**
	 * Returns a collection of Compulsory Testplans in a company
	 * Collection<Testplan>
	 * @return collection of Compulsory Testplans in a company,
	 */	
	public Collection<Testplan> getCompulsoryTestPlans(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}		
		Collection<Testplan> compulsoryTestplans = new ArrayList<Testplan>();  
		for(final Project project : company.getProjects())
		{			
			if(projectService.getCascadedCompulsoryTestPlans(project.getProjectID()) != null && 
					!projectService.getCascadedCompulsoryTestPlans(project.getProjectID()).isEmpty())
			{
				compulsoryTestplans.addAll(projectService.getCascadedCompulsoryTestPlans(project.getProjectID()));
			}						
		}			
		return compulsoryTestplans;			
	}
	/**
	 * Returns a collection of Optional Testplans in a company
	 * Collection<Testcase>
	 * @return collection of Optional Testplans in a company,
	 */	
	public Collection<Testplan> getOptionalTestPlans(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}		
		Collection<Testplan> optionalTestplans = new ArrayList<Testplan>();  
		for(final Project project : company.getProjects())
		{			
			if(projectService.getCascadedOptionalTestPlans(project.getProjectID()) != null && 
					!projectService.getCascadedOptionalTestPlans(project.getProjectID()).isEmpty())
			{
				optionalTestplans.addAll(projectService.getCascadedOptionalTestPlans(project.getProjectID()));
			}						
		}			
		return optionalTestplans;			
	}	
	/**
	 * Returns a collection of All Sev1 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev1 Defects in a company,
	 */	
	public Collection<Defect> getAllSev1Defects(long companyID)
	{		
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return null;
		}
		Collection<Defect> sev1Defects = new ArrayList<Defect>();
		for(final Defect defect : company.getDefects())
		{
			if(defectService.isSev1(defect.getDefectID()));
			{
				sev1Defects.add(defect);				
			}
		}
		return sev1Defects;
	}	
	/**
	 * Returns a collection of All Sev2 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev2 Defects in a company,
	 */	
	public Collection<Defect> getAllSev2Defects(long companyID)
	{		
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return null;
		}
		Collection<Defect> sev2Defects = new ArrayList<Defect>();
		for(final Defect defect : company.getDefects())
		{
			if(defectService.isSev2(defect.getDefectID()));
			{
				sev2Defects.add(defect);				
			}
		}
		return sev2Defects;
	}	
	/**
	 * Returns a collection of All Sev3 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev3 Defects in a company,
	 */	
	public Collection<Defect> getAllSev3Defects(long companyID)
	{	
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return null;
		}
		Collection<Defect> sev3Defects = new ArrayList<Defect>();
		for(final Defect defect : company.getDefects() )
		{
			if(defectService.isSev3(defect.getDefectID()));
			{
				sev3Defects.add(defect);				
			}
		}
		return sev3Defects;
	}	
	/**
	 * Returns a collection of All Sev4 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev4 Defects in a company,
	 */

	public Collection<Defect> getAllSev4Defects(long companyID)
	{		
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return null;
		}
		Collection<Defect> sev4Defects = new ArrayList<Defect>();
		for(final Defect defect : company.getDefects() )
		{
			if(defectService.isSev4(defect.getDefectID()));
			{
				sev4Defects.add(defect);				
			}
		}
		return sev4Defects;
	}	
	public Collection<TestcenterUser> getAllTesters(long companyID) {
		//		Company company = getCompany(companyID);
		//		if(company.getUsers() == null || company.getUsers().isEmpty())
		//		{
		//			return null;
		//		}
		//		Collection<User> testers = new ArrayList<User>();
		//		for(final User user : company.getUsers() )
		//		{
		//			if(userService.isTester((user.getUserID())))
		//			{
		//				testers.add(user);				
		//			}
		//		}
		//		return testers;
		return null;
	}
	public Collection<TestcenterUser> getAllSeniorTesters(long companyID) {
		//		Company company = getCompany(companyID);
		//		if(company.getUsers() == null || company.getUsers().isEmpty())
		//		{
		//			return null;
		//		}
		//		Collection<User> seniorTesters = new ArrayList<User>();
		//		for(final User user : company.getUsers() )
		//		{
		//			if(userService.isSeniorTester((user.getUserID())))
		//			{
		//				testers.add(user);				
		//			}
		//		}
		//		return testers;
		return null;
	}
	public Collection<TestcenterUser> getAllDevelopers(long companyID) {
		//		Company company = getCompany(companyID);
		//		if(company.getUsers() == null || company.getUsers().isEmpty())
		//		{
		//			return null;
		//		}
		//		Collection<User> seniorTesters = new ArrayList<User>();
		//		for(final User user : company.getUsers() )
		//		{
		//			if(userService.isDeveloper((user.getUserID())))
		//			{
		//				testers.add(user);				
		//			}
		//		}
		//		return testers;	
		return null;
	}
	public Collection<TestcenterUser> getAllSeniorDevelopers(long companyID) {
		//		Company company = getCompany(companyID);
		//		if(company.getUsers() == null || company.getUsers().isEmpty())
		//		{
		//			return null;
		//		}
		//		Collection<User> seniorTesters = new ArrayList<User>();
		//		for(final User user : company.getUsers() )
		//		{
		//			if(userService.isSeniorDeveloper((user.getUserID())))
		//			{
		//				testers.add(user);				
		//			}
		//		}
		//		return testers;
		return null;
	}	
}