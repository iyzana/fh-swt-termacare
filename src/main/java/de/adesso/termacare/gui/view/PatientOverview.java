package de.adesso.termacare.gui.view;

import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.PatientOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientOverview extends AbstractView<PatientOverviewController>{

	private BorderPane pane = new BorderPane();
	private ObservableList<DAOPatient> patients = FXCollections.observableArrayList();
	private TableView<DAOPatient> patientTableView = new TableView<>(patients);
	private	Button newPatient = new Button("new");
	private	Button editPatient = new Button("edit");
	private	Button deletePatient = new Button("delete");
	private Button infoPatient = new Button("info");
	private HBox bottomBox = new HBox();

	public PatientOverview(PatientOverviewController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		bottomBox.getChildren().addAll(newPatient, editPatient, deletePatient, infoPatient);

		pane.setCenter(patientTableView);
		pane.setBottom(bottomBox);

		scene.setRoot(pane);
	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){
		fillComponentWithText(newPatient, "newPatient");
		fillComponentWithText(editPatient, "editPatient");
		fillComponentWithText(deletePatient, "deletePatient");
		fillComponentWithText(infoPatient, "infoPatient");
	}

	@Override
	public void registerListener(){
		patientTableView.setOnMouseClicked(event -> controller.checkButtons());
		newPatient.setOnMouseClicked(event -> controller.newPatient());
		editPatient.setOnMouseClicked(event -> controller.editPatient());
		deletePatient.setOnMouseClicked(event -> controller.deletePatient(patientTableView.getSelectionModel().getSelectedItem()));
		infoPatient.setOnMouseClicked(event -> controller.infoPatient());
	}

	@Override
	public void setStyleClasses(){
		newPatient.setId("newPatient");
		editPatient.setId("editPatient");
		deletePatient.setId("deletePatient");
		infoPatient.setId("infoPatient");

		patientTableView.setId("patientList");
		bottomBox.setId("bottomBox");
	}
}
