package com.telefonica.modules.estudiante.application;

import java.util.List;
import java.util.Optional;
import com.telefonica.modules.estudiante.domain.Estudiante;
import com.telefonica.modules.estudiante.infrastructure.EstudianteRepository;

public class EstudianteService {
    private EstudianteRepository estudianteRepository;

    public EstudianteService (){}

    public EstudianteService(EstudianteRepository estudianteRepository){
        this.estudianteRepository = estudianteRepository;
    }

    public void saveEstudiante(Estudiante estudiante){
        estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(int id){
        estudianteRepository.delete(id);
    }

    public void upDateEstudiante(Estudiante estudiante){
        estudianteRepository.upDate(estudiante);
    }

    public Optional<Estudiante> findByIdEstudiante(int id){
        return estudianteRepository.findById(id);
    }

    public List<Estudiante> findAllEstudiantes(){
        return estudianteRepository.findAll();
    }
}
