package de.adesso.termacare.database.entity;

import de.adesso.termacare.gui.dto.DtoDoctor;
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

	public DtoDoctor toDAO(){
		return new DtoDoctor(id, title, getGender().getValue(), getGivenName(), getFamilyName());
	}
}