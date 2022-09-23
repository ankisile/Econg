package com.example.bunjang.service;

import com.example.bunjang.dto.*;

import java.util.List;

public interface ProjectService {

    void register(Long userId, PostProjectDTO postProjectDTO);

    List<GetProjectDTO> getProducts(String type);

    ProjectDTO getProductDetail(Long id);



    void postCommunity(Long userId, Long projectId, PostCommunityDTO postCommunityDTO);

    List<CommunityDTO> getCommunity(Long projectId);

}
