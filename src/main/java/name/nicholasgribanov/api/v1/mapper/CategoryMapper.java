package name.nicholasgribanov.api.v1.mapper;

import name.nicholasgribanov.api.v1.model.CategoryDTO;
import name.nicholasgribanov.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
