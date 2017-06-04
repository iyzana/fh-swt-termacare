package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.database.dao.AddressDao;

public class AddressRepo extends AbstractRepo<Address, Long> implements AddressDao{

	public AddressRepo(){
		super(Address.class);
	}
}
