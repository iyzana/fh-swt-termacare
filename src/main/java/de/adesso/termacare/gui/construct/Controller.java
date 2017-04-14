package de.adesso.termacare.gui.construct;

import javafx.scene.Scene;
import javafx.stage.Stage;

interface Controller{

	/**
	 * Creates a new view with the stage.
	 * Calls the <code>init()</code> on view.
	 *
	 * @param stage The stage. Given to view.
	 * @param scene The scene. Given to view.
	 */
	void init(Stage stage, Scene scene);

	/**
	 * Calls <code>show()</code> on view
	 */
	void show();
}
