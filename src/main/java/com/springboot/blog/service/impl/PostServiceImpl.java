package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        Post post = mapToEntity(postDto);
        post.setCategory(category);

        return mapToDTO(postRepository.save(post));
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        //create Sort instance
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        //create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        //get content for page object
        List<Post> listOfPost = posts.getContent();

        List<PostDto> content = listOfPost.stream().map(this::mapToDTO).toList();
        return PostResponse.builder()
                .content(content)
                .page(posts.getNumber())
                .size(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast())
                .build();
    }

    @Override
    public PostDto getPostById(Long categoryId) {
        Post post = postRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", categoryId));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long categoryId) {
        //Get post by id from database
        Post post = postRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", categoryId));
        //Get category by id from database
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        //Set new values
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(Long categoryId) {
        postRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", categoryId));
        postRepository.deleteById(categoryId);
    }

    @Override
    public List<PostDto> getPostByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts.stream().map(this::mapToDTO).toList();
    }

    //convert Entity to DTO
    private PostDto mapToDTO(Post post) {
        return mapper.map(post, PostDto.class);
    }

    //convert DTO to Entity
    private Post mapToEntity(PostDto postDto) {
        return mapper.map(postDto, Post.class);
    }
}
