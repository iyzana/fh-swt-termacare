package de.adesso.termacare.database.repo;

import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.database.dao.MedicationDao;

public class MedicationRepo extends AbstractRepo<Medication, Long> implements MedicationDao {
    public MedicationRepo() {
        super(Medication.class);
    }
}
