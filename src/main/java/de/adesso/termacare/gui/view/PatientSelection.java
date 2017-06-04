package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.dto.DtoPatient;
import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.PatientSelectionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientSelection extends AbstractView<PatientSelectionController>{

	private Button selectItem = new Button("Auswahl bestätigen");
	private Button back = new Button("Zurück");

	private ObservableList<DtoPatient> data = FXCollections.observableArrayList();
	private TableView<DtoPatient> tableView = new TableView<>(data);

	private BorderPane pane = new BorderPane();
	private HBox bottomBox = new HBox();

	public PatientSelection(PatientSelectionController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		bottomBox.getChildren().addAll(selectItem ,back);
		pane.setCenter(tableView);
		pane.setBottom(bottomBox);

		scene.setRoot(pane);
	}


	@Override
	public void fillComponentsWithSelectedLanguage(){

	}

	@Override
	public void registerListener(){
		selectItem.setOnMouseClicked(event -> controller.dataSelected());
		back.setOnMouseClicked(event -> controller.back());
	}

	@Override
	public void setStyleClasses(){

	}
}
