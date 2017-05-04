package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.PatientEditController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientEdit extends AbstractView<PatientEditController>{

	private Label givenNameLabel = new Label("given name:");
	private Label familyNameLabel = new Label("family name:");
	private Label birthdayLabel = new Label("b-day Label");
	private Label addressLabel = new Label("address Label");

	private TextField givenNameField = new TextField("given Field");
	private TextField familyNameField = new TextField("family Field");
	private TextField birthdayField = new TextField("b-day Field");
	private TextField addressField = new TextField("address Field");
	private HBox nameBox = new HBox();

	public PatientEdit(PatientEditController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		nameBox.getChildren().addAll(givenNameLabel, givenNameField, familyNameLabel, familyNameField);
	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){

	}

	@Override
	public void registerListener(){

	}

	@Override
	public void setStyleClasses(){
		givenNameLabel.setId("givenNameLabel");
		familyNameLabel.setId("familyNameLabel");
		birthdayLabel.setId("birthdayLabel");
		addressLabel.setId("addressLabel");
		givenNameField.setId("givenNameField");
		familyNameField.setId("familyNameField");
		birthdayField.setId("birthdayField");
		addressField.setId("addressField");
	}
}
