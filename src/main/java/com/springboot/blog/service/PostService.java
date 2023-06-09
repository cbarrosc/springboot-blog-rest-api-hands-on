package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long categoryId);

    PostDto updatePost(PostDto postDto, Long categoryId);

    void deletePostById(Long categoryId);

    List<PostDto> getPostByCategoryId(Long categoryId);

}
