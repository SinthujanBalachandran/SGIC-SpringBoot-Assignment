package com.example.Spring_Boot_Defect_Tracker.controlers;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_Boot_Defect_Tracker.entity.Defect;
import com.example.Spring_Boot_Defect_Tracker.repositories.DefectRepositories;
import com.example.Spring_Boot_Defect_Tracker.repositories.ProjectRepositories;

@RestController
@RequestMapping("/api/v2")
public class DefectController {
	
	@Autowired
	DefectRepositories defectRepo;
	@Autowired
	ProjectRepositories proRepo;
	

//	@GetMapping("/defect")
//	public List<Defect> getDefect(){
//		return defectRepo.findAll();
//	}
	
	@GetMapping("/project/{projectId}/defect")
    public Page<Defect> getAllCommentsByProjectId(@PathVariable (value = "projectId") Long projectId,org.springframework.data.domain.Pageable pageable) {
        return defectRepo.findByProjectId(projectId,pageable);
    }
	
	@DeleteMapping("/project/{projectId}/defect/{defectId}")
    public Optional<Object> deleteDefect(@PathVariable (value = "projectId") Long projectId,
                              @PathVariable (value = "defectId") Long defectId) {
        return defectRepo.findByIdAndProjectId(defectId, projectId).map(defect -> {
            defectRepo.delete(defect);
            return ResponseEntity.ok().build();
        });
    }
	
}
