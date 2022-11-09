package com.example.bunjang.service;

import com.example.bunjang.common.exception.IdNotFoundException;
import com.example.bunjang.dto.GetProjectDTO;
import com.example.bunjang.entity.Favorites;
import com.example.bunjang.entity.Project;
import com.example.bunjang.entity.User;
import com.example.bunjang.repository.FavoritesRepository;
import com.example.bunjang.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final ProjectRepository projectRepository;
    private final FavoritesRepository favoritesRepository;


    @Transactional
    @Override
    public String pushLikes(Long userId, Long projectId) {
        if(projectRepository.findById(projectId).isEmpty()){
            throw new IdNotFoundException("존재하지 않는 상품입니다");
        }
        Optional<Favorites> result = favoritesRepository.findByUser_IdAndProject_Id(userId, projectId);

        if(result.isPresent()){
            favoritesRepository.delete(result.get());
            return "찜 해제";
        }
        else{
            User user = User.builder().id(userId).build();
            Project project = Project.builder().id(projectId).build();
            Favorites favorites = Favorites.builder().user(user).project(project).build();
            favoritesRepository.save(favorites);
            return "찜 등록";
        }

    }

    @Transactional
    @Override
    public List<GetProjectDTO> getLikes(Long userId) {

        List<Favorites> result = favoritesRepository.findByUser_Id(userId);

        return result.stream().map(str->{
            Long id = str.getProject().getId();
            Optional<Project> res = projectRepository.findById(id);
            Project project =res.get();
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

        }).collect(Collectors.toList());

    }
}
