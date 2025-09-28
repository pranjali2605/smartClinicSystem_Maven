package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String listDoctors(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Doctor> doctors = (keyword != null && !keyword.isEmpty()) ?
                doctorService.searchDoctors(keyword) :
                doctorService.getAllDoctors();

        model.addAttribute("doctors", doctors);
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("keyword", keyword);
        return "doctors";
    }

    @PostMapping
    public String saveDoctor(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("doctors", doctorService.getAllDoctors());
            return "doctors";
        }
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String editDoctor(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id).orElse(new Doctor());
        doctor.setHistory(doctorService.getDoctorHistory(id));
        model.addAttribute("doctor", doctor);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Long id, @Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("doctors", doctorService.getAllDoctors());
            return "doctors";
        }

        doctorService.getDoctorById(id).ifPresent(existing -> {
            existing.setName(doctor.getName());
            existing.setSpecialization(doctor.getSpecialization());
            existing.setContact(doctor.getContact());
            existing.setQualifications(doctor.getQualifications());
            existing.setTimeSlots(doctor.getTimeSlots());
            doctorService.saveDoctor(existing);
        });

        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
