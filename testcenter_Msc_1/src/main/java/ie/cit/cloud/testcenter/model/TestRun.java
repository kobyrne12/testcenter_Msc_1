/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name = "TestRun")
public class TestRun {

	@Id        
	@GeneratedValue
	@Column(name = "testRunID")
    private long testRunID;
	
	
	@Length(min = 2, max = 32, message = "TestRun name must be between 2 to 32 characters.")
	@NotEmpty(message = "TestRun Name is required.")
	private String testRunName;  	
	
	
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "projectID", nullable = false)
//	private Project project;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "projectID", nullable = false)
//	private Company project;
	
	@Basic
	@Column(name="cycleID")
	private long cycleID;
	
	@Basic
	@Column(name = "testCaseID")
    private long testCaseID;
	
    
    public TestRun() {	
    }
       

    public TestRun(long cycleID,String testRunName) {
    	this.cycleID = cycleID;    	
    	this.testRunName = testRunName;  
    	
    }    
	

	public String GetDateNow()
	{ 	 
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime()); 
		//System.out.println("*********** Now the currentDate is :=>  " + currentDate);
		System.out.println("*********** Now the dateNow is :=>  " + dateNow);
		return dateNow;
	}

	private String getCurrentUser() 
	{
		System.out.println("*********** Current User is :=>  " + SecurityContextHolder.getContext().getAuthentication().getName());
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}


	/**
	 * @return the testRunID
	 */
	public long getTestRunID() {
		return testRunID;
	}


	/**
	 * @param testRunID the testRunID to set
	 */
	public void setTestRunID(long testRunID) {
		this.testRunID = testRunID;
	}


	/**
	 * @return the testRunName
	 */
	public String getTestRunName() {
		return testRunName;
	}


	/**
	 * @param testRunName the testRunName to set
	 */
	public void setTestRunName(String testRunName) {
		this.testRunName = testRunName;
	}


	/**
	 * @return the cycleID
	 */
	public long getCycleID() {
		return cycleID;
	}


	/**
	 * @param cycleID the cycleID to set
	 */
	public void setCycleID(long cycleID) {
		this.cycleID = cycleID;
	}


	/**
	 * @return the testCaseID
	 */
	public long getTestCaseID() {
		return testCaseID;
	}


	/**
	 * @param testCaseID the testCaseID to set
	 */
	public void setTestCaseID(long testCaseID) {
		this.testCaseID = testCaseID;
	}	

	

}