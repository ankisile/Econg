package com.example.bunjang.service;

import com.example.bunjang.common.exception.IdNotFoundException;
import com.example.bunjang.dto.CommunityDTO;
import com.example.bunjang.entity.Community;
import com.example.bunjang.repository.CommunityRepository;
import com.example.bunjang.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    private final CommunityRepository communityRepository;

    @Transactional
    @Override
    public List<CommunityDTO> getMyCommunityList() {

        String userEmail = SecurityUtil.getCurrentEmail().orElseThrow(() ->
                new IdNotFoundException("Security Context에 인증 정보가 없습니다."));

        List<Community> result = communityRepository.findByUser_EmailOrderByUpdatedAt(userEmail);

        return result.stream().map(community->{
            return new CommunityDTO(
                    community.getId(),
                    community.getContent(),
                    community.getUpdatedAt(),
                    community.getUser().getId(),
                    community.getUser().getProfileUrl(),
                    community.getUser().getNickName(),
                    community.getProject().getId(),
                    community.getProject().getTitle()
            );

        }).collect(Collectors.toList());
    }


}
