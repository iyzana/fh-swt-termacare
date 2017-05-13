package de.adesso.termacare.data.entity;

import de.adesso.termacare.data.dao.DAOMedication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

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

    public DAOMedication toDao() {
        String patientName = patient.familyName + ", " + patient.givenName;
        List<Long> doctorIds = doctors.stream().map(Doctor::getId).collect(toList());
        String doctorNames = doctors.stream().map(d -> d.familyName + ", " + d.givenName).collect(joining("; "));
        String time = appointment.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
        return new DAOMedication(id, patient.getId(), patientName, doctorIds, doctorNames, medicationType.name(), time);
    }
}
