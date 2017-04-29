package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.PatientEdit;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientEditController extends AbstractController<PatientEdit>{

	private Patient patient;

	PatientEditController(DAOPatient patient){
		// DAOPatient back to Patient
	}

	@Override
	public void init(Stage stage, Scene scene){
		init(new PatientEdit(this, stage, scene));
	}
}
