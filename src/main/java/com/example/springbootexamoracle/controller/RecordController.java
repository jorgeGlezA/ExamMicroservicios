package com.example.springbootexamoracle.controller;

import com.example.springbootexamoracle.dto.StoreDTO;
import com.example.springbootexamoracle.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final StoreService service;

    public RecordController(StoreService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getWithEmptyPhoneNumbers() {
        List<StoreDTO> list = service.findWithEmptyPhoneNumbers();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public ResponseEntity<List<StoreDTO>> saveAll(@RequestBody List<StoreDTO> dtos) {
        List<StoreDTO> saved = service.saveAll(dtos);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/customers/test")
    public ResponseEntity<List<StoreDTO>> getAllOrdered() {
        List<StoreDTO> list = service.findAllOrderedByFirstName();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/customers/test")
    public ResponseEntity<StoreDTO> create(@RequestBody StoreDTO dto) {
        StoreDTO created = service.save(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/customers/{id}/test")
    public ResponseEntity<StoreDTO> update(@PathVariable("id") Long id, @RequestBody StoreDTO dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/customers/{id}/test")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
