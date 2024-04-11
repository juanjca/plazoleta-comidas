package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Category;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

}
