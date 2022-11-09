package com.example.bunjang.controller;

import com.example.bunjang.common.response.ExceptionResponse;
import com.example.bunjang.common.response.Message;
import com.example.bunjang.dto.*;
import com.example.bunjang.service.ProjectService;
import com.example.bunjang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;


    /**
     * Post projects
     * 프로젝트 등록
     */
    @PostMapping("")
    public ResponseEntity postProjects(@RequestBody PostProjectDTO postProjectDTO){

        log.info("---------post projects-----------");

        Long userId = userService.findUserId();

        projectService.register(userId, postProjectDTO);

        Message message = Message.builder()
                .result("Project Register Success")
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    /**
     *
     * 프로젝트 가져오기
     */
    @GetMapping("")
    public ResponseEntity getProjects(@RequestParam(required = false) String type) {

        List<GetProjectDTO> projectDTOList = projectService.getProducts(type);

        Message message = Message.builder()
                .result(projectDTOList)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity getProductDetail(@PathVariable Long projectId) {

        Long userId = userService.findUserId();

        ProjectDTO projectDTO = projectService.getProductDetail(userId, projectId);

        Message message = Message.builder()
                .result(projectDTO)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);

    }



    @PostMapping("/{projectId}/communities")
        public ResponseEntity postProjectCommunity(@PathVariable Long projectId, @RequestBody PostCommunityDTO postCommunityDTO){

        Long userId = userService.findUserId();

        projectService.postCommunity(userId, projectId, postCommunityDTO);

        Message message = Message.builder()
                .result("Post Community Success")
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/communities")
    public ResponseEntity getProjectCommunity(@PathVariable Long projectId){

        List<CommunityDTO> result = projectService.getCommunity(projectId);
        Message message = Message.builder()
                .result(result)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }



    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity IdNotFoundException(Exception exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity EmptyResultDataAccessException(Exception exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}

