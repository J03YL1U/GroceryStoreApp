package mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.AccountDto;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.service.AccountService;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class PersonRestController {

	@Autowired
	private PersonService personService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private USerRoleService userRoleService;
	
	@PostMapping(value = { "/person/create/{email}", "/person/create/{email}/" })
	public PersonDto createPerson(@PathVariable("email") String email, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String phoneNumber, @RequestParam String address, @RequestParam UserRoleDto uDto,
			@RequestParam AccountDto aDto) throws IllegalArgumentException {
		UserRole u = userRoleService.getUserRole(uDto.getId());
		Account a = accountService.getAccount(aDto.getUsername());
		
		Person person = personService.createPerson(email, firstName, lastName, phoneNumber, address, u, a);
		return convertToDto(person);
	}
	
	@PutMapping(value = { "/person/update/{currentEmail}", "/person/update/{currentUsername}/" })
	public PersonDto updatePerson(@PathVariable("email") String currentEmail, @RequestParam String newEmail,
			@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String phoneNumber, @RequestParam String address, @RequestParam UserRoleDto uDto,
			@RequestParam AccountDto aDto) throws IllegalArgumentException {
		UserRole u = userRoleService.getUserRole(uDto.getId());
		Account a = accountService.getAccount(aDto.getUsername());
		
		Person person = personService.updatePerson(currentEmail, newEmail, firstName, lastName, phoneNumber, address, u, a);
		return convertToDto(person);
	}
	
	//UTILITIES
	private PersonDto convertToDto(Person p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(p.getEmail(),p.getFirstName(),p.getLastName(),p.getPhoneNumber(),
				p.getAddress(),convertToDto(p.getUserRole()),convertToDto(p.getAccount()));
		return personDto;
	}
	
	private UserRoleDto convertToDto(UserRole r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such UserRole!");
		}
		UserRoleDto userRoleDto = new UserRoleDto();
		return userRoleDto;
	}
	
	private AccountDto convertToDto(Account a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Account!");
		}
		AccountDto accountDto = new AccountDto(a.getUsername(),a.getPassword(),a.getInTown(),a.getTotalPoints(),
				convertToDto(a.getPerson()),convertToDto(a.getCart()));
		return accountDto;
	}
	
	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto();
		return cartDto;
	}
}