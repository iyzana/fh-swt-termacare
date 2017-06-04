package de.adesso.termacare.gui.construct;

import de.adesso.termacare.database.construct.GenericRepo;
import de.adesso.termacare.gui.dto.DtoAbstractData;
import lombok.Getter;

public abstract class AbstractOverviewController<T extends AbstractOverviewView> extends AbstractController<T>{

	protected T view;

	@Getter
	private GenericRepo repo;

	public abstract <I extends DtoAbstractData> AbstractEditController<AbstractView> initEditController(I focusedItem);

	public abstract AbstractEditController<AbstractView> initEditController();
}
