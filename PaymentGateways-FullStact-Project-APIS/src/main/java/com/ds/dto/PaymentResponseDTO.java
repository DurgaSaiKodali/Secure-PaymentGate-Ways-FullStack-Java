package com.ds.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PaymentResponseDTO {
	
	private String transactionId;
	private String status;
	private LocalDateTime createdAt;

}
