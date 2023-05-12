package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //Create a new comment for a post
    /* Example request:
    http POST http://localhost:8080/api/post/1/comments \
    Content-Type:application/json \
    name="John Doe" \
    email= john@doe.com \
    body="This is a comment body
    */
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(postId, commentDto));
    }

    //Get all comments for a post
    /* Example request:
    http GET http://localhost:8080/api/post/4/comments
     */
    @GetMapping("/post/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@Valid @PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    //Get a comment for a post by id
    /* Example request:
    http GET http://localhost:8080/api/post/4/comments/1
     */
    @GetMapping("/post/{postId}/comments/{commentId}")
    public CommentDto getCommentById(@PathVariable(value = "postId") Long postId,
                                     @PathVariable(value = "commentId") Long commentId){
        return commentService.getCommentById(postId, commentId);
    }

    //Update a comment for a post by id
    /* Example request:
    http PUT http://localhost:8080/api/post/4/comments/1 \
    Content-Type:application/json \
    name="John Doe" \
    email= john@doe.com \
    body="This is a comment body
     */
    @PutMapping("/post/{postId}/comments/{commentId}")
    public CommentDto updateComment(@PathVariable(value = "postId") Long postId,
                                    @PathVariable(value = "commentId") Long commentId,
                                    @Valid @RequestBody CommentDto commentDto){
        return commentService.updateComment(postId, commentId, commentDto);
    }

    //Delete a comment for a post by id
    /* Example request:
    http DELETE http://localhost:8080/api/post/4/comments/1
     */
    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                              @PathVariable(value = "commentId") Long commentId){
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment successfully deleted");
    }



}
