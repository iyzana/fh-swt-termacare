package de.adesso.termacare.data.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class DAOPatient{
	private long id;

	private String gender;
	private String givenName;
	private String familyName;

	private String livingPostcode;
	private String livingAddress;

	private String billingPostcode;
	private String billingAddress;
}
