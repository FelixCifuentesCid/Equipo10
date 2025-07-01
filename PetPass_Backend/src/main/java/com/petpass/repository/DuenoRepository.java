package com.petpass.repository;

import com.petpass.model.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Long> {
    // Método para validar login
    Dueno findByCorreoAndContrasena(String correo, String contrasena);
}
