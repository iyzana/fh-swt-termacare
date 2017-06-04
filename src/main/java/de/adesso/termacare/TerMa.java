package de.adesso.termacare;

import de.adesso.termacare.database.entity.Address;
import de.adesso.termacare.database.entity.Doctor;
import de.adesso.termacare.database.entity.Gender;
import de.adesso.termacare.database.entity.MedicationType;
import de.adesso.termacare.database.entity.Patient;
import de.adesso.termacare.database.repo.AddressRepo;
import de.adesso.termacare.database.repo.DoctorRepo;
import de.adesso.termacare.database.repo.MedicationRepo;
import de.adesso.termacare.database.repo.PatientRepo;
import de.adesso.termacare.gui.controller.DoctorEditController;
import de.adesso.termacare.gui.controller.DoctorOverviewController;
import de.adesso.termacare.gui.controller.DoctorSelectionController;
import de.adesso.termacare.gui.controller.MedicationEditController;
import de.adesso.termacare.gui.controller.MedicationOverviewController;
import de.adesso.termacare.gui.controller.PatientEditController;
import de.adesso.termacare.gui.controller.PatientOverviewController;
import de.adesso.termacare.gui.controller.PatientSelectionController;
import de.adesso.termacare.service.DoctorService;
import de.adesso.termacare.service.MedicationService;
import de.adesso.termacare.service.PatientService;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

import static de.adesso.termacare.util.DependencyInjector.getInstance;
import static de.adesso.termacare.util.DependencyInjector.inject;
import static java.util.Arrays.asList;

@Slf4j
public class TerMa extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        inject(PatientService.class, MedicationService.class, DoctorService.class, PatientRepo.class, AddressRepo.class,
                MedicationRepo.class, DoctorRepo.class, MedicationOverviewController.class, DoctorOverviewController.class,
                PatientOverviewController.class, PatientEditController.class, DoctorSelectionController.class,
                PatientSelectionController.class, DoctorEditController.class, MedicationEditController.class
        ); // create these classes and put the correct instances into the fields of them
        
        if (getInstance(PatientService.class).getPatients().isEmpty())
            createTestData();
        primaryStage.setHeight(900);
        primaryStage.setWidth(1200);
        
        MedicationOverviewController view = getInstance(MedicationOverviewController.class);
        view.init(primaryStage, null);
        view.show();
    }
    
    private void createTestData() {
        PatientService patientService = getInstance(PatientService.class);
        DoctorService doctorService = getInstance(DoctorService.class);
        MedicationService medicationService = getInstance(MedicationService.class);
        
        Address billing1 = new Address();
        billing1.setPostcode("44269");
        billing1.setDeparture("Dortmund");
        billing1.setAddress("Emil-Figge Straße");
        billing1.setNumber("42");
        Address living1 = new Address();
        living1.setPostcode("44269");
        living1.setDeparture("Dortmund");
        living1.setAddress("Emil-Figge Straße");
        living1.setNumber("42");
        patientService.createOrUpdatePatient(0, "Herr", Gender.MALE, "Jannis", "Kaiser", billing1, living1);
        Address billing2 = new Address();
        billing2.setPostcode("44139");
        billing2.setDeparture("Dortmund");
        billing2.setAddress("Sonnenstraße");
        billing2.setNumber("96");
        Address living2 = new Address();
        living2.setPostcode("44139");
        living2.setDeparture("Hamm");
        living2.setAddress("Andere Straße");
        living2.setNumber("4");
        patientService.createOrUpdatePatient(0, "Herr", Gender.MALE, "Sebastian", "Linde", billing2, living2);
        
        doctorService.createOrUpdateDoctor(0, "Dr. med.", Gender.MALE, "Matthias", "Arzt");
        doctorService.createOrUpdateDoctor(0, "Dr. med.", Gender.FEMALE, "Marie", "Ärztin");
        
        List<Doctor> doctors = doctorService.getDoctors();
        List<Patient> patients = patientService.getPatients();
        
        medicationService.createMedication(patients.get(0), asList(doctors.get(1)), MedicationType.INSPECTION, LocalDateTime.now().plusWeeks(1));
        medicationService.createMedication(patients.get(0), asList(doctors.get(0), doctors.get(1)), MedicationType.OPERATION, LocalDateTime.now().plusWeeks(2));
        medicationService.createMedication(patients.get(1), asList(doctors.get(0)), MedicationType.INSPECTION, LocalDateTime.now().plusDays(3));
    }
    
    public static void main(String[] args) {
        Application.launch(TerMa.class, args);
    }
}
