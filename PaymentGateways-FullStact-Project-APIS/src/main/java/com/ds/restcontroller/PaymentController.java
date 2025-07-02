package com.ds.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.dto.PaymentDTO;
import com.ds.dto.PaymentRequestDTO;
import com.ds.dto.PaymentResponseDTO;
import com.ds.model.Payment;
import com.ds.service.IPaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private IPaymentService paymentService;

	@PostMapping("/initiate")
	public ResponseEntity<PaymentResponseDTO> initiatePayment(@RequestBody PaymentRequestDTO dto,
			Authentication auth) {
		String username=auth.getName();
		PaymentResponseDTO response=paymentService.initiatePayment(dto, username);
		return ResponseEntity.status(201).body(response);
	}

	@GetMapping("/{txnId}")
	public ResponseEntity<Payment> getPayment(@PathVariable String txnId) {
		Payment payment = paymentService.getPaymentByTransactionId(txnId);
		return ResponseEntity.ok(payment); 
	}

	@GetMapping("/history")
	public ResponseEntity<List<PaymentDTO>> getHistory(@AuthenticationPrincipal UserDetails userDetails) {
		List<Payment> payments = paymentService.getPaymentForUser(userDetails.getUsername());
		List<PaymentDTO> dtos = payments.stream().map(p -> new PaymentDTO(p.getTransactionId(), p.getPaymentMethod(),
				p.getAmount(), p.getStatus(), p.getDescription(), p.getCreatedAt())).toList();
		return ResponseEntity.ok(dtos);
	}

}
