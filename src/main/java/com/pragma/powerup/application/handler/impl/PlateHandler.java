    package com.pragma.powerup.application.handler.impl;

    import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
    import com.pragma.powerup.application.dto.request.PlateRequestDto;
    import com.pragma.powerup.application.dto.response.PlateResponse;
    import com.pragma.powerup.application.handler.IPlateHandler;
    import com.pragma.powerup.application.mapper.PlateRequestMapper;
    import com.pragma.powerup.application.mapper.PlateResponseMapper;
    import com.pragma.powerup.application.mapper.PlateUpdateRequestMapper;
    import com.pragma.powerup.domain.api.IPlateServicePort;
    import com.pragma.powerup.domain.model.Plate;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class PlateHandler implements IPlateHandler {

        private final IPlateServicePort plateServicePort;
        private final PlateRequestMapper plateRequestMapper;
        private final PlateUpdateRequestMapper plateUpdateRequestMapper;
        private final PlateResponseMapper plateResponseMapper;

        @Override
        public void savePlate(PlateRequestDto plateRequestDto) {
            Plate plate = plateRequestMapper.toPlate(plateRequestDto);
            plateServicePort.savePlate(plate);
        }

        @Override
        public void updatePlate(PlatePutRequestDto platePutRequestDto) {
            Plate oldPlate = plateServicePort.getPlate(platePutRequestDto.getIdPlate());
            Plate newPlate = plateUpdateRequestMapper.toPlateUpdate(platePutRequestDto);
            newPlate.setName(oldPlate.getName());
            newPlate.setIdCategory(oldPlate.getIdCategory());
            newPlate.setIdRestaurant(oldPlate.getIdRestaurant());
            newPlate.setUrlImage(oldPlate.getUrlImage());
            plateServicePort.updatePlate(newPlate);
        }

        @Override
        public PlateResponse getPlate(Long idPlate) {
            Plate plate = plateServicePort.getPlate(idPlate);
            return plateResponseMapper.toResponse(plate);
        }
    }
