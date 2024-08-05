package com.telefonica.modules.estudiante.domain;

public class Estudiante {
    private int id; //atributos 
    private String nombre;
    private String especialidad;
    private String grado; 
    
    //poliformismo
    public Estudiante(){} //constructor vacio

    public Estudiante (int id, String nombre, String especialidad, String grado){
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.grado = grado;
    } //constructor lleno


     //getters y setters

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    //toString

    @Override
    public String toString(){
        return String.format("Id %d \n nombre %S \n Especialidad %S \n grado %S",id, nombre,especialidad,grado);  
    }
}
