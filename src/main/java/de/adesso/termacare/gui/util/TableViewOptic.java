package de.adesso.termacare.gui.util;

import de.adesso.termacare.gui.dto.DtoData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Data;

import static de.adesso.termacare.gui.construct.AbstractView.fillComponentWithText;

@Data
public class TableViewOptic<T extends DtoData>{

	private final TableView controller;
	private final BorderPane tablePane = new BorderPane();
	private Button create = new Button();
	private	Button edit = new Button();
	private	Button delete = new Button();
	private Button info = new Button();

	private HBox bottomBox = new HBox();

	private ObservableList<T> data = FXCollections.observableArrayList();
	private javafx.scene.control.TableView<T> table = new javafx.scene.control.TableView<>(data);

	public TableViewOptic(TableView controller){
		this.controller = controller;
		bottomBox.getChildren().addAll(create, edit, delete, info);
		tablePane.setBottom(bottomBox);
		tablePane.setCenter(table);
	}

	public void registerListener(){
		create.setOnMouseClicked(event -> controller.create());
		edit.setOnMouseClicked(event -> controller.edit());
		delete.setOnMouseClicked(event -> controller.delete());
		info.setOnMouseClicked(event -> controller.info());
	}

	public void fillComponentsWithSelectedLanguage(){
		fillComponentWithText(create, "new");
		fillComponentWithText(edit, "edit");
		fillComponentWithText(delete, "delete");
		fillComponentWithText(info, "info");
	}

	public void setStyleClasses(){
		create.setId("create");
		edit.setId("edit");
		delete.setId("delete");
		info.setId("info");

		table.setId("list");
		bottomBox.setId("bottomBox");
	}
}