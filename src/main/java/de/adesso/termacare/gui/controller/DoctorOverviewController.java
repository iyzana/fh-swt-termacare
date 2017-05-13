package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAODoctor;
import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.database.services.DoctorService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.DoctorOverview;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
public class DoctorOverviewController extends AbstractController<DoctorOverview> {

    private DoctorService service;

    @Override
    public void init(Stage stage, Scene scene) {
        super.init(new DoctorOverview(this, stage, scene));
        fillTableWithColumns();
        loadPersonsToTable();
        checkButtons();
    }

    private void fillTableWithColumns() {
        generateColumnFor("gender", 0, 50);
        generateColumnFor("givenName", 100, 0);
        generateColumnFor("familyName", 100, 0);
        generateColumnFor("amountOfPatients", 200, 0);
    }

    private void generateColumnFor(String identifier) {
        generateColumnFor(identifier, 0, 0);
    }

    private void generateColumnFor(String identifier, int minWidth, int maxWidth) {
        TableColumn<DAODoctor, String> column = new TableColumn<>(identifier);
        if (minWidth != 0) column.setMinWidth(minWidth);
        if (maxWidth != 0) column.setMaxWidth(maxWidth);
        column.setCellValueFactory(new PropertyValueFactory<>(identifier));
        view.getDoctorTableView().getColumns().add(column);
    }

    private void loadPersonsToTable() {
        List<DAODoctor> doctors = service.getDoctors().stream().map(this::doctorToDao).collect(toList());

        log.debug("loaded " + doctors.size() + " doctors");

        view.getDoctors().addAll(doctors);
    }

    private DAODoctor doctorToDao(Doctor doctor) {
        DAODoctor dao = doctor.toDAO();
        dao.setAmountOfPatients(service.getPatients(dao.getId()).size());
        return dao;
    }

    public void checkButtons() {
        view.getDoctorTableView().getFocusModel().focusedItemProperty().addListener((observable, oldValue, newValue) -> {
            Boolean visible = newValue != null;
            view.getEditDoctor().setDisable(visible);
            view.getDeleteDoctor().setDisable(visible);
            view.getInfoDoctor().setDisable(visible);
        });
    }

    public void newDoctor() {
        DoctorEditController doctorEditController = DependencyInjector.getInstance(DoctorEditController.class);
        doctorEditController.init(stage, scene);
        doctorEditController.show();
    }

    public void editDoctor() {
        DAODoctor focusedItem = view.getDoctorTableView().getFocusModel().getFocusedItem();
        DoctorEditController doctorEditController = DependencyInjector.getInstance(DoctorEditController.class);
        doctorEditController.init(stage, scene);
        doctorEditController.setDoctor(focusedItem);
        doctorEditController.show();
    }

    public void deleteDoctor() {
        DAODoctor focusedItem = view.getDoctorTableView().getFocusModel().getFocusedItem();
        view.getDoctors().remove(focusedItem);
        service.deleteDoctor(focusedItem.getId());
    }

    public void infoDoctor() {

    }
}
