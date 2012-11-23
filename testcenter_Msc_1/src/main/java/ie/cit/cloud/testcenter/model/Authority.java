/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity(name = "Authority")
public class Authority {

	@Id        
	@GeneratedValue
	@Column(name = "authorityID")
    private Long authorityID;
	
	@Column(name="authority")
	private String authority;
	
	Authority (){
	}

	
	/**
	 * @param authority
	 */
	public Authority(String authority) {
		this.authority = authority;
	}


	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * @return the authorityID
	 */
	public Long getAuthorityID() {
		return authorityID;
	}
	
	
}