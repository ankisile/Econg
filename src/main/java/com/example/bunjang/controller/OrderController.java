package com.example.bunjang.controller;

import com.example.bunjang.common.kakaopay.ApproveResponse;
import com.example.bunjang.common.kakaopay.ReadyResponse;
import com.example.bunjang.common.response.Message;
import com.example.bunjang.dto.GetOrderDTO;
import com.example.bunjang.dto.GetProjectDTO;
import com.example.bunjang.dto.OrderDTO;
import com.example.bunjang.dto.PostOrderDTO;
import com.example.bunjang.service.OrderService;
import com.example.bunjang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;


    @GetMapping("/pay")
    public ResponseEntity getOrderPage(@RequestParam Long rewardId){

        GetOrderDTO result = orderService.getOrderPage(rewardId);

        Message message = Message.builder()
                .result(result)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PostMapping("/pay")
    public ResponseEntity payKakaoPay(@RequestBody PostOrderDTO postOrderDTO) {

        log.info("kakaoPay post............................................");

        Long userId = userService.findUserId();

        ReadyResponse readyResponse = orderService.payReady(userId, postOrderDTO);

        Message message = Message.builder()
                .result(readyResponse.getNext_redirect_app_url())
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    // 결제승인요청
    @GetMapping("/pay/completed")
    public ResponseEntity payCompleted(@RequestParam("pg_token") String pgToken
                                       ) {

        log.info("결제 요청");
        log.info(pgToken);

        // 카카오 결재 요청하기
        ApproveResponse approveResponse = orderService.payApprove(pgToken);

        orderService.saveOrder(approveResponse);


        Message message = Message.builder()
                .result("KakaoPay Completed")
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/pay/cancel")
    public ResponseEntity payCanceled() {

        Message message = Message.builder()
                .result("KakaoPay Canceled")
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/pay/fail")
    public ResponseEntity payFailed() {

        Message message = Message.builder()
                .result("KakaoPay Canceled")
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping("/detail/{orderId}")
    public ResponseEntity getDetailOrder(@PathVariable Long orderId){

        OrderDTO orderDTO = orderService.getOrderDetail(orderId);

        Message message = Message.builder()
                .result(orderDTO)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getOrderProjects(){

        Long userId = userService.findUserId();

        List<GetOrderDTO> result = orderService.getOrderProjects(userId);

        Message message = Message.builder()
                .result(result)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
