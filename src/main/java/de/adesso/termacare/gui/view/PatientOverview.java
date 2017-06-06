package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.construct.AbstractOverviewView;
import de.adesso.termacare.gui.controller.PatientEditController;
import de.adesso.termacare.gui.controller.PatientOverviewController;
import de.adesso.termacare.gui.dto.DtoPatient;
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
public class PatientOverview extends AbstractOverviewView<PatientOverviewController>{

	private BorderPane pane = new BorderPane();
	private Button cancel = new Button("backToOverview");
	private TableViewController<DtoPatient, PatientOverviewController, PatientEditController> tableViewController;
	private VBox right = new VBox();

	public PatientOverview(PatientOverviewController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
		tableViewController = new TableViewController<>(controller);
	}

	@Override
	public void init(){

		right.getChildren().addAll(cancel);

		pane.setTop(LanguageSelection.getInstance().showLanguageSelection());
		pane.setRight(right);
		pane.setCenter(tableViewController.getTable());
		scene.setRoot(pane);
	}

	@Override
	public void fillComponentsWithSelectedLanguage(){
		fillComponentWithText(cancel, "backToOverview");
		tableViewController.fillComponents();
	}

	@Override
	public void registerListener(){
		cancel.setOnMouseClicked(event -> controller.backToOverview());
	}

	@Override
	public void setStyleClasses(){
		cancel.setId("patientOverviewCancel");
	}
}
