package de.adesso.termacare.database.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for saving an address including postcode, departure, address and number
 */
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
    
    /**
     * Format the city and postcode for user output
     * @return formatted string
     */
    String getCompressedDeparture() {
        return postcode + " " + departure;
    }
    
    /**
     * Format the number and address for user output
     * @return formatted string
     */
    String getCompressedAddress() {
        return address + " " + number;
    }
}
