package com.pruebatecnica.Sistema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pruebatecnica.Sistema.model.Articulo;
import com.pruebatecnica.Sistema.model.Factura;
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
        facturaKardexRepository.save(kardex);
    }

    public void eliminarPorFactura(String facNum) {
        facturaKardexRepository.deleteByFactura_FacNum(facNum);
    }

    public void registrarSalidaInventario(FacturaKardex salida, int cantidadASacar) {
        Articulo articulo = salida.getArticulo();
        Factura factura = salida.getFactura();
        int cantidadRestante = cantidadASacar;

        List<FacturaKardex> entradas = facturaKardexRepository
            .findByArticulo_ArtCodAndKarSaldoGreaterThanOrderByKarFecVencProdAsc(articulo.getArtCod(), 0);


        for (FacturaKardex entrada : entradas) {
            if (cantidadRestante <= 0) break;

            int saldoDisponible = entrada.getKarSaldo();
            int cantidadUsada = Math.min(cantidadRestante, saldoDisponible);

            entrada.setKarSaldo(saldoDisponible - cantidadUsada);
            facturaKardexRepository.save(entrada);

            FacturaKardex movimientoSalida = new FacturaKardex();
            movimientoSalida.setFactura(factura);
            movimientoSalida.setArticulo(articulo);
            movimientoSalida.setKarCantInit(articulo.getArtSal());
            movimientoSalida.setKarCantEnt(0);
            movimientoSalida.setKarCantSal(cantidadUsada);
            movimientoSalida.setKarSaldo(articulo.getArtSal() - cantidadUsada);
            movimientoSalida.setKarFecVencProd(entrada.getKarFecVencProd());

            articulo.setArtSal(articulo.getArtSal() - cantidadUsada);
            facturaKardexRepository.save(movimientoSalida);

            cantidadRestante -= cantidadUsada;
        }

        if (cantidadRestante > 0) {
            throw new RuntimeException("No hay suficiente inventario disponible para esta salida.");
        }
    }

    public void registrarEntradaInventario(FacturaKardex entrada) {
        entrada.setKarCantSal(0);
        entrada.setKarSaldo(entrada.getKarCantEnt());
        entrada.setKarCantInit(entrada.getArticulo().getArtSal());

        int nuevoSaldo = entrada.getArticulo().getArtSal() + entrada.getKarCantEnt();
        entrada.getArticulo().setArtSal(nuevoSaldo);
        entrada.setKarSaldo(nuevoSaldo);

        facturaKardexRepository.save(entrada);
    }

    public List<FacturaKardex> obtenerEntradasOrdenadasPorFecha(String artCod) {
        return facturaKardexRepository
                .findByArticulo_ArtCodAndKarCantEntGreaterThanOrderByFactura_FacFVenAsc(artCod, 0);
    }
}
