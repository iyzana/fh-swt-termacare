package de.adesso.termacare.data.entity;

import de.adesso.termacare.data.dao.DAOPatient;
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
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "billingAddress")
	private Address billingAddress;
	@Column(name = "livingAddress")
	private Address livingAddress;

	public DAOPatient toDAO(){
		return new DAOPatient(getGender().getValue(), getGivenName(), getFamilyName(),
						getLivingAddress().getCompressedDeparture(), getLivingAddress().getCompressedAddress(),
						getBillingAddress().getCompressedDeparture(), getBillingAddress().getCompressedAddress());
	}
}
