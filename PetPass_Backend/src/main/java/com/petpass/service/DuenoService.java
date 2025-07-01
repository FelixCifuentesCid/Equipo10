package com.petpass.service;

import com.petpass.model.Dueno;
import com.petpass.repository.DuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DuenoService {

    @Autowired
    private DuenoRepository duenoRepository;

    // Crear dueño
    public Dueno crearDueno(Dueno dueno) {
        return duenoRepository.save(dueno);
    }

    // Obtener todos los dueños
    public List<Dueno> obtenerTodosLosDuenos() {
        return duenoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Dueno> obtenerDuenoPorId(Long id) {
        return duenoRepository.findById(id);
    }

    // Eliminar por ID
    public void eliminarDueno(Long id) {
        duenoRepository.deleteById(id);
    }

    // Actualizar
    public Dueno actualizarDueno(Long id, Dueno duenoActualizado) {
        return duenoRepository.findById(id).map(dueno -> {
            dueno.setNombre(duenoActualizado.getNombre());
            dueno.setCorreo(duenoActualizado.getCorreo());
            dueno.setTelefono(duenoActualizado.getTelefono());
            dueno.setContrasena(duenoActualizado.getContrasena());
            dueno.setTipo(duenoActualizado.getTipo()); // 👈 Agregado
            return duenoRepository.save(dueno);
        }).orElse(null);
    }

    public Dueno validarLogin(String email, String contrasena) {
        return duenoRepository.findByCorreoAndContrasena(email, contrasena);
    }
}


