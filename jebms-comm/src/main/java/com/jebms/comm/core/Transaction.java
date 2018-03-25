package com.jebms.comm.core;


import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class Transaction {

	public static void setRollbackOnly() {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
}
