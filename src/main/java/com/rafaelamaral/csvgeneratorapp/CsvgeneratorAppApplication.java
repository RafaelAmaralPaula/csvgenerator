package com.rafaelamaral.csvgeneratorapp;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.rafaelamaral.csvgeneratorapp.service.GenerationFileCsv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class CsvgeneratorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvgeneratorAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(GenerationFileCsv generationFileCsv)throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
		return args -> {
			generationFileCsv.execute();
		};
	}


}
