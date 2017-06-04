package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.entity.Medication;
import de.adesso.termacare.database.construct.GenericRepo;
import de.adesso.termacare.database.dao.MedicationDao;

public class MedicationRepo extends GenericRepo<Medication, Long> implements MedicationDao {
    public MedicationRepo() {
        super(Medication.class);
    }
}
