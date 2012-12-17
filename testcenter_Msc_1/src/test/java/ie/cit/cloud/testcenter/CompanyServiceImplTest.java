package ie.cit.cloud.testcenter;

import ie.cit.cloud.testcenter.respository.company.CompanyRepository;
import ie.cit.cloud.testcenter.service.company.CompanyServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
public class CompanyServiceImplTest
{
	private CompanyServiceImpl tested;
    private CompanyRepository repo;  
    
    @Before
    public void setup() {
	repo = Mockito.mock(CompanyRepository.class);
	tested = new CompanyServiceImpl();
	tested.repo = repo;	
    }

    @Test
    public void testFindAllTestCases() {    	
		tested.getAllCompanies();
		Mockito.verify(repo).findAll();
    }

}

