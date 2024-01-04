package com.example.makhzan.Controller;

import com.example.makhzan.DTO.LandlordDTO;
import com.example.makhzan.DTO.MediaDTO;
import com.example.makhzan.Model.User;
import com.example.makhzan.Service.MediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/media")
public class MediaController {
    private final MediaService mediaService;

    @GetMapping("/get")
    public ResponseEntity getAllMedias() {
        return ResponseEntity.status(200).body(mediaService.getAllLMedias());
    }

    //All
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid MediaDTO mediaDTO) {
        mediaService.add(mediaDTO);
        return ResponseEntity.status(200).body("Media Added");
    }

    @PutMapping("update")
    public ResponseEntity updateMedia(@RequestBody @Valid MediaDTO mediaDTO, @AuthenticationPrincipal User user){
        mediaService.updateMedia(mediaDTO,user.getId());
        return ResponseEntity.status(200).body("Media Updated");

    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteLandlord(@AuthenticationPrincipal User user){
        mediaService.deleteMedia(user.getId());
        return ResponseEntity.status(200).body("Media deleted");
    }
}
