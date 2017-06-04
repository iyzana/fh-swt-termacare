package de.adesso.termacare.gui.util;

import de.adesso.termacare.gui.construct.AbstractEditController;
import de.adesso.termacare.gui.construct.AbstractOverviewController;
import de.adesso.termacare.gui.dto.DtoAbstractData;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.List;

public class TableViewController<T extends DtoAbstractData, C extends AbstractOverviewController> extends Pane{
	private final C controller;
	private TableViewOptic<T> view;

	public TableViewController(C controller){
		this.controller = controller;
		view = new TableViewOptic<>(this);
	}

	public void generateColumnFor(String identifier) {
		generateColumnFor(identifier, 0, 0);
	}

	public void generateColumnFor(String identifier, int minWidth, int maxWidth){
		TableColumn<T, String> column = new TableColumn<>(identifier);
		if(minWidth != 0) column.setMinWidth(minWidth);
		if(maxWidth != 0) column.setMaxWidth(maxWidth);
		column.setCellValueFactory(new PropertyValueFactory<>(identifier));
		view.getTable().getColumns().add(column);
	}

	public void create() {
		AbstractEditController controller = this.controller.initEditController();
		controller.show();
	}

	public void edit(){
		T focusedItem = view.getTable().getFocusModel().getFocusedItem();
		AbstractEditController controller = this.controller.initEditController(focusedItem);
		controller.show();
	}

	public void delete() {
		T focusedItem = view.getTable().getFocusModel().getFocusedItem();
		view.getData().remove(focusedItem);
		controller.getRepo().delete(focusedItem.getId());
	}

	public void info() {
		T focusedItem = view.getTable().getFocusModel().getFocusedItem();
		AbstractEditController controller = this.controller.initEditController(focusedItem);
		controller.setDisable(true);
		controller.show();
	}

	public void addAll(List<T> data){
		view.getData().addAll(data);
	}
}
