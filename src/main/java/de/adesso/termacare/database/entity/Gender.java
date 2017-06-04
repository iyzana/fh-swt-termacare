package de.adesso.termacare.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum Gender{
	MALE("male"),
	FEMALE("female"),
	OTHER("other");

	private String value;
}