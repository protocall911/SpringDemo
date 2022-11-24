package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Star {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;

    @Size(min = 2, max = 50, message = "Неверная строка")
    @NotBlank(message = "Строка не должна быть пустой")
    private String name;

    private String class_star;

    private Integer lumen;

    public Star() {
    }

    public Star(String name, String class_star, Integer lumen) {
        this.name = name;
        this.class_star = class_star;
        this.lumen = lumen;
    }

    public Long getUID() {
        return UID;
    }

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass_star() {
        return class_star;
    }

    public void setClass_star(String class_star) {
        this.class_star = class_star;
    }

    public Integer getLumen() {
        return lumen;
    }

    public void setLumen(Integer lumen) {
        this.lumen = lumen;
    }
}
