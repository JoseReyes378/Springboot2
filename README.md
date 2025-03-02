Documentación del Proyecto Spring Boot "ApiTurismo"

1. Descripción General

El proyecto ApiTurismo es una aplicación desarrollada en Spring Boot que gestiona información sobre hoteles. Implementa una API REST para manejar las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los hoteles.

2. Estructura del Proyecto

El proyecto sigue una estructura típica de Spring Boot con los siguientes componentes:

Controladores: Manejan las peticiones HTTP y definen los endpoints.

Servicios: Contienen la lógica de negocio.

Repositorios: Interactúan con la base de datos.

Entidades: Representan los datos manejados en la aplicación.

Configuración de seguridad: Define reglas de acceso y autenticación.

3. Explicación de las Clases

3.1 ApiTurismoApplication.java

package com.turismo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiTurismoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiTurismoApplication.class, args);
    }
}

Función: Clase principal de la aplicación que inicia Spring Boot.

Anotación @SpringBootApplication: Habilita la configuración automática, la detección de componentes y la configuración de la aplicación.

3.2 HomeController.java

package com.turismo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "Bienvenido a la API de Turismo";
    }
}

Función: Define un endpoint en la raíz (/) que devuelve un mensaje de bienvenida.

Anotaciones:

@RestController: Indica que esta clase maneja solicitudes HTTP.

@RequestMapping("/"): Define el mapeo de la URL base.

@GetMapping: Maneja peticiones GET.

3.3 Hotel.java (Entidad)

package com.turismo;

import jakarta.persistence.*;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ciudad;
    private double precio;

    // Getters y Setters
}

Función: Representa un hotel en la base de datos.

Anotaciones:

@Entity: Indica que esta clase es una entidad de la base de datos.

@Id, @GeneratedValue: Define el identificador único del hotel.

3.4 HotelRepository.java

package com.turismo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

Función: Interfaz que permite acceder a la base de datos.

Extiende JpaRepository, que proporciona operaciones CRUD automáticamente.

3.5 HotelService.java

package com.turismo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}

Función: Contiene la lógica de negocio relacionada con los hoteles.

Inyecta HotelRepository para interactuar con la base de datos.

3.6 HotelController.java

package com.turismo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }
}

Función: Define los endpoints REST para gestionar hoteles.

Endpoints:

GET /hoteles: Devuelve la lista de hoteles.

POST /hoteles: Agrega un nuevo hotel.

3.7 SecurityConfig.java

package com.turismo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .anyRequest().permitAll();
        return http.build();
    }
}

Función: Configura la seguridad de la aplicación.

Desactiva CSRF y permite acceso sin autenticación a todos los endpoints.

4. Pasos para Implementar la Aplicación

Crear el proyecto Spring Boot utilizando Spring Initializr.

Agregar dependencias en pom.xml, incluyendo Spring Web, Spring Data JPA, y Spring Security.

Configurar la base de datos en application.properties.

Crear la entidad Hotel.

Implementar el repositorio HotelRepository para operaciones CRUD.

Desarrollar el servicio HotelService con la lógica de negocio.

Crear el controlador HotelController para exponer la API REST.

Implementar seguridad con SecurityConfig.

Probar la API con Postman o herramientas similares.
