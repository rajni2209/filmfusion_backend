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

import com.filmfusion.Repository.BollywoodMediumRepository;
import com.filmfusion.models.BollywoodMedium;

@RestController
@RequestMapping("/bollywood/medium")
public class BollywoodMediumController {
	
	
	private final BollywoodMediumRepository bollywoodMediumRepository;
	
	public BollywoodMediumController(BollywoodMediumRepository bollywoodMediumRepository) {
		this.bollywoodMediumRepository = bollywoodMediumRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<BollywoodMedium>> saveBollywoodMedium(@RequestBody List<BollywoodMedium> bollywoodMedium) {
		 List<BollywoodMedium> saveBollywoodMedium = bollywoodMediumRepository.saveAll(bollywoodMedium);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveBollywoodMedium);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatBollywoodMedium(@PathVariable("level_no") int level_no  ,@RequestBody BollywoodMedium bollywoodMedium) {
		if(bollywoodMediumRepository.existsById(level_no)) {
			bollywoodMedium.setLevel_no(level_no);
			BollywoodMedium upBollywoodMedium =  bollywoodMediumRepository.save(bollywoodMedium);
			return ResponseEntity.ok(upBollywoodMedium);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglBollywoodMedium(@PathVariable("level_no") int level_no) {
			Optional<BollywoodMedium> byId = bollywoodMediumRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<BollywoodMedium>> findAllBollywoodMediums(){
		 return ResponseEntity.ok(bollywoodMediumRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(bollywoodMediumRepository.existsById(level_no)) {
			bollywoodMediumRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
