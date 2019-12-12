package main.transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.customer.Customer;
import main.customer.CustomerRepository;
import main.wallet.Wallet;
import main.wallet.WalletRepository;
import main.wallet.WalletService;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;


	
	
	@Autowired
	private WalletRepository walletRepository;

	public List<Wallet> getAllWallets() {
		List<Wallet> wallets = new ArrayList<>();
		walletRepository.findAll().forEach(wallets::add);
		return wallets;

	}

	public Wallet getWallet(String phoneNumber) {
		return walletRepository.findOne(phoneNumber);
	}
///2
	public Wallet getWalletForNested(String customerId, String walletId) {
		return walletRepository.findByCustomerIdAndId(customerId,walletId);

	}
	
	//
    public String getWalletByIdSar(String walletid) {

        return walletRepository.findById(walletid).get().getSAR();
    }
    
    //
    public String getWalletByIdTry(String walletid) {

        return walletRepository.findById(walletid).get().getTRY();
    }
    

	///end 2
	//1
    public Optional<Wallet> getWalletById(String walletid) {

        return walletRepository.findById(walletid);
    }
    
    //
    public Transaction createChild(String transactionid, Transaction transaction) {
        Set<Transaction> transactions = new HashSet<>();
        Wallet wallet = new Wallet();

        Optional<Wallet> byId = walletRepository.findById(transactionid);
      
        Wallet wallet1 = byId.get();

        //tie Author to Book
        transaction.setWallet(wallet1);

        Transaction transaction1 = transactionRepository.save(transaction);
        //tie Book to Author
        transactions.add(transaction1);
        wallet.setTransactions(transactions);

        return transaction1;

    }
    
    //
    public Transaction updateChild(String transactionid, Transaction transaction) {
        Set<Transaction> transactions = new HashSet<>();
        Wallet wallet = new Wallet();

        Optional<Wallet> byId = walletRepository.findById(transactionid);
      
        Wallet wallet1 = byId.get();

        //tie Author to Book
        transaction.setWallet(wallet1);

        Transaction transaction1 = transactionRepository.save(transaction);
        //tie Book to Author
        transactions.add(transaction1);
        wallet.setTransactions(transactions);

        return transaction1;

    }
    //End of 1

	public void addWallets(Wallet wallet) {
		walletRepository.save(wallet);
	}
//////////////?????????
	public void updateWallets(String phoneNumber, Wallet wallet) {

		walletRepository.save(wallet);

	}

	public void deleteWallets(String phoneNumber) {
		walletRepository.delete(phoneNumber);
	}




	
}
