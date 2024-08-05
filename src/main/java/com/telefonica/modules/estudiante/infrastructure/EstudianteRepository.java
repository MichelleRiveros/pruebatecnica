package com.telefonica.modules.estudiante.infrastructure;
import com.telefonica.modules.estudiante.domain.Estudiante;
import java.util.List;
import java.util.Optional;

public interface EstudianteRepository {
    void save (Estudiante estudiante);
    void delete(int id);
    void upDate(Estudiante estudiante);
    Optional<Estudiante> findById(int id);
    List<Estudiante> findAll();
}
