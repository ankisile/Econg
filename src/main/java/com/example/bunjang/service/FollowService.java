package com.example.bunjang.service;

import com.example.bunjang.dto.FollowDTO;

import java.util.List;

public interface FollowService {
    String follow(Long userId, FollowDTO followDTO);

    void unfollow(Long userId, FollowDTO followDTO);

    boolean isFollow(Long userId,Long followId);

    List<FollowDTO> getFollowers(Long userId);

    List<FollowDTO> getFollowings(Long userId);



}
