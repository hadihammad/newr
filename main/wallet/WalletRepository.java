package main.wallet;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, String> {

//	Wallet boolean existsById(String walletid);

	Optional<Wallet> findById(String walletid);

	Wallet findByCustomerIdAndId(String customerId, String walletId);
}
