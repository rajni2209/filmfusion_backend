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

import com.filmfusion.Repository.TollywoodHardRepository;
import com.filmfusion.models.TollywoodHard;

@RestController
@RequestMapping("/tollywood/hard")
public class TollywoodHardController {
	
	
	private final TollywoodHardRepository tollywoodHardRepository;
	
	public TollywoodHardController(TollywoodHardRepository tollywoodHardRepository) {
		this.tollywoodHardRepository = tollywoodHardRepository;
	}
	
	@PostMapping
	public ResponseEntity<List<TollywoodHard>> saveTollywoodHard(@RequestBody List<TollywoodHard> tollywoodHard) {
		List<TollywoodHard> saveTollywoodHard = tollywoodHardRepository.saveAll(tollywoodHard);
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveTollywoodHard);
	}
	
	
	@PutMapping("/{level_no}")
	public ResponseEntity<?> updatTollywoodHard(@PathVariable("level_no") int level_no  ,@RequestBody TollywoodHard tollywoodHard) {
		if(tollywoodHardRepository.existsById(level_no)) {
			tollywoodHard.setLevel_no(level_no);
			TollywoodHard upTollywoodHard =  tollywoodHardRepository.save(tollywoodHard);
			return ResponseEntity.ok(upTollywoodHard);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level_no " + level_no +" not found");
	}
	
	
	@GetMapping("/{level_no}")
	public ResponseEntity<?> getSinglTollywoodHard(@PathVariable("level_no") int level_no) {
			Optional<TollywoodHard> byId = tollywoodHardRepository.findById(level_no);
			if (byId.isPresent()) {
				return ResponseEntity.ok(byId.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("level not found with level_no" + " " +level_no);
	}
	
	@GetMapping
	public ResponseEntity<List<TollywoodHard>> findAllTollywoodHards(){
		 return ResponseEntity.ok(tollywoodHardRepository.findAll());
	}
	
	@DeleteMapping("/{level_no}")
	public ResponseEntity<?> delete(@PathVariable int level_no) {
		if(tollywoodHardRepository.existsById(level_no)) {
			tollywoodHardRepository.deleteById(level_no);
			return ResponseEntity.ok("deleted level_no" + level_no + "successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find level_no " + level_no);
	}
	
	
}
