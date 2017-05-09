package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.PatientEditController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientEdit extends AbstractView<PatientEditController>{

	private Label givenNameLabel = new Label("given name:");
	private Label familyNameLabel = new Label("family name:");
	private Label titleLabel = new Label("title Label");

	private Label addressLabel = new Label("address Label");

	private TextField givenNameField = new TextField("given Field");
	private TextField familyNameField = new TextField("family Field");
	private TextField titleField = new TextField("title Field");

	private TextField streetField = new TextField("street Field");
	private TextField postcodeField = new TextField("postcode Field");
	private TextField departureField = new TextField("departure Field");
	private TextField numberField = new TextField("number Field");

	private Button save = new Button("save");
	private Button cancel = new Button("backToOverview");

	private HBox titleBox = new HBox();
	private HBox givenNameBox = new HBox();
	private HBox familyNameBox = new HBox();
	private HBox addressBox = new HBox();
	private VBox contentBox = new VBox();
	private BorderPane pain = new BorderPane();
	private HBox bottomBox = new HBox();

	public PatientEdit(PatientEditController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){

		titleBox.getChildren().addAll(titleLabel, titleField);
		givenNameBox.getChildren().addAll(givenNameLabel, givenNameField);
		familyNameBox.getChildren().addAll(familyNameLabel, familyNameField);

		addressBox.getChildren().addAll(addressLabel, postcodeField, departureField, streetField, numberField);
		contentBox.getChildren().addAll(titleBox, givenNameBox, familyNameBox, addressBox);
		bottomBox.getChildren().addAll(save, cancel);

		pain.setCenter(contentBox);
		pain.setBottom(bottomBox);
		scene.setRoot(pain);
	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){

	}

	@Override
	public void registerListener(){
		save.setOnMouseClicked(event -> controller.save());
		cancel.setOnMouseClicked(event -> controller.backToOverview());
	}

	@Override
	public void setStyleClasses(){
		givenNameLabel.setId("givenNameLabel");
		familyNameLabel.setId("familyNameLabel");
		titleLabel.setId("titleLabel");

		givenNameField.setId("givenNameField");
		familyNameField.setId("familyNameField");
		titleField.setId("titleField");

		addressLabel.setId("addressLabel");
		postcodeField.setId("postcodeField");
		departureField.setId("departureField");
		streetField.setId("streetField");
		numberField.setId("numberField");
	}
}
