package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.PlateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {

    @Override
    Optional<PlateEntity> findById(Long idPlate);

    List<PlateEntity> findByIdRestaurant(Long idRestaurant);

}
