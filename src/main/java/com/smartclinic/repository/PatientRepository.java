package com.smartclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartclinic.model.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);

    List<Patient> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
            String name, String email, String phone);
}

