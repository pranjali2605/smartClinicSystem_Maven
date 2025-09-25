package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Show doctors list + form
    @GetMapping
    public String listDoctors(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Doctor> doctors;

        if (keyword != null && !keyword.isEmpty()) {
            doctors = doctorService.searchDoctors(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            doctors = doctorService.getAllDoctors();
        }

        model.addAttribute("doctors", doctors);
        model.addAttribute("doctor", new Doctor()); // for create form
        return "doctors"; // doctors.html
    }

    // Save new doctor
    @PostMapping
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    // Show edit doctor in same page
    @GetMapping("/edit/{id}")
    public String editDoctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors";
    }

    // Update doctor
    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute("doctor") Doctor doctor) {
        Doctor existing = doctorService.getDoctorById(id);
        existing.setName(doctor.getName());
        existing.setSpecialization(doctor.getSpecialization());
        doctorService.saveDoctor(existing);
        return "redirect:/doctors";
    }

    // Delete doctor
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
