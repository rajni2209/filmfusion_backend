package com.filmfusion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmfusion.models.BollywoodEasy;

@Repository
public interface BollywoodEasyRepository extends JpaRepository<BollywoodEasy, Integer>{
	
}

