package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class PostDto {
    private long id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, message = "Title should be at least 2 characters long")
    private String title;

    @NotEmpty(message = "Description should not be empty")
    @Size(min = 10, message = "Description should be at least 10 characters long")
    private String description;

    @NotEmpty(message = "Content should not be empty")
    private String content;
    private Set<CommentDto> comments;
}
