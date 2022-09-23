package com.example.bunjang.controller;

import com.example.bunjang.common.exception.CommentException;
import com.example.bunjang.common.exception.IdNotFoundException;
import com.example.bunjang.common.response.ExceptionResponse;
import com.example.bunjang.common.response.Message;
import com.example.bunjang.dto.CommunityDTO;
import com.example.bunjang.dto.PostCommunityDTO;
import com.example.bunjang.service.CommunityService;
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
@RequestMapping("/app/communities")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("")
    public ResponseEntity getMyCommunityList(){
        List<CommunityDTO> result = communityService.getMyCommunityList();
        Message message = Message.builder()
                .result(result)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @DeleteMapping("/{communityId}")
    public ResponseEntity deleteCommunityReply( @PathVariable("communityId") Long communityId){

        String result = communityService.deleteCommunity(communityId);
        Message message = Message.builder()
                .result(result)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/{communityId}")
    public ResponseEntity modifyCommunityReply(@PathVariable("communityId") Long communityId, @RequestBody PostCommunityDTO postCommunityDTO){

        String result = communityService.modifyCommunity(communityId, postCommunityDTO);
        Message message = Message.builder()
                .result(result)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity EmptyResultDataAccessException(Exception exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity CommentException(Exception exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity IdNotFoundException(Exception exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
