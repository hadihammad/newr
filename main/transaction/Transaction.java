package main.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import main.customer.Customer;
import main.wallet.Wallet;

@Entity
@Table(name = "Transaction")
public class Transaction {
	@Id
	@Column(name = "transaction_id")
	private String id;
	@Column(name = "currentDateNow")
	private String currentDateNow;
	@Column(name = "amountSar")
	private String amountSar;
	@Column(name = "amountTry")
	private String amountTry;
	@Column(name = "tryCredit")
	private String tryCredit;
	@Column(name = "sarCredit")
	private String sarCredit;
	@Column(name = "iban")
	private String iban;
	

	@ManyToOne
	@JoinColumn(name = "wallet_id")
	@JsonBackReference
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Wallet wallet;

	public Transaction() {
	}

	
	
//	public Transaction(String id, String currentDateNow, Wallet wallet) {
//		super();
//		this.id = id;
//		this.currentDateNow = currentDateNow;
//		this.wallet = wallet;
//	}



	public String getCurrentDateNow() {
		return currentDateNow;
	}

	public Transaction(String id, String currentDateNow, String amountSar, String amountTry, String tryCredit,
		String sarCredit, String iban, Wallet wallet) {
	super();
	this.id = id;
	this.currentDateNow = currentDateNow;
	this.amountSar = amountSar;
	this.amountTry = amountTry;
	this.tryCredit = tryCredit;
	this.sarCredit = sarCredit;
	this.iban = iban;
	this.wallet = wallet;
}



	public String getAmountSar() {
		return amountSar;
	}



	public void setAmountSar(String amountSar) {
		this.amountSar = amountSar;
	}



	public String getAmountTry() {
		return amountTry;
	}



	public void setAmountTry(String amountTry) {
		this.amountTry = amountTry;
	}



	public String getTryCredit() {
		return tryCredit;
	}



	public void setTryCredit(String tryCredit) {
		this.tryCredit = tryCredit;
	}



	public String getSarCredit() {
		return sarCredit;
	}



	public void setSarCredit(String sarCredit) {
		this.sarCredit = sarCredit;
	}



	public String getIban() {
		return iban;
	}



	public void setIban(String iban) {
		this.iban = iban;
	}



	public void setCurrentDateNow(String currentDateNow) {
		this.currentDateNow = currentDateNow;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}
