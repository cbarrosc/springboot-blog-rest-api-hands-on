package com.springboot.blog.payload;

import lombok.Builder;

@Builder
public record PostDto(
        long id,
        String title,
        String description,
        String content
) {
}
