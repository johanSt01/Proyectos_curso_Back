package com.devsenior.peluditos.service.Implementations;

import com.devsenior.peluditos.DTO.AtencionRequest;
import com.devsenior.peluditos.DTO.CitaRequest;
import com.devsenior.peluditos.DTO.CitaResponse;
import com.devsenior.peluditos.exception.RecursoNoEncontradoException;
import com.devsenior.peluditos.exception.ReglaNegocioException;
import com.devsenior.peluditos.model.Cita;
import com.devsenior.peluditos.model.Dueno;
import com.devsenior.peluditos.model.EstadoCita;
import com.devsenior.peluditos.model.Mascota;
import com.devsenior.peluditos.model.Usuario;
import com.devsenior.peluditos.model.Veterinario;
import com.devsenior.peluditos.repository.CitaRepository;
import com.devsenior.peluditos.repository.MascotaRepository;
import com.devsenior.peluditos.repository.VeterinarioRepository;
import com.devsenior.peluditos.service.Interfaces.CitaService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaServiceImpl(CitaRepository citaRepository,
                           MascotaRepository mascotaRepository,
                           VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public List<CitaResponse> listarTodos() {
        return citaRepository.findAllByOrderByFechaAscHoraAsc()
                .stream().map(this::toResponse).toList();
    }

    // El backend decide QUE citas ve el veterinario, a partir del token
    @Override
    public List<CitaResponse> listarMias(String username) {
        return citaRepository.findByVeterinarioUsuarioUsernameOrderByFechaAscHoraAsc(username)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public List<CitaResponse> listarPorMascota(Long mascotaId) {
        if (!mascotaRepository.existsById(mascotaId)) {
            throw new RecursoNoEncontradoException("No existe una mascota con id " + mascotaId);
        }
        return citaRepository.findByMascotaIdOrderByFechaDescHoraDesc(mascotaId)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public CitaResponse buscarPorId(Long id) {
        return toResponse(buscarEntidad(id));
    }

    @Override
    public CitaResponse crear(CitaRequest request) {
        Cita cita = new Cita();
        aplicarDatos(cita, request);
        cita.setEstado(EstadoCita.PROGRAMADA);   // toda cita nueva arranca programada
        return toResponse(citaRepository.save(cita));
    }

    @Override
    public CitaResponse actualizar(Long id, CitaRequest request) {
        Cita cita = buscarEntidad(id);
        if (cita.getEstado() != EstadoCita.PROGRAMADA) {
            throw new ReglaNegocioException("Solo se puede reprogramar una cita que siga programada");
        }
        aplicarDatos(cita, request);
        return toResponse(citaRepository.save(cita));
    }

    @Override
    public CitaResponse atender(Long id, AtencionRequest request, String username) {
        Cita cita = buscarEntidad(id);

        // Regla 1: un veterinario solo atiende SUS citas
        Usuario cuentaDelVeterinario = cita.getVeterinario() == null
                ? null : cita.getVeterinario().getUsuario();
        if (cuentaDelVeterinario == null || !cuentaDelVeterinario.getUsername().equals(username)) {
            throw new AccessDeniedException("Esta cita no esta asignada a ti");
        }

        // Regla 2: solo se atiende lo que sigue programado
        if (cita.getEstado() != EstadoCita.PROGRAMADA) {
            throw new ReglaNegocioException("La cita ya fue " + cita.getEstado().name().toLowerCase());
        }

        cita.setNotas(request.notas());
        cita.setCosto(request.costo());
        cita.setEstado(EstadoCita.ATENDIDA);
        return toResponse(citaRepository.save(cita));
    }

    @Override
    public CitaResponse cancelar(Long id) {
        Cita cita = buscarEntidad(id);
        if (cita.getEstado() != EstadoCita.PROGRAMADA) {
            throw new ReglaNegocioException("Solo se puede cancelar una cita programada");
        }
        cita.setEstado(EstadoCita.CANCELADA);
        return toResponse(citaRepository.save(cita));
    }

    @Override
    public void eliminar(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe una cita con id " + id);
        }
        citaRepository.deleteById(id);
    }

    // --- Ayudantes privados ---

    private Cita buscarEntidad(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una cita con id " + id));
    }

    private void aplicarDatos(Cita cita, CitaRequest request) {
        Mascota mascota = mascotaRepository.findById(request.mascotaId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una mascota con id " + request.mascotaId()));

        Veterinario veterinario = veterinarioRepository.findById(request.veterinarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un veterinario con id " + request.veterinarioId()));

        cita.setFecha(request.fecha());
        cita.setHora(request.hora());
        cita.setMotivo(request.motivo());
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
    }

    private CitaResponse toResponse(Cita cita) {
        Mascota mascota = cita.getMascota();
        Dueno dueno = mascota.getDueno();
        Veterinario vet = cita.getVeterinario();
        return new CitaResponse(
                cita.getId(), cita.getFecha(), cita.getHora(), cita.getMotivo(),
                cita.getEstado() == null ? null : cita.getEstado().name(),
                cita.getCosto(), cita.getNotas(),
                mascota.getId(), mascota.getNombre(), mascota.getEspecie(),
                dueno.getNombre() + " " + dueno.getApellido(),
                vet == null ? null : vet.getId(),
                vet == null ? "Sin asignar" : vet.getNombre()
        );
    }
}