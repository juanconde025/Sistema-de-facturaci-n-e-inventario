package com.pruebatecnica.Sistema.service;

import com.pruebatecnica.Sistema.model.Nit;
import com.pruebatecnica.Sistema.repository.NitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NitService {

    private final NitRepository nitRepository;

    public NitService(NitRepository nitRepository) {
        this.nitRepository = nitRepository;
    }

    public Optional<Nit> obtenerPorNitDoc(String nitDoc) {
        return nitRepository.findByNitDoc(nitDoc);
    }

    public void guardarNit(Nit nit) {
        nitRepository.save(nit);
    }
}
