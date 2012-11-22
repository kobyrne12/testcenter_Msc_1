/**
 * 
 */
package ie.cit.cloud.testcenter.service.testcase;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.display.ColModelAndNames;
import ie.cit.cloud.testcenter.display.GridAttributes;
import ie.cit.cloud.testcenter.model.Company;
import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;
import ie.cit.cloud.testcenter.model.summary.CycleSummary;
import ie.cit.cloud.testcenter.model.summary.CycleSummaryList;
import ie.cit.cloud.testcenter.model.summary.ProjectSummary;
import ie.cit.cloud.testcenter.model.summary.ProjectSummaryList;
import ie.cit.cloud.testcenter.respository.cycle.CycleRepository;
import ie.cit.cloud.testcenter.respository.testcase.TestcaseRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestcaseServiceImpl implements TestcaseService {

	@Autowired
	@Qualifier("hibernateTestcaseRespository")
	TestcaseRepository testcaseRepo;      
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	ProjectService projectService;	
	
	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testcase getTestcase(long cycleID) {
		return testcaseRepo.findById(cycleID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Testcase getTestcaseByName(String testcaseName) {
		return testcaseRepo.findTestcaseByName(testcaseName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewTestcase(Testcase testcase) {
		testcaseRepo.create(testcase);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Testcase cycle) {
		testcaseRepo.update(cycle);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long testcaseID) {
		testcaseRepo.delete(getTestcase(testcaseID));
	}

}