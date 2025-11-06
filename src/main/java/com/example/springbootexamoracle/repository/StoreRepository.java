package com.example.springbootexamoracle.repository;

import com.example.springbootexamoracle.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByOrderByFirstNameAsc();
}
