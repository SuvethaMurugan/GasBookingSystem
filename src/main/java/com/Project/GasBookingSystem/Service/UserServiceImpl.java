package com.Project.GasBookingSystem.Service;

import com.Project.GasBookingSystem.DAO.*;
import com.Project.GasBookingSystem.DTO.CylinderAddDTO;
import com.Project.GasBookingSystem.DTO.BankUpdateDTO;
import com.Project.GasBookingSystem.DTO.PaymentUpdateDTO;
import com.Project.GasBookingSystem.DTO.ViewUserDTO;
import com.Project.GasBookingSystem.Entity.*;
import com.Project.GasBookingSystem.Exception.AddCylinderException;
import com.Project.GasBookingSystem.Exception.PaymentException;
import com.Project.GasBookingSystem.Exception.ViewUserProfileException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private CylinderRepository cylinderRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public CustomerEntity createuser(CustomerEntity customerEntity) {
        BankEntity bank=new BankEntity();
        bank.setBranch(customerEntity.getBank().getBranch());
        bank.setAccountNo(customerEntity.getBank().getAccountNo());
        bank.setBankName(customerEntity.getBank().getBankName());
        bank.setBalance(customerEntity.getBank().getBalance());
        bank.setIsActive(customerEntity.getBank().getIsActive());
        this.bankRepository.save(bank);
        customerEntity.setBank(bank);
        return this.customerRepository.save(customerEntity);
    }

    @Override
    public CylinderEntity addCylinder(CylinderEntity cylinderEntity) {
        return this.cylinderRepository.save(cylinderEntity);
    }

    @Override
    public CustomerEntity addCylinderToCustomer(CylinderAddDTO cylinderAddDTO) throws AddCylinderException {
        Optional<CustomerEntity> customerIdOptional=this.customerRepository.findById(cylinderAddDTO.getCustomerId());
        Optional<CylinderEntity> cylinderIdOptional=this.cylinderRepository.findById(cylinderAddDTO.getCylinderId());
        CustomerEntity customerId=customerIdOptional.get();
        CylinderEntity cylinderId=cylinderIdOptional.get();
        if(!cylinderId.getIsActive()) throw new AddCylinderException("Enter an valid cylinder ID");
        cylinderId.setIsActive(Boolean.FALSE);
        this.cylinderRepository.save(cylinderId);
        BookingEntity bookingEntity=new BookingEntity();
        bookingEntity.setCylinder(cylinderId);
        this.bookingRepository.save(bookingEntity);
        customerId.getBookingEntityList().add(bookingEntity);
        return this.customerRepository.save(customerId);

    }

    @Override
    @Transactional(rollbackOn = {PaymentException.class})
    public BookingEntity paymentCylinder(PaymentUpdateDTO paymentDTO) throws PaymentException {
        Optional<CustomerEntity> customerEntityOptional=this.customerRepository.findById(paymentDTO.getCustomerId());
        if(customerEntityOptional.isEmpty()) throw new PaymentException("The account does not exists");
        CustomerEntity customerId=customerEntityOptional.get();
        if(customerId.getBookingEntityList().isEmpty()) throw new PaymentException("The Booking list is empty! Add a cylinder to book");
        Optional<BankEntity> bankEntityOptional=this.bankRepository.findById(customerId.getBank().getBankId());
        BankEntity bankId=bankEntityOptional.get();
        if(bankId.getIsActive()==null) throw new PaymentException("The bank account is not active! Register the Bank account");
        Optional<BookingEntity> bookingEntityOptional=this.bookingRepository.findById(paymentDTO.getBookingId());
        if(bookingEntityOptional.isEmpty()) throw new PaymentException("The entered Id doesn't exist enter an valid booking id");
        BookingEntity bookingId=bookingEntityOptional.get();
        if(bookingId.getStatus()!=null && bookingId.getStatus().equals("BOOKED")) throw new PaymentException("The amount is paid for the given booking Id");
        Double price=bookingEntityOptional.get().getCylinder().getPrice();
        if(bankId.getBalance()<price) throw new PaymentException("The account balance is insufficent");
        Double balance=bankId.getBalance()-price;
        bankId.setBalance(balance);
        this.bankRepository.save(bankId);
        PaymentEntity paymentEntity=new PaymentEntity();
        paymentEntity.setPaymentStatus("PAID");
        paymentEntity.setPaymentDate(LocalDate.now());
        paymentEntity.setPaymentType("Online Mode");
        paymentEntity.setPaymentAmount(price);
        bookingId.setStatus("BOOKED");
        bookingId.setBookingDate(LocalDate.now());
        bookingId.setDeliveryDate(LocalDate.now().plusDays(3));
        bookingId.setPayment(paymentEntity);
        this.paymentRepository.save(paymentEntity);
        this.customerRepository.save(customerId);
        return this.bookingRepository.save(bookingId);

    }

    @Override
    public CustomerEntity getCustomer(Integer id) {
        Optional<CustomerEntity> customer= this.customerRepository.findById(id);
        return customer.get();
    }

    @Override
    public CustomerEntity updateBankAccount(BankUpdateDTO bankUpdateDTO) {
        Optional<CustomerEntity> customer=this.customerRepository.findById(bankUpdateDTO.getCustomerId());
        CustomerEntity customerEntity1=customer.get();
        BankEntity bank=customerEntity1.getBank();
        bank.setAccountNo(bankUpdateDTO.getAccountNo());
        bank.setBankName(bankUpdateDTO.getBankName());
        bank.setBranch(bankUpdateDTO.getBankName());
        bank.setBalance(bankUpdateDTO.getBalance());
        bank.setIsActive(Boolean.TRUE);
        this.bankRepository.save(bank);
        return this.customerRepository.save(customerEntity1);

    }

    @Override
    public ViewUserDTO viewProfile(Integer id) throws ViewUserProfileException {
        ViewUserDTO viewUserDTO=new ViewUserDTO();
        Optional<CustomerEntity> viewuser=this.customerRepository.findById(id);
        if(viewuser.isEmpty()) throw new ViewUserProfileException("The Entered Id doesn't exists! Enter an valid Id");
        CustomerEntity customer=viewuser.get();
        viewUserDTO.setId(customer.getId());
        viewUserDTO.setBank(customer.getBank());
        viewUserDTO.setUserName(customer.getUserName());
        viewUserDTO.setBookingEntityList(customer.getBookingEntityList());
        viewUserDTO.setEmail(customer.getEmail());
        viewUserDTO.setIsActive(customer.isIsActive());
        viewUserDTO.setMobileNo(customer.getMobileNo());
        return viewUserDTO;

    }
}
