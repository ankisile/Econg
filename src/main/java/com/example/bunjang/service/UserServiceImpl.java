package com.example.bunjang.service;


import com.example.bunjang.common.Role;
import com.example.bunjang.common.exception.IdNotFoundException;
import com.example.bunjang.dto.*;
import com.example.bunjang.entity.Following;
import com.example.bunjang.entity.Project;
import com.example.bunjang.entity.User;
import com.example.bunjang.common.exception.DuplicateMemberException;
import com.example.bunjang.repository.FollowingRepository;
import com.example.bunjang.repository.ProjectRepository;
import com.example.bunjang.repository.UserRepository;
import com.example.bunjang.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        if(userRepository.findByNickName(registerReqDTO.getNickName()).orElse(null)!=null){
            throw new DuplicateMemberException("닉네임 중복입니다");
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
                .authenticate(false)
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

//        List<Project> result = projectRepository.findByStatusOrderByIdDesc("ONGOING");
        List<User> result = projectRepository.findUserOrderByProjectIdDesc();
        List<RecentUserDTO> userDTOList = result.stream().map(user -> {
//            User user = project.getUser();
            return new RecentUserDTO(user.getId(),user.getNickName(),user.getDescription(),user.getProfileUrl(),user.getAuthenticate());
        }).collect(Collectors.toList());

        return userDTOList.stream().filter(distinctByKey(RecentUserDTO::getUserId)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO getProfile( Boolean myProfile, Long userId) {

        Optional<User> result = userRepository.findById(userId);

        User user = result.get();

        Long followingNum = followingRepository.countFollowingsByUser_Id(userId);
        Long followerNum= followingRepository.countFollowingsByFollow_Id(userId);

        String userEmail = SecurityUtil.getCurrentEmail().orElseThrow(() ->
                new IdNotFoundException("Security Context에 인증 정보가 없습니다."));
        Optional<User> currentUser = userRepository.findByEmail(userEmail);

        Optional<Following> following = followingRepository.findByUser_IdAndFollow_Id(currentUser.get().getId(), userId);
        boolean isFollow = false;
        if(following.isPresent()) isFollow=true;

        return new UserDTO(user.getId(),user.getNickName(),user.getDescription(),user.getProfileUrl(),user.getAuthenticate(), followingNum, followerNum, myProfile, isFollow);


    }

    @Transactional
    @Override
    public List<GetProjectDTO> getPostProjects(Long userId) {

        List<Project> projectList = projectRepository.findByUser_Id(userId);

        return projectList.stream().map(project -> {
                    return new GetProjectDTO(
                            project.getId(),
                            project.getTitle(),
                            project.getOpeningDate(),
                            project.getClosingDate(),
                            project.getTotalAmount(),
                            project.getAchievedRate(),
                            project.getSummary(),
                            project.getThumbnail(),
                            project.getAuthenticate(),
                            project.getStatus(),
                            project.getUser().getNickName());
                }
        ).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String patchProfile(Long userId, PatchProfileDTO patchProfileDTO) {

        User user = userRepository.findById(userId).orElseThrow(()->new IdNotFoundException("id not found"));

        user.modifyNickName(patchProfileDTO.getNickName());
        user.modifyDescription(patchProfileDTO.getDescription());
        user.modifyProfileUrl(patchProfileDTO.getProfileUrl());

        userRepository.save(user);

        return "modify success";
    }


}
