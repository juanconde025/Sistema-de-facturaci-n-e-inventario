package com.pruebatecnica.Sistema.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.Sistema.model.Nit;

@Repository
public interface NitRepository extends JpaRepository<Nit, String> {
    Optional<Nit> findByNitDoc(String nitDoc);
}

