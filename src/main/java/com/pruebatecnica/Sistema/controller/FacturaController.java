package com.pruebatecnica.Sistema.controller;

import com.pruebatecnica.Sistema.model.Factura;
import com.pruebatecnica.Sistema.model.Nit;
import com.pruebatecnica.Sistema.service.FacturaService;
import com.pruebatecnica.Sistema.service.NitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;
    private final NitService nitService;

    public FacturaController(FacturaService facturaService, NitService nitService) {
        this.facturaService = facturaService;
        this.nitService = nitService;
    }

    @GetMapping("/info/{nitDoc}")
    public ResponseEntity<?> obtenerInfoPorNit(@PathVariable String nitDoc) {
        Optional<Nit> nitOpt = nitService.obtenerPorNitDoc(nitDoc);

        if (nitOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Nit nit = nitOpt.get();
        Optional<Factura> facturaProxima = facturaService.obtenerFacturaProximaPorNit(nitDoc);

        Map<String, Object> response = new HashMap<>();
        response.put("nit", nit);
        response.put("facturaProxima", facturaProxima.orElse(null));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarFactura(@RequestBody Factura factura) {
        facturaService.guardarFactura(factura);
        return ResponseEntity.ok("Factura guardada exitosamente.");
    }

    @GetMapping("/{facNum}")
    public ResponseEntity<Factura> obtenerFactura(@PathVariable String facNum) {
        return facturaService.obtenerPorFacNum(facNum)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{facNum}")
    public ResponseEntity<String> eliminarFactura(@PathVariable String facNum) {
        Optional<Factura> facturaOpt = facturaService.obtenerPorFacNum(facNum);
        if (facturaOpt.isPresent()) {
            facturaService.eliminarFactura(facNum);
            return ResponseEntity.ok("Factura eliminada exitosamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
