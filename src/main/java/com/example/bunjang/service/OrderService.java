package com.example.bunjang.service;

import com.example.bunjang.common.kakaopay.ApproveResponse;
import com.example.bunjang.dto.GetOrderDTO;
import com.example.bunjang.dto.GetProjectDTO;
import com.example.bunjang.dto.OrderDTO;
import com.example.bunjang.dto.PostOrderDTO;

public interface OrderService {

    GetOrderDTO getOrderPage(Long rewardId);

    String payReady(Long userId, PostOrderDTO postOrderDTO);

    ApproveResponse payApprove(String pgToken);

    void saveOrder(ApproveResponse approveResponse);

    OrderDTO getOrderDetail(Long orderId);

//    GetProjectDTO getOrderProjects(Long userId);
//    get orders
}
