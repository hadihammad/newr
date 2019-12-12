package main.wallet;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import main.customer.Customer;
//import main.transaction.Transaction;
import main.transaction.Transaction;

@Entity
@Table(name = "Wallet")
public class Wallet {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wallet_id")
//	@Max(value = 1)
	@Min(value = 1)
	@GeneratedValue
	private String id;
	private String phoneNumber;
	private String SAR;
	private String TRY;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
	private Set<Transaction> transactions = new HashSet<Transaction>();
//	
	public Wallet() {

	}



	public Wallet(String id, String phoneNumber, String sAR, String tRY, Customer customer
			,
			Set<Transaction> transactions
			) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		SAR = sAR;
		TRY = tRY;
		this.customer = customer;
		this.transactions = transactions;
	}



	public Set<Transaction> getTransactions() {
		return transactions;
	}



	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSAR() {
		return SAR;
	}

	public void setSAR(String SAR) {
		this.SAR = SAR;
	}

	public String getTRY() {
		return TRY;
	}

	public void setTRY(String TRY) {
		this.TRY = TRY;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
