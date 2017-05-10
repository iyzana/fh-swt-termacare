package de.adesso.termacare.database.services;

import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.repo.AddressRepo;
import de.adesso.termacare.database.repo.MedicationRepo;
import de.adesso.termacare.database.repo.PatientRepo;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by kaiser on 09.05.2017.
 */
public class PatientService {
    private AddressRepo addresses;
    private PatientRepo patients;
    private MedicationRepo medication;

    public List<Patient> getPatients() {
        return patients.list();
    }

    public void createPatient(String title, Gender gender, String givenName, String familyName, Address billing, Address living) {
        Patient patient = new Patient();

        patient.setTitle(title);
        patient.setGender(gender);
        patient.setGivenName(givenName);
        patient.setFamilyName(familyName);
        patient.setBillingAddress(billing);
        patient.setLivingAddress(living);

        patients.add(patient);
    }

     public List<Medication> getMedications(int patientId) {
         return medication.list().stream()
                 .filter(med -> patientId == med.getPatient().getId())
                 .collect(toList());
     }

    public void deletePatient(int patientId) {
        patients.delete(patientId);
    }
}

