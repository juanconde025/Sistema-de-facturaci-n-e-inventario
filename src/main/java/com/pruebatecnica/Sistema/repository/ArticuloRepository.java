package com.pruebatecnica.Sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.Sistema.model.Articulo;
import java.util.Optional;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, String> {
    Optional<Articulo> findByArtCod(String artCod);
}

