package de.adesso.termacare.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "doctor")
public class Doctor extends Person implements EntityInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
