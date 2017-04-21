package de.adesso.termacare.gui.controller;

import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.Overview;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OverviewController extends AbstractController<Overview>{

	@Override
	public void init(Stage stage, Scene scene){
		super.init(new Overview(this, stage, scene));
	}
}
