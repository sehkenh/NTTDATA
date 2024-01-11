package com.msvc.usuarioservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "id")
    private String usuarioId;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "Telefono")
    private String telefono;
}
