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

import com.filmfusion.Repository.TollywoodMediumRepository;
import com.filmfusion.models.TollywoodMedium;

@RestController
@RequestMapping("/tollywood/medium")
public class TollywoodMediumController {
	
	
	private final TollywoodMediumRepository tollywoodMediumRepository;
	
	public TollywoodMediumController(TollywoodMediumRepository tollywoodMediumRepository) {
		this.tollywoodMediumRepository = tollywoodMediumRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<TollywoodMedium>> saveTollywoodMedium(@RequestBody List<TollywoodMedium> tollywoodMedium) {
		 List<TollywoodMedium> saveTollywoodMedium = tollywoodMediumRepository.saveAll(tollywoodMedium);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveTollywoodMedium);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatTollywoodMedium(@PathVariable("level_no") int level_no  ,@RequestBody TollywoodMedium tollywoodMedium) {
		if(tollywoodMediumRepository.existsById(level_no)) {
			tollywoodMedium.setLevel_no(level_no);
			TollywoodMedium upTollywoodMedium =  tollywoodMediumRepository.save(tollywoodMedium);
			return ResponseEntity.ok(upTollywoodMedium);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglTollywoodMedium(@PathVariable("level_no") int level_no) {
			Optional<TollywoodMedium> byId = tollywoodMediumRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<TollywoodMedium>> findAllTollywoodMediums(){
		 return ResponseEntity.ok(tollywoodMediumRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(tollywoodMediumRepository.existsById(level_no)) {
			tollywoodMediumRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
