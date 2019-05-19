package name.nicholasgribanov.controllers.v1;

import name.nicholasgribanov.api.v1.model.CategoryDTO;
import name.nicholasgribanov.api.v1.model.CategoryListDTO;
import name.nicholasgribanov.domain.Category;
import name.nicholasgribanov.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createNewCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createNewCategory(categoryDTO);
    }
}
