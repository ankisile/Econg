package com.example.bunjang.common.kakaopay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReadyResponse {

    private String tid;
    private String next_redirect_app_url;
    private String android_app_scheme;

}
