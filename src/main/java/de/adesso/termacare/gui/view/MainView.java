package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends AbstractView<MainController>{

	private Button doctors = new Button("doktoren ansehen");
	private Button patients = new Button("patienten ansehen");
	private VBox gotoBox = new VBox();

	public MainView(MainController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		gotoBox.getChildren().addAll(doctors, patients);


		scene = new Scene(gotoBox);
		scene.getStylesheets().add("main.css");
	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){

	}

	@Override
	public void registerListener(){
		doctors.setOnMouseClicked(event -> controller.gotoDoctors());
		patients.setOnMouseClicked(event -> controller.gotoPatients());
	}

	@Override
	public void setStyleClasses(){

	}
}
