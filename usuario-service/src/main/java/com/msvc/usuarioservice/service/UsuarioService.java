package com.msvc.usuarioservice.service;

import com.msvc.usuarioservice.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    List<Usuario> getAllUsuarios();

    Usuario getUsuario(String usuarioId);
}
