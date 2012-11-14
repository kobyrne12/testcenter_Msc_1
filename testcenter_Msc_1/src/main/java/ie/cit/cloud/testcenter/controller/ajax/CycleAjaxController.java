/**
 * 
 */
package ie.cit.cloud.testcenter.controller.ajax;

/**
 * @author byrnek1
 *
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import ie.cit.cloud.testcenter.model.Cycle;
import ie.cit.cloud.testcenter.model.Project;
import ie.cit.cloud.testcenter.service.cycle.CycleService;
import ie.cit.cloud.testcenter.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Handles and retrieves the main requests
 */
@Controller
public class CycleAjaxController {
	@Autowired
	private CycleService cycleService;  

	/**
	 * Handles request for create a new Cycle
	 */
	@RequestMapping(value = "/newcycleAJAX", method = RequestMethod.POST)
	public @ResponseBody String addNewCycleAJAX(
			@RequestParam(value="projectID", required=true) long projectID,
			@RequestParam(value="cycleName", required=true) String cycleName,
			@RequestParam(value="cycleStartDate", required=true) String cycleStartDate,
			@RequestParam(value="cycleEndDate", required=true) String cycleEndDate,			
			Model model) 
	{
		System.out.println("************|||||||| ---: Received Ajax Request to create new Cycle");
		Cycle cycle = cycleService.getCycleByName(cycleName);	
		if (cycle.getProjectID() == projectID )
		{
			return cycleName + " already Exists";	 
		}
		else
		{
			try{    
				long projectPosition = cycleService.getMaxProjectPosNum(projectID);				
				cycleService.addNewCycle(new Cycle(cycleName,projectID,null,3,projectPosition,0,
						GetDateNow(),GetDateNow(),1,2,3,4,GetDateNow(),"KENNETH",GetDateNow(),"KENNETH"));
				return "ok";   
			}
			catch(ConstraintViolationException CVE)
			{   			
				System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations()); 				
				return CVE.getConstraintViolations().toString();
			}
		}
	}
	
	@RequestMapping(value = "/deletecycleAJAX", method = RequestMethod.POST )
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deleteCycle(@RequestParam(value="id", required=true) long cycleID) {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% long cycleID = " +cycleID);
		cycleService.remove(cycleID);
	}	 

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public void emptyResult() {
		// no code needed
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
}