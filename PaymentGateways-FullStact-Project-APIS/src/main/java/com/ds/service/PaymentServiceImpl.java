package com.ds.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.dto.PaymentRequestDTO;
import com.ds.dto.PaymentResponseDTO;
import com.ds.exception.ResourceNotFoundException;
import com.ds.model.Payment;
import com.ds.model.User;
import com.ds.repository.IPaymentRepository;
import com.ds.repository.IUserRepository;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	IUserRepository userRepo; 

	@Autowired
	private IPaymentRepository paymentRepo;

	@Override //making transactio 
	public PaymentResponseDTO initiatePayment(PaymentRequestDTO dto, String username) {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException(" User Not Found ..... "));
		Payment payment = new Payment();
		payment.setTransactionId(UUID.randomUUID().toString());
		payment.setPaymentMethod(dto.getPaymentMethod());
		payment.setAmount(dto.getAmount());
		payment.setDescription(dto.getDescription());
		payment.setCreatedAt(LocalDateTime.now());
		payment.setStatus("SUCCESS");
		payment.setUser(user);
		paymentRepo.save(payment);

		PaymentResponseDTO response = new PaymentResponseDTO();
		response.setTransactionId(payment.getTransactionId());
		response.setStatus(payment.getStatus());
		response.setCreatedAt(payment.getCreatedAt());

		return response;

	}

	@Override  //username
	public List<Payment> getPaymentForUser(String username) {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		return paymentRepo.findByUser(user);
	}

	@Override //transaction id
	public Payment getPaymentByTransactionId(String tnx) {

		return paymentRepo.findByTransactionId(tnx);
	}

}
