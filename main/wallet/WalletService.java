package main.wallet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.customer.Customer;
import main.customer.CustomerRepository;

@Service
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;
	
	
	@Autowired
	private CustomerRepository customerRepository;

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
    public Wallet createChild(String walletid, Wallet wallet) {
        Set<Wallet> walletsAccount = new HashSet<>();
        Customer customer = new Customer();

        Optional<Customer> byId = customerRepository.findById(walletid);
      
        Customer customer1 = byId.get();

        //tie Author to Book
        wallet.setCustomer(customer1);

        Wallet wallet1 = walletRepository.save(wallet);
        //tie Book to Author
        walletsAccount.add(wallet1);
        customer.setWalletsAccount(walletsAccount);

        return wallet1;

    }
    
    //
    public Wallet updateChild(String walletid, Wallet wallet) {
        Set<Wallet> walletsAccount = new HashSet<>();
        Customer customer = new Customer();

        Optional<Customer> byId = customerRepository.findById(walletid);
      
        Customer customer1 = byId.get();

        //tie Author to Book
        wallet.setCustomer(customer1);

        Wallet wallet1 = walletRepository.save(wallet);
        //tie Book to Author
        walletsAccount.add(wallet1);
        customer.setWalletsAccount(walletsAccount);

        return wallet1;

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
