package com.telefonica.modules.estudiante.adapter.in;
import java.util.Optional;
import java.util.Scanner;
import com.telefonica.modules.estudiante.application.EstudianteService;
import com.telefonica.modules.estudiante.domain.Estudiante;

public class EstudianteConsoleAdapter {
    private EstudianteService estudianteService;

    public EstudianteConsoleAdapter(EstudianteService estudianteService){
        this.estudianteService = estudianteService;
    }

    public void star(){

        int star = -1;

        while (star != 0){
            int id = 0;
            String nombre = "null";
            String especialidad = "null";
            String grado = "null";
            Scanner sc = new Scanner(System.in);

            switch(menu(sc)){
                case 1:
                    saveEstudiante(id, nombre, especialidad, grado, sc);
                    break;

                case 2:
                    deleteEstudiante(sc, id);
                    break;

                case 3:
                    upDateEstudiante(sc, id, nombre, especialidad, grado);
                    break;

                case 4:
                    findByIdEstudiante(sc, id);
                    break;

                case 5:
                    findAllEstudiantes(sc);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
                }
        }
    }

    public int menu(Scanner sc){
        System.out.println("Bienvenido, porfavor ingrese la opcion que desea realizar:\n " +
        "1. Registrar un estudiante \n 2. Eliminar un estudiante \n 3. Editar un estudiante" +
        "\n 4. Encontrar un estudiante por ID \n 5. Listar todos los estudiantes \n0. Salir");

        int choice = -1;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Ingrese una opción valida");
        }
        return choice;
    }

    public void saveEstudiante(int id, String nombre, String especialidad, String grado, Scanner sc){
        int star = -1;
        while(star != 0){
            System.out.println("Ingrese el ID del estudiante, (solo números, longitud máxima de 11) o Ingrese 0 para SALIR");
            id = -1;
            try {
                id = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Numero INCORRECTO, debe ingresar NUMEROS con una logitud maxima de 11");
                continue;
            }
            if(id == 0){
                return;
            }
            System.out.println("Ingrese el nombre del estudiante");
            nombre = sc.nextLine();
            System.out.println("Ingrese la especialidad del estudiante");
            especialidad = sc.nextLine();
            System.out.println("Ingrese el grado del estudiante");
            grado = sc.nextLine();
            Estudiante newEstudiante = new Estudiante(id, nombre, especialidad, grado);
            estudianteService.saveEstudiante(newEstudiante);
            System.out.println("Estudiante guardado exitosamente.");
        }
        sc.close();
    }

    public void deleteEstudiante(Scanner sc, int id){
        int star = -1;
        while (star != 0){
            System.out.println("Ingrese el ID del estudiante a ELIMINAR, (solo números, longitud máxima de 11) o Ingrese 0 para SALIR");
            id = -1;
            try {
                id = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Numero INCORRECTO, debe ingresar NUMEROS con una logitud maxima de 11");
                continue;
            }

            if(id == 0){
                return;
            }
            estudianteService.deleteEstudiante(id);
            System.out.println("Estudiante eliminado exitosamente.");
        }
        sc.close();
    }

    public void upDateEstudiante(Scanner sc, int id, String nombre, String especialidad, String grado){

        int star = -1;

        while(star != 0){

            System.out.println("Ingrese el ID del estudiante a ACTUAILIZAR, (solo números, longitud máxima de 11) o ingrese 0 para SALIR");
            id = -1;
            try {
                id = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Numero INCORRECTO, debe ingresar NUMEROS con una logitud maxima de 11");
                continue;
            }

            if(id == 0){
                return;
            }
            Optional<Estudiante> estudianteEncontrado = estudianteService.findByIdEstudiante(id);
            estudianteEncontrado.ifPresentOrElse(
            upDateEstudiante -> {

                int choices = -1;

                while (choices != 0){
                    System.out.println("Bienvenido, porfavor ingrese la opcion que desea realizar: " +
                    "\n1. Actualizar nombre \n 2. Actualizar especialidad \n 3. Actualizar grado" +
                    "\n 0. Salir ");
                    try {
                        choices = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println("Ingrese una opción valida");
                        continue;
                    }
                    if (choices == 0){
                        return;
                    }
                    switch (choices) {
                        case 1: 
                            System.out.print("Ingrese el nuevo nombre: ");
                            upDateEstudiante.setNombre(sc.nextLine());
                            System.out.println("Nombre cambiado con exito");
                            break;
                            
                        case 2:
                            System.out.print("Ingrese la nueva especialidad: ");
                            upDateEstudiante.setEspecialidad(sc.nextLine());
                            System.out.println("especialidad cambiada con exito");
                            break;
                        case 3:
                            System.out.print("Ingrese el nuevo grado: ");
                            System.out.println("grado con exito");
                            upDateEstudiante.setGrado(sc.nextLine());
                            break;
                    }
                    estudianteService.upDateEstudiante(upDateEstudiante);
                    System.out.println("Estudiante actualizado con exito");
                    break;
                }
            }, () -> System.out.println("Estudiante con el ID no registrado"));
        }
    }

    public void findByIdEstudiante(Scanner sc, int id){
        int star = -1;

        while(star != 0){

            System.out.println("Ingrese el ID del estudiante a ACTUAILIZAR, (solo números, longitud máxima de 11) o ingrese 0 para SALIR");
            id = -1;
            try {
                id = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Numero INCORRECTO, debe ingresar NUMEROS con una logitud maxima de 11");
                continue;
            }
            if (id == 0){
                return;
            }
            
            Optional<Estudiante> estudianteEncontrado = estudianteService.findByIdEstudiante(id);
            estudianteEncontrado.ifPresentOrElse(
                p -> {
                    System.out.println(p);
                    return;
                },() -> System.out.println("Estudiante con el ID no registrado"));
        }
        sc.close();
    }

    public void findAllEstudiantes(Scanner sc){
            estudianteService.findAllEstudiantes().forEach(p -> {
                System.out.println(p);
            });
    }
}
