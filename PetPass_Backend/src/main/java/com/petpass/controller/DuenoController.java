package com.petpass.controller;

import com.petpass.model.Dueno;
import com.petpass.service.DuenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/duenos")
@CrossOrigin(origins = "*") // Aplica globalmente a todos los endpoints aquí
public class DuenoController {

    @Autowired
    private DuenoService duenoService;

    // ✅ Login (con CORS explícito por si acaso)
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public Object login(@RequestBody Dueno datosLogin) {
        String correo = datosLogin.getCorreo();
        String contrasena = datosLogin.getContrasena();

        Dueno dueno = duenoService.validarLogin(correo, contrasena);
        if (dueno != null) {
            return new Object() {
                public final String tipo = dueno.getTipo();  // puede ser DUEÑO o CLÍNICA
                public final Long id = dueno.getId();
                public final String nombre = dueno.getNombre();
            };

        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    // CRUD básico
    @PostMapping
    public Dueno crearDueno(@RequestBody Dueno dueno) {
        return duenoService.crearDueno(dueno);
    }

    @GetMapping
    public List<Dueno> obtenerTodos() {
        return duenoService.obtenerTodosLosDuenos();
    }

    @GetMapping("/{id}")
    public Optional<Dueno> obtenerPorId(@PathVariable Long id) {
        return duenoService.obtenerDuenoPorId(id);
    }

    @PutMapping("/{id}")
    public Dueno actualizar(@PathVariable Long id, @RequestBody Dueno dueno) {
        return duenoService.actualizarDueno(id, dueno);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        duenoService.eliminarDueno(id);
    }
}

