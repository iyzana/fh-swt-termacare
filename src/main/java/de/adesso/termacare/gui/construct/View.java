package de.adesso.termacare.gui.construct;

import javafx.scene.control.Labeled;
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
	 * The {@link #fillComponentWithText(Labeled, String)}-method for all components
	 */
	void fillComponentsWithSelectedLanguage();

	/**
	 * This Method fills the component with text
	 * @param labeled The component
	 * @param nameInBundle The identifier in resource-bundle
	 */
	void fillComponentWithText(Labeled labeled, String nameInBundle);

	/**
	 * This method register the listener for e.g. buttons, slider etc.
	 */
	void registerListener();

	/**
	 * Here gets a component its style-classes
	 */
	void setStyleClasses();
}
