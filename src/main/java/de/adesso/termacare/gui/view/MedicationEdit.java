package de.adesso.termacare.gui.view;

import de.adesso.termacare.data.entity.MedicationType;
import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.MedicationEditController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicationEdit extends AbstractView<MedicationEditController>{

	private Button selectPatient = new Button("Patienten wählen");
	private Button addDoctor = new Button("Doktor hinzufügen");
	private Button removeDoctor = new Button("Doktor entfernen");
	private Button save = new Button("Speichern");
	private Button cancel = new Button("Zurück zur Übersicht");

	private Label patient = new Label("Keinen Patienten ausgewählt");
	private Label doctor = new Label("Bahandelnde Ärzte: ");
	private Label medication = new Label("Art der Behandlung: ");
	private Label medicationDate = new Label("Datum: ");
	private Label medicationTime = new Label("Uhrzeit: ");
	private Label medications = new Label("Behandlungsinhalte: ");

	private ObservableList<MedicationType> data = FXCollections.observableArrayList();
	private ComboBox<MedicationType> medicationType = new ComboBox<>(data);

	private DatePicker datePicker = new DatePicker();
	private TextField medicationStatTime = new TextField();
	private TextArea medicationInfo = new TextArea();

	private HBox medicationSelection = new HBox();
	private HBox patientSelection = new HBox();
	private HBox doctorSelection = new HBox();
	private VBox doctors = new VBox();
	private VBox doctorsBox = new VBox();
	private HBox bottomBox = new HBox();
	private BorderPane midPane = new BorderPane();
	private BorderPane mainPane = new BorderPane();

	public MedicationEdit(MedicationEditController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		patientSelection.getChildren().addAll(patient, selectPatient);
		doctorSelection.getChildren().addAll(addDoctor, removeDoctor);
		doctorsBox.getChildren().addAll(doctor, doctors, doctorSelection);
		medicationSelection.getChildren().addAll(medication, medicationType, medicationDate, datePicker, medicationTime, medicationStatTime, medications);

		midPane.setTop(medicationSelection);
		midPane.setCenter(medicationInfo);

		bottomBox.getChildren().addAll(save, cancel);

		mainPane.setLeft(doctorsBox);
		mainPane.setTop(patientSelection);
		mainPane.setCenter(midPane);
		mainPane.setBottom(bottomBox);
		scene.setRoot(mainPane);
	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){

	}

	@Override
	public void registerListener(){
		medicationStatTime.setOnKeyReleased(event -> controller.isValid());
		save.setOnMouseClicked(event -> controller.save());
		cancel.setOnMouseClicked(event -> controller.backToOverview());
	}

	@Override
	public void setStyleClasses(){

	}
}
