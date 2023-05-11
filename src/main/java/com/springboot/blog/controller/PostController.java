package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDto));
    }

    //Get all blog posts
    /* Example request:
     http GET http://localhost:8080/api/posts
     */
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
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
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
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
