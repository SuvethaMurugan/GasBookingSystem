package com.Project.GasBookingSystem.Controller;

import com.Project.GasBookingSystem.Exception.AddCylinderException;
import com.Project.GasBookingSystem.Exception.PaymentException;
import com.Project.GasBookingSystem.Exception.ViewUserProfileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.swing.text.View;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(value={PaymentException.class})
    public ResponseEntity<String> paymentException(PaymentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(value={ViewUserProfileException.class})
    public ResponseEntity<String> viewUserException(ViewUserProfileException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(value={AddCylinderException.class})
    public ResponseEntity<String> addCylinderExceptiom(AddCylinderException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

}
