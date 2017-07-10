package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.GenericRepo;
import de.adesso.termacare.database.dao.AddressDao;
import de.adesso.termacare.database.entity.Address;

/**
 * DAO implementation for adresses
 */
public class AddressRepo extends GenericRepo<Address, Long> implements AddressDao {
    
    public AddressRepo() {
        super(Address.class);
    }
}
