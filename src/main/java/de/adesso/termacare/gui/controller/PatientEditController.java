package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.services.PatientService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.PatientEdit;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientEditController extends AbstractController<PatientEdit>{

	private Patient patient;

	private PatientService service;

	PatientEditController(DAOPatient patient){
		// DAOPatient back to Patient
	}

	@Override
	public void init(Stage stage, Scene scene){
		init(new PatientEdit(this, stage, scene));
	}

	public void save(){
		service.createPatient(
						view.getTitleField().getText(), Gender.MALE, view.getGivenNameField().getText(),
						view.getFamilyNameField().getText(), generateBillingAddress(), generateLivingAddress()
		);
		backToOverview();
	}

	private Address generateLivingAddress(){
		return null;
	}

	private Address generateBillingAddress(){
		return null;
	}

	public void backToOverview(){
		OverviewController oc = new OverviewController();
		oc.init(stage, scene);
		oc.show();
	}

}
