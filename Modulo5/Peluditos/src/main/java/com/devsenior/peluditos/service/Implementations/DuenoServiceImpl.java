package com.devsenior.peluditos.service.Implementations;

import com.devsenior.peluditos.DTO.DuenoRequest;
import com.devsenior.peluditos.DTO.DuenoResponse;
import com.devsenior.peluditos.exception.RecursoNoEncontradoException;
import com.devsenior.peluditos.model.Dueno;
import com.devsenior.peluditos.repository.DuenoRepository;
import com.devsenior.peluditos.service.Interfaces.DuenoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuenoServiceImpl implements DuenoService {

    private final DuenoRepository duenoRepository;

    public DuenoServiceImpl(DuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    @Override
    public List<DuenoResponse> listarTodos() {
        return duenoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public DuenoResponse buscarPorId(Long id) {
        Dueno dueno = duenoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un dueño con id " + id));
        return toResponse(dueno);
    }

    @Override
    public DuenoResponse crear(DuenoRequest request) {
        Dueno dueno = toEntity(request);
        Dueno guardado = duenoRepository.save(dueno);
        return toResponse(guardado);
    }

    @Override
    public DuenoResponse actualizar(Long id, DuenoRequest request) {
        Dueno dueno = duenoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un dueño con id " + id));

        dueno.setNombre(request.nombre());
        dueno.setApellido(request.apellido());
        dueno.setDocumento(request.documento());
        dueno.setTelefono(request.telefono());
        dueno.setEmail(request.email());

        Dueno actualizado = duenoRepository.save(dueno);
        return toResponse(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!duenoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe un dueño con id " + id);
        }
        duenoRepository.deleteById(id);
    }

    // --- Conversión DTO <-> entidad (privados, solo los usa este servicio) ---

    private Dueno toEntity(DuenoRequest request) {
        Dueno dueno = new Dueno();
        dueno.setNombre(request.nombre());
        dueno.setApellido(request.apellido());
        dueno.setDocumento(request.documento());
        dueno.setTelefono(request.telefono());
        dueno.setEmail(request.email());
        return dueno;
    }

    private DuenoResponse toResponse(Dueno dueno) {
        return new DuenoResponse(
                dueno.getId(),
                dueno.getNombre(),
                dueno.getApellido(),
                dueno.getDocumento(),
                dueno.getTelefono(),
                dueno.getEmail()
        );
    }
}