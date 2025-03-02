package com.turismo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de hoteles
 */
@Service
class HotelService {
    @Autowired
    private HotelRepository repository;

    public List<Hotel> listarHoteles() { return repository.findAll(); }
    public Hotel obtenerHotel(String id) { return repository.findById(id).orElse(null); }
    public Hotel guardarHotel(Hotel hotel) { return repository.save(hotel); }
    public void eliminarHotel(String id) { repository.deleteById(id); }
}
