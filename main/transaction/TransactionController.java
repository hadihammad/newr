package main.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/walletId={parentId}/addTransaction", method = RequestMethod.POST)
	public Transaction createChild(@PathVariable String parentId, @RequestBody Transaction child) {
		return transactionService.createChild(parentId, child);
	}

	//
	@RequestMapping(value = "/walletId={parentId}/updateTransaction", method = RequestMethod.PUT)
	public Transaction updateChild(@PathVariable String parentId, @RequestBody Transaction child) {
		return transactionService.createChild(parentId, child);
	}

}
