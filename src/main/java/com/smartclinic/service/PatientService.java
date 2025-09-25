package com.smartclinic.service;

import com.smartclinic.model.Patient;
import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    void deletePatient(Long id);

    // new
    List<Patient> searchPatients(String keyword);
}
