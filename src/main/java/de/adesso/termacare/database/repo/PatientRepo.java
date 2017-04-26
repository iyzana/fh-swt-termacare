package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.data.entity.Patient;

public class PatientRepo extends AbstractRepo<Patient, Integer>{

	public PatientRepo(){
		super(Patient.class);
	}
}
