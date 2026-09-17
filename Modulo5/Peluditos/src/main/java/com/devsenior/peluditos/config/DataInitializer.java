package com.devsenior.peluditos.config;

import com.devsenior.peluditos.model.Dueno;
import com.devsenior.peluditos.model.Mascota;
import com.devsenior.peluditos.repository.DuenoRepository;
import com.devsenior.peluditos.repository.MascotaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Carga datos de prueba SOLO si la tabla de mascotas esta vacia.
// Pensado para la Clase 4 (consumo de APIs): asi el GET /api/mascotas
// no llega vacio la primera vez que el frontend lo consuma.
@Component
public class DataInitializer implements CommandLineRunner {

    private final DuenoRepository duenoRepository;
    private final MascotaRepository mascotaRepository;

    public DataInitializer(DuenoRepository duenoRepository, MascotaRepository mascotaRepository) {
        this.duenoRepository = duenoRepository;
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public void run(String... args) {
        if (mascotaRepository.count() > 0) {
            return;
        }

        Dueno anaTorres = new Dueno();
        anaTorres.setNombre("Ana");
        anaTorres.setApellido("Torres");
        anaTorres.setDocumento("1000000001");
        anaTorres.setTelefono("3001234567");
        anaTorres.setEmail("ana.torres@example.com");
        duenoRepository.save(anaTorres);

        Dueno luisPerez = new Dueno();
        luisPerez.setNombre("Luis");
        luisPerez.setApellido("Pérez");
        luisPerez.setDocumento("1000000002");
        luisPerez.setTelefono("3007654321");
        luisPerez.setEmail("luis.perez@example.com");
        duenoRepository.save(luisPerez);

        Mascota firulais = new Mascota();
        firulais.setNombre("Firulais");
        firulais.setEspecie("Perro");
        firulais.setRaza("Criollo");
        firulais.setEdad(4);
        firulais.setDueno(anaTorres);
        mascotaRepository.save(firulais);

        Mascota michi = new Mascota();
        michi.setNombre("Michi");
        michi.setEspecie("Gato");
        michi.setRaza("Siames");
        michi.setEdad(2);
        michi.setDueno(luisPerez);
        mascotaRepository.save(michi);

        Mascota rocky = new Mascota();
        rocky.setNombre("Rocky");
        rocky.setEspecie("Perro");
        rocky.setRaza("Labrador");
        rocky.setEdad(6);
        rocky.setDueno(anaTorres);
        mascotaRepository.save(rocky);
    }
}
