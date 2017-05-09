package de.adesso.termacare.gui.construct;

import javafx.scene.Scene;
import javafx.stage.Stage;

import static de.adesso.termacare.TerMa.logger;

public abstract class AbstractController<T extends AbstractView> implements Controller {

	protected T view;
	protected static Stage stage;
	protected static Scene scene;

	protected void init(T view) {
		this.view = view;
		view.init();
		logger.info("Initialised in: \"" + this.getClass().getSimpleName() + "\" the view: \"" + view.getClass().getSimpleName() + "\"");
	}

	@Override
	public void show() {
		view.show();
	}
}
