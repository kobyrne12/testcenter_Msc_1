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
	 * Returns a collection of cycles in a company 
	 * Collection<Cycle>
	 * @return collection of cycles in a company,
	 */	
	 Collection<Cycle> getAllCycles(long companyID);
	/**
	 * Returns a collection of All Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a company,
	 */
	
	 Collection<Testrun> getAllTestRuns(long companyID);
	/**
	 * Returns a collection of All Compulsory Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Compulsory Testruns in a company,
	 */	
	 Collection<Testrun> getCompulsoryTestRuns(long companyID);
	/**
	 * Returns a collection of All Optional Testruns in a company 
	 * Collection<Testrun>
	 * @return collection of All Optional Testruns in a company,
	 */	
	 Collection<Testrun> getOptionalTestRuns(long companyID);
	/**
	 * Returns a collection of Testcases in a company
	 * Collection<Testcase>
	 * @return collection of Testcases in a company
	 */	
	 Collection<Testcase> getAllTestCases(long companyID);
	/**
	 * Returns a collection of Compulsory Testcases in a company
	 * Collection<Testcase>
	 * @return collection of Compulsory Testcases in a company,
	 */	
	 Collection<Testcase> getCompulsoryTestCases(long companyID);
	/**
	 * Returns a collection of Optional Testcases in a company
	 * Collection<Testcase>
	 * @return collection of Optional Testcases in a company,
	 */	
	 Collection<Testcase> getOptionalTestCases(long companyID);
	/**
	 * Returns a collection of Testplans in a company
	 * Collection<Testplan>
	 * @return collection of Testplans in a company
	 */	
	 Collection<Testplan> getAllTestPlans(long companyID);
	/**
	 * Returns a collection of Compulsory Testplans in a company
	 * Collection<Testplan>
	 * @return collection of Compulsory Testplans in a company,
	 */	
	 Collection<Testplan> getCompulsoryTestPlans(long companyID);
	/**
	 * Returns a collection of Optional Testplans in a company
	 * Collection<Testcase>
	 * @return collection of Optional Testplans in a company,
	 */	
	 Collection<Testplan> getOptionalTestPlans(long companyID);
	/**
	 * Returns a collection of All Sev1 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev1 Defects in a company,
	 */	
	 Collection<Defect> getAllSev1Defects(long companyID);
	/**
	 * Returns a collection of All Sev2 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev2 Defects in a company,
	 */	
	 Collection<Defect> getAllSev2Defects(long companyID);
	/**
	 * Returns a collection of All Sev3 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev3 Defects in a company,
	 */	
	 Collection<Defect> getAllSev3Defects(long companyID);
	/**
	 * Returns a collection of All Sev4 Defects in a company 
	 * Collection<Defect>
	 * @return collection of All Sev4 Defects in a company,
	 */
	
	 Collection<Defect> getAllSev4Defects(long companyID);
	 Collection<TestcenterUser> getAllTesters(long companyID);
	 Collection<TestcenterUser> getAllSeniorTesters(long companyID);
	 Collection<TestcenterUser> getAllDevelopers(long companyID);
	 Collection<TestcenterUser> getAllSeniorDevelopers(long companyID);
	//TODO: add user details i.e getAllTestersCount(), getAllSeniorTestersCount()
	
}