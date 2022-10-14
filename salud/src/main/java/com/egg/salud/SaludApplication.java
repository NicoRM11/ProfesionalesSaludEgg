package com.egg.salud;

import java.text.SimpleDateFormat;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SaludApplication {
    @Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
        
         @Bean
	public SimpleDateFormat simpleDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd");
	}
        
        
	public static void main(String[] args) {
		SpringApplication.run(SaludApplication.class, args);
	}

}
