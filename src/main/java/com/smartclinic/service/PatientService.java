package com.smartclinic.service;

import com.smartclinic.model.Patient;
import com.smartclinic.model.PatientHistory;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    void deletePatient(Long id);
    List<Patient> searchPatients(String keyword);

    // new for history
    List<PatientHistory> getPatientHistory(Long patientId);
}