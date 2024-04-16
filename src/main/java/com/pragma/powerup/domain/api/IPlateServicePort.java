package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Plate;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlateServicePort {

    void savePlate(Plate plate);

    void updatePlate(Plate plate);

    Plate getPlate(Long idPlate);

    List<Plate> getMenuForRestaurant(Long idRestaurant);
}
