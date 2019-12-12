package main.wallet;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wallet_id")
	private long id; // It wall take the CustomerId value during creation.
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;
	@Min(value = 0)
	@Column(name = "SAR", nullable = false)
	private BigDecimal SAR;
	@Min(value = 0)
	@Column(name = "TRY", nullable = false)
	private BigDecimal TRY;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Transaction> transactions = new HashSet<Transaction>();

	public Wallet() {

	}

//	public Wallet(String id, String phoneNumber, BigDecimal sAR, BigDecimal tRY, Customer customer,
//			Set<Transaction> transactions) {
//		super();
//		this.id = id;
//		this.phoneNumber = phoneNumber;
//		SAR = sAR;
//		TRY = tRY;
//		this.customer = customer;
//		this.transactions = transactions;
//	}

	public Wallet(long id, String phoneNumber, BigDecimal sAR, BigDecimal tRY, Customer customer,
			Set<Transaction> transactions) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		SAR = sAR;
		TRY = tRY;
		this.customer = customer;
		this.transactions = transactions;
	}

	public long getId() {
		return id;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSAR(BigDecimal sAR) {
		SAR = sAR;
	}

	public void setTRY(BigDecimal tRY) {
		TRY = tRY;
	}

	public BigDecimal getSAR() {
		return SAR;
	}

	public BigDecimal getTRY() {
		return TRY;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
