package com.example.makhzan.Controller;

import com.example.makhzan.DTO.CustomerDTO;
import com.example.makhzan.Model.User;
import com.example.makhzan.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/customer")
public class CustomerController {
    private final CustomerService customerService;
    //Admin
    @GetMapping("/get")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    //All
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.register(customerDTO);
        return ResponseEntity.status(200).body("Customer Register");
    }

    @PutMapping("update")
    public ResponseEntity updateCustomer(@RequestBody @Valid CustomerDTO customerDTO, @AuthenticationPrincipal User user){
        customerService.updateCustomer(customerDTO,user.getId());
        return ResponseEntity.status(200).body("Customer updated");

    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user){
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body("Customer deleted");
    }
}

