/**
 * 
 */
package ie.cit.cloud.testcenter.respository.defect;

/**
 * @author byrnek1
 *
 */

import java.util.Set;

import ie.cit.cloud.testcenter.model.Defect;
import org.springframework.dao.EmptyResultDataAccessException;

public interface DefectRepository {

    /**
     * Returns existing Defect given by its ID
     * Defect
     * @param id
     *            Defect ID
     * @return Defect for given id, {@link EmptyResultDataAccessException} if no
     *         Defect was found
     */
	Defect get(long defectID);

    /**
     * Adds new Defect into repository
     * 
     * @param Defect
     */
    void create(Defect defect);

    /**
     * Updates existing Defect. Defect with the same ID as give Defect is updated
     * 
     * @param defect
     *            new Defect values
     */
    void update(Defect defect);

    /**
     * Deletes Defect item from repository.
     * 
     * @param defect
     */
    void delete(Defect defect);

    /**
     * Returns Defect items given by its ID
     * 
     * @param id
     *            Defect ID
     * @return Defect for given id, null if test was not found
     */
    Defect findById(long defectID);
    /**
     * Returns Defect items given by its name
     * 
     * @param id
     *            Defect ID
     * @return Defect for given name, null if Defect was not found
     */
    Defect findByName(String defectName);
    /**
     * Returns Defect items given by its name
     * 
     * @param id
     *            Defect ID
     * @return Defect for given name, null if Defect was not found
     */

	Set<Defect> findAllDefectsByParentID(long defectID);	
}
