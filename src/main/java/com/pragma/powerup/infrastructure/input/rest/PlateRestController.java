package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.*;
import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.application.handler.impl.OrderHandler;
import com.pragma.powerup.application.handler.impl.PlateHandler;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.model.State;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("plate")
public class PlateRestController {

    private final PlateHandler plateHandler;
    private final OrderHandler orderHandler;

    @PostMapping("/")
    public ResponseEntity<String> savePlate(@Valid @RequestBody PlateRequestDto plateRequestDto){
        plateHandler.savePlate(plateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Plate created successfully");
    }

    @PutMapping("/")
    public ResponseEntity<Void> updatePlate(@Valid @RequestBody PlatePutRequestDto platePutRequestDto){
        plateHandler.updatePlate(platePutRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/changeState/")
    public ResponseEntity<Void> statePlate(@RequestBody StatePlateRequestDto statePlateRequestDto) {
        plateHandler.changeStatePlate(statePlateRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<PlateResponse> getPlate(@RequestBody GetPlateRequestDto getPlateRequestDto){
        return ResponseEntity.ok(plateHandler.getPlate(getPlateRequestDto.getIdPlate()));
    }

    @GetMapping("/menu/{idRestaurant}/")
    public ResponseEntity<List<PlateResponse>> getMenuForRestaurant(@PathVariable("idRestaurant") Long restaurantId) {
        return ResponseEntity.ok(plateHandler.getMenuForRestaurant(restaurantId));
    }

    @GetMapping("/getAll/")
    public ResponseEntity<Page<List<Orders>>> get(@RequestBody ChangeStateOrder changeStateOrder,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return new ResponseEntity<>(plateHandler.getOrderByState(changeStateOrder, page, size), HttpStatus.OK);
    }

    @PutMapping("/update/")
    public ResponseEntity<Void> selectOrder(@RequestBody ChangeStateOrder state) {
        orderHandler.selectOrder(state.getIdOrder(), state.getState());
        return ResponseEntity.noContent().build();
    }


}
