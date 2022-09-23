package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProjectDTO {

    private Long id;

    private String title;

    private LocalDate openingDate;

    private LocalDate closingDate;

    private int totalAmount;

    private int achievedRate;

    private String summary;

    private String thumbnail;

    private boolean authenticate;

    private boolean favorite;

    private String user;
}
