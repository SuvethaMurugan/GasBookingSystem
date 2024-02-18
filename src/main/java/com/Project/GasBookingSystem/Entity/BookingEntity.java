package com.Project.GasBookingSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="Booking_Date")
    private LocalDate bookingDate;
    @Column(name="Delivery_Date")
    private LocalDate deliveryDate;
    @Column(name="Status")
    private String status;
    @OneToOne
    private PaymentEntity payment;
    @OneToOne
    private CylinderEntity cylinder;
}
