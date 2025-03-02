package com.turismo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase HotelController
 *
 * Descripción: Controlador REST para gestionar hoteles.
 */
@RestController
@RequestMapping("/hoteles")
class HotelController {
    @Autowired
    private HotelService service;

    @GetMapping
    public List<Hotel> listarHoteles() { return service.listarHoteles(); }

    @GetMapping("/{id}")
    public Hotel obtenerHotel(@PathVariable Long id) { return service.obtenerHotel(String.valueOf(id)); }

    @PostMapping
    public Hotel crearHotel(@RequestBody Hotel hotel) { return service.guardarHotel(hotel); }

    @DeleteMapping("/{id}")
    public void eliminarHotel(@PathVariable Long id) { service.eliminarHotel(String.valueOf(id)); }
}