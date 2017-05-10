package de.adesso.termacare.database.services;

import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.data.entity.MedicationType;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.repo.MedicationRepo;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by kaiser on 09.05.2017.
 */
public class MedicationService {
    private MedicationRepo medications;

    public List<Medication> getMedications() {
        return medications.list();
    }

    public void createMedication(Patient patient, List<Doctor> doctors, MedicationType type, LocalDateTime appointment) {
        if(!timeSlotFree(appointment))
            throw new IllegalStateException("there is another appointment at this time");
        
        Medication medication = new Medication();
        
        medication.setPatient(patient);
        medication.setDoctors(doctors);
        medication.setMedicationType(type);
        medication.setAppointment(appointment);
    
        medications.add(medication);
    }

    public List<Medication> getMedications(int patientId) {
        return medications.list().stream()
                         .filter(med -> patientId == med.getPatient().getId())
                         .collect(toList());
    }

    public void deleteMedication(int medicationId) {
        medications.delete(medicationId);
    }
    
    public void reschedule(int medicationId, LocalDateTime appointment) {
        Medication medication = medications.getByID(medicationId);
        if (medication == null)
            throw new IllegalArgumentException("Medication does not exist");
        
        medications.delete(medicationId);
    
        if(!timeSlotFree(appointment)) {
            medications.add(medication);
            throw new IllegalStateException("there is another appointment at this time");
        }
    
        medication.setAppointment(appointment);
        medications.add(medication);
    }
    
    private boolean timeSlotFree(LocalDateTime appointment) {
        return medications.list().stream()
                .noneMatch(medication -> intersects(medication.getAppointment(), appointment));
    }
    
    private boolean intersects(LocalDateTime time1, LocalDateTime time2) {
        if (time1.compareTo(time2) <= 0 && time1.plusHours(1).compareTo(time2) > 0) return true;
        if (time2.compareTo(time1) <= 0 && time2.plusHours(1).compareTo(time1) > 0) return true;
        return false;
    }
}
