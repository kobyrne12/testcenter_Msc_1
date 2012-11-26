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

import java.util.ArrayList;
import java.util.Set;

/**
 * Peforms business operation on company
 */
 public interface CompanyService {

	Set<Company> getAllCompanies();

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
	 * Returns a collection of cycles in a company 
	 * Set<Cycle>
	 * @return collection of cycles in a company,
	 */	
	 Set<Cycle> getAllCycles(long companyID);
	/**
	 * Returns a collection of All Testruns in a company 
	 * Set<Testrun>
	 * @return collection of All Testruns in a company,
	 */
	
	 Set<Testrun> getAllTestRuns(long companyID);
	/**
	 * Returns a collection of All Compulsory Testruns in a company 
	 * Set<Testrun>
	 * @return collection of All Compulsory Testruns in a company,
	 */	
	 Set<Testrun> getCompulsoryTestRuns(long companyID);
	/**
	 * Returns a collection of All Optional Testruns in a company 
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a company,
	 */	
	 Set<Testrun> getOptionalTestRuns(long companyID);
	/**
	 * Returns a collection of Testcases in a company
	 * Set<Testcase>
	 * @return collection of Testcases in a company
	 */	
	 Set<Testcase> getAllTestCases(long companyID);
	/**
	 * Returns a collection of Compulsory Testcases in a company
	 * Set<Testcase>
	 * @return collection of Compulsory Testcases in a company,
	 */	
	 Set<Testcase> getCompulsoryTestCases(long companyID);
	/**
	 * Returns a collection of Optional Testcases in a company
	 * Set<Testcase>
	 * @return collection of Optional Testcases in a company,
	 */	
	 Set<Testcase> getOptionalTestCases(long companyID);
	/**
	 * Returns a collection of Testplans in a company
	 * Set<Testplan>
	 * @return collection of Testplans in a company
	 */	
	 Set<Testplan> getAllTestPlans(long companyID);
	/**
	 * Returns a collection of Compulsory Testplans in a company
	 * Set<Testplan>
	 * @return collection of Compulsory Testplans in a company,
	 */	
	 Set<Testplan> getCompulsoryTestPlans(long companyID);
	/**
	 * Returns a collection of Optional Testplans in a company
	 * Set<Testcase>
	 * @return collection of Optional Testplans in a company,
	 */	
	 Set<Testplan> getOptionalTestPlans(long companyID);
	/**
	 * Returns a collection of All Sev1 Defects in a company 
	 * Set<Defect>
	 * @return collection of All Sev1 Defects in a company,
	 */	
	 Set<Defect> getAllSev1Defects(long companyID);
	/**
	 * Returns a collection of All Sev2 Defects in a company 
	 * Set<Defect>
	 * @return collection of All Sev2 Defects in a company,
	 */	
	 Set<Defect> getAllSev2Defects(long companyID);
	/**
	 * Returns a collection of All Sev3 Defects in a company 
	 * Set<Defect>
	 * @return collection of All Sev3 Defects in a company,
	 */	
	 Set<Defect> getAllSev3Defects(long companyID);
	/**
	 * Returns a collection of All Sev4 Defects in a company 
	 * Set<Defect>
	 * @return collection of All Sev4 Defects in a company,
	 */
	
	 Set<Defect> getAllSev4Defects(long companyID);
	 Set<TestcenterUser> getAllTesters(long companyID);
	 Set<TestcenterUser> getAllSeniorTesters(long companyID);
	 Set<TestcenterUser> getAllDevelopers(long companyID);
	 Set<TestcenterUser> getAllSeniorDevelopers(long companyID);
	//TODO: add user details i.e getAllTestersCount(), getAllSeniorTestersCount()
	
}