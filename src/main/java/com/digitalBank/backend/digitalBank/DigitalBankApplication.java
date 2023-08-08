package com.digitalBank.backend.digitalBank;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import com.digitalBank.backend.digitalBank.dto.BankAccountDTO;
import com.digitalBank.backend.digitalBank.dto.CurrentBankAccountDTO;
import com.digitalBank.backend.digitalBank.dto.CustomerDTO;
import com.digitalBank.backend.digitalBank.dto.SavingBankAccountDTO;
import com.digitalBank.backend.digitalBank.entities.BankAccount;
import com.digitalBank.backend.digitalBank.entities.CurrentAccount;
import com.digitalBank.backend.digitalBank.entities.Customer;
import com.digitalBank.backend.digitalBank.entities.SavingAccount;
import com.digitalBank.backend.digitalBank.enums.AccountStatus;
import com.digitalBank.backend.digitalBank.exceptions.BalanceNotSufficientException;
import com.digitalBank.backend.digitalBank.exceptions.BankAccountNotFoundException;
import com.digitalBank.backend.digitalBank.exceptions.CustomerNotFoundException;
import com.digitalBank.backend.digitalBank.mappers.BankAccountMapperImpl;
import com.digitalBank.backend.digitalBank.mappers.CustomerMapperImpl;
import com.digitalBank.backend.digitalBank.repositories.AccountOperationRepository;
import com.digitalBank.backend.digitalBank.repositories.BankAccountRepository;
import com.digitalBank.backend.digitalBank.repositories.CustomerRepository;
import com.digitalBank.backend.digitalBank.services.BankAccountService;
import com.digitalBank.backend.digitalBank.services.CustomerService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitalBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
			AccountOperationRepository accountOperationRepository, BankAccountService service,BankAccountMapperImpl mapper,CustomerService customerService,CustomerMapperImpl customerMapper) {
		return args -> {
			Stream.of("Mathieu", "Rafael","Jean-pierre","pierre").forEach(name -> {
				Customer customer = new Customer();
				customer.setNom(name);
				customer.setEmail(name + "@gmail.com");
				customerService.createCustomer(customerMapper.fromCustomer(customer));

			});
			List<CustomerDTO> listCustomers = customerService.listCustomers();
			listCustomers.forEach(cust -> {
				try {
					CurrentBankAccountDTO newCurrent= new CurrentBankAccountDTO();
					newCurrent.setBalance(Math.random() * 900);
					newCurrent.setCreationDate(new Date());
					newCurrent.setOverDraft(Math.random()*875);
					newCurrent.setCustomerDTO(cust);
					newCurrent.setId(UUID.randomUUID().toString());
					newCurrent.setStatus(AccountStatus.ACTIVATED);
					SavingBankAccountDTO newSaving = new SavingBankAccountDTO();
					newSaving.setCreationDate(new Date());
					newSaving.setBalance(Math.random() * 65);
					newSaving.setInterestRate( (Math.random() * 75));
					newSaving.setCustomer(cust);
					newSaving.setId(UUID.randomUUID().toString());
					newSaving.setStatus(AccountStatus.CRAETED);
					service.saveCurrentBankAccount(newCurrent);
					service.saveSavingsBankAccount(newSaving);
					List<BankAccountDTO> bankAccounts = service.bankAccountList();
					for (BankAccountDTO bankAccount : bankAccounts) {
						for (int i = 0; i < 10; i++) {
							String accountId;
							if(bankAccount instanceof SavingBankAccountDTO){
								accountId=((SavingBankAccountDTO) bankAccount).getId();
							}
							else accountId=((CurrentBankAccountDTO)bankAccount).getId();
							service.credit(accountId, 9000 + Math.random() * 10000000, "credit");
							service.debit(accountId, 9000 + Math.random() * 1200, "debit");

						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} 

			});
		};
	}
//	@Bean
//	CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
//			AccountOperationRepository accountOperationRepository) {
//		return args -> {
//			Stream.of("awatef", "Tawfik", "Mohamed", "Raja", "Najia", "Bechir").forEach(name -> {
//				Customer customer = new Customer();
//				customer.setNom(name);
//				customer.setEmail(name + "@gmail.com");
//				customerRepository.save(customer);
//			});
//			customerRepository.findAll().forEach(cust -> {
//				CurrentAccount currentAccount = new CurrentAccount();
//				currentAccount.setId(UUID.randomUUID().toString());
//				currentAccount.setBalance(Math.random() * 9000);
//				currentAccount.setCreationDate(new Date());
//				currentAccount.setCustomer(cust);
//				currentAccount.setOverDraft(900);
//				currentAccount.setStatus(AccountStatus.ACTIVATED);
//				bankAccountRepository.save(currentAccount);
//				SavingAccount savingAccount = new SavingAccount();
//				savingAccount.setId(UUID.randomUUID().toString());
//				savingAccount.setBalance(Math.random() * 9800);
//				savingAccount.setCreationDate(new Date());
//				savingAccount.setCustomer(cust);
//				savingAccount.setInterestRate(8222);
//				savingAccount.setStatus(AccountStatus.ACTIVATED);
//				bankAccountRepository.save(savingAccount);
//			});
//				bankAccountRepository.findAll().forEach(bank ->{
//					for(int i=0;i<5;i++) {
//						AccountOperation operation= new AccountOperation();
//						operation.setAmount(Math.random()*500);
//						operation.setBankAccount(bank);
//						operation.setOperationDate(new Date());
//						operation.setOperationType(Math.random()>0.5?OperationType.CREDIT:OperationType.VERSEMENT);
//						accountOperationRepository.save(operation);
//					}
//				});
//		};
//	}

}
