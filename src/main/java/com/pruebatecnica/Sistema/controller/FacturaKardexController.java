package com.pruebatecnica.Sistema.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public List<FacturaKardex> obtenerTodosLosMovimientos() {
        return facturaKardexService.obtenerTodos();
    }

    @GetMapping("/articulo/{codigo}")
    public ResponseEntity<List<FacturaKardex>> obtenerMovimientosPorArticulo(@PathVariable String codigo) {
        List<FacturaKardex> movimientos = facturaKardexService.obtenerPorCodigoArticulo(codigo);
        if (movimientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/entradas/{artCod}")
    public ResponseEntity<List<FacturaKardex>> obtenerEntradasPorArticulo(@PathVariable String artCod) {
        List<FacturaKardex> entradas = facturaKardexService.obtenerEntradasOrdenadasPorFecha(artCod);
        if (entradas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entradas);
    }

    @PostMapping
    public ResponseEntity<String> guardarMovimiento(@RequestBody FacturaKardex movimiento) {
        try {
            facturaKardexService.guardarMovimiento(movimiento);
            return ResponseEntity.ok("Movimiento registrado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntrada(@RequestBody FacturaKardex entrada) {
        try {
            facturaKardexService.registrarEntradaInventario(entrada);
            return ResponseEntity.ok("Entrada de inventario registrada exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/salida")
    public ResponseEntity<String> registrarSalida(@RequestBody FacturaKardex salida) {
        try {
            facturaKardexService.registrarSalidaInventario(salida, salida.getKarCantSal());
            return ResponseEntity.ok("Salida de inventario registrada exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/factura/{facNum}")
    public ResponseEntity<String> eliminarPorFactura(@PathVariable String facNum) {
        try {
            facturaKardexService.eliminarPorFactura(facNum);
            return ResponseEntity.ok("Movimientos eliminados exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar movimientos: " + e.getMessage());
        }
    }
}