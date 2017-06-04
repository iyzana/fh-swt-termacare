package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.construct.GenericRepo;
import de.adesso.termacare.database.dao.DoctorDao;

public class DoctorRepo extends GenericRepo<Doctor, Long> implements DoctorDao {
    
    public DoctorRepo() {
        super(Doctor.class);
    }
}
