package de.adesso.termacare.service;

import de.adesso.termacare.database.dao.DoctorDao;
import de.adesso.termacare.database.dao.MedicationDao;
import de.adesso.termacare.database.dao.PatientDao;
import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.Gender;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
     * delete a doctor
     *
     * @param doctorId doctor to delete
     */
    public void deleteDoctor(long doctorId) {
        log.info("deleting doctor with id {}", doctorId);
        
        doctors.delete(doctorId);
    }
}

