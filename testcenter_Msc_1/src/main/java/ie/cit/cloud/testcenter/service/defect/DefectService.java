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

	Defect getDefect(long defectID);   
    
	Defect getDefectByName(String defectName);
    
    void update(Defect defect);
    
    void remove(long defectID);  
    
    boolean isSev1(long defectID);

	boolean isSev2(long defectID);

	boolean isSev3(long defectID);

	boolean isSev4(long defectID);
	
	Set<Defect> getAllChildDefects(long defectID);
	
	//////////////////////////////////	
	/**
	 * Returns a Total collection of child Defects including the parent
	 * Set<Defect>
	 * @return Total collection of child Defects including the parent
	 */
	 Set<Defect> getParentAndChildDefects(long defectID);
	/**
	 * Returns a collection of Child defects for a defect 
	 * Set<Defect>
	 * @return collection of Child defects for a defect
	 */	
	 Set<Defect> getChildDefects(long defectID);
	/**
	 * Returns a Defect Parent Defect  
	 * Defect
	 * @return a Defect Parent Defect, otherwise null
	 */	
	 Defect getParentDefect(long defectID);
	/**
	 * Returns a collection of All Testruns in a defect incl all child defects 
	 * Set<Testrun>
	 * @return collection of All Testruns in a defect incl all child defects,
	 */	
	 Set<Testrun> getCascadedAllTestRuns(long defectID);
	/**
	 * Returns a collection of All compulsory Testruns in a defect incl all child defects 
	 * Set<Testrun>
	 * @return collection of All compulsory Testruns in a defect incl all child defects,
	 */	
	 Set<Testrun> getCascadedCompulsoryTestRuns(long defectID);
	/**
	 * Returns a collection of All Optional Testruns in a defect incl all child defects 
	 * Set<Testrun>
	 * @return collection of All Optional Testruns in a defect incl all child defects,
	 */	
	 Set<Testrun> getCascadedOptionalTestRuns(long defectID);
	/**
	 * Returns a collection of All Testcases in a defect incl all child defects 
	 * Set<Testcase>
	 * @return collection of All Testcases in a defect incl all child defects,
	 */	
	 Set<Testcase> getCascadedAllTestCases(long defectID);
	/**
	 * Returns a collection of All Compulsory Testcases in a defect incl all child defects 
	 * Set<Testcase>
	 * @return collection of All Compulsory Testcases in a defect incl all child defects,
	 */	
	 Set<Testcase> getCascadedCompulsoryTestCases(long defectID);
	/**
	 * Returns a collection of All Optional Testcases in a defect incl all child defects 
	 * Set<Testcase>
	 * @return collection of All Optional Testcases in a defect incl all child defects,
	 */	
	 Set<Testcase> getCascadedOptionalTestCases(long defectID);
	/**
	 * Returns a collection of All Testplans in a defect incl all child defects 
	 * Set<Testplan>
	 * @return collection of All Testplans in a defect incl all child defects,
	 */	
	 Set<Testplan> getCascadedAllTestPlans(long defectID);
	/**
	 * Returns a collection of All Compulsory Testplans in a defect incl all child defects 
	 * Set<Testplan>
	 * @return collection of All Compulsory Testplans in a defect incl all child defects,
	 */	
	 Set<Testplan> getCascadedCompulsoryTestPlans(long defectID);
	/**
	 * Returns a collection of All Optional Testplans in a defect incl all child defects 
	 * Set<Testplan>
	 * @return collection of All Optional Testplans in a defect incl all child defects,
	 */	
	 Set<Testplan> getCascadedOptionalTestPlans(long defectID);
	 /**
	  * Returns a collection of Projects in a defect incl all child defects 
	  * Set<Project>
	  * @return collection of Projects in a defect incl all child defects,
	  */		
	 Set<Project> getCascadedProjects(long defectID) ;
	 /**
	  * Returns a collection of Cycles in a defect incl all child defects 
	  * Set<Cycle>
	  * @return collection of Cycles in a defect incl all child defects,
	  */		
	 Set<Cycle> getCascadedCycles(long defectID) ;
	/**
	 * Returns a collection of Environments in a defect incl all child defects 
	 * Set<Environment>
	 * @return collection of Environments in a defect incl all child defects,
	 */		
	 Set<Environment> getCascadedEnvironments(long defectID) ;
	/**
	 * Returns a collection of Requirements in a defect incl all child defects 
	 * Set<Requirement>
	 * @return collection of Requirements in a defect incl all child defects,
	 */		
	 Set<Requirement> getCascadedRequirements(long defectID) ;
	
	 Set<TestcenterUser> getCascadedTesters(long defectID);
	 Set<TestcenterUser> getCascadedSnrTesters(long defectID);
	 Set<TestcenterUser> getCascadedDevelopers(long defectID);
	 Set<TestcenterUser> getCascadedSnrDevelopers(long defectID);
}