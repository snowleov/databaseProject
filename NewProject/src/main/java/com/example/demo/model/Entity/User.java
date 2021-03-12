package com.example.demo.model.Entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity(name = "user")
@Data
@EnableAutoConfiguration
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "fullname")
    public String fullname;

    @Column(name = "age")
    public Integer age;

    @Column(name = "job")
    public String job;


}
