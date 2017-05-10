package de.adesso.termacare.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "medication")
public class Medication implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "patient")
    private Patient patient;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Doctor> doctors;
    @Column(name = "medicationType")
    private MedicationType medicationType;
    @Column(name = "appointment")
    private LocalDateTime appointment;
}
