package de.adesso.termacare.gui.util;

import de.adesso.termacare.gui.construct.AbstractEditController;
import de.adesso.termacare.gui.construct.AbstractOverviewController;
import de.adesso.termacare.gui.dto.DtoAbstractData;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class TableViewController<T extends DtoAbstractData, O extends AbstractOverviewController, E extends AbstractEditController>{
	private final O overview;
	private TableViewOptic<T> view;

	public TableViewController(O overview){
		this.overview = overview;
		view = new TableViewOptic<>(this);
		view.fillComponentsWithSelectedLanguage();
		view.setStyleClasses();
		view.registerListener();
	}

	public BorderPane getTable(){
		return view.getTablePane();
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

	void create() {
		E controller = (E) this.overview.initEditController();
		controller.show();
	}

	void edit(){
		T focusedItem = view.getTable().getFocusModel().getFocusedItem();
		E controller = (E) this.overview.initEditController(focusedItem);
		controller.show();
	}

	void delete() {
		T focusedItem = view.getTable().getFocusModel().getFocusedItem();
		view.getData().remove(focusedItem);
		overview.getRepo().delete(focusedItem.getId());
	}

	void info() {
		T focusedItem = view.getTable().getFocusModel().getFocusedItem();
		E controller = (E) this.overview.initEditController(focusedItem);
		controller.setDisable(true);
		controller.show();
	}

	public void addAll(List<T> data){
		view.getData().addAll(data);
	}

	public void fillComponents(){
		view.fillComponentsWithSelectedLanguage();
	}
}
