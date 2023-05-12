package com.springboot.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
}
