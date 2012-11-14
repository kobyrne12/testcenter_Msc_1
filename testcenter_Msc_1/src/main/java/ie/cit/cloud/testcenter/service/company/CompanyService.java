/**
 * 
 */
package ie.cit.cloud.testcenter.service.company;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;

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
}