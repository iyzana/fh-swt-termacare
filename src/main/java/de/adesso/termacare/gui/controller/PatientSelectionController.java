package de.adesso.termacare.gui.controller;

import de.adesso.termacare.gui.dto.DtoPatient;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.PatientSelection;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class PatientSelectionController extends AbstractController<PatientSelection>{

	@Setter
	private MedicationEditController controller;

	@Override
	public void init(Stage stage, Scene scene){
		init(new PatientSelection(this, stage, scene));
		fillTableWithColumns();
	}

	private void fillTableWithColumns(){
		generateColumnFor("title", 0, 100);
		generateColumnFor("givenName", 0, 200);
		generateColumnFor("familyName", 200, 0);
		generateColumnFor("gender", 100, 150);
	}

	private void generateColumnFor(String identifier){
		generateColumnFor(identifier, 0, 0);
	}

	private void generateColumnFor(String identifier, int minWidth, int maxWidth){
		TableColumn<DtoPatient, String> column = new TableColumn<>(identifier);
		if(minWidth != 0) column.setMinWidth(minWidth);
		if(maxWidth != 0) column.setMaxWidth(maxWidth);
		column.setCellValueFactory(new PropertyValueFactory<>(identifier));
		view.getTableView().getColumns().add(column);
	}

	public void dataSelected(){
		controller.patient(view.getTableView().getFocusModel().getFocusedItem());
	}

	public void back(){
		controller.relaunch();
	}

	public void setData(List<Patient> patients){
		view.getData().addAll(patients.stream().map(Patient::toDAO).collect(Collectors.toList()));
	}
}
