package com.pruebatecnica.Sistema.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.Sistema.model.FacturaKardex;
import com.pruebatecnica.Sistema.service.FacturaKardexService;

@RestController
@RequestMapping("/api/kardex")
@CrossOrigin(origins = "*")
public class FacturaKardexController {

    private final FacturaKardexService facturaKardexService;

    public FacturaKardexController(FacturaKardexService facturaKardexService) {
        this.facturaKardexService = facturaKardexService;
    }

    @GetMapping
    public List<FacturaKardex> obtenerTodos() {
        return facturaKardexService.obtenerTodos();
    }

    @PostMapping("/entrada")
    public void registrarEntrada(@RequestBody FacturaKardex entrada) {
        facturaKardexService.registrarEntradaInventario(entrada);
    }

    @PostMapping("/salida")
    public void registrarSalida(@RequestBody FacturaKardex salida) {
        facturaKardexService.registrarSalidaInventario(salida, salida.getKarCantSal());
    }

    @GetMapping("/entradas/{artCod}")
    public List<FacturaKardex> obtenerEntradasPorArticulo(@PathVariable String artCod) {
        return facturaKardexService.obtenerEntradasOrdenadasPorFecha(artCod);
    }
}
