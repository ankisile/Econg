package com.example.bunjang.service;

import com.example.bunjang.dto.GetProjectDTO;

import java.util.List;

public interface FavoriteService {
    String pushLikes(Long userId, Long productId);

    List<GetProjectDTO> getLikes(Long id);
}
