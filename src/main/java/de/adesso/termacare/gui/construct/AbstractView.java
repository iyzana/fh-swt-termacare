package de.adesso.termacare.gui.construct;

import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		addPicturesToButtons();
		fillComponentsWithSelectedLanguage();
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void pictureForButton(Image icon, ButtonBase button) {
		button.setGraphic(new ImageView(icon));
		button.setContentDisplay(ContentDisplay.TOP);
	}

	@Override
	public void fillComponentWithText(Labeled labeled, String nameInBundle) {
		// stuff to do
	}
}
