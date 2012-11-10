/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */


import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "CycleTrend")
public class CycleTrend {

	@Id        
	@GeneratedValue
	@Column(name = "cycleTrendID")
	private Long cycleTrendID;

	@Basic    
	private long numOfDefects;

	@Basic    
	private long numOfSev1Defects;

	@Basic    
	private long numOfSev2Defects;

	@Basic    
	private long numOfSev3Defects;

	@Basic    
	private long numOfSev4Defects;

	@Basic
	@Column(name="projectID") /// or maybe cycleID
	private Long projectID; /// or maybe cycleID


	// @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")

	public CycleTrend() {	
	}

	//    public Requirement(Company company,long companyID,String requirementName,long parentID,String lastModifiedDate,String lastModifiedBy ) {
	//    	this(company,companyID,requirementName,parentID,96,94,10,25,50,100, lastModifiedDate, lastModifiedBy);
	//    }

	public CycleTrend(long projectID,long cycleTrendID, long numOfDefects,long numOfSev1Defects, long numOfSev2Defects, long numOfSev3Defects, long numOfSev4Defects)
	{
		this.projectID = projectID;
		this.cycleTrendID = cycleTrendID;
		this.numOfDefects = numOfDefects;
		this.numOfSev1Defects = numOfSev1Defects;
		this.numOfSev2Defects = numOfSev2Defects;
		this.numOfSev3Defects = numOfSev3Defects;
		this.numOfSev4Defects = numOfSev4Defects; 
	}

	// requirement ID 
	public long getCycleTrendID() {
		return cycleTrendID;
	}

	/**
	 * @return the numOfDefects
	 */
	public long getNumOfDefects() {
		return numOfDefects;
	}

	/**
	 * @param numOfDefects the numOfDefects to set
	 */
	public void setNumOfDefects(long numOfDefects) {
		this.numOfDefects = numOfDefects;
	}

	/**
	 * @return the numOfSev1Defects
	 */
	public long getNumOfSev1Defects() {
		return numOfSev1Defects;
	}

	/**
	 * @param numOfSev1Defects the numOfSev1Defects to set
	 */
	public void setNumOfSev1Defects(long numOfSev1Defects) {
		this.numOfSev1Defects = numOfSev1Defects;
	}

	/**
	 * @return the numOfSev2Defects
	 */
	public long getNumOfSev2Defects() {
		return numOfSev2Defects;
	}

	/**
	 * @param numOfSev2Defects the numOfSev2Defects to set
	 */
	public void setNumOfSev2Defects(long numOfSev2Defects) {
		this.numOfSev2Defects = numOfSev2Defects;
	}

	/**
	 * @return the numOfSev3Defects
	 */
	public long getNumOfSev3Defects() {
		return numOfSev3Defects;
	}

	/**
	 * @param numOfSev3Defects the numOfSev3Defects to set
	 */
	public void setNumOfSev3Defects(long numOfSev3Defects) {
		this.numOfSev3Defects = numOfSev3Defects;
	}

	/**
	 * @return the numOfSev4Defects
	 */
	public long getNumOfSev4Defects() {
		return numOfSev4Defects;
	}

	/**
	 * @param numOfSev4Defects the numOfSev4Defects to set
	 */
	public void setNumOfSev4Defects(long numOfSev4Defects) {
		this.numOfSev4Defects = numOfSev4Defects;
	}

	/**
	 * @return the projectID
	 */
	public Long getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	}