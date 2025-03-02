package com.turismo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio MongoDB para hoteles
 */
public interface HotelRepository extends MongoRepository<Hotel, String> {}
