package com.Project.GasBookingSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.print.Book;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name="Customer_ID")
    private Integer id;
    @Column(name="User_Name")
    private String userName;
    @Column(name="Password")
    private String password;
    @Column(name="Email_ID")
    private String email;
    @Column(name="Mobile_No")
    private String mobileNo;
    @Column(name="IsActive")
    private boolean IsActive;
    @OneToOne
    private BankEntity bank;
    @OneToMany
    private List<BookingEntity> bookingEntityList;
}
