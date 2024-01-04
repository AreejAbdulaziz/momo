package com.example.makhzan.Repository;

import com.example.makhzan.Model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Integer> {
    Storage findStorageById(Integer id);
}
