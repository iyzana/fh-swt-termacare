package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAOMedication;
import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.data.entity.MedicationType;
import de.adesso.termacare.data.entity.Patient;
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

public class MedicationEditController extends AbstractController<MedicationEdit>{

	private MedicationService service;

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

	public void launch(){
		view.getPatient().setText(patient != null ? "Patient: " + patient.getName() : "keinen Patienten ausgewählt");
		view.getDoctors().getChildren().clear();
		doctors.forEach(d -> view.getDoctors().getChildren().add(new Label(" - "+ d.getName())));
		view.getData().clear();
		view.getData().addAll(MedicationType.values());
		view.getMedicationType().getSelectionModel().select(medicationType != null ? medicationType : MedicationType.INSPECTION);
	}

	public void save(){
		service.createMedication(patient, doctors, medicationType, LocalDateTime.now());
		backToOverview();
	}

	public void backToOverview(){
		PatientOverviewController oc = DependencyInjector.getInstance(PatientOverviewController.class);
		oc.init(stage, scene);
		oc.show();
	}
}
