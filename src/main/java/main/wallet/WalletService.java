package main.wallet;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.customer.Customer;
import main.customer.CustomerRepository;
import main.transaction.Transaction;
import main.transaction.TransactionRepository;

@Service
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Wallet> getAllWallets() {
		List<Wallet> wallets = new ArrayList<>();
		walletRepository.findAll().forEach(wallets::add);
		return wallets;

	}

	public Wallet getWallet(long id) {
		return walletRepository.findOne(id);
	}

	public Wallet getWalletForNested(long customerId, long walletId) {
		return walletRepository.findByCustomerIdAndId(customerId, walletId);

	}

	public BigDecimal getWalletByIdSar(long walletid) {

		return walletRepository.findById(walletid).get().getSAR();
	}

	public BigDecimal getWalletByIdTry(long walletid) {

		return walletRepository.findById(walletid).get().getTRY();
	}

	public Optional<Wallet> getWalletById(long walletid) {

		return walletRepository.findById(walletid);
	}

	public Wallet createWalletUnderCustomer(long walletid, Wallet wallet) {
		if (

		walletRepository.findById(walletid).isPresent()) {
			throw new IllegalArgumentException("this Custmer already have a wallet accounts");

		} else {
			Set<Wallet> walletsAccount = new HashSet<>();
			Customer customer = new Customer();

			Optional<Customer> byId = customerRepository.findById(walletid);
			Customer customer1 = byId.get();
			wallet.setPhoneNumber(customerRepository.findById(walletid).get().getPhoneNumber());
			wallet.setId(customerRepository.findById(walletid).get().getId());
			// tie Wallet to Transaction (Parent to Child)
			wallet.setCustomer(customer1);
			Wallet wallet1 = walletRepository.save(wallet);

			// tie Transaction to Wallet (Child to Parent)
			walletsAccount.add(wallet1);
			customer.setWalletsAccount(walletsAccount);

			// TransactionBlock TransationList while creating the wallet for first time
			Date date = new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			String timestamp = ts.toString();

			Wallet obj = new Wallet();
			Wallet obj2 = new Wallet();
			Optional<Wallet> byId2 = walletRepository.findById(walletid);

			obj = byId2.get();
			Transaction transaction = new Transaction();
			transaction.setWallet(obj);

			String phoneNumber = walletRepository.findById(walletid).get().getPhoneNumber();

			transaction.setUserPhoneNumber(phoneNumber);
			transaction.setCurrentDateNow(timestamp);
			transaction.setFromAccount("Intial wallet Account: " + phoneNumber);
			transaction.setToAccount("Intial wallet Account: " + phoneNumber);
			transaction.setCurrentPerCurrency("SAR = " + walletRepository.findById(walletid).get().getSAR() + ", "
					+ "TRY = " + walletRepository.findById(walletid).get().getTRY());
			transaction.setAmountPerCurrency(BigDecimal.ZERO);
			Transaction transaction1 = transactionRepository.save(transaction);
			Set<Transaction> transactionAccount = new HashSet<>();

			transactionAccount.add(transaction1);
			obj2.setTransactions(transactionAccount);
			// EndofTransactionBlock

			return wallet1;
		}

	}

	public Wallet addMoneyByCreditToSarWallet(long walletid, Wallet wallet, BigDecimal amount) {
		Set<Wallet> walletsAccount = new HashSet<>();
		Customer customer = new Customer();
		Optional<Customer> byId = customerRepository.findById(walletid);

		Customer customer1 = byId.get();
		wallet.setPhoneNumber(walletRepository.findById(walletid).get().getPhoneNumber());
		wallet.setTRY(walletRepository.findById(walletid).get().getTRY());
		wallet.setSAR(amount.add(walletRepository.findById(walletid).get().getSAR()));
		wallet.setId(walletRepository.findById(walletid).get().getId());
		// tie Wallet to Transaction (Parent to Child)
		wallet.setCustomer(customer1);
		Wallet wallet1 = walletRepository.save(wallet);

		// tie Transaction to Wallet (Child to Parent)
		walletsAccount.add(wallet1);
		customer.setWalletsAccount(walletsAccount);

		// TransactionBlock TransationList while adding Credit to SAR wallet

		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		String timestamp = ts.toString();
		//
		Wallet obj = new Wallet();
		Wallet obj2 = new Wallet();
		Optional<Wallet> byId2 = walletRepository.findById(walletid);

		obj = byId2.get();
		Transaction transaction = new Transaction();
		transaction.setWallet(obj);
		transaction.setUserPhoneNumber(walletRepository.findById(walletid).get().getPhoneNumber());
		transaction.setCurrentDateNow(timestamp);
		transaction.setFromAccount("Credit");
		transaction.setToAccount("SAR");
		transaction.setCurrentPerCurrency("" + walletRepository.findById(walletid).get().getSAR());
		transaction.setAmountPerCurrency(amount);
		Transaction transaction1 = transactionRepository.save(transaction);
		Set<Transaction> transactionAccount = new HashSet<>();

		transactionAccount.add(transaction1);
		obj2.setTransactions(transactionAccount);
		// EndofTransactionBlock

		return wallet1;
	}

	public Wallet transferMoneyFromSarWalletToIban(long walletid, Wallet wallet, BigDecimal amount) {

		// check negative
		BigDecimal SAR = walletRepository.findById(walletid).get().getSAR();
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Money cannot be negative or zero value.");

		} else if (SAR.compareTo(BigDecimal.ZERO) == 0) {
			throw new IllegalArgumentException(
					"transaction terminated, you need to add money in your wallet by Credit before proceeding, "
							+ "you need " + (amount.subtract(SAR))
							+ " SAR more to procced with Transfer, your balance is " + SAR + " SAR");

		} else if (SAR.compareTo(amount) <= 0) {
			throw new IllegalArgumentException("transaction terminated, you don't have sufficient money, " + "you need "
					+ (amount.subtract(SAR) + " SAR more to procced with Transfer, your balance is " + SAR + " SAR"));

		}

		else {
			Set<Wallet> walletsAccount = new HashSet<>();
			Customer customer = new Customer();
			Optional<Customer> byId = customerRepository.findById(walletid);

			Customer customer1 = byId.get();
			wallet.setPhoneNumber(walletRepository.findById(walletid).get().getPhoneNumber());
			wallet.setTRY(walletRepository.findById(walletid).get().getTRY());
			wallet.setSAR(SAR.subtract(amount));
			wallet.setId(walletRepository.findById(walletid).get().getId());
			// tie Wallet to Transaction (Parent to Child)
			wallet.setCustomer(customer1);
			Wallet wallet1 = walletRepository.save(wallet);

			// tie Transaction to Wallet (Child to Parent)
			walletsAccount.add(wallet1);
			customer.setWalletsAccount(walletsAccount);

			// TransactionBlock TransationList while Transfer money SAR to IBAN

			Date date = new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			String timestamp = ts.toString();
			//
			Wallet obj = new Wallet();
			Wallet obj2 = new Wallet();
			Optional<Wallet> byId2 = walletRepository.findById(walletid);

			obj = byId2.get();
			Transaction transaction = new Transaction();
			transaction.setWallet(obj);
			transaction.setUserPhoneNumber(walletRepository.findById(walletid).get().getPhoneNumber());
			transaction.setCurrentDateNow(timestamp);
			transaction.setFromAccount("SAR Wallet");
			transaction.setToAccount("IBAN");
			transaction.setCurrentPerCurrency("" + SAR);
			transaction.setAmountPerCurrency(amount);
			Transaction transaction1 = transactionRepository.save(transaction);
			Set<Transaction> transactionAccount = new HashSet<>();

			transactionAccount.add(transaction1);
			obj2.setTransactions(transactionAccount);
			// EndofTransactionBlock

			return wallet1;
		}
		// check negative

	}

	public Wallet addMoneyByCreditToTryWallet(long walletid, Wallet wallet, BigDecimal amount) {
		Set<Wallet> walletsAccount = new HashSet<>();
		Customer customer = new Customer();
		Optional<Customer> byId = customerRepository.findById(walletid);

		Customer customer1 = byId.get();
		wallet.setPhoneNumber(walletRepository.findById(walletid).get().getPhoneNumber());
		wallet.setTRY(walletRepository.findById(walletid).get().getTRY().add(amount));
		wallet.setSAR(walletRepository.findById(walletid).get().getSAR());
		wallet.setId(walletRepository.findById(walletid).get().getId());
		// tie Wallet to Transaction (Parent to Child)
		wallet.setCustomer(customer1);
		Wallet wallet1 = walletRepository.save(wallet);

		// tie Transaction to Wallet (Child to Parent)
		walletsAccount.add(wallet1);
		customer.setWalletsAccount(walletsAccount);
		// TransactionBlock TransationList while adding Credit to TRY wallet

		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		String timestamp = ts.toString();
		//
		Wallet obj = new Wallet();
		Wallet obj2 = new Wallet();
		Optional<Wallet> byId2 = walletRepository.findById(walletid);

		obj = byId2.get();
		Transaction transaction = new Transaction();
		transaction.setWallet(obj);
		transaction.setUserPhoneNumber(walletRepository.findById(walletid).get().getPhoneNumber());
		transaction.setCurrentDateNow(timestamp);
		transaction.setFromAccount("Credit");
		transaction.setToAccount("TRY");
		transaction.setCurrentPerCurrency("" + walletRepository.findById(walletid).get().getTRY());
		transaction.setAmountPerCurrency(amount);
		Transaction transaction1 = transactionRepository.save(transaction);
		Set<Transaction> transactionAccount = new HashSet<>();

		transactionAccount.add(transaction1);
		obj2.setTransactions(transactionAccount);
		// EndofTransactionBlock
		return wallet1;
	}

	public Wallet transferMoneyFromTryWalletToIban(long walletid, Wallet wallet, BigDecimal amount) {

		// check negative
		BigDecimal TRY = walletRepository.findById(walletid).get().getTRY();
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Money cannot be negative or zero value.");

		} else if (TRY.compareTo(BigDecimal.ZERO) == 0) {
			throw new IllegalArgumentException(
					"transaction terminated, you need to add money in your wallet by Credit before proceeding, "
							+ "you need " + (amount.subtract(TRY)
									+ " TRY more to procced with Transfer, your balance is " + TRY + " TRY"));

		} else if (TRY.compareTo(amount) < 0) {
			throw new IllegalArgumentException("transaction terminated, you don't have sufficient money, " + "you need "
					+ (amount.subtract(TRY) + " TRY more to procced with Transfer, your balance is " + TRY + " TRY"));

		}

		else {
			Set<Wallet> walletsAccount = new HashSet<>();
			Customer customer = new Customer();
			Optional<Customer> byId = customerRepository.findById(walletid);

			Customer customer1 = byId.get();
			wallet.setPhoneNumber(walletRepository.findById(walletid).get().getPhoneNumber());
			wallet.setTRY(TRY.subtract(amount));
			wallet.setSAR(walletRepository.findById(walletid).get().getSAR());
			wallet.setId(walletRepository.findById(walletid).get().getId());
			// tie Wallet to Transaction (Parent to Child)
			wallet.setCustomer(customer1);
			Wallet wallet1 = walletRepository.save(wallet);

			// tie Transaction to Wallet (Child to Parent)
			walletsAccount.add(wallet1);
			customer.setWalletsAccount(walletsAccount);

			// TransactionBlock TransationList while Transfer money TRY to IBAN

			Date date = new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			String timestamp = ts.toString();
			//
			Wallet obj = new Wallet();
			Wallet obj2 = new Wallet();
			Optional<Wallet> byId2 = walletRepository.findById(walletid);

			obj = byId2.get();
			Transaction transaction = new Transaction();
			transaction.setWallet(obj);
			transaction.setUserPhoneNumber(walletRepository.findById(walletid).get().getPhoneNumber());
			transaction.setCurrentDateNow(timestamp);
			transaction.setFromAccount("TRY Wallet");
			transaction.setToAccount("IBAN");
			transaction.setCurrentPerCurrency("" + TRY);
			transaction.setAmountPerCurrency(amount);
			Transaction transaction1 = transactionRepository.save(transaction);
			Set<Transaction> transactionAccount = new HashSet<>();

			transactionAccount.add(transaction1);
			obj2.setTransactions(transactionAccount);
			// EndofTransactionBlock

			return wallet1;
		}
	}

	public void deleteWallets(long id) {
		walletRepository.delete(id);
	}

}
