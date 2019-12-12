package main.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer createParent(Customer parent) {
		return customerRepository.save(parent);
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		return customers;

	}

	public Customer getCustomer(long customerId) {
		return customerRepository.findOne(customerId);
	}

	public void addCustomers(Customer customer) {
		customerRepository.save(customer);
	}

	public void updateCustomers(long id, Customer customer) {
		customer.setId(customerRepository.findById(id).get().getId());
		customerRepository.save(customer);

	}

	public void deleteCustomers(long customerId) {
		customerRepository.delete(customerId);
	}
}
