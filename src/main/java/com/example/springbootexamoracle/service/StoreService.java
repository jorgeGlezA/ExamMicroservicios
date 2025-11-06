package com.example.springbootexamoracle.service;

import com.example.springbootexamoracle.dto.StoreDTO;
import com.example.springbootexamoracle.entity.Store;
import com.example.springbootexamoracle.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreService {

    private final StoreRepository repository;

    public StoreService(StoreRepository repository) {
        this.repository = repository;
    }

    public List<StoreDTO> findAllOrderedByFirstName() {
        return repository.findAllByOrderByFirstNameAsc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public StoreDTO save(StoreDTO dto) {
        Store s = toEntity(dto);
        Store saved = repository.save(s);
        return toDto(saved);
    }

    public List<StoreDTO> saveAll(List<StoreDTO> dtos) {
        List<Store> entities = dtos.stream().map(this::toEntity).collect(Collectors.toList());
        List<Store> saved = repository.saveAll(entities);
        return saved.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<StoreDTO> update(Long id, StoreDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            existing.setEmail(dto.getEmail());
            existing.setPhoneNumbers(dto.getPhoneNumbers());
            return toDto(repository.save(existing));
        });
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<StoreDTO> findWithEmptyPhoneNumbers() {
        return repository.findAll()
                .stream()
                .filter(s -> s.getPhoneNumbers() == null || s.getPhoneNumbers().isEmpty())
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private StoreDTO toDto(Store s) {
        StoreDTO dto = new StoreDTO();
        dto.setId(s.getId());
        dto.setFirstName(s.getFirstName());
        dto.setLastName(s.getLastName());
        dto.setEmail(s.getEmail());
        dto.setPhoneNumbers(s.getPhoneNumbers());
        return dto;
    }

    private Store toEntity(StoreDTO dto) {
        Store s = new Store();
        s.setId(dto.getId());
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        s.setPhoneNumbers(dto.getPhoneNumbers());
        return s;
    }
}
