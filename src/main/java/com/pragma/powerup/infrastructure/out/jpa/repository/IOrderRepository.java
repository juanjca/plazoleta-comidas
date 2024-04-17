package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.domain.model.State;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findByState(State state, Pageable pageable);

}
