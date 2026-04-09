package net.kkkallip.veebipood.controller;

import net.kkkallip.veebipood.entity.Category;
import net.kkkallip.veebipood.entity.Product;
import net.kkkallip.veebipood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @DeleteMapping("categories/{id}")
    public List<Category> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }

    @PostMapping("categories")
    public List<Category> addCategory(@RequestBody Category product) {
        if (product.getId() != null) {
            throw new RuntimeException("Cannot add with id");
        }
        categoryRepository.save(product);
        return categoryRepository.findAll();
    }
}
