package com.smartclinic.controller;

import com.smartclinic.model.Appointment;
import com.smartclinic.model.Patient;
import com.smartclinic.model.Doctor;
import com.smartclinic.service.AppointmentService;
import com.smartclinic.service.PatientService;
import com.smartclinic.service.DoctorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService,
                                 PatientService patientService,
                                 DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    // ✅ Show all appointments + form
    @GetMapping
    public String listAppointments(@RequestParam(value = "keyword", required = false) String keyword,
                                   Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments(); // add search if needed

        model.addAttribute("appointments", appointments);
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("keyword", keyword);
        return "appointments"; // appointments.html
    }

//     ✅ Save new appointment
    @PostMapping
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
        if (appointment.getPatient() != null && appointment.getPatient().getId() != null) {
            Patient patient = patientService.getPatientById(appointment.getPatient().getId());
            appointment.setPatient(patient);
        }
        if (appointment.getDoctor() != null && appointment.getDoctor().getId() != null) {
            Doctor doctor = doctorService.getDoctorById(appointment.getDoctor().getId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            appointment.setDoctor(doctor);
        }

        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }

    // ✅ Edit appointment (reuse same page)
    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointments";
    }

    // ✅ Update appointment
    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable Long id,
                                    @ModelAttribute("appointment") Appointment appointment) {
        Appointment existing = appointmentService.getAppointmentById(id);
        if (existing != null) {
            existing.setDate(appointment.getDate());

            if (appointment.getPatient() != null && appointment.getPatient().getId() != null) {
                existing.setPatient(patientService.getPatientById(appointment.getPatient().getId()));
            }
//            if (appointment.getDoctor() != null && appointment.getDoctor().getId() != null) {
//                existing.setDoctor(doctorService.getDoctorById(appointment.getDoctor().getId()));
//            }

            appointmentService.saveAppointment(existing);
        }
        return "redirect:/appointments";
    }

    // ✅ Delete appointment
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}
