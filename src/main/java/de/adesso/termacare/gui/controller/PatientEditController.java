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

	public void save(){
//		repo.update(generatePatient());
		backToOverview();
	}

	public void backToOverview(){
		OverviewController oc = new OverviewController();
		oc.init(stage, scene);
		oc.show();
	}

	private Patient generatePatient(){
		Patient p = new Patient();
		p.setTitle(view.getTitleField().getText());
		p.setGivenName(view.getGivenNameField().getText());
		p.setFamilyName(view.getFamilyNameField().getText());
//		p.setGender(view.getFamilyNameField().getText());
		return p;
	}
}
