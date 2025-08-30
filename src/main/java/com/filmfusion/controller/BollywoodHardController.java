package com.filmfusion.controller;

import java.util.List;
import java.util.Optional;

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

import com.filmfusion.Repository.BollywoodHardRepository;
import com.filmfusion.models.BollywoodHard;

@RestController
@RequestMapping("/bollywood/hard")
public class BollywoodHardController {
	
	
	private final BollywoodHardRepository bollywoodHardRepository;
	
	public BollywoodHardController(BollywoodHardRepository bollywoodHardRepository) {
		this.bollywoodHardRepository = bollywoodHardRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<BollywoodHard>> saveBollywoodHard(@RequestBody List<BollywoodHard> bollywoodHard) {
		 List<BollywoodHard> saveBollywoodHard = bollywoodHardRepository.saveAll(bollywoodHard);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveBollywoodHard);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatBollywoodHard(@PathVariable("level_no") int level_no  ,@RequestBody BollywoodHard bollywoodHard) {
		if(bollywoodHardRepository.existsById(level_no)) {
			bollywoodHard.setLevel_no(level_no);
			BollywoodHard upBollywoodHard =  bollywoodHardRepository.save(bollywoodHard);
			return ResponseEntity.ok(upBollywoodHard);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglBollywoodHard(@PathVariable("level_no") int level_no) {
			Optional<BollywoodHard> byId = bollywoodHardRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<BollywoodHard>> findAllBollywoodHards(){
		 return ResponseEntity.ok(bollywoodHardRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(bollywoodHardRepository.existsById(level_no)) {
			bollywoodHardRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
