package com.example.bunjang.common.kakaopay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ApproveResponse {
    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private String item_name;
    private String item_code;
    private int quantity;
    private LocalDateTime created_at;
    private LocalDateTime approved_at;
    private String payload;
    private Amount amount;
}

