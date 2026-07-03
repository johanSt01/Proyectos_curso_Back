package com.devsenior.peluditos.service.Implementations;

import com.devsenior.peluditos.model.Dueno;
import com.devsenior.peluditos.repository.DuenoRepository;
import com.devsenior.peluditos.service.Interfaces.DuenoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DuenoServiceImpl implements DuenoService {

    private final DuenoRepository duenoRepository;
    
    @Override
    public List<Dueno> listarTodos() {
        return duenoRepository.findAll();
    }
}
