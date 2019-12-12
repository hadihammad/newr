package main.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer/allCustomers")
	public List<Customer> getAllCustomer() {

		return customerService.getAllCustomers();

	}

	@GetMapping("/customerId={customerId}")
	public Customer getCustomer(@PathVariable long customerId) {

		return customerService.getCustomer(customerId);

	}

	@PostMapping(value = "/customer/addCustomer")
	public void addCustomer(@RequestBody Customer customer) {

		customerService.addCustomers(customer);

	}

	@PatchMapping(value = "/customer/updateCustomerId={customerId}")
	public void updateCustomer(@RequestBody Customer customer, @PathVariable long customerId) {

		customerService.updateCustomers(customerId, customer);

	}

	@DeleteMapping(value = "/customer/deleteCustomerId={customerId}")
	public void deleteCustomer(@PathVariable long customerId) {

		customerService.deleteCustomers(customerId);

	}

}
