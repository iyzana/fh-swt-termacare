package de.adesso.termacare.gui.construct;

import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class AbstractView<T extends AbstractController> implements View {

	protected T controller;
	@Getter
	private Stage stage;
	@Getter
	protected Scene scene;

	public AbstractView(T controller, Stage stage, Scene scene) {
		this.controller = controller;
		this.stage = stage;
		this.scene = scene;
	}

	@Override
	public void show() {
		setStyleClasses();
		registerListener();
		fillComponentsWithSelectedLanguage();
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void fillComponentWithText(Labeled labeled, String nameInBundle) {
		// stuff to do
	}
}
