package com.example.bunjang.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CommunityDTO {

    @NonNull private Long id;

    @NonNull
    private String content;

    @NonNull
    private LocalDateTime updatedAt;

    @NonNull
    private Long userId;

    @NonNull
    private String useProfileUrl;

    @NonNull
    private String userName;

    private Long projectId;

    private String projectName;
}
