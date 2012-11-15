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
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name = "Project")
public class Project {

	@Id        
	@GeneratedValue
	@Column(name = "projectID", unique = true, nullable = false)
    private long projectID;
	
	@Length(min = 2, max = 32, message = "Project name must be between 2 to 32 characters.")
	@NotEmpty(message = "Project Name is required.")
	//@Column(name = "projectName", unique = true, nullable = false)
	private String projectName;  	
   
//	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinColumn(name="companyID",nullable = false)  
//	@Transient
//	private Company company;
//	
//	@Basic
//	@Column(name="companyID")
//	private long companyID;
	
	
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "companyID", nullable = false)
	//@Transient
	
//    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinColumn(name="companyID",nullable = false)  
//    @Transient
//	private Company company;
	
	@Basic
	@Column(name="companyID")
	private Long companyID;	
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Cycle.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "projectID", referencedColumnName="projectID")
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Cycle> cycles;   
	
    @Basic    
    private long parentID;    
    @Basic    
    private int regressionRequiredPercent;
    @Basic    
    private int newFeatureRequiredPercent;
    @Basic    
    private int allowedSev1;
    @Basic    
    private int allowedSev2;
    @Basic    
    private int allowedSev3;
    @Basic    
    private int allowedSev4;
    @Basic    
    private String creationDate = GetDateNow();
    @Basic    
    private String createdBy = "Ken";
//    @Temporal(TemporalType.DATE)
//	@Column(name = "DATE", unique = true, nullable = false, length = 10)
    @Basic    
    private String lastModifiedDate;
    @Basic    
    private String lastModifiedBy;
    
    
//  @Basic
//  private String user; 
    
// @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")
    
    public Project() {	
    }
    
    public Project(long companyID, String projectName,long parentID,int regressionRequiredPercent,int newFeatureRequiredPercent, int allowedSev1,int allowedSev2,int allowedSev3,int allowedSev4,String lastModifiedDate,String lastModifiedBy ) {
    	this(companyID,projectName,null,parentID,regressionRequiredPercent,newFeatureRequiredPercent,allowedSev1,allowedSev2,allowedSev3,allowedSev4, lastModifiedDate, lastModifiedBy);
    }

    public Project(long companyID, String projectName,Collection<Cycle> cycles, long parentID ,int regressionRequiredPercent,int newFeatureRequiredPercent, int allowedSev1,int allowedSev2,int allowedSev3,int allowedSev4,String lastModifiedDate,String lastModifiedBy) {
    	//this.company = company;
    	this.projectName = projectName;
    	this.companyID = companyID;    	
    	this.cycles = cycles;
    	this.parentID = parentID;		
		this.regressionRequiredPercent = regressionRequiredPercent;
		this.newFeatureRequiredPercent = newFeatureRequiredPercent;
		this.allowedSev1 = allowedSev1;
		this.allowedSev2 = allowedSev2;
		this.allowedSev3 = allowedSev3;
		this.allowedSev4 = allowedSev4;	
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;

    }
	// project ID 
    public long getProjectID() {
    	return projectID;
    }
    // project Name
    public String getProjectName()
    {
    	return projectName;
    }	
    public void setProjectName(String projectName) 
    {
    	this.projectName = projectName;
    }
    // Parent Project ID 
    public long getParentID() {
		return parentID;
	}
	public void setParentID(long parentID) {
		this.parentID = parentID;
	}	
//	// creation Date
//    public String getCreationDate() {
//    	return creationDate;
//    }
//    // created By 
//    public String getCreatedBy() {
//    	return createdBy;
//    }
    // Regression Required Percent 
    public int getRegressionRequiredPercent() {
		return regressionRequiredPercent;
	}
	public void setRegressionRequiredPercent(int regressionRequiredPercent) {
		this.regressionRequiredPercent = regressionRequiredPercent;
	}
	

	/**
	 * @return the newFeatureRequiredPercent
	 */
	public int getNewFeatureRequiredPercent() {
		return newFeatureRequiredPercent;
	}

	/**
	 * @param newFeatureRequiredPercent the newFeatureRequiredPercent to set
	 */
	public void setNewFeatureRequiredPercent(int newFeatureRequiredPercent) {
		this.newFeatureRequiredPercent = newFeatureRequiredPercent;
	}

	/**
	 * @return the allowedSev1
	 */
	public int getAllowedSev1() {
		return allowedSev1;
	}

	/**
	 * @param allowedSev1 the allowedSev1 to set
	 */
	public void setAllowedSev1(int allowedSev1) {
		this.allowedSev1 = allowedSev1;
	}

	/**
	 * @return the allowedSev2
	 */
	public int getAllowedSev2() {
		return allowedSev2;
	}

	/**
	 * @param allowedSev2 the allowedSev2 to set
	 */
	public void setAllowedSev2(int allowedSev2) {
		this.allowedSev2 = allowedSev2;
	}

	/**
	 * @return the allowedSev3
	 */
	public int getAllowedSev3() {
		return allowedSev3;
	}

	/**
	 * @param allowedSev3 the allowedSev3 to set
	 */
	public void setAllowedSev3(int allowedSev3) {
		this.allowedSev3 = allowedSev3;
	}

	/**
	 * @return the allowedSev4
	 */
	public int getAllowedSev4() {
		return allowedSev4;
	}

	/**
	 * @param allowedSev4 the allowedSev4 to set
	 */
	public void setAllowedSev4(int allowedSev4) {
		this.allowedSev4 = allowedSev4;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		//this.lastModifiedDate = GetDateNow();
		this.lastModifiedDate = lastModifiedDate;
	}	
	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the companyID
	 */
	public Long getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
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
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}	

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the cycles
	 */
	public Collection<Cycle> getCycles() {
		return cycles;
	}

	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(Collection<Cycle> cycles) {
		this.cycles = cycles;
	}

	

}