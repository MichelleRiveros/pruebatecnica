package com.telefonica;

import com.telefonica.modules.estudiante.adapter.in.EstudianteConsoleAdapter;
import com.telefonica.modules.estudiante.adapter.out.EstudianteRepositoryMySQLRepository;
import com.telefonica.modules.estudiante.application.EstudianteService;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/pruebatecnica";
        String user = "root";
        String password = "1234";

        EstudianteRepositoryMySQLRepository estudianteRepositoryMySQLRepository = new EstudianteRepositoryMySQLRepository(url, user, password);
        EstudianteService estudianteService = new EstudianteService(estudianteRepositoryMySQLRepository);
        EstudianteConsoleAdapter estudianteConsoleAdapter = new EstudianteConsoleAdapter(estudianteService);
        estudianteConsoleAdapter.star();
    }
}