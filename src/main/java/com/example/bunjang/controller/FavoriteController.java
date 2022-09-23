package com.example.bunjang.controller;

import com.example.bunjang.common.response.Message;
import com.example.bunjang.dto.FavoriteDTO;
import com.example.bunjang.dto.GetProjectDTO;
import com.example.bunjang.service.FavoriteService;
import com.example.bunjang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity pushLikes(@RequestBody FavoriteDTO favoriteDTO){
        Long userId = userService.findUserId();
        String str = favoriteService.pushLikes(userId, favoriteDTO.getProjectId());
        Message message = Message.builder()
                .result(str)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getLikes(){
        Long userId = userService.findUserId();
        List<GetProjectDTO> projectDTOList = favoriteService.getLikes(userId);

        Message message = Message.builder()
                .result(projectDTOList)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
