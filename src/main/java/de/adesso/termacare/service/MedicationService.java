package de.adesso.termacare.service;

import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.Medication;
import de.adesso.termacare.database.entity.MedicationType;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.database.dao.MedicationDao;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by kaiser on 09.05.2017.
 */
public class MedicationService {
    private static MedicationService INSTANCE;
    
    public static MedicationService getInstance() {
        return INSTANCE == null ? INSTANCE = new MedicationService() : INSTANCE;
    }
    
    private MedicationService() {
    
    }
    
    private MedicationDao medications;
    
    public List<Medication> getMedications() {
        return medications.list();
    }
    
    public void createMedication(long id, Patient patient, List<Doctor> doctors, MedicationType type, LocalDateTime appointment) {
        if (id == 0 && !timeSlotFree(appointment))
            throw new IllegalStateException("there is another appointment at this time");
        
        Medication medication = new Medication();
        
        medication.setId(id);
        medication.setPatient(patient);
        medication.setDoctors(doctors);
        medication.setMedicationType(type);
        medication.setAppointment(appointment);
        
        medications.save(medication);
    }
    
    public List<Medication> getMedications(long patientId) {
        return medications.list().stream()
                .filter(med -> patientId == med.getPatient().getId())
                .collect(toList());
    }
    
    public void deleteMedication(long medicationId) {
        medications.delete(medicationId);
    }
    
    public void reschedule(long medicationId, LocalDateTime appointment) {
        Medication medication = medications.getByID(medicationId);
        if (medication == null)
            throw new IllegalArgumentException("Medication does not exist");
        
        medications.delete(medicationId);
        
        if (!timeSlotFree(appointment)) {
            medications.save(medication);
            throw new IllegalStateException("there is another appointment at this time");
        }
        
        medication.setAppointment(appointment);
        medications.save(medication);
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
