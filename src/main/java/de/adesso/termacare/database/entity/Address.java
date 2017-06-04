package de.adesso.termacare.database.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "address")
public class Address implements EntityInterface {
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name = "postcode")
    private String postcode;
    @Column(name = "departure")
    private String departure;
    @Column(name = "address")
    private String address;
    @Column(name = "homeNumber")
    private String number;
    
    String getCompressedDeparture() {
        return postcode + " " + departure;
    }
    
    String getCompressedAddress() {
        return address + " " + number;
    }
}
