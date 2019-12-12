package mockito;
 
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import main.customer.Customer;
import main.customer.CustomerRepository;
import main.customer.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockitoExample {
	@InjectMocks
	CustomerService service;
	@MockBean
	CustomerRepository repository;
	static long id=1;
	
	@Test
	public void testGetCustomer() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setPhoneNumber("1");
		customer.setPassword("1");
		customer.setNationalId("1");
		customer.setFirstName("1");
		customer.setLastName("1");
		
//		when(repository.findById("1") ).thenReturn(customer);
	}
	@Test
	public void getCustomerTest() {
		service.getCustomer(id);
		verify(repository).findOne(id);
			
	}

	
}