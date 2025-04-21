package com.pruebatecnica.Sistema.service;

import com.pruebatecnica.Sistema.model.Articulo;
import com.pruebatecnica.Sistema.repository.ArticuloRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public Optional<Articulo> obtenerPorArtCod(String artCod) {
        return articuloRepository.findByArtCod(artCod);
    }

    public void guardarArticulo(Articulo articulo) {
        articuloRepository.save(articulo);
    }

    public void eliminarArticulo(Articulo articulo){
        articuloRepository.delete(articulo);
    }
}
