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

@Entity(name = "DefectSection")
public class DefectSection {

	@Id        
	@GeneratedValue
	@Column(name = "defectSectionID")
	private Long defectSectionID;	

	@Length(min = 2, max = 64, message = "Defect Section name must be between 2 to 64 characters.")
	@NotEmpty(message = "Defect Section Name is required.")
	private String defectSectionName;  	
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity=Defect.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "defectSectionID", referencedColumnName="defectSectionID")		
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Defect> defects;    
	
	public DefectSection() {		
	}

	/**
	 * @param defectSectionName
	 * @param defects
	 */
	public DefectSection(String defectSectionName, Set<Defect> defects) {
		this.defectSectionName = defectSectionName;
		this.defects = defects;
	}

	/**
	 * @return the defectSectionName
	 */
	public String getDefectSectionName() {
		return defectSectionName;
	}

	/**
	 * @param defectSectionName the defectSectionName to set
	 */
	public void setDefectSectionName(String defectSectionName) {
		this.defectSectionName = defectSectionName;
	}

	/**
	 * @return the defects
	 */
	public Set<Defect> getDefects() {
		return defects;
	}

	/**
	 * @param defects the defects to set
	 */
	public void setDefects(Set<Defect> defects) {
		this.defects = defects;
	}

	/**
	 * @return the defectSectionID
	 */
	public Long getDefectSectionID() {
		return defectSectionID;
	}

	
}