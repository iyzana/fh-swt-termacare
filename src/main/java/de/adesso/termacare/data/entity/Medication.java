package de.adesso.termacare.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Medication implements EntityInterface {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "patient")
    private Patient patient;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Doctor> doctors;
    @Column(name = "medicationType")
    private MedicationType medicationType;
    @Column(name = "appointment")
    private LocalDateTime appointment;
}
