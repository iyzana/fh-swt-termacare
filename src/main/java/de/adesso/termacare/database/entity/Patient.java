package de.adesso.termacare.database.entity;

import de.adesso.termacare.gui.dto.DtoPatient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity for a Patient with billing and living address
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "patient")
@ToString(callSuper = true)
public class Patient extends Person implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    @JoinColumn(name = "billingAddress")
    @Cascade(value = CascadeType.ALL)
    private Address billingAddress;
    
    @OneToOne()
    @JoinColumn(name = "livingAddress")
    @Cascade(value = CascadeType.ALL)
    private Address livingAddress;
    
    public DtoPatient toDAO() {
        return new DtoPatient(id, getGender().getValue(), getTitle(), getGivenName(), getFamilyName(),
                getLivingAddress().getCompressedDeparture(), getLivingAddress().getCompressedAddress(),
                getBillingAddress().getCompressedDeparture(), getBillingAddress().getCompressedAddress());
    }
}
