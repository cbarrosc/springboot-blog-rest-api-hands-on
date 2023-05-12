package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
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

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDto));
    }

    //Get all blog posts providing pagination and sorting
    /* Example request:
     http GET http://localhost:8080/api/posts?page=0&size=10&sort-by=title&sort-dir=asc
     */
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
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }

    //Delete blog post
    /* Example request:
     http DELETE http://localhost:8080/api/posts/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);
        return ResponseEntity.ok("Post successfully deleted");
    }



}
