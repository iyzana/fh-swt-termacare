package de.adesso.termacare.data.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public final class DAODoctor{
	@NonNull
	private long id;

	@NonNull
	private String title;
	@NonNull
	private String gender;
	@NonNull
	private String givenName;
	@NonNull
	private String familyName;

	private int amountOfPatients;

}
