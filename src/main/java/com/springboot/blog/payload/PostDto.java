package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "Post", description = "Post DTO model class")
public final class PostDto {
    private long id;

    @Schema(name = "Title", description = "Title of the post", example = "Post title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, message = "Title should be at least 2 characters long")
    private String title;

    @Schema(name = "Description", description = "Description of the post", example = "Post description")
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 10, message = "Description should be at least 10 characters long")
    private String description;

    @Schema(name = "Content", description = "Content of the post", example = "Post content")
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private Set<CommentDto> comments;
    @Schema(name = "Category", description = "Category of the post", example = "1")
    private Long categoryId;
}
