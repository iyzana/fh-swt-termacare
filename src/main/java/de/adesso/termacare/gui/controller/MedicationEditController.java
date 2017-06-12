package de.adesso.termacare.gui.controller;

import de.adesso.termacare.database.dao.DoctorDao;
import de.adesso.termacare.database.dao.PatientDao;
import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.MedicationType;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.gui.construct.AbstractEditController;
import de.adesso.termacare.gui.dto.DtoDoctor;
import de.adesso.termacare.gui.dto.DtoMedication;
import de.adesso.termacare.gui.dto.DtoPatient;
import de.adesso.termacare.gui.view.MedicationEdit;
import de.adesso.termacare.service.MedicationService;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static de.adesso.termacare.util.DependencyInjector.getInstance;
import static java.util.stream.Collectors.toList;

public class MedicationEditController extends AbstractEditController<MedicationEdit> {
    
    private MedicationService service;
    private PatientDao patientService;
    private DoctorDao doctorService;
    
    @Setter
    private DtoMedication medication = null;
    
    private Patient patient;
    private List<Doctor> doctors = new LinkedList<>();
    private MedicationType medicationType = MedicationType.INSPECTION;
    
    @Override
    public void init(Stage stage, Scene scene) {
        init(new MedicationEdit(this, stage, scene));
        
        view.getData().clear();
        view.getData().addAll(MedicationType.values());
        view.getDoctors().getChildren().clear();
        
        if (medication != null) {
            patient = patientService.getByID(medication.getPatientId());
            doctors = medication.getDoctorIds().stream().map(doctorService::getByID).collect(toList());
            medicationType = MedicationType.valueOf(medication.getType());
        }
        
        launch();
    }
    
    private void launch() {
        if (patient != null)
            view.getPatient().setText(patient.getGivenName() + " " + patient.getFamilyName());
        else
            view.getPatient().setText("kein Patient ausgewählt");
        
        if (doctors != null)
            view.getDoctors().getChildren().addAll(doctors.stream().map(Doctor::getName).map(name -> new Label(" - " + name)).collect(toList()));
        if (medicationType != null)
            view.getMedicationType().getSelectionModel().select(medicationType);
    }
    
    public void save() {
        LocalDateTime dateTime = view.getDatePicker().getDateTimeValue();
        MedicationType type = view.getMedicationType().getValue();
        service.createMedication(medication == null ? 0 : medication.getId(), patient, doctors, type, dateTime);
        backToOverview();
    }
    
    public void backToOverview() {
        MedicationOverviewController oc = getInstance(MedicationOverviewController.class);
        oc.init(stage, scene);
        oc.show();
    }
    
    public void selectPatient() {
        PatientSelectionController controller = getInstance(PatientSelectionController.class);
        controller.init(stage, scene);
        controller.setData(patientService.list());
        controller.setController(this);
        controller.show();
    }
    
    public void addDoctor() {
        DoctorSelectionController controller = getInstance(DoctorSelectionController.class);
        controller.init(stage, scene);
        controller.setData(doctorService.list(), true);
        controller.setController(this);
        controller.show();
    }
    
    public void removeDoctor() {
        DoctorSelectionController controller = getInstance(DoctorSelectionController.class);
        controller.init(stage, scene);
        controller.setData(doctors, false);
        controller.setController(this);
        controller.show();
    }
    
    void relaunch() {
        launch();
        view.getScene().setRoot(view.getMainPane());
        show();
    }
    
    void patient(DtoPatient focusedItem) {
        patient = patientService.getByID(focusedItem.getId());
        relaunch();
    }
    
    void doctorAdd(DtoDoctor focusedItem) {
        doctors.add(doctorService.getByID(focusedItem.getId()));
        relaunch();
    }
    
    void doctorRemove(DtoDoctor focusedItem) {
        doctors.remove(doctorService.getByID(focusedItem.getId()));
        relaunch();
    }
    
    @Override
    public void setDisable(boolean disable) {
        view.setDisable(disable);
    }
    
}
