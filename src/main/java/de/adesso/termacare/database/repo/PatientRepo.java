package de.adesso.termacare.database.repo;

import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.database.dao.PatientDao;

public class PatientRepo extends AbstractRepo<Patient, Long> implements PatientDao {
    public PatientRepo() {
        super(Patient.class);
    }
}
