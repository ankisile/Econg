package com.example.bunjang.service;

import com.example.bunjang.common.exception.IdNotFoundException;
import com.example.bunjang.dto.*;
import com.example.bunjang.entity.*;
import com.example.bunjang.repository.*;
import com.example.bunjang.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectImageRepository projectImageRepository;
    private final RewardRepository rewardRepository;
    private final FavoritesRepository favoritesRepository;
    private final CommunityRepository communityRepository;

    @Transactional
    @Override
    public void register(Long userId, PostProjectDTO postProjectDTO) {

        User user = User.builder().id(userId).build();

        Project project = Project.builder()
                .title(postProjectDTO.getTitle())
                .openingDate(postProjectDTO.getOpeningDate())
                .closingDate(postProjectDTO.getClosingDate())
                .goalAmount(postProjectDTO.getGoalAmount())
                .summary(postProjectDTO.getSummary())
                .content(postProjectDTO.getContent())
                .thumbnail(postProjectDTO.getThumbnail())
                .user(user)
                .build();

        projectRepository.save(project);

        List<ProjectImgDTO> projectImgList = postProjectDTO.getProjectImgList();

        for (ProjectImgDTO projectImg:projectImgList) {
            projectImageRepository.save(ProjectImage.builder()
                    .imgUrl(projectImg.getProjectImgUrl())
                    .project(project)
                    .build());
        }

        List<RewardDTO> rewardList = postProjectDTO.getRewardList();

        for (RewardDTO reward:rewardList) {
            rewardRepository.save(Reward.builder()
                    .name(reward.getName())
                    .combination(reward.getCombination())
                    .price(reward.getPrice())
                    .stock(reward.getStock())
                    .project(project).build());
        }

    }

    @Transactional
    @Override
    public List<GetProjectDTO> getProducts(String type) {

        List<Project> result = projectRepository.findByStatusOrderByIdDesc("ONGOING");


        List<GetProjectDTO> list = result.stream().map(project -> {

            LocalDate now = LocalDate.now();

            long period = ChronoUnit.DAYS.between(now, project.getClosingDate());
//            System.out.println(period);
            if(type==null||(type.equals("almost")&&(0<=period&&period<=1)) || (type.equals("achieve")&&90<=project.getAchievedRate()&&project.getAchievedRate()<100) ){
                return new GetProjectDTO(
                        project.getId(),
                        project.getTitle(),
                        project.getOpeningDate(),
                        project.getClosingDate(),
                        project.getTotalAmount(),
                        project.getAchievedRate(),
                        project.getSummary(),
                        project.getThumbnail(),
                        project.getAuthenticate(),
                        project.getStatus(),
                        project.getUser().getNickName());
            }
            return null;
        }).collect(Collectors.toList());

        while (list.remove(null)) {        }
        if(type!=null) {
            Collections.shuffle(list);
        }

        return list;
    }



    @Transactional
    @Override
    public ProjectDTO getProductDetail(Long userId, Long id) {


        Optional<Project> result = projectRepository.findById(id);
        if(result.isEmpty()){
            throw new IdNotFoundException("존재하지 않은 id 입니다.");
        }
        Project project = result.get();

        List<ProjectImage> imgList = projectImageRepository.findByProject_Id(id);
        List<ProjectImgDTO> imgDTOList= imgList.stream().map(arr -> {
            return new ProjectImgDTO(arr.getId(),arr.getImgUrl());
        }).collect(Collectors.toList());

        List<Reward> rewardList = rewardRepository.findByProject_Id(id);
        List<RewardDTO> rewardDTOList= rewardList.stream().map(arr -> {
            return new RewardDTO(arr.getId(), arr.getName(),arr.getPrice(),arr.getStock(),arr.getSoldQuantity(),arr.getCombination());
        }).collect(Collectors.toList());

        Optional<Favorites> favorites = favoritesRepository.findByUser_IdAndProject_Id(userId, project.getId());
        Boolean heart = favorites.isEmpty()? false:true;

        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getOpeningDate(),
                project.getClosingDate(),
                project.getGoalAmount(),
                project.getTotalAmount(),
                project.getAchievedRate(),
                project.getSummary(),
                project.getContent(),
                project.getThumbnail(),
                project.getAuthenticate(),
                heart,
                project.getStatus(),
                project.getUser().getId(),
                project.getUser().getNickName(),
                project.getUser().getAuthenticate(),
                imgDTOList,
                rewardDTOList
        );
    }



    @Transactional
    @Override
    public void postCommunity(Long userId, Long projectId, PostCommunityDTO postCommunityDTO) {

        User user = User.builder().id(userId).build();
        Project project = Project.builder().id(projectId).build();

        Community community = Community.builder().content(postCommunityDTO.getContent()).user(user).project(project).build();

        communityRepository.save(community);

    }


    @Transactional
    @Override
    public List<CommunityDTO> getCommunity(Long projectId) {

        List<Community> result = communityRepository.findByProject_IdOrderByUpdatedAt(projectId);

        return result.stream().map(community->{
            return new CommunityDTO(
                    community.getId(),
                    community.getContent(),
                    community.getUpdatedAt(),
                    community.getUser().getId(),
                    community.getUser().getProfileUrl(),
                    community.getUser().getNickName()
                    );

        }).collect(Collectors.toList());

    }




}