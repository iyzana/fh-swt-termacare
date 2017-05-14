package de.adesso.termacare.gui.view;

import de.adesso.termacare.data.dao.DAODoctor;
import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.DoctorOverviewController;
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
public class DoctorOverview extends AbstractView<DoctorOverviewController>{

	private BorderPane pane = new BorderPane();
	private ObservableList<DAODoctor> doctors = FXCollections.observableArrayList();
	private TableView<DAODoctor> doctorTableView = new TableView<>(doctors);
	private	Button newDoctor = new Button("new");
	private	Button editDoctor = new Button("edit");
	private	Button deleteDoctor = new Button("delete");
	private Button infoDoctor = new Button("info");
	private Button cancel = new Button("backToOverview");
	private HBox bottomBox = new HBox();

	public DoctorOverview(DoctorOverviewController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		bottomBox.getChildren().addAll(newDoctor, editDoctor, deleteDoctor, infoDoctor, cancel);

		pane.setCenter(doctorTableView);
		pane.setBottom(bottomBox);

		scene.setRoot(pane);
	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){
		fillComponentWithText(newDoctor, "newDoctor");
		fillComponentWithText(editDoctor, "editDoctor");
		fillComponentWithText(deleteDoctor, "deleteDoctor");
		fillComponentWithText(infoDoctor, "infoDoctor");
		fillComponentWithText(cancel, "backToOverview");
	}

	@Override
	public void registerListener(){
		doctorTableView.setOnMouseClicked(event -> controller.checkButtons());
		newDoctor.setOnMouseClicked(event -> controller.newDoctor());
		editDoctor.setOnMouseClicked(event -> controller.editDoctor());
		deleteDoctor.setOnMouseClicked(event -> controller.deleteDoctor());
		infoDoctor.setOnMouseClicked(event -> controller.infoDoctor());
		cancel.setOnMouseClicked(event -> controller.backToOverview());
	}

	@Override
	public void setStyleClasses(){
		newDoctor.setId("newDoctor");
		editDoctor.setId("editDoctor");
		deleteDoctor.setId("deleteDoctor");
		infoDoctor.setId("infoDoctor");
		cancel.setId("doctorOverviewCancel");

		doctorTableView.setId("patientList");
		bottomBox.setId("bottomBox");
	}
}
