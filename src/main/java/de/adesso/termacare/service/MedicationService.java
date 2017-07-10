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
 * Singleton class for querying for/by medications or modifying medications
 */
public class MedicationService {
    private static MedicationService INSTANCE;
    
    public static MedicationService getInstance() {
        return INSTANCE == null ? INSTANCE = new MedicationService() : INSTANCE;
    }
    
    private MedicationService() {
    
    }
    
    private MedicationDao medications;
    
    /**
     * load all medications from the database
     *
     * @return list of all medications
     */
    public List<Medication> getMedications() {
        return medications.list();
    }
    
    /**
     * Save or update the data for a medication
     * If the given id is zero a new medication is created
     *
     * @throws IllegalStateException if there already is a medication in that timeslot
     * @param id id of the medication to update or 0 to create new medication
     * @param patient Patient which the medication is for
     * @param doctors Doctors that are present for the medication
     * @param type type of the medication
     * @param appointment time and date of the medication
     */
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
    
    /**
     * Get all medications for a given patient
     *
     * @param patientId patient to search for
     * @return list of patients medications
     */
    public List<Medication> getMedications(long patientId) {
        return medications.list().stream()
                .filter(med -> patientId == med.getPatient().getId())
                .collect(toList());
    }
    
    /**
     * Remove a medication by it's id
     *
     * @param medicationId medication to delete
     */
    public void deleteMedication(long medicationId) {
        medications.delete(medicationId);
    }
    
    /**
     * move a medications appointment
     *
     * @throws IllegalArgumentException if the medication does not exist
     * @throws IllegalStateException if there already is another medication in the same timeslot
     * @param medicationId medication to move
     * @param appointment new appointment details
     */
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
    
    /**
     * Check if a given time slot is unused as of now
     *
     * @param appointment appointment to check
     * @return true if slot is free, false otherwise
     */
    private boolean timeSlotFree(LocalDateTime appointment) {
        return medications.list().stream()
                .noneMatch(medication -> intersects(medication.getAppointment(), appointment));
    }
    
    /**
     * Check if two times are within one hour of each other
     *
     * @param time1 time to check collision for
     * @param time2 time to check collision with
     * @return true if times intersect, false otherwise
     */
    private boolean intersects(LocalDateTime time1, LocalDateTime time2) {
        if (time1.compareTo(time2) <= 0 && time1.plusHours(1).compareTo(time2) > 0) return true;
        if (time2.compareTo(time1) <= 0 && time2.plusHours(1).compareTo(time1) > 0) return true;
        return false;
    }
}
