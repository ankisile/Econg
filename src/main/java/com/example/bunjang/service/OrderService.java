package com.example.bunjang.service;

import com.example.bunjang.common.kakaopay.ApproveResponse;
import com.example.bunjang.common.kakaopay.ReadyResponse;
import com.example.bunjang.dto.GetOrderDTO;
import com.example.bunjang.dto.GetProjectDTO;
import com.example.bunjang.dto.OrderDTO;
import com.example.bunjang.dto.PostOrderDTO;

import java.util.List;

public interface OrderService {

    GetOrderDTO getOrderPage(Long rewardId);

    ReadyResponse payReady(Long userId, PostOrderDTO postOrderDTO);

//    ApproveResponse payApprove(String pgToken, String tid, Long userId, PostOrderDTO postOrderDTO);
    ApproveResponse payApprove(String pgToken);


//    void saveOrder(ApproveResponse approveResponse, String tid, Long userId, PostOrderDTO postOrderDTO);
    void saveOrder(ApproveResponse approveResponse);

    OrderDTO getOrderDetail(Long orderId);

    List<GetOrderDTO> getOrderProjects(Long userId);
//    get orders
}
