package com.pruebatecnica.Sistema.controller;

import com.pruebatecnica.Sistema.model.Articulo;
import com.pruebatecnica.Sistema.model.Factura;
import com.pruebatecnica.Sistema.model.FacturaKardex;
import com.pruebatecnica.Sistema.repository.ArticuloRepository;
import com.pruebatecnica.Sistema.repository.FacturaRepository;
import com.pruebatecnica.Sistema.service.FacturaKardexService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kardex")
public class FacturaKardexController {

    private final FacturaKardexService kardexService;
    private final FacturaRepository facturaRepository;
    private final ArticuloRepository articuloRepository;

    public FacturaKardexController(FacturaKardexService kardexService, FacturaRepository facturaRepository, ArticuloRepository articuloRepository) {
        this.kardexService = kardexService;
        this.facturaRepository = facturaRepository;
        this.articuloRepository = articuloRepository;
    }

    @GetMapping
    public List<FacturaKardex> obtenerTodos() {
        return kardexService.obtenerTodos();
    }

    @DeleteMapping("/{facNum}")
    public void eliminarPorFactura(@PathVariable String facNum) {
        kardexService.eliminarPorFactura(facNum);
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarMovimiento(@RequestBody FacturaKardex kardex) {
        Articulo articulo = kardex.getArticulo();
        String codArticulo = articulo.getArtCod();

        String facNum = kardex.getFactura().getFacNum();
        Optional<Factura> facturaOptional = facturaRepository.findByFacNum(facNum);
        if (facturaOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Factura no encontrada: " + facNum);
        }

        Factura factura = facturaOptional.get();

        if (kardex.getKarCantSal() > 0) {
            int cantidadPorSacar = kardex.getKarCantSal();
            int cantidadSacada = 0;

            List<FacturaKardex> entradas = kardexService.obtenerEntradasOrdenadasPorFecha(codArticulo);

            for (FacturaKardex entrada : entradas) {
                int disponible = entrada.getKarSaldo();
                if (disponible <= 0) continue;

                int cantidadUsada = Math.min(cantidadPorSacar, disponible);
                entrada.setKarSaldo(disponible - cantidadUsada);
                kardexService.guardarMovimiento(entrada);

                FacturaKardex salida = new FacturaKardex();
                salida.setFactura(factura);
                salida.setArticulo(articulo);
                salida.setKarCantInit(articulo.getArtSal());
                salida.setKarCantEnt(0);
                salida.setKarCantSal(cantidadUsada);
                salida.setKarSaldo(0); 
                salida.setKarFecVencProd(entrada.getKarFecVencProd());

                kardexService.guardarMovimiento(salida);

                cantidadSacada += cantidadUsada;
                cantidadPorSacar -= cantidadUsada;

                if (cantidadPorSacar == 0) break;
            }

            if (cantidadPorSacar > 0) {
                return ResponseEntity.badRequest().body("No hay suficiente inventario disponible para la salida.");
            }

            articulo.setArtSal(articulo.getArtSal() - cantidadSacada);
            articuloRepository.save(articulo);

            return ResponseEntity.ok("Salida registrada correctamente bajo lógica PEPS.");
        }

        if (kardex.getKarCantEnt() > 0) {
            kardex.setFactura(factura);
            kardex.setKarCantSal(0);
            kardex.setKarCantInit(articulo.getArtSal());
            int nuevoSaldo = articulo.getArtSal() + kardex.getKarCantEnt();
            kardex.setKarSaldo(nuevoSaldo);
            articulo.setArtSal(nuevoSaldo);
            articuloRepository.save(articulo);

            kardexService.guardarMovimiento(kardex);
            return ResponseEntity.ok("Entrada registrada correctamente.");
        }

        return ResponseEntity.badRequest().body("Debe especificar una cantidad de entrada o salida válida.");
    }
}
