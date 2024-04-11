package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Plate;

public interface IPlateServicePort {

    void savePlate(Plate plate);

    void updatePlate(Plate plate);

    Plate getPlate(Long idPlate);
}
