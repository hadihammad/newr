package main.wallet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.customer.Customer;
import main.customer.CustomerRepository;

@RestController
public class WalletController {

	@Autowired
	private WalletService walletService;
	
    @Autowired
    CustomerRepository parentService;

	@GetMapping("/wallets")
	public List<Wallet> getAllCustomer() {

		return walletService.getAllWallets();

	}

	@GetMapping("/wallets/{id}")
	public Wallet getWallet(@PathVariable String id) {

		return walletService.getWallet(id);

	}
	
	@GetMapping("/customer={customerId}/walletId={walletId}")
	public Wallet getWalletForNested(@PathVariable String customerId,@PathVariable String walletId) {

		return walletService.getWalletForNested(customerId,walletId);

	}
	
//
	
	@GetMapping("/customer={customerId}/currency=sar")
	public String getWalletByIdSar(@PathVariable String customerId) {

		return walletService.getWalletByIdSar(customerId);

	}
	//
	@GetMapping("/customer={customerId}/currency=try")
	public String getWalletByIdTry(@PathVariable String customerId) {

		return walletService.getWalletByIdTry(customerId);

	}
	//

	@PostMapping(value = "/wallets")
	public void addWallet(@RequestBody Wallet wallet) {

		walletService.addWallets(wallet);

	}

	@PutMapping(value = "/wallets/{id}")
	public void updateWallet(@RequestBody Wallet wallet, @PathVariable String id) {

		walletService.updateWallets(id, wallet);

	}

	@DeleteMapping(value = "/wallets/{id}")
	public void deleteWallet(@PathVariable String id) {

		walletService.deleteWallets(id);

	}
	
	//1
    @RequestMapping(value = "/custumersId={parentId}/addwallet", method = RequestMethod.POST)
    public Wallet createChild(@PathVariable String parentId, @RequestBody Wallet child) {
        return walletService.createChild(parentId, child);
    }
    //
    @RequestMapping(value = "/custumersId={parentId}/updateWallet", method = RequestMethod.PUT)
    public Wallet updateChild(@PathVariable String parentId, @RequestBody Wallet child) {
        return walletService.createChild(parentId, child);
    }
	
	//
    @RequestMapping(value = "/child/{childId}", method = RequestMethod.GET)
    public Optional<Wallet> getChildById(@PathVariable String childId) {
        return walletService.getWalletById(childId);
    }
	//
    @RequestMapping(value = "/parent", method = RequestMethod.POST)
    public Customer createParent(@RequestBody Customer parent) {
        return parentService.save(parent);
    }
	//
	
	//end of 1

}
