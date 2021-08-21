package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.MARCH;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository) {
        return args -> {
            Student vu_ngoc_duyet = new Student(
                    "Vu Ngoc Duyet",
                    "duyetvu@gmail.com",
                    LocalDate.of(2001, MARCH, 1)
            );

            Student lai_van_ban = new Student(
                    "Lai Van Ban",
                    "vanban@gmail.com",
                    LocalDate.of(2001, MARCH, 31)
            );

            studentRepository.saveAll(
                    List.of(vu_ngoc_duyet, lai_van_ban)
            );
        };
    }
}
