package de.adesso.termacare.data.entity;

import de.adesso.termacare.data.dao.DAODoctor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "doctor")
@ToString(callSuper = true)
public class Doctor extends Person implements EntityInterface{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public DAODoctor toDAO(){
		return new DAODoctor(id, title, getGender().getValue(), getGivenName(), getFamilyName());
	}
}
