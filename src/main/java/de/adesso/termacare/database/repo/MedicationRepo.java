package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.entity.Medication;

public class MedicationRepo extends AbstractRepo<Medication, Integer>{

	public MedicationRepo(){
		super(Medication.class);
	}
}