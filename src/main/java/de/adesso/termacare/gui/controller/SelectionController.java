package de.adesso.termacare.gui.controller;

import de.adesso.termacare.data.dao.DAOData;
import de.adesso.termacare.data.entity.Doctor;
import de.adesso.termacare.gui.construct.AbstractController;
import de.adesso.termacare.gui.view.Selection;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class SelectionController extends AbstractController<Selection<DAOData>>{

	@Override
	public void init(Stage stage, Scene scene){
		init(new Selection<>(this, stage, scene));
		fillTableWithColumns();
	}

	private void fillTableWithColumns(){
		generateColumnFor("Titel", 0, 100);
		generateColumnFor("Vorname", 0, 200);
		generateColumnFor("Nachname", 200, 0);
		generateColumnFor("Gender", 100, 150);
	}

	private void generateColumnFor(String identifier) {
		generateColumnFor(identifier, 0, 0);
	}

	private void generateColumnFor(String identifier, int minWidth, int maxWidth){
		TableColumn<DAOData, String> column = new TableColumn<>(identifier);
		if(minWidth != 0) column.setMinWidth(minWidth);
		if(maxWidth != 0) column.setMaxWidth(maxWidth);
		column.setCellValueFactory(new PropertyValueFactory<>(identifier));
		view.getTableView().getColumns().add(column);
	}

	public void setData(List<Doctor> doctors){
		List<DAOData> daos = doctors.stream().map(Doctor::toDAO).collect(Collectors.toList());
		
		view.getData().addAll(daos);
	}
}
