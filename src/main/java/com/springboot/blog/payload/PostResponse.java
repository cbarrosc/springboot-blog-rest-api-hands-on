package com.springboot.blog.payload;

import lombok.Builder;

import java.util.List;

@Builder
public record PostResponse(
        List<PostDto> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
}
