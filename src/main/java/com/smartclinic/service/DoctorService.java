package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
    List<Doctor> searchDoctors(String keyword);

}
