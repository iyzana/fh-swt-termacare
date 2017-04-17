package de.adesso.termacare.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum MedicationType{

	INSPECTION(0L,"inspection"),
	OPERATION(1L,"operation");

	private long id;
	private String type;
}
