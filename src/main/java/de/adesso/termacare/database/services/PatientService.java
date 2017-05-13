package de.adesso.termacare.database.services;

import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.repo.MedicationRepo;
import de.adesso.termacare.database.repo.PatientRepo;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by kaiser on 09.05.2017.
 */
public class PatientService {
    private PatientRepo patients;
    private MedicationRepo medications;

    public List<Patient> getPatients() {
        return patients.list();
    }

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

     public List<Medication> getMedications(long patientId) {
         return medications.list().stream()
                 .filter(med -> patientId == med.getPatient().getId())
                 .collect(toList());
     }

    public void deletePatient(long patientId) {
        patients.delete(patientId);
    }
}

