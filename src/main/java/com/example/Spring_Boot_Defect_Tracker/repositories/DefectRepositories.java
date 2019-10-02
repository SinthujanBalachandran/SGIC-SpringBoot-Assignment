package com.example.Spring_Boot_Defect_Tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.Spring_Boot_Defect_Tracker.entity.Defect;

@Repository
public interface DefectRepositories extends JpaRepository<Defect, Long> {
	Page<Defect> findByProjectId(Long projectId, Pageable pageable);
    Optional<Defect> findByIdAndProjectId(Long id, Long projectId);
}
