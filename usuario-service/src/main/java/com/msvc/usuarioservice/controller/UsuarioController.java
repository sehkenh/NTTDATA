package com.msvc.usuarioservice.controller;

import com.msvc.usuarioservice.entity.Usuario;
import com.msvc.usuarioservice.response.ApiResponse;
import com.msvc.usuarioservice.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuarioRequest) {
        Usuario usuario = usuarioService.saveUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    @GetMapping
    /*@CircuitBreaker(name = serviceBreaker, fallbackMethod = "serviceFallback")*/
    /*@Retry = "serviceBreaker", fallbackMethod = serviceFallback*/
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String usuarioId) {
        Usuario usuario = usuarioService.getUsuario(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    int cantidadReitentos = 1;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        /*log.info("Listar un solo usuario: UsuarioController");
        * log.info("Cantidad de reintentos: {}", cantidadReintentos);
        * cantidadReintentos ++;*/
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    /*Circuit Breaker
    *
    * public ResponseEntity<Usuario> serviceFallback(String usuarioId,Exception exception){
    *   log.info("El respaldo se ejecuta porque el servicio esta inactivo:",exception.getMessage())};
    *   Usuario usuario = Usuario.builder()
    *           .email("root1@gmail.com")
    *           .nombre("root")
    *           .informacion("Este usuario se crea por defecto cuando el servicio esta inhabilitado")
    *           .usuarioId("1234")
    *           .build();
    * return new ResponseEntity<>(usuario,HttpStatus.OK);
    *
    */
}

