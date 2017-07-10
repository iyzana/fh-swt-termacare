package de.adesso.termacare.database.entity;

import de.adesso.termacare.gui.dto.DtoMedication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Entity for representing a medication including patient, doctors and appointment
 */
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "medications")
public class Medication implements EntityInterface{
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

    public DtoMedication toDao() {
        String patientName = patient.familyName + ", " + patient.givenName;
        List<Long> doctorIds = doctors.stream().map(Doctor::getId).collect(toList());
        String doctorNames = doctors.stream().map(d -> d.familyName + ", " + d.givenName).collect(joining("; "));
        String time = appointment.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
        return new DtoMedication(id, patient.getId(), patientName, doctorIds, doctorNames, medicationType.name(), time);
    }
}
