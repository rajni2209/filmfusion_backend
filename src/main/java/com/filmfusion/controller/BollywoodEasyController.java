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

import com.filmfusion.Repository.BollywoodEasyRepository;
import com.filmfusion.models.BollywoodEasy;

@RestController
@RequestMapping("/bollywood/easy")
public class BollywoodEasyController {
	
	
	private final BollywoodEasyRepository bollywoodEasyRepository;
	
	public BollywoodEasyController(BollywoodEasyRepository bollywoodEasyRepository) {
		this.bollywoodEasyRepository = bollywoodEasyRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<BollywoodEasy>> saveBollywoodEasy(@RequestBody List<BollywoodEasy> bollywoodEasy) {
		 List<BollywoodEasy>  saveBollywoodEasy = bollywoodEasyRepository.saveAll(bollywoodEasy);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveBollywoodEasy);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatBollywoodEasy(@PathVariable("level_no") int level_no  ,@RequestBody BollywoodEasy bollywoodEasy) {
		if(bollywoodEasyRepository.existsById(level_no)) {
			bollywoodEasy.setLevel_no(level_no);
			BollywoodEasy upBollywoodEasy =  bollywoodEasyRepository.save(bollywoodEasy);
			return ResponseEntity.ok(upBollywoodEasy);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglBollywoodEasy(@PathVariable("level_no") int level_no) {
			Optional<BollywoodEasy> byId = bollywoodEasyRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<BollywoodEasy>> findAllBollywoodEasies(){
		 return ResponseEntity.ok(bollywoodEasyRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(bollywoodEasyRepository.existsById(level_no)) {
			bollywoodEasyRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
