package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.database.dao.AdressDao;

public class AddressRepo extends AbstractRepo<Address, Long> implements AdressDao {

	public AddressRepo(){
		super(Address.class);
	}
}
