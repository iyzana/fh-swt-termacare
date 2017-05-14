package de.adesso.termacare;

import de.adesso.termacare.database.repo.AddressRepo;
import de.adesso.termacare.database.repo.DoctorRepo;
import de.adesso.termacare.database.repo.MedicationRepo;
import de.adesso.termacare.database.repo.PatientRepo;
import de.adesso.termacare.database.services.DoctorService;
import de.adesso.termacare.database.services.MedicationService;
import de.adesso.termacare.database.services.PatientService;
import de.adesso.termacare.gui.controller.DoctorEditController;
import de.adesso.termacare.gui.controller.DoctorOverviewController;
import de.adesso.termacare.gui.controller.MedicationEditController;
import de.adesso.termacare.gui.controller.MedicationOverviewController;
import de.adesso.termacare.gui.controller.PatientEditController;
import de.adesso.termacare.gui.controller.PatientOverviewController;
import de.adesso.termacare.gui.controller.SelectionController;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.adesso.termacare.data.DependencyInjector.getInstance;
import static de.adesso.termacare.data.DependencyInjector.inject;

@Slf4j
public class TerMa extends Application{

	public static Logger logger = LoggerFactory.getLogger(TerMa.class);

	@Override
	public void start(Stage primaryStage) throws Exception{
		inject(PatientService.class, MedicationService.class, DoctorService.class, PatientRepo.class, AddressRepo.class,
		       MedicationRepo.class, DoctorRepo.class, MedicationOverviewController.class, DoctorOverviewController.class,
		       PatientOverviewController.class, PatientEditController.class, SelectionController.class,
		       DoctorEditController.class, MedicationEditController.class, SelectionController.class
		);

		primaryStage.setHeight(900);
		primaryStage.setWidth(1200);

		MedicationOverviewController view = getInstance(MedicationOverviewController.class);
		view.init(primaryStage, null);
		view.show();
	}

	public static void main(String[] args){
		Application.launch(TerMa.class, args);
	}
}
