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
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "User")
public class User {

	@Id        
	@GeneratedValue
	@Column(name = "userID")
    private Long userID;
	
	@Length(min = 2, max = 32, message = "User name must be between 2 to 32 characters.")
	@NotEmpty(message = "User Name is required.")
	private String userName;  	   
	
	@Length(min = 2, max = 32, message = "password must be between 2 to 32 characters.")
	@NotEmpty(message = "Password is required.")	
	private String password;   		
	
    @Email(message="Please provide a valid email address")
    @NotEmpty(message = "Email is required.")
    private String  email; 
    
    @Basic    
    private String userType;
    
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="companyID",nullable = false)  
	@Transient
	private Company company;
	@Basic
	@Column(name="companyID")
	private Long companyID;
   
    
// @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")
    
    public User() {	
    }
    
//    public User(Company company,long companyID,String userName,long parentID,String lastModifiedDate,String lastModifiedBy ) {
//    	this(company,companyID,userName,parentID,96,94,10,25,50,100, lastModifiedDate, lastModifiedBy);
//    }

    public User(Company company,long companyID,long userID, String userName,String email, String userType)
    {
    	this.company = company;
    	this.companyID = companyID;
    	this.userID = userID;
    	this.userName = userName;
    	this.email = email;
    	this.userType = userType;    
    }
    
    // Get company   
	public Company getCompany() {
		return this.company;
	} 
	public void setCompany(Company company) {
		this.company = company;
	}
	// Get companyID
	public long getTestPlanID() {
		return this.companyID;
	} 
	public void setTestPlanID(long companyID) {
		this.companyID = companyID;
	}

	// user ID 
    public long getUserID() {
    	return userID;
    }
    // user Name
    public String getUserName()
    {
    	return userName;
    }	
    public void setUserName(String userName) 
    {
    	this.userName = userName;
    }

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setUserAuthorities(List<String> authorities) {
		// TODO Auto-generated method stub
		
	}
   
	

}