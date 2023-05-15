package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    //Build Add Category REST API
    /* Example request:
     http POST http://localhost:8080/api/categories \
     Content-Type:application/json \
     Authorization: Bearer token
     name="Category 1" \
     description="Category 1 description" \
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(categoryDto));
    }

    //Build Get Category by id REST API
    /* Example request:
     http GET http://localhost:8080/api/categories/1 \
     Content-Type:application/json \
     Authorization: Bearer token
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(value = "id") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(categoryId));
    }

    //Build Get All Categories REST API
    /* Example request:
     http GET http://localhost:8080/api/categories \
     Content-Type:application/json \
     Authorization: Bearer token
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //Build Update Category REST API
    /* Example request:
     http PUT http://localhost:8080/api/categories/1 \
     Content-Type:application/json \
     Authorization: Bearer token
     name="Category 1" \
     description="Category 1 description" \
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(value = "id") Long categoryId, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }

    //Build Delete Category REST API
    /* Example request:
     http DELETE http://localhost:8080/api/categories/1 \
     Content-Type:application/json \
     Authorization: Bearer token
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
