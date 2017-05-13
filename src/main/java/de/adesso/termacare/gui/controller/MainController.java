package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends AbstractController<MainView>{

	@Override
	public void init(Stage stage, Scene scene){
		init(new MainView(this, stage, scene));
	}

	public void gotoDoctors(){
		DoctorOverviewController doctorOverviewController = DependencyInjector.getInstance(DoctorOverviewController.class);
		doctorOverviewController.init(stage, scene);
		doctorOverviewController.show();
	}

	public void gotoPatients(){
		PatientOverviewController patientOverviewController = DependencyInjector.getInstance(PatientOverviewController.class);
		patientOverviewController.init(stage, scene);
		patientOverviewController.show();
	}
}
