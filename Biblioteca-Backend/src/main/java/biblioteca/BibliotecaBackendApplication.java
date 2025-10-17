package biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Clase principal de la aplicación Spring Boot
 * Sistema de Gestión de Biblioteca - Ediciones Saberum
 *
 * @author Tristam
 * @version 1.0.0
 */
@SpringBootApplication
public class BibliotecaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaBackendApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("Sistema Biblioteca Saberum INICIADO");
        System.out.println("API disponible en: http://localhost:8080/api");
        System.out.println("Base de datos: PostgreSQL");
        System.out.println("Proyecto: Desarrollo Web Integrado");
        System.out.println("===========================================\n");
    }

}