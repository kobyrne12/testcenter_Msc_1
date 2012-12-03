/**
 * 
 */
package ie.cit.cloud.testcenter.service.defect;

/**
 * @author byrnek1
 *
 */

import java.util.Set;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.model.Environment;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.model.Requirement;
import ie.cit.cloud.testcenter.model.Testcase;
import ie.cit.cloud.testcenter.model.TestcenterUser;
import ie.cit.cloud.testcenter.model.Testplan;
import ie.cit.cloud.testcenter.model.Testrun;

/**
 * Peforms business operation on defect 
 */
public interface DefectService {
 
	void addNewDefect(Defect defect);

	Defect getDefect(Long defectID);   
    
	Defect getDefectByName(String defectName);
    
    void update(Defect defect);
    
    void remove(Long defectID);  
    
    boolean isSev1(Long defectID);

	boolean isSev2(Long defectID);

	boolean isSev3(Long defectID);

	boolean isSev4(Long defectID);
	
	Set<Defect> getAllChildDefects(Long defectID);
	
	//////////////////////////////////	
	/**
	 * Returns a Total collection of child Defects including the parent
	 * Set<Defect>
	 * @return Total collection of child Defects including the parent
	 */
	 Set<Defect> getParentAndChildDefects(Long defectID);
	/**
	 * Returns a collection of Child defects for a defect 
	 * Set<Defect>
	 * @return collection of Child defects for a defect
	 */	
	 Set<Defect> getChildDefects(Long defectID);
	/**
	 * Returns a Defect Parent Defect  
	 * Defect
	 * @return a Defect Parent Defect, otherwise null
	 */	
	 Defect getParentDefect(Long defectID);
	/**
	 * Returns a collection of All Testruns in a defect incl all child defects 
	 * Set<Testrun>
	 * @return collection of All Testruns in a defect incl all child defects,
	 */	
	 Set<Testrun> getCascadedAllTestRuns(Long defectID);
	/**
	 * Returns a collection of All compulsory Testruns in a defect incl all child defects 
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a defect incl all child defects,
	 */	
	 Set<Testrun> getCascadedCompulsoryTestRuns(Long defectID);
	/**
	 * Returns a collection of All Optional Testruns in a defect incl all child defects 
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a defect incl all child defects,
	 */	
	 Set<Testrun> getCascadedOptionalTestRuns(Long defectID);
	/**
	 * Returns a collection of All Testcases in a defect incl all child defects 
	 * Set<Testcase>
	 * @return collection of All Testcases in a defect incl all child defects,
	 */	
	 Set<Testcase> getCascadedAllTestCases(Long defectID);
	/**
	 * Returns a collection of All Compulsory Testcases in a defect incl all child defects 
	 * Set<Testcase>
	 * @return collection of All Compulsory Testcases in a defect incl all child defects,
	 */	
	 Set<Testcase> getCascadedCompulsoryTestCases(Long defectID);
	/**
	 * Returns a collection of All Optional Testcases in a defect incl all child defects 
	 * Set<Testcase>
	 * @return collection of All Optional Testcases in a defect incl all child defects,
	 */	
	 Set<Testcase> getCascadedOptionalTestCases(Long defectID);
	/**
	 * Returns a collection of All Testplans in a defect incl all child defects 
	 * Set<Testplan>
	 * @return collection of All Testplans in a defect incl all child defects,
	 */	
	 Set<Testplan> getCascadedAllTestPlans(Long defectID);
	/**
	 * Returns a collection of All Compulsory Testplans in a defect incl all child defects 
	 * Set<Testplan>
	 * @return collection of All Compulsory Testplans in a defect incl all child defects,
	 */	
	 Set<Testplan> getCascadedCompulsoryTestPlans(Long defectID);
	/**
	 * Returns a collection of All Optional Testplans in a defect incl all child defects 
	 * Set<Testplan>
	 * @return collection of All Optional Testplans in a defect incl all child defects,
	 */	
	 Set<Testplan> getCascadedOptionalTestPlans(Long defectID);
	 /**
	  * Returns a collection of Projects in a defect incl all child defects 
	  * Set<Project>
	  * @return collection of Projects in a defect incl all child defects,
	  */		
	 Set<Project> getCascadedProjects(Long defectID) ;
	 /**
	  * Returns a collection of Cycles in a defect incl all child defects 
	  * Set<Cycle>
	  * @return collection of Cycles in a defect incl all child defects,
	  */		
	 Set<Cycle> getCascadedCycles(Long defectID) ;
	/**
	 * Returns a collection of Environments in a defect incl all child defects 
	 * Set<Environment>
	 * @return collection of Environments in a defect incl all child defects,
	 */		
	 Set<Environment> getCascadedEnvironments(Long defectID) ;
	/**
	 * Returns a collection of Requirements in a defect incl all child defects 
	 * Set<Requirement>
	 * @return collection of Requirements in a defect incl all child defects,
	 */		
	 Set<Requirement> getCascadedRequirements(Long defectID) ;
	
	 Set<TestcenterUser> getCascadedTesters(Long defectID);
	 Set<TestcenterUser> getCascadedSnrTesters(Long defectID);
	 Set<TestcenterUser> getCascadedDevelopers(Long defectID);
	 Set<TestcenterUser> getCascadedSnrDevelopers(Long defectID);
}