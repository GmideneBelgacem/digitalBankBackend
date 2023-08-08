package com.digitalBank.backend.digitalBank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.digitalBank.backend.digitalBank.dto.CustomerDTO;
import com.digitalBank.backend.digitalBank.entities.Customer;
import com.digitalBank.backend.digitalBank.exceptions.CustomerNotFoundException;
import com.digitalBank.backend.digitalBank.mappers.CustomerMapperImpl;
import com.digitalBank.backend.digitalBank.repositories.CustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	private CustomerRepository customerRepository;
	private CustomerMapperImpl mapper;

	public CustomerServiceImpl(CustomerRepository customerRepository,CustomerMapperImpl mapper) {
		super();
		this.mapper=mapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		return savedCustomer;
	}

	@Override
	public List<CustomerDTO> listCustomers() {
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		customerDTOs = customerRepository.findAll().stream().map(cu -> mapper.fromCustomer(cu))
				.collect(Collectors.toList());
		return customerDTOs;
	}
	@Override
	public CustomerDTO findCustomerByID(Long customerId) throws CustomerNotFoundException {

		return mapper.fromCustomer(customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("customer not found!")));
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer = mapper.fromCustomerDTO(customerDTO);
		customer=customerRepository.save(customer);
		return (mapper.fromCustomer(customer));
	}

	@Override
	public CustomerDTO update(CustomerDTO customerRequest) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerRequest.getId()).orElseThrow(
				()->new CustomerNotFoundException("customer not found!"));
		customer = mapper.fromCustomerDTO(customerRequest);
		customer=customerRepository.save(customer);
		return (mapper.fromCustomer(customer));
	}

	@Override
	public void delete(Long customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId).orElseThrow(
				()->new CustomerNotFoundException("customer not found!"));
		customerRepository.delete(customer);
		
	}


}
