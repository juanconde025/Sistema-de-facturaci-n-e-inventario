package com.pruebatecnica.Sistema.controller;

import com.pruebatecnica.Sistema.model.Nit;
import com.pruebatecnica.Sistema.service.NitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/nits")
@CrossOrigin(origins = "*")
public class NitController {

    private final NitService nitService;

    public NitController(NitService nitService) {
        this.nitService = nitService;
    }

    @GetMapping("/{nitDoc}")
    public ResponseEntity<Nit> obtenerPorNitDoc(@PathVariable("nitDoc") String nitDoc) {
        Optional<Nit> nit = nitService.obtenerPorNitDoc(nitDoc);
        return nit.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> guardarNit(@RequestBody Nit nit) {
        nitService.guardarNit(nit);
        return ResponseEntity.ok("Cliente guardado correctamente.");
    }

    @DeleteMapping("/{nitDoc}")
    public ResponseEntity<String> eliminarNit(@PathVariable String nitDoc) {
        Optional<Nit> nit = nitService.obtenerPorNitDoc(nitDoc);
        if (nit.isPresent()) {
            nitService.eliminarCliente(nit.get());
            return ResponseEntity.ok("Cliente eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
