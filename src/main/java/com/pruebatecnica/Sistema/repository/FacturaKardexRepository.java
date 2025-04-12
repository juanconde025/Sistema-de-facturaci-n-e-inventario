package com.pruebatecnica.Sistema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.Sistema.model.FacturaKardex;

@Repository
public interface FacturaKardexRepository extends JpaRepository<FacturaKardex, Integer> {
    
    void deleteByFactura_FacNum(String facNum);
    
    List<FacturaKardex> findByArticulo_ArtCodAndKarSaldoGreaterThanOrderByKarFecVencProdAsc(String artCod, int saldo);
    
    List<FacturaKardex> findByArticulo_ArtCodAndKarCantEntGreaterThanOrderByFactura_FacFVenAsc(String artCod, int cantidad);
    
    List<FacturaKardex> findByArticulo_ArtCodOrderByFactura_FacFechaAsc(String artCod);
}