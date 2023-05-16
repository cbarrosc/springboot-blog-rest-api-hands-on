package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "CRUD REST API for post Resources")
public class PostController {

    private final PostService postService;

    //Create blog post
    /* Example request:
     http POST http://localhost:8080/api/posts \
     Content-Type:application/json \
     title="My first post" \
     description="My first post description" \
     content="My first post content"
     */

    @Operation(summary = "Create Post REST API",
            description = "Create Post REST API is used to save a new post in the database.")
    @ApiResponse(responseCode = "201",
            description = "Http Status Created",
            content = @Content(mediaType = "application/json"))
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDto));
    }

    //Get all blog posts providing pagination and sorting
    /* Example request:
     http GET http://localhost:8080/api/posts?page=0&size=10&sort-by=title&sort-dir=asc
     */
    @Operation(summary = "Get all Post REST API",
            description = "Get all Post REST API is to fetch all posts from the database. It also provides pagination and sorting functionality.")
    @ApiResponse(responseCode = "200",
            description = "Http Status Success",
            content = @Content(mediaType = "application/json"))
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                    @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(name = "sort-by", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(name = "sort-dir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //Get blog post by id
    /* Example request:
     http GET http://localhost:8080/api/posts/1
     */
    @Operation(summary = "Get Post by id REST API",
            description = "Get Post by id REST API is used to retrieve a post by id from the database.")
    @ApiResponse(responseCode = "200",
            description = "Http Status Success",
            content = @Content(mediaType = "application/json"))
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //Update blog post
    /* Example request:
     http PUT http://localhost:8080/api/posts/1 \
     Content-Type:application/json \
     title="My first post updated" \
     description="My first post description updated" \
     content="My first post content updated"
     */
    @Operation(summary = "Update Post by id REST API",
            description = "Update Post by id REST API is used to update a post by id in the database.")
    @ApiResponse(responseCode = "200",
            description = "Http Status Success",
            content = @Content(mediaType = "application/json"))
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }

    //Delete blog post
    /* Example request:
     http DELETE http://localhost:8080/api/posts/1
     */
    @Operation(summary = "Delete Post by id REST API",
            description = "Delete Post by id REST API is used delete a post by id from the database.")
    @ApiResponse(responseCode = "200",
            description = "Http Status Success",
            content = @Content(mediaType = "application/json"))
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);
        return ResponseEntity.ok("Post successfully deleted");
    }

    //Get all blog post by category id
    /* Example request:
     http GET http://localhost:8080/api/posts/category/1
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable(name = "id") Long categoryId){
        return ResponseEntity.ok(postService.getPostByCategoryId(categoryId));
    }



}
