package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.Model.Landlord;
import com.example.makhzan.Model.Storage;
import com.example.makhzan.Repository.LandLordRepository;
import com.example.makhzan.Repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final LandLordRepository landLordRepository;
    public List<Storage> getStorages(){
        return storageRepository.findAll();
    }

    public void addStorage(Storage storage, Integer userid){
        Landlord landlord = landLordRepository.findLandLordById(userid);
        if(landlord == null) throw  new ApiException("User not found");
        storage.setLandlord(landlord);
        storageRepository.save(storage);
    }

    public void updateStorage(Integer id, Integer userid, Storage storage){
        Storage oldStorage = storageRepository.findStorageById(id);
        if(oldStorage == null) throw  new ApiException("Storage not found");
        Landlord landlord = landLordRepository.findLandLordById(userid);
        if (landlord == null) throw new ApiException("User not found");
        if(!oldStorage.getLandlord().equals(landlord)) throw new ApiException("User is not allowed to update");
        storage.setId(id);
        storageRepository.save(storage);
    }

    public void deleteStorage(Integer id , Integer userid){
        Storage storage = storageRepository.findStorageById(id);
        if(storage == null) throw  new ApiException("Storage not found");
        Landlord landlord = landLordRepository.findLandLordById(userid);
        if(landlord == null) throw  new ApiException("User not found");
        if(!storage.getLandlord().equals(landlord)) throw new ApiException("User not allowed to delete");
        storageRepository.delete(storage);
    }
}
