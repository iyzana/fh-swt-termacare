package de.adesso.termacare.gui.view;

import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.OverviewController;
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
public class Overview extends AbstractView<OverviewController>{

	private BorderPane pane = new BorderPane();
	private ObservableList<DAOPatient> patients;
	private TableView<DAOPatient> patientTableView = new TableView<>(patients);
	private	Button newPatient = new Button("new");
	private	Button editPatient = new Button("edit");
	private	Button deletePatient = new Button("delete");
	private HBox bottomBox = new HBox();

	public Overview(OverviewController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		bottomBox.getChildren().addAll(newPatient, editPatient, deletePatient);

		pane.setCenter(patientTableView);
		pane.setBottom(bottomBox);

		scene = new Scene(pane);
		scene.getStylesheets().add("main.css");
	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){
		fillComponentWithText(newPatient, "newPatient");
		fillComponentWithText(editPatient, "editPatient");
		fillComponentWithText(deletePatient, "deletePatient");
	}

	@Override
	public void registerListener(){
		newPatient.setOnMouseClicked(event -> controller.newPatient());
		editPatient.setOnMouseClicked(event -> controller.editPatient());
		deletePatient.setOnMouseClicked(event -> controller.deletePatient());
	}

	@Override
	public void setStyleClasses(){
		newPatient.setId("newPatient");
		editPatient.setId("editPatient");
		deletePatient.setId("deletePatient");
		patientTableView.setId("patientList");
	}
}
