/**
 * 
 */
package ie.cit.cloud.testcenter.service.cycle;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.model.TestPlan;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.respository.cycle.CycleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CycleServiceImpl implements CycleService {
	
	@Autowired
    @Qualifier("hibernateCycleRespository")
    CycleRepository repo;        
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<Cycle> getAllCycles() {
	return repo.findAll();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Cycle getCycle(long cycleID) {
	return repo.findById(cycleID);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Cycle getCycleByName(String cycleName) {
    	return repo.findCycleByName(cycleName);
    }
    
   // @Secured("ROLE_ADMIN")
    @Transactional(rollbackFor=ConstraintViolationException.class)   
    public long addNewCycle(Cycle cycle) {
	repo.create(cycle);	
	return cycle.getCycleID();
    }
   
   // @Secured("ROLE_ADMIN")
    public void update(Cycle cycle) {
	repo.update(cycle);
    }  
    
  //  @Secured("ROLE_ADMIN")
    public void remove(long cycleID) {
	repo.delete(repo.get(cycleID));
    }
       
 
	public boolean updateCycle(long cycleID, Cycle cycle) {
		// TODO Auto-generated method stub
		return false;
	}
		
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Collection<Cycle> getAllCyclesByProjectID(long projectID) {
		return repo.findAllCyclesByProjectID(projectID);
	}
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public CycleSummary getCycleSummary(long cycleID) {
		Cycle cycle = repo.findById(cycleID);
		CycleSummary cycleSummary = new CycleSummary();
		cycleSummary.setCycleID(cycle.getCycleID());
		cycleSummary.setTotalNumberOfTestRuns(cycle.getTestruns().size());		
		return cycleSummary;
	}

	public void updateCycleWithId(long cycleID, Cycle cycle) {
		// TODO Auto-generated method stub
		
	}

	public void updateCycleNameWithId(long cycleID, Cycle cycle,
			String cycleName) {
		// TODO Auto-generated method stub
		
	}	

}