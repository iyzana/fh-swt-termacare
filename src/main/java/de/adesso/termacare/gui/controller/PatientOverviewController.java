package de.adesso.termacare.gui.controller;

import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.gui.construct.AbstractOverviewController;
import de.adesso.termacare.gui.dto.DtoAbstractData;
import de.adesso.termacare.gui.dto.DtoPatient;
import de.adesso.termacare.gui.view.PatientOverview;
import de.adesso.termacare.service.PatientService;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

import static de.adesso.termacare.util.DependencyInjector.getInstance;

public class PatientOverviewController extends AbstractOverviewController<PatientOverview, PatientEditController>{

    private PatientService service;

    @Override
    public void init(Stage stage, Scene scene) {
        super.init(new PatientOverview(this, stage, scene));
        fillTableWithColumns();
        loadPersonsToTable();
    }

    @Override
    public <I extends DtoAbstractData> PatientEditController initEditController(
            I focusedItem){
        PatientEditController controller = initEditController();
        controller.setPatient((DtoPatient) focusedItem);
        return controller;
    }

    @Override
    public PatientEditController initEditController(){
        PatientEditController controller = getInstance(PatientEditController.class);
        controller.init(stage, scene);
        return controller;
    }

    private void fillTableWithColumns() {
        view.getTableViewController().generateColumnFor("title", 100, 0);
        view.getTableViewController().generateColumnFor("givenName", 100, 0);
        view.getTableViewController().generateColumnFor("familyName", 100, 0);
        view.getTableViewController().generateColumnFor("gender", 0, 50);
        view.getTableViewController().generateColumnFor("livingPostcode");
        view.getTableViewController().generateColumnFor("livingAddress");
        view.getTableViewController().generateColumnFor("billingPostcode");
        view.getTableViewController().generateColumnFor("billingAddress");
    }

    private void loadPersonsToTable() {
        List<DtoPatient> patients = service.getPatients().stream().map(Patient::toDAO).collect(Collectors.toList());
        view.getTableViewController().addAll(patients);
    }

    public void backToOverview() {
        MedicationOverviewController oc = getInstance(MedicationOverviewController.class);
        oc.init(stage, scene);
        oc.show();
    }
}
