package de.adesso.termacare.gui.construct;

import de.adesso.termacare.gui.language.LanguageSelection;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static de.adesso.termacare.TerMa.logger;

public abstract class AbstractController<T extends AbstractView> implements Controller {

	protected T view;
	protected Stage stage;
	protected Scene scene;

	protected void init(T view) {
		view.init();
		this.view = view;
		scene = view.getScene();
		stage = view.getStage();
		logger.info("Initialised in: \"" + this.getClass().getSimpleName() + "\" the view: \"" + view.getClass().getSimpleName() + "\"");
	}

	@Override
	public void show() {
		view.show();
	}

	LanguageSelection getLanguageSelection(){
		return LanguageSelection.getInstance();
	}
}
