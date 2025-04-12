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

import com.pruebatecnica.Sistema.dto.MovimientoKardexDTO;
import com.pruebatecnica.Sistema.model.FacturaKardex;
import com.pruebatecnica.Sistema.service.ArticuloService;
import com.pruebatecnica.Sistema.service.FacturaKardexService;
import com.pruebatecnica.Sistema.service.FacturaService;

@RestController
@RequestMapping("/api/kardex")
@CrossOrigin(origins = "*")
public class FacturaKardexController {

    private final FacturaKardexService facturaKardexService;
    private final ArticuloService articuloService;
    private final FacturaService facturaService;

    public FacturaKardexController(FacturaKardexService facturaKardexService,
                                 ArticuloService articuloService,
                                 FacturaService facturaService) {
        this.facturaKardexService = facturaKardexService;
        this.articuloService = articuloService;
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<FacturaKardex> obtenerTodosLosMovimientos() {
        return facturaKardexService.obtenerTodos();
    }

    @GetMapping("/articulo/{codigo}")
    public ResponseEntity<List<FacturaKardex>> obtenerMovimientosPorArticulo(@PathVariable String codigo) {
        List<FacturaKardex> movimientos = facturaKardexService.obtenerPorCodigoArticulo(codigo);
        return movimientos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movimientos);
    }

    @GetMapping("/entradas/{artCod}")
    public ResponseEntity<List<FacturaKardex>> obtenerEntradasPorArticulo(@PathVariable String artCod) {
        List<FacturaKardex> entradas = facturaKardexService.obtenerEntradasOrdenadasPorFecha(artCod);
        return entradas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(entradas);
    }

    @PostMapping
    public ResponseEntity<String> guardarMovimiento(@RequestBody MovimientoKardexDTO movimientoDTO) {
        try {
            FacturaKardex movimiento = convertirDTOaEntidad(movimientoDTO);
            facturaKardexService.guardarMovimiento(movimiento);
            return ResponseEntity.ok("Movimiento registrado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntrada(@RequestBody MovimientoKardexDTO movimientoDTO) {
        try {
            FacturaKardex entrada = convertirDTOaEntidad(movimientoDTO);
            facturaKardexService.registrarEntradaInventario(entrada);
            return ResponseEntity.ok("Entrada de inventario registrada exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/salida")
    public ResponseEntity<String> registrarSalida(@RequestBody MovimientoKardexDTO movimientoDTO) {
        try {
            FacturaKardex salida = convertirDTOaEntidad(movimientoDTO);
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

    private FacturaKardex convertirDTOaEntidad(MovimientoKardexDTO dto) {
        FacturaKardex movimiento = new FacturaKardex();
        movimiento.setArticulo(dto.getArticulo());
        movimiento.setFactura(dto.getFactura());
        movimiento.setKarCantEnt(dto.getKarCantEnt());
        movimiento.setKarCantSal(dto.getKarCantSal());
        movimiento.setKarCosUnit(dto.getKarCosUnit());
        movimiento.setKarPreVen(dto.getKarPreVen());
        movimiento.setKarFecVencProd(dto.getKarFecVencProd());
        return movimiento;
    }
}