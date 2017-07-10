package de.adesso.termacare.service;

import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.Gender;
import de.adesso.termacare.database.entity.Medication;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.database.dao.DoctorDao;
import de.adesso.termacare.database.dao.MedicationDao;
import de.adesso.termacare.database.dao.PatientDao;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Singleton class for querying for/by doctors or modifying doctors
 */
@Slf4j
public class DoctorService {
    private static DoctorService INSTANCE;
    
    public static DoctorService getInstance() {
        return INSTANCE == null ? INSTANCE = new DoctorService() : INSTANCE;
    }
    
    private DoctorService() {
    
    }
    
    public DoctorDao doctors;
    public PatientDao patients;
    public MedicationDao medications;
    
    /**
     * load all doctors from the database
     *
     * @return list of all doctors
     */
    public List<Doctor> getDoctors() {
        log.info("getting all doctors");
        return doctors.list();
    }
    
    /**
     * Save or update the data for a doctor
     * If the given id is zero a new medication is created
     *
     * @param id id of the doctor to update or 0 to create new doctor
     * @param title title of the doctor
     * @param gender gender of the doctor
     * @param givenName givenName of the doctor
     * @param familyName familyName of the doctor
     */
    public void createOrUpdateDoctor(long id, String title, Gender gender, String givenName, String familyName) {
        Doctor doctor = new Doctor();
        
        doctor.setId(id);
        doctor.setTitle(title);
        doctor.setGender(gender);
        doctor.setGivenName(givenName);
        doctor.setFamilyName(familyName);
        
        if (id == 0) log.info("creating new doctor");
        else log.info("updating doctor with id {}", id);
        
        doctors.save(doctor);
    }
    
    /**
     * get all the medications the given doctor has to attend to.
     *
     * @throws IllegalArgumentException if the doctor does not exist
     * @param doctorId doctor to load medications for
     * @return list of all medications of the doctor
     */
    public List<Medication> getMedications(long doctorId) {
        log.info("loading medications for doctor with id {}", doctorId);
        
        Doctor doctor = doctors.getByID(doctorId);
        if (doctor == null)
            throw new IllegalArgumentException("doctor does not exist");
        
        return medications.list().stream()
                .filter(med -> med.getDoctors().contains(doctor))
                .collect(toList());
    }
    
    /**
     * get all the patients the given doctor is treating
     *
     * @param doctorId doctor to load patients for
     * @return list of all the doctors patients
     */
    public List<Patient> getPatients(long doctorId) {
        log.info("loading patients for doctor with id {}", doctorId);
        
        return getMedications(doctorId).stream()
                .map(Medication::getPatient)
                .collect(toList());
    }
    
    /**
     * delete a doctor
     *
     * @param doctorId doctor to delete
     */
    public void deleteDoctor(long doctorId) {
        log.info("deleting doctor with id {}", doctorId);
        
        doctors.delete(doctorId);
    }
}

