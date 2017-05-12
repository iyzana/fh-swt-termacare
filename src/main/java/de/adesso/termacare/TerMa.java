package de.adesso.termacare;

import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.database.repo.AddressRepo;
import de.adesso.termacare.database.repo.DoctorRepo;
import de.adesso.termacare.database.repo.MedicationRepo;
import de.adesso.termacare.database.repo.PatientRepo;
import de.adesso.termacare.database.services.DoctorService;
import de.adesso.termacare.database.services.MedicationService;
import de.adesso.termacare.database.services.PatientService;
import de.adesso.termacare.gui.controller.DoctorEditController;
import de.adesso.termacare.gui.controller.MedicationEditController;
import de.adesso.termacare.gui.controller.PatientOverviewController;
import de.adesso.termacare.gui.controller.PatientEditController;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.adesso.termacare.data.DependencyInjector.*;

@Slf4j
public class TerMa extends Application{

	public static Logger logger = LoggerFactory.getLogger(TerMa.class);

	@Override
	public void start(Stage primaryStage) throws Exception{
		inject(PatientService.class, MedicationService.class, DoctorService.class, PatientRepo.class, AddressRepo.class,
		       MedicationRepo.class, DoctorRepo.class, PatientOverviewController.class, PatientEditController.class,
		       DoctorEditController.class, MedicationEditController.class
		);
//		createTestData();

		PatientOverviewController overview = getInstance(PatientOverviewController.class);
		overview.init(primaryStage, null);
		overview.show();
	}

	private void createTestData(){
		PatientService patientService = getInstance(PatientService.class);
		patientService.createPatient("Herr", Gender.MALE, "Jannis", "Kaiser", new Address(), new Address());

		patientService.getPatients().forEach(p -> log.debug(p.toString()));
	}

	public static void main(String[] args){
		Application.launch(TerMa.class, args);
	}
}
