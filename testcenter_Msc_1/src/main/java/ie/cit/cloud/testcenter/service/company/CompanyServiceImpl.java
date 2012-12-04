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

import java.util.HashSet;
import java.util.Set;
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
    public Set<Company> getAllCompanies() {
	return repo.findAll();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Company getCompany(Long companyID) {
    	try{
    		return repo.findById(companyID);		
		}
		catch(NoResultException nre)			
		{	
			return null;
		}		
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
    public void remove(Long companyID) {
	repo.delete(repo.get(companyID));
    }
       
  //  @Secured("ROLE_ADMIN")    
    public void updateCompanyWithId(Long companyID, Company company) {
    	Company oldCompany = repo.findById(companyID);
    	oldCompany.setCompanyName(company.getCompanyName());
    	// TODO Finish update for all values
    	oldCompany.setCycleDisplayName(company.getCycleDisplayName());    			
    	repo.update(oldCompany);
    }
 //   @Secured("ROLE_ADMIN")
    public void updateCompanyNameWithId(Long companyID, Company company, String companyName) {
    	Company oldCompany = repo.findById(companyID);
    	oldCompany.setCompanyName(company.getCompanyName());
    	// TODO Finish update for all values
    	repo.update(oldCompany);
    }

	public boolean updateCompany(Long companyID, Company company) {
		// TODO Auto-generated method stub
		return false;
	}	
///////////////////////////////////////////////////////////////////////////////////////////////////////
		
	/**
	 * Returns a collection of cycles in a company 
	 * Set<Cycle>
	 * @return collection of cycles in a company,
	 */	
	public Set<Cycle> getAllCycles(Long companyID)
	{		
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}
		Set<Cycle> cycles = new HashSet<Cycle>();
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
	 * Set<Testrun>
	 * @return collection of All Testruns in a company,
	 */

	public Set<Testrun> getAllTestRuns(Long companyID)
	{		
		Set<Cycle> cycles = getAllCycles(companyID);		
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testrun> testruns = new HashSet<Testrun>();
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
	 * Set<Testrun>
	 * @return collection of All Compulsory Testruns in a company,
	 */	
	public Set<Testrun> getCompulsoryTestRuns(Long companyID)
	{
		Set<Cycle> cycles = getAllCycles(companyID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testrun> testruns = new HashSet<Testrun>();
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
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a company,
	 */	
	public Set<Testrun> getOptionalTestRuns(Long companyID)
	{
		Set<Cycle> cycles = getAllCycles(companyID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Set<Testrun> testruns = new HashSet<Testrun>();
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
	 * Set<Testcase>
	 * @return collection of Testcases in a company
	 */	
	public Set<Testcase> getAllTestCases(Long companyID)
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
	 * Set<Testcase>
	 * @return collection of Compulsory Testcases in a company,
	 */	
	public Set<Testcase> getCompulsoryTestCases(Long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}		
		Set<Testcase> compulsoryTestcases = new HashSet<Testcase>();  
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
	 * Set<Testcase>
	 * @return collection of Optional Testcases in a company,
	 */	
	public Set<Testcase> getOptionalTestCases(Long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}		
		Set<Testcase> optionalTestcases = new HashSet<Testcase>();  
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
	 * Set<Testplan>
	 * @return collection of Testplans in a company
	 */	
	public Set<Testplan> getAllTestPlans(Long companyID)
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
	 * Set<Testplan>
	 * @return collection of Compulsory Testplans in a company,
	 */	
	public Set<Testplan> getCompulsoryTestPlans(Long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}		
		Set<Testplan> compulsoryTestplans = new HashSet<Testplan>();  
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
	 * Set<Testcase>
	 * @return collection of Optional Testplans in a company,
	 */	
	public Set<Testplan> getOptionalTestPlans(Long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return null;
		}		
		Set<Testplan> optionalTestplans = new HashSet<Testplan>();  
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
	 * Set<Defect>
	 * @return collection of All Sev1 Defects in a company,
	 */	
	public Set<Defect> getAllSev1Defects(Long companyID)
	{		
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return null;
		}
		Set<Defect> sev1Defects = new HashSet<Defect>();
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
	 * Set<Defect>
	 * @return collection of All Sev2 Defects in a company,
	 */	
	public Set<Defect> getAllSev2Defects(Long companyID)
	{		
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return null;
		}
		Set<Defect> sev2Defects = new HashSet<Defect>();
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
	 * Set<Defect>
	 * @return collection of All Sev3 Defects in a company,
	 */	
	public Set<Defect> getAllSev3Defects(Long companyID)
	{	
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return null;
		}
		Set<Defect> sev3Defects = new HashSet<Defect>();
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
	 * Set<Defect>
	 * @return collection of All Sev4 Defects in a company,
	 */

	public Set<Defect> getAllSev4Defects(Long companyID)
	{		
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return null;
		}
		Set<Defect> sev4Defects = new HashSet<Defect>();
		for(final Defect defect : company.getDefects() )
		{
			if(defectService.isSev4(defect.getDefectID()));
			{
				sev4Defects.add(defect);				
			}
		}
		return sev4Defects;
	}	
	public Set<TestcenterUser> getAllTesters(Long companyID) {
		//		Company company = getCompany(companyID);
		//		if(company.getUsers() == null || company.getUsers().isEmpty())
		//		{
		//			return null;
		//		}
		//		Set<User> testers = new HashSet<User>();
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
	public Set<TestcenterUser> getAllSeniorTesters(Long companyID) {
		//		Company company = getCompany(companyID);
		//		if(company.getUsers() == null || company.getUsers().isEmpty())
		//		{
		//			return null;
		//		}
		//		Set<User> seniorTesters = new HashSet<User>();
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
	public Set<TestcenterUser> getAllDevelopers(Long companyID) {
		//		Company company = getCompany(companyID);
		//		if(company.getUsers() == null || company.getUsers().isEmpty())
		//		{
		//			return null;
		//		}
		//		Set<User> seniorTesters = new HashSet<User>();
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
	public Set<TestcenterUser> getAllSeniorDevelopers(Long companyID) {
		//		Company company = getCompany(companyID);
		//		if(company.getUsers() == null || company.getUsers().isEmpty())
		//		{
		//			return null;
		//		}
		//		Set<User> seniorTesters = new HashSet<User>();
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