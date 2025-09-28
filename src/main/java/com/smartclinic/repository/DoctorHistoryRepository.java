package com.smartclinic.repository;

import com.smartclinic.model.DoctorHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface DoctorHistoryRepository extends MongoRepository<DoctorHistory, String> {

    // Fetch history by doctorId, sorted newest first
    List<DoctorHistory> findByDoctorIdOrderByCreatedAtDesc(Long doctorId);
}
