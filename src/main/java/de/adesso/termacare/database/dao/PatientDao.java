package de.adesso.termacare.database.dao;

import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.data.entity.Patient;

import java.util.List;

/**
 * Created by jannis on 6/4/17.
 */
public interface PatientDao {
    List<Patient> list();
    
    void save(Patient instance);
    
    Patient getByID(Long id);
    
    void delete(Long id);
}
