package main.wallet;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.customer.CustomerRepository;

@RestController
public class WalletController {

	@Autowired
	private WalletService walletService;

	@Autowired
	CustomerRepository parentService;

	@GetMapping("/wallet/allWallets") // all wallets Transactions per different Users, only for testing purposes,not
										// ideal for business.
	public List<Wallet> getAllCustomer() {

		return walletService.getAllWallets();

	}

	@GetMapping("/walletId={walletId}") // wallet Transactions for specific phoneNumbers, wallet Id is always same as
										// customer Id.
	public Wallet getWallet(@PathVariable long walletId) {

		return walletService.getWallet(walletId);

	}

	@GetMapping("/customerId={customerId}/walletId={walletId}") // individual view: customer can only able to see his
																// own Info.
	public Wallet getWalletForNested(@PathVariable long customerId, @PathVariable long walletId)
			throws AccessDeniedException {
		if (!(customerId == walletId)) {
			throw new AccessDeniedException("You are not Authorized to view this page");
		} else {
			return walletService.getWalletForNested(customerId, walletId);
		}
	}

	@GetMapping("/customerId={customerId}/currency=sar") // individual view: customer can see specific current currency
															// for SAR
	public BigDecimal getWalletByIdSar(@PathVariable long customerId) {

		return walletService.getWalletByIdSar(customerId);

	}

	@GetMapping("/customerId={customerId}/currency=try") // individual view: customer can see specific current currency
															// for TRY
	public BigDecimal getWalletByIdTry(@PathVariable long customerId) {

		return walletService.getWalletByIdTry(customerId);

	}

	@DeleteMapping(value = "/walletId={walletId}") // Delete wallet per Id, wallet Id is always same as customer Id.
	public void deleteWallet(@PathVariable long walletId) {

		walletService.deleteWallets(walletId);

	}

	@PostMapping(value = "/custumerId={custumerId}/addwallet") // Initialize the wallet per currency.
	public Wallet createWalletUnderCustomer(@PathVariable long custumerId, @RequestBody Wallet child) {

		return walletService.createWalletUnderCustomer(custumerId, child);
	}

	@PatchMapping(value = "/custumerId={custumerId}/addMoneyByCreditToSarWallet={amount:.+}") // Deposit SAR
	public Wallet addMoneyByCreditToSarWallet(@PathVariable long custumerId, @RequestBody Wallet child,
			@PathVariable BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Money cannot be negative or zero value.");
		} else {
			return walletService.addMoneyByCreditToSarWallet(custumerId, child, amount);
		}
	}

	@PatchMapping(value = "/custumerId={custumerId}/transferMoneyFromSarWalletToIban={amount:.+}") // deduct money from
																									// SAR to IBAN
	public Wallet transferMoneyFromSarWalletToIban(@PathVariable long custumerId, @RequestBody Wallet child,
			@PathVariable BigDecimal amount) {
		return walletService.transferMoneyFromSarWalletToIban(custumerId, child, amount);
	}

	@PatchMapping(value = "/custumerId={custumerId}/addMoneyByCreditToTryWallet={amount:.+}") // Deposit TRY
	public Wallet addMoneyByCreditToTryWallet(@PathVariable long custumerId, @RequestBody Wallet child,
			@PathVariable BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Money cannot be negative or zero value.");
		} else {
			return walletService.addMoneyByCreditToTryWallet(custumerId, child, amount);
		}
	}

	@PatchMapping(value = "/custumerId={custumerId}/transferMoneyFromTryWalletToIban={amount:.+}") // deduct money from
																									// TRY to IBAN
	public Wallet transferMoneyFromTryWalletToIban(@PathVariable long custumerId, @RequestBody Wallet child,
			@PathVariable BigDecimal amount) {
		return walletService.transferMoneyFromTryWalletToIban(custumerId, child, amount);
	}

}
