package de.adesso.termacare.gui.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class DtoPatient extends DtoAbstractData{
	private String gender;
	private String title;
	private String givenName;
	private String familyName;

	private String livingPostcode;
	private String livingAddress;

	private String billingPostcode;
	private String billingAddress;

	public DtoPatient(
					long id, String gender, String title, String givenName, String familyName, String livingPostcode,
					String livingAddress, String billingPostcode, String billingAddress){
		super(id);
		this.gender = gender;
		this.title = title;
		this.givenName = givenName;
		this.familyName = familyName;
		this.livingPostcode = livingPostcode;
		this.livingAddress = livingAddress;
		this.billingPostcode = billingPostcode;
		this.billingAddress = billingAddress;
	}
}
