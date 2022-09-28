package com.example.bunjang.service;

import com.example.bunjang.common.exception.UserNotDefinedException;
import com.example.bunjang.dto.FollowDTO;
import com.example.bunjang.entity.Following;
import com.example.bunjang.entity.User;
import com.example.bunjang.repository.FollowingRepository;
import com.example.bunjang.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowingRepository followingRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public String follow(Long userId, FollowDTO followDTO) {

        boolean empty = userRepository.findById(followDTO.getUserId()).isEmpty();
        if(userId == followDTO.getUserId() || empty) throw new UserNotDefinedException("유효한 사용자가 아닙니다.");
        if(!isFollow(userId, followDTO.getUserId())) {
            User activeUser = User.builder().id(userId).build();
            User passiveUser = User.builder().id(followDTO.getUserId()).build();

            Following following = Following.builder().user(activeUser).follow(passiveUser).build();
            followingRepository.save(following);
            return "팔로우 완료";
        }
        else{
            followingRepository.deleteByUser_IdAndFollow_Id(userId, followDTO.getUserId());
            return "팔로우 해제";
        }
    }

    @Transactional
    @Override
    public void unfollow(Long userId, FollowDTO followDTO) {
        followingRepository.deleteByUser_IdAndFollow_Id(userId, followDTO.getUserId());
    }

    @Override
    public boolean isFollow(Long userId, Long followId) {
        Optional<Following> result = followingRepository.findByUser_IdAndFollow_Id(userId, followId);
        return result.isPresent();
    }

    @Override
    public List<FollowDTO> getFollowers(Long userId) {
        List<Following> result = followingRepository.findByFollow_Id(userId);

        return result.stream().map(following -> {
//            String follow = isFollow(userId, following.getUser().getId()) ? "true": "false";
//            follow = following.getUser().getId() == myId ? "MyAccount" : follow;

            return new FollowDTO(
            following.getUser().getId(),
           following.getUser().getName(),
           following.getUser().getProfileUrl(),
                    isFollow(userId, following.getUser().getId())
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<FollowDTO> getFollowings(Long userId) {
        List<Following> result = followingRepository.findByUser_Id(userId);

        return result.stream().map(following -> {
//            String follow = isFollow(userId, following.getUser().getId()) ? "true": "false";
//            follow = following.getUser().getId() == myId ? "MyAccount" : follow;

            return new FollowDTO(
                    following.getUser().getId(),
                    following.getUser().getName(),
                    following.getUser().getProfileUrl(),
                    isFollow(userId, following.getUser().getId())
            );
        }).collect(Collectors.toList());
    }



}
