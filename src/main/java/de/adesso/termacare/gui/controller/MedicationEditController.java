package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.MedicationEdit;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MedicationEditController extends AbstractController<MedicationEdit>{

	@Override
	public void init(Stage stage, Scene scene){
		init(new MedicationEdit(this, stage, scene));
	}

	public void backToOverview(){
		MedicationOverviewController oc = DependencyInjector.getInstance(MedicationOverviewController.class);
		oc.init(stage, scene);
		oc.show();
	}
}
