package com.smartclinic.repository;

import com.smartclinic.model.AppointmentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentMongoRepository extends MongoRepository<AppointmentDocument, String> {
    List<AppointmentDocument> findByDoctorId(Long doctorId);
    List<AppointmentDocument> findByPatientId(Long patientId);
}
