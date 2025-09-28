package com.smartclinic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "patient_history")
public class PatientHistory {

    @Id
    private String id;
    private Long patientId;
    private String name;
    private String email;
    private String phone;
    private Integer age;
    private String gender;
    private String description;
    private String address;
    private LocalDateTime createdAt;

    public PatientHistory() {
        this.createdAt = LocalDateTime.now();
    }

    public PatientHistory(Patient p) {
        this.patientId = p.getId();
        this.name = p.getName();
        this.email = p.getEmail();
        this.phone = p.getPhone();
        this.age = p.getAge();
        this.gender = p.getGender();
        this.description = p.getDescription();
        this.address = p.getAddress();
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    // ...
}
