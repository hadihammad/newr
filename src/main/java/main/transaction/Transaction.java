package main.transaction;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import main.wallet.Wallet;

@Entity
@Table(name = "Transaction")
public class Transaction {
	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "currentDateNow")
	private String currentDateNow;
	@Column(name = "fromAccount")
	private String fromAccount;
	@Column(name = "toAccount")
	private String toAccount;
	@Column(name = "currentPerCurrency")
	private String currentPerCurrency;
	@Column(name = "amountPerCurrency")
	private BigDecimal amountPerCurrency;
	@Column(name = "userPhoneNumber")
	private String userPhoneNumber;

	@ManyToOne
	@JoinColumn(name = "wallet_id")
	@JsonBackReference
	private Wallet wallet;

	public Transaction() {
	}

	public Transaction(long id, String currentDateNow, String fromAccount, String toAccount, String currentPerCurrency,
			BigDecimal amountPerCurrency, String userPhoneNumber, Wallet wallet) {
		super();
		this.id = id;
		this.currentDateNow = currentDateNow;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.currentPerCurrency = currentPerCurrency;
		this.amountPerCurrency = amountPerCurrency;
		this.userPhoneNumber = userPhoneNumber;
		this.wallet = wallet;
	}

	public BigDecimal getAmountPerCurrency() {
		return amountPerCurrency;
	}

	public void setAmountPerCurrency(BigDecimal amount) {
		this.amountPerCurrency = amount;
	}

	public String getCurrentPerCurrency() {
		return currentPerCurrency;
	}

	public void setCurrentPerCurrency(String currentPerCurrency) {
		this.currentPerCurrency = currentPerCurrency;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCurrentDateNow() {
		return currentDateNow;
	}

	public void setCurrentDateNow(String currentDateNow) {
		this.currentDateNow = currentDateNow;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}
