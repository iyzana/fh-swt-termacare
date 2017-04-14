package de.adesso.termacare.gui.construct;

import javafx.scene.control.ButtonBase;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
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
	 * The {@link #pictureForButton(Image, ButtonBase)}-method for all components
	 */
	void addPicturesToButtons();

	/**
	 * Adds the icon to the button and further text is under the picture
	 * @param icon The picture for the button
	 * @param button The button
	 */
	void pictureForButton(Image icon, ButtonBase button);

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
