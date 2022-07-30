package com.example.bunjang.service;

import com.example.bunjang.dto.CompanyDTO;
import com.example.bunjang.dto.CompanyDetailDTO;
import com.example.bunjang.dto.RegisterReqDTO;
import com.example.bunjang.dto.UserResDTO;
import com.example.bunjang.entity.User;

import java.util.List;

public interface UserService {

    void register(RegisterReqDTO registerReqDTO);
    UserResDTO findUserInfo();
    Long findUserId();
//    void login(String email);

    List<CompanyDTO> getCompanies();

    String[] getCompanyDetail(Long companyId);


}
