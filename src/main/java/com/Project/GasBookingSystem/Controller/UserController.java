package com.Project.GasBookingSystem.Controller;

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
import com.Project.GasBookingSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CustomerEntity createUser(@RequestBody CustomerEntity customerEntity){
        return this.userService.createuser(customerEntity);
    }
    @PostMapping("/book")
    public CylinderEntity createUser(@RequestBody CylinderEntity cylinderEntity){
        return this.userService.addCylinder(cylinderEntity);
    }
    @PostMapping("/addcylinder")
    public CustomerEntity addcylinderToUser(@RequestBody CylinderAddDTO cylinderAddDTO) throws AddCylinderException {
        return this.userService.addCylinderToCustomer(cylinderAddDTO);
    }
    @PostMapping("/book/payment")
    public BookingEntity paymentForCylinder(@RequestBody PaymentUpdateDTO paymentDTO) throws PaymentException {
        return this.userService.paymentCylinder(paymentDTO);
    }
    @PatchMapping("/update/bank")
    public CustomerEntity updateBankAccount(@RequestBody BankUpdateDTO bankUpdateDTO){
        return this.userService.updateBankAccount(bankUpdateDTO);
    }
    @GetMapping("/customer/{id}")
    public CustomerEntity getCustomer(@PathVariable("id") Integer id){

        return this.userService.getCustomer(id);
    }
    @GetMapping("viewprofile/{id}")
    public ViewUserDTO viewProfile(@PathVariable("id") Integer id) throws ViewUserProfileException {
        return this.userService.viewProfile(id);
    }
}
