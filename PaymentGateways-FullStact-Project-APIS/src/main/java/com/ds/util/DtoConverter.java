package com.ds.util;

import java.util.List;
import java.util.stream.Collectors;

import com.ds.dto.PaymentDTO;
import com.ds.dto.UserDTO;
import com.ds.model.Payment;
import com.ds.model.User;

public class DtoConverter {
	
	public static UserDTO toUserDto(User user) {
		
		return new UserDTO(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getEmail(),
				user.getCreatedAt()
				);
		
	}
	
	public static PaymentDTO toPaymentDTO(Payment payment) {
		return new PaymentDTO(
				payment.getTransactionId(),
				payment.getPaymentMethod(),
				payment.getAmount(),
				payment.getStatus(),
				payment.getDescription(),
				payment.getCreatedAt()
				);
	}
	public static List<PaymentDTO> toPaymentDTOLIST(List<Payment> payments){
		return payments.stream()
				.map(DtoConverter :: toPaymentDTO)
				.collect(Collectors.toList());
	}

}
