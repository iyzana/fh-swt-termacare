package de.adesso.termacare.service;

import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.Gender;
import de.adesso.termacare.database.repo.DoctorRepo;
import de.adesso.termacare.database.repo.MedicationRepo;
import de.adesso.termacare.database.repo.PatientRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DoctorServiceTest {
    
    private static DoctorService doctorService;
    private static DoctorRepo doctorRepo;
    private static PatientRepo patientRepo;
    private static MedicationRepo medicationRepo;
    
    private static List<Doctor> doctors;
    
    @BeforeAll
    static void init() {
        doctorService = DoctorService.getInstance();
        
        doctorRepo = mock(DoctorRepo.class);
        doctorService.doctors = doctorRepo;
        patientRepo = mock(PatientRepo.class);
        doctorService.patients = patientRepo;
        medicationRepo = mock(MedicationRepo.class);
        doctorService.medications = medicationRepo;
        
        Doctor doctor1 = new Doctor();
        doctor1.setId(1);
        doctor1.setTitle("Dr. med.");
        doctor1.setGender(Gender.MALE);
        doctor1.setGivenName("Matthias");
        doctor1.setFamilyName("Arzt");
        Doctor doctor2 = new Doctor();
        doctor2.setId(2);
        doctor2.setTitle("Dr. med.");
        doctor2.setGender(Gender.FEMALE);
        doctor2.setGivenName("Marie");
        doctor2.setFamilyName("Ärztin");
        
        doctors = Arrays.asList(doctor1, doctor2);
    }
    
    @Test
    void getDoctorsWhenEmpty() {
        doReturn(emptyList()).when(doctorRepo).list();
        
        assertEquals(0, doctorService.getDoctors().size());
    }
    
    @Test
    void getDoctors() {
        doReturn(doctors).when(doctorRepo).list();
        
        assertEquals(2, doctorService.getDoctors().size());
    }
    
    @Test
    void createOrUpdateDoctor() {
        Doctor doctor3 = new Doctor();
        doctor3.setId(1);
        doctor3.setTitle("Dr. med.");
        doctor3.setGender(Gender.MALE);
        doctor3.setGivenName("Matthias");
        doctor3.setFamilyName("Ärztin");
        
        doctorService.createOrUpdateDoctor(doctor3.getId(),
                doctor3.getTitle(),
                doctor3.getGender(),
                doctor3.getGivenName(),
                doctor3.getFamilyName());
        
        verify(doctorRepo).save(doctor3);
    }
}