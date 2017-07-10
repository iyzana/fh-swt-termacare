package de.adesso.termacare.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for representing the gender of a person
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum Gender{
	MALE("male"),
	FEMALE("female"),
	OTHER("other");

	private String value;
}
