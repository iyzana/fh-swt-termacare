package de.adesso.termacare.gui.construct;

import javafx.stage.Stage;

interface View{
	/**
	 * Here is the content defined witch is shown
	 */
	void init();

	/**
	 * Calls {@link Stage#show()}
	 */
	void show();

	/**
	 * This method register the listener for e.g. buttons, slider etc.
	 */
	void registerListener();

	/**
	 * Here gets a component its style-classes
	 */
	void setStyleClasses();
}
