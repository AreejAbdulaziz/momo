package com.example.makhzan.Controller;

import com.example.makhzan.DTO.CustomerDTO;
import com.example.makhzan.DTO.LandlordDTO;
import com.example.makhzan.Model.User;
import com.example.makhzan.Service.CustomerService;
import com.example.makhzan.Service.LandLordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/landlord")
public class LandlordController {
    private final LandLordService landLordService;

    @GetMapping("/get")
    public ResponseEntity getAllLandlord() {
        return ResponseEntity.status(200).body(landLordService.getAllLandlords());
    }

    //All
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid LandlordDTO landlordDTO) {
        landLordService.register(landlordDTO);
        return ResponseEntity.status(200).body("Landlord Register");
    }

    @PutMapping("update")
    public ResponseEntity updateLandlord(@RequestBody @Valid LandlordDTO landlordDTO, @AuthenticationPrincipal User user){
        landLordService.updateLandlord(landlordDTO,user.getId());
        return ResponseEntity.status(200).body("Landlord updated");

    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteLandlord(@AuthenticationPrincipal User user){
        landLordService.deleteLandlord(user.getId());
        return ResponseEntity.status(200).body("Landlord deleted");
    }
}
