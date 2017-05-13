package de.adesso.termacare.database.repo;

import de.adesso.termacare.database.construct.AbstractRepo;
import de.adesso.termacare.data.entity.Medication;

public class MedicationRepo extends AbstractRepo<Medication, Long>{

	public MedicationRepo(){
		super(Medication.class);
	}
}
