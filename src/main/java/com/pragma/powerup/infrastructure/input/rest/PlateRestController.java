package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.GetPlateRequestDto;
import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
import com.pragma.powerup.application.dto.request.PlateRequestDto;
import com.pragma.powerup.application.dto.request.StatePlateRequestDto;
import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.application.handler.impl.PlateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    public ResponseEntity<String> savePlate(@Valid @RequestBody PlateRequestDto plateRequestDto){
        plateHandler.savePlate(plateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Plate created successfully");
    }

    @PutMapping("/")
    public ResponseEntity<Void> updatePlate(@Valid @RequestBody PlatePutRequestDto platePutRequestDto){
        System.out.println("Entro al controlador");
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

    @GetMapping("/menu/{idRestaurant}")
    public ResponseEntity<List<PlateResponse>> getMenuForRestaurant(@PathVariable("idRestaurant") Long restaurantId) {
        return ResponseEntity.ok(plateHandler.getMenuForRestaurant(restaurantId));
    }


}
