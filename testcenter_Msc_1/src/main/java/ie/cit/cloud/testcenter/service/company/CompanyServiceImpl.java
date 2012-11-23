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
	 * Returns total Number of Projects for a company
	 * int
	 * @return total Number of Projects for a company,
	 */
	
	public int getAllProjectsCount(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getProjects() == null || company.getProjects().isEmpty())
		{
			return 0;
		}
		return company.getProjects().size();
	}
	/**
	 * Returns total Number of Cycles for a company
	 * int
	 * @return total Number of Cycles for a company,
	 */	
	public int getAllCyclesCount(long companyID)
	{
		if(getAllCycles(companyID) != null)
		{
			return getAllCycles(companyID).size();
		}
		else
		{
			return 0;
		}		
	}
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
			cycles.addAll(project.getCycles());			
		}
		return cycles;
	}
	/**
	 * Returns total Number of All Testruns for a company
	 * int
	 * @return total Number of All Testruns for a company,
	 */	
	public int getAllTestRunsCount(long companyID)
	{		
		if(getAllTestRuns(companyID) != null)
		{
			return getAllTestRuns(companyID).size();
		}
		else
		{
			return 0;
		}		
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
			testruns.addAll(cycle.getTestruns());
		}
		return testruns;
	}	
	
	/**
	 * Returns total Number of All Testruns for a company
	 * int
	 * @return total Number of All Testruns for a company,
	 */	
	public int getRequiredTestRunsCount(long companyID)
	{	
		if(getRequiredTestRuns(companyID) != null)
		{
			return getRequiredTestRuns(companyID).size();
		}
		else
		{
			return 0;
		}			
	}
	/**
	 * Returns a collection of All Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a company,
	 */	
	public Collection<Testrun> getRequiredTestRuns(long companyID)
	{
		Collection<Cycle> cycles = getAllCycles(companyID);
		if(cycles == null || cycles.isEmpty())
		{
			return null;
		}
		Collection<Testrun> testruns = new ArrayList<Testrun>();
		for(final Cycle cycle : cycles)
		{			
			testruns.addAll(cycleService.getCascadedRequiredTestRuns(cycle.getCycleID()));
		}
		return testruns;
	}
	
	/**
	 * Returns total Number of All requirements for a company
	 * int
	 * @return total Number of All requirements for a company,
	 */		
	public int getAllRequirementsCount(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getRequirements() == null || company.getRequirements().isEmpty())
		{
			return 0;
		}
		return company.getRequirements().size();
	}
	
	/**
	 * Returns total Number of All environments for a company
	 * int
	 * @return total Number of All environments for a company,
	 */		
	public int getAllEnvironmentsCount(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getEnvironments() == null || company.getEnvironments().isEmpty())
		{
			return 0;
		}
		return company.getEnvironments().size();
	}
	/**
	 * Returns total Number of All defects for a company
	 * int
	 * @return total Number of All defects for a company,
	 */		
	public int getAllDefectsCount(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getDefects() == null || company.getDefects().isEmpty())
		{
			return 0;
		}
		return company.getDefects().size();
	}
	/**
	 * Returns total Number of All testcases for a company
	 * int
	 * @return total Number of All testcases for a company,
	 */		
	public int getAllTestCasesCount(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getTestcases() == null || company.getTestcases().isEmpty())
		{
			return 0;
		}
		return company.getTestcases().size();
	}
	/**
	 * Returns total Number of All testplans for a company
	 * int
	 * @return total Number of All testplans for a company,
	 */		
	public int getAllTestPlansCount(long companyID)
	{
		Company company = getCompany(companyID);
		if(company.getTestplans() == null || company.getTestplans().isEmpty())
		{
			return 0;
		}
		return company.getTestplans().size();
	}
	/**
	 * Returns total Number of Sev 1 Defects for a company
	 * int
	 * @return total Number of Sev 1 Defects for a company,
	 */	
	public int getAllSev1DefectsCount(long companyID)
	{
		if(getAllSev1Defects(companyID) != null)
		{
			return getAllSev1Defects(companyID).size();
		}
		else
		{
			return 0;
		}		
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
	 * Returns total Number of Sev 2 Defects for a company
	 * int
	 * @return total Number of Sev 2 Defects for a company,
	 */	
	public int getAllSev2DefectsCount(long companyID)
	{
		if(getAllSev2Defects(companyID) != null)
		{
			return getAllSev2Defects(companyID).size();
		}
		else
		{
			return 0;
		}		
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
	 * Returns total Number of Sev 3 Defects for a company
	 * int
	 * @return total Number of Sev 3 Defects for a company,
	 */	
	public int getAllSev3DefectsCount(long companyID)
	{
		if(getAllSev3Defects(companyID) != null)
		{
			return getAllSev3Defects(companyID).size();
		}
		else
		{
			return 0;
		}		
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
	 * Returns total Number of Sev 4 Defects for a company
	 * int
	 * @return total Number of Sev 4 Defects for a company,
	 */	
	public int getAllSev4DefectsCount(long companyID)
	{
		if(getAllSev4Defects(companyID) != null)
		{
			return getAllSev4Defects(companyID).size();
		}
		else
		{
			return 0;
		}		
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
	public int getallTestersCount(long companyID) {
		if(getAllTesters(companyID) != null)
		{
			return getAllTesters(companyID).size();
		}
		else
		{
			return 0;
		}				
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
	public int getallSeniorTestersCount(long companyID) {
		if(getAllSeniorTesters(companyID) != null)
		{
			return getAllSeniorTesters(companyID).size();
		}
		else
		{
			return 0;
		}		
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
	public int getallDevelopersCount(long companyID) {
		if(getAllDevelopers(companyID) != null)
		{
			return getAllDevelopers(companyID).size();
		}
		else
		{
			return 0;
		}	
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
	public int getallSeniorDevelopersCount(long companyID) {
		if(getAllSeniorDevelopers(companyID) != null)
		{
			return getAllSeniorDevelopers(companyID).size();
		}
		else
		{
			return 0;
		}			
	}
	
}