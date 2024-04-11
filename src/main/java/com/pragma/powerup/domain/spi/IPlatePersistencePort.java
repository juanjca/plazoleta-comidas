package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Plate;

public interface IPlatePersistencePort {

    void savePlate(Plate plate);

    void updatePlate(Plate plate);

    Plate getPlate(Long idPlate);

}
