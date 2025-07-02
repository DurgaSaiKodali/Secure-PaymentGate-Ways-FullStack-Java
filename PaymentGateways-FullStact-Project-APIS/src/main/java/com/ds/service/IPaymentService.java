package com.ds.service;

import java.util.List;

import com.ds.dto.PaymentRequestDTO;
import com.ds.dto.PaymentResponseDTO;
import com.ds.model.Payment;

public interface IPaymentService {
	PaymentResponseDTO initiatePayment(PaymentRequestDTO dto,String username);
	List<Payment> getPaymentForUser(String username);
	Payment getPaymentByTransactionId(String tnx);

}
