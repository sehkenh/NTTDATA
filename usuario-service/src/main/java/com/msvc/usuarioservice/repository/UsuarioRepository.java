package com.msvc.usuarioservice.repository;

import com.msvc.usuarioservice.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
