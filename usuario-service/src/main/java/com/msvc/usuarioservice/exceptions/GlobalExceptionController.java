package com.msvc.usuarioservice.exceptions;

import com.msvc.usuarioservice.exceptions.ResourceNotFoundException;
import com.msvc.usuarioservice.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        String mensaje = resourceNotFoundException.getMessage();

        ApiResponse response = new ApiResponse().builder()
                .message(mensaje)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
