package com.smartclinic.controller;

import com.smartclinic.model.Patient;
import com.smartclinic.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // List + search patients
    @GetMapping
    public String listPatients(@RequestParam(value = "keyword", required = false) String keyword,
                               Model model) {
        List<Patient> patients;

        if (keyword != null && !keyword.isEmpty()) {
            patients = patientService.searchPatients(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            patients = patientService.getAllPatients();
        }

        model.addAttribute("patients", patients);
        model.addAttribute("patient", new Patient());  // form object
        return "patients"; // single page
    }

    // Save new patient
    @PostMapping
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    // Edit patient (prefill form)
    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patients";
    }

    // Update patient
    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute("patient") Patient patient) {
        Patient existing = patientService.getPatientById(id);
        if (existing != null) {
            existing.setName(patient.getName());
            existing.setEmail(patient.getEmail());
            existing.setPhone(patient.getPhone());
            existing.setAge(patient.getAge());
            existing.setGender(patient.getGender());
            existing.setDescription(patient.getDescription());
           // existing.setDoctorName(patient.getDoctorName());
            existing.setAddress(patient.getAddress());
            patientService.savePatient(existing);
        }
        return "redirect:/patients";
    }

    // Delete patient
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
