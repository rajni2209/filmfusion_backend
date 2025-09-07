package com.filmfusion.apiactive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiActive {
	
	
	@GetMapping("/health")
	public String healthCheck() {
		return "ok";
	}
	
}
