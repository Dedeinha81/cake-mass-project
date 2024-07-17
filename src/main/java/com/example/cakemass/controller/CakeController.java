package com.example.cakemass.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cakemass.model.Cake;
import com.example.cakemass.repository.CakeRepository;

@RestController
@RequestMapping("/api/cakes")


public class CakeController {

  @Autowired  
private CakeRepository cakeRepository;

@GetMapping
@Operation(summary = "Get all cakes")
public List<Cake> getAllCakes(){
    return cakeRepository.findAll();

}

 @GetMapping("/{id}")
    @Operation(summary = "Get cake by ID")
    public ResponseEntity<Cake> getCakeById(@PathVariable Long id) {
        return cakeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
 @PostMapping
    @Operation(summary = "Create a new cake")
    public Cake createCake(@RequestBody Cake cake) {
        return cakeRepository.save(cake);
    }
@PutMapping("/{id}")
    @Operation(summary = "Update an existing cake")
    public ResponseEntity<Cake> updateCake(@PathVariable Long id, @RequestBody Cake cakeDetails) {
        return cakeRepository.findById(id)
                .map(cake -> {
                    cake.setName(cakeDetails.getName());
                    cake.setMass(cakeDetails.getMass());
                    return ResponseEntity.ok(cakeRepository.save(cake));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cake")
    public ResponseEntity<Object> deleteCake(@PathVariable Long id) {
        return cakeRepository.findById(id)
                .map(cake -> {
                    cakeRepository.delete(cake);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public CakeRepository getCakeRepository() {
        return cakeRepository;
    }

    public void setCakeRepository(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }
}
