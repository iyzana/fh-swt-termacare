package de.adesso.termacare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Patient extends Person implements EntityInterface{
	@Id @GeneratedValue
	private long id;

	@Column(name = "billingAddress")
	private Address billingAddress;
	@Column(name = "livingAddress")
	private Address livingAddress;
}
