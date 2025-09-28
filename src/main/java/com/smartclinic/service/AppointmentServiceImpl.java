package com.smartclinic.service;

import com.smartclinic.model.Appointment;
import com.smartclinic.model.AppointmentDocument;
import com.smartclinic.repository.AppointmentRepository;        // JPA
import com.smartclinic.repository.AppointmentMongoRepository;  // Mongo
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;         // JPA
    private final AppointmentMongoRepository appointmentMongoRepository; // Mongo

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AppointmentMongoRepository appointmentMongoRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMongoRepository = appointmentMongoRepository;
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        // 1️⃣ Save in SQL (JPA)
        Appointment saved = appointmentRepository.save(appointment);

        // 2️⃣ Convert LocalDateTime to java.util.Date for Mongo
        Date appointmentDate = Date.from(saved.getDate().atZone(ZoneId.systemDefault()).toInstant());

        // 3️⃣ Save in Mongo
        AppointmentDocument mongoDoc = new AppointmentDocument(
                saved.getDoctor().getId(),
                saved.getDoctor().getName(),
                saved.getPatient().getId(),
                saved.getPatient().getName(),
                appointmentDate,
                null,        // timeSlot (optional)
                "BOOKED"     // status
        );

        appointmentMongoRepository.save(mongoDoc);

        return saved;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
