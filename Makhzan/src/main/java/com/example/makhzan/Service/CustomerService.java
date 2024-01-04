package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.DTO.CustomerDTO;
import com.example.makhzan.Model.Customer;
import com.example.makhzan.Model.User;
import com.example.makhzan.Repository.CustomerRepository;
import com.example.makhzan.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void register(CustomerDTO customerDTO){
        User user=new User(null,customerDTO.getPassword(),customerDTO.getName(),customerDTO.getEmail(),customerDTO.getPhoneNumber(),customerDTO.getRole(),null,null);
        user.setRole("CUSTOMER");
        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);

        Customer customer=new Customer(null, customerDTO.getBirthDate(),null,user,null);
        customerRepository.save(customer);
    }
    public void updateCustomer(CustomerDTO customerDTO ,Integer id) {
        Customer oldCustomer = customerRepository.findCustomerById(id);
        if (oldCustomer == null) {
            throw new ApiException("Customer Not Found");
        }
        User user=userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User Not Found");
        }
        oldCustomer.setName(customerDTO.getName());
        oldCustomer.setEmail(customerDTO.getEmail());
        oldCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        oldCustomer.setBirthDate(customerDTO.getBirthDate());
        String hash=new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        oldCustomer.setPassword(hash);

        customerRepository.save(oldCustomer);
    }
    public void deleteCustomer(Integer id){
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("Customer Not Found");
        }
        User user=userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User Not Found");
        }
        user.setCustomer(null);
        userRepository.save(user);
        customerRepository.delete(customer);

    }

}
