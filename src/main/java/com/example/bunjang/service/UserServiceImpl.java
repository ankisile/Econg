package com.example.bunjang.service;


import com.example.bunjang.common.Role;
import com.example.bunjang.common.exception.IdNotFoundException;
import com.example.bunjang.dto.*;
import com.example.bunjang.entity.Community;
import com.example.bunjang.entity.Favorites;
import com.example.bunjang.entity.Project;
import com.example.bunjang.entity.User;
import com.example.bunjang.common.exception.DuplicateMemberException;
import com.example.bunjang.repository.CommunityRepository;
import com.example.bunjang.repository.FollowingRepository;
import com.example.bunjang.repository.ProjectRepository;
import com.example.bunjang.repository.UserRepository;
import com.example.bunjang.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Slf4j
@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FollowingRepository followingRepository;
    private final ProjectRepository projectRepository;
    private final CommunityRepository communityRepository;

    private final PasswordEncoder passwordEncoder;

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new HashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Transactional
    @Override
    public void register(RegisterReqDTO registerReqDTO) {

        if (userRepository.findByEmail(registerReqDTO.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입된 유저입니다.");
        }

//        log.info(registerReqDTO.getPassword());
        String encodePassword = passwordEncoder.encode(registerReqDTO.getPassword());

        User user = User.builder()
                .email(registerReqDTO.getEmail())
                .password(encodePassword)
                .phone(registerReqDTO.getPhoneNumber())
                .name(registerReqDTO.getUserName())
                .nickName(registerReqDTO.getNickName())
                .activated(true)
                .role(Role.ROLE_USER)
                .profileUrl("gs://econg-7e3f6.appspot.com/bud.png")
                .build();

        userRepository.save(user);
    }

    //
//    public UserResDTO findUserInfo() {
//        String email = SecurityUtil.getCurrentEmail().orElseThrow(() ->
//                new RuntimeException("Security Context에 인증 정보가 없습니다."));
//
//        return userRepository.findByEmail(email)
//                .map(user -> new UserResDTO(user))
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 user 입니다. email=" + email));
//    }
//
    @Override
    public Long findUserId(){
        String email = SecurityUtil.getCurrentEmail().orElseThrow(() ->
                new RuntimeException("Security Context에 인증 정보가 없습니다."));

        return userRepository.findByEmail(email).get().getId();
    }

    @Transactional
    @Override
    public List<RecentUserDTO> getRecentUsers() {

        List<Project> result = projectRepository.findByStatusOrderByIdDesc("ONGOING");

        List<RecentUserDTO> userDTOList = result.stream().map(project -> {
            User user = project.getUser();
            return new RecentUserDTO(user.getId(),user.getNickName(),user.getDescription(),user.getProfileUrl(),user.getAuthenticate());
        }).collect(Collectors.toList());

        return userDTOList.stream().filter(distinctByKey(RecentUserDTO::getUserId)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO getProfile(Long userId) {
        Optional<User> result = userRepository.findById(userId);

        User user = result.get();

        Long followingNum = followingRepository.countFollowingsByUser_Id(userId);
        Long followerNum= followingRepository.countFollowingsByFollow_Id(userId);

        return new UserDTO(user.getId(),user.getNickName(),user.getDescription(),user.getProfileUrl(),user.getAuthenticate(), followingNum, followerNum);


    }

    @Override
    public void getPostProjects(Long userId) {

        List<Project> projectList = projectRepository.findByUser_Id(userId);
    }




}
