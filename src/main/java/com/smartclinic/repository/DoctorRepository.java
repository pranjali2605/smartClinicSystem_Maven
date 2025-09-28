package com.smartclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartclinic.model.Doctor;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByNameContainingIgnoreCaseOrSpecializationContainingIgnoreCase(String name, String specialization);
}
