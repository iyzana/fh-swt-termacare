package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.dao.DAOMedication;
import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.database.services.MedicationService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.MedicationOverview;
import javafx.collections.FXCollections;
import de.adesso.termacare.data.dao.DAOMedication;
import de.adesso.termacare.data.entity.Medication;
import de.adesso.termacare.database.services.DoctorService;
import de.adesso.termacare.database.services.MedicationService;
import de.adesso.termacare.database.services.PatientService;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.MedicationOverview;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
public class MedicationOverviewController extends AbstractController<MedicationOverview> {

    private MedicationService service;
    private PatientService patientService;
    private DoctorService doctorService;

    @Override
    public void init(Stage stage, Scene scene) {
        super.init(new MedicationOverview(this, stage, scene));
        fillTableWithColumns();
        loadMedicationssToTable();
        checkButtons();
    }

    private void fillTableWithColumns() {
        generateColumnFor("patientName", 0, 50);
        generateColumnFor("doctorNames", 200, 0);
        generateColumnFor("type", 100, 0);
        generateColumnFor("time", 200, 0);
    }

    private void generateColumnFor(String identifier) {
        generateColumnFor(identifier, 0, 0);
    }

	private void generateColumnFor(String identifier, int minWidth, int maxWidth){
		TableColumn<DAOMedication, String> column = new TableColumn<>(identifier);
		if(minWidth != 0) column.setMinWidth(minWidth);
		if(maxWidth != 0) column.setMaxWidth(maxWidth);
		column.setCellValueFactory(new PropertyValueFactory<>(identifier));
		view.getDoctorTableView().getColumns().add(column);
	}

    private void loadMedicationssToTable() {
        List<DAOMedication> medications = service.getMedications().stream().map(Medication::toDao).collect(toList());

        log.debug("loaded " + medications.size() + " medications");

        view.getMedications().addAll(medications);
    }

    public void checkButtons() {
        view.getMedicationTableView().getFocusModel().focusedItemProperty().addListener((observable, oldValue, newValue) -> {
            Boolean visible = newValue != null;
            view.getDeleteMedication().setDisable(visible);
            view.getInfoMedication().setDisable(visible);
        });
    }

    public void newMedication() {
        MedicationEditController medicationEditController = DependencyInjector.getInstance(MedicationEditController.class);
        medicationEditController.init(stage, scene);
        medicationEditController.show();
    }

    public void deleteMedication() {
        DAOMedication focusedItem = view.getMedicationTableView().getFocusModel().getFocusedItem();
        view.getMedications().remove(focusedItem);
        service.deleteMedication(focusedItem.getId());
    }

    public void infoMedication() {

    }

	public void gotoDoctors(){
		DoctorOverviewController doctorOverviewController = DependencyInjector.getInstance(DoctorOverviewController.class);
		doctorOverviewController.init(stage, scene);
		doctorOverviewController.show();
	}

	public void gotoPatients(){
		PatientOverviewController patientOverviewController = DependencyInjector.getInstance(PatientOverviewController.class);
		patientOverviewController.init(stage, scene);
		patientOverviewController.show();
	}
}
