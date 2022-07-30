package com.example.bunjang.service;

//import com.example.bunjang.config.Salt;
//import com.example.bunjang.config.SaltUtil;
import com.example.bunjang.common.Role;
import com.example.bunjang.dto.*;
import com.example.bunjang.entity.Product;
import com.example.bunjang.entity.ProductImage;
import com.example.bunjang.entity.User;
import com.example.bunjang.exception.DuplicateMemberException;
import com.example.bunjang.repository.UserRepository;
import com.example.bunjang.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(RegisterReqDTO registerReqDTO) {

        if (userRepository.findByEmail(registerReqDTO.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입된 유저입니다.");
        }

        log.info(registerReqDTO.getPassword());
        String encodePassword = passwordEncoder.encode(registerReqDTO.getPassword());

        User user = User.builder()
                .email(registerReqDTO.getEmail())
                .password(encodePassword)
                .phone(registerReqDTO.getPhoneNumber())
                .userName(registerReqDTO.getUserName())
                .point(0)
                .activated(true)
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    public UserResDTO findUserInfo() {
        String email = SecurityUtil.getCurrentEmail().orElseThrow(() ->
                new RuntimeException("Security Context에 인증 정보가 없습니다."));

        return userRepository.findByEmail(email)
                .map(user -> new UserResDTO(user))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 user 입니다. email=" + email));
    }

    public Long findUserId(){
        String email = SecurityUtil.getCurrentEmail().orElseThrow(() ->
                new RuntimeException("Security Context에 인증 정보가 없습니다."));

        return userRepository.findByEmail(email).get().getUserId();
    }

    @Override
    public List<CompanyDTO> getCompanies() {

        List<User> user = userRepository.findByRole(Role.ROLE_ADMIN);

        return user.stream().map(arr -> {
            return new CompanyDTO(arr.getUserName(), arr.getProfileUrl());
        }).collect(Collectors.toList());
    }

    @Override
    public String[] getCompanyDetail(Long companyId) {

        Optional<User> result = userRepository.findById(companyId);

        if(result.isEmpty())
            new RuntimeException("존재하지 않는 user 입니다. companyId=" + companyId);

        User user = result.get();

        return new String[]{user.getUserName(), user.getProfileUrl()};


    }


}
