package com.pruebatecnica.Sistema.service;

import com.pruebatecnica.Sistema.model.Factura;
import com.pruebatecnica.Sistema.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Optional<Factura> obtenerPorFacNum(String facNum) {
        return facturaRepository.findByFacNum(facNum);
    }

    public void guardarFactura(Factura factura) {
        facturaRepository.save(factura);
    }
}
