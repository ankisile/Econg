package com.example.bunjang.service;

import com.example.bunjang.dto.CommunityDTO;
import com.example.bunjang.dto.PostCommunityDTO;

import java.util.List;

public interface CommunityService {

    List<CommunityDTO> getMyCommunityList();

    String deleteCommunity(Long communityId);

    String modifyCommunity(Long communityId, PostCommunityDTO postCommunityDTO);

}
