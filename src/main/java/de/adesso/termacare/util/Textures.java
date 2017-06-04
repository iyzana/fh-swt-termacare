package de.adesso.termacare.util;

import javafx.scene.image.Image;
import lombok.Getter;

public enum Textures{
	LANGUAGESELECTIONIMAGE( new Image("images/ic_language_black_48dp_2x.png")),
	GERMANFLAG(new Image("images/german_flag.png")),
	BRITISHFLAG(new Image("images/british-flag-small.png"));

	@Getter
	Image image;

	Textures(Image image) {
		this.image = image;
	}

}
