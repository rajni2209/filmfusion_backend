package com.filmfusion.models;

import java.util.List;

import com.filmfusion.conterter.ListToJsonConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "bollywood_hard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BollywoodHard {

	
	@Id
	private int level_no;
	private Character actor_FirstLetter;
	private Character actress_FirstLetter;
	private Character movie_FirstLetter;
	
	@Convert(converter = ListToJsonConverter.class)
	@Column(columnDefinition = "JSON")
	private List<String> actor_option;
	@Convert(converter = ListToJsonConverter.class)
	@Column(columnDefinition = "JSON")
	private List<String> actress_option;
	@Convert(converter = ListToJsonConverter.class)
	@Column(columnDefinition = "JSON")
	private List<String> movie_option;
	
	private String correctActor;
	private String correctActress;
	private String correctMovie;
	
	private String hint;
	
	
}

