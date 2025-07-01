package com.petpass.controller;


import com.petpass.model.Mascota;
import com.petpass.model.Dueno;
import com.petpass.service.MascotaService;
import com.petpass.service.DuenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.List;



@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private DuenoService duenoService;

    @PostMapping
    public ResponseEntity<?> crearMascota(@RequestBody Mascota mascota) {
        try {
            System.out.println("Mascota recibida: " + mascota.getNombre() + " - Dueño ID: " + mascota.getDueno().getId());
            Optional<Dueno> duenoOpt = duenoService.obtenerDuenoPorId(mascota.getDueno().getId());
            if (duenoOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dueño no encontrado");
            }

            mascota.setDueno(duenoOpt.get());
            Mascota nueva = mascotaService.guardarMascota(mascota);
            return ResponseEntity.ok(nueva);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar mascota: " + e.getMessage());
        }
    }

    @GetMapping("/dueno/{id}")
    public ResponseEntity<?> obtenerMascotasPorDueno(@PathVariable Long id) {
        try {
            List<Mascota> mascotas = mascotaService.obtenerMascotasPorDuenoId(id);
            return ResponseEntity.ok(mascotas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener mascotas: " + e.getMessage());
        }
    }

}
