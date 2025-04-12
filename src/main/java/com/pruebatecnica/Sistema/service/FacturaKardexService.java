package com.pruebatecnica.Sistema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pruebatecnica.Sistema.model.Articulo;
import com.pruebatecnica.Sistema.model.FacturaKardex;
import com.pruebatecnica.Sistema.repository.FacturaKardexRepository;

@Service
public class FacturaKardexService {

    private final FacturaKardexRepository facturaKardexRepository;

    public FacturaKardexService(FacturaKardexRepository facturaKardexRepository) {
        this.facturaKardexRepository = facturaKardexRepository;
    }

    public List<FacturaKardex> obtenerTodos() {
        return facturaKardexRepository.findAll();
    }

    public void guardarMovimiento(FacturaKardex kardex) {
        if (kardex.getArticulo() == null) {
            throw new IllegalArgumentException("El artículo no puede ser nulo.");
        }
        facturaKardexRepository.save(kardex);
    }

    public void eliminarPorFactura(String facNum) {
        facturaKardexRepository.deleteByFactura_FacNum(facNum);
    }

    public void registrarEntradaInventario(FacturaKardex entrada) {
        if (entrada.getArticulo() == null || entrada.getFactura() == null) {
            throw new IllegalArgumentException("Artículo y Factura son requeridos");
        }

        Articulo articulo = entrada.getArticulo();
        entrada.setKarCantInit(articulo.getArtSal());
        entrada.setKarCantSal(0);
        entrada.setKarSaldo(articulo.getArtSal() + entrada.getKarCantEnt());

        articulo.setArtSal(entrada.getKarSaldo());
        
        facturaKardexRepository.save(entrada);
    }

    public void registrarSalidaInventario(FacturaKardex salida, int cantidadASacar) {
        if (salida.getArticulo() == null || salida.getFactura() == null) {
            throw new IllegalArgumentException("Artículo y Factura son requeridos");
        }

        Articulo articulo = salida.getArticulo();
        salida.setKarCantInit(articulo.getArtSal());
        salida.setKarCantEnt(0);
        salida.setKarSaldo(articulo.getArtSal() - salida.getKarCantSal());

        articulo.setArtSal(salida.getKarSaldo());
        
        facturaKardexRepository.save(salida);
    }

    public List<FacturaKardex> obtenerEntradasOrdenadasPorFecha(String artCod) {
        return facturaKardexRepository
                .findByArticulo_ArtCodAndKarCantEntGreaterThanOrderByFactura_FacFVenAsc(artCod, 0);
    }

    public List<FacturaKardex> obtenerPorCodigoArticulo(String codigo) {
        return facturaKardexRepository.findByArticulo_ArtCod(codigo);
    }
}
