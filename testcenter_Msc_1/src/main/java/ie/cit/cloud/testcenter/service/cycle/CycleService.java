/**
 * 
 */
package ie.cit.cloud.testcenter.service.cycle;

/**
 * @author byrnek1
 *
 */

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

    void updateCycleWithId(long cycleID, Cycle cycle);
    
    void updateCycleNameWithId(long cycleID, Cycle cycle,String cycleName);
    
    boolean updateCycle(long cycleID, Cycle cycle);

	Collection<Cycle> getAllCyclesByProjectID(long projectID);
	
	CycleSummary getCycleSummary(long cycleID);

	long getMaxProjectPosNum(long projectID);

}