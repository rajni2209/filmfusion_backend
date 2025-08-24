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

import com.filmfusion.Repository.KollywoodEasyRepository;
import com.filmfusion.models.KollywoodEasy;


@RestController
@RequestMapping("/kollywood/easy")
public class KollywoodEasyController {
	
	
	private final KollywoodEasyRepository kollywoodEasyRepository;
	
	public KollywoodEasyController(KollywoodEasyRepository kollywoodEasyRepository) {
		this.kollywoodEasyRepository = kollywoodEasyRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<KollywoodEasy>> saveKollywoodEasy(@RequestBody List<KollywoodEasy> kollywoodEasy) {
		 List<KollywoodEasy> saveKollywoodEasy = kollywoodEasyRepository.saveAll(kollywoodEasy);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveKollywoodEasy);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatKollywoodEasy(@PathVariable("level_no") int level_no  ,@RequestBody KollywoodEasy kollywoodEasy) {
		if(kollywoodEasyRepository.existsById(level_no)) {
			kollywoodEasy.setLevel_no(level_no);
			KollywoodEasy upKollywoodEasy =  kollywoodEasyRepository.save(kollywoodEasy);
			return ResponseEntity.ok(upKollywoodEasy);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglKollywoodEasy(@PathVariable("level_no") int level_no) {
			Optional<KollywoodEasy> byId = kollywoodEasyRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<KollywoodEasy>> findAllKollywoodEasies(){
		 return ResponseEntity.ok(kollywoodEasyRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(kollywoodEasyRepository.existsById(level_no)) {
			kollywoodEasyRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
