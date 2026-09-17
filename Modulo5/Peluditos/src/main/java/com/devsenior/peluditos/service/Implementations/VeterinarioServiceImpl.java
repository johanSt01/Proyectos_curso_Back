package com.devsenior.peluditos.service.Implementations;

import com.devsenior.peluditos.DTO.VeterinarioRequest;
import com.devsenior.peluditos.DTO.VeterinarioResponse;
import com.devsenior.peluditos.exception.RecursoNoEncontradoException;
import com.devsenior.peluditos.model.Veterinario;
import com.devsenior.peluditos.repository.VeterinarioRepository;
import com.devsenior.peluditos.service.Interfaces.VeterinarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public List<VeterinarioResponse> listarTodos() {
        return veterinarioRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public VeterinarioResponse buscarPorId(Long id) {
        Veterinario vet = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un veterinario con id " + id));
        return toResponse(vet);
    }

    @Override
    public VeterinarioResponse crear(VeterinarioRequest request) {
        Veterinario vet = new Veterinario();
        vet.setNombre(request.nombre());
        vet.setEspecialidad(request.especialidad());
        vet.setTarjetaProfesional(request.tarjetaProfesional());

        Veterinario guardado = veterinarioRepository.save(vet);
        return toResponse(guardado);
    }

    @Override
    public VeterinarioResponse actualizar(Long id, VeterinarioRequest request) {
        Veterinario vet = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un veterinario con id " + id));

        vet.setNombre(request.nombre());
        vet.setEspecialidad(request.especialidad());
        vet.setTarjetaProfesional(request.tarjetaProfesional());

        Veterinario actualizado = veterinarioRepository.save(vet);
        return toResponse(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!veterinarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe un veterinario con id " + id);
        }
        veterinarioRepository.deleteById(id);
    }

    private VeterinarioResponse toResponse(Veterinario vet) {
        return new VeterinarioResponse(
                vet.getId(), vet.getNombre(), vet.getEspecialidad(), vet.getTarjetaProfesional()
        );
    }
}
