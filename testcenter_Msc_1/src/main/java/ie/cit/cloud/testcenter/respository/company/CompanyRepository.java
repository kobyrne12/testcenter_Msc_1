/**
 * 
 */
package ie.cit.cloud.testcenter.respository.company;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Company;

import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;

public interface CompanyRepository {

    /**
     * Returns existing Company given by its ID
     * Company
     * @param id
     *            Company ID
     * @return Company for given id, {@link EmptyResultDataAccessException} if no
     *         Company was found
     */
	Company get(Long companyID);

    /**
     * Adds new Company into repository
     * 
     * @param Company
     */
    void create(Company company);

    /**
     * Updates existing Company. Company with the same ID as give Company is updated
     * 
     * @param testplan
     *            new Company values
     */
    void update(Company company);

    /**
     * Deletes Company item from repository.
     * 
     * @param testplan
     */
    void delete(Company company);

    // ================ various find-er methods ================
    /**
     * Returns list of all tests
     * 
     * @return all Companys
     */
    Set<Company> findAll();

    /**
     * Returns Company items given by its ID
     * 
     * @param id
     *            Company ID
     * @return Company for given id, null if test was not found
     */
    Company findById(Long companyID);
    /**
     * Returns Company items given by its name
     * 
     * @param id
     *            Company ID
     * @return Company for given name, null if Company was not found
     */
    Company findCompanyByName(String testplanname);   
    
   

}
