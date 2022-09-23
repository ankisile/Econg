package com.example.bunjang.repository;

import com.example.bunjang.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {

    List<ProjectImage> findByProject_Id(long id);
//
//    ProjectImage findByProduct_ProductIdAndAndRepresent(long id, boolean t);
}
