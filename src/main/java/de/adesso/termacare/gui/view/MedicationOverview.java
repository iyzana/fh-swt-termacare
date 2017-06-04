package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.dto.DtoMedication;
import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.MedicationOverviewController;
import de.adesso.termacare.gui.language.LanguageSelection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicationOverview extends AbstractView<MedicationOverviewController>{

	private	Button newMedication = new Button();
	private	Button editMedication = new Button();
	private	Button deleteMedication = new Button();
	private Button infoMedication = new Button();
	private Button doctors = new Button("Alle Doktoren ansehen");
	private Button patients = new Button("Alle Patienten ansehen");

	private ObservableList<DtoMedication> medications = FXCollections.observableArrayList();
	private TableView<DtoMedication> medicationTableView = new TableView<>(medications);

	private VBox gotoBox = new VBox();
	private BorderPane pane = new BorderPane();
	private HBox bottomBox = new HBox();

	public MedicationOverview(MedicationOverviewController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		gotoBox.getChildren().addAll(doctors, patients);
		bottomBox.getChildren().addAll(newMedication, editMedication, deleteMedication, infoMedication);

		pane.setTop(LanguageSelection.getInstance().showLanguageSelection());
		pane.setBottom(bottomBox);
		pane.setCenter(medicationTableView);
		pane.setRight(gotoBox);
		scene = new Scene(pane);
		scene.getStylesheets().add("main.css");
	}

	@Override
	public void fillComponentsWithSelectedLanguage(){
		fillComponentWithText(newMedication, "new");
		fillComponentWithText(editMedication, "edit");
		fillComponentWithText(deleteMedication, "delete");
		fillComponentWithText(infoMedication, "info");
	}

	@Override
	public void registerListener(){
		newMedication.setOnMouseClicked(event -> controller.newMedication());
		editMedication.setOnMouseClicked(event -> controller.editMedication());
		deleteMedication.setOnMouseClicked(event -> controller.deleteMedication());
		infoMedication.setOnMouseClicked(event -> controller.infoMedication());

		doctors.setOnMouseClicked(event -> controller.gotoDoctors());
		patients.setOnMouseClicked(event -> controller.gotoPatients());
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
