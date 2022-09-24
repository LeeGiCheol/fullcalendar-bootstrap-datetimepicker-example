package me.gicheol.calendar.example;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalendarConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
