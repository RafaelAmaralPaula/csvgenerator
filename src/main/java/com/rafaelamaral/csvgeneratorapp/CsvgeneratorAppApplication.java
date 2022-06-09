package com.rafaelamaral.csvgeneratorapp;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.rafaelamaral.csvgeneratorapp.engine.CsvFileGenerator;
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
	public CommandLineRunner run(CsvFileGenerator generationFileCsv)throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
		return args -> {
			generationFileCsv.generate();
		};
	}


}
