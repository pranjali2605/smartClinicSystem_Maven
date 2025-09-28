package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.model.Patient;
import com.smartclinic.model.PatientHistory;
import com.smartclinic.repository.PatientRepository;
import com.smartclinic.repository.PatientHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientHistoryRepository historyRepository;

    public PatientServiceImpl(PatientRepository patientRepository, PatientHistoryRepository historyRepository) {
        this.patientRepository = patientRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        Patient saved = patientRepository.save(patient);

        // Save to history
        historyRepository.save(new PatientHistory(saved));

        return saved;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> searchPatients(String keyword) {
        return patientRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                keyword, keyword, keyword);
    }

    // new method for history
    @Override
    public List<PatientHistory> getPatientHistory(Long patientId) {
        return historyRepository.findByPatientId(patientId);
    }


}
