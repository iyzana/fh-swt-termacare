package de.adesso.termacare.database.dao;

import de.adesso.termacare.database.entity.Address;

import java.util.List;

/**
 * data acces object for addresses
 */
public interface AddressDao{
    /**
     * Returns all entities saved in the table
     *
     * @return A list of all entities
     */
    List<Address> list();
    
    /**
     * Method to save an instance of T in the table
     *
     * @param instance The instance to save
     */
    void save(Address instance);
    
    /**
     * Method to get an instance with the given id
     *
     * @param id The id of the entity in the database
     * @return The instance with the id
     */
    Address getByID(Long id);
    
    /**
     * Method to DELETE an employee from the records
     *
     * @param id The id of the entity in the database
     */
    void delete(Long id);
}
