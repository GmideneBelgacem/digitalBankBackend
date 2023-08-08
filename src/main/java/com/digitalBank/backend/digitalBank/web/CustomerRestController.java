package com.digitalBank.backend.digitalBank.web;

import java.util.List;

import com.digitalBank.backend.digitalBank.dto.CustomerDTO;
import com.digitalBank.backend.digitalBank.exceptions.CustomerNotFoundException;
import com.digitalBank.backend.digitalBank.services.BankAccountService;
import com.digitalBank.backend.digitalBank.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CustomerRestController {
	@Autowired
	private CustomerService customerService;
	@GetMapping("/customers")
	public List<CustomerDTO> customers() {
		return customerService.listCustomers();
	}
	@GetMapping("/customers/{id}")
	public CustomerDTO getcustomer(@PathVariable (name="id")Long customerId) throws CustomerNotFoundException {
		return customerService.findCustomerByID(customerId);
	}
	@PostMapping("/customers")
	public CustomerDTO createcustomer(@RequestBody CustomerDTO customerRequest) throws CustomerNotFoundException {
		return customerService.createCustomer(customerRequest);
	}
	@PutMapping("/customers/{customerId}")
	public CustomerDTO updatecustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerRequest) throws CustomerNotFoundException {
		customerRequest.setId(customerId);
		return customerService.update(customerRequest);
	}
	@DeleteMapping("/customers/{id}")
	public void deletecustomer(@PathVariable (name="id")Long customerId) throws CustomerNotFoundException {
		customerService.delete(customerId);
	}
}
