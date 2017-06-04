package de.adesso.termacare.database.dao;

import de.adesso.termacare.data.entity.Address;

import java.util.List;

public interface AddressDao{
    List<Address> list();
    
    void save(Address instance);
    
    Address getByID(Long id);
    
    void delete(Long id);
}
