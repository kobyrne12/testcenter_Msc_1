/**
 * 
 */
package ie.cit.cloud.testcenter.service.defect;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.Defect;
import ie.cit.cloud.testcenter.respository.defect.DefectRepository;
import ie.cit.cloud.testcenter.service.company.CompanyService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import javax.persistence.NoResultException;
import javax.persistence.Transient;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefectServiceImpl implements DefectService {

	@Autowired
	@Qualifier("hibernateDefectRespository")
	DefectRepository defectRepo;      

	@Autowired
	CompanyService companyService;

	@Autowired
	ProjectService projectService;	

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Defect getDefect(long defectID) {
		return defectRepo.findById(defectID);
	}

	@Transactional(rollbackFor=NoResultException.class,readOnly=true)
	public Defect getDefectByName(String defectName) {
		return defectRepo.findByName(defectName);
	}

	// @Secured("ROLE_ADMIN")
	@Transactional(rollbackFor=ConstraintViolationException.class)   
	public void addNewDefect(Defect defect) {
		defectRepo.create(defect);	
	}

	// @Secured("ROLE_ADMIN")
	public void update(Defect defect) {
		defectRepo.update(defect);
	}  

	//  @Secured("ROLE_ADMIN")
	public void remove(long defectID) {
		defectRepo.delete(getDefect(defectID));
	}

	public boolean isSev1(long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity() == 1)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev2(long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity()  == 2)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev3(long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity()  == 3)
		{
			return true;
		}
		return false;				
	}

	public boolean isSev4(long defectID)
	{
		Defect defect = getDefect(defectID);
		if(defect.getSeverity() == 4)
		{
			return true;
		}
		return false;				
	}

}