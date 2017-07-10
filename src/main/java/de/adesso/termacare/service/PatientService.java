package de.adesso.termacare.service;

import de.adesso.termacare.database.entity.Address;
import de.adesso.termacare.database.entity.Gender;
import de.adesso.termacare.database.entity.Medication;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.database.dao.MedicationDao;
import de.adesso.termacare.database.dao.PatientDao;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Singleton class for querying for/by patients or modifying patients
 */
public class PatientService {
    private static PatientService INSTANCE;
    
    public static PatientService getInstance() {
        return INSTANCE == null ? INSTANCE = new PatientService() : INSTANCE;
    }
    
    private PatientService() {
    
    }
    
    private PatientDao patients;
    private MedicationDao medications;
    
    /**
     * Load a list of all Patients from the database
     *
     * @return List of all patients
     */
    public List<Patient> getPatients() {
        return patients.list();
    }
    
    /**
     * Save or update a patients data.
     * If the given id is zero a new patient is created
     *
     * @param id id of the patient to update or 0 to create new patient
     * @param title title of the patient
     * @param gender gender of the patient
     * @param givenName givenName of the patient
     * @param familyName familyName of the patient
     * @param billing billing address of the patient
     * @param living living address of the patient
     */
    public void createOrUpdatePatient(long id, String title, Gender gender, String givenName, String familyName, Address billing, Address living) {
        Patient patient = new Patient();
        
        patient.setId(id);
        patient.setTitle(title);
        patient.setGender(gender);
        patient.setGivenName(givenName);
        patient.setFamilyName(familyName);
        patient.setBillingAddress(billing);
        patient.setLivingAddress(living);
        
        patients.save(patient);
    }
    
    /**
     * Get all medications - future or past - for a given patient
     *
     * @param patientId patientId to query for
     * @return List of medications of this patient
     */
    public List<Medication> getMedications(long patientId) {
        return medications.list().stream()
                .filter(med -> patientId == med.getPatient().getId())
                .collect(toList());
    }
    
    /**
     * Delete a patient
     * All medications of this patient must be delete first
     *
     * @param patientId patientId to delete
     */
    public void deletePatient(long patientId) {
        patients.delete(patientId);
    }
}

