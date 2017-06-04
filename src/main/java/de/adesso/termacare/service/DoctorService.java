package de.adesso.termacare.service;

import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.Gender;
import de.adesso.termacare.database.entity.Medication;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.database.dao.DoctorDao;
import de.adesso.termacare.database.dao.MedicationDao;
import de.adesso.termacare.database.dao.PatientDao;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DoctorService {
    private static DoctorService INSTANCE;
    
    public static DoctorService getInstance() {
        return INSTANCE == null ? INSTANCE = new DoctorService() : INSTANCE;
    }
    
    private DoctorService() {
    
    }
    
    private DoctorDao doctors;
    private PatientDao patients;
    private MedicationDao medications;
    
    public List<Patient> getPatients() {
        return patients.list();
    }
    
    public List<Doctor> getDoctors() {
        return doctors.list();
    }
    
    public void createOrUpdateDoctor(long id, String title, Gender gender, String givenName, String familyName) {
        Doctor doctor = new Doctor();
        
        doctor.setId(id);
        doctor.setTitle(title);
        doctor.setGender(gender);
        doctor.setGivenName(givenName);
        doctor.setFamilyName(familyName);
        
        doctors.save(doctor);
    }
    
    public List<Medication> getMedications(long doctorId) {
        Doctor doctor = doctors.getByID(doctorId);
        if (doctor == null)
            throw new IllegalArgumentException("doctor does not exist");
        
        return medications.list().stream()
                .filter(med -> med.getDoctors().contains(doctor))
                .collect(toList());
    }
    
    public List<Patient> getPatients(long doctorId) {
        return getMedications(doctorId).stream()
                .map(Medication::getPatient)
                .collect(toList());
    }
    
    public void deleteDoctor(long doctorId) {
        doctors.delete(doctorId);
    }
}

