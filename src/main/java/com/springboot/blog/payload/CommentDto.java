package com.springboot.blog.payload;

import lombok.Builder;

@Builder
public record CommentDto(
        Long id,
        String name,
        String email,
        String body
) {
}
