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

import com.filmfusion.Repository.TollywoodEasyRepository;
import com.filmfusion.models.TollywoodEasy;

@RestController
@RequestMapping("/tollywood/easy")
public class TollywoodEasyController {
	
	
	private final TollywoodEasyRepository tollywoodEasyRepository;
	
	public TollywoodEasyController(TollywoodEasyRepository tollywoodEasyRepository) {
		this.tollywoodEasyRepository = tollywoodEasyRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<TollywoodEasy>> saveTollywoodEasy(@RequestBody List<TollywoodEasy> tollywoodEasy) {
		 List<TollywoodEasy> saveTollywoodEasy = tollywoodEasyRepository.saveAll(tollywoodEasy);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveTollywoodEasy);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatTollywoodEasy(@PathVariable("level_no") int level_no  ,@RequestBody TollywoodEasy tollywoodEasy) {
		if(tollywoodEasyRepository.existsById(level_no)) {
			tollywoodEasy.setLevel_no(level_no);
			TollywoodEasy upTollywoodEasy =  tollywoodEasyRepository.save(tollywoodEasy);
			return ResponseEntity.ok(upTollywoodEasy);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglTollywoodEasy(@PathVariable("level_no") int level_no) {
			Optional<TollywoodEasy> byId = tollywoodEasyRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<TollywoodEasy>> findAllTollywoodEasies(){
		 return ResponseEntity.ok(tollywoodEasyRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(tollywoodEasyRepository.existsById(level_no)) {
			tollywoodEasyRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
