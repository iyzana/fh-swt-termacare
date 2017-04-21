package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.entity.Doctor;

public class DoctorRepo extends AbstractRepo<Doctor, Integer>{

	public DoctorRepo(){
		super(Doctor.class);
	}
}
