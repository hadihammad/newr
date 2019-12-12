package main.wallet;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {

	Optional<Wallet> findById(long walletid);

	Wallet findByCustomerIdAndId(long customerId, long walletId);
}
