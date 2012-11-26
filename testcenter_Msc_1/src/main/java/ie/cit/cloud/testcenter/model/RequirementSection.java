/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.Set;

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

@Entity(name = "RequirementSection")
public class RequirementSection {

	@Id        
	@GeneratedValue
	@Column(name = "requirementSectionID")
	private Long requirementSectionID;	

	@Length(min = 2, max = 64, message = "Requirement Section name must be between 2 to 64 characters.")
	@NotEmpty(message = "Requirement Section Name is required.")
	private String requirementSectionName;  	
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Requirement.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "requirementSectionID", referencedColumnName="requirementSectionID")		
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Requirement> requirements;    
	
	public RequirementSection() {		
	}

	/**
	 * @param requirementSectionName
	 * @param requirements
	 */
	public RequirementSection(String requirementSectionName, Set<Requirement> requirements) {
		this.requirementSectionName = requirementSectionName;
		this.requirements = requirements;
	}

	/**
	 * @return the requirementSectionName
	 */
	public String getRequirementSectionName() {
		return requirementSectionName;
	}

	/**
	 * @param requirementSectionName the requirementSectionName to set
	 */
	public void setRequirementSectionName(String requirementSectionName) {
		this.requirementSectionName = requirementSectionName;
	}

	/**
	 * @return the requirements
	 */
	public Set<Requirement> getRequirements() {
		return requirements;
	}

	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}

	/**
	 * @return the requirementSectionID
	 */
	public Long getRequirementSectionID() {
		return requirementSectionID;
	}

	
}