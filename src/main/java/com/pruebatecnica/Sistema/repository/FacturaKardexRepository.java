package com.pruebatecnica.Sistema.repository;

import com.pruebatecnica.Sistema.model.FacturaKardex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaKardexRepository extends JpaRepository<FacturaKardex, Long> {
    
    void deleteByFactura_FacNum(String facNum);
    
    List<FacturaKardex> findByArticulo_ArtCodAndKarSaldoGreaterThanOrderByKarFecVencProdAsc(String artCod, int saldo);
    
    List<FacturaKardex> findByArticulo_ArtCodAndKarCantEntGreaterThanOrderByFactura_FacFVenAsc(String artCod, int cantidad);
}
