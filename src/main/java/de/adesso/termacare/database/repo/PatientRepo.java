package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.database.construct.GenericRepo;
import de.adesso.termacare.database.dao.PatientDao;

/**
 * DAO implementation for patient
 */
public class PatientRepo extends GenericRepo<Patient, Long> implements PatientDao {
    public PatientRepo() {
        super(Patient.class);
    }
}
