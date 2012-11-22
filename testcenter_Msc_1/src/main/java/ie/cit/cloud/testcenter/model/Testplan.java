/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "Testplan")
public class Testplan {

	@Id        
	@GeneratedValue
	@Column(name = "testplanID")
	private Long testplanID;	

	@Basic
	@Column(name="companyID")
	private long companyID;    

	@Length(min = 2, max = 32, message = "Testplan name must be between 2 to 32 characters.")
	@NotEmpty(message = "TestPlan Name is required.")
	private String testplanName;  	

	@OneToMany(fetch=FetchType.EAGER, targetEntity=Testcase.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "testplanID", referencedColumnName="testplanID")		
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Testcase> testcases;    
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=TestplanSection.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "testplanID", referencedColumnName="testplanID")		
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<TestplanSection> testplanSections;    

	//@Basic    
	//private String testplanSection;
	
	//    @NotEmpty(message = "Tester Name is required.")
	//    private String testername;    
	//    @Basic    
	//    private int totalTests;
	//    @Basic    
	//    private int totalNotRun;
	//    @Basic    
	//    private int totalPassed;
	//    @Basic    
	//    private int totalFailed;
	//    @Basic    
	//    private int totalInProgress;
	//    @Basic    
	//    private int totalDeferred;
	//    @Basic    
	//    private int totalBlocked;    
	// 	  @Basic
	// 	  private String user;     
	// 	  @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")

	public Testplan() {		
	}
	/**
	 * @param companyID
	 * @param testplanName
	 */
	public Testplan(Long companyID,String testplanName) {
		this.companyID = companyID;
		this.testplanName = testplanName;	
	}

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the testplanName
	 */
	public String getTestplanName() {
		return testplanName;
	}

	/**
	 * @param testplanName the testplanName to set
	 */
	public void setTestplanName(String testplanName) {
		this.testplanName = testplanName;
	}

	/**
	 * @return the testcases
	 */
	public Collection<Testcase> getTestcases() {
		return testcases;
	}

	/**
	 * @param testcases the testcases to set
	 */
	public void setTestcases(Collection<Testcase> testcases) {
		this.testcases = testcases;
	}

	/**
	 * @return the testplanID
	 */
	public Long getTestplanID() {
		return testplanID;
	}
	/**
	 * @return the testplanSections
	 */
	public Collection<TestplanSection> getTestplanSections() {
		return testplanSections;
	}
	/**
	 * @param testplanSections the testplanSections to set
	 */
	public void setTestplanSections(Collection<TestplanSection> testplanSections) {
		this.testplanSections = testplanSections;
	}
	
}