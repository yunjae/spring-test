package com.apress.prospringmvc.moneytransfer.repository;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.apress.prospringmvc.moneytransfer.domain.Account;
import com.apress.prospringmvc.moneytransfer.domain.MoneyTransferTransaction;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;
import com.apress.prospringmvc.moneytransfer.service.MoneyTransferService;

public class MoneyTransferServiceImpl implements MoneyTransferService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Transaction transfer(String source, String target, BigDecimal amount) {
		// TODO Auto-generated method stub
		Account src = this.accountRepository.find(source);
		Account dst = this.accountRepository.find(target);
		src.credit(amount);
		dst.debit(amount);
		
		MoneyTransferTransaction transaction = new MoneyTransferTransaction(src, dst, amount);
		this.transactionRepository.store(transaction);
		return transaction;
	}

}
