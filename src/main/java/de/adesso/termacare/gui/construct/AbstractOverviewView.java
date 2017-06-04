package de.adesso.termacare.gui.construct;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by slinde on 04.06.2017.
 */
public abstract class AbstractOverviewView<T extends AbstractOverviewController> extends AbstractView<T>{

	public AbstractOverviewView(T controller, Stage stage, Scene scene){
		super(controller, stage, scene);
	}
}
