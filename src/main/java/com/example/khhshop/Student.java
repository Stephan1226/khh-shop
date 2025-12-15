package com.example.khhshop;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;

    public void setAge(int age) {
        if (age < 0 || age >= 100) {
            throw new IllegalArgumentException("Age cannot be negative and less than 100");
        }
        this.age = age;
    }
}
