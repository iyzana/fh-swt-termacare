package de.adesso.termacare.gui.view;

import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.gui.controller.MedicationEditController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MedicationEdit extends AbstractView<MedicationEditController>{

	public MedicationEdit(MedicationEditController controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}

	@Override
	public void init(){

	}

	@Override
	public void addPicturesToButtons(){

	}

	@Override
	public void fillComponentsWithSelectedLanguage(){

	}

	@Override
	public void registerListener(){

	}

	@Override
	public void setStyleClasses(){

	}
}
