package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.CategoryRequestDto;
import com.pragma.powerup.application.handler.ICategoryHandler;
import com.pragma.powerup.application.mapper.CategoryRequestMapper;
import com.pragma.powerup.application.mapper.PlateRequestMapper;
import com.pragma.powerup.domain.api.ICategoryServicePort;
import com.pragma.powerup.domain.api.IPlateServicePort;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Plate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;

    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestMapper.toCategory(categoryRequestDto);
        categoryServicePort.saveCategory(category);
    }
}
