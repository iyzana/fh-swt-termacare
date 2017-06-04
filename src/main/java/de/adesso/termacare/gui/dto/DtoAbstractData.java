package de.adesso.termacare.gui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class DtoAbstractData implements DtoData{
	private long id;
}
