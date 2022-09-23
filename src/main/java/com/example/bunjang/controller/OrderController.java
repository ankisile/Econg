package com.example.bunjang.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/app/orders")
public class OrderController {

    //get order
//    프로젝트 이름, 리워드 아이디 전달
    //post order //db에 일단 저장
    //post order complete  -> orders/payments/complete
    //get order complete

}
