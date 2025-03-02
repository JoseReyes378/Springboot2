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
![Captura de pantalla 2025-03-02 235300](https://github.com/user-attachments/assets/29e57c00-e75f-42e0-9a51-e9075c1462ae)

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
![Captura de pantalla 2025-03-02 235352](https://github.com/user-attachments/assets/38af3129-1644-4245-b0c4-b01337ea9a45)

Función: Define un endpoint en la raíz (/) que devuelve un mensaje de bienvenida.

Anotaciones:

@RestController: Indica que esta clase maneja solicitudes HTTP.

@RequestMapping("/"): Define el mapeo de la URL base.

@GetMapping: Maneja peticiones GET.

3.3 Hotel.java (Entidad)
![Captura de pantalla 2025-03-02 235432](https://github.com/user-attachments/assets/fa2b1f84-99b3-475b-a70b-46bd55235be4)

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
![Captura de pantalla 2025-03-02 235456](https://github.com/user-attachments/assets/20db91b7-74d9-4620-bcf4-81a35425524a)

package com.turismo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

Función: Interfaz que permite acceder a la base de datos.

Extiende JpaRepository, que proporciona operaciones CRUD automáticamente.

3.5 HotelService.java
![Captura de pantalla 2025-03-02 235521](https://github.com/user-attachments/assets/76c6d9b9-7c36-4938-90b7-918c7a10a541)

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
![Captura de pantalla 2025-03-02 235620](https://github.com/user-attachments/assets/99bd112c-ef6e-4f32-b9bd-2e5a6ea06249)

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
![Captura de pantalla 2025-03-02 235545](https://github.com/user-attachments/assets/0a9637b6-91a4-4661-a90d-9641ac1d1f73)

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
