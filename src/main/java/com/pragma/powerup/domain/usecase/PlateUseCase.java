package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.domain.api.IPlateServicePort;
import com.pragma.powerup.domain.model.Plate;
import com.pragma.powerup.domain.spi.IPlatePersistencePort;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PlateUseCase implements IPlateServicePort {

    private final IPlatePersistencePort platePersistencePort;

    public PlateUseCase(IPlatePersistencePort platePersistencePort) {
        this.platePersistencePort = platePersistencePort;
    }

    @Override
    public void savePlate(Plate plate) {
        platePersistencePort.savePlate(plate);
    }

    @Override
    public void updatePlate(Plate plate) {
        platePersistencePort.updatePlate(plate);
    }

    @Override
    public Plate getPlate(Long idPlate) {
        return platePersistencePort.getPlate(idPlate);
    }

    @Override
    public List<Plate> getMenuForRestaurant(Long idRestaurant) {
        return platePersistencePort.getMenuForRestaurant(idRestaurant);
    }
}
