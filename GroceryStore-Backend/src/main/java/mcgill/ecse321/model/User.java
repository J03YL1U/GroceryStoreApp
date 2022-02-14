package mcgill.ecse321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User{

	private int phoneNumber;
	public void setPhoneNumber(int value) {
		this.phoneNumber = value;
	}
	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	private String address;
	public void setAddress(String value) {
		this.address = value;
	}
	public String getAddress() {
		return this.address;
	}

	private String firstName;
	public void setFirstName(String value) {
		this.firstName = value;
	}
	public String getFirstName() {
		return this.firstName;
	}

	private String lastName;
	public void setLastName(String value) {
		this.lastName = value;
	}
	public String getLastName() {
		return this.lastName;
	}

	@Id
	private String email;
	public void setEmail(String value) {
		this.email = value;
	}
	public String getEmail() {
		return this.email;
	}

	// !!! userRole is an abstract class. How do we reference it properly
	private UserRole userRole;
	@OneToOne(optional=false)
	public UserRole getUserRole() {
		return this.userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	private Account account;
	@OneToOne(optional=false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
