package com.Project.GasBookingSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Payment_Id")
    private Integer paymentId;
    @Column(name="Payment_type")
    private String paymentType;
    @Column(name="Payment_Date")
    private LocalDate paymentDate;
    @Column(name="Amount")
    private Double paymentAmount;
    @Column(name="Payment_Status")
    private String paymentStatus;
}
