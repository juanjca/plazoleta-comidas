package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Plate;
import com.pragma.powerup.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.infrastructure.exception.PlateAlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.PlateNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.mapper.PlateEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IPlateRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlateJpaAdapter implements IPlatePersistencePort {

    private final IPlateRepository plateRepository;
    private final PlateEntityMapper plateEntityMapper;

    @Override
    public void savePlate(Plate plate) {
        plateRepository.save(plateEntityMapper.toEntityPlate(plate));
    }

    @Override
    public void updatePlate(Plate plate) {
        plateRepository.save(plateEntityMapper.toEntityPlate(plate));
    }

    @Override
    public Plate getPlate(Long idPlate) {
        return plateEntityMapper.toPlate(plateRepository.findById(idPlate)
                .orElseThrow(PlateNotFoundException::new));
    }
}
