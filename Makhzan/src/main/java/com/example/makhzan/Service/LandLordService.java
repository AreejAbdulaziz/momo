package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.DTO.CustomerDTO;
import com.example.makhzan.DTO.LandlordDTO;
import com.example.makhzan.Model.Customer;
import com.example.makhzan.Model.Landlord;
import com.example.makhzan.Model.User;
import com.example.makhzan.Repository.LandLordRepository;
import com.example.makhzan.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandLordService {
    private final LandLordRepository landLordRepository;
    private final UserRepository userRepository;

    public List<Landlord> getAllLandlords(){
        return landLordRepository.findAll();
    }
    public void register(LandlordDTO landlordDTO){
        User user=new User(null,landlordDTO.getPassword(),landlordDTO.getName(),landlordDTO.getEmail(),landlordDTO.getPhoneNumber(),landlordDTO.getRole(),null,null); //r
        user.setRole("LANDLORD");
        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);

        Landlord landlord=new Landlord(null, "pending",landlordDTO.getIsCompany(),landlordDTO.getLicense(),user,null);
        landLordRepository.save(landlord);
    }

    public void updateLandlord(LandlordDTO landlordDTO ,Integer id) {
        Landlord landlord = landLordRepository.findLandLordById(id);
        if (landlord == null) {
            throw new ApiException("Landlord Not Found");
        }
        User user=userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User Not Found");
        }
        landlord.setName(landlordDTO.getName());
        landlord.setEmail(landlordDTO.getEmail());
        landlord.setPhoneNumber(landlordDTO.getPhoneNumber());
        landlord.setIsCompany(landlordDTO.getIsCompany());
        landlord.setLicense(landlordDTO.getLicense());
        String hash=new BCryptPasswordEncoder().encode(landlordDTO.getPassword());
        landlord.setPassword(hash);

        landLordRepository.save(landlord);
    }

    public void deleteLandlord(Integer id){
        Landlord landlord = landLordRepository.findLandLordById(id);
        if (landlord == null) {
            throw new ApiException("Landlord Not Found");
        }
        User user=userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User Not Found");
        }
        user.setCustomer(null);
        userRepository.save(user);
        landLordRepository.delete(landlord);

    }
}
