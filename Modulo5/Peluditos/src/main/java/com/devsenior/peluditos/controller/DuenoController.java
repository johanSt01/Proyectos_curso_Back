package com.devsenior.peluditos.controller;

import com.devsenior.peluditos.DTO.DuenoRequest;
import com.devsenior.peluditos.DTO.DuenoResponse;
import com.devsenior.peluditos.service.Interfaces.DuenoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
@AllArgsConstructor
public class DuenoController {

    private final DuenoService duenoService;

    @GetMapping
    public ResponseEntity<List<DuenoResponse>> listarTodos() {
        return ResponseEntity.ok(duenoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuenoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(duenoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DuenoResponse> crear(@Valid @RequestBody DuenoRequest request) {
        DuenoResponse creado = duenoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DuenoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DuenoRequest request) {
        return ResponseEntity.ok(duenoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        duenoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
