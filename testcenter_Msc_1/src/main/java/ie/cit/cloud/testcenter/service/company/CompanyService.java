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
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Peforms business operation on company
 */
public interface CompanyService {

	Collection<Company> getAllCompanies();

	void addNewCompany(Company company);

	Company getCompany(long companyID);

	Company getCompanyByName(String companyName);

	void update(Company company);

	void remove(long companyID);

	void updateCompanyWithId(long companyID, Company company);

	void updateCompanyNameWithId(long companyID, Company company,String companyName);

	boolean updateCompany(long companyID, Company company); 
	//////////////

	/**
	 * Returns total Number of Projects for a company
	 * int
	 * @return total Number of Projects for a company,
	 */	
	int getAllProjectsCount(long companyID);
	/**
	 * Returns total Number of Cycles for a company
	 * int
	 * @return total Number of Cycles for a company,
	 */	
	int getAllCyclesCount(long companyID);
	/**
	 * Returns a collection of cycles in a company 
	 * Collection<Cycle>
	 * @return collection of cycles in a company,
	 */
	Collection<Cycle> getAllCycles(long companyID);
	/**
	 * Returns total Number of All Testruns for a company
	 * int
	 * @return total Number of All Testruns for a company,
	 */
	int getAllTestRunCount(long companyID);
	/**
	 * Returns a collection of All Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a company,
	 */
	Collection<Testrun> getAllTestRuns(long companyID);
	/**
	 * Returns total Number of All Testruns for a company
	 * int
	 * @return total Number of All Testruns for a company,
	 */
	int getRequiredTestRunCount(long companyID);
	/**
	 * Returns a collection of All Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a company,
	 */
	Collection<Testrun> getRequiredTestRuns(long companyID);
	/**
	 * Returns total Number of All requirements for a company
	 * int
	 * @return total Number of All requirements for a company,
	 */	
	int getAllRequirementsCount(long companyID);
	/**
	 * Returns total Number of All environments for a company
	 * int
	 * @return total Number of All environments for a company,
	 */	
	int getAllEnvironmentsCount(long companyID);
	/**
	 * Returns total Number of All defects for a company
	 * int
	 * @return total Number of All defects for a company,
	 */	
	int getAllDefectsCount(long companyID);
	/**
	 * Returns total Number of All testcases for a company
	 * int
	 * @return total Number of All testcases for a company,
	 */	
	int getAllTestCasesCount(long companyID);
	/**
	 * Returns total Number of All testplans for a company
	 * int
	 * @return total Number of All testplans for a company,
	 */	
	int getAllTestPlansCount(long companyID);
	/**
	 * Returns a collection of All Sev1 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev1 Defects in a company,
	 */
	Collection<Defect> getAllSev1Defects(long companyID);
	/**
	 * Returns total Number of All Sev1 Defects for a company
	 * int
	 * @return total Number of All Sev1 Defects for a company,
	 */	
	int getAllSev1DefectsCount(long companyID);
	/**
	 * Returns a collection of All Sev2 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev2 Defects in a company,
	 */
	Collection<Defect> getAllSev2Defects(long companyID);
	/**
	 * Returns total Number of All Sev2 Defects for a company
	 * int
	 * @return total Number of All Sev2 Defects for a company,
	 */	
	int getAllSev2DefectsCount(long companyID);
	/**
	 * Returns a collection of All Sev3 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev3 Defects in a company,
	 */
	public Collection<Defect> getAllSev3Defects(long companyID);
	/**
	 * Returns total Number of All Sev3 Defects for a company
	 * int
	 * @return total Number of All Sev3 Defects for a company,
	 */	
	int getAllSev3DefectsCount(long companyID);
	/**
	 * Returns a collection of All Sev4 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev4 Defects in a company,
	 */	
	Collection<Defect> getAllSev4Defects(long companyID);
	/**
	 * Returns total Number of All Sev4 Defects for a company
	 * int
	 * @return total Number of All Sev4 Defects for a company,
	 */		
	int getAllSev4DefectsCount(long companyID);	
	
	//TODO: add user details i.e getAllTestersCount(), getAllSeniorTestersCount()
	
}