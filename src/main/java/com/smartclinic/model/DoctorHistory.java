package com.smartclinic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "doctor_history")
public class DoctorHistory {

    @Id
    private String id;

    private Long doctorId;
    private String name;
    private List<String> specializations;
    private List<String> availableTimeSlots;
    private String contact;
    private String qualifications;
    private Date createdAt;

    public DoctorHistory(Long doctorId, String name, List<String> specializations,
                         List<String> availableTimeSlots, String contact, String qualifications, Date createdAt) {
        this.doctorId = doctorId;
        this.name = name;
        this.specializations = specializations;
        this.availableTimeSlots = availableTimeSlots;
        this.contact = contact;
        this.qualifications = qualifications;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getSpecializations() { return specializations; }
    public void setSpecializations(List<String> specializations) { this.specializations = specializations; }

    public List<String> getAvailableTimeSlots() { return availableTimeSlots; }
    public void setAvailableTimeSlots(List<String> availableTimeSlots) { this.availableTimeSlots = availableTimeSlots; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getQualifications() { return qualifications; }
    public void setQualifications(String qualifications) { this.qualifications = qualifications; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}

