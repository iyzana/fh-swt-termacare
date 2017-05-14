package de.adesso.termacare.gui.view;

import de.adesso.termacare.data.dao.DAOData;
import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.SelectionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Selection extends AbstractView<SelectionController>{

	private Button selectItem = new Button("Auswahl bestätigen");
	private Button back = new Button("Zurück");

	private ObservableList<DAOData> data = FXCollections.observableArrayList();
	private TableView<DAOData> tableView = new TableView<>(data);

	private BorderPane pane = new BorderPane();

	public Selection(SelectionController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){
		pane.setCenter(tableView);
		pane.setBottom(selectItem);

		scene.setRoot(pane);
	}

	@Override
	public void addPicturesToButtons(){

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
