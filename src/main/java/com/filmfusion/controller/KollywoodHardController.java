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

import com.filmfusion.Repository.KollywoodHardRepository;
import com.filmfusion.models.KollywoodHard;


@RestController
@RequestMapping("/kollywood/hard")
public class KollywoodHardController {
	
	
	private final KollywoodHardRepository kollywoodHardRepository;
	
	public KollywoodHardController(KollywoodHardRepository kollywoodHardRepository) {
		this.kollywoodHardRepository = kollywoodHardRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<KollywoodHard>> saveKollywoodHard(@RequestBody List<KollywoodHard> kollywoodHard) {
		List<KollywoodHard> saveBollywoodHard = kollywoodHardRepository.saveAll(kollywoodHard);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveBollywoodHard);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatKollywoodHard(@PathVariable("level_no") int level_no  ,@RequestBody KollywoodHard kollywoodHard) {
		if(kollywoodHardRepository.existsById(level_no)) {
			kollywoodHard.setLevel_no(level_no);
			KollywoodHard upBollywoodHard =  kollywoodHardRepository.save(kollywoodHard);
			return ResponseEntity.ok(upBollywoodHard);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglKollywoodHard(@PathVariable("level_no") int level_no) {
			Optional<KollywoodHard> byId = kollywoodHardRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<KollywoodHard>> findAllKollywoodHards(){
		 return ResponseEntity.ok(kollywoodHardRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(kollywoodHardRepository.existsById(level_no)) {
			kollywoodHardRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
