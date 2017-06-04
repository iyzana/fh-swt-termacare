package de.adesso.termacare.database.dao;

import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.data.entity.Medication;

import java.util.List;

/**
 * Created by jannis on 6/4/17.
 */
public interface MedicationDao {
    List<Medication> list();
    
    void save(Medication instance);
    
    Medication getByID(Long id);
    
    void delete(Long id);
}
