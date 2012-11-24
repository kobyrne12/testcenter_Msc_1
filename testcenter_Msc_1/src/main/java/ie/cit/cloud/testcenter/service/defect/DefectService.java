/**
 * 
 */
package ie.cit.cloud.testcenter.service.defect;

/**
 * @author byrnek1
 *
 */

import java.util.Collection;

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
	
	Collection<Defect> getAllChildDefects(long defectID);
	
	//////////////////////////////////	
	/**
	 * Returns a Total collection of child Defects including the parent
	 * Collection<Defect>
	 * @return Total collection of child Defects including the parent
	 */
	 Collection<Defect> getParentAndChildDefects(long defectID);
	/**
	 * Returns a collection of Child defects for a defect 
	 * Collection<Defect>
	 * @return collection of Child defects for a defect
	 */	
	 Collection<Defect> getChildDefects(long defectID);
	/**
	 * Returns a Defect Parent Defect  
	 * Defect
	 * @return a Defect Parent Defect, otherwise null
	 */	
	 Defect getParentDefect(long defectID);
	/**
	 * Returns a collection of All Testruns in a defect incl all child defects 
	 * Collection<Testrun>
	 * @return collection of All Testruns in a defect incl all child defects,
	 */	
	 Collection<Testrun> getCascadedAllTestRuns(long defectID);
	/**
	 * Returns a collection of All compulsory Testruns in a defect incl all child defects 
	 * Collection<Testrun>
	 * @return collection of All compulsory Testruns in a defect incl all child defects,
	 */	
	 Collection<Testrun> getCascadedCompulsoryTestRuns(long defectID);
	/**
	 * Returns a collection of All Optional Testruns in a defect incl all child defects 
	 * Collection<Testrun>
	 * @return collection of All Optional Testruns in a defect incl all child defects,
	 */	
	 Collection<Testrun> getCascadedOptionalTestRuns(long defectID);
	/**
	 * Returns a collection of All Testcases in a defect incl all child defects 
	 * Collection<Testcase>
	 * @return collection of All Testcases in a defect incl all child defects,
	 */	
	 Collection<Testcase> getCascadedAllTestCases(long defectID);
	/**
	 * Returns a collection of All Compulsory Testcases in a defect incl all child defects 
	 * Collection<Testcase>
	 * @return collection of All Compulsory Testcases in a defect incl all child defects,
	 */	
	 Collection<Testcase> getCascadedCompulsoryTestCases(long defectID);
	/**
	 * Returns a collection of All Optional Testcases in a defect incl all child defects 
	 * Collection<Testcase>
	 * @return collection of All Optional Testcases in a defect incl all child defects,
	 */	
	 Collection<Testcase> getCascadedOptionalTestCases(long defectID);
	/**
	 * Returns a collection of All Testplans in a defect incl all child defects 
	 * Collection<Testplan>
	 * @return collection of All Testplans in a defect incl all child defects,
	 */	
	 Collection<Testplan> getCascadedAllTestPlans(long defectID);
	/**
	 * Returns a collection of All Compulsory Testplans in a defect incl all child defects 
	 * Collection<Testplan>
	 * @return collection of All Compulsory Testplans in a defect incl all child defects,
	 */	
	 Collection<Testplan> getCascadedCompulsoryTestPlans(long defectID);
	/**
	 * Returns a collection of All Optional Testplans in a defect incl all child defects 
	 * Collection<Testplan>
	 * @return collection of All Optional Testplans in a defect incl all child defects,
	 */	
	 Collection<Testplan> getCascadedOptionalTestPlans(long defectID);
	 /**
	  * Returns a collection of Projects in a defect incl all child defects 
	  * Collection<Project>
	  * @return collection of Projects in a defect incl all child defects,
	  */		
	 Collection<Project> getCascadedProjects(long defectID) ;
	 /**
	  * Returns a collection of Cycles in a defect incl all child defects 
	  * Collection<Cycle>
	  * @return collection of Cycles in a defect incl all child defects,
	  */		
	 Collection<Cycle> getCascadedCycles(long defectID) ;
	/**
	 * Returns a collection of Environments in a defect incl all child defects 
	 * Collection<Environment>
	 * @return collection of Environments in a defect incl all child defects,
	 */		
	 Collection<Environment> getCascadedEnvironments(long defectID) ;
	/**
	 * Returns a collection of Requirements in a defect incl all child defects 
	 * Collection<Requirement>
	 * @return collection of Requirements in a defect incl all child defects,
	 */		
	 Collection<Requirement> getCascadedRequirements(long defectID) ;
	
	 Collection<TestcenterUser> getCascadedTesters(long defectID);
	 Collection<TestcenterUser> getCascadedSnrTesters(long defectID);
	 Collection<TestcenterUser> getCascadedDevelopers(long defectID);
	 Collection<TestcenterUser> getCascadedSnrDevelopers(long defectID);
}