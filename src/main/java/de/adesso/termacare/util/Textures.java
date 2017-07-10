package de.adesso.termacare.util;

import javafx.scene.image.Image;
import lombok.Getter;

/**
 * List and paths of available images in the application
 */
public enum Textures{
	LANGUAGESELECTIONIMAGE( new Image("images/ic_language_black_48dp_2x.png")),
	GERMANFLAG(new Image("images/german_flag.png")),
	BRITISHFLAG(new Image("images/british-flag-small.png"));

	@Getter
	Image image;
	
	/**
	 * Create Texture by image
	 *
	 * @param image Image of this texture
	 */
	Textures(Image image) {
		this.image = image;
	}

}
