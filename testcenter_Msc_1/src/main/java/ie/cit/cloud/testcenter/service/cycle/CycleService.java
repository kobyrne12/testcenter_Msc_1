/**
 * 
 */
package ie.cit.cloud.testcenter.service.cycle;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;

import java.util.Collection;

/**
 * Peforms business operation on cycle
 */
public interface CycleService {

    Collection<Cycle> getAllCycles();

    long addNewCycle(Cycle cycle);

    Cycle getCycle(long cycleID);   
    
    Cycle getCycleByName(String cycleName);
    
    void update(Cycle cycle);
    
    void remove(long cycleID);
   
    boolean updateCycle(Cycle cycle);

	Collection<Cycle> getAllCyclesByProjectID(long projectID);
	
	CycleSummary getCycleSummary(long cycleID);

	long getMaxProjectPosNum(long projectID);
	
	CycleSummaryList getGridCycles(long projectID, String testplanID,
			String testcaseID, String userID, String environmentID,
			String requirementID, String defectID, String testrunID);

	ColModelAndNames getColumnModelAndNames(Long companyID);

}