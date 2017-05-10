package de.adesso.termacare;

import de.adesso.termacare.data.DependencyInjector;
import de.adesso.termacare.data.entity.Address;
import de.adesso.termacare.data.entity.Gender;
import de.adesso.termacare.database.repo.AddressRepo;
import de.adesso.termacare.database.repo.PatientRepo;
import de.adesso.termacare.database.services.PatientService;
import de.adesso.termacare.gui.controller.OverviewController;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class TerMa extends Application {

    public static Logger logger = LoggerFactory.getLogger(TerMa.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        DependencyInjector.inject(PatientService.class, PatientRepo.class, AddressRepo.class);
        createTestData();
        
        OverviewController overview = new OverviewController();
        overview.init(primaryStage, null);
        overview.show();
    }
    
    private void createTestData() {
        PatientService patientService = DependencyInjector.getInstance(PatientService.class);
        patientService.createPatient("Herr", Gender.MALE, "Jannis", "Kaiser", new Address(), new Address());
    
        patientService.getPatients().forEach(p -> log.debug(p.toString()));
    }
    
    public static void main(String[] args) {
        Application.launch(TerMa.class, args);
    }
}
