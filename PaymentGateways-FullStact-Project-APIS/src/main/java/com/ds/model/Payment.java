package com.ds.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique Transaction ID (for external tracking)
    @Column(unique = true, nullable = false, updatable = false)
    private String transactionId;

    private String paymentMethod; // CARD, UPI, WALLET, etc.

    private Double amount;

    private String status; // SUCCESS, FAILED, REFUNDED, etc.

    private String description; // Optional: Reason or Note

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Linked user who made the payment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        this.transactionId = UUID.randomUUID().toString(); // auto-generate on creation
    }
}
