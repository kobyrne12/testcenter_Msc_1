/**
 * 
 */
package ie.cit.cloud.testcenter.respository.requirement;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Requirement;
import org.springframework.dao.EmptyResultDataAccessException;

public interface RequirementRepository {

    /**
     * Returns existing Requirement given by its ID
     * Requirement
     * @param id
     *            Requirement ID
     * @return Requirement for given id, {@link EmptyResultDataAccessException} if no
     *         Requirement was found
     */
	Requirement get(Long requirementID);

    /**
     * Adds new Requirement into repository
     * 
     * @param Requirement
     */
    void create(Requirement requirement);

    /**
     * Updates existing Requirement. Requirement with the same ID as give Requirement is updated
     * 
     * @param requirement
     *            new Requirement values
     */
    void update(Requirement requirement);

    /**
     * Deletes Requirement item from repository.
     * 
     * @param requirement
     */
    void delete(Requirement requirement);

    /**
     * Returns Requirement items given by its ID
     * 
     * @param id
     *            Requirement ID
     * @return Requirement for given id, null if test was not found
     */
    Requirement findById(Long requirementID);
    /**
     * Returns Requirement items given by its name
     * 
     * @param id
     *            Requirement ID
     * @return Requirement for given name, null if Requirement was not found
     */
    Requirement findByName(String requirementName);
    /**
     * Returns Requirement items given by its name
     * 
     * @param id
     *            Requirement ID
     * @return Requirement for given name, null if Requirement was not found
     */	
}
