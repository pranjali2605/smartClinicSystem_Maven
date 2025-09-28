package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(Long id);
    void deleteDoctor(Long id);
    List<Doctor> searchDoctors(String keyword);
    List<String> getDoctorHistory(Long doctorId); // Mongo history
}
