package com.Project.GasBookingSystem.DTO;

import com.Project.GasBookingSystem.Entity.BankEntity;
import com.Project.GasBookingSystem.Entity.BookingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewUserDTO {
    private Integer id;
    private String userName;
    private String email;
    private String mobileNo;
    private boolean IsActive;
    @OneToOne
    private BankEntity bank;
    @OneToMany
    private List<BookingEntity> bookingEntityList;
}
