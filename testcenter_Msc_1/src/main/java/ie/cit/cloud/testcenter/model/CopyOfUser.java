///**
// * 
// */
//package ie.cit.cloud.testcenter.model;
//
///**
// * @author byrnek1
// *
// */
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.Transient;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotEmpty;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.GrantedAuthorityImpl;
//import org.springframework.security.core.userdetails.UserDetails;
//
//
//@Entity(name = "User")
//public class CopyOfUser implements UserDetails {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id        
//	@GeneratedValue
//	@Column(name = "userID")
//	private Long userID;
//
//	@Length(min = 2, max = 32, message = "User name must be between 2 to 32 characters.")
//	// @Email(message="Please provide a valid email address")
//	@NotEmpty(message = "User Name is required.")
//	@Column(name = "username")
//	private String username;  	   
//
//	@Length(min = 2, max = 32, message = "password must be between 2 to 32 characters.")
//	@NotEmpty(message = "Password is required.")	
//	@Column(name = "password")
//	private String password; 
//
//	@Column(name = "enabled")
//	private Boolean enabled;
//	
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@Fetch(value = FetchMode.SUBSELECT)
//	@JoinTable(name = "USER_AUTHORITHY", joinColumns = { @JoinColumn(name = "userID")},
//	inverseJoinColumns={@JoinColumn(name="authorityID")})
//	private Set<Authority> authoritySet;
//	
//	// Getters & Setters for original props
//	// Please include getters and setters for the variables defined above.
//	// Spring Security props for internal checks of UserDetails
//	private transient Collection<GrantedAuthority> authorities;
//	////////////////////
//
//	//  @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	//	@JoinColumn(name="companyID",nullable = false)  
//	//	@Transient
//	//	private Company company;    
//	@Basic
//	@Column(name="companyID")
//	private Long companyID;
//
//	@ManyToMany(mappedBy="users", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//	@Fetch(value = FetchMode.SUBSELECT)
//	private Collection<Testrun> testruns = new ArrayList<Testrun>();		
//
//	@ManyToMany(mappedBy="users", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//	@Fetch(value = FetchMode.SUBSELECT)
//	private Collection<Requirement> requirements = new ArrayList<Requirement>();
//
//	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "USERS_JOIN_DEFECTS", joinColumns = { @JoinColumn(name = "userID") }, inverseJoinColumns = { @JoinColumn(name = "defectID") })
//	@Fetch(value = FetchMode.SUBSELECT)
//	private Collection<Defect> defects  = new ArrayList<Defect>();
//
//	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "USERS_JOIN_Environments", joinColumns = { @JoinColumn(name = "userID") }, inverseJoinColumns = { @JoinColumn(name = "environmentID") })
//	@Fetch(value = FetchMode.SUBSELECT)
//	private Collection<Environment> environments  = new ArrayList<Environment>();
//
//	public CopyOfUser() {	
//	}
//
//	@Transient
//	public Collection<GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
//
//	@Transient
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Transient
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Transient
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Transient
//	public boolean isEnabled() {
//		return getEnabled();
//	}
//
//	@Transient
//	public void setUserAuthorities(List<String> authorities) {
//		List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
//		for (String role : authorities) {
//			listOfAuthorities.add(new GrantedAuthorityImpl(role));
//		}
//		this.authorities = (Collection<GrantedAuthority>) listOfAuthorities;
//	}
//	/**
//	 * @param userName
//	 * @param password	
//	 * @param companyID	
//	 */
//	public CopyOfUser(String username, String password,Long companyID) {
//		this(username,password,true,companyID,null,null,null,null);
//	
//	}
//
//	/**
//	 * @param userName
//	 * @param password
//	 * @param enabled
//	 * @param companyID
//	 * @param testruns
//	 * @param requirements
//	 * @param defects
//	 * @param environments
//	 */
//	public CopyOfUser(String username, String password, Boolean enabled,
//			Long companyID, Collection<Testrun> testruns,
//			Collection<Requirement> requirements, Collection<Defect> defects,
//			Collection<Environment> environments) {
//		this.username = username;
//		this.password = password;
//		this.enabled = enabled;
//		this.companyID = companyID;
//		this.testruns = testruns;
//		this.requirements = requirements;
//		this.defects = defects;
//		this.environments = environments;
//	}
//
//	/**
//	 * @return the username
//	 */
//	public String getUsername() {
//		return username;
//	}
//
//	/**
//	 * @param username the username to set
//	 */
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	/**
//	 * @return the password
//	 */
//	public String getPassword() {
//		return password;
//	}
//
//	/**
//	 * @param password the password to set
//	 */
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	/**
//	 * @return the enabled
//	 */
//	public Boolean getEnabled() {
//		return enabled;
//	}
//
//	/**
//	 * @param enabled the enabled to set
//	 */
//	public void setEnabled(Boolean enabled) {
//		this.enabled = enabled;
//	}
//
//	/**
//	 * @return the authoritySet
//	 */
//	public Set<Authority> getAuthoritySet() {
//		return authoritySet;
//	}
//
//	/**
//	 * @param authoritySet the authoritySet to set
//	 */
//	public void setAuthoritySet(Set<Authority> authoritySet) {
//		this.authoritySet = authoritySet;
//	}
//
//	/**
//	 * @return the companyID
//	 */
//	public Long getCompanyID() {
//		return companyID;
//	}
//
//	/**
//	 * @param companyID the companyID to set
//	 */
//	public void setCompanyID(Long companyID) {
//		this.companyID = companyID;
//	}
//
//	/**
//	 * @return the testruns
//	 */
//	public Collection<Testrun> getTestruns() {
//		return testruns;
//	}
//
//	/**
//	 * @param testruns the testruns to set
//	 */
//	public void setTestruns(Collection<Testrun> testruns) {
//		this.testruns = testruns;
//	}
//
//	/**
//	 * @return the requirements
//	 */
//	public Collection<Requirement> getRequirements() {
//		return requirements;
//	}
//
//	/**
//	 * @param requirements the requirements to set
//	 */
//	public void setRequirements(Collection<Requirement> requirements) {
//		this.requirements = requirements;
//	}
//
//	/**
//	 * @return the defects
//	 */
//	public Collection<Defect> getDefects() {
//		return defects;
//	}
//
//	/**
//	 * @param defects the defects to set
//	 */
//	public void setDefects(Collection<Defect> defects) {
//		this.defects = defects;
//	}
//
//	/**
//	 * @return the environments
//	 */
//	public Collection<Environment> getEnvironments() {
//		return environments;
//	}
//
//	/**
//	 * @param environments the environments to set
//	 */
//	public void setEnvironments(Collection<Environment> environments) {
//		this.environments = environments;
//	}
//
//	/**
//	 * @return the userID
//	 */
//	public Long getUserID() {
//		return userID;
//	}
//
//	
//}