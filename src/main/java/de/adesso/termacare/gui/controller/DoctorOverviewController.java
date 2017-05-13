package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAODoctor;
import de.adesso.termacare.database.services.DoctorService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.DoctorOverview;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorOverviewController extends AbstractController<DoctorOverview>{

	private DoctorService service;

	@Override
	public void init(Stage stage, Scene scene){
		super.init(new DoctorOverview(this, stage, scene));
		fillTableWithColumns();
		loadPersonsToTable();
		checkButtons();
	}

	private void fillTableWithColumns(){
		generateColumnFor("gender", 0, 50);
		generateColumnFor("givenNameLabel", 100, 0);
		generateColumnFor("familyNameLabel", 100, 0);
		generateColumnFor("Anzahl Behandlungen", 200, 0);
	}

	private void generateColumnFor(String identifier){
		generateColumnFor(identifier, 0, 0);
	}

	private void generateColumnFor(String identifier, int minWidth, int maxWidth){
		TableColumn<DAODoctor, String> column = new TableColumn<>(identifier);
		if(minWidth != 0) column.setMinWidth(minWidth);
		if(maxWidth != 0) column.setMaxWidth(maxWidth);
		column.setCellValueFactory(new PropertyValueFactory<>(identifier));
		view.getDoctorTableView().getColumns().add(column);
	}

	private void loadPersonsToTable(){
		view.setDoctors(FXCollections.observableArrayList(service.getDoctors().stream().map(doctor -> {
			DAODoctor dao = doctor.toDAO();
			dao.setAmountOfPatients(service.getPatients(dao.getId()).size());
			return dao;
		}).collect(Collectors.toList())));
	}

	public void checkButtons(){
		Optional<DAODoctor> patient = Optional.ofNullable(view.getDoctorTableView().getFocusModel().getFocusedItem());
		Boolean visible = patient.map(daoPatient -> false).orElse(true);
		view.getEditDoctor().setDisable(visible);
		view.getDeleteDoctor().setDisable(visible);
		view.getInfoDoctor().setDisable(visible);
	}

	public void newDoctor(){
		PatientEditController patientEditController = DependencyInjector.getInstance(PatientEditController.class);
		patientEditController.init(stage, scene);
		patientEditController.show();
	}

	public void editDoctor(){
		DAODoctor focusedItem = view.getDoctorTableView().getFocusModel().getFocusedItem();
		DoctorEditController doctorEditController = DependencyInjector.getInstance(DoctorEditController.class);
		doctorEditController.setDoctor(focusedItem);
		doctorEditController.init(stage, scene);
		doctorEditController.show();
	}

	public void deleteDoctor(){

	}

	public void infoDoctor(){

	}
}
