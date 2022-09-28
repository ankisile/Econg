package com.example.bunjang.service;

import com.example.bunjang.common.exception.IdNotFoundException;
import com.example.bunjang.common.kakaopay.ApproveResponse;
import com.example.bunjang.common.kakaopay.ReadyResponse;
import com.example.bunjang.dto.*;
import com.example.bunjang.entity.*;
import com.example.bunjang.repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final RewardRepository rewardRepository;
    private final OrdersRepository ordersRepository;

    private PostOrderDTO postOrderDTO;
    private Long userId;
    private ReadyResponse readyResponse;


    @Transactional
    @Override
    public GetOrderDTO getOrderPage(Long rewardId) {
        Reward reward = rewardRepository.findById(rewardId).orElseThrow(() -> new IdNotFoundException("rewardId is not exist"));

        return new GetOrderDTO(reward.getProject().getId(), reward.getProject().getTitle(), reward.getProject().getThumbnail(), rewardId,reward.getName(),reward.getPrice(),reward.getCombination());
    }

    @Override
    public String payReady(Long userId, PostOrderDTO postOrderDTO) {

        this.postOrderDTO = postOrderDTO;
        this.userId = userId;

        String itemName = postOrderDTO.getRewardName();

        String order_id = userId+"_" + itemName;//

        // 카카오가 요구한 결제요청request값을 담아줍니다.
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("partner_order_id", order_id); //
        parameters.add("partner_user_id", "Econg"); //
        parameters.add("item_name", itemName);
        parameters.add("quantity", "1");
        parameters.add("total_amount", String.valueOf(postOrderDTO.getPrice()));
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "https://localhost:8080/app/orders/pay/completed"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "https://localhost:8080/app/orders/pay/cancel"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "https://localhost:8080/app/orders/pay/fail"); // 결제 실패시 넘어갈 url

        log.info("파트너주문아이디:"+ parameters.get("partner_order_id")) ;
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부url요청 통로 열기.
        RestTemplate template = new RestTemplate();

        try{

            String url = "https://kapi.kakao.com/v1/payment/ready";

            // template으로 값을 보내고 받아온 ReadyResponse값 readyResponse에 저장.
            readyResponse = template.postForObject(url, requestEntity, ReadyResponse.class);
            log.info("결재준비 응답객체: " + readyResponse);
            // 받아온 값 return
            return readyResponse.getNext_redirect_app_url();
        }catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    // 결제 승인요청 메서드
    @Override
    public ApproveResponse payApprove(String pgToken) {

        // 주문명 만들기.
        String itemName = postOrderDTO.getRewardName();

        String order_id = userId+"_" + itemName;

        // request값 담기.
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", readyResponse.getTid());
        parameters.add("partner_order_id", order_id); // 주문명
        parameters.add("partner_user_id", "Econg");
        parameters.add("pg_token", pgToken);

        // 하나의 map안에 header와 parameter값을 담아줌.
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부url 통신
        RestTemplate template = new RestTemplate();
        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        template.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });
        String url = "https://kapi.kakao.com/v1/payment/approve";

        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스.
        ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
        log.info("결재승인 응답객체: " + approveResponse);

        return approveResponse;
    }

    @Transactional
    @Override
    public void saveOrder(ApproveResponse approveResponse) {

        Reward reward = Reward.builder().id(postOrderDTO.getRewardId()).build();
        User user = User.builder().id(userId).build();

        Orders orders = Orders.builder()
                .orderName(approveResponse.getItem_name())
                .donation(postOrderDTO.getPrice())
                .orderStatus("PAYCOMPLETED")
                .deliveryAddress(postOrderDTO.getDeliveryAddress())
                .paymentMethodType(approveResponse.getPayment_method_type())
                .paymentTid(approveResponse.getTid())
                .reward(reward)
                .user(user)
                .build();

        ordersRepository.save(orders);

        reward = rewardRepository.findById(postOrderDTO.getRewardId()).orElseThrow(()->new IdNotFoundException("id is not found"));
        Project project = reward.getProject();

        reward.changeSoldQuantity();
        project.changeAchievedRate();
        project.changeTotalAmount(reward.getPrice());

    }

    @Transactional
    @Override
    public OrderDTO getOrderDetail(Long orderId) {
        Orders orders = ordersRepository.findById(orderId).orElseThrow(()->new IdNotFoundException("id is not found"));

        return new OrderDTO(orders.getPaymentTid(), orders.getOrderName(), orders.getOrderStatus(), orders.getDeliveryAddress(), orders.getPaymentMethodType());
    }

//    @Transactional
//    @Override
//    public GetProjectDTO getOrderProjects(Long userId) {
//
//        List<Orders> ordersList = ordersRepository.findByUser_Id(userId);
//
//        return new GetProjectDTO()
//    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK f2a4d01f35979e50cbb0b443e03681f6"); //
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }



}