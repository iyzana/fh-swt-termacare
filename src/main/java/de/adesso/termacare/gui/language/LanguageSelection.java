package de.adesso.termacare.gui.language;

import de.adesso.termacare.gui.construct.AbstractView;
import de.adesso.termacare.util.Language;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j
@Data
public class LanguageSelection{
	private AbstractView view;

	private final static LanguageSelection languageSelection = new LanguageSelection();

	public static LanguageSelection getInstance() {
		return languageSelection;
	}

	/**
	 * Build the Language Selection
	 */
	private LanguageSelection() {
		optic = new LanguageSelectionOptic(this);
	}

	private LanguageSelectionOptic optic;
	private Locale locale = Locale.getDefault();

	/**
	 * Close LanguageSelection and return it
	 *
	 * @return The LanguageSelection
	 */
	public HBox showLanguageSelection() {
		if (isVisible()) showOrRemoveSelection();
		return optic.getLanguageSelectionBox();
	}

	private boolean isVisible() {
		return optic.isShow();
	}

	/**
	 * call {@link #removeSelection()} or {@link #showSelection()}
	 */
	void showOrRemoveSelection() {
		if (!optic.isShow()) showSelection();
		else removeSelection();
		optic.setShow(!optic.isShow());
	}

	/**
	 * Removes the LanguageButtons from the Box
	 */
	private void removeSelection() {
		log.info("Language selection is closed");
		for (Button language : optic.getLanguages()) {
			optic.getLanguageSelectionBox().getChildren().remove(language);
		}
	}

	/**
	 * Adds the LanguageButtons to the Box
	 */
	private void showSelection() {
		log.info("Language selection is opened");
		for (Button language : optic.getLanguages()) {
			optic.getLanguageSelectionBox().getChildren().add(language);
		}
	}

	/**
	 * This Method selects the given Language as the new one and close the LanguageSelection
	 *
	 * @param language The new Language
	 * @param locale   The Locale of the Language
	 */
	void setLanguageAndRemoveButtons(Language language, Locale locale) {
		setNewLanguage(language, locale);
		removeSelection();

		optic.setShow(!optic.isShow());
	}

	private void setNewLanguage(Language language, Locale locale) {
		log.info(language.name() + " is selected as the new language, the old one was " + this.optic.getLanguage().name());
		this.optic.setLanguage(language);
		this.locale = locale;
		view.fillComponentsWithSelectedLanguage();
	}
}
