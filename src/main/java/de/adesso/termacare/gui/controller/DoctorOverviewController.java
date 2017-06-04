package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DtoDoctor;
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
	public void init(Stage stage, Scene scene){
		super.init(new DoctorOverview(this, stage, scene));
		fillTableWithColumns();
		loadDoctorsToTable();
	}

    private void fillTableWithColumns() {
        generateColumnFor("gender", 0, 0);
        generateColumnFor("givenName", 100, 0);
        generateColumnFor("familyName", 100, 0);
        generateColumnFor("amountOfPatients", 200, 0);
    }

    private void generateColumnFor(String identifier) {
        generateColumnFor(identifier, 0, 0);
    }

    private void generateColumnFor(String identifier, int minWidth, int maxWidth) {
        TableColumn<DtoDoctor, String> column = new TableColumn<>(identifier);
        if (minWidth != 0) column.setMinWidth(minWidth);
        if (maxWidth != 0) column.setMaxWidth(maxWidth);
        column.setCellValueFactory(new PropertyValueFactory<>(identifier));
        view.getDoctorTableView().getColumns().add(column);
    }

    private void loadDoctorsToTable() {
        List<DtoDoctor> doctors = service.getDoctors().stream().map(this::doctorToDao).collect(toList());

        log.debug("loaded " + doctors.size() + " doctorIds");

        view.getDoctors().addAll(doctors);
    }

    private DtoDoctor doctorToDao(Doctor doctor) {
        DtoDoctor dao = doctor.toDAO();
        dao.setAmountOfPatients(service.getPatients(dao.getId()).size());
        return dao;
    }

    public void backToOverview() {
        MedicationOverviewController oc = DependencyInjector.getInstance(MedicationOverviewController.class);
        oc.init(stage, scene);
        oc.show();
    }

    public void newDoctor() {
        DoctorEditController doctorEditController = DependencyInjector.getInstance(DoctorEditController.class);
        doctorEditController.init(stage, scene);
        doctorEditController.show();
    }

    public void editDoctor() {
        DtoDoctor focusedItem = view.getDoctorTableView().getFocusModel().getFocusedItem();
        DoctorEditController doctorEditController = DependencyInjector.getInstance(DoctorEditController.class);
        doctorEditController.init(stage, scene);
        doctorEditController.setDoctor(focusedItem);
        doctorEditController.show();
    }

    public void deleteDoctor() {
        DtoDoctor focusedItem = view.getDoctorTableView().getFocusModel().getFocusedItem();
        view.getDoctors().remove(focusedItem);
        service.deleteDoctor(focusedItem.getId());
    }

	public void infoDoctor(){

	}
}
