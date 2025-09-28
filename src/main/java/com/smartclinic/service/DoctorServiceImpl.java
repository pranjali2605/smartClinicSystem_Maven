package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.model.DoctorHistory;
import com.smartclinic.repository.DoctorHistoryRepository;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorHistoryRepository doctorHistoryRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorHistoryRepository doctorHistoryRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorHistoryRepository = doctorHistoryRepository;
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> searchDoctors(String keyword) {
        return doctorRepository.findByNameContainingIgnoreCaseOrSpecializationContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public List<String> getDoctorHistory(Long doctorId) {
        List<DoctorHistory> historyList = doctorHistoryRepository.findByDoctorIdOrderByCreatedAtDesc(doctorId);
        if (historyList == null || historyList.isEmpty()) return Collections.emptyList();

        // Convert to readable strings
        return historyList.stream()
                .map(h -> "Name: " + h.getName() +
                        ", Specializations: " + String.join(", ", h.getSpecializations()) +
                        ", TimeSlots: " + String.join(", ", h.getAvailableTimeSlots()) +
                        ", Contact: " + h.getContact() +
                        ", Qualifications: " + h.getQualifications())
                .collect(Collectors.toList());
    }
}
