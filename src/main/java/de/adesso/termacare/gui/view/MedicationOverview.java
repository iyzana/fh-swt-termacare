package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.construct.AbstractOverviewView;
import de.adesso.termacare.gui.controller.MedicationOverviewController;
import de.adesso.termacare.gui.dto.DtoMedication;
import de.adesso.termacare.gui.util.LanguageSelection;
import de.adesso.termacare.gui.util.TableViewController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicationOverview extends AbstractOverviewView<MedicationOverviewController>{

	private Button doctors = new Button();
	private Button patients = new Button();

	private VBox gotoBox = new VBox();
	private BorderPane pane = new BorderPane();
	private TableViewController<DtoMedication, MedicationOverviewController> tableViewController;

	public MedicationOverview(MedicationOverviewController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
		tableViewController = new TableViewController<>(controller);
	}

	@Override
	public void init(){
		gotoBox.getChildren().addAll(doctors, patients);

		pane.setTop(LanguageSelection.getInstance().showLanguageSelection());
		pane.setCenter(tableViewController.getTable());
		pane.setRight(gotoBox);
		scene = new Scene(pane);
		scene.getStylesheets().add("main.css");
	}

	@Override
	public void fillComponentsWithSelectedLanguage(){
		fillComponentWithText(doctors, "allDoctors");
		fillComponentWithText(patients, "allPatients");
	}

	@Override
	public void registerListener(){
		doctors.setOnMouseClicked(event -> controller.gotoDoctors());
		patients.setOnMouseClicked(event -> controller.gotoPatients());
	}

	@Override
	public void setStyleClasses(){

	}
}
