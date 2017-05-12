package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.database.services.DoctorService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.DoctorEdit;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoctorEditController extends AbstractController<DoctorEdit>{

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
		OverviewController oc = DependencyInjector.getInstance(OverviewController.class);
		oc.init(stage, scene);
		oc.show();
	}
}
