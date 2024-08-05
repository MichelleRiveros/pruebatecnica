package com.telefonica.modules.estudiante.adapter.out;

import com.telefonica.modules.estudiante.domain.Estudiante;
import com.telefonica.modules.estudiante.infrastructure.EstudianteRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteRepositoryMySQLRepository implements EstudianteRepository {
    private final String url;
    private final String user;
    private final String password;

    public EstudianteRepositoryMySQLRepository(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Estudiante estudiante){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO estudiante (id, nombre, especialidad, grado) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, estudiante.getId());
                statement.setString(2, estudiante.getNombre());
                statement.setString(3, estudiante.getEspecialidad());
                statement.setString(4, estudiante.getGrado());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM estudiante WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upDate(Estudiante estudiante){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE estudiante SET nombre = ?, especialidad = ?, grado = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, estudiante.getNombre());
                statement.setString(2, estudiante.getEspecialidad());
                statement.setString(3, estudiante.getGrado());
                statement.setInt(4, estudiante.getId());
                statement.executeUpdate();
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Estudiante> findById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, nombre, especialidad, grado FROM estudiante WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()){
                        Estudiante estudiante1 = new Estudiante(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("especialidad"),
                            resultSet.getString("grado")
                        );
                        return Optional.of(estudiante1);   
                    }    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Estudiante> findAll(){
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, nombre, especialidad, grado FROM estudiante";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()){
                        Estudiante estudiante1 = new Estudiante(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("especialidad"),
                            resultSet.getString("grado")
                        );
                        estudiantes.add(estudiante1);
                    }   
                }   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }
}
