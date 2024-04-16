package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Plate;
import com.pragma.powerup.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.infrastructure.exception.PlateNotFoundException;
import com.pragma.powerup.infrastructure.exception.UserNotExistException;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlateEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.PlateEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IPlateRepository;

import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlateJpaAdapter implements IPlatePersistencePort {

    private final IPlateRepository plateRepository;
    private final PlateEntityMapper plateEntityMapper;
    private final IRestaurantRepository restaurantRepository;

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

//    @Override
//    public List<Plate> getMenuForRestaurant(Long restaurantId, Pageable pageable) {
//        return plateEntityMapper.toPlateList(plateRepository.findByIdRestaurant(restaurantId, pageable)
//        );
//    }

    @Override
    public List<Plate> getMenuForRestaurant(Long restaurantId) {
        List<PlateEntity> listPlateEntity = plateRepository.findByIdRestaurant(restaurantId);
        return plateEntityMapper.toPlateList(listPlateEntity);
    }

}
