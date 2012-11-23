/**
 * 
 */
package ie.cit.cloud.testcenter.service.defect;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Defect;

/**
 * Peforms business operation on defect 
 */
public interface DefectService {
 
	void addNewDefect(Defect defect);

	Defect getDefect(long defectID);   
    
	Defect getDefectByName(String defectName);
    
    void update(Defect defect);
    
    void remove(long defectID);  
    
    boolean isSev1(long defectID);

	boolean isSev2(long defectID);

	boolean isSev3(long defectID);

	boolean isSev4(long defectID);

}