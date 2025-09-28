package com.smartclinic.repository;

import com.smartclinic.model.PatientHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientHistoryRepository extends MongoRepository<PatientHistory, String> {
    List<PatientHistory> findByPatientId(Long patientId);
}
