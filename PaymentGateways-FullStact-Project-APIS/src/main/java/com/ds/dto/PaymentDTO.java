package com.ds.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	
	private String TransactionId;
	private String paymentMethod;
	private Double amount;
	private String status;
	private String description;
	private LocalDateTime createdAt;

}
