package com.interviewmaster;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InterviewMasterApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(InterviewMasterApplication.class, args);
    }

    //Logger log = LoggerFactory.getLogger(InterviewMasterApplication.class);

    @Bean
    public Logger logger()
    {
        return LoggerFactory.getLogger(InterviewMasterApplication.class);
    }
}
