package de.adesso.termacare.database.dao;

import de.adesso.termacare.data.entity.Address;

import java.util.List;

/**
 * Created by jannis on 6/4/17.
 */
public interface AdressDao {
    List<Address> list();
    
    void save(Address instance);
    
    Address getByID(Long id);
    
    void delete(Long id);
}
