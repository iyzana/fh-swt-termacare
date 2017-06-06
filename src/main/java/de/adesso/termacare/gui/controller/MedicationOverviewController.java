package de.adesso.termacare.gui.controller;

import de.adesso.termacare.database.dao.MedicationDao;
import de.adesso.termacare.database.entity.Medication;
import de.adesso.termacare.gui.construct.AbstractOverviewController;
import de.adesso.termacare.gui.dto.DtoAbstractData;
import de.adesso.termacare.gui.dto.DtoMedication;
import de.adesso.termacare.gui.view.MedicationOverview;
import de.adesso.termacare.service.DoctorService;
import de.adesso.termacare.service.MedicationService;
import de.adesso.termacare.service.PatientService;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static de.adesso.termacare.util.DependencyInjector.getInstance;
import static java.util.stream.Collectors.toList;

@Slf4j
public class MedicationOverviewController extends AbstractOverviewController<MedicationOverview, MedicationEditController>{

    private MedicationService service;
    private PatientService patientService;
    private DoctorService doctorService;

    @Override
    public void init(Stage stage, Scene scene) {
        super.init(new MedicationOverview(this, stage, scene));
        repo = getInstance(MedicationDao.class);
        fillTableWithColumns();
        loadMedicationsToTable();
    }

	@Override
	public <I extends DtoAbstractData> MedicationEditController initEditController(I focusedItem){
		MedicationEditController controller = initEditController();
		controller.setMedication((DtoMedication) focusedItem);
		return controller;
	}

	@Override
	public MedicationEditController initEditController(){
		MedicationEditController controller = getInstance(MedicationEditController.class);
		controller.init(stage, scene);
		return controller;
	}

	private void fillTableWithColumns(){
		view.getTableViewController().generateColumnFor("patientName");
		view.getTableViewController().generateColumnFor("doctorNames", 200, 0);
		view.getTableViewController().generateColumnFor("type", 100, 0);
		view.getTableViewController().generateColumnFor("time", 200, 0);
	}

    private void loadMedicationsToTable() {
        List<DtoMedication> medications = service.getMedications().stream().map(Medication::toDao).collect(toList());

        log.debug("loaded " + medications.size() + " medications");

        view.getTableViewController().addAll(medications);
    }

	public void gotoDoctors(){
		DoctorOverviewController doctorOverviewController = getInstance(DoctorOverviewController.class);
		doctorOverviewController.init(stage, scene);
		doctorOverviewController.show();
	}

	public void gotoPatients(){
		PatientOverviewController patientOverviewController = getInstance(PatientOverviewController.class);
		patientOverviewController.init(stage, scene);
		patientOverviewController.show();
	}
}
