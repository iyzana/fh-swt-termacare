package de.adesso.termacare.database.dao;

import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.data.entity.Doctor;

import java.util.List;

/**
 * Created by jannis on 6/4/17.
 */
public interface DoctorDao {
    List<Doctor> list();
    
    void save(Doctor instance);
    
    Doctor getByID(Long id);
    
    void delete(Long id);
}
