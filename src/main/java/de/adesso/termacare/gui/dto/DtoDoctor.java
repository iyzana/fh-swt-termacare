package de.adesso.termacare.gui.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
public final class DtoDoctor extends DtoAbstractData{

	@NonNull
	private String title;
	@NonNull
	private String gender;
	@NonNull
	private String givenName;
	@NonNull
	private String familyName;

	private int amountOfPatients;

	public DtoDoctor(long id, String title, String gender, String givenName, String familyName){
		super(id);
		this.title = title;
		this.gender = gender;
		this.givenName = givenName;
		this.familyName = familyName;
	}
}
