package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.data.entity.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
class PatientController{

	private Patient patient;

	PatientController(DAOPatient patient){
	}
}
