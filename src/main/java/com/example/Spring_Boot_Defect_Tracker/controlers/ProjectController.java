
package com.example.Spring_Boot_Defect_Tracker.controlers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_Boot_Defect_Tracker.entity.Project;
import com.example.Spring_Boot_Defect_Tracker.repositories.ProjectRepositories;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

	@Autowired
	ProjectRepositories repository;

	@PostMapping(value = "/project")
	public ResponseEntity<?> createNote(@RequestBody Project project) {
		repository.save(project);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@GetMapping("/project")
	public List<Project> getProject() {
		return repository.findAll();
	}
	
	 @DeleteMapping("/project/{projectId}")
	    public ResponseEntity<?> deletePost(@PathVariable Long projectId) {
	        return repository.findById(projectId).map(project -> {
	            repository.delete(project);
	            return ResponseEntity.ok().build();
	        }).orElseThrow();
	    }
	 
	 @PutMapping("/project/{projectId}")
	    public Optional<Object> updatePost(@PathVariable Long projectId, @Valid @RequestBody Project projectRequest) {
	        return repository.findById(projectId).map(project -> {
	        	project.setProjectName(projectRequest.getProjectName());
	        	project.setProjectDescription(projectRequest.getProjectDescription());
	            return repository.save(project);
	        });
	    }
	 
	 
	
}
