package de.adesso.termacare.util;

import javafx.scene.image.Image;
import lombok.Getter;

public enum Textures{
	LANGUAGESELECTIONIMAGE( new Image("Images/PNG/ic_language_black_48dp_2x.png")),
	GERMANFLAG(new Image("Images/PNG/german_flag.png")),
	BRITISHFLAG(new Image("Images/PNG/british-flag-small.png"));

	@Getter
	Image image;

	Textures(Image image) {
		this.image = image;
	}

}
