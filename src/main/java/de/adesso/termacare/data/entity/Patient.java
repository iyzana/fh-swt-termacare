package de.adesso.termacare.data.entity;

import de.adesso.termacare.data.dao.DAOPatient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "patient")
public class Patient extends Person implements EntityInterface{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name = "billingAddress")
	private Address billingAddress;
	@OneToOne()
	@JoinColumn(name = "livingAddress")
	private Address livingAddress;

	public DAOPatient toDAO(){
		return new DAOPatient(getGender().getValue(), getGivenName(), getFamilyName(),
						getLivingAddress().getCompressedDeparture(), getLivingAddress().getCompressedAddress(),
						getBillingAddress().getCompressedDeparture(), getBillingAddress().getCompressedAddress());
	}
}
