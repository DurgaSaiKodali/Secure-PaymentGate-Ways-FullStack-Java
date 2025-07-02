package com.ds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.model.Payment;
import com.ds.model.User;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
	 List<Payment> findByUser(User user);
	    Payment findByTransactionId(String transactionId);
	

}
