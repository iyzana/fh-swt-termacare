package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAOPatient;
import de.adesso.termacare.data.entity.Patient;
import de.adesso.termacare.database.services.PatientService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.PatientOverview;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientOverviewController extends AbstractController<PatientOverview> {

    private PatientService service;

    @Override
    public void init(Stage stage, Scene scene) {
        super.init(new PatientOverview(this, stage, scene));
        fillTableWithColumns();
        loadPersonsToTable();
        checkButtons();
    }

    private void fillTableWithColumns() {
        generateColumnFor("title", 100, 0);
        generateColumnFor("givenName", 100, 0);
        generateColumnFor("familyName", 100, 0);
        generateColumnFor("gender", 0, 50);
        generateColumnFor("livingPostcode");
        generateColumnFor("livingAddress");
        generateColumnFor("billingPostcode");
        generateColumnFor("billingAddress");
    }

    private void generateColumnFor(String identifier) {
        generateColumnFor(identifier, 0, 10000);
    }

    private void generateColumnFor(String identifier, int minWidth, int maxWidth) {
        TableColumn<DAOPatient, String> column = new TableColumn<>(identifier);
        if (minWidth != 0) column.setMinWidth(minWidth);
        if (maxWidth != 0) column.setMaxWidth(maxWidth);
        column.setCellValueFactory(new PropertyValueFactory<>(identifier));
        view.getPatientTableView().getColumns().add(column);
    }

    private void loadPersonsToTable() {
        List<DAOPatient> patients = service.getPatients().stream().map(Patient::toDAO).collect(Collectors.toList());
        view.getPatients().addAll(patients);
    }

    public void checkButtons() {
        Optional<DAOPatient> patient = Optional.ofNullable(view.getPatientTableView().getFocusModel().getFocusedItem());
        Boolean visible = patient.map(daoPatient -> false).orElse(true);
        view.getEditPatient().setDisable(visible);
        view.getDeletePatient().setDisable(visible);
        view.getInfoPatient().setDisable(visible);
    }

    public void newPatient() {
        PatientEditController patientEditController = DependencyInjector.getInstance(PatientEditController.class);
        patientEditController.init(stage, scene);
        patientEditController.show();
    }

    public void editPatient() {
        DAOPatient focusedItem = view.getPatientTableView().getFocusModel().getFocusedItem();
        PatientEditController patientEditController = DependencyInjector.getInstance(PatientEditController.class);
        patientEditController.init(stage, scene);
        patientEditController.setPatient(focusedItem);
        patientEditController.show();
    }

    public void deletePatient() {

    }

    public void infoPatient() {

    }
}
