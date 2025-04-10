package com.pruebatecnica.Sistema.repository;

import com.pruebatecnica.Sistema.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, String> {
    Optional<Factura> findByFacNum(String facNum); 
}
