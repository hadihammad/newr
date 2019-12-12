package main.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import main.wallet.Wallet;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private long id;
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;
	@Column(name = "password", nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@Column(name = "nationalId", nullable = false)
	private String nationalId;
	@Column(name = "firstName", nullable = false)
	private String firstName;
	@Column(name = "lastName", nullable = false)
	private String lastName;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Wallet> walletsAccount = new HashSet<Wallet>();

	public Customer() {

	}

	public Customer(long id, String phoneNumber, String password, String nationalId, String firstName, String lastName,
			Set<Wallet> walletsAccount) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.nationalId = nationalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.walletsAccount = walletsAccount;
	}

	public Set<Wallet> getWalletsAccount() {
		return walletsAccount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setWalletsAccount(Set<Wallet> walletsAccount) {
		this.walletsAccount = walletsAccount;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
