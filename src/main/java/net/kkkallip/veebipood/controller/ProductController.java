package net.kkkallip.veebipood.controller;

import net.kkkallip.veebipood.entity.Product;
import net.kkkallip.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //localhost:8080/products?page=0&size=4&sort=price,ascending
    @GetMapping("products")
    public Page<Product> getProducts(Pageable pageable, @RequestParam(required = false) Long activeCategoryId) {
        if (activeCategoryId == null || activeCategoryId == 0L) {
            return productRepository.findAll(pageable);
        }
        return productRepository.findAllByCategoryId(pageable, activeCategoryId);
    }

    @GetMapping("products/admin")
    public List<Product> getAdminProducts() {
        return productRepository.findAll();
    }


    @GetMapping("products/{id}")
    public Product getOneProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("Cannot add with id");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("Cannot edit without ID");
        }
        if (!productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product ID does not exist");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }
}
