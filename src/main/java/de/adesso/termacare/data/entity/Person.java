package de.adesso.termacare.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
abstract class Person {
    @Column(name = "title")
    protected String title;
    @Column(name = "gender")
    protected Gender gender;
    @Column(name = "givenNameLabel")
    protected String givenName;
    @Column(name = "familyNameLabel")
    protected String familyName;

	String getName(){
		return familyName + ", " + givenName;
	}
}
