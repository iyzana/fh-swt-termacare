package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.dao.DAODoctor;
import de.adesso.termacare.data.dao.DAOMedication;
import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.data.entity.MedicationType;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.repo.DoctorRepo;
import de.adesso.termacare.database.repo.PatientRepo;
import de.adesso.termacare.database.services.MedicationService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.MedicationEdit;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static de.adesso.termacare.data.DependencyInjector.getInstance;

public class MedicationEditController extends AbstractController<MedicationEdit>{

	private MedicationService service;
	private PatientRepo patientService;
	private DoctorRepo doctorService;

	@Setter
	private DAOMedication medication;

	private Patient patient;
	private List<Doctor> doctors = new LinkedList<>();
	private MedicationType medicationType;

	@Override
	public void init(Stage stage, Scene scene){
		init(new MedicationEdit(this, stage, scene));
		launch();
	}

	private void launch(){
		view.getPatient().setText(patient != null ? "Patient: " + patient.getName() : "keinen Patienten ausgewählt");
		view.getDoctors().getChildren().clear();
		doctors.forEach(d -> view.getDoctors().getChildren().add(new Label(" - "+ d.getName())));
		view.getData().clear();
		view.getData().addAll(MedicationType.values());
		view.getMedicationType().getSelectionModel().select(medicationType != null ? medicationType : MedicationType.INSPECTION);
	}

	public void save(){
		LocalDateTime dateTime = view.getDatePicker().getDateTimeValue();
		service.createMedication(patient, doctors, medicationType, dateTime);
		backToOverview();
	}

	public void backToOverview(){
		PatientOverviewController oc = getInstance(PatientOverviewController.class);
		oc.init(stage, scene);
		oc.show();
	}

	public void selectPatient(){
		PatientSelectionController controller = getInstance(PatientSelectionController.class);
		controller.init(stage, scene);
		controller.show();
	}

	public void addDoctor(){
		DoctorSelectionController controller = getInstance(DoctorSelectionController.class);
		controller.init(stage, scene);
		controller.show();
	}

	public void removeDoctor(){
		DoctorSelectionController controller = getInstance(DoctorSelectionController.class);
		controller.setData(doctors);
		controller.init(stage, scene);
		controller.show();
	}

	void relaunch(){
		launch();
		view.getScene().setRoot(view.getMainPane());
		show();
	}

	public void patient(DAOPatient focusedItem){
		patient = patientService.getByID(focusedItem.getId());
		relaunch();
	}

	public void doctorAdd(DAODoctor focusedItem){
		doctors.add(doctorService.getByID(focusedItem.getId()));
		relaunch();
	}

	public void doctorRemove(DAODoctor focusedItem){
		doctors.remove(doctorService.getByID(focusedItem.getId()));
		relaunch();
	}
}
