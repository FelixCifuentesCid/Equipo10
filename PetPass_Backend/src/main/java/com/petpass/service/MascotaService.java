package com.petpass.service;

import com.petpass.model.Mascota;
import com.petpass.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> obtenerMascotasPorDuenoId(Long duenoId) {
        return mascotaRepository.findByDuenoId(duenoId);
    }
}

