package de.adesso.termacare.gui.construct;

public abstract class AbstractEditController<T extends AbstractView> extends AbstractController<T>{

	public abstract void setDisable(boolean disable);
}
