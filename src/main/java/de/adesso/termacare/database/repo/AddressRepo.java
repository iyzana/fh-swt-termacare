package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.data.entity.Address;

public class AddressRepo extends AbstractRepo<Address, Integer> {

	public AddressRepo(){
		super(Address.class);
	}
}
