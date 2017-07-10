package de.adesso.termacare.database.dao;

import de.adesso.termacare.database.entity.Medication;

import java.util.List;

/**
 * Created by jannis on 6/4/17.
 */
public interface MedicationDao {
    /**
     * Returns all entities saved in the table
     *
     * @return A list of all entities
     */
    List<Medication> list();
    
    /**
     * Method to save an instance of T in the table
     *
     * @param instance The instance to save
     */
    void save(Medication instance);
    
    /**
     * Method to get an instance with the given id
     *
     * @param id The id of the entity in the database
     * @return The instance with the id
     */
    Medication getByID(Long id);
    
    /**
     * Method to DELETE an employee from the records
     *
     * @param id The id of the entity in the database
     */
    void delete(Long id);
}
