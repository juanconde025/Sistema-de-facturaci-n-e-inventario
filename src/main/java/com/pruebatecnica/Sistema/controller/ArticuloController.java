package com.pruebatecnica.Sistema.controller;

import com.pruebatecnica.Sistema.model.Articulo;
import com.pruebatecnica.Sistema.model.Nit;
import com.pruebatecnica.Sistema.service.ArticuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*") 
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Articulo> obtenerPorCodigo(@PathVariable("codigo") String codigo) {
        Optional<Articulo> articulo = articuloService.obtenerPorArtCod(codigo);
        return articulo.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> guardarArticulo(@RequestBody Articulo articulo) {
        if (articulo.getArtPrV() < articulo.getArtCos()) {
            return ResponseEntity.badRequest().body("El precio de venta debe ser mayor al costo.");
        }

        articuloService.guardarArticulo(articulo);
        return ResponseEntity.ok("Artículo guardado correctamente.");
    }

    @DeleteMapping("/{artCod}")
    public ResponseEntity<String> eliminarArt(@PathVariable String artCod) {
        Optional<Articulo> art = articuloService.obtenerPorArtCod(artCod);
        if (art.isPresent()) {
            articuloService.eliminarArticulo(art.get());
            return ResponseEntity.ok("Artículo eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
