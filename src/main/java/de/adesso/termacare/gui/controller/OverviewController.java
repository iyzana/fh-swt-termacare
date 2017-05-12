package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.services.PatientService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.Overview;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.stream.Collectors;

public class OverviewController extends AbstractController<Overview>{

	private PatientService service;

	@Override
	public void init(Stage stage, Scene scene){
		super.init(new Overview(this, stage, scene));
		fillTableWithColumns();
		loadPersonsToTable();
		checkButtons();
	}

	private void fillTableWithColumns(){
		generateColumnFor("gender", 0, 50);
		generateColumnFor("givenNameLabel", 100, 0);
		generateColumnFor("familyNameLabel", 100, 0);
		generateColumnFor("livingPostcode");
		generateColumnFor("livingAddress");
		generateColumnFor("billingPostcode");
		generateColumnFor("billingAddress");
	}

	private void generateColumnFor(String identifier){
		generateColumnFor(identifier, 0, 0);
	}

	private void generateColumnFor(String identifier, int minWidth, int maxWidth){
		TableColumn<DAOPatient, String> column = new TableColumn<>(identifier);
		if(minWidth != 0) column.setMinWidth(minWidth);
		if(maxWidth != 0) column.setMaxWidth(maxWidth);
		column.setCellValueFactory(new PropertyValueFactory<>(identifier));
		view.getPatientTableView().getColumns().add(column);
	}

	private void loadPersonsToTable(){
		view.setPatients(
						FXCollections.observableArrayList(service.getPatients().stream().map(Patient::toDAO).collect(Collectors.toList())));
	}

	public void checkButtons(){
		Optional<DAOPatient> patient = Optional.ofNullable(view.getPatientTableView().getFocusModel().getFocusedItem());
		Boolean visible = patient.map(daoPatient -> false).orElse(true);
		view.getEditPatient().setDisable(visible);
		view.getDeletePatient().setDisable(visible);
		view.getInfoPatient().setDisable(visible);
	}

	public void newPatient(){
		PatientEditController patientEditController = DependencyInjector.getInstance(PatientEditController.class);
		patientEditController.init(stage, scene);
		patientEditController.show();
	}

	public void editPatient(){
		DAOPatient focusedItem = view.getPatientTableView().getFocusModel().getFocusedItem();
		PatientEditController patientEditController = DependencyInjector.getInstance(PatientEditController.class);
		patientEditController.setPatient(focusedItem);
		patientEditController.init(stage, scene);
		patientEditController.show();
	}

	public void deletePatient(){

	}

	public void infoPatient(){

	}
}
