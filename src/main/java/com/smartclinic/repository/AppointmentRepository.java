package com.smartclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartclinic.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

