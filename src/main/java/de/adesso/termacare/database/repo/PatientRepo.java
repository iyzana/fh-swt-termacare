package de.adesso.termacare.database.repo;

import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.construct.AbstractRepo;

public class PatientRepo extends AbstractRepo<Patient, Long>{

	public PatientRepo(){
		super(Patient.class);
	}
}
