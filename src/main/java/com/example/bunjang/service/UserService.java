package com.example.bunjang.service;

import com.example.bunjang.dto.*;

import java.util.List;

public interface UserService {

    void register(RegisterReqDTO registerReqDTO);
//    void login(LoginDTO loginDTO);

    Long findUserId();

    List<RecentUserDTO> getRecentUsers();

    UserDTO getProfile(Boolean myProfile, Long userId);

    List<GetProjectDTO> getPostProjects(Long userId);

    String patchProfile(Long userId, PatchProfileDTO patchProfileDTO);
//        올린 프로젝트 후원한 프로젝트 받아오기 -> 이건 api 따로 만들기



}
