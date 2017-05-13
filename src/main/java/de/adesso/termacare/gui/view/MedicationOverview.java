package de.adesso.termacare.gui.view;

import de.adesso.termacare.data.dao.DAOMedication;
import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.MedicationOverviewController;
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
public class MedicationOverview extends AbstractView<MedicationOverviewController>{

	private BorderPane pane = new BorderPane();
	private ObservableList<DAOMedication> medications = FXCollections.observableArrayList();
	private TableView<DAOMedication> medicationTableView = new TableView<>(medications);
	private	Button newMedication = new Button("new");
	private	Button deleteMedication = new Button("delete");
	private Button infoMedication = new Button("info");
	private HBox bottomBox = new HBox();

	public MedicationOverview(MedicationOverviewController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		bottomBox.getChildren().addAll(newMedication, deleteMedication, infoMedication);

		pane.setCenter(medicationTableView);
		pane.setBottom(bottomBox);

		scene.setRoot(pane);
	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){
		fillComponentWithText(newMedication, "newMedication");
		fillComponentWithText(deleteMedication, "deleteMedication");
		fillComponentWithText(infoMedication, "infoMedication");
	}

	@Override
	public void registerListener(){
		medicationTableView.setOnMouseClicked(event -> controller.checkButtons());
		newMedication.setOnMouseClicked(event -> controller.newMedication());
		deleteMedication.setOnMouseClicked(event -> controller.deleteMedication());
		infoMedication.setOnMouseClicked(event -> controller.infoMedication());
	}

	@Override
	public void setStyleClasses(){
		newMedication.setId("newMedication");
		deleteMedication.setId("deleteMedication");
		infoMedication.setId("infoMedication");

		medicationTableView.setId("patientList");
		bottomBox.setId("bottomBox");
	}
}
