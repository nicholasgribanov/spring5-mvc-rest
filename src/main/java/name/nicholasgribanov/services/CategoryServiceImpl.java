package name.nicholasgribanov.services;

import name.nicholasgribanov.api.v1.mapper.CategoryMapper;
import name.nicholasgribanov.api.v1.model.CategoryDTO;
import name.nicholasgribanov.domain.Category;
import name.nicholasgribanov.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }

    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDTO) {
       return saveCategoryDto(categoryMapper.categoryDtoToCategory(categoryDTO));
    }

    private CategoryDTO saveCategoryDto(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryDTO(savedCategory);

    }
}
