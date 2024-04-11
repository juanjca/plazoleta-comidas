package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
import com.pragma.powerup.application.dto.request.PlateRequestDto;
import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.application.handler.impl.PlateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("plate")
public class PlateRestController {

    private final PlateHandler plateHandler;

    @PostMapping("/")
    public ResponseEntity<String> savePlate(@Valid @RequestBody PlateRequestDto plateRequestDto){
        plateHandler.savePlate(plateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/")
    public ResponseEntity<Void> updatePlate(@Valid @RequestBody PlatePutRequestDto platePutRequestDto){
        plateHandler.updatePlate(platePutRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{number}")
    public ResponseEntity<PlateResponse> getPlate(@PathVariable(name = "number") Long idPlate){
        return ResponseEntity.ok(plateHandler.getPlate(idPlate));
    }
}
