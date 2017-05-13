package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAODoctor;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.database.services.DoctorService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.DoctorEdit;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

public class DoctorEditController extends AbstractController<DoctorEdit>{

	@Setter
	private DAODoctor doctor;

	private DoctorService service;

	@Override
	public void init(Stage stage, Scene scene){
		init(new DoctorEdit(this, stage, scene));
	}

	public void save(){
		service.createDoctor(view.getTitleField().getText(), Gender.MALE, view.getGivenNameField().getText(),
		                      view.getFamilyNameField().getText()
		);
		backToOverview();
	}

	public void backToOverview(){
		DoctorOverviewController oc = DependencyInjector.getInstance(DoctorOverviewController.class);
		oc.init(stage, scene);
		oc.show();
	}
}
