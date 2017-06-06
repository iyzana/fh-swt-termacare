package de.adesso.termacare.gui.construct;

import de.adesso.termacare.database.construct.GenericRepo;
import de.adesso.termacare.gui.dto.DtoAbstractData;
import lombok.Getter;

public abstract class AbstractOverviewController<T extends AbstractOverviewView, E extends AbstractEditController> extends AbstractController<T>{

	@Getter
	protected GenericRepo repo;

	public abstract <I extends DtoAbstractData> E initEditController(I focusedItem);

	public abstract E initEditController();
}
