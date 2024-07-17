package com.example.cakemass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cakemass.model.Cake;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {

}
