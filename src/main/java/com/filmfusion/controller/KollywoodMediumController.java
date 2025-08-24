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

import com.filmfusion.Repository.KollywoodMediumRepository;
import com.filmfusion.models.KollywoodMedium;

@RestController
@RequestMapping("/kollywood/medium")
public class KollywoodMediumController {
	
	
	private final KollywoodMediumRepository kollywoodMediumRepository;
	
	public KollywoodMediumController(KollywoodMediumRepository kollywoodMediumRepository) {
		this.kollywoodMediumRepository = kollywoodMediumRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<KollywoodMedium>> saveKollywoodMedium(@RequestBody List<KollywoodMedium> kollywoodMedium) {
		List<KollywoodMedium> saveKollywoodMedium = kollywoodMediumRepository.saveAll(kollywoodMedium);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveKollywoodMedium);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatKollywoodMedium(@PathVariable("level_no") int level_no  ,@RequestBody KollywoodMedium kollywoodMedium) {
		if(kollywoodMediumRepository.existsById(level_no)) {
			kollywoodMedium.setLevel_no(level_no);
			KollywoodMedium upKollywoodMedium =  kollywoodMediumRepository.save(kollywoodMedium);
			return ResponseEntity.ok(upKollywoodMedium);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglKollywoodMedium(@PathVariable("level_no") int level_no) {
			Optional<KollywoodMedium> byId = kollywoodMediumRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<KollywoodMedium>> findAllKollywoodMediums(){
		 return ResponseEntity.ok(kollywoodMediumRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(kollywoodMediumRepository.existsById(level_no)) {
			kollywoodMediumRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
