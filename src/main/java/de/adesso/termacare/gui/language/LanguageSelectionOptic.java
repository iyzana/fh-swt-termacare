package de.adesso.termacare.gui.language;

import de.adesso.termacare.util.Language;
import de.adesso.termacare.util.Textures;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lombok.Data;

import java.util.ArrayList;
import java.util.Locale;

@Data
class LanguageSelectionOptic{
	private HBox languageSelectionBox = new HBox();
	private Button selectLanguage = new Button();
	private Button german = new Button();
	private Button english = new Button();
	private ArrayList<Button> languages = new ArrayList<>();
	private boolean show;
	private Language language = Language.GERMAN;

	/**
	 * Build the Language Selection
	 */
	LanguageSelectionOptic(LanguageSelection controller) {

		fillButtonsWithText();
		fillButtonListWithButtons();

		languageSelectionBox.getChildren().add(selectLanguage);
		languageSelectionBox.setAlignment(Pos.TOP_LEFT);

		selectLanguage.setOnMouseClicked(event -> controller.showOrRemoveSelection());

		german.setOnMouseClicked(event -> controller.setLanguageAndRemoveButtons(Language.GERMAN, new Locale("de", "DE")));
		english.setOnMouseClicked(event -> controller.setLanguageAndRemoveButtons(Language.ENGLISH, new Locale("en", "GB")));
		languageSelectionBox.getStyleClass().add("languageSelectionBox");

		for (Button button : languages)
			button.getStyleClass().add("languageSelection");
		selectLanguage.getStyleClass().add("languageSelection");
	}


	/**
	 * fills the buttons with images<br>
	 * <br>
	 * Codetemplate: <br>
	 * {@code
	 * <button>.setGraphic(<image>);
	 * }
	 * <br>
	 * Codeexample: <br>
	 * {@code
	 * english.setGraphic(new ImageView(Textures.BRITISHFLAG.getImage()));
	 * }
	 */
	private void fillButtonsWithText() {
		selectLanguage.setGraphic(new ImageView(Textures.LANGUAGESELECTIONIMAGE.getImage()));
		german.setGraphic(new ImageView(Textures.GERMANFLAG.getImage()));
		english.setGraphic(new ImageView(Textures.BRITISHFLAG.getImage()));
	}

	/**
	 * fills the list of languages with the languages <br>
	 * <br>
	 * Codetemplate: <br>
	 * {@code
	 * <list>.add(<language>);
	 * }
	 * <br>
	 * Codeexample: <br>
	 * {@code
	 * languages.add(english);
	 * }
	 */
	private void fillButtonListWithButtons() {
		languages.add(german);
		languages.add(english);
	}
}
