package de.adesso.termacare.database.repo;

import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.database.dao.DoctorDao;

public class DoctorRepo extends AbstractRepo<Doctor, Long> implements DoctorDao {
    
    public DoctorRepo() {
        super(Doctor.class);
    }
}
