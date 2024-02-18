package com.Project.GasBookingSystem.Service;

import com.Project.GasBookingSystem.DTO.CylinderAddDTO;
import com.Project.GasBookingSystem.DTO.BankUpdateDTO;
import com.Project.GasBookingSystem.DTO.PaymentUpdateDTO;
import com.Project.GasBookingSystem.DTO.ViewUserDTO;
import com.Project.GasBookingSystem.Entity.BookingEntity;
import com.Project.GasBookingSystem.Entity.CustomerEntity;
import com.Project.GasBookingSystem.Entity.CylinderEntity;
import com.Project.GasBookingSystem.Exception.AddCylinderException;
import com.Project.GasBookingSystem.Exception.PaymentException;
import com.Project.GasBookingSystem.Exception.ViewUserProfileException;

public interface UserService {
     CustomerEntity createuser(CustomerEntity customerEntity);

     CylinderEntity addCylinder(CylinderEntity cylinderEntity);
     CustomerEntity addCylinderToCustomer(CylinderAddDTO cylinderAddDTO) throws AddCylinderException;
     BookingEntity paymentCylinder(PaymentUpdateDTO paymentDTO) throws PaymentException;

     CustomerEntity getCustomer(Integer id);

     CustomerEntity updateBankAccount(BankUpdateDTO bankUpdateDTO);

     ViewUserDTO viewProfile(Integer id) throws ViewUserProfileException;
}
