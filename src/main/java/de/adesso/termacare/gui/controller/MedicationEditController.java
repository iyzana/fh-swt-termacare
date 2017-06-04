package de.adesso.termacare.gui.controller;

import de.adesso.termacare.gui.dto.DtoDoctor;
import de.adesso.termacare.gui.dto.DtoMedication;
import de.adesso.termacare.gui.dto.DtoPatient;
import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.MedicationType;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.database.dao.DoctorDao;
import de.adesso.termacare.database.dao.PatientDao;
import de.adesso.termacare.service.MedicationService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.MedicationEdit;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static de.adesso.termacare.util.DependencyInjector.getInstance;

public class MedicationEditController extends AbstractController<MedicationEdit>{

	private MedicationService service;
	private PatientDao patientService;
	private DoctorDao doctorService;

	@Setter
	private DtoMedication medication = null;

	private Patient patient;
	private List<Doctor> doctors = new LinkedList<>();
	private MedicationType medicationType;

	@Override
	public void init(Stage stage, Scene scene){
		init(new MedicationEdit(this, stage, scene));
		launch();
	}

	private void launch(){
		if(medication != null){
			view.getPatient().setText("Patient: " + medication.getPatientName());
			view.getDoctors().getChildren().clear();
			view.getDoctors().getChildren().addAll(medication.getDoctorIds().stream().map(doctor -> new Label(" - " + doctorService.getByID(doctor).getName())).collect(Collectors.toList()));
			view.getData().clear();
			view.getData().addAll(MedicationType.values());
			view.getMedicationType().getSelectionModel().select(medicationType != null ? medicationType : MedicationType.INSPECTION);
		} else
			view.getPatient().setText("kein Patient ausgewählt");
	}

	public void save(){
		LocalDateTime dateTime = view.getDatePicker().getDateTimeValue();
		MedicationType type = view.getMedicationType().getValue();
		service.createMedication(patient, doctors, type, dateTime);
		backToOverview();
	}

	public void backToOverview(){
		MedicationOverviewController oc = getInstance(MedicationOverviewController.class);
		oc.init(stage, scene);
		oc.show();
	}

	public void selectPatient(){
		PatientSelectionController controller = getInstance(PatientSelectionController.class);
		controller.init(stage, scene);
		controller.setData(patientService.list());
		controller.setController(this);
		controller.show();
	}

	public void addDoctor(){
		DoctorSelectionController controller = getInstance(DoctorSelectionController.class);
		controller.init(stage, scene);
		controller.setData(doctorService.list(), true);
		controller.setController(this);
		controller.show();
	}

	public void removeDoctor(){
		DoctorSelectionController controller = getInstance(DoctorSelectionController.class);
		controller.init(stage, scene);
		controller.setData(doctors, false);
		controller.setController(this);
		controller.show();
	}

	void relaunch(){
		launch();
		view.getScene().setRoot(view.getMainPane());
		show();
	}

	void patient(DtoPatient focusedItem){
		patient = patientService.getByID(focusedItem.getId());
		relaunch();
	}

	void doctorAdd(DtoDoctor focusedItem){
		doctors.add(doctorService.getByID(focusedItem.getId()));
		relaunch();
	}

	void doctorRemove(DtoDoctor focusedItem){
		doctors.remove(doctorService.getByID(focusedItem.getId()));
		relaunch();
	}

	void setDisable(boolean disable){
		view.setDisable(disable);
	}

}
